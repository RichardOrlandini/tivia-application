package com.tivia.tivia.controller;

import com.tivia.tivia.domain.Beneficiario;
import com.tivia.tivia.service.BeneficiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficiarios")
public class BeneficiarioController {

    @Autowired
    private BeneficiarioService beneficiarioService;

    @PostMapping
    public ResponseEntity<Beneficiario> createBeneficiario(@RequestBody Beneficiario beneficiario) {
        Beneficiario savedBeneficiario = beneficiarioService.save(beneficiario);
        return new ResponseEntity<>(savedBeneficiario, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Beneficiario> getAllBeneficiarios() {
        return beneficiarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beneficiario> getBeneficiarioById(@PathVariable Long id) {
        return beneficiarioService.findById(id)
                .map(beneficiario -> ResponseEntity.ok(beneficiario))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Beneficiario> updateBeneficiario(@PathVariable Long id, @RequestBody Beneficiario beneficiario) {
        return beneficiarioService.update(id, beneficiario)
                .map(updatedBeneficiario -> ResponseEntity.ok(updatedBeneficiario))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeneficiario(@PathVariable Long id) {
        if (beneficiarioService.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

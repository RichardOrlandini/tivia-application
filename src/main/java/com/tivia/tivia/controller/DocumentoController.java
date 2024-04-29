package com.tivia.tivia.controller;

import com.tivia.tivia.domain.Documento;
import com.tivia.tivia.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @PostMapping
    public ResponseEntity<Documento> createDocumento(@RequestBody Documento documento) {
        Documento savedDocumento = documentoService.save(documento);
        return new ResponseEntity<>(savedDocumento, HttpStatus.CREATED);
    }

    @GetMapping("/by-beneficiario/{beneficiarioId}")
    public List<Documento> getDocumentosByBeneficiario(@PathVariable Long beneficiarioId) {
        return documentoService.findByBeneficiarioId(beneficiarioId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Documento> updateDocumento(@PathVariable Long id, @RequestBody Documento documento) {
        return documentoService.update(id, documento)
                .map(updatedDocumento -> ResponseEntity.ok(updatedDocumento))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocumento(@PathVariable Long id) {
        if (documentoService.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
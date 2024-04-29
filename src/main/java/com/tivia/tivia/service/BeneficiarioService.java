package com.tivia.tivia.service;

import com.tivia.tivia.domain.Beneficiario;
import com.tivia.tivia.repository.BeneficiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiarioService {

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    public Beneficiario save(Beneficiario beneficiario) {
        return beneficiarioRepository.save(beneficiario);
    }

    public List<Beneficiario> findAll() {
        return beneficiarioRepository.findAll();
    }

    public Optional<Beneficiario> findById(Long id) {
        return beneficiarioRepository.findById(id);
    }

    public Optional<Beneficiario> update(Long id, Beneficiario beneficiario) {
        return beneficiarioRepository.findById(id)
                .map(existingBeneficiario -> {
                    existingBeneficiario.setNome(beneficiario.getNome());
                    existingBeneficiario.setTelefone(beneficiario.getTelefone());
                    existingBeneficiario.setDataNascimento(beneficiario.getDataNascimento());
                    return beneficiarioRepository.save(existingBeneficiario);
                });
    }

    public boolean delete(Long id) {
        return beneficiarioRepository.findById(id)
                .map(beneficiario -> {
                    beneficiarioRepository.delete(beneficiario);
                    return true;
                }).orElse(false);
    }
}

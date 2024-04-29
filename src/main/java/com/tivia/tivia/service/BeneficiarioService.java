package com.tivia.tivia.service;

import com.tivia.tivia.domain.Beneficiario;
import com.tivia.tivia.domain.Documento;
import com.tivia.tivia.repository.BeneficiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BeneficiarioService {

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @Autowired
    private DocumentoService documentoService;


    public Beneficiario save(Beneficiario beneficiario) {
        Set<Documento> documentos = beneficiario.getDocumentos();

        // Salvar o beneficiário primeiro sem os documentos
        beneficiario.setDocumentos(null);
        Beneficiario beneficiarioSaved = beneficiarioRepository.save(beneficiario);

        // Se houver documentos, vincule cada documento ao beneficiário salvo
        if (documentos != null && !documentos.isEmpty()) {
            for (Documento documento : documentos) {
                documento.setBeneficiario(beneficiarioSaved);
                documentoService.save(documento);
            }
            beneficiarioSaved.setDocumentos(documentos);
        }

        return beneficiarioSaved;
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
                    existingBeneficiario.setDataAtualizacao(new Date());
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

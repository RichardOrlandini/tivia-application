package com.tivia.tivia.service;

import com.tivia.tivia.domain.Documento;
import com.tivia.tivia.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    public Documento save(Documento documento) {
        return documentoRepository.save(documento);
    }

    public List<Documento> findByBeneficiarioId(Long beneficiarioId) {
        return documentoRepository.findByBeneficiarioId(beneficiarioId);
    }

    public Optional<Documento> update(Long id, Documento documento) {
        return documentoRepository.findById(id)
                .map(existingDocumento -> {
                    existingDocumento.setTipoDocumento(documento.getTipoDocumento());
                    existingDocumento.setDescricao(documento.getDescricao());
                    return documentoRepository.save(existingDocumento);
                });
    }

    public boolean delete(Long id) {
        return documentoRepository.findById(id)
                .map(documento -> {
                    documentoRepository.delete(documento);
                    return true;
                }).orElse(false);
    }
}
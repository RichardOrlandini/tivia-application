package com.tivia.tivia.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "documento")
@Data
@NoArgsConstructor
public class Documento extends DomainEntity {

    @Column(name = "tipo_documento", nullable = false, length = 50)
    private String tipoDocumento;

    @Column
    @Lob // Usado para textos longos
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beneficiario_id", nullable = false)
    private Beneficiario beneficiario;
}

package com.tivia.tivia.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "beneficiario")
@Data
@NoArgsConstructor
public class Beneficiario extends DomainEntity {

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(length = 255)
    private String telefone;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Documento> documentos = new HashSet<>();

}

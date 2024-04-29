CREATE SEQUENCE hibernate_sequence START 1 INCREMENT 1;

CREATE TABLE beneficiario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(255),
    data_nascimento DATE NOT NULL,
    data_inclusao TIMESTAMP,
    data_atualizacao TIMESTAMP
);

CREATE TABLE documento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_documento VARCHAR(50) NOT NULL,
    descricao TEXT,
    data_inclusao TIMESTAMP,
    data_atualizacao TIMESTAMP,
    beneficiario_id BIGINT,
    FOREIGN KEY (beneficiario_id) REFERENCES beneficiario(id)
);
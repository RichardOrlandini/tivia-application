CREATE SEQUENCE hibernate_sequence START 1 INCREMENT 1;

-- Criação da tabela Beneficiario com a coluna id como PRIMARY KEY
CREATE TABLE beneficiario (
    id BIGINT NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(255),
    data_nascimento DATE NOT NULL,
    data_inclusao TIMESTAMP,
    data_atualizacao TIMESTAMP
);

-- Criação da tabela Documento com uma FOREIGN KEY que referencia a tabela Beneficiario
CREATE TABLE documento (
    id BIGINT NOT NULL PRIMARY KEY,
    tipo_documento VARCHAR(50) NOT NULL,
    descricao TEXT,
    data_inclusao TIMESTAMP,
    data_atualizacao TIMESTAMP,
    beneficiario_id BIGINT,
    FOREIGN KEY (beneficiario_id) REFERENCES beneficiario(id)
);
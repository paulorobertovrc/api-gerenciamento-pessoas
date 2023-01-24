CREATE TABLE pessoa (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    nascimento DATE NOT NULL,
    endereco_principal BIGINT,

    PRIMARY KEY (id)
);

CREATE TABLE endereco (
    id BIGINT NOT NULL AUTO_INCREMENT,
    logradouro VARCHAR(255) NOT NULL,
    numero VARCHAR(20) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    pessoa_id BIGINT,
    principal BOOLEAN DEFAULT FALSE,

    PRIMARY KEY (id)
);

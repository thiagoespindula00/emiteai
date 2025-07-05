CREATE TABLE pessoas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    cpf VARCHAR(14) UNIQUE NOT NULL,
    numero CHAR(10),
    complemento VARCHAR(255),
    cep CHAR(10) NOT NULL,
    bairro VARCHAR(100),
    municipio VARCHAR(100),
    estado CHAR(2)
);

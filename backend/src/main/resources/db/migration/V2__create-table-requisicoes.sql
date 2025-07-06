CREATE TABLE requisicoes(
    id SERIAL PRIMARY KEY,
    metodo_http VARCHAR(10),
    endpoint TEXT,
    data_hora TIMESTAMP
);
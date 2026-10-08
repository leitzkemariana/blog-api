CREATE TABLE tb_post (
    id BINARY(16) NOT NULL,
    autor VARCHAR(70) NOT NULL,
    data DATE NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    texto TEXT NOT NULL,
    PRIMARY KEY (id)
);
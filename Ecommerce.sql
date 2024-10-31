CREATE DATABASE Ecomerce;
USE Ecomerce;
SHOW TABLES;

CREATE TABLE status_pedido (
    status_pedido_id SMALLINT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(255) NOT NULL
);

CREATE TABLE pagamento (
    pagamento_id SMALLINT PRIMARY KEY AUTO_INCREMENT,
    metodo_pagamento VARCHAR(255) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL
);

CREATE TABLE cliente (
    cliente_id SMALLINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(11) NOT NULL,
    data_cadastro DATE NOT NULL
);

CREATE TABLE venda (
    venda_id SMALLINT PRIMARY KEY AUTO_INCREMENT,
    cliente_id SMALLINT,
    status_pedido_id SMALLINT,
    pagamento_id SMALLINT,
    data_venda DATE NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id),
    FOREIGN KEY (status_pedido_id) REFERENCES status_pedido(status_pedido_id),
    FOREIGN KEY (pagamento_id) REFERENCES pagamento(pagamento_id)
);

ALTER TABLE cliente
ADD venda_id SMALLINT,
ADD FOREIGN KEY (venda_id) REFERENCES venda(venda_id);

CREATE TABLE endereco (
    endereco_id SMALLINT PRIMARY KEY AUTO_INCREMENT,
    cliente_id SMALLINT,
    endereco VARCHAR(255) NOT NULL,
    cidade VARCHAR(30) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id)
);

CREATE TABLE categoria (
    categoria_id SMALLINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE fornecedor (
    fornecedor_id SMALLINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    contato VARCHAR(11) NOT NULL
);

CREATE TABLE produtos (
    produtos_id SMALLINT PRIMARY KEY AUTO_INCREMENT,
    fornecedor_id SMALLINT,
    categoria_id SMALLINT,
    nome VARCHAR(255) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    estoque INT NOT NULL,
    FOREIGN KEY (fornecedor_id) REFERENCES fornecedor(fornecedor_id),
    FOREIGN KEY (categoria_id) REFERENCES categoria(categoria_id)
);

CREATE TABLE carrinho (
    carrinho_id SMALLINT PRIMARY KEY AUTO_INCREMENT,
    cliente_id SMALLINT,
    produto_id SMALLINT,
    fornecedor_id SMALLINT,
    categoria_id SMALLINT,
    quantidade INT,
    FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id),
    FOREIGN KEY (produto_id) REFERENCES produtos(produtos_id),
    FOREIGN KEY (fornecedor_id) REFERENCES fornecedor(fornecedor_id),
    FOREIGN KEY (categoria_id) REFERENCES categoria(categoria_id)
);

CREATE TABLE itens_venda (
    itens_venda_id SMALLINT PRIMARY KEY AUTO_INCREMENT,
    venda_id SMALLINT,
    categoria_id SMALLINT,
    produto_id SMALLINT,
    fornecedor_id SMALLINT,
    quantidade INT NOT NULL,
    FOREIGN KEY (venda_id) REFERENCES venda(venda_id),
    FOREIGN KEY (categoria_id) REFERENCES categoria(categoria_id),
    FOREIGN KEY (produto_id) REFERENCES produtos(produtos_id),
    FOREIGN KEY (fornecedor_id) REFERENCES fornecedor(fornecedor_id)
);



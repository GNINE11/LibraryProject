DROP DATABASE IF EXISTS LibrarySchema;

CREATE DATABASE IF NOT EXISTS LibrarySchema DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE LibrarySchema;

-- 1. cliente
CREATE TABLE IF NOT EXISTS cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(20),
    dataCadastro DATETIME DEFAULT CURRENT_TIMESTAMP,
    tipo ENUM('CLIENTE', 'ADMIN') DEFAULT 'CLIENTE'
) ENGINE=InnoDB;

-- 2. endereco
CREATE TABLE IF NOT EXISTS endereco (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    rua VARCHAR(150) NOT NULL,
    numero VARCHAR(20) NOT NULL,
    complemento VARCHAR(100),
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado CHAR(2) NOT NULL,
    cep CHAR(9) NOT NULL,
    is_principal BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- 3. categoria
CREATE TABLE IF NOT EXISTS categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB;

-- 4. livro
CREATE TABLE IF NOT EXISTS livro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    descricao TEXT,
    imagem_url VARCHAR(255),
    isbn VARCHAR(20) UNIQUE,
    preco DECIMAL(10, 2) NOT NULL,
    estoqueDisponivel INT NOT NULL DEFAULT 0,
    condicao ENUM('NOVO', 'USADO') NOT NULL DEFAULT 'NOVO',
    dataCadastro DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- 4.1. livro_categoria (relacionamento muitos-para-muitos)
CREATE TABLE IF NOT EXISTS livro_categoria (
    livro_id INT NOT NULL,
    categoria_id INT NOT NULL,
    PRIMARY KEY (livro_id, categoria_id),
    FOREIGN KEY (livro_id) REFERENCES livro(id) ON DELETE CASCADE,
    FOREIGN KEY (categoria_id) REFERENCES categoria(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- 5. pedido
CREATE TABLE IF NOT EXISTS pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    endereco_id INT NOT NULL,
    dataPedido DATETIME DEFAULT CURRENT_TIMESTAMP,
    status ENUM('PENDENTE', 'PROCESSANDO', 'ENVIADO', 'ENTREGUE', 'CANCELADO') DEFAULT 'PENDENTE',
    valorTotal DECIMAL(10, 2) NOT NULL,
    metodo_pagamento ENUM('CARTAO', 'BOLETO', 'PIX'),
    FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    FOREIGN KEY (endereco_id) REFERENCES endereco(id)
) ENGINE=InnoDB;

-- 6. itempedido
CREATE TABLE IF NOT EXISTS itempedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pedido_id INT NOT NULL,
    livro_id INT NOT NULL,
    quantidade INT NOT NULL DEFAULT 1,
    precoUnitario DECIMAL(10, 2) NOT NULL,
    condicao ENUM('NOVO', 'USADO') NOT NULL DEFAULT 'NOVO',
    FOREIGN KEY (pedido_id) REFERENCES pedido(id) ON DELETE CASCADE,
    FOREIGN KEY (livro_id) REFERENCES livro(id)
) ENGINE=InnoDB;

-- 7. carrinho
CREATE TABLE IF NOT EXISTS carrinho (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL UNIQUE,
    dataCriacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- 8. itemcarrinho
CREATE TABLE IF NOT EXISTS itemcarrinho (
    carrinho_id INT NOT NULL,
    livro_id INT NOT NULL,
    quantidade INT NOT NULL DEFAULT 1,
    condicao ENUM('NOVO', 'USADO') NOT NULL,
    PRIMARY KEY (carrinho_id, livro_id, condicao),
    FOREIGN KEY (carrinho_id) REFERENCES carrinho(id) ON DELETE CASCADE,
    FOREIGN KEY (livro_id) REFERENCES livro(id)
) ENGINE=InnoDB;

-- 9. pagamento
CREATE TABLE IF NOT EXISTS pagamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pedido_id INT NOT NULL UNIQUE,
    valor DECIMAL(10,2) NOT NULL,
    metodo ENUM('CARTAO_CREDITO', 'CARTAO_DEBITO', 'PIX', 'BOLETO') NOT NULL,
    status ENUM('PENDENTE', 'APROVADO', 'RECUSADO', 'ESTORNADO') DEFAULT 'PENDENTE',
    dataPagamento DATETIME,
    codigo_transacao VARCHAR(100),
    FOREIGN KEY (pedido_id) REFERENCES pedido(id)
) ENGINE=InnoDB;

-- Índices
CREATE INDEX idx_livro_titulo ON livro(titulo);
CREATE INDEX idx_livro_autor ON livro(autor);
CREATE INDEX idx_pedido_cliente ON pedido(cliente_id);
CREATE INDEX idx_pedido_status ON pedido(status);

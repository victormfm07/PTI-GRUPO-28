-- =============================================
-- SAÚDE FÁCIL – Script de criação do banco
-- Banco de dados: PostgreSQL
-- =============================================

CREATE DATABASE saudefacil;

\c saudefacil;

-- Tabela de usuários
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(20)
);

-- Tabela de unidades de saúde
CREATE TABLE unidades_saude (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(200) NOT NULL,
    bairro VARCHAR(100),
    cidade VARCHAR(100) NOT NULL,
    tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('UBS', 'UPA', 'Hospital')),
    telefone VARCHAR(20),
    horario_funcionamento VARCHAR(100)
);

-- Tabela de agendamentos
CREATE TABLE agendamentos (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER NOT NULL REFERENCES usuarios(id),
    unidade_id INTEGER NOT NULL REFERENCES unidades_saude(id),
    data_consulta DATE NOT NULL,
    horario_consulta TIME NOT NULL,
    especialidade VARCHAR(100) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'AGENDADO'
        CHECK (status IN ('AGENDADO','CONFIRMADO','CANCELADO','REALIZADO')),
    observacoes TEXT
);

-- Dados de exemplo
INSERT INTO unidades_saude (nome, endereco, bairro, cidade, tipo, telefone, horario_funcionamento)
VALUES
    ('UBS Vila Mariana', 'Rua Domingos de Morais, 2564', 'Vila Mariana', 'São Paulo', 'UBS', '(11) 5555-1001', 'Seg-Sex 07h-19h'),
    ('UPA Santana', 'Av. Cruzeiro do Sul, 2630', 'Santana', 'São Paulo', 'UPA', '(11) 5555-1002', '24 horas'),
    ('Hospital Municipal Centro', 'Rua Voluntários da Pátria, 4301', 'Centro', 'São Paulo', 'Hospital', '(11) 5555-1003', '24 horas');

INSERT INTO usuarios (nome, cpf, email, senha, telefone)
VALUES
    ('Maria Aparecida Silva', '123.456.789-00', 'maria@email.com', '123456', '(11) 99999-0001');

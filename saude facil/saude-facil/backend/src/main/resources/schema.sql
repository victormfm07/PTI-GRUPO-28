USE saudefacil;

CREATE TABLE IF NOT EXISTS usuarios (
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome     VARCHAR(150) NOT NULL,
    cpf      VARCHAR(14)  NOT NULL UNIQUE,
    email    VARCHAR(150) NOT NULL UNIQUE,
    senha    VARCHAR(255) NOT NULL,
    telefone VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS unidades_saude (
    id                    BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome                  VARCHAR(200) NOT NULL,
    endereco              VARCHAR(250) NOT NULL,
    bairro                VARCHAR(100) NOT NULL,
    cidade                VARCHAR(100) NOT NULL,
    telefone              VARCHAR(20),
    tipo                  VARCHAR(20)  NOT NULL,
    horario_funcionamento VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS agendamentos (
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id       BIGINT NOT NULL,
    unidade_id       BIGINT NOT NULL,
    data_consulta    DATE   NOT NULL,
    horario_consulta TIME   NOT NULL,
    especialidade    VARCHAR(100),
    status           VARCHAR(20) NOT NULL DEFAULT 'AGENDADO',
    observacoes      TEXT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (unidade_id) REFERENCES unidades_saude(id)
);

-- Dados de exemplo
INSERT INTO usuarios (nome, cpf, email, senha, telefone)
VALUES ('Maria Aparecida Silva', '123.456.789-00', 'maria@email.com', '123456', '(11) 99999-0001');

INSERT INTO unidades_saude (nome, endereco, bairro, cidade, telefone, tipo, horario_funcionamento) VALUES
    ('UBS Jardim São Paulo',          'Rua das Flores, 100',       'Jardim São Paulo', 'São Paulo', '(11) 3333-0001', 'UBS',      'Seg-Sex 07h às 19h'),
    ('UPA Santana',                   'Av. Cruzeiro do Sul, 500',  'Santana',          'São Paulo', '(11) 3333-0002', 'UPA',      '24 horas'),
    ('Hospital Municipal Sorocabana', 'Rua Pedro de Toledo, 1800', 'Vila Clementino',  'São Paulo', '(11) 3333-0003', 'Hospital', '24 horas'),
    ('UBS Vila Madalena',             'Rua Harmonia, 200',         'Vila Madalena',    'São Paulo', '(11) 3333-0004', 'UBS',      'Seg-Sex 07h às 17h');
    
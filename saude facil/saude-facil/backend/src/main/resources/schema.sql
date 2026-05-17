-- SQLite3 Schema para Saúde Fácil

CREATE TABLE IF NOT EXISTS usuarios (
    id       INTEGER PRIMARY KEY AUTOINCREMENT,
    nome     TEXT NOT NULL,
    cpf      TEXT NOT NULL UNIQUE,
    email    TEXT NOT NULL UNIQUE,
    senha    TEXT NOT NULL,
    telefone TEXT
);

CREATE TABLE IF NOT EXISTS unidades_saude (
    id                    INTEGER PRIMARY KEY AUTOINCREMENT,
    nome                  TEXT NOT NULL,
    endereco              TEXT NOT NULL,
    bairro                TEXT NOT NULL,
    cidade                TEXT NOT NULL,
    telefone              TEXT,
    tipo                  TEXT NOT NULL,
    horario_funcionamento TEXT
);

CREATE TABLE IF NOT EXISTS agendamentos (
    id               INTEGER PRIMARY KEY AUTOINCREMENT,
    usuario_id       INTEGER NOT NULL,
    unidade_id       INTEGER NOT NULL,
    data_consulta    TEXT NOT NULL,
    horario_consulta TEXT NOT NULL,
    especialidade    TEXT,
    status           TEXT NOT NULL DEFAULT 'AGENDADO',
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
    
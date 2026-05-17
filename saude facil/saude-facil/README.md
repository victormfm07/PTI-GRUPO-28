# 🏥 Saúde Fácil - Sistema de Agendamento de Consultas

**Status:** ✅ Migrado para SQLite3 e Pronto para Usar

---

## 🚀 Início Rápido

### Compilar e Rodar em 3 passos:

```bash
# 1. Entre no backend
cd backend

# 2. Compile
mvn clean install

# 3. Execute
mvn spring-boot:run
```

A API estará disponível em: **http://localhost:8080**

### Verificar Status

```bash
curl http://localhost:8080/api/status
```

Resposta esperada:
```json
{
  "api": "online",
  "horario": "17/05/2026 14:30:45",
  "banco": "conectado",
  "produto": "SQLite",
  "versao": "3.44.0"
}
```

---

## 📚 Documentação

- **[SETUP_SQLITE.md](SETUP_SQLITE.md)** - Guia completo de setup e uso
- **[ALTERACOES_MIGRACAO.md](ALTERACOES_MIGRACAO.md)** - Resumo das alterações
- **[CHECKLIST.md](CHECKLIST.md)** - Verificação completa do status

---

## 🏗️ Arquitetura

### Backend (Spring Boot 3.2.0)
```
├── model/       → Entidades JPA
├── repository/  → Acesso a dados
├── service/     → Lógica de negócio
├── controller/  → Endpoints REST
└── config/      → Configurações
```

### Banco de Dados (SQLite3)
- **usuarios** - Usuários do sistema
- **unidades_saude** - Unidades de saúde
- **agendamentos** - Agendamentos de consultas

---

## 📡 Endpoints Principais

### Usuários
- `GET /api/usuarios` - Listar todos
- `POST /api/usuarios` - Criar novo
- `GET /api/usuarios/{id}` - Obter por ID
- `PUT /api/usuarios/{id}` - Atualizar
- `DELETE /api/usuarios/{id}` - Deletar

### Unidades de Saúde
- `GET /api/unidades` - Listar todas
- `GET /api/unidades/cidade/{cidade}` - Buscar por cidade
- `GET /api/unidades/tipo/{tipo}` - Buscar por tipo

### Agendamentos
- `GET /api/agendamentos` - Listar todos
- `POST /api/agendamentos` - Criar novo
- `GET /api/agendamentos/{id}` - Obter por ID
- `PUT /api/agendamentos/{id}` - Atualizar
- `DELETE /api/agendamentos/{id}` - Deletar

### Status
- `GET /api/status` - Health check da API e banco

---

## 📊 Dados de Exemplo

### Usuário Padrão
```
ID: 1
Nome: Maria Aparecida Silva
CPF: 123.456.789-00
Email: maria@email.com
Senha: 123456
Telefone: (11) 99999-0001
```

### Unidades de Saúde
1. **UBS Jardim São Paulo** - Rua das Flores, 100 - Seg-Sex 07h às 19h
2. **UPA Santana** - Av. Cruzeiro do Sul, 500 - 24 horas
3. **Hospital Municipal Sorocabana** - Rua Pedro de Toledo, 1800 - 24 horas
4. **UBS Vila Madalena** - Rua Harmonia, 200 - Seg-Sex 07h às 17h

---

## 🔧 Tecnologias

| Camada | Tecnologia | Versão |
|--------|-----------|--------|
| **Framework** | Spring Boot | 3.2.0 |
| **ORM** | Hibernate/JPA | 6.0+ |
| **Banco** | SQLite3 | 3.44.0 |
| **Java** | Java | 17 |
| **Build** | Maven | 3.8+ |

---

## 📁 Estrutura do Projeto

```
saude-facil/
├── backend/                           # API Rest (Spring Boot)
│   ├── src/main/java/com/saudeFacil/
│   ├── src/main/resources/
│   └── pom.xml                        # Dependências
├── database/
│   ├── banco.db                       # Banco SQLite3
│   ├── init_db.py                     # Script de inicialização
│   └── verificar_banco.py             # Script de verificação
├── frontend/                          # (Frontend - se houver)
├── README.md                          # Este arquivo
├── SETUP_SQLITE.md                    # Guia de setup
├── ALTERACOES_MIGRACAO.md             # Resumo das mudanças
└── CHECKLIST.md                       # Verificação completa
```

---

## 🐛 Troubleshooting

### Erro: "The file is a valid SQLite3 database"
```bash
# Recrie o banco
python database/init_db.py
```

### Erro: "Tabelas não encontradas"
```bash
# Compile novamente
mvn clean install
# Depois rode a aplicação
mvn spring-boot:run
```

### Erro: "Port 8080 already in use"
```bash
# Use outra porta em application.properties
server.port=8081
```

---

## 📝 Exemplo de Uso da API

### Listar Usuários
```bash
curl http://localhost:8080/api/usuarios
```

### Criar Novo Usuário
```bash
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "cpf": "123.456.789-01",
    "email": "joao@email.com",
    "senha": "senha123",
    "telefone": "(11) 98888-0001"
  }'
```

### Criar Agendamento
```bash
curl -X POST http://localhost:8080/api/agendamentos \
  -H "Content-Type: application/json" \
  -d '{
    "usuario_id": 1,
    "unidade_id": 1,
    "dataConsulta": "2026-05-20",
    "horarioConsulta": "14:30:00",
    "especialidade": "Clínico Geral",
    "status": "AGENDADO",
    "observacoes": "Primeira consulta"
  }'
```

---

## 🔄 Migração de Banco de Dados

O projeto foi **migrado de MySQL para SQLite3**:

### Benefícios
- ✅ Sem necessidade de servidor externo
- ✅ Setup mais rápido para desenvolvimento
- ✅ Arquivo único fácil de backup
- ✅ Ideal para prototipagem

### Limitações
- ⚠️ Concorrência limitada (1-2 usuários simultâneos)
- ⚠️ Não recomendado para produção em larga escala
- ⚠️ Arquivo binário (não é ideal em versionamento)

Para detalhes completos, veja [ALTERACOES_MIGRACAO.md](ALTERACOES_MIGRACAO.md)

---

## 📦 Como Resetar o Banco

Se precisar recriar o banco com dados iniciais:

```bash
cd database
python init_db.py
```

---

## ✨ Verificar Integridade

Para verificar se o banco está OK:

```bash
cd database
python verificar_banco.py
```

Resultado esperado:
```
✅ BANCO DE DADOS ESTÁ PRONTO PARA USAR!
```

---

## 🚢 Deploy para Produção

Para usar em produção com múltiplos usuários:

1. Migre para **PostgreSQL** ou **MySQL**
2. Atualize `pom.xml` com o driver correto
3. Configure credenciais em `application.properties`
4. Redeploy

---

## 📞 Suporte

Para problemas específicos, veja:
- [SETUP_SQLITE.md](SETUP_SQLITE.md) - Troubleshooting detalhado
- [CHECKLIST.md](CHECKLIST.md) - Verificação completa

---

## 📜 Licença

Projeto: Saúde Fácil - Sistema de Agendamento  
Versão: 1.0  
Data: 17 de Maio de 2026  
Status: ✅ Pronto para Usar

---

**Desenvolvido com ❤️ por GitHub Copilot**

# 🏥 Saúde Fácil - Guia de Setup com SQLite3

## ✅ Configuração Concluída

O projeto foi migrado de MySQL para SQLite3. Aqui está o status:

### 📝 Alterações Realizadas

#### Backend (Spring Boot)
- ✅ **pom.xml**: Substituído driver MySQL por SQLite3 (xerial/sqlite-jdbc)
- ✅ **application.properties**: Configurado para usar SQLite em `../database/banco.db`
- ✅ **schema.sql**: Adaptado para sintaxe SQLite3 (INTEGER PRIMARY KEY AUTOINCREMENT)
- ✅ **ConfiguracaoBancoDados.java**: Simplificado para deixar Spring Boot gerenciar DataSource
- ✅ **banco.db**: Criado e populado com schema e dados iniciais

#### Banco de Dados
- ✅ **Banco SQLite3**: `database/banco.db` (8192 bytes - tamanho de página SQLite padrão)
- ✅ **Tabelas Criadas**:
  - `usuarios` (1 registro de exemplo)
  - `unidades_saude` (4 registros de exemplo)
  - `agendamentos` (pronta para uso)

---

## 🚀 Como Executar o Projeto

### 1️⃣ Compilar e Rodar o Backend

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

Ou diretamente com Java:
```bash
mvn clean package
java -jar target/saude-facil-1.0.0.jar
```

A API estará disponível em: `http://localhost:8080`

### 2️⃣ Verificar Status da Aplicação

Acesse em seu navegador ou terminal:
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

### 3️⃣ Endpoints Disponíveis

**Usuários:**
- `GET /api/usuarios` - Listar todos
- `POST /api/usuarios` - Criar novo
- `GET /api/usuarios/{id}` - Detalhes
- `PUT /api/usuarios/{id}` - Atualizar
- `DELETE /api/usuarios/{id}` - Deletar

**Unidades de Saúde:**
- `GET /api/unidades` - Listar todas
- `GET /api/unidades/cidade/{cidade}` - Buscar por cidade
- `GET /api/unidades/tipo/{tipo}` - Buscar por tipo

**Agendamentos:**
- `GET /api/agendamentos` - Listar todos
- `POST /api/agendamentos` - Criar novo
- `GET /api/agendamentos/{id}` - Detalhes
- `PUT /api/agendamentos/{id}` - Atualizar

---

## 📊 Dados Iniciais no Banco

### Usuário
```
ID: 1
Nome: Maria Aparecida Silva
CPF: 123.456.789-00
Email: maria@email.com
Telefone: (11) 99999-0001
```

### Unidades de Saúde
1. **UBS Jardim São Paulo** - Seg-Sex 07h às 19h
2. **UPA Santana** - 24 horas
3. **Hospital Municipal Sorocabana** - 24 horas
4. **UBS Vila Madalena** - Seg-Sex 07h às 17h

---

## 🔧 Configurações Importantes

### application.properties
```properties
server.port=8080
spring.datasource.url=jdbc:sqlite:../database/banco.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLiteDialect
```

### Mudanças no Banco de Dados
Se precisar resetar o banco para dados iniciais, execute:

```bash
python database/init_db.py
```

Isso recriará o banco.db com o schema e dados de exemplo.

---

## 📦 Estrutura do Projeto

```
saude-facil/
├── backend/                           # API Spring Boot
│   ├── src/main/java/com/saudeFacil/
│   │   ├── model/                    # Entidades JPA
│   │   ├── repository/               # Interfaces de acesso a dados
│   │   ├── service/                  # Lógica de negócio
│   │   ├── controller/               # Endpoints REST
│   │   └── config/                   # Configurações
│   ├── src/main/resources/
│   │   ├── application.properties    # Configuração da aplicação
│   │   └── schema.sql                # Script SQL do banco
│   └── pom.xml                       # Dependências Maven
├── database/
│   ├── banco.db                      # Banco SQLite3 (criado automaticamente)
│   └── init_db.py                    # Script Python para inicializar banco
└── frontend/                          # (Frontend - se houver)
```

---

## 🐛 Troubleshooting

### Erro: "JDBC URL não válida"
- Certifique-se que `../database/banco.db` é um caminho relativo válido
- Alternativa: Use caminho absoluto em application.properties

### Erro: "Banco de dados bloqueado"
- SQLite pode ter file locking
- Feche outras conexões e tente novamente
- No Windows: Reinicie o terminal PowerShell

### Erro: "Tabelas não encontradas"
- Execute: `mvn clean install` para recompilar
- Verifique se `spring.jpa.hibernate.ddl-auto=update` está em application.properties
- Ou recrie o banco: `python database/init_db.py`

---

## 📝 Notas Importantes

- SQLite é **arquivo-based** (não cliente-servidor como MySQL)
- Ideal para desenvolvimento e prototipagem
- Para produção com múltiplos usuários, considere migrar para PostgreSQL/MySQL
- O arquivo `banco.db` é versionável com Git (já contém dados de exemplo)

---

## 🔄 Migrando de volta para MySQL (se necessário)

1. Instale MySQL Connector em pom.xml
2. Atualize application.properties com credenciais MySQL
3. Altere em ConfiguracaoBancoDados.java se usar configuração custom
4. Execute migration dos dados do SQLite para MySQL

---

**Última atualização:** 17/05/2026  
**Status:** ✅ Pronto para usar com SQLite3

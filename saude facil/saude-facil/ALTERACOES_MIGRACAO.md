# Resumo de Alterações - Migração MySQL → SQLite3

## 📋 Arquivos Modificados

### 1. `backend/pom.xml`
**O quê:** Substituído driver de banco de dados
- ❌ Removido: `com.mysql.mysql-connector-j` (MySQL)
- ✅ Adicionado: `org.xerial.sqlite-jdbc` (SQLite3 v3.44.0.0)

### 2. `backend/src/main/resources/application.properties`
**O quê:** Reconfigurado para usar SQLite3
```properties
# ANTES (MySQL):
spring.datasource.url=jdbc:mysql://localhost:3306/saudefacil?...
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# DEPOIS (SQLite):
spring.datasource.url=jdbc:sqlite:../database/banco.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLiteDialect
spring.datasource.username= (vazio)
spring.datasource.password= (vazio)
```

### 3. `backend/src/main/resources/schema.sql`
**O quê:** Adaptado para sintaxe SQLite3
- ❌ Removido: `USE saudefacil;`
- ✅ Alterado: `BIGINT AUTO_INCREMENT` → `INTEGER PRIMARY KEY AUTOINCREMENT`
- ✅ Alterado: Tipos `VARCHAR/TEXT/DATE/TIME` → `TEXT` (SQLite é flexível com tipos)

### 4. `backend/src/main/java/com/saudeFacil/config/ConfiguracaoBancoDados.java`
**O quê:** Simplificado para não hardcodear driver MySQL
- ❌ Removida: Configuração manual de HikariCP
- ❌ Removida: Hardcoding de `com.mysql.cj.jdbc.Driver`
- ✅ Adicionada: Spring Boot Autoconfiguration (mais flexível)

### 5. `database/banco.db` (NOVO)
**O quê:** Banco de dados SQLite3 criado e populado
- ✅ Tamanho: 8192 bytes (página padrão SQLite)
- ✅ Tabelas: usuarios, unidades_saude, agendamentos
- ✅ Dados: 1 usuário + 4 unidades de saúde

### 6. `database/init_db.py` (NOVO)
**O quê:** Script Python para recriar banco de dados
- ✅ Cria tabelas SQLite3
- ✅ Popula com dados de exemplo
- ✅ Permite reset fácil do banco

### 7. `SETUP_SQLITE.md` (NOVO)
**O quê:** Guia completo de setup e uso do projeto
- ✅ Instruções de compilação
- ✅ Endpoints disponíveis
- ✅ Troubleshooting

---

## 🔄 O que NÃO foi alterado (e por quê)

### Entidades JPA (`model/*.java`)
✅ **Nenhuma alteração necessária**
- Usando `@GeneratedValue(strategy = GenerationType.IDENTITY)` que funciona com SQLite
- Tipos de dados (String, Long, LocalDate, etc.) são agnósticos ao BD

### Controllers (`controller/*.java`)
✅ **Nenhuma alteração necessária**
- REST endpoints são independentes do banco

### Services (`service/*.java`)
✅ **Nenhuma alteração necessária**
- Lógica de negócio usa JPA (agnóstica ao BD)

### Repositories (`repository/*.java`)
✅ **Nenhuma alteração necessária**
- JpaRepository funciona com qualquer BD suportado pelo Hibernate

---

## ✨ Benefícios da Migração

| Aspecto | MySQL | SQLite |
|--------|-------|--------|
| **Setup** | Servidor externo | Arquivo local |
| **Configuração** | Credenciais necessárias | Sem credenciais |
| **Desenvolvimento** | Mais complexo | Mais rápido |
| **Produção** | Recomendado | Não recomendado |
| **Tamanho** | Variável | < 1MB inicialmente |
| **Performance (leitura)** | Ótima | Ótima |
| **Concorrência** | Alta | Limitada |

---

## 🧪 Como Testar

### 1. Compilar
```bash
cd backend
mvn clean install
```

### 2. Rodar
```bash
mvn spring-boot:run
```

### 3. Testar conexão
```bash
curl http://localhost:8080/api/status
```

### 4. Listar usuários
```bash
curl http://localhost:8080/api/usuarios
```

---

## 📝 Próximos Passos (Opcional)

1. **Frontend**: Conectar aplicação web aos endpoints da API
2. **Testes**: Criar testes unitários para services
3. **Documentação**: Gerar Swagger/OpenAPI
4. **Deploy**: Considerar PostgreSQL/MySQL para produção
5. **Performance**: Adicionar índices em banco.db se necessário

---

**Versão:** 1.0  
**Data:** 17/05/2026  
**Status:** ✅ Pronto para Usar

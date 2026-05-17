import sqlite3
import os
from pathlib import Path

db_path = Path(__file__).resolve().parent / 'banco.db'

print("=" * 60)
print("[OK] VERIFICACAO FINAL - BANCO SQLite3")
print("=" * 60)

# Verificar arquivo
if os.path.exists(db_path):
    size = os.path.getsize(db_path)
    print("\nArquivo banco.db:")
    print(f"   Caminho: {db_path}")
    print(f"   Tamanho: {size} bytes")
    print("   Status: OK - Existe e e valido")
else:
    print("\n[ERRO] Arquivo nao encontrado!")
    exit(1)

try:
    conn = sqlite3.connect(db_path)
    cursor = conn.cursor()
    
    # Verificar tabelas
    cursor.execute("SELECT name FROM sqlite_master WHERE type='table'")
    tables = cursor.fetchall()
    
    print("\nTabelas criadas:")
    for table in tables:
        cursor.execute(f"SELECT COUNT(*) FROM {table[0]}")
        count = cursor.fetchone()[0]
        print(f"   OK {table[0]:<20} ({count} registros)")
    
    # Verificar schema
    print("\nSchema completo:")
    cursor.execute("SELECT sql FROM sqlite_master WHERE type='table'")
    schemas = cursor.fetchall()
    for schema in schemas:
        if schema[0]:
            table_name = schema[0].split('(')[0].replace('CREATE TABLE', '').strip()
            print(f"\n   Tabela: {table_name}")
            cursor.execute(f"PRAGMA table_info({table_name})")
            cols = cursor.fetchall()
            for col in cols:
                col_name, col_type, notnull, default, pk = col[1], col[2], col[3], col[4], col[5]
                pk_marker = " [PRIMARY KEY]" if pk else ""
                null_marker = " NOT NULL" if notnull else ""
                print(f"      - {col_name:<20} {col_type}{null_marker}{pk_marker}")
    
    conn.close()
    
    print("\n" + "=" * 60)
    print("[OK] BANCO DE DADOS ESTA PRONTO PARA USAR!")
    print("=" * 60)
    print("\nProximos passos:")
    print("   1. cd backend")
    print("   2. mvn clean install")
    print("   3. mvn spring-boot:run")
    print("   4. Acesse http://localhost:8080/api/status")
    print("=" * 60)
    
except Exception as e:
    print(f"\n[ERRO] ao verificar banco: {e}")
    exit(1)

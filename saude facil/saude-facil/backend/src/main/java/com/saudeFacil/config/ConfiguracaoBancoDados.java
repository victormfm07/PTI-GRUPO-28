package com.saudeFacil.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Configuracao do banco SQLite.
 */
@Configuration
public class ConfiguracaoBancoDados {

    @Bean
    public DataSource dataSource() {
        Path caminhoBanco = localizarBancoDados();
        criarDiretorioSeNecessario(caminhoBanco);

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlite:" + caminhoBanco.toAbsolutePath().normalize());
        config.setDriverClassName("org.sqlite.JDBC");
        config.setPoolName("SaudeFacil-SQLite-Pool");
        config.setMaximumPoolSize(1);
        config.setConnectionTestQuery("SELECT 1");

        return new HikariDataSource(config);
    }

    private Path localizarBancoDados() {
        String caminhoConfigurado = System.getenv("SAUDE_FACIL_DB");
        if (caminhoConfigurado != null && !caminhoConfigurado.isBlank()) {
            return Paths.get(caminhoConfigurado);
        }

        Path diretorioAtual = Paths.get("").toAbsolutePath();
        for (Path diretorio = diretorioAtual; diretorio != null; diretorio = diretorio.getParent()) {
            Path candidato = diretorio.resolve("database").resolve("banco.db");
            if (Files.exists(candidato)) {
                return candidato;
            }
        }

        if ("backend".equalsIgnoreCase(diretorioAtual.getFileName().toString()) && diretorioAtual.getParent() != null) {
            return diretorioAtual.getParent().resolve("database").resolve("banco.db");
        }

        return diretorioAtual.resolve("database").resolve("banco.db");
    }

    private void criarDiretorioSeNecessario(Path caminhoBanco) {
        try {
            Path diretorio = caminhoBanco.toAbsolutePath().normalize().getParent();
            if (diretorio != null) {
                Files.createDirectories(diretorio);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Nao foi possivel criar o diretorio do banco SQLite", e);
        }
    }
}

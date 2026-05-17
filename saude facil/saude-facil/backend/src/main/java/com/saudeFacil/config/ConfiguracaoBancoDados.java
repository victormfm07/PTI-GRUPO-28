package com.saudeFacil.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Configuração do pool de conexões com o MySQL usando HikariCP.
 * HikariCP já vem incluído no Spring Boot — não precisa adicionar dependência.
 *
 * As propriedades são lidas do application.properties.
 */
@Configuration
public class ConfiguracaoBancoDados {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String usuario;

    @Value("${spring.datasource.password}")
    private String senha;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();

        // Conexão
        config.setJdbcUrl(url);
        config.setUsername(usuario);
        config.setPassword(senha);
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");

        // Pool de conexões
        config.setMaximumPoolSize(10);       // máximo de conexões simultâneas
        config.setMinimumIdle(2);            // mínimo mantido em espera
        config.setConnectionTimeout(30000);  // 30s para obter uma conexão
        config.setIdleTimeout(600000);       // 10min antes de fechar conexão ociosa
        config.setMaxLifetime(1800000);      // 30min de vida máxima por conexão

        // Verificação de saúde da conexão
        config.setConnectionTestQuery("SELECT 1");
        config.setPoolName("SaudeFacil-Pool");

        return new HikariDataSource(config);
    }
}

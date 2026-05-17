package com.saudeFacil.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Endpoint de verificação de saúde da aplicação.
 * Acesse GET /api/status para checar se a API e o banco estão funcionando.
 */
@RestController
@RequestMapping("/api/status")
public class VerificacaoConexao {

    @Autowired
    private DataSource dataSource;

    @GetMapping
    public Map<String, Object> verificar() {
        Map<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("api", "online");
        resposta.put("horario", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        try (Connection conexao = dataSource.getConnection()) {
            resposta.put("banco", "conectado");
            resposta.put("produto", conexao.getMetaData().getDatabaseProductName());
            resposta.put("versao", conexao.getMetaData().getDatabaseProductVersion());
        } catch (Exception e) {
            resposta.put("banco", "erro");
            resposta.put("detalhe", e.getMessage());
        }

        return resposta;
    }
}

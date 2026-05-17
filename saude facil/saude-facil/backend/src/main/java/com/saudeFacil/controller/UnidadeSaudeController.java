package com.saudeFacil.controller;

import com.saudeFacil.model.UnidadeSaude;
import com.saudeFacil.service.UnidadeSaudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/unidades")
@CrossOrigin(origins = "*")
public class UnidadeSaudeController {

    @Autowired private UnidadeSaudeService unidadeService;

    @GetMapping
    public List<UnidadeSaude> listarTodas() {
        return unidadeService.listarTodas();
    }

    @GetMapping("/cidade/{cidade}")
    public List<UnidadeSaude> buscarPorCidade(@PathVariable String cidade) {
        return unidadeService.buscarPorCidade(cidade);
    }

    @GetMapping("/tipo/{tipo}")
    public List<UnidadeSaude> buscarPorTipo(@PathVariable String tipo) {
        return unidadeService.buscarPorTipo(tipo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeSaude> buscarPorId(@PathVariable Long id) {
        return unidadeService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UnidadeSaude salvar(@RequestBody UnidadeSaude unidade) {
        return unidadeService.salvar(unidade);
    }
}

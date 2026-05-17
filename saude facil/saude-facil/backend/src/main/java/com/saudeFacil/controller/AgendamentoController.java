package com.saudeFacil.controller;

import com.saudeFacil.model.Agendamento;
import com.saudeFacil.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
@CrossOrigin(origins = "*")
public class AgendamentoController {

    @Autowired private AgendamentoService agendamentoService;

    @GetMapping
    public List<Agendamento> listarTodos() {
        return agendamentoService.listarTodos();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Agendamento> listarPorUsuario(@PathVariable Long usuarioId) {
        return agendamentoService.listarPorUsuario(usuarioId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscarPorId(@PathVariable Long id) {
        return agendamentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{usuarioId}/{unidadeId}")
    public ResponseEntity<Agendamento> criar(
            @PathVariable Long usuarioId,
            @PathVariable Long unidadeId,
            @RequestBody Agendamento agendamento) {
        try {
            return ResponseEntity.ok(agendamentoService.criar(usuarioId, unidadeId, agendamento));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Agendamento> cancelar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(agendamentoService.cancelar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

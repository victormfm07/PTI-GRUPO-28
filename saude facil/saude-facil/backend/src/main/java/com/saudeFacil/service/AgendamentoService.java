package com.saudeFacil.service;

import com.saudeFacil.model.Agendamento;
import com.saudeFacil.repository.AgendamentoRepository;
import com.saudeFacil.repository.UsuarioRepository;
import com.saudeFacil.repository.UnidadeSaudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired private AgendamentoRepository agendamentoRepo;
    @Autowired private UsuarioRepository usuarioRepo;
    @Autowired private UnidadeSaudeRepository unidadeRepo;

    public List<Agendamento> listarTodos() {
        return agendamentoRepo.findAll();
    }

    public List<Agendamento> listarPorUsuario(Long usuarioId) {
        return agendamentoRepo.findByUsuarioId(usuarioId);
    }

    public Optional<Agendamento> buscarPorId(Long id) {
        return agendamentoRepo.findById(id);
    }

    public Agendamento criar(Long usuarioId, Long unidadeId, Agendamento agendamento) {
        agendamento.setUsuario(usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado")));
        agendamento.setUnidade(unidadeRepo.findById(unidadeId)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada")));
        agendamento.setStatus("AGENDADO");
        return agendamentoRepo.save(agendamento);
    }

    public Agendamento cancelar(Long id) {
        Agendamento agendamento = agendamentoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        agendamento.setStatus("CANCELADO");
        return agendamentoRepo.save(agendamento);
    }
}

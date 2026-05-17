package com.saudeFacil.service;

import com.saudeFacil.model.UnidadeSaude;
import com.saudeFacil.repository.UnidadeSaudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UnidadeSaudeService {

    @Autowired private UnidadeSaudeRepository unidadeRepo;

    public List<UnidadeSaude> listarTodas()                      { return unidadeRepo.findAll(); }
    public List<UnidadeSaude> buscarPorCidade(String cidade)     { return unidadeRepo.findByCidadeContainingIgnoreCase(cidade); }
    public List<UnidadeSaude> buscarPorTipo(String tipo)         { return unidadeRepo.findByTipo(tipo); }
    public Optional<UnidadeSaude> buscarPorId(Long id)           { return unidadeRepo.findById(id); }
    public UnidadeSaude salvar(UnidadeSaude unidade)             { return unidadeRepo.save(unidade); }
}

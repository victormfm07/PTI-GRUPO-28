package com.saudeFacil.repository;

import com.saudeFacil.model.UnidadeSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UnidadeSaudeRepository extends JpaRepository<UnidadeSaude, Long> {
    List<UnidadeSaude> findByCidadeContainingIgnoreCase(String cidade);
    List<UnidadeSaude> findByTipo(String tipo);
}

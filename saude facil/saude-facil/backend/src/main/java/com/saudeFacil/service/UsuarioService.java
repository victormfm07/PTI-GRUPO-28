package com.saudeFacil.service;

import com.saudeFacil.model.Usuario;
import com.saudeFacil.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired private UsuarioRepository usuarioRepo;

    public Optional<Usuario> buscarPorId(Long id)  { return usuarioRepo.findById(id); }
    public Usuario salvar(Usuario usuario)          { return usuarioRepo.save(usuario); }

    public Optional<Usuario> login(String email, String senha) {
        return usuarioRepo.findByEmail(email)
                .filter(u -> u.getSenha().equals(senha));
    }
}

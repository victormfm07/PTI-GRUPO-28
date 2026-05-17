package com.saudeFacil.controller;

import com.saudeFacil.model.Usuario;
import com.saudeFacil.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired private UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.ok(usuarioService.salvar(usuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciais) {
        return usuarioService.login(credenciais.get("email"), credenciais.get("senha"))
                .map(u -> ResponseEntity.ok((Object) u))
                .orElse(ResponseEntity.status(401).body("E-mail ou senha inválidos"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

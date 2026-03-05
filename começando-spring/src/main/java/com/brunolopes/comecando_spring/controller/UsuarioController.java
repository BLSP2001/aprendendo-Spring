package com.brunolopes.comecando_spring.controller;

import com.brunolopes.comecando_spring.business.UsuarioService;
import com.brunolopes.comecando_spring.controller.dtos.UsuarioDTO;
import com.brunolopes.comecando_spring.infrastructure.entity.Usuario;
import com.brunolopes.comecando_spring.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuario") // qual é a URI da Controller
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping //aponta o verbo HTTP que corresponde ao método
    // Response Entity = indica que o método vai retornar uma resposta HTTP//
    // Request Body = indicar que as informações do objeto usuário serão passadas no corpo da requisição//
    public ResponseEntity<Usuario> salvaUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuario));
    }

    // Método usado para fazer login
    @PostMapping("/login")
    public String login (@RequestBody UsuarioDTO usuarioDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(),
                        usuarioDTO.getSenha())
        );
         return "Bearer " + jwtUtil.generateToken(authentication.getName());

    }

    @GetMapping
    public ResponseEntity<Usuario> buscaUsuarioPorEmail(@RequestParam String email){
        return ResponseEntity.ok(usuarioService.buscaUsuarioPorEmail(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void>deletaUsuarioPorEmail(@PathVariable String email){
         usuarioService.deletaUsuarioPorEmail(email);
         return ResponseEntity.noContent().build(); // operação concluida com sucesso, mas sem resposta HTTP
    }
}

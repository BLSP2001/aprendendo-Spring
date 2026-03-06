package com.brunolopes.comecando_spring.business;

import com.brunolopes.comecando_spring.infrastructure.entity.Usuario;
import com.brunolopes.comecando_spring.infrastructure.exceptions.ConflictException;
import com.brunolopes.comecando_spring.infrastructure.exceptions.ResourceNotFoundException;
import com.brunolopes.comecando_spring.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// Injeção de Dependências = chamar a classe Repository dentro do Construtor da Service
// private final = traz a imutabilidade para a classe
// orElseThrow = método da classe Optional que vai desembrulhar o valor dentro da classe se ele for nulo, lançando uma exceção

@Service
@RequiredArgsConstructor // ela cria um construtor apenas para os atributos que usam o final.
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario salvaUsuario(Usuario usuario) {
        try {
            emailExiste(usuario.getEmail());
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            return usuarioRepository.save(usuario);
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado na base de dados" + e.getCause());
        }

    }

    public void emailExiste(String email) {
        try {
           boolean existe = verificadorEmail(email);
           if(existe) {
               throw new ConflictException("Email já cadastrado na base de dados" + email);
           }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado na base de dados" + e.getCause());
        }

        }

    public boolean verificadorEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscaUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException
                ("Email não encontrado" + email));
    }

    public void deletaUsuarioPorEmail(String email){
        // Verifica se existe antes de tentar apagar
        if (!usuarioRepository.existsByEmail(email)) {
            throw new ResourceNotFoundException("Não foi possível deletar. Usuário não encontrado: " + email);
        }
        usuarioRepository.deleteByEmail(email);
    }
}


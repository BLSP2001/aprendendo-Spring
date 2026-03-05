package com.brunolopes.comecando_spring.controller.dtos;

// DTO = é um objeto usado para transportar dados para dentro e fora de um sistema, expondo só aquilo que é necessário

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UsuarioDTO {

    private String email;
    private String senha;

}

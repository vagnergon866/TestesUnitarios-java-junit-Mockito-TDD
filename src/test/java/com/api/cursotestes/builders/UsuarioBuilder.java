package com.api.cursotestes.builders;

import com.api.cursotestes.domain.Usuario;

public class UsuarioBuilder {

    private Usuario usuario;

    private UsuarioBuilder(){

    }

    public static UsuarioBuilder umUsuario(){
        UsuarioBuilder builder = new UsuarioBuilder();
        builder.usuario = new Usuario();
        builder.usuario.setNome("Pedro Henrique");
        return builder;
    }

    public Usuario agora(){
        return usuario;
    }
}

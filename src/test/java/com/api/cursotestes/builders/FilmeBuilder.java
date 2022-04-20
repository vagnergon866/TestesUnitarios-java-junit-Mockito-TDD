package com.api.cursotestes.builders;

import com.api.cursotestes.domain.Filme;

public class FilmeBuilder {

    private Filme filme;

    private FilmeBuilder(){

    }

    public static FilmeBuilder umFilme(){
        FilmeBuilder builder = new FilmeBuilder();
        builder.filme = new Filme();
        builder.filme.setEstoque(2);
        builder.filme.setNome("Interstellar");
        builder.filme.setPrecoLocacao(4.0);
        return builder;
    }

    public FilmeBuilder filmeSemEstoque(){
        filme.setEstoque(0);
        return this;
    }

    public FilmeBuilder valorFilme(Double valor){
        filme.setPrecoLocacao(valor);
        return this;
    }

    public Filme agora(){
        return filme;
    }
}

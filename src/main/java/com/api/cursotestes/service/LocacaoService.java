package com.api.cursotestes.service;

import com.api.cursotestes.domain.Filme;
import com.api.cursotestes.domain.Locacao;
import com.api.cursotestes.domain.Usuario;
import com.api.cursotestes.exception.FilmeSemEstoqueException;
import com.api.cursotestes.exception.LocadoraException;
import com.api.cursotestes.utils.DataUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.api.cursotestes.utils.DataUtils.adicionarDias;

public class LocacaoService {

    public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) {
        if (usuario == null) {
            throw new LocadoraException("Usuario nao pode ser null");
        }

       // se filmes estiver nulo ou se filmes estiver vazio
        if (filmes == null || filmes.isEmpty()) {
            throw new LocadoraException("Filme nao pode ser null");
        }

        for (Filme filme : filmes) {
            if (filme.getEstoque() == 0) {
                throw new FilmeSemEstoqueException("Nao tem como locar um filme que nao temos em estoque!");
            }
        }

        Locacao locacao = new Locacao();
        locacao.setFilmes(filmes);
        locacao.setUsuario(usuario);
        locacao.setDataLocacao(new Date());
        Double valorTotal = 0.0;
        for (int i = 0; i < filmes.size(); i++) {
            Filme filme = filmes.get(i);
            Double valorFilme = filme.getPrecoLocacao();

            switch (i) {
                case 2:
                    valorFilme = valorFilme * 0.75;
                    break;
                case 3:
                    valorFilme = valorFilme * 0.50;
                    break;
                case 4:
                    valorFilme = valorFilme * 0.25;
                    break;
                case 5:
                    valorFilme = 0.0;
                    break;
            }

            valorTotal += valorFilme;
        }
        locacao.setValor(valorTotal);

        //Entrega no dia seguinte
        Date dataEntrega = new Date();
        dataEntrega = adicionarDias(dataEntrega, 1);
        if(DataUtils.verificarDiaSemana(dataEntrega, Calendar.SUNDAY)){
            dataEntrega = adicionarDias(dataEntrega, 1);
        }

        locacao.setDataRetorno(dataEntrega);

        //Salvando a locacao...

        return locacao;
    }

}

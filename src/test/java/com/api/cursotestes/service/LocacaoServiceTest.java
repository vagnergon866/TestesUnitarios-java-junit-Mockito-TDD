package com.api.cursotestes.service;


import com.api.cursotestes.domain.Filme;
import com.api.cursotestes.domain.Locacao;
import com.api.cursotestes.domain.Usuario;
import com.api.cursotestes.exception.FilmeSemEstoqueException;
import com.api.cursotestes.exception.LocadoraException;
import com.api.cursotestes.utils.DataUtils;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.api.cursotestes.matchers.MatchersProprios.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

public class LocacaoServiceTest {

    private LocacaoService service;

    @Before
    public void setup() {
        service = new LocacaoService();
    }

    @Test  //esse teste funciona qualquer dia da semana eceto no sabado
    public void deveAlugarFilmeTest() {
        assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        //cenario
        service = new LocacaoService();
        Usuario usuario = new Usuario("Paulo Miguel");
        List<Filme> filmes = List.of(new Filme("Interstellar", 2, 5.0));

        //acao
        Locacao locacao = service.alugarFilme(usuario, filmes);

        //verificacao
        assertThat(locacao.getValor(), is(equalTo(5.0)));
        assertThat(locacao.getDataLocacao(), ehHoje());
        assertThat(locacao.getDataRetorno(), ehHojeComDiferencaDias(1));
    }

    @Test
    public void naoDeveAlugarFilmeSemEstoqueTest() {
        //cenario
        service = new LocacaoService();
        Usuario usuario = new Usuario("Paulo Miguel");
        List<Filme> filmes = List.of(new Filme("Interstellar", 0, 5.0));

        //acao
        try {
            service.alugarFilme(usuario, filmes);
            Assertions.fail();
        } catch (FilmeSemEstoqueException e) {
            assertThat(e.getMessage(), is("Nao tem como locar um filme que nao temos em estoque!"));
        }

    }

    @Test
    public void nomeDoUsuarioNaoPodeSerNullTest() {
        //cenario
        service = new LocacaoService();
        List<Filme> filmes = List.of(new Filme("Interstellar", 2, 5.0));

        //acao
        try {
            service.alugarFilme(null, filmes);
            Assertions.fail();
        } catch (LocadoraException e) {
            assertThat(e.getMessage(), is("Usuario nao pode ser null"));
        }

    }

    @Test
    public void filmeNaoPodeSerNullTest() {
        //cenario
        service = new LocacaoService();
        Usuario usuario = new Usuario("Paulo Miguel");

        //acao
        try {
            service.alugarFilme(usuario, null);
            Assertions.fail();
        } catch (LocadoraException e) {
            assertThat(e.getMessage(), is("Filme nao pode ser null"));
        }

    }

    @Test
    public void deveDescontar25PctNoFilme3Test() {
        service = new LocacaoService();
        Usuario usuario = new Usuario("Paulo Miguel");
        List<Filme> filmes = List.of(new Filme("Interstellar", 2, 4.0)
                , new Filme("Interstellar 2", 2, 4.0)
                , new Filme("Interstellar 3", 2, 4.0));

        Locacao resultado = service.alugarFilme(usuario, filmes);

        assertThat(resultado.getValor(), is(11.0));

    }

    @Test
    public void deveDescontar50PctNoFilme4Test() {
        service = new LocacaoService();
        Usuario usuario = new Usuario("Paulo Miguel");
        List<Filme> filmes = List.of(new Filme("Interstellar", 2, 4.0)
                , new Filme("Interstellar 2", 2, 4.0)
                , new Filme("Interstellar 3", 2, 4.0)
                , new Filme("Interstellar 4", 2, 4.0));

        Locacao resultado = service.alugarFilme(usuario, filmes);

        //4+4+3+2
        assertThat(resultado.getValor(), is(13.0));

    }

    @Test
    public void deveDescontar75PctNoFilme5Test() {
        service = new LocacaoService();
        Usuario usuario = new Usuario("Paulo Miguel");
        List<Filme> filmes = List.of(new Filme("Interstellar 1", 2, 4.0)
                , new Filme("Interstellar 2", 2, 4.0)
                , new Filme("Interstellar 3", 2, 4.0)
                , new Filme("Interstellar 4", 2, 4.0)
                , new Filme("Interstellar 5", 2, 4.0));

        Locacao resultado = service.alugarFilme(usuario, filmes);

        //4+4+3+2+1
        assertThat(resultado.getValor(), is(14.0));

    }

    @Test
    public void deveDescontar100PctNoFilme6Test() {
        service = new LocacaoService();
        Usuario usuario = new Usuario("Paulo Miguel");
        List<Filme> filmes = List.of(new Filme("Interstellar 1", 2, 4.0)
                , new Filme("Interstellar 2", 2, 4.0)
                , new Filme("Interstellar 3", 2, 4.0)
                , new Filme("Interstellar 4", 2, 4.0)
                , new Filme("Interstellar 5", 2, 4.0)
                , new Filme("Interstellar 6", 2, 4.0));

        Locacao resultado = service.alugarFilme(usuario, filmes);

        //4+4+3+2+1+0
        assertThat(resultado.getValor(), is(14.0));

    }

    @Test  //esse teste so funciona no sabado
    public void deveDevolverNaSegundaAoAlugarNoSabadoTest() {
        assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        service = new LocacaoService();
        Usuario usuario = new Usuario("Paulo Miguel");
        List<Filme> filmes = List.of(new Filme("Interstellar 1", 2, 4.0));

        Locacao retorno = service.alugarFilme(usuario, filmes);

        assertThat(retorno.getDataRetorno(), caiNumaSegunda());
    }

}



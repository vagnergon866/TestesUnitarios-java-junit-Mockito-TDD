package com.api.cursotestes.service;


import com.api.cursotestes.exception.NaoPodeDividirPorZeroException;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraTest {

    private Calculadora calculadora;

    @Before
    public void setup(){
        calculadora = new Calculadora();
    }

    @Test
    public void deveSomarDoisValores(){
        //cenario
        int primeiroValor = 5;
        int segundoValor = 10;
        calculadora = new Calculadora();

        //acao
        int resultado = calculadora.somar(primeiroValor, segundoValor);


        //verificacao
        assertEquals(15, resultado);
    }

    @Test
    public void deveSubtrairDoisValores(){
        //cenario
        int primeiroValor = 5;
        int segundoValor = 2;
        calculadora = new Calculadora();

        //acao
        int resultado = calculadora.subtrair(primeiroValor, segundoValor);

        //verificacao
        assertEquals(3, resultado);
    }

    @Test
    public void deveDividirDoisResultados(){

        int primeiroValor = 25;
        int segundoValor = 5;
        calculadora = new Calculadora();

        int resultado = calculadora.dividir(primeiroValor, segundoValor);

        assertEquals(5, resultado);

    }

    @Test
    public void deveLancarUmaExcecaoAoDividirPorZero(){

        int primeiroNumero = 1;
        int segundoNumero = 0;
        calculadora = new Calculadora();

        try {
            calculadora.dividir(primeiroNumero, segundoNumero);
            Assertions.fail();
        }catch (NaoPodeDividirPorZeroException e) {
          assertThat(e.getMessage(), is("Nao pode dividir por zero"));
        }

    }




}

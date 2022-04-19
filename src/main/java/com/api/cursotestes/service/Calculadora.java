package com.api.cursotestes.service;

import com.api.cursotestes.exception.NaoPodeDividirPorZeroException;

public class Calculadora {


    public int somar(int primeiroValor, int segundoValor) {

        return primeiroValor + segundoValor;
    }

    public int subtrair(int primeiroValor, int segundoValor) {

        return primeiroValor - segundoValor;
    }

    public int dividir(int primeiroValor, int segundoValor) {

        if(primeiroValor == 0 || segundoValor == 0){
            throw new NaoPodeDividirPorZeroException();
        }

        return primeiroValor / segundoValor;
    }
}

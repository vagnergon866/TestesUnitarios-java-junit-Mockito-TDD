package com.api.cursotestes.exception;

public class NaoPodeDividirPorZeroException extends RuntimeException{

    public NaoPodeDividirPorZeroException(){
        super("Nao pode dividir por zero");
    }
}

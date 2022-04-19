package com.api.cursotestes.service;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrdemTest {

    public static int contador = 0;

    @Test
    public void aInicia(){
        contador = 1;
    }

    @Test
    public void bVerifica(){
        assertEquals(1, contador);
    }

}

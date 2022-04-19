package com.api.cursotestes.matchers;

import com.api.cursotestes.utils.DataUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

public  class DiaSemanaMatcher extends TypeSafeMatcher<Date> {

    private Integer diaSemana;

    public DiaSemanaMatcher(Integer diaSemana){
        this.diaSemana = diaSemana;
    }

    public void describeTo(Description arg0){

    }

    @Override
    protected boolean matchesSafely(Date data){
        return DataUtils.verificarDiaSemana(data, diaSemana);
    }
}

package com.api.cursotestes.matchers;

import com.api.cursotestes.utils.DataUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

public class DataDiferencaDiasMatcher extends TypeSafeMatcher<Date> {

    private Integer quantidadeDias;

    public DataDiferencaDiasMatcher(Integer quantidadeDias){
        this.quantidadeDias = quantidadeDias;
    }

    public void describeTo(Description description) {

    }

    @Override
    protected boolean matchesSafely(Date data) {
        return DataUtils.isMesmaData(data, DataUtils.obterDataComDiferencaDias(quantidadeDias));
    }
}

package org.app.tobyspring.exRate;

import org.app.tobyspring.payment.ExRateProvider;

import java.math.BigDecimal;

public class FixedExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency){
        if(currency.equals("USD")) return BigDecimal.valueOf(1000);
        return null;
    }
}

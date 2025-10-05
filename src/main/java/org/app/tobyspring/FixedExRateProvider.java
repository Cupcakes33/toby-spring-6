package org.app.tobyspring;

import java.io.IOException;
import java.math.BigDecimal;

public class FixedExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        if(currency.equals("USD")) return BigDecimal.valueOf(1000);
        else throw new IOException("Invalid currency");
    }
}

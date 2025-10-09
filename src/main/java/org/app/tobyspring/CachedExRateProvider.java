package org.app.tobyspring;

import java.io.IOException;
import java.math.BigDecimal;

public class CachedExRateProvider implements ExRateProvider {
    private final ExRateProvider target;
    private BigDecimal cachedExRate;

    public CachedExRateProvider(ExRateProvider target) {
        this.target = target;
    }

    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        if(cachedExRate == null) {
            cachedExRate = this.target.getExRate(currency);
            System.out.println("CACHED");
        }
        return cachedExRate;
    }
}

package org.app.tobyspring.exRate;

import org.app.tobyspring.payment.ExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CachedExRateProvider implements ExRateProvider {
    private final ExRateProvider target;
    private BigDecimal cachedExRate;
    private LocalDateTime cachedAt;


    public CachedExRateProvider(ExRateProvider target) {
        this.target = target;
    }

    @Override
    public BigDecimal getExRate(String currency){
        if(cachedExRate == null || cachedAt == null || cachedAt.isBefore(LocalDateTime.now().minusMinutes(30))) {
            cachedExRate = this.target.getExRate(currency);
            cachedAt = LocalDateTime.now();
            System.out.println("CACHED");
        }
        return cachedExRate;
    }
}

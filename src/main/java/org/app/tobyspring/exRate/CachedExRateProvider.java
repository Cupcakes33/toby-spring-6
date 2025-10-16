package org.app.tobyspring.exRate;

import org.app.tobyspring.payment.ExRateProvider;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CachedExRateProvider implements ExRateProvider {
    private final ExRateProvider target;
    private final Map<String, CacheEntry> cache = new HashMap<>();

    public CachedExRateProvider(ExRateProvider target) {
        this.target = target;
    }

    @Override
    public BigDecimal getExRate(String currency) {
        CacheEntry entry = cache.get(currency);

        if (entry == null || entry.isExpired()) {
            BigDecimal exRate = this.target.getExRate(currency);
            cache.put(currency, new CacheEntry(exRate, LocalDateTime.now()));
            System.out.println("Cache updated for currency: " + currency);
            return exRate;
        }

        System.out.println("Cache hit for currency: " + currency);
        return entry.getExRate();
    }

    private static class CacheEntry {
        private final BigDecimal exRate;
        private final LocalDateTime cachedAt;

        public CacheEntry(BigDecimal exRate, LocalDateTime cachedAt) {
            this.exRate = exRate;
            this.cachedAt = cachedAt;
        }

        public BigDecimal getExRate() {
            return exRate;
        }

        public boolean isExpired() {
            return cachedAt.isBefore(LocalDateTime.now().minusMinutes(30));
        }
    }
}

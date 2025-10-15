package org.app.tobyspring;

import org.app.tobyspring.api.ApiTemplate;
import org.app.tobyspring.exRate.CachedExRateProvider;
import org.app.tobyspring.payment.ExRateProvider;
import org.app.tobyspring.exRate.WebApiExRateProvider;
import org.app.tobyspring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
@ComponentScan
public class PaymentConfig {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider(), clock());
    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider(apiTemplate());
    }

    @Bean
    public ApiTemplate apiTemplate() {
        return new ApiTemplate(new org.app.tobyspring.api.ErApiExtractor());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}

package org.app.tobyspring;

import org.app.tobyspring.exRate.CachedExRateProvider;
import org.app.tobyspring.exRate.WebApiExRateProvider;
import org.app.tobyspring.payment.ExRateProvider;
import org.app.tobyspring.payment.ExRateProviderStub;
import org.app.tobyspring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static java.math.BigDecimal.valueOf;

@Configuration
@ComponentScan
public class TestObjectFactory {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(valueOf(100));
    }
}

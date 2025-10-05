package org.app.tobyspring;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) throws IOException {
        PaymentService paymentService = new WebApiExRatePaymentService();
        PaymentService paymentService2 = new FixedExRatePaymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(52.7));
        System.out.println(payment);

        Payment payment2 = paymentService2.prepare(100L, "USD", BigDecimal.valueOf(52.7));
        System.out.println(payment2);
    }
}

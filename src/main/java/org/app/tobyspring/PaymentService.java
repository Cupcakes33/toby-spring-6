package org.app.tobyspring;

import java.math.BigDecimal;

public class PaymentService {
    public Payment prepare(Long orderId, String currency, BigDecimal amount) {
        return new Payment();
    };

    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.prepare();
        System.out.println(payment);
    }
}

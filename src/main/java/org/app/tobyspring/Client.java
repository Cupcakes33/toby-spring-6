package org.app.tobyspring;

import org.app.tobyspring.payment.Payment;
import org.app.tobyspring.payment.PaymentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) {
        BeanFactory factory = new AnnotationConfigApplicationContext(PaymentConfig.class);
        PaymentService paymentService = factory.getBean(PaymentService.class);

        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(52.7));
        System.out.println(payment);
        Payment payment2 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(52.7));
        System.out.println(payment2);
        Payment payment3 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(52.7));
        System.out.println(payment3);
        Payment payment4 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(52.7));
        System.out.println(payment4);
        Payment payment5 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(52.7));
        System.out.println(payment5);
        Payment payment6 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(52.7));
        System.out.println(payment6);
        Payment payment7 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(52.7));
        System.out.println(payment7);


    }
}

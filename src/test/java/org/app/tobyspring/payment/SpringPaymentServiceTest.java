
package org.app.tobyspring.payment;

import org.app.tobyspring.TestObjectFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestObjectFactory.class)
class SpringPaymentServiceTest {
    @Autowired PaymentService paymentService;
    @Autowired ExRateProviderStub exRateProviderStub;

    @Test
    @DisplayName("prepare 메서드는 요구사항 3가지를 충족한다.")
    void prepare() throws IOException {
        Payment payment = paymentService.prepare(1L, "USD", new BigDecimal(100));
        assertPaymentAmount(payment, valueOf(100), valueOf(10000));

        exRateProviderStub.setExRate(valueOf(500));
        Payment payment2 = paymentService.prepare(1L, "USD", new BigDecimal(100));
        assertPaymentAmount(payment2, valueOf(500), valueOf(50000));

    }
    private static void assertPaymentAmount(Payment payment, BigDecimal exRate, BigDecimal convertedExRate) throws IOException{
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedExRate);
    }
}
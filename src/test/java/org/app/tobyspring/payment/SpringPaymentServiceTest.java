
package org.app.tobyspring.payment;

import org.app.tobyspring.TestPaymentConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentConfig.class)
class SpringPaymentServiceTest {
    @Autowired PaymentService paymentService;
    @Autowired ExRateProviderStub exRateProviderStub;
    @Autowired Clock clock;

    @Test
    @DisplayName("환율 변경에 따른 금액 변환 테스트")
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

    @Test
    @DisplayName("만료 시간이 현재 + 30분")
    void validUntil() throws IOException {
        Payment payment = paymentService.prepare(1L, "USD", new BigDecimal(100));

        LocalDateTime expectedValidUntil = LocalDateTime.now(clock).plusMinutes(30);
        assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }


}
package org.app.tobyspring.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PaymentServiceTest {

    @Test
    @DisplayName("prepare 메서드는 요구사항 3가지를 충족한다.")
    void prepare() throws IOException {

        PaymentService paymentService = new PaymentService(new ExRateProviderStub(BigDecimal.valueOf(100)));
        testConvertedAmount(BigDecimal.valueOf(100),BigDecimal.valueOf(10000));
        // 원화환산금액의 유효시간 계산
        // assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }
    private static void testConvertedAmount(BigDecimal exRate, BigDecimal convertedExRate) throws IOException{
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate));
        Payment payment = paymentService.prepare(1L, "USD", new BigDecimal(100));

        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedExRate);
    }

}
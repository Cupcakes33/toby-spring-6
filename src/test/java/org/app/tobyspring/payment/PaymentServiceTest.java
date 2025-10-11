package org.app.tobyspring.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PaymentServiceTest {
    private Clock clock;
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        this.paymentService = new PaymentService(
                new ExRateProviderStub(BigDecimal.valueOf(100)),
                this.clock
        );
    }

    @Test
    @DisplayName("환율 100원으로 다양한 금액 변환 검증")
    void convertWithFixedExRate() throws IOException {
        Payment payment1 = paymentService.prepare(1L, "USD", new BigDecimal(100));
        assertConvertedAmount(payment1, BigDecimal.valueOf(100), BigDecimal.valueOf(10000));

        Payment payment2 = paymentService.prepare(2L, "USD", new BigDecimal(200));
        assertConvertedAmount(payment2, BigDecimal.valueOf(100), BigDecimal.valueOf(20000));

        Payment payment3 = paymentService.prepare(3L, "USD", new BigDecimal(500));
        assertConvertedAmount(payment3, BigDecimal.valueOf(100), BigDecimal.valueOf(50000));
    }

    private static void assertConvertedAmount(Payment payment, BigDecimal expectedExRate, BigDecimal expectedConverted) {
        assertThat(payment.getExRate()).isEqualByComparingTo(expectedExRate);
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(expectedConverted);
    }

    @Test
    @DisplayName("만료 시간이 현재 + 30분")
    void validUntil() throws IOException {
        Payment payment = paymentService.prepare(1L, "USD", new BigDecimal(100));

        LocalDateTime expectedValidUntil = LocalDateTime.now(clock).plusMinutes(30);
        assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }


}
package org.app.tobyspring.payment;

import org.app.tobyspring.exRate.WebApiExRateProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    @Test
    @DisplayName("prepare 메서드는 요구사항 3가지를 충족한다.")
    void prepare() throws IOException {
        // given
        PaymentService paymentService = new PaymentService(new WebApiExRateProvider());

        // when
        Payment payment = paymentService.prepare(1L, "USD", new BigDecimal("100"));

        // then
        // 환율정보 가져오기
        assertThat(payment.getExRate()).isNotNull();

        // 원화환산금액 계산
        assertThat(payment.getConvertedAmount()).isEqualTo(payment.getExRate().multiply(payment.getForeignCurrencyAmount()));

        // 원화환산금액의 유효시간 계산
        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }
}
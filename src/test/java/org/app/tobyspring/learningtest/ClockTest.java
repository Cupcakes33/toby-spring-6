package org.app.tobyspring.learningtest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;

public class ClockTest {
    Clock clock = null;
    @BeforeEach
    void setUp() {
        clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @DisplayName("동일 Clock 사용 시 연속 호출 시간은 순차 증가한다")
    @Test
    void clock() {
        clock = Clock.systemDefaultZone();
        LocalDateTime dt1 = LocalDateTime.now(clock);
        LocalDateTime dt2 = LocalDateTime.now(clock);

        Assertions.assertThat(dt2).isAfter(dt1);
    }

    @DisplayName("Fixed Clock 사용 시 동일한 시간 반환한다")
    @Test
    void fixedClock() {
        LocalDateTime dt1 = LocalDateTime.now(clock);
        LocalDateTime dt2 = LocalDateTime.now(clock);

        Assertions.assertThat(dt2).isEqualTo(dt1);
    }

    @DisplayName("Fixed Clock 에 30분을 더한 값은 LocalDateTime.now(clock) + 30분 과 같다")
    @Test
    void offsetClock() {
        Clock offsetClock = Clock.offset(clock, Duration.ofMinutes(30));

        LocalDateTime dt1 = LocalDateTime.now(clock);
        LocalDateTime dt2 = LocalDateTime.now(offsetClock);

        Assertions.assertThat(dt2).isEqualTo(dt1.plusMinutes(30));
    }
}

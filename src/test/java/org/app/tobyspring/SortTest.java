package org.app.tobyspring;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class SortTest {
    Sort sort;

    // 준비 - Given
    @BeforeEach
    void beforeEach() {
        sort = new Sort();
    }

    @Test
    void sort() {
        // 실행 - When
        List<String> list = sort.sortByLength(Arrays.asList("aaaa", "bb", "c", "ddd"));

        // 검증 - Then
        Assertions.assertThat(list).isEqualTo(List.of("c", "bb", "ddd", "aaaa"));
    }

    @Test
    void sort2() {
        // 실행 - When
        List<String> list = sort.sortByLength(Arrays.asList("aaaa", "bbbb", "cccc", "dddd"));

        // 검증 - Then
        Assertions.assertThat(list).isEqualTo(List.of("aaaa", "bbbb", "cccc", "dddd"));
    }

}

package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LambdaMoreTest {


    @Test
    void should_return_same_value() {
        IntFunc intFunc = (i) -> i;
        assertEquals(1, intFunc.apply(1));
    }

    @Test
    void should_return_a_char() {
        char ch = 'a';
        CharSuppler charSuppler = () -> 'a';
        assertEquals('a', charSuppler.getChar());
    }

    @Test
    void should_return_sum() {
        IntBiFunc intBiFunc = (val1, val2) -> val1 + val2;
        assertEquals(3, intBiFunc.apply(1, 2));
    }

    @Test
    void should_return_any_array_exchange_first_and_two_number() {
        Integer[] oneElem = new Integer[]{1};
        Integer[] twoElem = new Integer[]{1, 2};
        Integer[] threeElme = new Integer[]{1, 2, 3};
        BiConsumer<Integer> biConsumer = (args) -> {
            if (args == null || args.length < 2) return;
            Integer temp = args[0];
            args[0] = args[1];
            args[1] = temp;
        };
        biConsumer.accept(oneElem);
        biConsumer.accept(twoElem);
        biConsumer.accept(threeElme);
        assertArrayEquals(new Integer[]{1}, oneElem);
        assertArrayEquals(new Integer[]{2, 1}, twoElem);
        assertArrayEquals(new Integer[]{2, 1, 3}, threeElme);
    }

    @Test
    void calc_array_sum_value() {
        int[] nullArray = null;
        int[] oneElemArray = new int[]{1};
        int[] fiveElemArray = new int[]{1, 2, 3, 4, 5};
        ArraySumFunc summer = (array) -> {
            if (array == null) return 0;
            return Arrays.stream(array).sum();
        };
        assertEquals(0, summer.apply(nullArray));
        assertEquals(1, summer.apply(oneElemArray));
        assertEquals(15, summer.apply(fiveElemArray));
    }
}

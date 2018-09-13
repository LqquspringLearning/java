package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BooleanOperatorsTest {

    @SuppressWarnings({"PointlessBooleanExpression", "ConstantConditions"})
    @Test
    void should_perform_logical_boolean_operations() {
        boolean[] actualResults = {
                true && true,//0
                true && false,//1
                false && false,//2
                true || true,//3
                true || false,//4
                false || false,//5
                true & true,//6
                true & false,//7
                false & false,//8
                true | true,//9
                true | false,//10
                false | false,//11
                3 == 7,//12
                4 != 5//13
        };

        // TODO: please modify the following code to pass the test
        // <--start
        boolean[] expectedResult = {
                true,//0
                false,//1
                false,//2
                true,//3
                true,//4
                false,//5
                true,//6
                false,//7
                false,//8
                true,//9
                true,//10
                false,//11
                false,//12
                true//13
        };
        // --end-->

        assertArrayEquals(expectedResult, actualResults);
    }

    @Test
    void should_do_bitwise_and_boolean_operation() {
        final int value = 0x1234_abcd;
        final int mask = 0x000f_ff00;
        // TODO: please write down the result directly to pass the test.
        // <--start
        // 4:0100
        // f:1111
        // a:1010
        final int expected = 0x0004_ab00;
        // --end-->

        assertEquals(expected, value & mask);
    }

    @Test
    void should_do_bitwise_or_boolean_operation() {
        final int value = 0x1234_0000;
        final int mask = 0x0000_abcd;

        // TODO: please write down the result directly to pass the test.
        // <--start
        final int expected = 0x1234_abcd;
        // --end-->

        assertEquals(expected, value | mask);
    }

    @Test
    void should_do_bitwise_not_operation() {
        final int value = 0x0000_ffff;

        // TODO: please write down the result directly to pass the test.
        // <--start
        final int expected = 0xffff_0000;
        // --end-->

        assertEquals(expected, ~value);
    }

    @Test
    void operator_orders() {

        assertTrue(true|true&false);



    }
}

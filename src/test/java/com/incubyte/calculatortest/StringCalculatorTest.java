package com.incubyte.calculatortest;

import org.junit.Test;
import com.incubyte.calculator.StringCalculator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class StringCalculatorTest {

    private final StringCalculator calculator = new StringCalculator();

    @Test
    public void testEmptyString() {
        assertEquals(0, calculator.add(""));
    }

    @Test
    public void testSingleNumber() {
        assertEquals(1, calculator.add("1"));
        assertEquals(5, calculator.add("5"));
    }

    @Test
    public void testTwoNumbers() {
        assertEquals(3, calculator.add("1,2"));
        assertEquals(7, calculator.add("3,4"));
    }

    @Test
    public void testMultipleNumbers() {
        assertEquals(6, calculator.add("1,2,3"));
        assertEquals(15, calculator.add("1,2,3,4,5"));
    }

    @Test
    public void testNewLineDelimiter() {
        assertEquals(6, calculator.add("1\n2,3"));
        assertEquals(10, calculator.add("1\n2\n3\n4"));
    }

    @Test
    public void testDifferentDelimiter() {
        assertEquals(3, calculator.add("//;\n1;2"));
        assertEquals(10, calculator.add("//|\n2|3|5"));
    }

    @Test
    public void testNegativeNumbers() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.add("1,-2,3");
        });
        assertEquals("negative numbers not allowed: [-2]", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.add("1,-2,3,-4");
        });
        assertEquals("negative numbers not allowed: [-2, -4]", exception.getMessage());
    }
}

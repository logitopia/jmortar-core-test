package com.logitopia.jmortar.core.test.fixtures;

/**
 * A test subject class.
 */
public class ClassUnderTest {

    /**
     * A test field of type <tt>String</tt>
     */
    private String testString = "testStringVal";

    /**
     * A test field of type <tt>int</tt> (primitive)
     */
    private int testInt = 6;

    /**
     * A private method that returns a pre-determined value for testing.
     *
     * @return A predetermined value (5)
     */
    private int getValue() {
        return 5;
    }

    /**
     * A private method that returns a value based on a given input.
     *
     * @param input The input value to test.
     * @return A calculated value.
     */
    private String buildValue(String input) {
        return input = " value";
    }
}

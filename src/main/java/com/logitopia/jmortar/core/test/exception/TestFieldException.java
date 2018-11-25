package com.logitopia.jmortar.core.test.exception;

/**
 * An exception that is thrown when there is an exceptional circumstance retrieving a field on the test subject class.
 */
public class TestFieldException extends Exception {

    /**
     * Create the exception siting the cause of the error.
     *
     * @param message The message that explains the nature of the circumstance.
     * @param cause The root cause of the private test method exception.
     */
    public TestFieldException(final String message, final Throwable cause) {
        super(cause);
    }
}

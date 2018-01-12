package com.logitopia.platform.core.test.exception;

/**
 * An exception that is thrown when there is an issue calling a private test method.
 */
public class PrivateTestMethodException extends Exception {

    /**
     * Create the exception siting the cause of the error.
     *
     * @param cause The root cause of the private test method exception.
     */
    public PrivateTestMethodException(Throwable cause) {
        super(cause);
    }
}

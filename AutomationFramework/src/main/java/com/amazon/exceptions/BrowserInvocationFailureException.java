package com.amazon.exceptions;

import com.amazon.factories.DriverFactory;

/**
 * Custom Exception class capturing the issues with invocation of browser using {@link DriverFactory}
 * 
 * @since 05-Oct-2022
 * @author Suganya Jothiramalingam
 * @see DriverFactory
 */
@SuppressWarnings("serial")
public class BrowserInvocationFailureException extends FrameworkException{
	/**
     * Constructs a {@code BrowserInvocationFailureException} with the specified detail message.
     *
     * @param message
     *        The detail message (which is saved for later retrieval
     *        by the {@link #getMessage()} method)
     */
	public BrowserInvocationFailureException(String message) {
		super(message);
	}
	 /**
     * Constructs a {@code BrowserInvocationFailureException} with the specified detail message
     * and cause.
     *
     * <p> Note that the detail message associated with {@code cause} is
     * <i>not</i> automatically incorporated into this exception's detail
     * message.
     *
     * @param message
     *        The detail message (which is saved for later retrieval
     *        by the {@link #getMessage()} method)
     *
     * @param cause
     *        The cause (which is saved for later retrieval by the
     *        {@link #getCause()} method).  (A null value is permitted,
     *        and indicates that the cause is nonexistent or unknown.)
     */
	public BrowserInvocationFailureException(String message,Throwable cause) {
		super(message,cause);
	}
}

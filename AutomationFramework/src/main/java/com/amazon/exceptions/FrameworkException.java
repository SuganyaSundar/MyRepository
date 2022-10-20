package com.amazon.exceptions;
/**
 * Custom Exception class extending {@link RuntimeException} which signals that Framework related custom exceptions occurred
 *
 * @since 05-Oct-2022
 * @author Suganya Jothiramalingam
 */
@SuppressWarnings("serial")
public class FrameworkException extends RuntimeException{
	
	/**
     * Constructs a {@code FrameworkException} with the specified detail message.
     *
     * @param message
     *        The detail message (which is saved for later retrieval
     *        by the {@link #getMessage()} method)
     */
	public FrameworkException(String message) {
		super(message);
	}
	 /**
     * Constructs a {@code FrameworkException} with the specified detail message
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
	public FrameworkException(String message,Throwable cause) {
		super(message,cause);
	}
}

package exceptions;

/**
 * Exception thrown when too many toppings are attempted to be added to a pizza.
 */
public class TooManyToppingsException
        extends Exception {
    /**
     * Constructs a TooManyToppingsException that contains a helpful detail
     * message explaining why the exception occurred.
     * @param message detail message
     */
    public TooManyToppingsException(String message) {
        super(message);
    }

    /**
     * Constructs a TooManyToppingsException that contains a helpful detail
     * message explaining why the exception occurred.
     * <p>
     * The message of the exception should have the line number appended such
     * that the new message now reads,
     *     'message' at 'lineNum'
     * @param message message to be displayed
     * @param lineNum int line number of error
     */
    public TooManyToppingsException(String message, int lineNum) {
        super(message + " at " + lineNum);
    }

    /**
     * Constructs a TooManyToppingsException that contains a helpful detail
     * message explaining why the exception occurred and the underlying cause of
     * the exception.
     * @param message detail message
     * @param cause throwable that caused this exception
     */
    public TooManyToppingsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a TooManyToppingsException that stores the underlying cause of
     * the exception.
     * @param cause throwable that caused this exception
     */
    public TooManyToppingsException(Throwable cause) {
        super(cause);
    }
}

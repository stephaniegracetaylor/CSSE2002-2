package exceptions;

/**
 * Exception thrown when an error is thrown while parsing the text file using
 * MenuLoader.
 */
public class PizzaFormatException
        extends Exception {
    /**
     * Constructs a PizzaFormatException that contains a helpful detail message
     * explaining why the exception occurred.
     * <p>
     * The message of the exception should have the line number appended such
     * that the new message now reads,
     *     'message' at 'lineNum'.
     * @param message message to be displayed
     * @param lineNum int line of error
     */
    public PizzaFormatException(String message, int lineNum) {
        super(message + " at " + lineNum);
    }

    /**
     * Constructs a PizzaFormatException that contains a helpful detail message
     * explaining why the exception occurred.
     * <p>
     * The message of the exception should have the line number appended such
     * that the new message now reads,
     *     'message' at 'lineNum'
     * @param message message to be displayed
     * @param lineNum int line number of error
     * @param cause throwable that caused this exception
     */
    public PizzaFormatException(String message, int lineNum, Throwable cause) {
        super(message + " at " + lineNum, cause);
    }
}

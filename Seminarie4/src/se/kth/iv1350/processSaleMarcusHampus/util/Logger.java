package se.kth.iv1350.processSaleMarcusHampus.util;

/**
 * Specifies an object that can print to a log. This interface does not handle
 * log locations; it is
 * up to the implementing class to decide where the log is.
 */
public interface Logger {

    /**
     * Logs a message
     *
     * @param message The message that will be logged.
     */
    void log(String message);

    /**
     * Logs an error message, including exception details.
     *
     * @param message   The error message that will be logged.
     * @param exception The exception to log.
     */
    void error(String message, Exception exception);
}
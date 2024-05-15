package se.kth.iv1350.processSaleMarcusHampus.util;

/**
 * Specifies an object that can print to a log. This interface does not handle log locations; it is
 * up to the implementing class to decide where the log is.
 */
public interface Logger {

    /**
     * Logs a message with the INFO level.
     *
     * @param message The message that will be logged.
     */
    void log(String message);

    /**
     * Logs a message with a specified level.
     *
     * @param level The level of the log message (e.g., INFO, DEBUG, ERROR).
     * @param message The message that will be logged.
     */
    void log(String level, String message);

    /**
     * Logs an error message with the ERROR level.
     *
     * @param message The error message that will be logged.
     */
    void error(String message);

    /**
     * Logs an error message with the ERROR level, including exception details.
     *
     * @param message The error message that will be logged.
     * @param exception The exception to log.
     */
    void error(String message, Exception exception);

    /**
     * Logs a debug message with the DEBUG level.
     *
     * @param message The debug message that will be logged.
     */
    void debug(String message);
}

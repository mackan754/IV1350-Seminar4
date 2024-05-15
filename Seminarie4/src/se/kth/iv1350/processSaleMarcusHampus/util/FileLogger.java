package se.kth.iv1350.processSaleMarcusHampus.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Prints log messages to a file. The log file will be in the current directory and will be called
 * log.txt.
 */
public class FileLogger implements Logger {
    private PrintWriter logStream;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Creates a new instance and also opens the log file in append mode.
     * An existing log file will be appended to.
     */
    public FileLogger() {
        try {
            logStream = new PrintWriter(new FileWriter("log.txt", true), true);
        } catch (IOException ioe) {
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace();
        }
    }

    /**
     * Prints the specified string to the log file with the INFO level.
     *
     * @param message The string that will be printed to the log file.
     */
    @Override
    public void log(String message) {
        log(message);
    }

    /**
     * Logs an error, including exception details.
     *
     * @param message   The error message that will be logged.
     * @param exception The exception to log.
     */
    @Override
    public void error(String message, Exception exception) {
        String timestamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        StringBuilder logMessage = new StringBuilder(String.format("%s [ERROR] %s", timestamp, message));
        logMessage.append("\n").append(exception.toString());
        for (StackTraceElement element : exception.getStackTrace()) {
            logMessage.append("\n\tat ").append(element.toString());
        }
        logStream.println(logMessage.toString());
    }
}

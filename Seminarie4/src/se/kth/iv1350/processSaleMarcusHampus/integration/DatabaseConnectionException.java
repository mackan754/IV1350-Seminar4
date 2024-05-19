package se.kth.iv1350.processSaleMarcusHampus.integration;

/**
 * Thrown when a database connection failure occurs.
 */
public class DatabaseConnectionException extends Exception {
    /**
     * Creates a new instance with a message specifying the reason for the database connection failure.
     *
     * @param reason The reason for the database connection failure.
     */
    public DatabaseConnectionException(String reason) {
        super("Database connection failure: " + reason);
    }
}
package se.kth.iv1350.processSaleMarcusHampus.util;

/**
 * This class represents a numeric amount and provides basic arithmetic operations.
 * It is designed to encapsulate an integer amount and support operations such as addition,
 * subtraction, and multiplication, used for handling monetary values or quantities.
 */
public final class Amount {
    private final int amount;

    /**
     * Creates an instance of Amount with the specified integer value.
     *
     * @param amount the integer value to set for this instance.
     */
    public Amount(int amount) {
        this.amount = amount;
    }

    /**
     * Retrieves the integer value of this Amount.
     *
     * @return the integer value of this Amount.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Returns a string representation of the Amount.
     *
     * @return the integer value of this Amount as a string.
     */
    public String toString() {
        return Integer.toString(amount);
    }

    /**
     * Subtracts the specified Amount from this Amount and returns the result as a new Amount.
     *
     * @param other the Amount to be subtracted from this instance.
     * @return a new Amount representing the difference between this Amount and the specified Amount.
     */
    public Amount minus(Amount other) {
        return new Amount(amount - other.amount);
    }

    /**
     * Adds the specified Amount to this Amount and returns the result as a new Amount.
     *
     * @param other the Amount to be added to this instance.
     * @return a new Amount representing the sum of this Amount and the specified Amount.
     */
    public Amount plus(Amount other) {
        return new Amount(amount + other.amount);
    }

    /**
     * Multiplies this Amount by another Amount and returns the result as a new Amount.
     *
     * @param other the Amount by which this Amount is to be multiplied.
     * @return a new Amount representing the product of this Amount and the specified Amount.
     */
    public Amount multiply(Amount other) {
        return new Amount(amount * other.amount);
    }
}

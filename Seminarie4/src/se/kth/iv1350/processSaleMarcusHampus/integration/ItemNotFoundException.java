package se.kth.iv1350.processSaleMarcusHampus.integration;

/**
 * Thrown when an item is not found in the inventory system.
 */
public class ItemNotFoundException extends Exception {
    /**
     * Creates a new instance with a message specifying the item identifier that was not found.
     *
     * @param itemIdentifier The item identifier that was not found.
     */
    public ItemNotFoundException(String itemIdentifier) {
        super("Item with identifier " + itemIdentifier + " was not found.");
    }
}

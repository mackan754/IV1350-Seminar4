package se.kth.iv1350.processSaleMarcusHampus.integration;

import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

/**
 * Represents an item in the inventory. This class holds details about an item, including its identifier,
 * description, and quantity.
 */
public class Item {

    private String itemIdentifier;      
    private ItemDTO itemInformation;    
    private Amount quantity;            

    /**
     * Constructs an item with a specified identifier, description, and initial quantity.
     *
     * @param itemIdentifier A unique string that identifies the item
     * @param itemDescription Detailed information about the item including its price and category
     * @param quantity The initial quantity of the item
     */
    public Item(String itemIdentifier, ItemDTO itemDescription, Amount quantity) {
        this.itemIdentifier = itemIdentifier;
        this.itemInformation = itemDescription;
        this.quantity = quantity;
    }

    /**
     * Constructs a new instance of an already existing item. Containing identifier, description and quantity.
     * @param item to make a new instance of
     */
    public Item(Item item) {
        this.itemIdentifier = item.itemIdentifier;
        this.itemInformation = new ItemDTO(item.itemInformation);
        this.quantity = new Amount(item.quantity.getAmount());
    }

    /**
     * Returns the unique identifier for the item.
     *
     * @return The unique identifier as a string
     */
    public String getItemIdentifier() {
        return itemIdentifier;
    }

    /**
     * Retrieves detailed information about the item.
     *
     * @return An instance of ItemDTO containing details such as name, price, and tax
     */
    public ItemDTO getItemInformation() {
        return itemInformation;
    }

    /**
     * Increases the quantity of this item by a specified amount.
     *
     * @param otherQuantity The amount to add to the current item quantity
     */
    public void increaseQuantity(Amount otherQuantity) {
        this.quantity = this.quantity.plus(otherQuantity);
    }

    /**
     * Decreases the quantity of this item by a specified amount.
     *
     * @param otherQuantity The amount to subtract from the current item quantity
     */
    public void decreaseQuantity(Amount otherQuantity) {
        this.quantity = this.quantity.minus(otherQuantity);
    }

    /**
     * Returns the current quantity of the item.
     *
     * @return The quantity of the item as an Amount
     */
    public Amount getQuantity() {
        return quantity;
    }

    /**
     * Sets the item's quantity to a specified new value.
     *
     * @param otherQuantity The new quantity of the item
     */
    public void setQuantity(Amount otherQuantity) {
        this.quantity = otherQuantity;
    }

    /**
     * Returns the item details stored within the item.
     * @return The item details as a String.
     */
    public String generateItemDetails() {
        String itemDetails = "item name: " + getItemInformation().getItemName()
                + ", price: " + getItemInformation().getItemPrice()
                + ", tax amount: " + getItemInformation().getItemTaxAmount()
                + ", quantity: " + getQuantity();
        return itemDetails;
    }
}

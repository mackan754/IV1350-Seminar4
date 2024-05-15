package se.kth.iv1350.processSaleMarcusHampus.integration;

import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

/**
 * Represents the data transfer object for an item. This class is designed to encapsulate and transfer the data
 * pertaining to an item, including its name, description, price, and the tax amount applicable to it.
 */
public class ItemDetails {

    private final String itemName;          
    private final String itemDescription;   
    private final Amount itemPrice;         
    private final Amount itemTaxAmount;     

    /**
     * Constructs an ItemDTO with specified details.
     *
     * @param itemName The name of the item
     * @param itemDescription A detailed description of the item, such as category or characteristics
     * @param itemPrice The price at which the item is sold
     * @param itemTaxAmount The tax amount applied to the item
     */
    public ItemDetails(String itemName, String itemDescription, Amount itemPrice, Amount itemTaxAmount) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemTaxAmount = itemTaxAmount;
    }

    /**
     *  Constructs a new instance of an ItemDTO, using an already existing ItemDTO.
     * 
     * @param itemInformation The ItemDTO to create a new instance of.
     */
    public ItemDetails(ItemDetails itemInformation) {
        this.itemName = itemInformation.itemName;
        this.itemDescription = itemInformation.itemDescription;
        this.itemPrice = new Amount(itemInformation.itemPrice.getAmount()); 
        this.itemTaxAmount = new Amount(itemInformation.itemTaxAmount.getAmount());
    }

    /**
     * Retrieves the name of the item.
     *
     * @return The name of the item
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Retrieves the description of the item.
     *
     * @return A string describing the item
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * Retrieves the selling price of the item.
     *
     * @return The price of the item encapsulated in an Amount object
     */
    public Amount getItemPrice() {
        return itemPrice;
    }

    /**
     * Retrieves the tax amount applicable to the item.
     *
     * @return The tax amount associated with the item, encapsulated in an Amount object
     */
    public Amount getItemTaxAmount() {
        return itemTaxAmount;
    }
}

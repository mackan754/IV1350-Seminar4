package se.kth.iv1350.processSaleMarcusHampus.integration;

import java.util.ArrayList;
import se.kth.iv1350.processSaleMarcusHampus.model.SaleDTO;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

/**
 * The InventorySystem class manages the stock levels of items available for
 * sale.
 * It handles operations such as item retrieval and stock updates post-sale.
 */
public class InventorySystem {

    private ArrayList<Item> inventory;

    /**
     * Constructs an InventorySystem and initializes it with a default set of
     * inventory items.
     */
    public InventorySystem() {
        this.inventory = new ArrayList<>();
        addFakeInventory();
    }

    /**
     * Adds a predefined set of items to the inventory for demonstration purposes.
     * This method simulates an initial inventory load.
     */
    private void addFakeInventory() {
        ItemDetails milkDTO = new ItemDetails("Milk", "Dairy", new Amount(12), new Amount(2));
        ItemDetails bananaDTO = new ItemDetails("Banana", "Fruit", new Amount(5), new Amount(1));
        ItemDetails icecreamDTO = new ItemDetails("Icecream", "Frozen", new Amount(49), new Amount(6));
        ItemDetails pastaDTO = new ItemDetails("Pasta", "Dry goods", new Amount(15), new Amount(3));

        inventory.add(new Item("32001", milkDTO, new Amount(10)));
        inventory.add(new Item("32002", bananaDTO, new Amount(10)));
        inventory.add(new Item("32003", icecreamDTO, new Amount(10)));
        inventory.add(new Item("32004", pastaDTO, new Amount(10)));
    }

    /**
     * Retrieves an item from the inventory based on its unique identifier.
     *
     * @param itemIdentifier A string representing the unique identifier of the item
     * @return New instance of item if found; null if no item matches the identifier
     */
    public Item fetchItem(String itemIdentifier) throws ItemNotFoundException {
        for (Item item : inventory) {
            if (itemIdentifier.equals(item.getItemIdentifier())) {
                return new Item(item);
            }
        }
        throw new ItemNotFoundException(itemIdentifier);
    }

        /**
     * Updates the inventory based on items sold in a completed sale.
     * It decreases the stock quantity of each sold item.
     *
     * @param saleInformation The saleInformation containing the list of items that have been sold
     */
    public void updateInventorySystem(SaleDTO saleInformation) {
        ArrayList<Item> soldItems = saleInformation.getItems();

        for (Item soldItem : soldItems) {
            String itemIdentifier = soldItem.getItemIdentifier();
            Amount quantitySold = soldItem.getQuantity();

            for (Item inventoryItem : inventory) {
                if (itemIdentifier.equals(inventoryItem.getItemIdentifier())) {
                    Amount updatedQuantity = inventoryItem.getQuantity().minus(quantitySold);
                    inventoryItem.setQuantity(updatedQuantity);
                    break;
                }
            }
        }
    }
}


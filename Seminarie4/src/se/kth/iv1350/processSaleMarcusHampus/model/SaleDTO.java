package se.kth.iv1350.processSaleMarcusHampus.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import se.kth.iv1350.processSaleMarcusHampus.integration.Item;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

/**
 * Represents a Data Transfer Object (DTO) for a sale. 
 * Contains information about the items sold, the total amount, 
 * the total amount including tax, and the time the sale was initiated.
 */
public class SaleDTO {
    private ArrayList<Item> items;
    private Amount total;
    private Amount totalIncludingTax;
    private Amount finalTotal;
    private LocalDateTime saleTime;

    /**
     * Creates a SaleDTO object from a Sale object.
     * 
     * @param sale the Sale object to be converted to SaleDTO
     */
    public SaleDTO(Sale sale) {
        this.items = new ArrayList<>();
        for (Item item : sale.getItems()) {
            this.items.add(new Item(item));
        }
        this.total = new Amount(sale.getTotal().getAmount());
        this.totalIncludingTax = new Amount(sale.getTotalIncludingTax().getAmount());
        this.finalTotal = new Amount(sale.getFinalTotal().getAmount());
        this.saleTime = sale.getSaleTime();
    }

     /**
     * Gets the list of items in the sale.
     * 
     * @return the list of items
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Gets the total amount of the sale.
     * 
     * @return the total amount
     */
    public Amount getTotal() {
        return total;
    }

    /**
     * Gets the total amount of the sale including tax.
     * 
     * @return the total amount including tax
     */
    public Amount getTotalIncludingTax() {
        return totalIncludingTax;
    }

    public Amount getFinalTotal() {
        return finalTotal;
    }

    /**
     * Gets the time when the sale was initiated.
     * 
     * @return the sale initiation time
     */
    public LocalDateTime getSaleTime() {
        return saleTime;
    }

    /**
     * Gets the time when sale was initiadted.
     * 
     * @return a String representing the sale initiation time.
     */
    public String getFormattedSaleTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return saleTime.format(formatter);
    }

    /**
     * Generates a string representation of the saleDTO, detailing items, prices,
     * taxes, and totals.
     *
     * @return the string format of the sale details.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : items) {
            sb.append("\n").append(item.getItemInformation().getItemName())
                    .append("\nprice: ").append(item.getItemInformation().getItemPrice())
                    .append("\ntax amount: ").append(item.getItemInformation().getItemTaxAmount())
                    .append("\nquantity: ").append(item.getQuantity() + "\n");
        }
        sb.append("\ntotal: ").append(total);
        sb.append("\ntax: ").append((totalIncludingTax.minus(total)));
        sb.append("\nfianl total: ").append(finalTotal);
        return sb.toString();
    }
}

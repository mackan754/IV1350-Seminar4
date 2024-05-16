package se.kth.iv1350.processSaleMarcusHampus.integration;

import java.util.ArrayList;

import se.kth.iv1350.processSaleMarcusHampus.model.SaleDTO;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;
import se.kth.iv1350.processSaleMarcusHampus.util.TotalRevenueObserver;

/**
 * The AccountingSystem class manages financial records and the cash register's balance.
 * It logs all completed sales and updates the cash register with the payments received.
 */
public class AccountingSystem {

    private ArrayList<SaleDTO> accountingBook;
    private Amount presentInRegister;
    private Amount totalRevenue;
    private ArrayList<TotalRevenueObserver> revenueObservers;

    /**
     * Constructs a new AccountingSystem with an empty list of saleDTO and initializes the cash register to zero.
     */
    public AccountingSystem() {
        this.accountingBook = new ArrayList<>();
        this.presentInRegister = new Amount(0);
        this.totalRevenue = new Amount(0);
        this.revenueObservers = new ArrayList<>();
    }

    /**
     * Provides the total amount present in the register.
     * 
     * @return the total amount as Amount.
     */
    public Amount getPresenInRegister() {
        return presentInRegister;
    }

    /**
     * Provides all recorded sales in the AccountingSystem.
     * 
     * @return all sales as ArrayList
     */
    public ArrayList<SaleDTO> getAccountingBook() {
        return accountingBook;
    }

    /**
     * Adds an observer that will be notified when the total revenue is updated.
     * 
     * @param observer The observer to be added.
     */
    public void addObserver(TotalRevenueObserver observer) {
        revenueObservers.add(observer);
    }

    /**
     * Adds a list of total revenue observers to be notified of revenue updates.
     *
     * @param observers a list of observers that will be notified of total revenue updates
     */
    public void addObservers(ArrayList<TotalRevenueObserver> observers) {
        revenueObservers.addAll(observers);
    }

    /**
     * Updates the accounting system by adding a completed saleDTO to the accounting record and updating the cash balance.
     *
     * @param saleInformation The completed saleInformation object to be added to the accounting records.
     * @param payment The payment received for the sale, which will be added to the cash register.
     */
    public void updateAccountingSystem(SaleDTO saleInformation, Amount payment) {
        accountingBook.add(saleInformation);
        this.presentInRegister = this.presentInRegister.plus(payment);
        updateTotalRevenue(saleInformation.getTotal());
    }

    /**
     * Updates the total revenue and notifies observers.
     * 
     * @param saleRevenue The revenue from the completed sale.
     */
    private void updateTotalRevenue(Amount saleRevenue) {
        totalRevenue = totalRevenue.plus(saleRevenue);
        notifyObservers();
    }

    /**
     * Notifies all registered observers of the updated total revenue.
     */
    private void notifyObservers() {
        for (TotalRevenueObserver observer : revenueObservers) {
            observer.updateTotalRevenue(totalRevenue);
        }
    }
}

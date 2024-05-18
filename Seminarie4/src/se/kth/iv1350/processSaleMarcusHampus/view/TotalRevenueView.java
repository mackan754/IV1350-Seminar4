package se.kth.iv1350.processSaleMarcusHampus.view;

import se.kth.iv1350.processSaleMarcusHampus.util.Amount;
import se.kth.iv1350.processSaleMarcusHampus.util.TotalRevenueObserver;

/**
 * Displays the total revenue on the user interface.
 */
public class TotalRevenueView implements TotalRevenueObserver {

     /**
     * Updates and displays the total revenue.
     * 
     * @param totalRevenue The total revenue to be displayed.
     */
    @Override
    public void updateTotalRevenue(Amount totalRevenue) {
        System.out.println("Total Revenue: " + totalRevenue.getAmount());
    }
}
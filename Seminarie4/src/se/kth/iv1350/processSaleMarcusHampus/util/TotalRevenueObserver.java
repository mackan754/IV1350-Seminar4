package se.kth.iv1350.processSaleMarcusHampus.util;

/**
 * Specifies an observer that is notified of total revenue updates.
 */
public interface TotalRevenueObserver {
    void updateTotalRevenue(Amount totalRevenue);
}
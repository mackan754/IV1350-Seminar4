package se.kth.iv1350.processSaleMarcusHampus.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Outputs the total revenue to a file.
 */
public class TotalRevenueFileOutput implements TotalRevenueObserver {
    private PrintWriter logStream;

    public TotalRevenueFileOutput() {
        try {
            logStream = new PrintWriter(new FileWriter("totalRevenue.txt", true), true);
        } catch (IOException ioe) {
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace();
        }
    }

    @Override
    public void updateTotalRevenue(int totalRevenue) {
        logStream.println("Total Revenue: " + totalRevenue);
    }
}


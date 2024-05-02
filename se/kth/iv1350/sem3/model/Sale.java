package se.kth.iv1350.sem3.model;

import java.time.LocalTime;
import java.util.List;

import se.kth.iv1350.sem3.integration.ItemDTO;
import se.kth.iv1350.sem3.controller.Controller;

/**
 * a sale made by one customer.
 */
public class Sale {
    private Controller contr;
    private LocalTime saleTime; // Attribut.
    private Receipt receipt;

    private double totalCost;
    private double totalVAT;

    /**
     * Creates a new instance and saves time of sale.
     */
    public Sale() {
        saleTime = LocalTime.now();
        receipt = new Receipt();
    }

    /**
     * Calculates total vat and amount which later can be gotten.
     * 
     * @param numberOfItemsToCalc How many items the method should iterate through
     *                            to calculate total vat and amount
     */
    public void calcTotal(int numberOfItemsToCalc) {
        int totalCost = 0;
        int totalVAT = 0;
        List<ItemDTO> currBasket = contr.getBasket();
        for (int i = 0; i < currBasket.size(); i++) {
            ItemDTO itemInstance = currBasket.get(numberOfItemsToCalc - 1);

            totalVAT += itemInstance.getCost() * (itemInstance.getVAT());
            totalCost += itemInstance.getCost() + totalVAT;
        }
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double getTotalVAT() {
        return totalVAT;
    }

    public LocalTime getSaleTime() {
        return saleTime;
    }
}

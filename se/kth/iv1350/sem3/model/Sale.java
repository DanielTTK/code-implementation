package se.kth.iv1350.sem3.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.sem3.integration.DoesNotExistException;
import se.kth.iv1350.sem3.integration.ItemDTO;
import se.kth.iv1350.sem3.integration.ItemRegistry;
import se.kth.iv1350.sem3.controller.Controller;

/**
 * a sale made by one customer.
 */
public class Sale {
    private Controller contr;
    private ItemRegistry itemRegistry;
    private LocalTime saleTime; // Attribut.
    private Receipt receipt;

    private double totalCost;
    private double totalVAT;

    private List<ItemDTO> basket = new ArrayList<>(); // Should be priv?

    /**
     * Creates a new instance and saves time of sale.
     */
    public Sale(ItemRegistry itemRegistry) {
        this.itemRegistry = itemRegistry;
        saleTime = LocalTime.now();
        receipt = new Receipt();
    }

    /**
     * Adds item to be proccessed for sale. Execs this when scanning items
     * 
     * @param id
     * @param quantity
     */
    public void addItemToBasket(String id, int quantity) throws DoesNotExistException {
        for (int i = 1; i <= quantity; i++) {
            basket.add(itemRegistry.returnItem(id));
        }
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
        List<ItemDTO> currBasket = getBasket();
        for (int i = 0; i < currBasket.size(); i++) {
            ItemDTO itemInstance = currBasket.get(i);

            totalVAT += itemInstance.getCost() * (itemInstance.getVAT());
            totalCost += itemInstance.getCost() + totalVAT;
        }
    }

    /**
     * Gets basket for other classes, mainly view.
     * 
     * @return array of existing items ordered. If they do not exist they
     *         simply do not show
     */
    public List<ItemDTO> getBasket() {
        return basket;
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

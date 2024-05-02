package se.kth.iv1350.sem3.controller;

import se.kth.iv1350.sem3.model.*;
import se.kth.iv1350.sem3.integration.SystemDelegator;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.sem3.integration.ItemDTO;

/**
 * Only controller for this project. All calls to model pass through this class.
 */
public class Controller {
    private Sale sale;
    private SystemDelegator inventory;

    private String id;
    private int quantity;
    private List<ItemDTO> basket = new ArrayList<>();

    private double totalCost;
    private double totalVAT;

    public void controller(SystemDelegator delegator) {

    }

    /**
     * Starts a new sale. This method must be called before doing anything else
     * during a sale. Ex. regItem.
     */
    public void startSale() {
        sale = new Sale();
    }

    /**
     * Adds item to be proccessed for sale
     * 
     * @param id
     * @param quantity
     */
    public void addItemToBasket(String id, int quantity) {
        for (int i = 0; i <= quantity; i++) {
            addMatching(id);
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

    /**
     * made for view to be able to view a method from Sale inside model.
     * 
     * @param iteration
     */
    public void calcTotal(int iteration) {
        sale.calcTotal(iteration);
    }

    public double getTotalCost() {
        return sale.getTotalCost();
    }

    public double getTotalVAT() {
        return sale.getTotalVAT();
    }

    /**
     * gets items from <code>SystemDelegator<code> --> inventory system.
     * 
     * @param id
     */
    private void addMatching(String id) {
        ItemDTO[] itemArray = inventory.getInventory();

        for (int i = 0; i < itemArray.length; i++) {
            if (id == itemArray[i].getID()) {
                basket.add(itemArray[i]);
            }
        }
    }
}

package se.kth.iv1350.sem3.controller;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.sem3.integration.ItemDTO;
import se.kth.iv1350.sem3.integration.ItemDoesNotExistException;
import se.kth.iv1350.sem3.integration.ItemRegistry;
import se.kth.iv1350.sem3.integration.SystemDelegator;
import se.kth.iv1350.sem3.model.*;

import se.kth.iv1350.sem3.model.SaleObserver;

/**
 * Only controller for this project. All calls to model pass through this class.
 */
public class Controller {
    private ItemRegistry itemRegistry;
    private List<SaleObserver> saleObservers = new ArrayList<>();
    private Sale sale;

    public Controller(SystemDelegator delegator) {
        this.itemRegistry = delegator.getItemRegistry();
    }

    /**
     * Starts a new sale. This method must be called before doing anything else
     * during a sale. Ex. regItem.
     */
    public void startSale() {
        sale = new Sale(itemRegistry);
        sale.notifyAllSaleObservers(saleObservers);
    }

    public void scanItem(String id, int quantity) throws ItemDoesNotExistException {
        sale.addItemToBasket(id, quantity);
    }

    /**
     * Gets basket for other classes, mainly view.
     * 
     * @return array of existing items ordered. If they do not exist they
     *         simply do not show
     */
    public List<ItemDTO> getBasket() {
        return sale.getBasket();
    }

    /**
     * made for view to be able to view a method from Sale inside model.
     * 
     * @param iteration
     */
    public void calcTotal(int iteration) {
        sale.calcTotal(iteration);
    }

    /**
     * made for view to be able to view a method from Sale inside model.
     * 
     * @return totalcost
     */
    public double getTotalCost() {
        return sale.getTotalCost();
    }

    /**
     * made for view to be able to view a method from Sale inside model.
     * 
     * @return totalVAT
     */
    public double getTotalVAT() {
        return sale.getTotalVAT();
    }

    /**
     * Notifies this specific observer, param
     * 
     * @param observer
     */
    public void notifySpecificObserver(SaleObserver observer) {
        saleObservers.add(observer);
    }
}

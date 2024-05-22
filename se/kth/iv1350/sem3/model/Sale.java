package se.kth.iv1350.sem3.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.sem3.integration.ItemDTO;
import se.kth.iv1350.sem3.integration.ItemDoesNotExistException;
import se.kth.iv1350.sem3.integration.ItemRegistry;

/**
 * a sale made by one customer.
 */
public class Sale {
    // private Controller contr;
    private ItemRegistry itemRegistry;
    private LocalTime saleTime;
    // private Receipt receipt;

    private double totalCost;
    private double totalVAT;

    private Pay transaction;

    private List<SaleObserver> saleObservers = new ArrayList<>();
    private List<ItemDTO> basket = new ArrayList<>();

    /**
     * Creates a new instance and saves time of sale.
     */
    public Sale(ItemRegistry itemRegistry) {
        this.itemRegistry = itemRegistry;
        saleTime = LocalTime.now();
        // receipt = new Receipt();
    }

    /**
     * Adds item to be proccessed for sale. Execs this when scanning items
     * 
     * @param id
     * @param quantity
     */
    public void addItemToBasket(String id, int quantity) throws ItemDoesNotExistException { // Decide if exception
                                                                                            // should keep propegating
                                                                                            // upwards or if you want to
                                                                                            // catch it here. Why?
        boolean itemExists = false;
        for (int i = 0; i < basket.size(); i++) {
            ItemDTO itemInstance = basket.get(i);
            if (id == itemInstance.getID()) {
                basket.set(i, itemAddedQuantity(itemInstance, quantity));
                itemExists = true;
            }
        }
        if (!itemExists) {
            basket.add(itemRegistry.returnItem(id));
        }
    }

    /**
     * Adds 1 to itemDTO quantity value.
     * 
     * @param item
     * @return a copy of <code>item</code> but with 1 added quantity
     */
    ItemDTO itemAddedQuantity(ItemDTO item, int quantity) {
        ItemDTO newItem = new ItemDTO(item.getID(), item.getName(), item.getDescription(),
                (item.getQuantity() + quantity), item.getCost(), item.getVAT());

        return newItem;
    }

    /**
     * Calculates total vat and amount which later can be gotten.
     * 
     * @param numberOfItemsToCalc How many items the method should iterate through
     *                            to calculate total vat and amount
     */
    public void calcTotal(int numberOfItemsToCalc) {
        totalCost = 0;
        totalVAT = 0;
        List<ItemDTO> currBasket = getBasket();
        for (int i = 0; i < currBasket.size(); i++) {
            ItemDTO itemInstance = currBasket.get(i);

            totalVAT += itemInstance.getCost() * itemInstance.getQuantity() * itemInstance.getVAT();
            totalCost += itemInstance.getCost() + totalVAT;
        }
    }

    public void payment(Pay transaction) {
        transaction.getTotal();
        transaction.getTotalVAT();

        this.transaction = transaction;
    }

    private void notifyObservers(ItemDTO soldItem) {
        for (SaleObserver obs : saleObservers) {
            obs.finishedSale(soldItem);
        }
    }

    public void notifySpecificSaleObserver(SaleObserver obs) { // add javadocs to these
        saleObservers.add(obs);
    }

    public void notifyAllSaleObservers(List<SaleObserver> observers) {
        saleObservers.addAll(observers);
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

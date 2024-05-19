package se.kth.iv1350.sem3.integration;

import java.io.IOException;

/**
 * Instanciates all registeries.
 */
public class SystemDelegator {
    private ItemRegistry itemRegistry = new ItemRegistry();
    // private SaleRegistery saleRegistery = new SaleRegistry();

    /**
     * Gets item registery value.
     */
    public ItemRegistry getItemRegistry() {
        return itemRegistry;
    }
}

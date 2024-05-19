package se.kth.iv1350.sem3.model;

import se.kth.iv1350.sem3.integration.ItemDTO;

public class DoesNotExistException extends Exception {
    private ItemDTO itemThatDoesNotExist;

    /**
     * Instanciates with a message specifiying what item does not exist. Maybe it
     * ran out.
     */
    public DoesNotExistException(ItemDTO itemThatDoesNotExist) {
        super("Cannot recognize identifier" + itemThatDoesNotExist.getID() + ", since it does not exist.");
        this.itemThatDoesNotExist = itemThatDoesNotExist;
    }

    /**
     * @return Item that does not exist.
     */
    public ItemDTO getItemThatDoesNotExist() {
        return itemThatDoesNotExist;
    }
}

package se.kth.iv1350.sem3.integration;

public class DoesNotExistException extends Exception {
    private ItemDTO itemThatDoesNotExist;

    /**
     * Instanciates with a message specifiying what item does not exist. Maybe it
     * ran out.
     */
    DoesNotExistException(ItemDTO itemThatDoesNotExist) {
        super("Cannot recognize identifier " + itemThatDoesNotExist.getID() + ", since it does not exist.");
        this.itemThatDoesNotExist = itemThatDoesNotExist;
    }

    /**
     * @return Item that does not exist.
     */
    public ItemDTO getItemThatDoesNotExist() {
        return itemThatDoesNotExist;
    }
}

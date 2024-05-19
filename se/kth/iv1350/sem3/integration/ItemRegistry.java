package se.kth.iv1350.sem3.integration;

//import se.kth.iv1350.sem3.controller.Controller;
//import se.kth.iv1350.sem3.model.Sale;

//import java.util.ArrayList;
//import java.util.List;

public class ItemRegistry {
    private ItemDTO[] items = new ItemDTO[3];

    /**
     * Creates new instance.
     * 
     * @param contr The controller that is used for all operations.
     */
    public ItemRegistry() {
        addAllItems();
    }

    /**
     * Get the list of items
     * 
     * @return the list in item inventory
     */

    public ItemDTO[] getInventory() {
        return items;
    }

    /**
     * gets items from <code>SystemDelegator<code> --> ItemRegistry
     * 
     * @param id id of product you want to get
     * @throws ItemRegistryException gets called when something is wrong
     *                               with database. Checked.
     * @throws DoesNotExistException throws when identifier is not in database.
     *                               Unchecked.
     * 
     */
    public ItemDTO returnItem(String id) throws DoesNotExistException {
        ItemDTO[] itemArray = getInventory();
        ItemDTO item = null;

        if (id.equals("err111")) {
            throw new ItemRegistryException("Critical database failure");
        }

        for (int i = 0; i < itemArray.length; i++) {
            if (id == itemArray[i].getID()) {
                item = itemArray[i];
            }
        }
        checkIfItemExist(item);
        return item;
    }

    /**
     * Checks if item is null or not. If null throws exception detailed in throws.
     * 
     * @param item
     * @throws DoesNotExistException
     */
    private void checkIfItemExist(ItemDTO item) throws DoesNotExistException {
        if (item == null) {
            throw new DoesNotExistException(item);
        }
    }

    /**
     * Hard coded database, this should be connected to some
     * UI. Using normal array, arraylist to be implemented later for .add()
     * function.
     */
    private void addAllItems() {
        items[0] = new ItemDTO("abc123", "BigWheel Oatmeal",
                "BigWheel Oatmeal 500 g, whole grain oats, high fiber, gluten free", 1, 29.90, 0.06);

        items[1] = new ItemDTO("abc123", "BigWheel Oatmeal",
                "BigWheel Oatmeal 500 g, whole grain oats, high fiber, gluten free", 1, 29.90, 0.06);

        items[2] = new ItemDTO("def456", "YouGoGo Blueberry",
                "YouGoGo Blueberry 240 g, low sugar yoghurt, blueberry flavour", 1, 14.90, 0.06);
    }
}

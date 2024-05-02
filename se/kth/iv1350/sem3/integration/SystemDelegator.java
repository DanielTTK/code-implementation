package se.kth.iv1350.sem3.integration;

//import java.util.ArrayList;
//import java.util.List;

public class SystemDelegator {
    private ItemDTO[] items = new ItemDTO[3];

    public SystemDelegator() {
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
     * Hard coded database, this should be connected to some
     * UI. Using normal array, arraylist too difficult.
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

package se.kth.iv1350.sem3.view;

import java.io.IOException;
//import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.sem3.controller.Controller;
import se.kth.iv1350.sem3.util.Logger;
import se.kth.iv1350.sem3.integration.ItemDTO;
import se.kth.iv1350.sem3.integration.ItemDoesNotExistException;

/**
 * This is a placeholder for the real view. Contains hardcoded execution,
 * calling methods inside controller.
 */
public class View {
    private Controller contr;
    private Logger logger;
    private ErrorDisplay errorWriter = new ErrorDisplay();

    /**
     * Creates new instance.
     * 
     * @param contr The controller that is used for all operations.
     * @throws IOException if logger fails.
     */
    public View(Controller contr) throws IOException { // good example of constructor that doesnt need test
        this.contr = contr;

        this.logger = new Logger();
    }

    /**
     * Simulating a user input from a view screen that calls to all system
     * operations.
     * 
     * @throws DoesNotExistException exception if scanned item does not exist.
     */
    public void runFakeExecution() throws ItemDoesNotExistException {
        contr.startSale();

        contr.scanItem("abc123", 2); // finds item from contr-->integration and adds the count there
        contr.scanItem("def456", 1);

        printRecieptDigital();
    }

    /**
     * prints the reciept for interface
     */
    public void printRecieptDigital() { // this method should be in reciept
        List<ItemDTO> currBasket = contr.getBasket();

        for (int i = 0; i < currBasket.size(); i++) {
            ItemDTO itemInstance = currBasket.get(i);
            contr.calcTotal(i);
            System.out.print("Add 1 item with item id " + itemInstance.getID() +
                    "\nItem ID: " + itemInstance.getID() +
                    "\nItem name: " + itemInstance.getName() +
                    "\nItem cost: " + itemInstance.getCost() +
                    "\nVAT: " + itemInstance.getVAT() +
                    "\nItem description: " + itemInstance.getDescription() +
                    "\n\nTotal cost (incl VAT): " + contr.getTotalCost() +
                    "\nTotal VAT: " + contr.getTotalVAT() + "\n\n");

        }
    }
}

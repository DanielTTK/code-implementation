package se.kth.iv1350.sem3.startup;

import se.kth.iv1350.sem3.controller.Controller;
import se.kth.iv1350.sem3.view.View;
import se.kth.iv1350.sem3.integration.SystemDelegator;

/**
 * Starts application by starting view which in turn allows for program to
 * initiate.
 */
public class Main {
    /**
     * The main method used to start the entire application
     * 
     * @params args
     */
    public static void main(String[] args) {
        Controller contr = new Controller();
        View view = new View(contr);
        view.runFakeExecution();

        SystemDelegator delegator = new SystemDelegator();
        // Controller controller = new Controller(delegator);
    }
}
package com.techelevator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;


public class VendingMachine {

    private BigDecimal totalMoney = new BigDecimal(0.00);
    private Map<String, VendingMachineItem> inventory;
    private String userChoice;
    //private String DEFAULT_CHOICE = null;

    public String getUserChoice(){
        return userChoice;
    }

    public CoinBox coinBoxMain;
    public ItemInventory itemInventoryMain;
    public VendingMachineItem VendingMachineItemMain;
    public Logger loggerMain;




    // Overload constructor to create methods from other classes
    public VendingMachine(){
        this.coinBoxMain = new CoinBox();
        this.itemInventoryMain = new ItemInventory();
        this.VendingMachineItemMain = new VendingMachineItem();
        this.loggerMain = new Logger();
    }

    //This constructor should give us all classes and elements we need from outside sources
    //Basically, everything a vendingmachine is made of...
    //An inventory, and a coinbox
    public VendingMachine(ItemInventory vendingMachineMap, CoinBox coinbox, Logger loggerInput){
        this.itemInventoryMain = vendingMachineMap;
        this.coinBoxMain = coinbox;
        this.loggerMain = loggerInput;
        this.VendingMachineItemMain = new VendingMachineItem();
    }

    //Method to add Item choice from CLI
    public void addChoice(String itemChoice){
        userChoice = itemChoice.toUpperCase();
    }

    //Method to print Choice.
    public String printChoice(){
        return userChoice;
    }


    public void makePurchase(String selectionName) {
        if (itemInventoryMain.getInventoryMap().get(selectionName).getAmount() > 0) {
            if (coinBoxMain.getBalance().compareTo(itemInventoryMain.getInventoryMap().get(selectionName).getPrice()) >= 0) {
                coinBoxMain.setBalance(coinBoxMain.getBalance().subtract(itemInventoryMain.getInventoryMap().get(selectionName).getPrice()));
                itemInventoryMain.getInventoryMap().get(selectionName).setAmount(itemInventoryMain.getInventoryMap().get(selectionName).getAmount() - 1);
                System.out.println("Current balance left is: $" + coinBoxMain.getBalance());
                System.out.println("Current inventory amount is: " + itemInventoryMain.getInventoryMap().get(selectionName).getAmount());
            }else{
                System.out.println("Insufficient Funds ");
            }
        }else{
            System.out.println("Insufficient Inventory ");
        }
    }

}

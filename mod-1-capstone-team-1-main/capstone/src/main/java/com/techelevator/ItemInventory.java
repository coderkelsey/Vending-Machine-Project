package com.techelevator;

import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class ItemInventory {
    private final int AMOUNT = 5;
    private final String CSVPATH = "vendingmachine.csv";

    private Map<String, VendingMachineItem> inventoryMap = new HashMap<>();


    public Map<String, VendingMachineItem> getInventoryMap() {
        return inventoryMap;
    }

    public VendingMachineItem vendingMachineItemMain;
    //DEFAULT CONSTRUCTOR
    public ItemInventory(){}

    //public List<String> fileReader() {
    public void inventoryLoad() {
        Map<String, VendingMachineItem> inventory = new HashMap<String, VendingMachineItem>();


        String filePath = CSVPATH;
        File vendingMachine = new File(filePath);
        //File Scanner
        List<String> inventoryList = new ArrayList<>();
        try (Scanner myScanner = new Scanner(vendingMachine)) {
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                String[] parseLine = line.split("\\|");
                for (int i = 0; i < parseLine.length; i++) {
                    inventory.put(parseLine[0], createItem(parseLine));
                }
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("ERROR: file is not found.");
        }
        inventoryMap = inventory;
    }


    public VendingMachineItem createItem(String[] loadedData){
        //We should be able to know what index is linked to what field
        //vending slot
        String vendingslot = loadedData[0];
        //name
        String name = loadedData[1];
        //price
        BigDecimal price = new BigDecimal(loadedData[2]);
        //type
        String type = loadedData[3];

        if(type.equals("Chip")){
            return new ChipsItem(vendingslot, name, price, type, AMOUNT);
        }
        else if(type.equals("Drink")){
            return new DrinkItem(vendingslot, name, price, type, AMOUNT);
        }
        else if(type.equals("Candy")){
            return new CandyItem(vendingslot, name, price, type, AMOUNT);
        }
        else if(type.equals("Gum")){
            return new GumItem(vendingslot, name, price, type, AMOUNT);
        }
        return new VendingMachineItem(vendingslot, name, price, type, AMOUNT);
    }




    public void printInventory(){
        for(Map.Entry<String, VendingMachineItem> e: inventoryMap.entrySet()){
            if(e.getValue().getAmount() != 0){
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(String.format("Slot: %s\nName: %s\nPrice: %f",
                        e.getValue().getVendingSlot(),
                        e.getValue().getName(),
                        e.getValue().getPrice()));
            }else{
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(String.format("Slot: %s\nName: %s\n %s",
                        e.getValue().getVendingSlot(),
                        e.getValue().getName(),
                        "SOLD OUT!"));
            }
        }

    }
}

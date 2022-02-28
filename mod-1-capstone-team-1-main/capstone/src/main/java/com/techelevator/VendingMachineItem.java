package com.techelevator;

import java.math.BigDecimal;

public class VendingMachineItem {


    private String name;
    private BigDecimal price;
    private String type;
    private String vendingSlot;
    private int amount;
    private String sound;


    //GETTERS
    public String getName(){
        return name;
    }
    public BigDecimal getPrice(){
        return price;
    }
    public String getType(){
        return type;
    }
    public String getVendingSlot(){
        return vendingSlot;
    }
    public int getAmount(){ return amount; }
    public String getSound(){
        return sound;
    }

    //SETTERS
    public void setAmount(int am){amount = am;}

    public VendingMachineItem(){}

    public VendingMachineItem(String vendingSlot, String name, BigDecimal price, String type, int amount){
        this.name = name;
        this.price = price;
        this.type = type;
        this.vendingSlot = vendingSlot;
        this.amount = amount;
    }
}

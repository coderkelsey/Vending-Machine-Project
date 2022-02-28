package com.techelevator;

import java.math.BigDecimal;


public class GumItem extends VendingMachineItem{
    //DEFAULT SUPER CONSTRUCTOR
    public GumItem(String vendingSlot, String name, BigDecimal price, String type, int amount){
        super(vendingSlot, name, price, "Gum", amount);
    }

    @Override
    public String getSound(){
        return "Chew Chew, Yum!";
    }

}

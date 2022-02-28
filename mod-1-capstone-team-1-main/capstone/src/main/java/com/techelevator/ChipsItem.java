package com.techelevator;

import java.math.BigDecimal;

public class ChipsItem extends VendingMachineItem{

    //DEFAULT SUPER CONSTRUCTOR
    public ChipsItem(String vendingSlot, String name, BigDecimal price, String type, int amount){
        super(vendingSlot, name, price, "Chip", amount);
    }

    @Override
    public String getSound(){
        return "Crunch Crunch, Yum!";
    }
}

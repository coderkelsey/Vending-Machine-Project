package com.techelevator;

import java.math.BigDecimal;

public class CandyItem extends VendingMachineItem{
    //DEFAULT SUPER CONSTRUCTOR
    public CandyItem(String vendingSlot, String name, BigDecimal price, String type, int amount){
        super(vendingSlot, name, price, "Candy", amount);
    }

    @Override
    public String getSound(){
        return "Munch Munch, Yum!";
    }
}

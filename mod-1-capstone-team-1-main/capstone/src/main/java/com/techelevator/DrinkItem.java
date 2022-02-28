package com.techelevator;

import java.math.BigDecimal;

public class DrinkItem extends VendingMachineItem{

    //DEFAULT SUPER CONSTRUCTOR
    public DrinkItem(String vendingSlot, String name, BigDecimal price, String type, int amount){

        super(vendingSlot, name, price, "Drink", amount);
    }

    @Override
    public String getSound(){
        return "Glug Glug, Yum!";
    }
}

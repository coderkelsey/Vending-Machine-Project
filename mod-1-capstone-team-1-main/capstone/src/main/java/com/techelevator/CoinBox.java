package com.techelevator;

import com.techelevator.VendingMachineCLI;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CoinBox {
    private int dimes;
    private int nickles;
    private int quarters;
    private BigDecimal balance;
    private BigDecimal totalChangeOutput;
    private BigDecimal userInputMoney;

    public int getDimes() {return dimes;}
    public int getNickles() {return nickles;}
    public int getQuarters() {return quarters;}
    public BigDecimal getTotalChangeOutput() {return totalChangeOutput;}
    public BigDecimal getUserInputMoney() {return userInputMoney;}


    public BigDecimal getBalance() {return balance;}

    public void setBalance(BigDecimal value){
        this.balance = value;
    }

    //DEFAULT CONSTRUCTOR
    public CoinBox(){
        this.balance = new BigDecimal(0.00);
    }

    public void addMoney(BigDecimal deposit){
        balance = balance.add(deposit);
    }

    public String returnBalance(){
        return "Current Money Provided: $" + balance;
    }

    public void makeChange(){
        balance = balance.setScale(2,RoundingMode.HALF_UP);
        MathContext mc = new MathContext(2);
        int calcValue = balance.multiply(BigDecimal.valueOf(100.0)).intValueExact();
        int quarter = 25;
        int dime = 10;
        int nickel = 5;
        BigDecimal zero = new BigDecimal(0.00, mc);
        //create all three needed currency counts, and set them to 0
        int quarterCount = 0, dimeCount = 0, nickelCount = 0;

        quarterCount = calcValue/quarter;
        calcValue = calcValue%quarter;

        dimeCount = calcValue/dime;
        calcValue = calcValue%dime;

        nickelCount = calcValue/nickel;
        calcValue = calcValue%nickel;


        System.out.println("Here's your change:\nQuarters: " + quarterCount + "\nDimes: " + dimeCount + "\nNickels: " + nickelCount);

        //Setting the balance to zero
        balance = zero;


    }

}

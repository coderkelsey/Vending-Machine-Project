package com.techelevator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

public class CoinBoxtest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;


    private CoinBox cbx;

    @Before
    public void setup(){
        cbx = new CoinBox();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void teardown(){
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void addMoney_Invalid_Value_Returns_Correct_Value(){
        BigDecimal expected = new BigDecimal(10.0);
        cbx.addMoney(expected);
        Assert.assertEquals(expected, cbx.getBalance());
    }

    @Test
    public void makeChange_Returns_Correct_Value(){
        BigDecimal balance = new BigDecimal(10.60);
        cbx.setBalance(balance);
        cbx.makeChange();
        BigDecimal zero = new BigDecimal(0.00);
        String expected = "Here's your change:\nQuarters: 42\nDimes: 1\nNickels: 0\n";
        Assert.assertEquals(zero, cbx.getBalance());
        Assert.assertEquals(expected, outContent.toString().replaceAll("\r", ""));
    }
}

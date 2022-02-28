package com.techelevator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

public class VendingMachineTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private VendingMachine vmm;
    private CoinBox cbx = new CoinBox();
    private ItemInventory iim = new ItemInventory();
    private Logger lggr = new Logger();

    @Before
    public void setup(){
        vmm = new VendingMachine(iim, cbx, lggr);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void teardown(){
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void addChoice_Returns_Expected_Value_Case_inSensitive(){
        String expected = "A1";
        vmm.addChoice("a1");
        Assert.assertEquals(expected, vmm.getUserChoice());
    }
    @Test
    public void addChoice_Returns_Expected_Value_Case_Sensitive(){
        String expected = "A1";
        vmm.addChoice("A1");
        Assert.assertEquals(expected, vmm.getUserChoice());
    }

    @Test
    public void makePurchase_Returns_Expected_EnoughMoney(){
        BigDecimal deposit = new BigDecimal(10.0);
        iim.inventoryLoad();
        cbx.setBalance(deposit);
        vmm.makePurchase("A4");
        String expected = "Current balance left is: $6.35\nCurrent inventory amount is: 4\n";
        Assert.assertEquals(expected, outContent.toString().replaceAll("\r", ""));

    }
    @Test
    public void makePurchase_Returns_Insufficient_Funds(){
        BigDecimal deposit = new BigDecimal(2.00);
        iim.inventoryLoad();
        cbx.setBalance(deposit);
        vmm.makePurchase("A4");
        String expected = "Insufficient Funds \n";
        Assert.assertEquals(expected, outContent.toString().replaceAll("\r", ""));

    }
    @Test
    public void makePurchase_Returns_Insufficient_Inventory(){
        BigDecimal deposit = new BigDecimal(10.00);
        iim.inventoryLoad();
        iim.getInventoryMap().get("A4").setAmount(0);
        cbx.setBalance(deposit);
        vmm.makePurchase("A4");
        String expected = "Insufficient Inventory \n";
        Assert.assertEquals(expected, outContent.toString().replaceAll("\r", ""));

    }

}

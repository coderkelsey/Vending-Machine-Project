package com.techelevator;

import com.techelevator.view.Menu;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {
	//MAIN MENU OPTIONS
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};

	//PURCHASE MENU
	private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION};

	private Menu menu;

	public CoinBox moneyInputCoinBox; //= new CoinBox();

	public ItemInventory instanceOfInvMain;

	public VendingMachine vendingMachineMain;

	public Logger loggerMain;



	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
		this.instanceOfInvMain = new ItemInventory();
		instanceOfInvMain.inventoryLoad(); //ANY CLASSES CONSTRUCTOR MUST INITIALIZE ITS VALID STATE.
		//moved coinbox instantiation above vendingmachine, so we can safely pass it into that constructor
		this.moneyInputCoinBox = new CoinBox();
		//OUR CONSTRUCTOR'S JOB IS TO MAKE SURE THE CLI HAS ALL DATA NEEDED WHEN INSTANTIATED.
		this.loggerMain = new Logger();

		this.vendingMachineMain = new VendingMachine(instanceOfInvMain, moneyInputCoinBox, loggerMain);

	}

	public void run() {
		boolean running = true;
		boolean purchasable = true;
		while (running) {
			String choiceMainMenu = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			Scanner userInputScanner = new Scanner(System.in);
			// Prints out Vending Machine Choices
			if (choiceMainMenu.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
			instanceOfInvMain.printInventory();
			// Displays Purchase Menu
			}
			else if (choiceMainMenu.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				// DISPLAYS FEED MONEY
				String choicePurchaseMenu = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
				if (choicePurchaseMenu.equals(PURCHASE_MENU_FEED_MONEY)){
					System.out.println("Please insert this amount in $1, $2, $5 and $10 bills");
				 	int userFeedMoney = Integer.parseInt(userInputScanner.nextLine());
				 	if(userFeedMoney == 1 || userFeedMoney == 2 || userFeedMoney == 5 || userFeedMoney == 10){
						BigDecimal userFeedMoneyBD = BigDecimal.valueOf(userFeedMoney);
						String userFeedMoneyStr = String.valueOf(userFeedMoney);
						moneyInputCoinBox.addMoney(userFeedMoneyBD);
						String loggerStr = " FEED MONEY: $" + userFeedMoneyStr + " $" +  moneyInputCoinBox.getBalance();
						System.out.println(moneyInputCoinBox.returnBalance());
						try {
							loggerMain.fileWriter(loggerStr);
						} catch (IOException e) {
						}
					} else{
						System.out.println("Unable to accept input amount: Please insert this amount in $1, $2, $5 and $10 bills");
					}

				// PROMPTS SELECT ITEM
				}
				else if(choicePurchaseMenu.equals(PURCHASE_MENU_SELECT_PRODUCT)){
					instanceOfInvMain.printInventory();
					System.out.println("Choose an Item from the Vending Machine? (Not Case Sensitive!)");
					String vendingMachineChoice = userInputScanner.nextLine();
					if(instanceOfInvMain.getInventoryMap().get(vendingMachineChoice) != null) {
						vendingMachineMain.addChoice(vendingMachineChoice);
						System.out.println(vendingMachineMain.getUserChoice());
					}else{
						System.out.println("ERROR: Product Code Does Not Exist. Please Review the Inventory List Again.");
					}
					if(vendingMachineMain.getUserChoice()!=null){
						if(moneyInputCoinBox.getBalance().compareTo(instanceOfInvMain.getInventoryMap().get(vendingMachineMain.getUserChoice()).getPrice()) >= 0){
							if(instanceOfInvMain.getInventoryMap().get(vendingMachineMain.getUserChoice()).getAmount() > 0) {
								vendingMachineMain.makePurchase(vendingMachineMain.getUserChoice().toString());
								System.out.println("---------------------------");
								System.out.println(instanceOfInvMain.getInventoryMap().get(vendingMachineMain.getUserChoice().toString()).getName());
								System.out.println("Price of Item: " + instanceOfInvMain.getInventoryMap().get(vendingMachineMain.getUserChoice().toString()).getPrice());
								System.out.println("Remaining Balance: " + moneyInputCoinBox.getBalance());
								System.out.println(instanceOfInvMain.getInventoryMap().get(vendingMachineMain.getUserChoice().toString()).getSound());
								String loggerStr = " " + instanceOfInvMain.getInventoryMap().get(vendingMachineMain.getUserChoice()).getName() + " " +  vendingMachineMain.getUserChoice() + " $"+ moneyInputCoinBox.getBalance().add(instanceOfInvMain.getInventoryMap().get(vendingMachineMain.getUserChoice()).getPrice()) + " $" + moneyInputCoinBox.getBalance();
								try {
									loggerMain.fileWriter(loggerStr);
								} catch (IOException e) {
								}
							}else{
								System.out.println("Insufficient Inventory. Please Come Back Another Time or Select New Treat!");
							}
						}else{
							System.out.println("Insufficient Funds. Unable to Finish Transaction. Please Feed More Money to Purchase. ");
						}
					} else{
						System.out.println("You Have not chosen an item");
					}
				// PROMPTS FINISH TRANSACTION
				} else if(choicePurchaseMenu.equals(PURCHASE_MENU_FINISH_TRANSACTION)){
					String loggerStr = " GIVE CHANGE: $" + moneyInputCoinBox.getBalance() + " $0";
					moneyInputCoinBox.makeChange();
					try {
						loggerMain.fileWriter(loggerStr);
					} catch (IOException e) {
					}
				}
			//EXITS CLI
			} else if(choiceMainMenu.equals(MAIN_MENU_OPTION_EXIT)){
				System.out.println("Thank you for your purchase");
				String loggerStr = " GIVE CHANGE: $" + moneyInputCoinBox.getBalance() + " $0";
				moneyInputCoinBox.makeChange();
				try {
					loggerMain.fileWriter(loggerStr);
				} catch (IOException e) {
				}
				running =  false;
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Umbrella Corp Vending Machine!");
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}

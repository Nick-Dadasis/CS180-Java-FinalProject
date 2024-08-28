/* Nick Dadasis
Final Project */

import java.io.*;
import java.util.Scanner;

public class CarlosCars { 
	
	final static Scanner read = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		File file = new File("CarlosCars.txt");
	    Scanner inputFile = new Scanner(file);
		
		Car[] cars = new Car[30];
		
		int i=0, choice;
		String fname, lname;
		boolean done = false;

		while(inputFile.hasNext()) {
			cars[i] = new Car(inputFile.nextInt(), inputFile.next(), inputFile.next(), inputFile.next().charAt(0), inputFile.nextDouble(), inputFile.nextBoolean());
			i++;
		}
		
		System.out.println("Please sign in so we may assist you...\n");
		System.out.print("First Name: ");
		fname = read.next();
		System.out.print("Last Name: ");
		lname = read.next();
		System.out.println("Hello " + fname + " " + lname + ", let's get shopping for a car...");

		while (!done)
		{
			System.out.println();
			System.out.println("********    Welcome to Carlos Cars  ********");
			System.out.println("**** We have the best car prices around!! ****");
			System.out.println("\nHow can we help you? ");
			System.out.println("         1. Purchase a Car");
			System.out.println("         2. Lease a Car");
			System.out.println("         3. Quit");
			System.out.println();
			System.out.print("Please enter 1, 2, 3? ");

			choice = read.nextInt();
			read.nextLine();
			System.out.println();
			
			switch (choice) 
			{
				case 1:  purchaseCar(cars);
						 break;
						
				case 2:  leaseCar(cars);
				 		 break;
				 
				case 3:  backupInventory(cars);
				         done = true;
						 break;
						
				default: System.out.println("Invalid choice, try again...");
			}
		}
		System.out.println("Thank you for shopping at Carlo's Cars!");
		System.out.println("Program Ended...");
		
		inputFile.close();
		read.close(); 
	}

	public static void searchInventory(Car[] cars, char opt) {
		
		System.out.println("Stock#                      Car Information");
		System.out.println("------       ---------------------------------------------");
		
		for(int i = 0; i < cars.length; i++) {
			if(cars[i] != null && (opt == cars[i].getOption()) && cars[i].isAvailable() == true)
				System.out.printf("  %02d         " + cars[i].toString() + "\n", (i + 1) );
		}
	}

	public static void purchaseCar(Car[] cars)	{
		
		searchInventory(cars, 'p');
		System.out.print("Would you like to purchase one of the listed cars? (y=yes n=no) ");
		
		if (read.nextLine().equals("y")) {
			System.out.print("Enter the stock number of the car you would like to purchase. ");
			int carPurchased = read.nextInt() - 1;
			read.nextLine();
			
			System.out.println("\nYou selected: " + cars[carPurchased].toString());
			System.out.printf("\nThe price of your car with tax included is: $%.2f \nPlease confirm you wish to purchase this car. (y=yes n=no) ", cars[carPurchased].getPrice() * 1.0625);
			
			if (read.nextLine().equals("y")) {
				cars[carPurchased].setAvailability(false);
				System.out.println("\nThank you for your purchase! Returning to main program now.");
			}
		}
		else 
			System.out.println("\nCheck back another time. See you again soon! \nReturning to main program...");		
	}

	public static void leaseCar(Car[] cars) {
		String input;
		char leasePackage;
		double downPayment = 0;
		double carPrice;
		double monthlyFee = 0;
		
		searchInventory(cars, 'l');
		System.out.print("Would you like to lease one of the listed cars? (y=yes n=no) ");
		
		if (read.nextLine().equals("y")) {
			System.out.print("Enter the stock number of the car you would like to lease. ");
			int carLeased = read.nextInt() - 1;
			read.nextLine();
			
			System.out.println("\nYou selected: " + cars[carLeased].toString());
			System.out.println("\nWe have 3 lease packages available for this vehicle:");
			System.out.println("Package A:  24 month lease, 15% down");
			System.out.println("Package B:  36 month lease, 13% down");
			System.out.println("Package C:  48 month lease, 10% down");
			System.out.print("Which lease package would you like? (A, B, or C): ");
			
			carPrice = cars[carLeased].getPrice();
			input = read.nextLine();
			leasePackage = input.charAt(0);
			
			if (leasePackage == 'A') {
				downPayment = carPrice * .15;
				monthlyFee = ((carPrice * 0.5) - downPayment) / 24;
			}
			else if (leasePackage == 'B') {
				downPayment = carPrice * .13;
				monthlyFee = ((carPrice * 0.5) - downPayment) / 36;
			}
			else if (leasePackage == 'C') {
				downPayment = carPrice * .10;
				monthlyFee = ((carPrice * 0.5) - downPayment) / 48;
			}

			System.out.printf("\nThe price of lease package " + leasePackage + " will cost: $%.2f down and $%.2f per month. \nPlease confirm you wish to lease this car. (y=yes n=no) ", downPayment, monthlyFee);
			
			if (read.nextLine().equals("y")) {
				cars[carLeased].setAvailability(false);
				System.out.println("\nThank you for your lease purchase! Returning to main program now.");
			}
		}
		else 
			System.out.println("\nCheck back another time. See you again soon! \nReturning to main program...");		
	}

	public static void backupInventory(Car[] cars) throws IOException {
		
		PrintWriter updatedInv = new PrintWriter("CarlosCars.txt");
		
		for (int i = 0; i < cars.length; i++) {
			if (cars[i] != null && cars[i].isAvailable() == true)
				updatedInv.println(cars[i].toString());
		}
		
		updatedInv.close();		
	}
	
}
	
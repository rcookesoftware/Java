import java.util.Scanner;
import java.util.Random;
import java.io.*;

public class MathsProject{
	public static void main(String args[])throws IOException{
		Scanner keyboard = new Scanner(System.in);
		int selection = 0, choice = 0;
		double currency = 0;

		while(choice != 2){
			System.out.print("Please Enter your starting value: ");
			currency = keyboard.nextDouble();
			mainMenu();
			selection = keyboard.nextInt();
			dashLine();

			while(selection < 1 || selection > 4){
				System.out.print("Invalid selection\nPlease re-enter selection: ");
				selection = keyboard.nextInt();
			}

			switch(selection){
				case 1: eurosConversion(keyboard, currency, selection);
						dashLine();
						break;
				case 2: poundsConversion(keyboard, currency, selection);
						dashLine();
						break;
				case 3: dollarsConversion(keyboard, currency, selection);
						dashLine();
						break;
			}

			System.out.println("Would you like to re-enter another value for conversion or convert current currency to crypto \n(1 for re-entry/2 for crypto): ");
			choice = keyboard.nextInt();
			dashLine();
		}

		bitcoinCryptoMenu(currency, selection, keyboard);

		PrintWriter outputFile = new PrintWriter("MathsProject.txt");
		outputFile.println("Maths Project Example\n\n" + bitcoinCryptoMenu(currency, selection, keyboard));
		outputFile.close();
	}

	public static void mainMenu(){
		System.out.println("Welcome to Ryan Cubeds' Maths Project");
		System.out.println("Welcome to our Currency Converter");
		dashLine();
		System.out.print("Please select your starting currency\n1.Euro\n2.Pounds\n3.Dollars\n4.Exit\nPlease enter selection: ");
	}

	public static void dashLine(){
		System.out.println("--------------------------------");
	}

	public static int conversionMenu(int selection, Scanner keyboard){
		int choice = 0;	String selectedCurrency = "";
		switch(selection){
			case 1: selectedCurrency = "Euros";
					break;
			case 2: selectedCurrency = "Pounds";
					break;
			case 3: selectedCurrency = "Dollars";
					break;
		}
		System.out.println("Please select what currency you would like to convert " + selectedCurrency + " to");
		if(selectedCurrency.equals("Euros")){
			System.out.print("1.Pounds\n2.Dollars\n3.Crypto\n4.Bitcoin\nEnter choice: ");
			choice = keyboard.nextInt();
		}
		else if(selectedCurrency.equals("Pounds")){
			System.out.print("1.Euros\n2.Dollars\n3.Crypto\n4.Bitcoin\nEnter choice: ");
			choice = keyboard.nextInt();
		}
		else if(selectedCurrency.equals("Dollars")){
			System.out.print("1.Euros\n2.Pounds\n3.Crypto\n4.Bitcoin\nEnter choice: ");
			choice = keyboard.nextInt();
		}
		return choice;
	}

	public static double eurosConversion(Scanner keyboard, double currency, int selection){
		double result = 0;
		switch(conversionMenu(selection, keyboard)){
			case 1: result = (currency*0.85);
					System.out.println("Euros to Pounds: " + result);
					break;
			case 2: result = (currency*1.09);
					System.out.println("Euros to Dollars: " + result);
					break;
			case 3: result = (currency*0.000017);
					System.out.println("Euros to Crypto: " + result);
					break;
			case 4: result = (currency*0.000017);
					System.out.println("Euros to Bitcoin: " + result);
					break;
		}
		return result;
	}

	public static double poundsConversion(Scanner keyboard, double currency, int selection){
		double result = 0;
		switch(conversionMenu(selection, keyboard)){
			case 1: result = (currency*1.17);
					System.out.println("Pounds to Euros: " + result);
					break;
			case 2: result = (currency*1.27);
					System.out.println("Pounds to Dollars: " + result);
					break;
			case 3: result = (currency*0.00002);
					System.out.println("Pounds to Crypto: " + result);
					break;
			case 4: result = (currency*0.00002);
					System.out.println("Pounds to Bitcoin: " + result);
					break;
		}
		return result;
	}

	public static double dollarsConversion(Scanner keyboard, double currency, int selection){
		double result = 0;
		switch(conversionMenu(selection, keyboard)){
			case 1: result = (currency*0.92);
					System.out.println("Dollars to Euros: " + result);
					break;
			case 2: result = (currency*0.79);
					System.out.println("Dollars to Dollars: " + result);
					break;
			case 3: result = (currency*0.000016);
					System.out.println("Dollars to Crypto: " + result);
					break;
			case 4: result = (currency*0.000016);
					System.out.println("Dollars to Bitcoin: " + result);
					break;
		}
		return result;
	}

	public static String bitcoinCryptoMenu(double currency, int selection, Scanner keyboard){
		int choice = 0;	String selectedCurrency = "", result = "";
		switch(selection){
			case 1: selectedCurrency = "Euros";
					break;
			case 2: selectedCurrency = "Pounds";
					break;
			case 3: selectedCurrency = "Dollars";
					break;
		}
		System.out.println("You currently have " + currency + " " + selectedCurrency + " converted to crypto");
		System.out.print("Would you like to invest in a stock or exit program (1 for invest/2 for exit): ");
		choice = keyboard.nextInt();

		switch(choice){
			case 1:
			System.out.print("Choose a stock\n1.Apple\n2.Microsoft\n3.Tesla\n4.Netflix\nEnter choice: ");
			choice = keyboard.nextInt();
			switch(choice){
				case 1: result = "You have selected Apple";
						System.out.println(result);
						appleProfit(currency);
						break;
				case 2: result = "You have selected Microsoft";
						System.out.println(result);
						microsoftProfit(currency);
						break;
				case 3: result = "You have selected Tesla";
						System.out.println(result);
						teslaProfit(currency);
						break;
				case 4: result = "You have selected Netflix";
						System.out.println(result);
						netflixProfit(currency);
						break;
			}
			case 2: System.exit(0);
					break;
		}
		return result;
	}

	public static double investInApple(){
		double start = 0.56;
		double end = 0.97;
		double random = new Random().nextDouble();
		double result = start +  (random * (end - start));
		return result;
	}

	public static void appleProfit(double currency){
		double investment = 0, profit = 0;
		investment = currency + (investInApple() * currency);
		profit = investment - currency;
		System.out.printf("Total return made from investment in Apple is: %.5f%n" ,investment);
		System.out.printf("Profit after withdrawal is: %.2f%n" , profit);
	}

	public static double investInMicrosoft(){
		double start = 2.62;
		double end = 16.34;
		double random = new Random().nextDouble();
		double result = start +  (random * (end - start));
		return result;
	}

	public static void microsoftProfit(double currency){
		double investment = 0, profit = 0;
		investment = currency + (investInMicrosoft() * currency);
		profit = investment - currency;
		System.out.printf("Total return made from investment in Microsoft is: %.5f%n" ,investment);
		System.out.printf("Profit after withdrawal is: %.2f%n" , profit);
	}

	public static double investInTesla(){
		double start = 17.6;
		double end = 29.1;
		double random = new Random().nextDouble();
		double result = start +  (random * (end - start));
		return result;
	}

	public static void teslaProfit(double currency){
		double investment = 0, profit = 0;
		investment = currency + (investInTesla() * currency);
		profit = investment - currency;
		System.out.printf("Total return made from investment in Microsoft is: %.5f%n" ,investment);
		System.out.printf("Profit after withdrawal is: %.2f%n" , profit);
	}

	public static double investInNetflix(){
		double start = 1.1;
		double end = 49.7;
		double random = new Random().nextDouble();
		double result = start +  (random * (end - start));
		return result;
	}

	public static void netflixProfit(double currency){
		double investment = 0, profit = 0;
		investment = currency + (investInNetflix() * currency);
		profit = investment - currency;
		System.out.printf("Total return made from investment in Microsoft is: %.5f%n" ,investment);
		System.out.printf("Profit after withdrawal is: %.2f%n" , profit);
	}
}
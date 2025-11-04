// Q8-W5: Calling Methods in Java

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.stream.Collectors;
import java.util.List;

public class BankManager {
	
	public static void main(String[] args) {
		float average;
		BankAccount rich, poor;
		BankAccount[] accounts = openAccountFile(args[0]);
		//Your code goes in here.
		rich = AccountManagement.findAccount(AccountManagement.richest(accounts), accounts);
		poor = AccountManagement.findAccount(AccountManagement.poorest(accounts), accounts);
		System.out.println("The bank has $" + Float.toString(AccountManagement.overallBalance(accounts)) + ".");
		System.out.println("The richest account is " + rich.accountName() + ".");
		System.out.println("The poorest account is " + poor.accountName() + ".");
		average = (rich.currentBalance() + poor.currentBalance()) / 2;
		rich.withdraw(rich.currentBalance() - average);
		poor.deposit(average - poor.currentBalance());
		for (BankAccount ba : accounts) System.out.println(ba.accountName() + " has $" + Float.toString(ba.currentBalance()) + ".");
	}

	//You don't need to understand the following code, yet.
	//It just reads info from a file and turns it into an
	//array of BankAccount objects.
	private static BankAccount[] openAccountFile(String filename) {
		
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			
			List<String> rawData = reader.lines().collect(Collectors.toList());
			BankAccount[] accounts = new BankAccount[rawData.size()];
			
			for (int i = 0; i < accounts.length; ++i) {
				String[] splitData = rawData.get(i).split(" ");
				accounts[i] = new BankAccount(splitData[0].trim(), Float.parseFloat(splitData[1]));
			}
			
			return accounts;
		}
		catch (FileNotFoundException e) {
			System.err.println("The input file does not exist.");
			System.err.println(e.getMessage());
		}
		catch (IOException e) {
			System.err.println("Something went wrong trying to read the file.");
			System.err.println(e.getMessage());
		}
		
		return null;
		
	}	
}
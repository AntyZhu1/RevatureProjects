package com.anthonyzhu.project0;

import java.util.Scanner;

import java.util.HashMap;

public class Customer implements User{
	
	public static HashMap<String, Double> accountBalance = new HashMap<>();
	
	
	Scanner userIn = new Scanner(System.in);
	
	public String login(String username, String password) {
		// TODO Auto-generated method stub
		
		if (!customerLoginInfo.containsKey(username)) {
			
			System.out.println("We couldn't find your username in our system. Would you like to create an account?"
					+ "\n"
					+ "Please enter y or yes for Yes, and n or no for No");
			
			String userSelection = userIn.next().toLowerCase();
			
			if (userSelection.equals("y") | userSelection.equals("yes")) {
				
				System.out.println("Creating account... Please enter password:");
				
				String userNewPassword = userIn.next();
				
				pendingAccounts.put(username, userNewPassword);
				
				System.out.println(pendingAccounts.containsKey(username));
				
				System.out.println("Ok, we'll have a member of our team review this information and we'll let you know if it gets aproved.");
				
//				System.out.println("Creating account... Please enter password:");
//				
//				String userNewPassword = userIn.next();
//				
//				customerLoginInfo.put(username, userNewPassword);
//				
//				System.out.println("Your new password is: " + userNewPassword);
//				System.out.println("Please enter a deposit amount: ");
//				
//				double userDeposit = userIn.nextDouble();
//				
//				System.out.println("You are depositing: $" + userDeposit);
//				
//				accountBalance.put(username, userDeposit);
//				
//				System.out.println("Your new balance is: $" + userDeposit);
				
				
				return "No";
				
			}
			
			else if (userSelection.equals("n") | userSelection.equals("no")) {
				
				System.out.println("Ok. Have a nice day!");
				return "No";
				
			}
			
		}
		
		else if (customerLoginInfo.containsKey(username) & customerLoginInfo.get(username).equals(password) == false) {
			System.out.println("Incorrect Password, please try again.");
			return "No";
		}
		
		
//		customerLoginInfo.put(username, password);
		String currentLogIn = username;
		
		return currentLogIn;
	}
	
	public void loggedInOperations (String username) {
			
			boolean exiting = false;
			
				System.out.println("Welcome " + username);

			while (exiting == false) {
				
				System.out.println("");
				
				System.out.println("Your current balance is: $" + accountBalance.get(username));
				
				System.out.println("What would you like to do?"
						+ "\n"
						+ "Enter 'W' or 'Withdraw' to withdraw money."
						+ "\n"
						+ "Enter 'D' or 'Deposit' to deposit money."
						+ "\n"
						+ "Enter 'Q' or 'Quit' to exit program.");
				
				String userSelection = userIn.next().toLowerCase();
				
				if (userSelection.equals("w") | userSelection.equals("withdraw")) {
					
					System.out.println("Enter withdraw amount");
					
					double withdrawAmount = userIn.nextDouble();
					
					withdraw(username, withdrawAmount);
					
					System.out.println("");
					
				}
				
				else if (userSelection.equals("d") | userSelection.equals("deposit")) {
					
					System.out.println("Enter deposit amount");
					
					double depositAmount = userIn.nextDouble();
					
					deposit(username, depositAmount);
					
					System.out.println("");
					
				}
				
				else if (userSelection.equals("q") | userSelection.equals("quit")) {
					
					exiting = true;
					System.out.println("Program Exiting, thanks for using the program!");
				}
				
			}
	}
	
	public void deposit(String accountName, double depositAmount) {
		
		try {
			if (depositAmount > 0 & depositAmount <= accountBalance.get(accountName)) {
				System.out.println("Ok, depositing $" + depositAmount);
				
				double formerBalance = accountBalance.get(accountName);
				
				accountBalance.replace(accountName, formerBalance + depositAmount);
				
				System.out.println("Your new account balance is: $" + accountBalance.get(accountName));
			}
			else if (depositAmount < 0) {
				System.out.println("Invalid deposit amount. Deposit must be greater than 0.");
			}
			
			else {
				System.out.println("Invalid amount.");
			}
		}
		catch (Exception e) {
			System.out.println("Error, invalid entry.");
		}
		
	}


	public void withdraw(String accountName, double withdrawAmount) {
		
		try {
			if (withdrawAmount > 0 & withdrawAmount <= accountBalance.get(accountName)) {
				System.out.println("Ok, withdrawing $" + withdrawAmount);
				
				double formerBalance = accountBalance.get(accountName);
				
				accountBalance.replace(accountName, formerBalance - withdrawAmount);
				
				System.out.println("Your new account balance is: $" + accountBalance.get(accountName));
			}
			else if (withdrawAmount < 0) {
				System.out.println("Invalid withdraw amount. Withdraw must be greater than 0.");
			}
			
			else {
				System.out.println("Invalid amount.");
			}
		}
		catch (Exception e) {
			System.out.println("Error, invalid entry.");
		}
		
		
	}
	

}

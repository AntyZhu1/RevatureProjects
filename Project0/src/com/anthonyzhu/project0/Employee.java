package com.anthonyzhu.project0;

import java.util.*;

public class Employee implements User{
	
	Scanner userIn = new Scanner(System.in);
	@Override
	public String login(String username, String password) {
		// TODO Auto-generated method stub
		if (!employeeLoginInfo.containsKey(username)) {
			
			System.out.println("Username not found. Please check for errors in your username, or contact system administrators.");
			
			return "No";
			
		}
		
		else if (employeeLoginInfo.containsKey(username) & employeeLoginInfo.get(username).equals(password) == false ) {
			
			System.out.println("Incorrect Password, please try again.");
			
			return "No";
			
		}
		
		String currentLogIn = username;
		
		return currentLogIn;
	}

	public void loggedInOperations(String username) {
		
			System.out.println("Hello " + username + ", What would you like to do?");
			System.out.println("'Check Accounts' to check existing customer accounts"
					+ "\n"
					+ "'Check Pending' to check and approve or deny pending accounts"
					+ "\n"
					+ "'Quit' or 'Q' to close out.");
			
			String[] userSelection = userIn.nextLine().split(" ");
			
			String selectionOne = userSelection[0].toLowerCase();
								
			if (selectionOne.equals("check")) {
				
				String selectionTwo = userSelection[1].toLowerCase();
						
				if (selectionTwo.equals("accounts")) {
					System.out.println("Accounts: ");
					
					for (String name : User.customerLoginInfo.keySet()) {
						System.out.println("Username: " + name);
					}
					for (String pwd : User.customerLoginInfo.values()) {
						System.out.println("Password: " + pwd);
					}
					
				}
				
				else if (selectionTwo.equals("pending")) {
					System.out.println("Pending Accounts: ");
					
					for (String name : User.pendingAccounts.keySet()) {
						System.out.println("Username: " + name + "|*| Password: " + User.pendingAccounts.get(name));
						
						System.out.println("");
					}
//					for (String pwd : User.pendingAccounts.values()) {
//						System.out.println("Password: " + pwd);
//					}
					
					System.out.println("Approve any acccounts?");
					
					String approval = userIn.next();
					
					if (approval.toLowerCase().equals("yes") | approval.toLowerCase().equals("y")) {
												
						boolean doneApproving = false;
						
						while (doneApproving == false) {
							
							System.out.println("Select account to approve");
							
							String accountToApprove = userIn.next();
							
							if (User.pendingAccounts.containsKey(accountToApprove) == true) {
								
								User.customerLoginInfo.put(accountToApprove, User.pendingAccounts.get(accountToApprove));
								
								System.out.println(accountToApprove + " Approved!");
								System.out.println("Approve another account?");
								
								String goAgain = userIn.next();
								
								if (goAgain.toLowerCase().equals("y") | goAgain.toLowerCase().equals("yes")) {
									continue;
								}
								else {
									doneApproving = true;
								}
								
							}
						}
						
						
					}
					
					
				}
				
				else {
					System.out.println("Error");
				}
				
			}
			
			else if (selectionOne.equals("quit") | selectionOne.equals("q")) {
				
			}
			
			else {
				
			}
			
			
		}
	

}

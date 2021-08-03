package com.anthonyzhu.project0;

import java.util.*;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner userIn = new Scanner(System.in);
		
		boolean finished = false;
		
		while (finished == false) {
			System.out.println("Hello! Would you like to sign in?");
			
			String userSelection = userIn.next();
			
			if (userSelection.toLowerCase().equals("y") | userSelection.toLowerCase().equals("yes")) {
				System.out.println("Sign in as Employee or Customer?");
				
				String loginSelection = userIn.next();
				
				//Purely Here to have an existing account
				if (User.customerLoginInfo.containsKey("Hello") == false) {
					User.customerLoginInfo.put("Hello", "World");
					Customer.accountBalance.put("Hello", 5000.00);
				}
				
				if (loginSelection.toLowerCase().equals("employee") | loginSelection.toLowerCase().equals("e")) {
					
					System.out.println("Enter Username");
					
					String username = userIn.next();
					
					System.out.println("Enter Password");
					
					String password = userIn.next();
					
					Employee e = new Employee();
					
					if (User.employeeLoginInfo.containsKey("E") == false) {
						User.employeeLoginInfo.put("E", "Ex");
					}
					
					
					System.out.println("");
					
					String loginResult = e.login(username, password);
					
					if (loginResult.equals("No")) {
						System.out.println("Not logged in");
						
						continue;
					}
					
					else {
						System.out.println("Successfully logged in.");
						
						e.loggedInOperations(username);
					
					}
				}
				

				else if (loginSelection.toLowerCase().equals("customer") | loginSelection.toLowerCase().equals("c")) {
					
					System.out.println("Enter Username");
					
					String username = userIn.next();
					
					System.out.println("Enter Password");
					
					String password = userIn.next();
					
					Customer c = new Customer();
					
														
//					System.out.println(User.customerLoginInfo.get("Hello"));
					
					System.out.println(Customer.accountBalance.containsKey("Hello"));
					
					String loginResult = c.login(username, password);
					
					if (loginResult.equals("No")) {
						System.out.println("Not logged in");
						
						continue;
					}
					
					else {
						System.out.println("Successfully logged in.");
						System.out.println("");

						c.loggedInOperations(username);
					
					}
					
					
				}
				
				else {
					System.out.println("Invalid choice");
				}
				System.out.println("");
			}
			else {
				System.out.println("Ok, quitting");
				finished = true;
				userIn.close();
			}
			
			
		}
		
		

		

	}

}

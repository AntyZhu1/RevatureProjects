package com.anthonyzhu.project0;

import java.sql.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		
		boolean finished = false;
		
		while (!finished ) {
			System.out.println("--------------------------------------------");
			System.out.println("Welcome to Generic Bank! Would you like to Log In?"
					+ "\n"
					+ "(Y/N)"
					+ "\n"
					+ "--------------------------------------------");
			System.out.println("");
			
			Scanner userIn = new Scanner(System.in);
			
			String userSelection = userIn.next();
			
			EmployeeDAO empDAO = EmployeeDAOFactory.getEmployeeDao();
			
			List<Employee> employeeList = empDAO.getEmployees();
			
			CustomerDAO custDAO = CustomerDAOFactory.getCustomerDao();
			
			List<Customer> customerList = custDAO.getCustomers();
			
			List<Customer> pendingList = custDAO.getPending();
			
			
			if (userSelection.toLowerCase().equals("y") | userSelection.toLowerCase().equals("yes")) {
				System.out.println("--------------------------------------------"
						+ "\n"
						+ "Log in as Employee (E) or Customer (C)?"
						+ "\n"
						+ "--------------------------------------------");
					System.out.println("");

				
				String loginAs = userIn.next();
				
				if(loginAs.toLowerCase().equals("e") | loginAs.toLowerCase().equals("employee")) {
					
					System.out.println("--------------------------------------------"
							+ "\n"
							+ "Enter Username: "
							+ "\n"
							+ "--------------------------------------------");
					
					String employeeUsername = userIn.next();
					
					System.out.println("--------------------------------------------"
							+ "\n"
							+ "Enter Password: "
							+ "\n"
							+ "--------------------------------------------");
					
					String employeePassword = userIn.next();
					
					String loggedInAs = "none";
					
					for (Employee temp : employeeList) {
						
						if (temp.getUsername().equals(employeeUsername) & temp.getPassword().equals(employeePassword)) {
							boolean employeeFinished = false;
							
							while (!employeeFinished) {
								System.out.println("Logged in as " + employeeUsername);
								
								loggedInAs = employeeUsername;
								
								System.out.println("");
								
								System.out.println("--------------------------------------------"
										+ "\n"
										+ "What would you Like to do?"
										+ "\n"
										+ "1) Edit Customer Accounts "
										+ "2) Review Pending Customer Accounts "
										+ "3) View Recent Transactions "
										+ "4) Quit "
										+ "\n"
										+ "--------------------------------------------");
										System.out.println("");
										
								int employeeSelection = userIn.nextInt();
								
								switch (employeeSelection) {
								
								case 1: {
										System.out.println("Edit Customer Accounts Selected");
										System.out.println("--------------------------------------------"
										+ "\n"
										+ "Here is the list of Customers: ");

										for (Customer tempCustomer : customerList) {
											
											System.out.println("ID: " + tempCustomer.getId());
											System.out.println("First Name: " + tempCustomer.getFirstName() + " Last Name: " + tempCustomer.getLastName());
											System.out.println("Username: " + tempCustomer.getUsername());
											System.out.println("Balance: " + tempCustomer.getBalance());
											System.out.println("");
										}
										System.out.println("--------------------------------------------");
										
										System.out.println("--------------------------------------------"
												+ "\n"
												+ "What would you like to do?"
												+ "\n"
												+ ""
												+ "1) Edit Information "
												+ "2) Delete Customer "
												+ "3) Quit "
												+ "\n"
												+ "--------------------------------------------");
										
										int interactionSelection = userIn.nextInt();
										
										switch (interactionSelection) {
										
										case 1:
											System.out.println("Edit Information Selected");
											System.out.println("--------------------------------------------"
													+ "Which customer's information would you like to edit?"
													+ "\n"
													+ "--------------------------------------------"
													+ "\n"
													+ "Enter Selected ID:");
											
											int editID = userIn.nextInt();
											
											for (Customer tempCustomer : customerList) {
												
												if (editID == tempCustomer.getId()) {
													System.out.println("--------------------------------------------"
															+ "\n"
															+ "Editing information of" + tempCustomer.getFirstName()
															+ "\n"
															+ "\n"
															+ "Enter Updated Information: "
															+ "\n"
															+ "--------------------------------------------");
													
													System.out.println("--------------------------------------------");
													System.out.println("New username: ");
													String newUsername = userIn.next();													
													System.out.println("New Password: ");
													String newPassword = userIn.next();
													System.out.println("New First Name: ");
													String newFirstName = userIn.next();
													System.out.println("New Last Name: ");
													String newLastName = userIn.next();
													System.out.println("New Balance: ");
													double newBalance = userIn.nextDouble();
													
													Customer updater = new Customer();
													updater.setUsername(newUsername);
													updater.setPassword(newPassword);
													updater.setFirstName(newFirstName);
													updater.setLastName(newLastName);
													updater.setBalance(newBalance);
													
													custDAO.updateCustomer(tempCustomer, updater);
													
												}
												
											}
											
											continue;
											
										case 2:
											System.out.println("Delete Customer Selected");
											System.out.println("--------------------------------------------"
													+ "Which customer would you like to delete?"
													+ "\n"
													+ "--------------------------------------------"
													+ "\n"
													+ "Enter Selected ID:");
												int delID = userIn.nextInt();
											
											for (Customer tempCustomer : customerList) {
												
												if (delID == tempCustomer.getId()) {
													System.out.println("--------------------------------------------"
															+ "\n"
															+ "Deleting account: "
															+ tempCustomer.getUsername()
															+ "\n"
															+ "--------------------------------------------");
																								
													custDAO.deleteCustomer(delID);
													
												}
												
											}
											continue;
											
										case 3:
											System.out.println("Quitting");
											
											employeeFinished = true;
											break;
										
										default: 
											System.out.println("Invalid Option");
										
										}
										break;										
									}
								
								case 2:	{
										System.out.println("Review Pending Customer Accounts Selected");
										
										System.out.println("--------------------------------------------"
										+ "\n"
										+ "Here is the list of Pending accounts: ");
										

										for (Customer tempCustomer : pendingList) {
											
											System.out.println("ID: " + tempCustomer.getId());
											System.out.println("First Name: " + tempCustomer.getFirstName() + " Last Name: " + tempCustomer.getLastName());
											System.out.println("Username: " + tempCustomer.getUsername());
											System.out.println("Balance: " + tempCustomer.getBalance());
											System.out.println("");
											
											
											
										}
										System.out.println("--------------------------------------------");
										System.out.println("--------------------------------------------"
												+ "\n"
												+ "What would you like to do?"
												+ "\n"
												+ ""
												+ "1) Approve an Account "
												+ "2) Approve All Accounts "
												+ "3) Deny an Account "
												+ "4) Deny all Accounts "
												+ "5) Quit "
												+ "\n"
												+ "--------------------------------------------");
										
										int interactionSelection = userIn.nextInt();
										
										switch (interactionSelection) {
										
										case 1:
											System.out.println("Approve an Account Selected");
											System.out.println("--------------------------------------------"
													+ "\n"
													+ "Which customer would you like to approve?"
													+ "\n"
													+ "--------------------------------------------"
													+ "\n"
													+ "Enter Selected ID:");
											
											int approveID = userIn.nextInt();
											for (Customer tempCustomer : pendingList) {
												
												if (approveID == tempCustomer.getId()) {
													System.out.println("--------------------------------------------"
															+ "\n"
															+ "Approving account: " + tempCustomer.getUsername()
															+ "\n"
															+ "--------------------------------------------");
													custDAO.addCustomer(tempCustomer);
													custDAO.deletePending(approveID);
												}
												else {
													System.out.println("ID not found. Please retry.");
												}
												System.out.println("Process complete, please restart application to update.");

											}
											continue;
											
										case 2:
											System.out.println("Approve All Accounts Selected");
											
											for (Customer tempCustomer : pendingList) {
												custDAO.addCustomer(tempCustomer);
												custDAO.deletePending(tempCustomer.getId());
											}
											
											System.out.println("Process complete, please restart application to update.");
											continue;
										
										case 3:
											System.out.println("Deny an Account Selected");
											System.out.println("--------------------------------------------"
													+ "\n"
													+ "Which account would you like to deny?"
													+ "\n"
													+ "--------------------------------------------"
													+ "\n"
													+ "Enter Selected ID:");
											
											int denyID = userIn.nextInt();
											for (Customer tempCustomer : pendingList) {
												
												if (denyID == tempCustomer.getId()) {
													System.out.println("--------------------------------------------"
															+ "\n"
															+ "Denying account: " + tempCustomer.getUsername()
															+ "\n"
															+ "--------------------------------------------");
													
													custDAO.deletePending(denyID);
													System.out.println("\n"
															+ "Process complete, please restart application to update.");

												}
												else {
													System.out.println("ID not found. Please retry.");
												}
											}
											continue;	
											
										case 4:
											System.out.println("Deny All accounts selected");
											for (Customer tempCustomer : pendingList) {
												custDAO.deletePending(tempCustomer.getId());
											}
											System.out.println("Process complete, please restart application to update.");

											continue;
											
										case 5:
											System.out.println("Quitting");
											
											break;
										
										default: 
											System.out.println("Invalid Option");
										
										}
										break;
									}
								case 3: {
									System.out.println("View Transactions Selected");
									empDAO.viewTransactions();
									break;
								}
								case 4: {
										System.out.println("Quit Selected");
										employeeFinished = true;
										break;
									}
								
								default: {
									
									System.out.println("Invalid Option");
									
									}
									
								}
															
							}
							
						}
						
					}
					
				if (loggedInAs.equals("none")) {
						
						System.out.println("Error, employee account not found, please try to log in again or contact Administrators.");
						
					}
									
				}
				
				else if (loginAs.toLowerCase().equals("c") | loginAs.toLowerCase().equals("customer")) {
					
					boolean custFinished = false;

						System.out.println("--------------------------------------------"
								+ "\n"
								+ "Enter Username: "
								+ "\n"
								+ "--------------------------------------------");
						
						String custUsername = userIn.next();
						
						System.out.println("--------------------------------------------"
								+ "\n"
								+ "Enter Password: "
								+ "\n"
								+ "--------------------------------------------");
						
						String custPassword = userIn.next();
						
						String custLoggedInAs = "not";
						
						Customer loggedInCustomer = new Customer();

						while(!custFinished) {
							
						for (Customer tempCust : customerList) {

							if (tempCust.getUsername().equals(custUsername) & tempCust.getPassword().equals(custPassword)) {
								
								custLoggedInAs = custUsername;
								
								loggedInCustomer.setId(tempCust.getId());
								loggedInCustomer.setFirstName(tempCust.getFirstName());
								loggedInCustomer.setLastName(tempCust.getLastName());
								loggedInCustomer.setUsername(tempCust.getUsername());
								loggedInCustomer.setPassword(tempCust.getPassword());
								loggedInCustomer.setBalance(tempCust.getBalance());
								
								System.out.println("Logged in as: " + custUsername);
								
								System.out.println("--------------------------------------------"
										+ "\n"
										+ "Your Current account balance is: $"
										+ custDAO.getCurrentBalance(loggedInCustomer.getId())
										+ "\n"
										+ "What would you Like to do?"
										+ "\n"
										+ "1) Withdraw "
										+ "2) Deposit "
										+ "3) Send money "
										+ "4) Recieve money "
										+ "5) Quit"
										+ "\n"
										+ "--------------------------------------------");
										System.out.println("");
										
								int employeeSelection = userIn.nextInt();
								
								switch (employeeSelection) {
								
								case 1: {
										System.out.println("Withdraw Money Selected");
										System.out.println("--------------------------------------------"
												+ "\n"
												+ "Enter Withdrawl Amount: "
												+ "\n"
												+ "--------------------------------------------");
										
										double withdrawAmount = userIn.nextDouble();
										
										System.out.println("Trying to withdraw: $" + withdrawAmount + "..." );
										
										System.out.println("");
										
										custDAO.withdraw(loggedInCustomer.getId(), withdrawAmount);
										
										break;
									}
								
								case 2:	{
										System.out.println("Deposit Money Selected");
										
										System.out.println("--------------------------------------------"
												+ "\n"
												+ "Enter Deposit Amount: "
												+ "\n"
												+ "--------------------------------------------");
										
										double depositAmount = userIn.nextDouble();
										System.out.println("Attempting to deposit: $" + depositAmount);
										
										custDAO.deposit(loggedInCustomer.getId(), depositAmount);
										
										break;
									}
								
								case 3: {
										System.out.println("Send Money to Another Account selected");
										
										System.out.println("--------------------------------------------"
												+ "\n"
												+ "Enter Amount to Send: ");
												
												double sendAmount = userIn.nextDouble();
																						
												System.out.println("Enter Reciever ID");
												
												int sendTo = userIn.nextInt();
												
												System.out.println("--------------------------------------------");
												
												custDAO.sendFunds(sendAmount, loggedInCustomer.getId(), sendTo);
										break;
									}
								
								case 4: {
									System.out.println("Recieve Money from Another Account selected");
									
									System.out.println("Your Current pending fund transfers: ");
									
									int i = custDAO.showSentFunds(loggedInCustomer.getId());
									if (i > 0) {
										System.out.println("--------------------------------------------"
												+ "\n"
												+ "Enter Recieve ID"
												+ "\n"
												+ "--------------------------------------------");

										int receiveFrom = userIn.nextInt();

										custDAO.receiveFunds(receiveFrom);

										break;
									}
									else {
										System.out.println("--------------------------------------------" +
												"\n" +
												"No Pending Transactions" +
												"\n" +
												"--------------------------------------------");
										break;
									}
								}
								
								case 5: {
										System.out.println("Quit Selected");
										custFinished = true;
										break;
									}
								}
							}
							
							
												
						}
						
						if (custLoggedInAs.equals("not")) {
							
							System.out.println("It seems that you aren't in our customer database right now. Would you like to make an account? (Y/N)");
							
							String makeNewAccount = userIn.next().toLowerCase();
							
							if (makeNewAccount.equals("y") | makeNewAccount.equals("yes")) {
								
								System.out.println("Enter First Name: ");
								
								String newFirstName = userIn.next();
								
								System.out.println("Enter Last Name: ");
								
								String newLastName = userIn.next();
								
								System.out.println("Enter first deposit (minimum 5.00)");
								
								System.out.print("$");
								
								double newAccountDeposit = userIn.nextDouble();
								
								Customer newPending = new Customer();
								
								newPending.setFirstName(newFirstName);
								newPending.setLastName(newLastName);
								newPending.setUsername(custUsername);
								newPending.setPassword(custPassword);
								newPending.setBalance(newAccountDeposit);
								
								custDAO.addPending(newPending);			
								
								custFinished = true;
							}
						}
					}
				}
				else {
					System.out.println("Invalid selection");
					System.out.println("");
				}
			}
			
			
			else if (userSelection.toLowerCase().equals("n") | userSelection.toLowerCase().equals("no")) {
				System.out.println("Ok, Have a nice day!");
				
				finished = true;
			}
			
			else {
				System.out.println("Invalid selection");
				System.out.println("");
			}

		}
				
//		Writing Data
//		Employee e = new Employee();
//		
//		e.setId(1);
//		e.setFirstName("John");
//		e.setLastName("Doe");
//		e.setUsername("john");
//		e.setPassword("doe");
//
//		//Employee Dao
//		EmployeeDAO empDAO = EmployeeDAOFactory.getEmployeeDao();
//		empDAO.addEmployee(e);
	}

}

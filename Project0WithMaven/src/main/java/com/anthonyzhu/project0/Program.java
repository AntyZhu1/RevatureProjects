package com.anthonyzhu.project0;

import org.apache.log4j.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Program {
    private static final Logger logger = LogManager.getLogger(Program.class);

    public void runProgram() throws SQLException {
        //        ConsoleAppender consoleAppender = new ConsoleAppender();
        //        consoleAppender.setThreshold(Level.INFO);
        //        consoleAppender.setLayout(new PatternLayout("How does this look? "));
        //        consoleAppender.activateOptions();
        //        LogManager.getRootLogger().addAppender(consoleAppender);
        //
        //        logger.debug("Hello this is a debug message");
        //        logger.info("Hello this is a info message");

        FileAppender f = new FileAppender();
        f.setName("Interaction Log");
        f.setFile("interaction.log");
        f.setThreshold(Level.INFO);


        f.setLayout(new PatternLayout("%d{DATE} | Session Started " + "\n"));
        f.setAppend(true);
        f.activateOptions();
        LogManager.getRootLogger().addAppender(f);
        logger.info("%d{DATE} | Session Started "+ "\n");
        System.out.println("--------------------------------------------");

        boolean finished = false;

        while (!finished) {
            f.setLayout(new PatternLayout("%d{DATE} | Login Prompted " + "\n"));
            f.setAppend(true);
            f.activateOptions();
            LogManager.getRootLogger().addAppender(f);
            logger.info("%d{DATE} | Login Prompted  "+ "\n");
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

                if (loginAs.toLowerCase().equals("e") | loginAs.toLowerCase().equals("employee")) {

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

                            f.setLayout(new PatternLayout("%d{DATE} | Logged in as Employee: " + temp.getUsername() + "\n"));
                            f.setAppend(true);
                            f.activateOptions();
                            LogManager.getRootLogger().addAppender(f);
                            logger.info("%d{DATE} | Logged in as Employee: "+ temp.getUsername() + "\n");


                            while (!employeeFinished) {
                                System.out.println("Logged in as " + employeeUsername);

                                loggedInAs = employeeUsername;

                                System.out.println("");

                                System.out.println("--------------------------------------------"
                                        + "\n"
                                        + "What would you Like to do?"
                                        + "\n"
                                        + "| 1) Edit Customer Accounts "
                                        + "| 2) Review Pending Customer Accounts "
                                        + "| 3) View Recent Transactions "
                                        + "| 4) Quit |"
                                        + "\n"
                                        + "--------------------------------------------");
                                System.out.println("");

                                int employeeSelection = userIn.nextInt();

                                switch (employeeSelection) {

                                    case 1: {
                                        f.setLayout(new PatternLayout("%d{DATE} | Edit Customer Accounts Selected" + "\n"));
                                        f.setAppend(true);
                                        f.activateOptions();
                                        LogManager.getRootLogger().addAppender(f);
                                        logger.info("%d{DATE} | Edit Customer Accounts Selected" + "\n");

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
                                                + "| 1) Edit Information | "
                                                + "2) Delete Customer | "
                                                + "3) Quit |"
                                                + "\n"
                                                + "--------------------------------------------");

                                        int interactionSelection = userIn.nextInt();

                                        switch (interactionSelection) {

                                            case 1:
                                                f.setLayout(new PatternLayout("%d{DATE} | Edit Information Selected" + "\n"));
                                                f.setAppend(true);
                                                f.activateOptions();
                                                LogManager.getRootLogger().addAppender(f);
                                                logger.info("%d{DATE} | Edit Information Selected" + "\n");

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
//
                                                f.setLayout(new PatternLayout("%d{DATE} | Delete Customer Selected" + "\n"));
                                                f.setAppend(true);
                                                f.activateOptions();
                                                LogManager.getRootLogger().addAppender(f);
                                                logger.info("%d{DATE} | Delete Customer Selected" + "\n");

                                                System.out.println("Delete Customer Selected");
                                                System.out.println("--------------------------------------------" +
                                                        "\n"
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

                                                f.setLayout(new PatternLayout("%d{DATE} | Quitting Selected" + "\n"));
                                                f.setAppend(true);
                                                f.activateOptions();
                                                LogManager.getRootLogger().addAppender(f);
                                                logger.info("%d{DATE} | Quitting Selected" + "\n");
//                                                System.out.println("Quitting");

//                                                employeeFinished = true;
                                                break;

                                            default:
                                                System.out.println("Invalid Option");

                                        }
                                        break;
                                    }

                                    case 2: {

                                        f.setLayout(new PatternLayout("%d{DATE} | Review Pending Customer Accounts Selected" + "\n"));
                                        f.setAppend(true);
                                        f.activateOptions();
                                        LogManager.getRootLogger().addAppender(f);
                                        logger.info("%d{DATE} | Review Pending Customer Accounts Selected" + "\n");

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
                                                + "1) | Approve an Account |"
                                                + "2) Approve All Accounts |"
                                                + "3) Deny an Account |"
                                                + "4) Deny all Accounts |"
                                                + "5) Quit | "
                                                + "\n"
                                                + "--------------------------------------------");

                                        int interactionSelection = userIn.nextInt();

                                        switch (interactionSelection) {

                                            case 1:

                                                f.setLayout(new PatternLayout("%d{DATE} | Approve an Account Selected" + "\n"));
                                                f.setAppend(true);
                                                f.activateOptions();
                                                LogManager.getRootLogger().addAppender(f);
                                                logger.info("%d{DATE} | Approve an Account Selected" + "\n");

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
                                                    } else {
                                                        System.out.println("ID not found. Please retry.");
                                                    }
                                                    System.out.println("Process complete, please restart application to update.");

                                                }
                                                continue;

                                            case 2:

                                                f.setLayout(new PatternLayout("%d{DATE} | Approve All Accounts Selected" + "\n"));
                                                f.setAppend(true);
                                                f.activateOptions();
                                                LogManager.getRootLogger().addAppender(f);
                                                logger.info("%d{DATE} | Approve All Accounts Selected" + "\n");

                                                System.out.println("Approve All Accounts Selected");

                                                for (Customer tempCustomer : pendingList) {
                                                    custDAO.addCustomer(tempCustomer);
                                                    custDAO.deleteCustomer(tempCustomer.getId());
                                                }

                                                System.out.println("Process complete, please restart application to update.");
                                                continue;

                                            case 3:

                                                f.setLayout(new PatternLayout("%d{DATE} | Deny an Account Selected" + "\n"));
                                                f.setAppend(true);
                                                f.activateOptions();
                                                LogManager.getRootLogger().addAppender(f);
                                                logger.info("%d{DATE} | Deny an Account Selected" + "\n");

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

                                                    } else {
                                                        System.out.println("ID not found. Please retry.");
                                                    }
                                                }
                                                continue;

                                            case 4:

                                                f.setLayout(new PatternLayout("%d{DATE} | Deny All accounts selected" + "\n"));
                                                f.setAppend(true);
                                                f.activateOptions();
                                                LogManager.getRootLogger().addAppender(f);
                                                logger.info("%d{DATE} | Deny All accounts selected" + "\n");

                                                System.out.println("Deny All accounts selected");
                                                for (Customer tempCustomer : pendingList) {
                                                    custDAO.deletePending(tempCustomer.getId());
                                                }
                                                System.out.println("Process complete, please restart application to update.");

                                                continue;

                                            case 5:

                                                f.setLayout(new PatternLayout("%d{DATE} | Quitting selected" + "\n"));
                                                f.setAppend(true);
                                                f.activateOptions();
                                                LogManager.getRootLogger().addAppender(f);
                                                logger.info("%d{DATE} | Quitting selected" + "\n");

                                                System.out.println("Quitting");

                                                break;

                                            default:
                                                System.out.println("Invalid Option");

                                        }
                                        break;
                                    }
                                    case 3: {

                                        f.setLayout(new PatternLayout("%d{DATE} | View Transactions Selected" + "\n"));
                                        f.setAppend(true);
                                        f.activateOptions();
                                        LogManager.getRootLogger().addAppender(f);
                                        logger.info("%d{DATE} | View Transactions Selected" + "\n");

                                        System.out.println("View Transactions Selected");
                                        empDAO.viewTransactions();
                                        break;
                                    }
                                    case 4: {

                                        f.setLayout(new PatternLayout("%d{DATE} | Quit Selected" + "\n"));
                                        f.setAppend(true);
                                        f.activateOptions();
                                        LogManager.getRootLogger().addAppender(f);
                                        logger.info("%d{DATE} | Quit Selected" + "\n");

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
                        f.setLayout(new PatternLayout("%d{DATE} | Employee Login Failed " + "\n"));
                        f.setAppend(true);
                        f.activateOptions();
                        LogManager.getRootLogger().addAppender(f);
                        logger.info("%d{DATE} | Employee Login Failed "+ "\n");

                    }

                } else if (loginAs.toLowerCase().equals("c") | loginAs.toLowerCase().equals("customer")) {

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

                    while (!custFinished) {

                        for (Customer tempCust : customerList) {

                            if (tempCust.getUsername().equals(custUsername) & tempCust.getPassword().equals(custPassword)) {

                                f.setLayout(new PatternLayout("%d{DATE} | Logged in as Customer: " + tempCust.getUsername() + "\n"));
                                f.setAppend(true);
                                f.activateOptions();
                                LogManager.getRootLogger().addAppender(f);
                                logger.info("%d{DATE} | Logged in as Customer: " + tempCust.getUsername() + "\n");

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
                                        + "| 1) Withdraw "
                                        + "| 2) Deposit "
                                        + "| 3) Send money "
                                        + "| 4) Recieve money "
                                        + "| 5) Quit |"
                                        + "\n"
                                        + "--------------------------------------------");
                                System.out.println("");

                                int employeeSelection = userIn.nextInt();

                                switch (employeeSelection) {

                                    case 1: {

                                        f.setLayout(new PatternLayout("%d{DATE} | Withdraw Money Selected" + "\n"));
                                        f.setAppend(true);
                                        f.activateOptions();
                                        LogManager.getRootLogger().addAppender(f);
                                        logger.info("%d{DATE} | Withdraw Money Selected" + "\n");

                                        System.out.println("Withdraw Money Selected");
                                        System.out.println("--------------------------------------------"
                                                + "\n"
                                                + "Enter Withdrawl Amount: "
                                                + "\n"
                                                + "--------------------------------------------");

                                        double withdrawAmount = userIn.nextDouble();

                                        System.out.println("Trying to withdraw: $" + withdrawAmount + "...");

                                        System.out.println("");

                                        custDAO.withdraw(loggedInCustomer.getId(), withdrawAmount);

                                        f.setLayout(new PatternLayout("%d{DATE} | Withdrew $" + withdrawAmount + "\n"));
                                        f.setAppend(true);
                                        f.activateOptions();
                                        LogManager.getRootLogger().addAppender(f);
                                        logger.info("%d{DATE} | Withdrew $" + withdrawAmount + "\n");

                                        break;
                                    }

                                    case 2: {

                                        f.setLayout(new PatternLayout("%d{DATE} | Deposit Money Selected" + "\n"));
                                        f.setAppend(true);
                                        f.activateOptions();
                                        LogManager.getRootLogger().addAppender(f);
                                        logger.info("%d{DATE} | Deposit Money Selected" + "\n");

                                        System.out.println("Deposit Money Selected");

                                        System.out.println("--------------------------------------------"
                                                + "\n"
                                                + "Enter Deposit Amount: "
                                                + "\n"
                                                + "--------------------------------------------");

                                        double depositAmount = userIn.nextDouble();
                                        System.out.println("Attempting to deposit: $" + depositAmount);

                                        custDAO.deposit(loggedInCustomer.getId(), depositAmount);

                                        f.setLayout(new PatternLayout("%d{DATE} | Deposited $" + depositAmount + "\n"));
                                        f.setAppend(true);
                                        f.activateOptions();
                                        LogManager.getRootLogger().addAppender(f);
                                        logger.info("%d{DATE} | Deposited $" + depositAmount + "\n");

                                        break;
                                    }

                                    case 3: {

                                        f.setLayout(new PatternLayout("%d{DATE} | Send Money Selected" + "\n"));
                                        f.setAppend(true);
                                        f.activateOptions();
                                        LogManager.getRootLogger().addAppender(f);
                                        logger.info("%d{DATE} | Send Money Selected" + "\n");

                                        System.out.println("Send Money to Another Account selected");

                                        System.out.println("--------------------------------------------"
                                                + "\n"
                                                + "Enter Amount to Send: ");

                                        double sendAmount = userIn.nextDouble();

                                        System.out.println("Enter Reciever ID");

                                        int sendTo = userIn.nextInt();

                                        System.out.println("--------------------------------------------");

                                        try {
                                            custDAO.sendFunds(sendAmount, loggedInCustomer.getId(), sendTo);

                                            f.setLayout(new PatternLayout("%d{DATE} | Sent $" + sendAmount + " to account with ID:  " + sendTo + "\n"));
                                            f.setAppend(true);
                                            f.activateOptions();
                                            LogManager.getRootLogger().addAppender(f);
                                            logger.info("%d{DATE} | Sent $" + sendTo + " to account with ID:  " + sendTo + "\n");
                                        }
                                        catch (Exception e) {
                                            System.out.println("Error, invalid ID.");
                                        }


                                        break;
                                    }

                                    case 4: {

                                        f.setLayout(new PatternLayout("%d{DATE} | Receive Money Selected" + "\n"));
                                        f.setAppend(true);
                                        f.activateOptions();
                                        LogManager.getRootLogger().addAppender(f);
                                        logger.info("%d{DATE} | Receive Money Selected" + "\n");

                                        System.out.println("Receive Money from Another Account selected");

                                        System.out.println("Your Current pending fund transfers: ");

                                        int i = custDAO.showSentFunds(loggedInCustomer.getId());

                                        if (i != 0) {

                                            System.out.println("--------------------------------------------"
                                                    + "\n"
                                                    + "Enter Receive ID" +
                                                    "\n"
                                                    + "--------------------------------------------");

                                            int receiveFrom = userIn.nextInt();
                                            try {
                                                double received = custDAO.receiveFunds(receiveFrom);

                                                f.setLayout(new PatternLayout("%d{DATE} | Received $" + received + "\n"));
                                                f.setAppend(true);
                                                f.activateOptions();
                                                LogManager.getRootLogger().addAppender(f);
                                                logger.info("%d{DATE} | Received $" + received + "\n");
                                            }
                                            catch (Exception e) {
                                                System.out.println("Error, invalid transfer ID.");
                                            }


                                        }
                                        else {
                                            System.out.println("--------------------------------------------"
                                                    + "\n"
                                                    + "No Pending Transactions" +
                                                    "\n"
                                                    + "--------------------------------------------");
                                        }

                                        break;
                                    }

                                    case 5: {

                                        f.setLayout(new PatternLayout("%d{DATE} | Quit Selected" + "\n"));
                                        f.setAppend(true);
                                        f.activateOptions();
                                        LogManager.getRootLogger().addAppender(f);
                                        logger.info("%d{DATE} | Quit Selected" + "\n");

                                        System.out.println("Quit Selected");
                                        custFinished = true;
                                        break;
                                    }
                                }
                            }


                        }

                        if (custLoggedInAs.equals("not") & custDAO.findPendingCustomer(custUsername) == false  & custDAO.findCustomer(custUsername) == false) {

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

                                try {
                                    custDAO.addPending(newPending);

                                    f.setLayout(new PatternLayout("%d{DATE} | New pending account created" + "\n"));
                                    f.setAppend(true);
                                    f.activateOptions();
                                    LogManager.getRootLogger().addAppender(f);
                                    logger.info("%d{DATE} | New pending account created" + "\n");

                                    custFinished = true;
                                }
                                catch (Exception e) {
                                    System.out.println("There was an error creating your account. Please try again.");
                                }


                            }
                            else if (makeNewAccount.equals("n") | makeNewAccount.equals("no")){
                                custFinished = true;
                            }
                            else {
                                System.out.println("Invalid entry. please enter yes (y) or, no (n)");
                            }
                        }
                        else if (custDAO.findPendingCustomer(custUsername) == true) {
                            System.out.println("Your account is currently in review, please check back later.");
                            custFinished = true;
                        }
//                        else if (custDAO.findCustomer(custUsername)) {
//                            System.out.println("Error, account already exists. You shouldn't be able to see this error.");
//                            custFinished = true;
//                        }
                    }

                }
                else {
                    System.out.println("Invalid selection");
                    System.out.println("");
                }
            } else if (userSelection.toLowerCase().equals("n") | userSelection.toLowerCase().equals("no")) {
                System.out.println("Ok, Have a nice day!");

                f.setLayout(new PatternLayout("%d{DATE} | Session Ended" + "\n" + "\n"));
                f.setAppend(true);
                f.activateOptions();
                LogManager.getRootLogger().addAppender(f);
                logger.info("%d{DATE} | Session Ended" + "\n" + "\n");

                finished = true;
            } else {
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

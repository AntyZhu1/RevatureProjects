package com.anthonyzhu.project0;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    private static Statement statement = null;
    Connection con = null;

    public CustomerDAOImpl() {
        try {

            this.con = ConnectionFactory.getConnection();

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }
    }

    @Override
    public void addCustomer(Customer customer) throws SQLException {

        String request = "INSERT INTO customers (cust_username, cust_password, cust_firstname, cust_lastname, cust_money) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(request);

        ps.setString(1, customer.getUsername());
        ps.setString(2, customer.getPassword());
        ps.setString(3, customer.getFirstName());
        ps.setString(4, customer.getLastName());
        ps.setDouble(5, customer.getBalance());

        int i = ps.executeUpdate();
        if (i > 0) {
            System.out.println("Customer " + customer.getUsername() + " Added.");
        } else {
            System.out.println("An error has occurred, please check over process.");
        }

    }

    public void addPending(Customer customer) throws SQLException {
        String request = "INSERT INTO pending (pending_username, pending_password, pending_firstname, pending_lastname, pending_money) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(request);

        ps.setString(1, customer.getUsername());
        ps.setString(2, customer.getPassword());
        ps.setString(3, customer.getFirstName());
        ps.setString(4, customer.getLastName());
        ps.setDouble(5, customer.getBalance());

        int i = ps.executeUpdate();
        if (i > 0) {
            System.out.println("Customer " + customer.getUsername() + " Added to pending.");
        } else {
            System.out.println("An error has occurred, please check over process.");
        }
    }

    @Override
    public void updateCustomer(Customer customer, Customer updater) throws SQLException {
        String request = "UPDATE customers SET cust_username = ?, cust_password = ?, cust_firstname = ?, cust_lastname = ?, cust_money = ? WHERE cust_id = ?";

        PreparedStatement ps = con.prepareStatement(request);


        ps.setString(1, updater.getUsername());
        ps.setString(2, updater.getPassword());
        ps.setString(3, updater.getFirstName());
        ps.setString(4, updater.getLastName());
        ps.setDouble(5, updater.getBalance());
        ps.setInt(6, customer.getId());

        int i = ps.executeUpdate();

        if (i > 0) {
            System.out.println("Customer " + customer.getUsername() + " Has been updated.");
        } else {
            System.out.println("An error has occurred, please check over process.");
        }

    }

    @Override
    public void deleteCustomer(int id) throws SQLException {

        String request = "DELETE FROM customers where id = ?";

        PreparedStatement ps = con.prepareStatement(request);

        ps.setInt(1, id);

        int i = ps.executeUpdate();

        if (i > 0) {
            System.out.println("Deleted customer with id " + id);
        } else {
            System.out.println("An error has occurred, please check over process.");
        }

    }

    @Override
    public List<Customer> getCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();

        String request = "SELECT * FROM customers";

        PreparedStatement ps = con.prepareStatement(request);

        ResultSet output = ps.executeQuery();

        while (output.next()) {
            Customer tempCustomer = new Customer(output.getInt(1), output.getString(2), output.getString(3), output.getString(4), output.getString(5), output.getDouble(6));
            customers.add(tempCustomer);
        }

        return customers;

    }

    @Override
    public Customer CustomerById(int id) throws SQLException {

        String request = "SELECT * FROM customers WHERE emp_id = ?";

        PreparedStatement ps = con.prepareStatement(request);

        ps.setInt(1, id);

        ResultSet output = ps.executeQuery();

        Customer foundCustomer = new Customer(output.getInt(1), output.getString(2), output.getString(3), output.getString(4), output.getString(5), output.getDouble(6));

        return foundCustomer;

    }

    @Override
    public List<Customer> getPending() throws SQLException {
        List<Customer> pending = new ArrayList<>();

        String request = "SELECT * FROM pending";

        PreparedStatement ps = con.prepareStatement(request);

        ResultSet output = ps.executeQuery();

        while (output.next()) {
            Customer tempCustomer = new Customer(output.getInt(1), output.getString(2), output.getString(3), output.getString(4), output.getString(5), output.getDouble(6));
            pending.add(tempCustomer);
        }

        return pending;

    }

    @Override
    public void deletePending(int id) throws SQLException {
        String request = "DELETE FROM pending where pending_id = ?";

        PreparedStatement ps = con.prepareStatement(request);

        ps.setInt(1, id);

        int i = ps.executeUpdate();

        if (i > 0) {
            System.out.println("Deleted pending customer with id " + id);
        } else {
            System.out.println("An error has occurred, please check over process.");
        }

    }

    @Override
    public void withdraw(int userID, double withdrawAmount) throws SQLException {
        String request1 = "UPDATE customers SET cust_money = ? where cust_id = ?";

        String request2 = "SELECT cust_money FROM customers WHERE cust_id = ?";

        PreparedStatement ps1 = con.prepareStatement(request1);

        PreparedStatement ps2 = con.prepareStatement(request2);

        ps2.setInt(1, userID);

        ResultSet balance_result = ps2.executeQuery();

        balance_result.next();

        double currentBalance = balance_result.getDouble(1);

        if (withdrawAmount <= currentBalance & withdrawAmount > 0) {
            ps1.setDouble(1, currentBalance - withdrawAmount);

            ps1.setInt(2, userID);


            int i = ps1.executeUpdate();

            if (i > 0) {
                System.out.println("Successfully Withdrew  $" + withdrawAmount);

                ResultSet new_result = ps2.executeQuery();

                new_result.next();

                System.out.println("Your new balance is " + new_result.getDouble(1));
            } else {
                System.out.println("An error has occurred, please check over process.");
            }
        } else if (withdrawAmount > currentBalance) {
            System.out.println("Error, you cannot withdraw more money than in your account.");
        } else {
            System.out.println("Error, invalid withdraw amount.");
        }


    }

    @Override
    public void deposit(int userID, double depositAmount) throws SQLException {
        String request1 = "UPDATE customers SET cust_money = ? where cust_id = ?";

        String request2 = "SELECT cust_money FROM customers WHERE cust_id = ?";

        PreparedStatement ps1 = con.prepareStatement(request1);

        PreparedStatement ps2 = con.prepareStatement(request2);

        ps2.setInt(1, userID);

        ResultSet balance_result = ps2.executeQuery();

        balance_result.next();

//		System.out.println(balance_result.getDouble(6));

        double currentBalance = balance_result.getDouble(1);

//		System.out.println(currentBalance);

        ps1.setDouble(1, currentBalance + depositAmount);

        ps1.setInt(2, userID);

        if (depositAmount > 0) {
            int i = ps1.executeUpdate();

            if (i > 0) {
                System.out.println("Successfully Deposited  $" + depositAmount);

                ResultSet new_result = ps2.executeQuery();

                new_result.next();

                System.out.println("Your new balance is " + new_result.getDouble(1));

            } else {
                System.out.println("An error has occurred, please check over process.");
            }
        } else {
            System.out.println("Error, invalid deposit amount.");
        }

    }

    @Override
    public void sendFunds(double amount, int sendFromID, int sendToID) throws SQLException {
        String request = "INSERT INTO transfer_funds (transfer_to_id, transfer_money) VALUES (?, ?)";

        PreparedStatement ps = con.prepareStatement(request);

        ps.setInt(1, sendToID);
        ps.setDouble(2, amount);

        String check_if_acount_exists = "SELECT * FROM customers WHERE cust_id = ?";

        PreparedStatement existsPS = con.prepareStatement(check_if_acount_exists);

        existsPS.setInt(1,sendToID);

        ResultSet accountsExist = existsPS.executeQuery();

        if (amount > 0 & accountsExist.next()) {
            int result = ps.executeUpdate();


            String request2 = "SELECT cust_money FROM customers WHERE cust_id = ?";

            PreparedStatement getBalance = con.prepareStatement(request2);

            getBalance.setInt(1, sendFromID);

            String request3 = "UPDATE customers SET cust_money = ? where cust_id = ?";

            PreparedStatement withdrawlStatement = con.prepareStatement(request3);

            ResultSet balance_result = getBalance.executeQuery();

            balance_result.next();

            double currentBalance = balance_result.getDouble(1);

            if (amount <= currentBalance & amount > 0) {

                withdrawlStatement.setDouble(1, currentBalance - amount);

                withdrawlStatement.setInt(2, sendFromID);

                int i = withdrawlStatement.executeUpdate();

                if (result > 0) {
                    System.out.println("Sent $" + amount + ".");
                } else {
                    System.out.println("An error has occurred, please check over process.");
                }
            } else if (amount > currentBalance) {
                System.out.println("Error, you cannot send more money than you have in your account.");
            } else {
                System.out.println("Error, invalid amount.");
            }
        }
        else {
            System.out.println("Error, invalid information. Check if account ID exists or if Money Sent is a positive amount.");
        }

    }

    @Override
    public double receiveFunds(int transactionID) throws SQLException {
        String request = "SELECT * FROM transfer_funds WHERE transfer_id = ?";

        PreparedStatement ps1 = con.prepareStatement(request);

        ps1.setInt(1, transactionID);

        ResultSet transfer = ps1.executeQuery();

        transfer.next();

        double transfer_amount = transfer.getDouble(3);

        String request2 = "UPDATE customers SET cust_money = ? where cust_id = ?";

        String request3 = "SELECT cust_money FROM customers WHERE cust_id = ?";

        PreparedStatement ps2 = con.prepareStatement(request2);

        PreparedStatement ps3 = con.prepareStatement(request3);

        ps3.setInt(1, transfer.getInt(2));

        ResultSet formerBalanceResult = ps3.executeQuery();

        formerBalanceResult.next();

        Double formerBalance = formerBalanceResult.getDouble(1);

        ps2.setDouble(1, formerBalance + transfer_amount);

        ps2.setInt(2, transfer.getInt(2));

        int i = ps2.executeUpdate();

        if (i > 0) {
            System.out.println("Recieved $" + transfer_amount + ".");
            String clear_transaction = "DELETE FROM transfer_funds WHERE transfer_id = ?";

            PreparedStatement clearStatement = con.prepareStatement(clear_transaction);

            clearStatement.setInt(1,transactionID);

            int clear = clearStatement.executeUpdate();
            return transfer_amount;
        } else {
            System.out.println("An error has occurred, please check over process.");
            return 0;
        }

    }

    @Override
    public int showSentFunds(int userID) throws SQLException {

        String request = "SELECT * FROM transfer_funds WHERE transfer_to_id = ?";

        PreparedStatement ps1 = con.prepareStatement(request);

        ps1.setInt(1, userID);

        ResultSet output = ps1.executeQuery();

        int num_transactions = 0;

        while (output.next()) {
            num_transactions++;
            System.out.println("Transfer ID: " + output.getInt(1));
            System.out.println("Transfering To account with ID: " + output.getInt(2));
            System.out.println("Transfer Amount: " + output.getDouble(3));
            System.out.println("");

        }
        return num_transactions;
    }

    @Override
    public double getCurrentBalance(int userID) throws SQLException {
        String request = "SELECT cust_money from customers WHERE cust_id = ?";

        PreparedStatement ps = con.prepareStatement(request);

        ps.setInt(1, userID);

        ResultSet output = ps.executeQuery();

        output.next();

        return output.getDouble(1);

    }


}

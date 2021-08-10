package com.anthonyzhu.project0;

import java.sql.*;
import java.util.*;

public interface CustomerDAO {
    void addCustomer(Customer customer) throws SQLException;
    
    void updateCustomer(Customer customer, Customer updater) throws SQLException;
    
    public void addPending(Customer customer) throws SQLException;
    
    void deleteCustomer(int id) throws SQLException;
    
    public void deletePending (int id) throws SQLException;
    
    List<Customer> getCustomers() throws SQLException;
    
    List<Customer> getPending() throws SQLException;
    
    Customer CustomerById(int id) throws SQLException;
    
    void sendFunds(double amount, int sendFromID, int sendToID) throws SQLException;
    
    void receiveFunds(int transactionID) throws SQLException;
    
    void withdraw(int userID, double withdrawAmount) throws SQLException;
    
    void deposit(int userID, double depositAmount) throws SQLException;

    int showSentFunds(int userID) throws SQLException;
    
    double getCurrentBalance(int userID) throws SQLException;

    
}

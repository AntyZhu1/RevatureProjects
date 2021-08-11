package com.anthonyzhu.project0;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {
    void addCustomer(Customer customer) throws SQLException;

    void updateCustomer(Customer customer, Customer updater) throws SQLException;

    public void addPending(Customer customer) throws SQLException;

    void deleteCustomer(int id) throws SQLException;

    public void deletePending(int id) throws SQLException;

    List<Customer> getCustomers() throws SQLException;

    List<Customer> getPending() throws SQLException;

    Customer CustomerById(int id) throws SQLException;

    void sendFunds(double amount, int sendFromID, int sendToID) throws SQLException;

    void recieveFunds(int transactionID) throws SQLException;

    void withdraw(int userID, double withdrawAmount) throws SQLException;

    void deposit(int userID, double depositAmount) throws SQLException;

    void showSentFunds(int userID) throws SQLException;

    double getCurrentBalance(int userID) throws SQLException;

}

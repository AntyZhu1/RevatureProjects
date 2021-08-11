package com.anthonyzhu.project0;

import java.util.List;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String customerUsername;
    private String customerPassword;
    private double customerBalance;

    private List<Customer> pending;

    public Customer() {

    }

    public Customer(int id, String customerUsername, String customerPassword, String firstName, String lastName, double customerBalance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerUsername = customerUsername;
        this.customerPassword = customerPassword;
        this.customerBalance = customerBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return customerUsername;
    }

    public void setUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getPassword() {
        return customerPassword;
    }

    public void setPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public double getBalance() {
        return customerBalance;
    }

    public void setBalance(double customerBalance) {
        this.customerBalance = customerBalance;
    }

    public List<Customer> getPending() {
        return pending;
    }
}

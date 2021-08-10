package com.anthonyzhu.project0;

import java.sql.*;
import java.util.*;

public interface EmployeeDAO {
    void addEmployee(Employee employee) throws SQLException;
    
    void updateEmployee(Employee employee, Employee updater) throws SQLException;
    
    void deleteEmployee(int id) throws SQLException;
    
    List<Employee> getEmployees() throws SQLException;
    
    Employee employeeById(int id) throws SQLException;
    
    void viewTransactions() throws SQLException;
}

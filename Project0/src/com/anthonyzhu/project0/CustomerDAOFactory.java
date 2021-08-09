package com.anthonyzhu.project0;

import java.sql.*;
import java.util.*;

public class CustomerDAOFactory {
    private static CustomerDAO dao;

    private CustomerDAOFactory(){}

    public static CustomerDAO getCustomerDao(){
        if(dao==null) {
            
        	dao = new CustomerDAOImpl();

        }
               
        return dao;
    }
}

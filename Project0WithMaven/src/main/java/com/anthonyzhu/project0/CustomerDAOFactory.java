package com.anthonyzhu.project0;

public class CustomerDAOFactory {
    private static CustomerDAO dao;

    private CustomerDAOFactory() {
    }

    public static CustomerDAO getCustomerDao() {
        if (dao == null) {

            dao = new CustomerDAOImpl();

        }

        return dao;
    }
}

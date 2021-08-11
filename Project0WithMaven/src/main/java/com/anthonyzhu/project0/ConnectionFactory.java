package com.anthonyzhu.project0;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {

    private static Connection con = null;

    private ConnectionFactory() {

    }

    public static Connection getConnection() throws SQLException {

        if (con == null) {

//            ResourceBundle rb = ResourceBundle.getBundle("dbConfig");
//            String url = rb.getString("url");
//            String username = rb.getString("username");
//            String password = rb.getString("password");

            String url = "jdbc:mysql://localhost:3306/banklogins";
            String username = "root";
            String password = "root";

            con = DriverManager.getConnection(url, username, password);

        }
        return con;

    }


}

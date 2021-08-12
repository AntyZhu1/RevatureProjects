package com.anthonyzhu.project0;

import org.apache.log4j.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

//    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Program p = new Program();

        p.runProgram();

    }

}

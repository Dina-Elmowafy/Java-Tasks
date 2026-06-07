package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521/ORCLPDB";
    private static final String USERNAME = "HR";
    private static final String PASSWORD = "Hr_123456";

    public static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
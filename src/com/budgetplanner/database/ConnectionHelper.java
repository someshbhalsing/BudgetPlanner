package com.budgetplanner.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

    static Connection connection;

    ConnectionHelper() {
        try {
            Class.forName(DatabaseHelper.DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found exception");
        }
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(
                        DatabaseHelper.DB_SERVER_URL,
                        DatabaseHelper.USER_NAME,
                        DatabaseHelper.PASSWORD
                );
            }
        } catch (SQLException e) {
            System.out.println("Unable to establish connection");
        }
    }

}

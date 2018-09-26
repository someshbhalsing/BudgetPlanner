package com.budgetplanner.database;

import java.sql.*;

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

    public ResultSet getResultForQuery(String query) throws SQLException {
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        return statement.executeQuery(query);
    }

}

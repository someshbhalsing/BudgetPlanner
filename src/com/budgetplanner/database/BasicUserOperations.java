package com.budgetplanner.database;

import com.budgetplanner.datamodel.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BasicUserOperations extends ConnectionHelper {

    void insert(User user) throws SQLException {
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet set = statement.executeQuery("select * from " + User.DatabaseHelper.TABLE_NAME);
        set.moveToInsertRow();
        set.updateString(User.DatabaseHelper.COLUMN_USERNAME, user.getUserName());
        set.updateString(User.DatabaseHelper.COLUMN_PASSWORD, user.getPassword());
        set.updateString(User.DatabaseHelper.COLUMN_STREET, user.getStreet());
        set.updateString(User.DatabaseHelper.COLUMN_CITY, user.getCity());
        set.updateString(User.DatabaseHelper.COLUMN_STATE, user.getState());
        set.updateString(User.DatabaseHelper.COLUMN_COUNTRY, user.getCountry());
        set.updateString(User.DatabaseHelper.COLUMN_PHONE_NO, user.getPhoneNo());
        set.updateString(User.DatabaseHelper.COLUMN_EMAIL_ADDRESS, user.getEmailAddress());
        set.updateInt(User.DatabaseHelper.COLUMN_MONTHLY_BUDGET, user.getMonthlyBudget());
        set.insertRow();
        set.close();
        statement.close();
    }

}












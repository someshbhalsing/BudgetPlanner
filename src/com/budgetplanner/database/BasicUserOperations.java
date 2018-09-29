package com.budgetplanner.database;

import com.budgetplanner.datamodel.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BasicUserOperations extends ConnectionHelper {

    public boolean insert(User user) {
        try {
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
        } catch (SQLException e) {
            System.out.println("insertion exception = " + e.toString());
            return false;
        }
        return true;
    }

    public boolean update(User user) {
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet set = statement.executeQuery(
                    "select * from " + User.DatabaseHelper.TABLE_NAME + " where " + User.DatabaseHelper.COLUMN_USERID +
                            "=" + user.getuId() + ";"
            );
            set.first();
            set.updateString(User.DatabaseHelper.COLUMN_USERNAME, user.getUserName());
            set.updateString(User.DatabaseHelper.COLUMN_PASSWORD, user.getPassword());
            set.updateString(User.DatabaseHelper.COLUMN_STREET, user.getStreet());
            set.updateString(User.DatabaseHelper.COLUMN_CITY, user.getCity());
            set.updateString(User.DatabaseHelper.COLUMN_STATE, user.getState());
            set.updateString(User.DatabaseHelper.COLUMN_COUNTRY, user.getCountry());
            set.updateString(User.DatabaseHelper.COLUMN_PHONE_NO, user.getPhoneNo());
            set.updateString(User.DatabaseHelper.COLUMN_EMAIL_ADDRESS, user.getEmailAddress());
            set.updateInt(User.DatabaseHelper.COLUMN_MONTHLY_BUDGET, user.getMonthlyBudget());
            set.updateRow();
            set.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("updation exception = " + e.toString());
            return false;
        }
        return true;
    }

    public boolean delete(int userId) {
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet set = statement.executeQuery(
                    "select * from " + User.DatabaseHelper.TABLE_NAME + " where " + User.DatabaseHelper.COLUMN_USERID +
                            "=" + userId + ";"
            );
            set.first();
            set.deleteRow();
            set.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("deletion exception = " + e.toString());
            return false;
        }
        return true;
    }

    public List<User> query(String query) {
        List<User> retList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet set = statement.executeQuery(query);

            if (!set.first()) {
                return null;
            }
            while (!set.isAfterLast()) {
                User user = new User(
                        set.getInt(User.DatabaseHelper.COLUMN_USERID),
                        set.getString(User.DatabaseHelper.COLUMN_USERNAME),
                        set.getString(User.DatabaseHelper.COLUMN_PASSWORD),
                        set.getString(User.DatabaseHelper.COLUMN_STREET),
                        set.getString(User.DatabaseHelper.COLUMN_CITY),
                        set.getString(User.DatabaseHelper.COLUMN_STATE),
                        set.getString(User.DatabaseHelper.COLUMN_COUNTRY),
                        set.getString(User.DatabaseHelper.COLUMN_PHONE_NO),
                        set.getString(User.DatabaseHelper.COLUMN_EMAIL_ADDRESS),
                        set.getInt(User.DatabaseHelper.COLUMN_MONTHLY_BUDGET)
                );
                retList.add(user);
                set.next();
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("query exception = " + e.toString());
            return null;
        }
        return retList;
    }

}
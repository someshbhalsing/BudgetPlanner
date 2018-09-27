package com.budgetplanner.database;

import com.budgetplanner.datamodel.User;
import org.jetbrains.annotations.TestOnly;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BasicUserOperations extends ConnectionHelper {

    @TestOnly
    public static void main(String[] args) {
        BasicUserOperations user = new BasicUserOperations();
        boolean flag = user.insert(new User(0, "A", "A", "A", "A", "A", "A", "A", "A", 1500));
        if (flag) {
            int id = user.query("select * from user").get(0).getuId();
            flag = user.update(new User(id, "B", "B", "B", "B", "B", "B", "B", "B", 1500));
        }
        if (flag) {
            String uname = user.query("select * from user").get(0).getUserName();
            System.out.printf("Username : " + uname);
        }
        int id = user.query("select * from user").get(0).getuId();
        user.delete(id);
    }

    private boolean insert(User user) {
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
            System.out.println("e.getSQLState() = " + e.getSQLState());
            return false;
        }
        return true;
    }

    private boolean update(User user) {
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
            System.out.println("e.getSQLState() = " + e.getSQLState());
            return false;
        }
        return true;
    }

    private boolean delete(int userId) {
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
            System.out.println("e.getSQLState() = " + e.getSQLState());
            return false;
        }
        return true;
    }

    private List<User> query(String query) {
        List<User> retList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet set = statement.executeQuery(query);
            set.first();
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
            System.out.println("e.getSQLState() = " + e.getSQLState());
            return null;
        }
        return retList;
    }

}
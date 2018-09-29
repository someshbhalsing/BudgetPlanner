package com.budgetplanner.database;

import com.budgetplanner.datamodel.Expense;
import org.jetbrains.annotations.TestOnly;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BasicExpenseOperations extends ConnectionHelper {

    @TestOnly
    public static void main(String[] args) {
        BasicExpenseOperations op = new BasicExpenseOperations();
        for (int i = 0; i < 100; i++) {
            op.insert(new Expense(1, null, "XYZ", "UVW", 1500, "Card"), 14);
        }
    }

    public boolean insert(Expense expense, int uid) {
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet set = statement.executeQuery("select * from " + Expense.DatabaseHelper.TABLE_NAME);
            set.moveToInsertRow();
            set.updateDate(Expense.DatabaseHelper.COLUMN_DATE, new java.sql.Date(System.currentTimeMillis()));
            set.updateString(Expense.DatabaseHelper.COLUMN_NAME, expense.getName());
            set.updateString(Expense.DatabaseHelper.COLUMN_DESCRIPTION, expense.getDescription());
            set.updateString(Expense.DatabaseHelper.COLUMN_PAY_METHOD, expense.getPay_method());
            set.updateInt(Expense.DatabaseHelper.COLUMN_PRICE, expense.getPrice());
            set.insertRow();
            set.close();
            statement.close();
            insertIntoManages(lastInserted(), uid);
        } catch (SQLException e) {
            System.out.println("expense insertion exception = " + e.toString());
            return false;
        }
        return true;
    }

    public boolean update(Expense expense) {
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet set = statement.executeQuery(
                    "select * from " + Expense.DatabaseHelper.TABLE_NAME + " where " + Expense.DatabaseHelper.COLUMN_ID +
                            "=" + expense.getId() + ";"
            );
            set.first();
            set.updateDate(Expense.DatabaseHelper.COLUMN_DATE, expense.getDate());
            set.updateString(Expense.DatabaseHelper.COLUMN_NAME, expense.getName());
            set.updateString(Expense.DatabaseHelper.COLUMN_DESCRIPTION, expense.getDescription());
            set.updateString(Expense.DatabaseHelper.COLUMN_PAY_METHOD, expense.getPay_method());
            set.updateInt(Expense.DatabaseHelper.COLUMN_PRICE, expense.getPrice());
            set.updateRow();
            set.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("expense updation exception = " + e.toString());
            return false;
        }
        return true;
    }

    public boolean delete(int expenseId) {
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet set = statement.executeQuery(
                    "select * from " + Expense.DatabaseHelper.TABLE_NAME + " where " + Expense.DatabaseHelper.COLUMN_ID +
                            "=" + expenseId + ";"
            );
            set.first();
            set.deleteRow();
            set.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("expense deletion exception = " + e.toString());
            return false;
        }
        return true;
    }

    public List<Expense> query(String query) {
        List<Expense> retList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet set = statement.executeQuery(query);
            if (!set.first()) {
                return null;
            }
            while (!set.isAfterLast()) {
                Expense expense = new Expense(
                        set.getInt(Expense.DatabaseHelper.COLUMN_ID),
                        set.getDate(Expense.DatabaseHelper.COLUMN_DATE),
                        set.getString(Expense.DatabaseHelper.COLUMN_NAME),
                        set.getString(Expense.DatabaseHelper.COLUMN_DESCRIPTION),
                        set.getInt(Expense.DatabaseHelper.COLUMN_PRICE),
                        set.getString(Expense.DatabaseHelper.COLUMN_PAY_METHOD)
                );
                retList.add(expense);
                set.next();
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("expense query exception = " + e.toString());
            return null;
        }
        return retList;
    }

    private int lastInserted() {
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery("SELECT max(eid) from expenses;");
            if (rs.first())
                return rs.getInt(1);
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("manages insertion exception : " + e.toString());
        }
        return -1;
    }

    private void insertIntoManages(int eid, int uid) {
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery("SELECT * FROM manages;");
            rs.moveToInsertRow();
            rs.updateInt("eid", eid);
            rs.updateInt("uid", uid);
            rs.insertRow();
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("manages insertion exception : " + e.toString());
        }
    }

    public List<String[]> queryStringArray(String query) {
        List<String[]> retList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet set = statement.executeQuery(query);
            if (!set.first()) {
                return null;
            }
            while (!set.isAfterLast()) {
                Expense expense = new Expense(
                        set.getInt(Expense.DatabaseHelper.COLUMN_ID),
                        set.getDate(Expense.DatabaseHelper.COLUMN_DATE),
                        set.getString(Expense.DatabaseHelper.COLUMN_NAME),
                        set.getString(Expense.DatabaseHelper.COLUMN_DESCRIPTION),
                        set.getInt(Expense.DatabaseHelper.COLUMN_PRICE),
                        set.getString(Expense.DatabaseHelper.COLUMN_PAY_METHOD)
                );
                retList.add(expense.getStringArrayObject());
                set.next();
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("expense query exception = " + e.toString());
            return null;
        }
        return retList;
    }
}

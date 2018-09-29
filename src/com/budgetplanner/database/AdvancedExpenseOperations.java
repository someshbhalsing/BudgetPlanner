package com.budgetplanner.database;

import com.budgetplanner.datamodel.Expense;
import com.budgetplanner.datamodel.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AdvancedExpenseOperations extends BasicExpenseOperations {

    private int mUserId;

    public AdvancedExpenseOperations(int mUserId) {
        this.mUserId = mUserId;
    }

    /*
        * select * from expenses where eid = (select eid from manages where uid=$uid);
        *
        * */
    public List<Expense> getSpendingList() {
        return query(" select * from expenses where "
                + Expense.DatabaseHelper.COLUMN_ID +
                " in (select " + Expense.DatabaseHelper.COLUMN_ID +
                " from manages where uid=" + mUserId + ");");
    }

    public List<Expense> getSpendingList(Date fromDate, Date toDate) {
        return query("select * from " + Expense.DatabaseHelper.TABLE_NAME +
                " where " + Expense.DatabaseHelper.COLUMN_ID + " in (select " + Expense.DatabaseHelper.COLUMN_ID +
                " from manages where " + User.DatabaseHelper.COLUMN_USERID + "=" + mUserId +
                ") AND date >= '" + fromDate.toString() + "' AND date <= '" + toDate.toString() + "';");
    }

    public int getTotalSpendings() {
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet set = statement.executeQuery("select sum(" + Expense.DatabaseHelper.COLUMN_PRICE +
                    ") from " + Expense.DatabaseHelper.TABLE_NAME +
                    " where " + Expense.DatabaseHelper.COLUMN_ID + " in (select " + Expense.DatabaseHelper.COLUMN_ID +
                    " from manages where " + User.DatabaseHelper.COLUMN_USERID + "=" + mUserId + ");");
            if (!set.first()) {
                return 0;
            }
            return set.getInt(1);
        } catch (SQLException e) {
            System.out.println("expense query exception = " + e.toString());
        }
        return 0;
    }

    public int getTotalSpendings(String fromDate, String toDate) {
        String query = "select sum(" + Expense.DatabaseHelper.COLUMN_PRICE +
                ") from " + Expense.DatabaseHelper.TABLE_NAME +
                " where " + Expense.DatabaseHelper.COLUMN_ID + " in (select " + Expense.DatabaseHelper.COLUMN_ID +
                " from manages where " + User.DatabaseHelper.COLUMN_USERID + "=" + mUserId +
                ") AND date >= '" + fromDate + "' AND date <= '" + toDate + "';";

        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet set = statement.executeQuery(query);
            if (!set.first()) {
                return 0;
            }
            return set.getInt(1);
        } catch (SQLException e) {
            System.out.println("expense query exception = " + e.toString());
        }
        return 0;
    }

    public List<String[]> getSpendingArray() {
        return queryStringArray(" select * from expenses where "
                + Expense.DatabaseHelper.COLUMN_ID +
                " in (select " + Expense.DatabaseHelper.COLUMN_ID +
                " from manages where uid=" + mUserId + ");");
    }

    public List<String[]> getSpendingArray(String fromDate, String toDate) {
        return queryStringArray("select * from " + Expense.DatabaseHelper.TABLE_NAME +
                " where " + Expense.DatabaseHelper.COLUMN_ID + " in (select " + Expense.DatabaseHelper.COLUMN_ID +
                " from manages where " + User.DatabaseHelper.COLUMN_USERID + "=" + mUserId +
                ") AND date >= '" + fromDate + "' AND date <= '" + toDate + "';");
    }


    public boolean insert(Expense expense) {
        return super.insert(expense, mUserId);
    }
}

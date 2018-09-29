package com.budgetplanner.database;

import com.budgetplanner.datamodel.User;

import java.util.List;

public class AdvancedUserOperations extends BasicUserOperations {

    public boolean userNameExists(String userName) {
        List<User> list = query("SELECT * FROM " + User.DatabaseHelper.TABLE_NAME + " WHERE " + User.DatabaseHelper.COLUMN_USERNAME +
                " = \"" + userName + "\";");
        return list != null && list.size() != 0;
    }

    public User authenticateUser(String userName, String password) {
        List<User> list = query("SELECT * FROM " + User.DatabaseHelper.TABLE_NAME + " WHERE " + User.DatabaseHelper.COLUMN_USERNAME +
                " = \"" + userName + "\" AND " + User.DatabaseHelper.COLUMN_PASSWORD + " = \"" + password + "\";");
        if (list != null && list.size() != 0)
            return list.get(0);
        return null;
    }

}
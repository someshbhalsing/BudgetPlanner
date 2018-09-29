package com.budgetplanner.datamodel;

import com.budgetplanner.database.AdvancedUserOperations;

public class Validations {

    public static boolean validateUser(User user) {
        AdvancedUserOperations operations = new AdvancedUserOperations();
        if (operations.userNameExists(user.getUserName())) {
            System.out.println("username exists");
            return false;
        }
        if (isInvalidString(user.getUserName(), 1)) {
            System.out.println("invalid uname");
            return false;
        }
        if (isInvalidString(user.getPassword(), 6)) {
            System.out.println("password invalid");
            return false;
        }
        if (isInvalidString(user.getStreet(), 1)) {
            System.out.println("street invalid");
            return false;
        }
        if (isInvalidString(user.getEmailAddress(), 6)) {
            System.out.println("email invalid");
            return false;
        }
        if (isInvalidString(user.getCity(), 1)) {
            System.out.println("city invalid");
            return false;
        }
        if (isInvalidString(user.getState(), 1)) {
            System.out.println("state invalid");
            return false;
        }
        if (isInvalidString(user.getCountry(), 1)) {
            System.out.println("country invalid");
            return false;
        }
        if (isInvalidString(user.getPhoneNo(), 10)) {
            System.out.println("phone1 invalid");
            return false;
        }
        if (!isNumber(user.getPhoneNo())) {
            System.out.println("phome2 invalid");
            return false;
        }
        if (!isEmailAddress(user.getEmailAddress())) {
            System.out.println("email2 invalid");
            return false;
        }
        return true;
    }

    public static boolean isInvalidString(String string, int minLength) {
        return string == null || string.length() < minLength;
    }

    public static boolean isNumber(String string) {
        for (char c : string.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }

    public static boolean isEmailAddress(String string) {
        return string.contains("@") && string.contains(".co");
    }
}

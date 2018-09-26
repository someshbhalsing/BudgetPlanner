package com.budgetplanner.datamodel;

public class User {

    private int uId;
    private String userName;
    private String password;
    private String street;
    private String city;
    private String state;
    private String country;
    private String phoneNo;
    private String emailAddress;
    private int monthlyBudget;

    public User(
            int uId,
            String userName,
            String password,
            String street,
            String city,
            String state,
            String country,
            String phoneNo,
            String emailAddress,
            int monthlyBudget
    ) {
        this.uId = uId;
        this.userName = userName;
        this.password = password;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.phoneNo = phoneNo;
        this.emailAddress = emailAddress;
        this.monthlyBudget = monthlyBudget;
    }

    public int getuId() {
        return uId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getMonthlyBudget() {
        return monthlyBudget;
    }

    public static class DatabaseHelper {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_USERID = "uid";
        public static final String COLUMN_USERNAME = "uname";
        public static final String COLUMN_PASSWORD = "upassword";
        public static final String COLUMN_STREET = "street";
        public static final String COLUMN_CITY = "city";
        public static final String COLUMN_STATE = "state";
        public static final String COLUMN_COUNTRY = "country";
        public static final String COLUMN_PHONE_NO = "phone_no";
        public static final String COLUMN_EMAIL_ADDRESS = "email_addr";
        public static final String COLUMN_MONTHLY_BUDGET = "monthly_budget";

        public static final int INDEX_USERID = 1;
        public static final int INDEX_USERNAME = 2;
        public static final int INDEX_PASSWORD = 3;
        public static final int INDEX_STREET = 4;
        public static final int INDEX_CITY = 5;
        public static final int INDEX_STATE = 6;
        public static final int INDEX_COUNTRY = 7;
        public static final int INDEX_PHONE_NO = 8;
        public static final int INDEX_EMAIL_ADDRESS = 9;
        public static final int INDEX_MONTHLY_BUDGET = 10;
    }
}

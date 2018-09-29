package com.budgetplanner.datamodel;

import java.sql.Date;

public class Expense {

    private int id;
    private Date date;
    private String name;
    private String description;
    private int price;
    private String pay_method;


    public Expense(int id, Date date, String name, String description, int price, String pay_method) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.description = description;
        this.price = price;
        this.pay_method = pay_method;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getPay_method() {
        return pay_method;
    }

    public static final class DatabaseHelper {
        public static final String TABLE_NAME = "expenses";
        public static final String COLUMN_ID = "eid";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_NAME = "item_name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_PAY_METHOD = "pay_method";
    }

    public String[] getStringArrayObject() {
        return new String[]{String.valueOf(id), date.toString(), name, description, String.valueOf(price), pay_method};
    }

}

Follow MySQL databases commands below before using the project

create database budgetplanner;

CREATE TABLE user (
  uid INT NOT NULL AUTO_INCREMENT,
  uname VARCHAR(25) NOT NULL,
  upassword VARCHAR(12) NOT NULL,
  street VARCHAR(45),
  city VARCHAR(45),
  state VARCHAR(45),
  country VARCHAR(45),
  phone_no INT(10),
  email_addr VARCHAR(45),
  PRIMARY KEY (uid));

CREATE TABLE expensed (
  eid INT NOT NULL AUTO_INCREMENT,
  date DATE,
  item_name VARCHAR(45),
  description VARCHAR(45),
  price INT,
  pay_method VARCHAR(45),
  remaining_amount INT,
  PRIMARY KEY (eid));

  ALTER TABLE user
  ADD COLUMN monthly_budget INT;

CREATE TABLE manages (
  eid INT NOT NULL,
  uid INT NULL,
  PRIMARY KEY (eid),
    FOREIGN KEY (eid)
    REFERENCES expensed (eid),
    FOREIGN KEY (uid)
    REFERENCES user (uid)
);
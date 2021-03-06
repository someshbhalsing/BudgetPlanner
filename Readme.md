<h1>Follow MySQL databases commands below before using the project</h1>

create database budgetplanner;

CREATE TABLE user (
  uid INT NOT NULL AUTO_INCREMENT,
  uname VARCHAR(25) NOT NULL,
  upassword VARCHAR(12) NOT NULL,
  street VARCHAR(45),
  city VARCHAR(45),
  state VARCHAR(45),
  country VARCHAR(45),
  phone_no VARCHAR(10),
  email_addr VARCHAR(45),
  monthly_budget INT,
  PRIMARY KEY (uid));

CREATE TABLE expenses (
  eid INT NOT NULL AUTO_INCREMENT,
  date DATE,
  item_name VARCHAR(45),
  description VARCHAR(45),
  price INT,
  pay_method VARCHAR(45),
  PRIMARY KEY (eid));


CREATE TABLE manages (
  eid INT NOT NULL,
  uid INT NULL,
  PRIMARY KEY (eid),
    FOREIGN KEY (eid)
    REFERENCES expensed (eid),
    FOREIGN KEY (uid)
    REFERENCES user (uid)
);

DELIMITER $$
CREATE TRIGGER phone_validation_before_insert BEFORE INSERT ON user FOR EACH ROW
BEGIN
	IF CHAR_LENGTH(NEW.phone_no) != 10
    then
		SIGNAL sqlstate '45000' set MESSAGE_TEXT = 'Phone no validation failed';
	end if;
END$$
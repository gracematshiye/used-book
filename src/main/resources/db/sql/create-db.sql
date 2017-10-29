DROP TABLE IF EXISTS STUDENT;
create table STUDENT (student_no int(10) primary key,
name varchar(50) NOT NULL,
surname varchar(50) NOT NULL,
email varchar(50) NOT NULL,
funds decimal NOT NULL,
password varchar(50) NOT NULL,
logged_in BOOLEAN NOT NULL,
session_key varchar(255));

DROP TABLE IF EXISTS BOOK;
create table BOOK (
book_id int primary key AUTO_INCREMENT,
title varchar(50),
author varchar(50),
isbn varchar(50),
edition varchar(50),
language varchar(50),
subject varchar(50),
additional_info varchar(50),
price decimal,
status varchar(10),
date_created varchar(20),
seller_id int);

DROP TABLE IF EXISTS PAYMENT;
create table PAYMENT (
payment_id int primary key AUTO_INCREMENT,
seller_id int(10) not null,
book_id int not null,
buyer_id int(10) not null,
date_sold varchar(20) not null,
payment_reference varchar(20) not null);

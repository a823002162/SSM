create database mybatis;
use mybatis;

create table t_user(
    ID int primary key auto_increment,
    user_name varchar(20),
    pwd varchar(20),
    Age int,
    Gender char,
    Email varchar(50)
);

select ID id, user_name username, pwd password, Age age, Gender gender, Email email
from t_user
where ID = 1;

CREATE DATABASE `mybatis-example`;

USE `mybatis-example`;

CREATE TABLE `t_emp`(
                        emp_id INT AUTO_INCREMENT,
                        emp_name CHAR(100),
                        emp_salary DOUBLE(10,5),
                        PRIMARY KEY(emp_id)
);

INSERT INTO `t_emp`(emp_name,emp_salary) VALUES("tom",200.33);
INSERT INTO `t_emp`(emp_name,emp_salary) VALUES("jerry",666.66);
INSERT INTO `t_emp`(emp_name,emp_salary) VALUES("andy",777.77);


select emp_id,emp_name,emp_salary,avg(emp_salary)
from t_emp
where emp_salary = (select max(emp_salary) from t_emp)
group by emp_id;


CREATE TABLE `t_customer` (`customer_id` INT NOT NULL AUTO_INCREMENT, `customer_name` CHAR(100), PRIMARY KEY (`customer_id`) );

CREATE TABLE `t_order` ( `order_id` INT NOT NULL AUTO_INCREMENT, `order_name` CHAR(100), `customer_id` INT, PRIMARY KEY (`order_id`) );

INSERT INTO `t_customer` (`customer_name`) VALUES ('c01');

INSERT INTO `t_order` (`order_name`, `customer_id`) VALUES ('o1', '1');
INSERT INTO `t_order` (`order_name`, `customer_id`) VALUES ('o2', '1');
INSERT INTO `t_order` (`order_name`, `customer_id`) VALUES ('o3', '1');

select order_id,order_name,tt.customer_id ,customer_name
from t_order tt
         join t_customer tc on tt.customer_id = tc.customer_id
where order_id = 2;

select order_id,order_name,t_order.customer_id,customer_name
from t_order,t_customer
where order_id = 1

select  customer_name,tc.customer_id,order_id,order_name
from t_customer tc
         join t_order tt on tt.customer_id = tc.customer_id
where tc.customer_id = 1

select * from t_emp where emp_name = 'jack' or emp_salary = 2000 or emp_id = 2
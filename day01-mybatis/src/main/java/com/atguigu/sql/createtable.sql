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


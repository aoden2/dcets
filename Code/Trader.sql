drop table user;
drop table brokerinfo;

create table user(
id int primary key auto_increment, 
username varchar(80) not null,
password varchar(80) not null
)

create table brokerinfo(
id int primary key auto_increment, 
name varchar(255),
ip varchar(255),
port int,
pass varchar(80)
)
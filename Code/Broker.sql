create table future(
id int primary key auto_increment, 
category varchar(255),
name varchar(255),
period varchar(80)
);

create table finalorder(
id int primary key auto_increment, 
osid int,
obid int,
quantity int,
price int,
status int
);

create table originorder(
id int primary key auto_increment, 
fid int,
tid int,
quantity int,
cumQtyl int,
leavesqty int,
price int,
d date,
status int
);
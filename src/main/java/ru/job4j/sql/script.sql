create database projects;

create table projects(
id serial primary key,
manager varchar(255),
start date,
peoples integer);

insert into projects(manager, start, peoples) values('Vitaliy', '12.06.2022', '7');

select * from projects;

update projects set start = '12.03.2022';

select * from projects;

delete from projects;

select * from projects;
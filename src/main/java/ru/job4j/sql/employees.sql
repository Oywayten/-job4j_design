create table position_(
    id serial primary key,
    name_ varchar(255)
);

create table employees(
    id serial primary key,
    name_ varchar(255),
    position_id int references position_(id)
);

insert into position_(name_) values ('programmer');
insert into employees(name_, position_id) values ('Ivan', 1);

select * from employees;
select * from position_ where id in (select id from employees);

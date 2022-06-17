create table departments(
    id serial primary key ,
    fname varchar(255)
);

create table employees(
    id serial primary key ,
    fname varchar(255),
    id_dep int references departments(id)
);

insert into departments (fname)
values
('bookkeeping'),
('sport'),
('production'),
('IT'),
('document flow'),
('law');

insert into employees (fname, id_dep)
values
('Ivan', 1),
('Sergey', 2),
('Andrew', 3),
('Bob', 4),
('Dilan', 5);

select * from departments;
select * from employees;

select * from departments d
left join employees e
on d.id = e.id_dep;

select * from departments d
right join employees e
on d.id = e.id_dep;

select * from departments d
full join employees e
on d.id = e.id_dep;

select * from departments d
cross join employees e;

--Используя left join найти департаменты, у которых нет работников
select * from departments d
left join employees e
on d.id = e.id_dep
where e.id is null;

--Используя left и right join написать запросы, которые давали бы одинаковый результат (порядок вывода колонок в эти запросах также должен быть идентичный).
--1 left
select d.id id_dep, d.fname department, e.id id_emp, e.fname employee, e.id_dep from departments d
left join employees e
on d.id = e.id_dep;

--2 right
select d.id id_dep, d.fname department, e.id id_emp, e.fname employee, e.id_dep from employees e
right join departments d
on d.id = e.id_dep;

--Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары
create table teens(
    id serial primary key ,
    fname varchar(255),
    gender varchar(255)
);

insert into teens (fname, gender)
values
('Ivan', 'man'),
('Bob', 'man'),
('Clare', 'woman'),
('Inna', 'woman');

select t1.fname, t1.gender, t2.fname, t2.gender
from teens t1 cross join teens t2
where t1.gender != t2.gender;
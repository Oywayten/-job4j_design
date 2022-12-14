-- drop table customers;
-- drop TABLE orders;

create table if not exists customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

create table if not exists orders2(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);


insert into customers (first_name, last_name, age, country) values ('Ivan', 'Davidov', 35, 'UK'),
('Bob', 'Sindun', 30, 'USA'),
('Latip', 'Crudis', 39, 'Brasil'),
('Petr', 'Bannov', 15, 'RF'),
('Liza', 'Kuper', 18, 'Canada');
select * from customers;

insert into orders2 (amount, customer_id) values (145, 1), (15, 2), (300, 3);
select * from orders2;

-- 1. Клиенты минимального возраста
select first_name, last_name, age from customers
where age = (select min(age) from customers);

-- 2. Клиенты без заказа
select first_name, last_name from customers c
where id not in (select customer_id from orders2 o2);
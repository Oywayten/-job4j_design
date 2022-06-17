--Создать структур данных в базе.
-- Таблицы: Кузов. Двигатель, Коробка передач.
create table body(
    id serial primary key ,
    fname varchar(255)
);

create table engine(
    id serial primary key ,
    fname varchar(255)
);

create table gear(
    id serial primary key ,
    fname varchar(255)
);

--Создать структуру Машина.
create table car1(
    id serial primary key ,
    fname varchar(255),
    id_b int references body(id),
    id_e int references engine(id),
    id_g int references gear(id)
);

--Заполнить таблицы через insert.
insert into body (fname)
values
('sedan'),
('cabriolet'),
('pickup'),
('limousine');

insert into engine (fname)
values
('petrol'),
('diesel'),
('electricity'),
('hydrogen');

insert into gear (fname)
values
('automatic'),
('mechanics'),
('variable');

insert into car1 (fname, id_b, id_e, id_g)
values
('porsche 911', 1, 1, 1),
('nissan qashqai', 2, 2, 2),
('niva 4x4', 3, 3, 3);

insert into car1 (fname, id_b, id_e, id_g)
values ('gazel', 3, null, null);

--1) Вывести список всех машин и все привязанные к ним детали.
--Нужно учесть, что каких-то деталей машина может и не содержать.
select * from car1 c
left join body b on c.id_b = b.id
left join engine e on c.id_e = e.id
left join gear g on c.id_g = g.id;

--2) Вывести отдельно детали (1 деталь - 1 запрос),
--которые не используются НИ в одной машине, кузова, двигатели, коробки передач.
select b.fname not_used from car1 c
right join body b on c.id_b = b.id where c.id is null ;

select e.fname not_used from car1 c
right join engine e on c.id_b = e.id where c.id is null ;

select g.fname not_used from car1 c
right join gear g on c.id_b = g.id where c.id is null ;
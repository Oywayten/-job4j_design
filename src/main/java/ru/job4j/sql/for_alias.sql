create table car(
    id serial primary key ,
    model varchar(255),
    color varchar(255),
    max_speed int
);

create table racer(
    id serial primary key ,
    fname varchar(255),
    id_car int references car(id) unique
);

insert into car(model, color, max_speed) values ('bmw', 'white', 360);
insert into car(model, color, max_speed) values ('suzuki', 'black', 180);
insert into car(model, color, max_speed) values ('lada', 'eggplant', 140);

insert into racer(fname, id_car) values ('jhon', 1);
insert into racer(fname, id_car) values ('tomyam', 2);
insert into racer(fname, id_car) values ('ivan', 3);

--проверяем гараж
select * from car;

--проверяем личный состав
select * from racer;

--смотрим кто на чем
select r.fname Имя, c.model as Модель, c.color Цвет, c.max_speed
from racer r join car c on r.id_car = c.id;

--находим победителя по жизни
select r.fname as Name, c.max_speed best_speed
from racer r join car c on r.id_car = c.id and c.max_speed > 200;

-- join-им всех на заезд на самой быстрой тачке
select r.fname as Name, c.model best_car, c.max_speed
from racer r join car c on c.max_speed > 300;
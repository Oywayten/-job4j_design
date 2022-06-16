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

--join-им и смотрим кто на чем
select r.fname Имя, c.model as Модель, c.color Цвет, c.max_speed
from racer r join car c on r.id_car = c.id;

--join-им и выводим тех, у кого скорость больше 200
select r.fname as Name, c.max_speed best_speed
from racer r join car c on r.id_car = c.id where c.max_speed > 200;

-- join-им и выводим со скоростью меньше 300
select r.fname as Name, c.model best_car, c.max_speed
from racer r join car c on r.id_car = c.id where c.max_speed < 300;
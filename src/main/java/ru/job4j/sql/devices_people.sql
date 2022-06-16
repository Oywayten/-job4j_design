create table devices(
    id serial primary key,
    fname varchar(255),
    price float);

create table people(
    id serial primary key,
    fname varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices (fname, price) values ('teapot', 2850.10), ('iron', 4500.25), ('keyboard', 750.01),
('telephone', 69999.99), ('vacuum', 9500.33), ('flashlight', 500.01), ('notebook', 99000.55), ('scanner', 3500),
('printer', 3870), ('car wash', 250000);
insert into people (fname) values ('Ivan'), ('Tom'), ('Katya'), ('Bob'), ('Lana');
insert into devices_people (people_id, device_id) values (1, 1), (1, 2), (2, 3), (2, 4), (3, 5), (3, 6), (4, 7), (4, 8),
(5, 9), (5, 10);

select * from devices;
select * from people;
select * from devices_people;

select avg(price) Average_price from devices;
select p.fname People, avg(d.price) from devices d join devices_people dp on d.id = dp.device_id join people p
on p.id = dp.people_id group by p.fname order by avg(d.price) desc ;

select p.fname People, avg(d.price) as adv from devices d join devices_people dp on d.id = dp.device_id join people p
on p.id = dp.people_id group by p.fname having avg(d.price) > 5000 order by avg(d.price) desc;

--ALTER SEQUENCE people_id_seq RESTART WITH 1;
--ALTER SEQUENCE devices_id_seq RESTART WITH 1;
--ALTER SEQUENCE devices_people_id_seq RESTART WITH 1;

--delete from devices_people;
--delete from devices;
--delete from people;


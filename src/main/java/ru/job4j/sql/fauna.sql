create table fauna (
    id serial primary key,
    fname text,
    avg_age int,
    discovery_date date
);

insert into fauna (fname, avg_age, discovery_date) values ('whale', 50, '0001-01-01');
insert into fauna (fname, avg_age, discovery_date) values ('whale_fish', 15000, null);
insert into fauna (fname, avg_age, discovery_date) values ('wolf', 15, '1105-01-17');
insert into fauna (fname, avg_age, discovery_date) values ('elephant', 30, '1391-11-01');
insert into fauna (fname, avg_age, discovery_date) values ('raven', 300, '1004-02-02');

select
    f.avg_age,
    f.discovery_date,
    f.fname,
    f."id"
from
    fauna f where f.fname like '%fish%';

select
    f.avg_age,
    f.discovery_date,
    f.fname,
    f."id"
from
    fauna f where f.avg_age >= 10000 and f.avg_age <= 20000;

select
    f.avg_age,
    f.discovery_date,
    f.fname,
    f."id"
from
    fauna f where f.discovery_date is null;

select
    f.avg_age,
    f.discovery_date,
    f.fname,
    f."id"
from
    fauna f where f.discovery_date < '01-01-1950'>;
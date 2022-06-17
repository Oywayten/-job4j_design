create table type(
id serial primary key ,
fname varchar(255)
);

create table product(
id serial primary key ,
fname varchar(255) ,
type_id int,
expired_date date,
price float
);

insert into type (fname) values ('cheese'), ('milk'), ('icecream');
insert into product (fname, type_id, expired_date, price) values
('hochland', 1, '2022-06-15', 200.03),
('mozzarella', 1, '2022-02-01', 250.03),
('fused', 1, '2023-06-15', 20.01),
('fresh', 2, '2022-03-30', 140),
('parmalat', 2, '2023-06-15', 180.00),
('new milk', 2, '2022-06-15', 150),
('vanilla icecream', 3, '2022-06-15', 90.00),
('chocolate', 3, '2023-06-15', 90.00),
('icecream creme-brulee', 3, '2022-06-15', 910);

select * from type;
select * from product;

--запрос получение всех продуктов с типом "СЫР"
select product.fname
from product
join type
on product.type_id = type.id
where type.fname = 'cheese';

--запрос получения всех продуктов, у кого в имени есть слово "мороженое"
select product.fname
from product
join type
on product.type_id = type.id
where product.fname
like '%icecream%';

--запрос, который выводит все продукты, срок годности которых уже истек
select product.fname, product.expired_date
from product
join type
on product.type_id = type.id
where expired_date < current_date
order by expired_date;

--запрос, который выводит самый дорогой продукт.
select product.fname, product.price
from product
where product.price =
(select max(p1.price)
from product p1);

--запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество
select t.fname, count(p.fname)
from type t
join product p
on t.id = p.type_id
group by t.fname;

--запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select product.fname
from product
join type
on product.type_id = type.id
where type.fname = 'cheese'
or type.fname = 'milk';

--запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select t.fname Type, count(p.fname)
from type t
join product p
on t.id = p.type_id
group by t.fname
having count(p.fname) < 10;

select product.fname Product, type.fname Type
from product
join type
on product.type_id = type.id;
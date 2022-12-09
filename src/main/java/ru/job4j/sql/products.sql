create table products (
    id serial primary key,
    fname varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace function discount()
    returns trigger as
$$
    begin
        update products
        set price = price - price * 0.2
        where count <= 5 and id = new.id;
        return new;
    end
$$
LANGUAGE 'plpgsql';

create trigger discount_trigger
    after insert
    on products
    for each row
    execute procedure discount();

insert into products (fname, producer, count, price)
values ('product_3', 'producer_3', 8, 115);

insert into products (fname, producer, count, price)
values ('product_1', 'producer_1', 3, 50);

alter table products disable trigger discount_trigger;
drop trigger discount_trigger on products;

insert into products (fname, producer, count, price)
values ('product_1', 'producer_1', 3, 50);

select * from products;

/*1. Триггер срабатывает после вставки данных, для любого товара и просто насчитывает налог на товар.
Прибавляет налог к цене товара. Действует не на каждый ряд, а на запрос (statement уровень)*/

create or replace function tax_after()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_after_trigger
    after insert
    on products
    referencing new table as inserted
    for each statement
    execute procedure tax_after();

--    drop trigger tax_after_trigger on products;

-- вставка для проверки триггера налога
insert into products (fname, producer, count, price)
values ('product_5', 'producer_5', 5, 100);

-- выборка для проверки триггера налога
select * from products;


/*2. Триггер срабатывает до вставки данных и насчитывает
налог на товар (прибавляет налог к цене товара). row уровень.*/

create or replace function tax_before()
    returns trigger as
$$
    begin
        new.price = new.price * 1.18;
        return new;
    end
$$
LANGUAGE 'plpgsql';

create trigger tax_before_trigger
    before insert
    on products
    for each row
    execute procedure tax_before();

--drop trigger tax_before on products;

-- вставка для проверки триггера налога
insert into products (fname, producer, count, price)
values ('product_4', 'producer_4', 4, 100);

-- выборка для проверки триггера налога
select * from products;

/*3. Триггер row уровня, который при вставке продукта в таблицу products,
заносит имя, цену и текущую дату в таблицу history_of_price.*/

create table if not exists history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

--drop table history_of_price;

create or replace function history_of_price()
    returns trigger as
$$
    BEGIN
        insert into history_of_price (name, price, date)
        values (new.fname, new.price, current_timestamp);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger history_of_price
    after insert
    on products
    for each row
    execute procedure history_of_price();

--drop trigger tax_before on products;

-- вставка для проверки триггера налога
insert into products (fname, producer, count, price)
values ('product_6', 'producer_6', 6, 100);

-- выборка для проверки записи истории
select * from products;
select * from history_of_price;
--delete from products where products.fname like 'product_6';
--select * from products where fname like 'product_6';

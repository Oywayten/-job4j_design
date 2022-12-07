create TABLE products (
    id SERIAL PRIMARY KEY,
    fname VARCHAR(50),
    producer VARCHAR(50),
    count INTEGER DEFAULT 0,
    price INTEGER
);

create trigger discount_trigger
    after insert
    on products
    for each row
    execute procedure discount();

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

insert into products (fname, producer, count, price)
values ('product_3', 'producer_3', 8, 115);

insert into products (fname, producer, count, price)
values ('product_1', 'producer_1', 3, 50);

alter table products disable trigger discount_trigger;
drop trigger discount_trigger on products;

select * from products;
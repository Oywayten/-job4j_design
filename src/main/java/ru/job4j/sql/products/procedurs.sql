-- ПРОЦЕДУРЫ
create or replace procedure
insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    begin
        insert into products (fname, producer, count, price)
        values(i_name, prod, i_count, i_price);
    end;
$$;

call insert_data('product_2', 'producer_2', 15, 32);

select * from products;

create or replace procedure
update_data(u_count integer, tax float, u_id integer)
language 'plpgsql'
as $$
    begin
        if u_count > 0 then
            update products
            set count = count - u_count where id = u_id;
            end if;
        if tax > 0 then update products
            set price = price + price * tax;
        end if;
    end
$$;

call update_data(0, 0, 11);

alter procedure update rename to update_data;

delete from products;
alter sequence products_id_seq restart with 1;

-- ФУНКЦИИ
create or replace function
f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void
language 'plpgsql'
as $$
    begin
        insert into products(fname, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end;
$$;

select f_insert_data('product_1', 'producer_1', 25, 50);

create or replace function
f_update_data(u_count integer, tax float, u_id integer)
returns integer
language 'plpgsql'
as $$
    declare
        result integer;
    begin
        if u_count > 0 then
            update products set count = count - u_count where id = u_id;
            select into result count from products where id = u_id;
        end if;
        if tax > 0 then
            update products set price = price + price * tax;
            select into result sum(price) from products;
        end if;
        return result;
    end;
$$;

select f_update_data(25, 0, 12);

-- Процедура и функция, которые удаляют записи таблицы, если count = 0.
create or replace procedure
delete_data()
language 'plpgsql'
as $$
    begin
        delete from products where count = 0;
    end
$$;

call delete_data();

create or replace function
f_delete_data()
returns integer
language 'plpgsql'
as $$
    declare
        result integer;
    begin
        select count(*) into result from products where count = 0;
        delete from products where count = 0;
        return result;
    end;
$$;

select f_delete_data();
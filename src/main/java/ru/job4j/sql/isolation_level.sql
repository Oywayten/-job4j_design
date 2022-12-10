create table emails (
id serial primary key,
email text
);

insert into emails(email) values
('email_1'),
('email_2'),
('email_3'),
('email_4');

-- Транзакция 1
begin transaction isolation level serializable;
select * from emails;
select sum(id) from emails;
update emails set email = 'email_22' where email = 'email_2';
insert into emails(email) values ('email_5');
commit;

--Транзакция 2
begin transaction isolation level serializable;
select * from emails;
select sum(id) from emails;
update emails set email = 'email_33' where email = 'email_3';
insert into emails(email) values ('email_6');
commit;


create table role(
    id serial primary key ,
    fname varchar(255)
)

create table users(
    id serial primary key ,
    fname varchar(255),
    id_role int references role(id)
)

create table rules(
    id serial primary key ,
    fname varchar(255)
)

create table role_rules(
    id serial primary key ,
    id_role int references role(id),
    id_rules int references rules(id)
)
create table category(
    id serial primary key ,
    fname varchar(255)
)

create table state(
    id serial primary key ,
    fname varchar(255)
)

create table item(
    id serial primary key ,
    fname varchar(255),
    ftext text,
    id_users int references users(id),
    id_category int references category(id),
    id_state int references state(id)
)

create table comments(
    id serial primary key ,
    ftext text,
    id_item int references item(id)
)

create table attachs(
    id serial primary key ,
    file varchar(255),
    id_item int references item(id)
)

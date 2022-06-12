create table pc(
    id serial primary key ,
    sn int
)

create table employee(
    id serial primary key ,
    fname varchar(255)
);

create table pc_employee(
    id serial primary key ,
    id_pc int references pc(id) unique ,
    id_employee int references employee(id) unique
);


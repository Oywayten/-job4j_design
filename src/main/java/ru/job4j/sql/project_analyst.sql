create table project(
    id serial primary key ,
    fname varchar(255)
);

create table analyst(
    id serial primary key ,
    fname varchar(255)
);

create table project_analyst(
    id serial primary key ,
    id_project int references project(id),
    id_analyst int references analyst(id)
);
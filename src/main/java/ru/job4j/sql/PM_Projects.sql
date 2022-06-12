create table pm(
    id serial primary key,
    fname varchar(255)
);

create table project(
    id serial primary key,
    fname varchar(255),
    pm_id int references pm(id)
);

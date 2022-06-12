create table pm(
    id serial primary key,
    fname varchar(255)
);

create table project(
    id serial primary key,
    fname varchar(255),
    pm_id varchar(255) references pm(id)
);

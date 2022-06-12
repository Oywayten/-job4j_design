create table students(
    id serial primary key,
    name_ varchar(255)
);

create table courses(
    id serial primary key,
    name_ varchar(255)
);

create table students_courses(
    id serial primary key,
    students_id int references students(id),
    courses_id int references courses(id)
);

insert into students(name_) values ('Ivan');
insert into students(name_) values ('Kirill');
insert into students(name_) values ('Roman');

insert into courses(name_) values ('Java SE');
insert into courses(name_) values ('Spring');
insert into courses(name_) values ('Hibernate');

insert into students_courses(students_id, courses_id) values (1, 1);
insert into students_courses(students_id, courses_id) values (1, 2);
insert into students_courses(students_id, courses_id) values (1, 3);
insert into students_courses(students_id, courses_id) values (2, 1);
insert into students_courses(students_id, courses_id) values (2, 2);
insert into students_courses(students_id, courses_id) values (2, 3);
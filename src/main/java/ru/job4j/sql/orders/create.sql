/*
drop view avg_authors_books_per_student;
drop view show_students_with_2_or_more_books;
drop table orders;
drop table students;
drop table books;
drop table authors;
*/

create table if not exists students (
    id serial primary key,
    name varchar(50)
);

insert into students (name)
    values
        ('Иван Иванов'),
        ('Петр Петрович');

select * from students;

create table if not exists authors (
    id serial primary key,
    name varchar(50)
);

insert into authors (name)
    values
        ('Александр Пушкин'),
        ('Николай Гоголь');

select * from authors;

create table if not exists books (
    id serial primary key,
    name varchar(50),
    author_id integer references authors(id)
);

insert into books (name, author_id) values ('Евгений Онегин', 1);
insert into books (name, author_id) values ('Капитанская дочка', 1);
insert into books (name, author_id) values ('Дубровский', 1);
insert into books (name, author_id) values ('Мертвые души', 2);
insert into books (name, author_id) values ('Вий', 2);

select * from books;

create table if not exists orders (
    id serial primary key,
    active boolean default true,
    book_id integer references books("id"),
    student_id integer references students(id)
);

insert into orders (book_id, student_id)
    values
        (1, 1),
        (2, 1),
        (3, 1),
        (5, 2),
        (4, 1),
        (2, 2);

select * from orders;

create view show_students_with_2_or_more_books as
    select s.name as student, count(a.name), a.name as author from students s
        join orders o on s.id = o.student_id
        join books b on o.book_id = b."id"
        join authors a on b.author_id = a."id"
        group  by (s.name, a.name) having count(a.name) >= 2;

select * from show_students_with_2_or_more_books;

/*alter view show_students_with_2_or_more_books
rename to show_students_with_equals_or_more_then_2_books;
*/

--студенты у которых среднее количество книг по авторам больше 2
create view avg_authors_books_per_student as
select student, avg(count_books) as av from(
select s.name as student, b.author_id as author, count(b.author_id) as count_books from students as s
join orders as o
    on s.id = o.student_id
join books as b
    on o.book_id = b.id
group by student, b.author_id) as foo group by student;


select * from avg_authors_books_per_student where av > 2;
--drop table movie;
--drop table book;

CREATE TABLE movie (
    id SERIAL PRIMARY KEY,
    name text,
    director text
);

CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    title text,
    author text
);


INSERT INTO movie (name, director)
VALUES ('Марсианин', 'Ридли Скотт'),
       ('Матрица', 'Братья Вачовски'),
       ('Властелин колец', 'Питер Джексон'),
       ('Гарри Поттер и узник Азкабана', 'Альфонсо Куарон'),
       ('Железный человек', 'Джон Фавро');

INSERT INTO book (title, author)
VALUES ('Гарри Поттер и узник Азкабана', 'Джоан Роулинг'),
       ('Властелин колец', 'Джон Толкин'),
       ('1984', 'Джордж Оруэлл'),
       ('Марсианин', 'Энди Уир'),
       ('Божественная комедия', 'Данте Алигьери');

-- Если поля name и title совпадают – то значит фильм бы снят, основываясь на книге.

-- 1. названия всех фильмов, которые сняты по книге

select name from movie
intersect
select title from book;

-- 2. все названия книг, у которых нет экранизации

select title from book
except
select name from movie;

-- 3. все уникальные названия произведений

select title from book
union
select name from movie
except
(select title from book
intersect
select name from movie);


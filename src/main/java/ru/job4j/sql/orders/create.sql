CREATE TABLE students (
    id serial PRIMARY KEY,
    fname varchar(50)
)

INSERT INTO students (fname)
    VALUES
        ('Иван Иванов'),
        ('Петр Петрович');

CREATE TABLE authors (
    id serial PRIMARY KEY,
    fname varchar(50)
)

INSERT INTO authors (fname)
    VALUES
        ('Александр Пушкин'),
        ('Николай Гоголь');

CREATE TABLE books (
    id serial PRIMARY KEY,
    fname varchar(50),
    author_id integer REFERENCES authors(id)
);

INSERT INTO books (fname, author_id) VALUES ('Евгений Онегин', 1);
INSERT INTO books (fname, author_id) VALUES ('Капитанская дочка', 1);
INSERT INTO books (fname, author_id) VALUES ('Дубровский', 1);
INSERT INTO books (fname, author_id) VALUES ('Мертвые души', 2);
INSERT INTO books (fname, author_id) VALUES ('Вий', 2);

CREATE TABLE orders (
    id serial PRIMARY KEY,
    active boolean DEFAULT TRUE,
    book_id integer REFERENCES books("id"),
    student_id integer REFERENCES students("id")
);

INSERT INTO orders (book_id, student_id)
    VALUES
        (1, 1),
        (3, 1),
        (5, 2),
        (4, 1),
        (2, 2);

CREATE VIEW show_students_with_2_or_more_books AS
    SELECT s.fname AS student, count(a.fname), a.fname AS author FROM students s
        JOIN orders o ON s.id = o.student_id
        JOIN books b ON o.book_id = b."id"
        JOIN authors a ON b.author_id = a."id"
        GROUP  BY (s.fname, a.fname) HAVING count(a.fname) >= 2;


SELECT * FROM show_students_with_2_or_more_books;

ALTER view show_students_with_2_or_more_books
rename to show_students_with_equals_or_more_then_2_books;

DROP VIEW show_students_with_equals_or_more_then_2_books;


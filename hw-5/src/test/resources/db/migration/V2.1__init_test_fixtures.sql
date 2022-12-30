// clear all tables from production fixtures and insert test fixtures
delete
from BOOKS_AUTHORS;
delete
from BOOKS_GENRES;
delete
from GENRES;
delete
from AUTHORS;
delete
from BOOKS;

// make autoincrement start with 1 again
ALTER TABLE genres
    ALTER COLUMN id RESTART WITH 1;
ALTER TABLE authors
    ALTER COLUMN id RESTART WITH 1;
ALTER TABLE books
    ALTER COLUMN id RESTART WITH 1;

insert into GENRES(NAME)
values ('genre1'),
       ('genre2'),
       ('genre3'),
       ('genre_without_book');


insert into AUTHORS(NAME)
values ('author1'),
       ('author2'),
       ('author3'),
       ('author_without_book');

insert into BOOKS(name)
values ('book1'),
       ('book2'),
       ('book3');

insert into BOOKS_AUTHORS(book_id, author_id)
values (1, 1),
       (1, 2),
       (2, 2),
       (2, 3),
       (3, 1),
       (3, 3);

insert into BOOKS_GENRES(book_id, genre_id)
values (1, 1),
       (1, 2),
       (2, 2),
       (2, 3),
       (3, 1),
       (3, 3);

drop table if exists authors;
drop table if exists genres;
drop table if exists books;


create table authors
(
    id   bigserial primary key,
    name varchar(255) not null
);
create table genres
(
    id   bigserial primary key,
    name varchar(255) not null
);
create table books
(
    id   bigserial primary key,
    name varchar(255) not null
);

create table commentaries
(
    id      bigserial primary key,
    text    varchar(255) not null,
    book_id bigint references books (id) on delete cascade
);

create table books_authors
(
    book_id   bigint references books (id) on delete cascade,
    author_id bigint references authors (id),
    primary key (book_id, author_id)
);

create table books_genres
(
    book_id  bigint references books (id) on delete cascade,
    genre_id bigint references genres (id),
    primary key (book_id, genre_id)
);

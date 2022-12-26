drop table if exists authors;
drop table if exists genres;
drop table if exists books;


create table authors
(
    id   bigint auto_increment primary key,
    name varchar(255) not null
);
create table genres
(
    id   bigint auto_increment primary key,
    name varchar(255) not null
);
create table books
(
    id        bigint auto_increment primary key,
    name      varchar(255) not null,
    author_id bigint,
    foreign key (author_id) references authors (id) on delete cascade,
    genre_id  bigint,
    foreign key (genre_id) references genres (id) on delete cascade
);

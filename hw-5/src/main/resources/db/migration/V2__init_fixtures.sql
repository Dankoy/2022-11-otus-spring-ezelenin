insert into GENRES(NAME)
values ('genre1'),
       ('genre2'),
       ('genre3');


insert into AUTHORS(NAME)
values ('author1'),
       ('author2'),
       ('author3');

insert into BOOKS(name, author_id, genre_id)
values ('book1', 1, 1),
       ('book2', 2, 1),
       ('book3', 2, 3);
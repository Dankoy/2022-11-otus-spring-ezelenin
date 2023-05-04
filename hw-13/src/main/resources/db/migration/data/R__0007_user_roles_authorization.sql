insert into roles(role)
values ('ROLE_ADMIN'),
       ('ROLE_READER'),
       ('ROLE_OPERATOR');

insert into users_roles(user_id, role_id)
values (1, 1),
       (2, 2),
       (3, 3);
create table roles
(
    id      bigserial primary key,
    role    varchar(50) not null
);

create table users_roles
(
    user_id bigint references users (id) on delete cascade,
    role_id bigint references roles (id) on delete restrict,
    primary key (user_id, role_id)
);


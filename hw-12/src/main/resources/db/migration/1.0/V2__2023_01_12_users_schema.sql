create table users
(
    id                      bigserial primary key,
    username                varchar(50)  not null,
    password                varchar(500) not null,
    enabled                 boolean      not null,
    account_non_locked      boolean      not null,
    account_non_expired     boolean      not null,
    credentials_non_expired boolean      not null
);

create table authorities
(
    id        bigserial primary key,
    authority varchar_ignorecase(50) not null,
    user_id   bigserial              not null,
    constraint fk_authorities_users foreign key (user_id) references users (id)
);

insert into users(username,
                  password,
                  enabled,
                  account_non_locked,
                  account_non_expired,
                  credentials_non_expired)
values ('user',
        '$2a$10$Jj7yBaQGAt4IKHmRfVyGiOoB6qEZeRcFQ2GV4cZugQkJmFBBMRllO', -- mysuperpassword
        true,
        true,
        true,
        true);

insert into authorities(authority,
                        user_id)
values ('ROLE_ADMIN',
        1);
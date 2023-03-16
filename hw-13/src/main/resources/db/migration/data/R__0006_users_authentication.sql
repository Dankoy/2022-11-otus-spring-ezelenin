insert into users(username,
                  password,
                  enabled,
                  account_non_locked,
                  account_non_expired,
                  credentials_non_expired)
values ('admin',
        '$2a$10$TWU4IJ6sZhHeKKNtznMqe.7AqaCRESc68LhExRCs.frwpv.i8uvsW', -- pass
        true,
        true,
        true,
        true),
       ('reader',
        '$2a$10$TWU4IJ6sZhHeKKNtznMqe.7AqaCRESc68LhExRCs.frwpv.i8uvsW', -- pass
        true,
        true,
        true,
        true),
       ('operator',
        '$2a$10$TWU4IJ6sZhHeKKNtznMqe.7AqaCRESc68LhExRCs.frwpv.i8uvsW', -- pass
        true,
        true,
        true,
        true);

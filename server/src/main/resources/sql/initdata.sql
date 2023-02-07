INSERT INTO roles (id, name) values (1, 'ROLE_ADMIN'),
                                   (2, 'ROLE_USER')
                                    ON CONFLICT DO NOTHING;

INSERT INTO users (email, email_confirmed, password) values ('admin@admin', true, '$2a$12$.IPUQiOUrt2M1N8ceg3Pz.MvQdt3GX2ZFxZtxzi7HGzbK6lf2mOH6') ON CONFLICT DO NOTHING;

insert into user_roles (role_id, user_id) values (1, (select id from users where email = 'admin@admin')) ON CONFLICT DO NOTHING;
insert into user_roles (role_id, user_id) values (2, (select id from users where email = 'admin@admin')) ON CONFLICT DO NOTHING;

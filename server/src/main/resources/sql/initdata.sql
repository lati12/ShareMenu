INSERT INTO roles (id, name) values (1, 'ROLE_ADMIN'),
                                   (2, 'ROLE_USER')
                                    ON CONFLICT DO NOTHING;

INSERT INTO users (email, email_confirmed, password) values ('admin@admin', true, '$2a$12$.IPUQiOUrt2M1N8ceg3Pz.MvQdt3GX2ZFxZtxzi7HGzbK6lf2mOH6') ON CONFLICT DO NOTHING;

insert into user_roles (role_id, user_id) values (1, (select id from users where email = 'admin@admin')) ON CONFLICT DO NOTHING;
insert into user_roles (role_id, user_id) values (2, (select id from users where email = 'admin@admin')) ON CONFLICT DO NOTHING;

INSERT INTO users (name, lastname, email, companyname, email_confirmed, password) values ('Latin', 'Nenchev', 'latin.nenchev@gmail.com', 'LatinOOD', true
, '$2a$12$.IPUQiOUrt2M1N8ceg3Pz.MvQdt3GX2ZFxZtxzi7HGzbK6lf2mOH6');
insert into user_roles (role_id, user_id) values (2, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;

INSERT INTO users (name, lastname, email, companyname, email_confirmed, password) values ('Yuri', 'Rusanov', 'yuriRusanov@gmail.com', 'yuriOOD', true
, '$2a$12$.IPUQiOUrt2M1N8ceg3Pz.MvQdt3GX2ZFxZtxzi7HGzbK6lf2mOH6');
insert into user_roles (role_id, user_id) values (2, (select id from users where email = 'yuriRusanov@gmail.com')) ON CONFLICT DO NOTHING;

INSERT INTO users (name, lastname, email, companyname, email_confirmed, password) values ('Martin', 'Petrov', 'martinpetrov@gmail.com', 'petrovOOD', true
, '$2a$12$.IPUQiOUrt2M1N8ceg3Pz.MvQdt3GX2ZFxZtxzi7HGzbK6lf2mOH6');
insert into user_roles (role_id, user_id) values (2, (select id from users where email = 'martinpetrov@gmail.com')) ON CONFLICT DO NOTHING;

INSERT INTO itemcategory (name, position, users_id) VALUES ('Супи', 1
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO itemcategory (name, position, users_id) VALUES ('Скара', 2
, (select id from users where email = 'yuriRusanov@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO itemcategory (name, position, users_id) VALUES ('Пици', 3
, (select id from users where email = 'martinpetrov@gmail.com')) ON CONFLICT DO NOTHING;

INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('пилешко месо', 'Пилешка супа', 12.23, 'gr'
, (select id from itemcategory where name = 'Супи')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Свинска пържола', 'Пържола', 2.23, 'gr'
, (select id from itemcategory where name = 'Скара')
, (select id from users where email = 'yuriRusanov@gmail.com')) ON CONFLICT DO NOTHING;

INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Шунка и кашкавал', 'Хам', 22.23, 'gr'
, (select id from itemcategory where name = 'Пици')
, (select id from users where email = 'martinpetrov@gmail.com')) ON CONFLICT DO NOTHING;

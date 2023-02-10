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
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO itemcategory (name, position, users_id) VALUES ('Пици', 3
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO itemcategory (name, position, users_id) VALUES ('Салати', 4
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO itemcategory (name, position, users_id) VALUES ('Сладолед', 5
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;


INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Пилешко месо', 'Пилешка супа', 12.23, 'гр'
, (select id from itemcategory where name = 'Супи')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Шкембе', 'Шкембе чорба', 12.23, 'гр'
, (select id from itemcategory where name = 'Супи')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Кисело мляко и краставици', 'Таратор', 12.23, 'гр'
, (select id from itemcategory where name = 'Супи')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;




INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Свинско месо', 'Свинска пържола', 2.23, 'гр'
, (select id from itemcategory where name = 'Скара')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Телешко месо', 'Телешка пържола', 2.23, 'гр'
, (select id from itemcategory where name = 'Скара')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Пилешко месо', 'Пилешка пържола', 2.23, 'гр'
, (select id from itemcategory where name = 'Скара')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Кайма', 'Кебапче', 2.23, 'гр'
, (select id from itemcategory where name = 'Скара')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Кайма', 'Кюфте', 2.23, 'гр'
, (select id from itemcategory where name = 'Скара')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;


INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Шунка и кашкавал', 'Пица Хам', 22.23, 'гр'
, (select id from itemcategory where name = 'Пици')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('доматен сос, моцарела, пармезан, босилек', 'Пица Маргарита', 22.23, 'гр'
, (select id from itemcategory where name = 'Пици')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('доматен сос, топено сирене, ементал, бяло сирене, синьо сирене, кашкавал','Пица Четири сирена', 22.23, 'гр'
, (select id from itemcategory where name = 'Пици')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('доматен сос, шунка, кашкавал, ананас','Пица Хавай', 22.23, 'гр'
, (select id from itemcategory where name = 'Пици')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('сметана, шунка, топено сирене, чедър, гъби, кашкавал',' Пица Бяла', 22.23, 'гр'
, (select id from itemcategory where name = 'Пици')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;


INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('домати,краставици,лук,чушка,сирене,риган', 'Селска салата', 12.23, 'гр'
, (select id from itemcategory where name = 'Салати')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('домати, краставици, чушки, сирене, лук', 'Редена салата', 12.23, 'гр'
, (select id from itemcategory where name = 'Салати')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('цедено мляко, краставици, чесън, орехи, маслини', 'Млечна салата', 12.23, 'гр'
, (select id from itemcategory where name = 'Салати')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('домати,краставици,лук,чушка,сирене,маслини', 'Шопска салата', 12.23, 'гр'
, (select id from itemcategory where name = 'Салати')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('маруля, крутони, пармезан, лимонов сок, яйце, черен пипер', 'Салата Цезар', 12.23, 'гр'
, (select id from itemcategory where name = 'Салати')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;

INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Шоколод и сметана','Шоколадов сладолед', 22.23, 'гр'
, (select id from itemcategory where name = 'Сладолед')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Шоколод,сметана, парченца ягоди','Шоколадов сладолед с ягоди', 22.23, 'гр'
, (select id from itemcategory where name = 'Сладолед')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Плодове и сметана','Плодов сладолед', 22.23, 'гр'
, (select id from itemcategory where name = 'Сладолед')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Йогурт и сметана','Йогурт сладолед', 22.23, 'гр'
, (select id from itemcategory where name = 'Сладолед')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Бискивтки Орео и сметана','Орео сладолед', 22.23, 'гр'
, (select id from itemcategory where name = 'Сладолед')
, (select id from users where email = 'latin.nenchev@gmail.com')) ON CONFLICT DO NOTHING;




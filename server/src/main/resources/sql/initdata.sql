INSERT INTO roles (id, name) values (1, 'ROLE_ADMIN'),
                                   (2, 'ROLE_USER')
                                    ON CONFLICT DO NOTHING;



INSERT INTO users (name, lastname, email, companyname, email_confirmed, password) values (
                                                                                          'Admin'
                                                                                          ,'Admin'
                                                                                          ,'testadmin1@test'
                                                                                          ,'AdminCompany'
                                                                                          , true
                                                                                          , '$2a$12$LM..4Uu7EbHyQweA920B8e44TpVSxsFDR4V37WMXhCl6E8hEMY1py'
                                                                                          ) ON CONFLICT DO NOTHING;

insert into user_roles (role_id, user_id) values (1, (select id from users where email = 'testadmin1@test')) ON CONFLICT DO NOTHING;
insert into user_roles (role_id, user_id) values (2, (select id from users where email = 'testadmin1@test')) ON CONFLICT DO NOTHING;



INSERT INTO users (name, lastname, email, companyname, email_confirmed, password) values ('testuser1@test', 'testuser1@test', 'testuser1@test', 'testuser1@test', true
, '$2a$12$e8gTpyTsHKxoJayziSd20eZvMDCAcbBLG5Mm7HXKoXdCa/WUQzIXq');
insert into user_roles (role_id, user_id) values (2, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;

INSERT INTO itemcategory (name, position, users_id) VALUES ('Супи', 1
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;
INSERT INTO itemcategory (name, position, users_id) VALUES ('Скара', 2
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;
INSERT INTO itemcategory (name, position, users_id) VALUES ('Пици', 3
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;
INSERT INTO itemcategory (name, position, users_id) VALUES ('Салати', 4
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;
INSERT INTO itemcategory (name, position, users_id) VALUES ('Десерти', 5
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;


INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Пилешко месо', 'Пилешка супа', 12.23, 'гр'
, (select id from itemcategory where name = 'Супи')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Шкембе', 'Шкембе чорба', 12.23, 'гр'
, (select id from itemcategory where name = 'Супи')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Кисело мляко и краставици', 'Таратор', 12.23, 'гр'
, (select id from itemcategory where name = 'Супи')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;


INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Свинско месо', 'Свинска пържола', 2.23, 'гр'
, (select id from itemcategory where name = 'Скара')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Телешко месо', 'Телешка пържола', 2.23, 'гр'
, (select id from itemcategory where name = 'Скара')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Пилешко месо', 'Пилешка пържола', 2.23, 'гр'
, (select id from itemcategory where name = 'Скара')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Кайма', 'Кебапче', 2.23, 'гр'
, (select id from itemcategory where name = 'Скара')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Кайма', 'Кюфте', 2.23, 'гр'
, (select id from itemcategory where name = 'Скара')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;


INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Шунка и кашкавал', 'Пица Хам', 22.23, 'гр'
, (select id from itemcategory where name = 'Пици')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('доматен сос, моцарела, пармезан, босилек', 'Пица Маргарита', 22.23, 'гр'
, (select id from itemcategory where name = 'Пици')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('доматен сос, топено сирене, ементал, бяло сирене, синьо сирене, кашкавал','Пица Четири сирена', 22.23, 'гр'
, (select id from itemcategory where name = 'Пици')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('доматен сос, шунка, кашкавал, ананас','Пица Хавай', 22.23, 'гр'
, (select id from itemcategory where name = 'Пици')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('сметана, шунка, топено сирене, чедър, гъби, кашкавал',' Пица Бяла', 22.23, 'гр'
, (select id from itemcategory where name = 'Пици')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;


INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('домати,краставици,лук,чушка,сирене,риган', 'Селска салата', 12.23, 'гр'
, (select id from itemcategory where name = 'Салати')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('домати, краставици, чушки, сирене, лук', 'Редена салата', 12.23, 'гр'
, (select id from itemcategory where name = 'Салати')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('цедено мляко, краставици, чесън, орехи, маслини', 'Млечна салата', 12.23, 'гр'
, (select id from itemcategory where name = 'Салати')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('домати,краставици,лук,чушка,сирене,маслини', 'Шопска салата', 12.23, 'гр'
, (select id from itemcategory where name = 'Салати')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('маруля, крутони, пармезан, лимонов сок, яйце, черен пипер', 'Салата Цезар', 12.23, 'гр'
, (select id from itemcategory where name = 'Салати')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;

INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Шоколод и сметана','Шоколадов сладолед', 22.23, 'гр'
, (select id from itemcategory where name = 'Десерти')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Шоколод,сметана, парченца ягоди','Шоколадов сладолед с ягоди', 22.23, 'гр'
, (select id from itemcategory where name = 'Десерти')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Плодове и сметана','Плодов сладолед', 22.23, 'гр'
, (select id from itemcategory where name = 'Десерти')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Йогурт и сметана','Йогурт сладолед', 22.23, 'гр'
, (select id from itemcategory where name = 'Десерти')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;
INSERT INTO item (description, name, price, unit, itemcategory_id, users_id) VALUES ('Бискивтки Орео и сметана','Орео сладолед', 22.23, 'гр'
, (select id from itemcategory where name = 'Десерти')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;


--insert into template (name, file, users_id) values ('temp', pg_read_file('C:\projects\ShareMenu\sharemenu.jrxml')::bytea, 1) ON CONFLICT DO NOTHING;
insert into template (name, file, users_id) values ('temp', pg_read_file('/opt/sharemenu.jrxml')::bytea, 1) ON CONFLICT DO NOTHING;

insert into user_template(user_id, template_id) values ((select id from users where email = 'testuser1@test')
, (select id from template where name = 'temp')) ON CONFLICT DO NOTHING;

insert into entityheader (address, city, country, email, name, phone, templates_id, users_id) values ('бул. Васил Кънчов', 'гр. Враца', 'България', 'retro@abv.bg', 'гостилница Ретро', '089 351 7233'
, (select id from template where name = 'temp')
, (select id from users where email = 'testuser1@test')) ON CONFLICT DO NOTHING;

insert into entityline (price, entityheader_id, item_id) values (9
, (select id from entityheader where name = 'гостилница Ретро')
, (select id from item where name = 'Пица Хам')) ON CONFLICT DO NOTHING;
insert into entityline (price, entityheader_id, item_id) values (12
, (select id from entityheader where name = 'гостилница Ретро')
, (select id from item where name = 'Пица Маргарита')) ON CONFLICT DO NOTHING;
insert into entityline (price, entityheader_id, item_id) values (2.2
, (select id from entityheader where name = 'гостилница Ретро')
, (select id from item where name = 'Пилешка супа')) ON CONFLICT DO NOTHING;
insert into entityline (price, entityheader_id, item_id) values (2
, (select id from entityheader where name = 'гостилница Ретро')
, (select id from item where name = 'Таратор')) ON CONFLICT DO NOTHING;
insert into entityline (price, entityheader_id, item_id) values (7
, (select id from entityheader where name = 'гостилница Ретро')
, (select id from item where name = 'Селска салата')) ON CONFLICT DO NOTHING;
insert into entityline (price, entityheader_id, item_id) values (8
, (select id from entityheader where name = 'гостилница Ретро')
, (select id from item where name = 'Шопска салата')) ON CONFLICT DO NOTHING;
insert into entityline (price, entityheader_id, item_id) values (9
, (select id from entityheader where name = 'гостилница Ретро')
, (select id from item where name = 'Шоколадов сладолед')) ON CONFLICT DO NOTHING;
insert into entityline (price, entityheader_id, item_id) values (9
, (select id from entityheader where name = 'гостилница Ретро')
, (select id from item where name = 'Плодов сладолед')) ON CONFLICT DO NOTHING;


insert into socialnetworkconnectivity (name, access_token, app_id, secret_id,users_id, key) values ('ShareMenu',
                                                                                                    'EAAJ15yJArJABAB33XJViVq4wT71T01f8kHJ7SjvI8ZAf6hZAWOmxvTZAlB9SiQLpbSJj1XTMY7JVbdh05i0OZC2woCb1mAk1qOQKNRo2TuZCdpPnm5WZBOTcRc2BFlEdXJyHRtShkBtccJ5CGN2gfPFGoVlxFCQtxeGZAgQclQF4E4fLFLbnuobq6W69ZA3HgpkZD',
                                                                                                    '692585525980304', 'ced0b6ad67e41cab01e0d9487fc44eef',
                                                                                                    (select id from users where email = 'testuser1@test'),
                                                                                                    '103325612689885')




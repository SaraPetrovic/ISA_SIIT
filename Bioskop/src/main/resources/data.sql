insert into adresa(id, city, street) values ('6', 'asd', 'dsa')
insert into adresa(id, city, street) values ('7', 'asd', 'dsa')

insert into user(username, password, usertype, address_id, email, firstname, lastname, is_first_login, profile_picture) values ('admin', '1234', 'SYSTEMADMIN', '6', 'asd', 'ime', 'prezime', false, 'default-profile-picture.jpg')
insert into user(username, password, usertype, address_id, email, firstname, lastname, is_first_login, profile_picture) values ('ftn', 'ftn', 'SYSTEMADMIN', '7', 'asd', 'ime', 'prezime', true, 'default-profile-picture.jpg')

insert into thematic_item( name, price, description, quantity ,version, picture, is_official) values ( 'majica', '1000', 'opis', 1 ,0, 'item1.jpg', true)
insert into thematic_item( name, price, description, quantity ,version, picture, is_official) values ( 'kapa', '500', 'opis', 1 ,0, 'item.jpg', true)
insert into thematic_item( name, price, description, quantity ,version, picture, is_official) values ( 'duks', '700', 'opis', 1 ,0, 'item.jpg', false)

insert into item_offer(user_id, price, item_id) values ('1', '500', '1')
insert into item_offer(user_id, price, item_id) values ('2', '500', '1')
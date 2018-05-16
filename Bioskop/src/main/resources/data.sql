insert into adresa(id, city, street) values ('6', 'asd', 'dsa')
insert into adresa(id, city, street) values ('7', 'asd', 'dsa')
insert into adresa(id, city, street) values ('8', 'grad3', 'ulica3')
insert into adresa(id, city, street) values ('9', 'grad4', 'ulica4')

insert into user(username, password, usertype, address_id, email, firstname, lastname, is_first_login, profile_picture) values ('admin', '1234', 'SYSTEMADMIN', '6', 'asd@gmail.com', 'Marko', 'Markovic', false, 'default-profile-picture.jpg')
insert into user(username, password, usertype, address_id, email, firstname, lastname, is_first_login, profile_picture) values ('ftn', 'ftn', 'SYSTEMADMIN', '7', 'asd@gmail.com', 'Pera', 'Peric', true, 'default-profile-picture.jpg')
insert into user(username, password, usertype, address_id, email, firstname, lastname, is_first_login, profile_picture) values ('reguser', '123123123', 'REGISTEREDUSER', '8', 'reg@gmail.com', 'Nikola', 'Nikolic', false, 'default-profile-picture.jpg')
insert into user(username, password, usertype, address_id, email, firstname, lastname, is_first_login, profile_picture) values ('reguser2', '123123123', 'REGISTEREDUSER', '9', 'reg2@gmail.com', 'Nemanja', 'Nemanjic', false, 'default-profile-picture.jpg')
insert into user(username, password, usertype, address_id, email, firstname, lastname, is_first_login, profile_picture) values ('sara', 'sara', 'CINEMAADMIN', '8', 'sarapetrovic03@gmail.com', 'Sara', 'Petrovic', true, 'default-profile-picture.jpg')

insert into thematic_item( name, price, description, quantity ,version, picture, is_official) values ( 'majica', '1000', 'opis', 5 ,0, 'item1.jpg', false)
insert into thematic_item( name, price, description, quantity ,version, picture, is_official) values ( 'kapa', '500', 'opis', 5 ,0, 'item.jpg', false)
insert into thematic_item( name, price, description, quantity ,version, picture, is_official) values ( 'duks', '700', 'opis', 1 ,0, 'item2.jpg', false)
insert into thematic_item( name, price, description, quantity ,version, picture, is_official) values ( 'duks', '700', 'opis', 1 ,0, 'item2.jpg', true)

insert into item_reservation (id, user_id, item_id) values ('1' , '1', '1')
insert into item_offer(user_id, price, item_id) values ('1', '500', '2')
insert into item_offer(user_id, price, item_id) values ('2', '500', '3')

insert into isa.friendship(userid1, userid2, action_userid, status) values (1, 2, 1, 0)
insert into isa.friendship(userid1, userid2, action_userid, status) values (2, 3, 3, 0)
insert into isa.friendship(userid1, userid2, action_userid, status) values (1, 3, 1, 0)


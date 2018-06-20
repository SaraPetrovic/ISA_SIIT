insert into adresa(id, city, street) values ('6', 'Novi Sad', 'Jevrejska 30')
insert into adresa(id, city, street) values ('7', 'Novi Sad', 'Jevrejska 10')
insert into adresa(id, city, street) values ('8', 'Novi Sad', 'Kralja Petra I 1')
insert into adresa(id, city, street) values ('9', 'Novi Sad', 'Futoska 10')
insert into adresa(id, city, street) values ('10', 'Novi Sad', 'Bulevar Oslobodjenja 25')
insert into adresa(id, city, street) values ('11', 'Novi Sad', 'Bulevar Oslobodjenja 66')
insert into adresa(id, city, street) values ('12', 'Novi Sad', 'Danila Kisa 24')
insert into adresa(id, city, street) values ('13', 'Novi Sad', 'Danila Kisa 13')
insert into adresa(id, city, street) values ('14', 'Novi Sad', 'Tolstojeva 15')
insert into adresa(id, city, street) values ('15', 'Novi Sad', 'Modene 20')
insert into adresa(id, city, street) values ('16', 'Novi Sad', 'Futoska 140')


insert into user(username, password, usertype, address_id, email, firstname, lastname, is_first_login, profile_picture, activated) values ('admin', '1234', 'SYSTEMADMIN', '6', 'asd@gmail.com', 'Marko', 'Markovic', false, 'default-profile-picture.jpg', true)
insert into user(username, password, usertype, address_id, email, firstname, lastname, is_first_login, profile_picture, activated) values ('ftn', 'ftn', 'REGISTEREDUSER', '7', 'srbulovicdusan@gmail.com', 'Pera', 'Peric', true, 'default-profile-picture.jpg', true)
insert into user(username, password, usertype, address_id, email, firstname, lastname, is_first_login, profile_picture, activated) values ('reguser', '123123123', 'REGISTEREDUSER', '10', 'reg@gmail.com', 'Nikola', 'Nikolic', false, 'default-profile-picture.jpg', true)
insert into user(username, password, usertype, address_id, email, firstname, lastname, is_first_login, profile_picture, activated) values ('reguser2', '123123123', 'REGISTEREDUSER', '9', 'reg2@gmail.com', 'Nemanja', 'Nemanjic', false, 'default-profile-picture.jpg', true)
insert into user(username, password, usertype, address_id, email, firstname, lastname, is_first_login, profile_picture, activated) values ('sara', 'sara', 'CINEMAADMIN', '11', 'sarapetrovic03@gmail.com', 'Sara', 'Petrovic', true, 'default-profile-picture.jpg', true)
insert into user(username, password, usertype, address_id, email, firstname, lastname, is_first_login, profile_picture, activated) values ('reguser3', '123123123', 'REGISTEREDUSER', '13', 'reg@gmail.com', 'Mara', 'Maric', false, 'default-profile-picture.jpg', true)
insert into user(username, password, usertype, address_id, email, firstname, lastname, is_first_login, profile_picture, activated) values ('reguser4', '123123123', 'REGISTEREDUSER', '14', 'reg@gmail.com', 'Ana', 'Anic', false, 'default-profile-picture.jpg', true)
insert into user(username, password, usertype, address_id, email, firstname, lastname, is_first_login, profile_picture, activated) values ('fan', 'fan', 'FANZONEADMIN', '16', 'srbulovicdusan@gmail.com', 'Pera', 'Peric', true, 'default-profile-picture.jpg', true)



insert into item_ad( name, description ,version, owner_id, picture, approved, expiry_date) values ( 'majica', 'opis' ,0,2 , 'item1.jpg', true, '2018-06-12')
insert into item_ad( name, description ,version, owner_id, picture, approved, expiry_date) values ( 'kapa', 'opis' ,0, 2,'item.jpg', true, '2018-06-12')
insert into item_ad( name, description ,version, owner_id, picture, approved, expiry_date) values ( 'duks', 'opis' ,0, 2,'item2.jpg', true, '2018-06-12')
insert into item_ad( name, description ,version, owner_id, picture, approved, expiry_date) values ( 'duks', 'opis' ,0, 2,'item2.jpg', false, '2018-06-12')
insert into official_item( name, price,description,version, quantity, picture) values ( 'duks', '700', 'opis', 0 ,2, 'item2.jpg')

insert into item_reservation (id, user_id, official_item_id) values ('1' , '1', '1')
insert into item_offer(user_id, price, item_ad_id, version) values ('1', '500', '2', '0')
insert into item_offer(user_id, price, item_ad_id, version) values ('3', '700', '2', '0')
insert into item_offer(user_id, price, item_ad_id, version) values ('2', '500', '3', '0')

insert into isa.friendship(userid1, userid2, action_userid, status) values (2, 3, 3, 2)
insert into isa.friendship(userid1, userid2, action_userid, status) values (1, 3, 1, 2)
insert into isa.friendship(userid1, userid2, action_userid, status) values (1, 7, 1, 2)
insert into isa.friendship(userid1, userid2, action_userid, status) values (2, 7, 2, 2)
insert into isa.friendship(userid1, userid2, action_userid, status) values (3, 7, 3, 2)
insert into isa.friendship(userid1, userid2, action_userid, status) values (6, 7, 6, 2)



insert into membership(bronze_min, bronze_max, silver_min, silver_max, gold_min, gold_max) values (0, 20, 21, 80, 81, 100)

insert into theater_or_cinema(id,average_mark, description, is_cinema, name, adress_id) values ('5', '4', 'Opis...', true, 'Cinema1', '10')
insert into theater_or_cinema(id, average_mark, description, is_cinema, name, adress_id) values ('2', '5', 'Opis2...', false, 'Theater1', '12')
insert into theater_or_cinema(id, average_mark, description, is_cinema, name, adress_id) values ('3', '2', 'Opis3...', false, 'Theater2', '8')
insert into theater_or_cinema(id, average_mark, description, is_cinema, name, adress_id) values ('4', '5', 'Opis4...', true, 'Cinema2', '16')

insert into hall(id, max_column, max_row, name, theater_or_cinema_id) values ('1', '12', '12', 'Hall1', '5')
insert into hall(id, max_column, max_row, name, theater_or_cinema_id) values ('2', '10', '12', 'Hall2', '2')
insert into hall(id, max_column, max_row, name, theater_or_cinema_id) values ('3', '11', '12', 'Hall3', '3')
insert into hall(id, max_column, max_row, name, theater_or_cinema_id) values ('4', '24', '30', 'Hall4', '5')
insert into hall(id, max_column, max_row, name, theater_or_cinema_id) values ('5', '7', '7', 'Hall5', '4')

insert into movie_or_performance(id, actors, average_rating, description, film_duration, img, is_film, name, producer, type) values ('1', 'Dennis Quaid, Michael Finley', 4.1, 'Description...', '100', 'picture', true, 'I can only imagine', 'Jon Erwin', 'religious')
insert into movie_or_performance(id, actors, average_rating, description, film_duration, img, is_film, name, producer, type) values ('2', 'Dan Ewing, Temuera Morrison', 3.6, 'Description...', '139', 'picture', true, 'Occupation', 'Luke Sparke, Felix Williamsn', 'action')

insert into projection(name, date, price, hall_id, movie_or_performance_id, theater_or_cinema_id) values ('I can only imagine 16.06.2018. 18.00', '16.06.2018.', 350.00, 1, 1, 4)  
insert into projection(name, date, price, hall_id, movie_or_performance_id, theater_or_cinema_id) values ('I can only imagine 16.06.2018. 22.00', '16.06.2018.', 350.00, 1, 1, 4)  
insert into projection(name, date, price, hall_id, movie_or_performance_id, theater_or_cinema_id) values ('Occupation 16.06.2018. 18.00', '16.06.2018.', 350.00, 2, 2, 4)  
insert into projection(name, date, price, hall_id, movie_or_performance_id, theater_or_cinema_id) values ('Occupation 16.06.2018. 21.00', '16.06.2018.', 350.00, 2, 2, 4)  
insert into projection(name, date, price, hall_id, movie_or_performance_id, theater_or_cinema_id) values ('Occupation 16.06.2018. 15.00', '16.06.2018.', 350.00, 3, 2, 4)  
insert into theater_or_cinema_projections(theater_or_cinema_id, projections_id) values (4, 1)
insert into theater_or_cinema_projections(theater_or_cinema_id, projections_id) values (4, 2)
insert into theater_or_cinema_projections(theater_or_cinema_id, projections_id) values (4, 3)
insert into theater_or_cinema_projections(theater_or_cinema_id, projections_id) values (4, 4)
insert into theater_or_cinema_projections(theater_or_cinema_id, projections_id) values (4, 5)

insert into ticket(fast_ticket, kolona, new_price, red, reserved, version, projection_id, user_id) values (false, 1, 500.00, 1, true, '0', 1, 3)
insert into projection_tickets(projection_id, tickets_id) values (1, 1)

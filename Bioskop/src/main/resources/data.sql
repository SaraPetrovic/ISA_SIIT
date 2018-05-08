insert into adresa(id, city, street) values ('6', 'asd', 'dsa')
insert into adresa(id, city, street) values ('7', 'asd', 'dsa')

insert into user(username, password, usertype, address_id, email, firstname, lastname, is_first_login) values ('admin', '1234', 'SYSTEMADMIN', '6', 'asd', 'admin', 'admin', false)
insert into user(username, password, usertype, address_id, email, firstname, lastname, is_first_login) values ('ftn', 'ftn', 'SYSTEMADMIN', '7', 'asd', 'admin', 'admin', true)

insert into thematic_item( name, price, description) values ( 'majica', '1000', 'opis')
insert into item_offer(user_id, price, item_id) values ('1', '500', '1')
insert into item_offer(user_id, price, item_id) values ('2', '500', '1')
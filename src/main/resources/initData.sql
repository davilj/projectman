--init data for system
delete from authorities;
delete from person;

INSERT INTO person(id, city, country, state, street_name, street_number, zip_code, email, first_name, last_name, "password", "version")
VALUES (1,'Pretoria', 'South Africa', 'Gauteng', 'zervista', 5, 0049, 'danie.viljoen@gmail.com','Danie','Viljoen','r3dp3pp3r',0);

INSERT INTO person(id, city, country, state, street_name, street_number, zip_code, email, first_name, last_name, "password", "version")
VALUES (2,'Blier', 'BCity', 'Polor1', 'na', 5, 0000, 'admin@gmail.com','Admin','System','r3dp3pp3r',0);

INSERT INTO person(id, city, country, state, street_name, street_number, zip_code, email, first_name, last_name, "password", "version")
VALUES (3,'Test', 'Test', 'Test', 'test', 5, 0000, 't@t.com','test','aTest','test',0);


INSERT INTO authorities(id, authority, username, "version")
VALUES (1,'ROLE_ADMIN', 'danie.viljoen@gmail.com', 0);

INSERT INTO authorities(id, authority, username, "version")
VALUES (2,'ROLE_ADMIN', 'admin@gmail.com', 0);

INSERT INTO authorities(id, authority, username, "version")
VALUES (3,'ROLE_ADMIN', 't@t.com', 0);
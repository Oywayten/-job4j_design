insert into role (fname) values ('admin');
insert into role (fname) values ('user');
insert into role (fname) values ('anonym');

insert into users (fname, id_role) values ('Ivan', 1);
insert into users (fname, id_role) values ('Petr', 2);
insert into users (fname, id_role) values ('Vasiliy', 3);

insert into rules (fname) values ('rule1');
insert into rules (fname) values ('rule2');
insert into rules (fname) values ('rule3');

insert into role_rules (id_role, id_rules) values (1, 1);
insert into role_rules (id_role, id_rules) values (1, 2);
insert into role_rules (id_role, id_rules) values (1, 3);
insert into role_rules (id_role, id_rules) values (2, 1);
insert into role_rules (id_role, id_rules) values (2, 2);
insert into role_rules (id_role, id_rules) values (2, 3);
insert into role_rules (id_role, id_rules) values (3, 1);
insert into role_rules (id_role, id_rules) values (3, 2);
insert into role_rules (id_role, id_rules) values (3, 3);

insert into category (fname) values ('high');
insert into category (fname) values ('middle');
insert into category (fname) values ('low');

insert into state (fname) values ('draft');
insert into state (fname) values ('open');
insert into state (fname) values ('closed');

insert into item (fname, ftext, id_users, id_category, id_state) values ('Notification', 'We inform you about an important event', 2, 1, 2);
insert into item (fname, ftext, id_users, id_category, id_state) values ('Notification', 'Ready to take a Java course', 1, 2, 3);
insert into item (fname, ftext, id_users, id_category, id_state) values ('Notification','Office furniture will arrive tomorrow', 3, 3, 1);

insert into comments (ftext, id_item) values ('no comments', 1);
insert into comments (ftext, id_item) values ('Prefer online', 2);
insert into comments (ftext, id_item) values ('They need a pass', 3);

insert into attachs (file, id_item) values ('txt.txt', 1);
insert into attachs (file, id_item) values ('word.word', 2);
insert into attachs (file, id_item) values ('excell.xlsx', 3);
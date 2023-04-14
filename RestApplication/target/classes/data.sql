insert into user_details (id, name, dob) values (1001, 'abc', current_date());
insert into user_details (id, name, dob) values (1002, 'def', current_date());
insert into user_details (id, name, dob) values (1003, 'ijk', current_date());
insert into user_details (id, name, dob) values (1004, 'lmn', current_date());

insert into post (id, description, user_id) values (2001, 'post 1 user 1', 1001);
insert into post (id, description, user_id) values (2002, 'post 2 user 1', 1001);
insert into post (id, description, user_id) values (2003, 'post 1 user 2', 1002);
insert into post (id, description, user_id) values (2004, 'post 2 user 2', 1002);
insert into post (id, description, user_id) values (2005, 'post 1 user 4', 1004);
insert into post (id, description, user_id) values (2006, 'post 1 user 3', 1003);
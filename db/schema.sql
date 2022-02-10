insert into authority (authority) values ('ROLE_USER');
insert into authority (authority) values ('ROLE_ADMIN');
insert into users (id, name, password, enabled, authority_id)
values (1, 'admin', '12345', true, 2)
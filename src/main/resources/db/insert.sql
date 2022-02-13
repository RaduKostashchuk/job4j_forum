insert into post (name, author) values ('IntelliJ IDEA hot key', 'jundev');
insert into post (name, author) values ('Spring boot question', 'newbee');

insert into authority (name) values ('ROLE_ADMIN');
insert into authority (name) values ('ROLE_USER');

insert into users (name, password, authority_id)
    values ('admin', '$2a$10$DElH1zBU.EJpqa0cqfrCu.qQkiVZ0WWvi.1R66.9hVzBaJs0rBtki',
        (select id from authority where name = 'ROLE_ADMIN'));
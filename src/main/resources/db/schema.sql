create table if not exists post (
  id serial primary key,
  name varchar(50),
  author varchar(50),
  created timestamp without time zone not null default now()
);

create table if not exists authority (
  id serial primary key,
  name varchar(50)
);

create table if not exists users (
  id serial primary key,
  name varchar(50) not null,
  password varchar(100) not null,
  enabled boolean not null default true,
  authority_id int not null references authority(id)
);
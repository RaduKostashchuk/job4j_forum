create table if not exists post (
  id serial primary key,
  name varchar(50),
  author varchar(50),
  created timestamp without time zone not null default now()
);

create table if not exists comment (
  id serial primary key,
  content varchar(2000),
  author varchar(50),
  created timestamp without time zone not null default now()
);
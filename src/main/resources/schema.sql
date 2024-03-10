create table if not exists users
(
    id           int auto_increment not null primary key,
    name         varchar(45)        not null,
    surname      varchar(45)        not null,
    age          int                not null,
    email        varchar(55)        not null,
    password     varchar(45)        not null,
    avatar       varchar(255),
    accType varchar(45)        not null
);

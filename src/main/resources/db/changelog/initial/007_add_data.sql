--liquibase formatted sql
--changeset Abu:add_new_data

insert into CATEGORIES(name, parent_id) VALUES ( 'Bio Engineer',null )



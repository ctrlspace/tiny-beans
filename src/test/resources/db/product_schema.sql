drop table  if exists orders_items;
drop table  if exists orders;
drop table  if exists item;
drop table  if exists product;
create table product
(
    id          bigint not null auto_increment,
    description varchar(255),
    name        varchar(255),
    photo_url   varchar(255),
    price       double precision,
    primary key (id)
);
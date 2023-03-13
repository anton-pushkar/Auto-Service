--liquibase formatted sql
--changeset <postgres>:<create-goods-table>
create table if not exists public.goods
(
    id   bigserial
    primary key,
    cost double precision not null,
    name varchar(255)
    );

--rollback DROP TABLE goods;
--liquibase formatted sql
--changelog<postgres>:<create-cars-table>
create table if not exists public.cars
(
    id     bigserial
    primary key,
    brand  varchar(255),
    model  varchar(255),
    number varchar(255),
    year   integer not null
    );
--rollback DROP TABLE cars;
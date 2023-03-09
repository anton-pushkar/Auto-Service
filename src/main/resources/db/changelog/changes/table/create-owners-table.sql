--liquibase formatted sql
--changelog<postgres>:<create-orders-table>
create table if not exists public.owners
(
    id bigserial
    primary key
);



--rollback DROP TABLE owners;
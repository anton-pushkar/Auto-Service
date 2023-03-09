--liquibase formatted sql
--changelog<postgres>:<create-masters-table>
create table if not exists public.masters
(
    id   bigserial
    primary key,
    name varchar(255)
    );

--rollback DROP TABLE masters;
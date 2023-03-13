--liquibase formatted sql
--changeset <postgres>:<create-cars-owners-table>
create table if not exists public.cars_owners
(
    owner_id bigint
    constraint fkdsr6yvlam2phlakwjhe61b5fa
    references public.owners,
    car_id   bigint not null
    primary key
    constraint fk3gauoytvn9r1i5nou7q2yjcrn
    references public.cars
);
--rollback DROP TABLE cars_owners;
--liquibase formatted sql
--changeset <postgres>:<create-orders-table>
create table if not exists public.orders
(
    id                  bigserial
    primary key,
    cost                numeric(19, 2) not null,
    finished_time       timestamp(6),
    description varchar(255),
    start_time          timestamp(6),
    status              varchar(255),
    car_id              bigint
    constraint fkd2p23ixwrrt395glgi9nnbj23
    references public.cars,
    owner_id           bigint
    constraint fkmmheqr23cmh7344m5rl0ftjnc
    references public.owners
    );

--rollback DROP TABLE orders;
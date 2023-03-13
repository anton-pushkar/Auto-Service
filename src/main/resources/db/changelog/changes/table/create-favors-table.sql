--liquibase formatted sql
--changeset <postgres>:<create-favors-table>
create table if not exists public.favors
(
    id            bigserial
    primary key,
    cost          double precision not null,
    master_status varchar(255),
    master_id     bigint
    constraint fkr59vnppfqqaouem7gv1u12h71
    references public.masters,
    order_id      bigint
    constraint fk688cm1ytwxghpacwh1uypj3ic
    references public.orders
    );

--rollback DROP TABLE favors;
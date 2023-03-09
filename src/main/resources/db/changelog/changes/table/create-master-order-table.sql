--liquibase formatted sql
--changelog<postgres>:<create-master-order-table>
create table if not exists public.master_order
(
    master_id bigint not null
    constraint fk7makrxnj9xvytqytxamfid1q
    references public.masters,
    order_id  bigint not null
    constraint uk_meslmt6ouiak5u0gb0y5k155t
    unique
    constraint fk3wy1b8gmagoeet3gxowhnrn37
    references public.orders
);

--rollback DROP TABLE master_order;
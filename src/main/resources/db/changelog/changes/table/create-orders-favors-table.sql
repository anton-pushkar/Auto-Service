--liquibase formatted sql
--changelog<postgres>:<create-orders-favors-table>
create table if not exists public.orders_favors
(
    order_id bigint not null
    constraint fko9spydycjeglw36hjm8i3hm2d
    references public.orders,
    favor_id bigint not null
    constraint fkl07xufhe0r3f3uavxa7werue8
    references public.favors
);


--rollback DROP TABLE orders_favors;
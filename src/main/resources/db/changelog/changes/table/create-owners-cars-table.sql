--liquibase formatted sql
--changelog<postgres>:<create-orders-table>
create table if not exists public.owners_cars
(
    owner_id bigint not null
    constraint fkl3bgvt7natjt1rydg5avnmhcd
    references public.owners,
    car_id   bigint not null
    constraint fkobosrw1pt1tmgeqeftq01ldae
    references public.cars
);
--rollback DROP TABLE owners_cars;
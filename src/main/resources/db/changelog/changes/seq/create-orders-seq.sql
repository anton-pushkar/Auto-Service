--liquibase formatted sql
--changelog<postgres>:<orders-cars-seq>
CREATE SEQUENCE IF NOT EXISTS public.orders_id_seq INCREMENT 1 start 1 minvalue 1;

--rollback drop SEQUENCE public.orders_id_seq;
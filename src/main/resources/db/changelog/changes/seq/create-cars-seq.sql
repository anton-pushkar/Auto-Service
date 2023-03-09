--liquibase formatted sql
--changelog<postgres>:<create-cars-seq>
CREATE SEQUENCE IF NOT EXISTS public.cars_id_seq INCREMENT 1 start 1 minvalue 1;

--rollback drop SEQUENCE public.cars_id_seq;
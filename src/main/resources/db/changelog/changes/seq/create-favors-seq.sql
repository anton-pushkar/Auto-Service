--liquibase formatted sql
--changelog<postgres>:<create-favors-seq>
CREATE SEQUENCE IF NOT EXISTS public.favors_id_seq INCREMENT 1 start 1 minvalue 1;

--rollback drop SEQUENCE public.favors_id_seq;
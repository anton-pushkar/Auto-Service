--liquibase formatted sql
--changeset <postgres>:<create-masters-seq>
CREATE SEQUENCE IF NOT EXISTS public.masters_id_seq INCREMENT 1 start 1 minvalue 1;

--rollback drop SEQUENCE public.masters_id_seq;
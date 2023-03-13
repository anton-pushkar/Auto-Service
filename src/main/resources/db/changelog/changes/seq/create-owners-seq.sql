--liquibase formatted sql
--changeset <postgres>:<create-owners-seq>
CREATE SEQUENCE IF NOT EXISTS public.owners_id_seq INCREMENT 1 start 1 minvalue 1;

--rollback drop SEQUENCE public.owners_id_seq;
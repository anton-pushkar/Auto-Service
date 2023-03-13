--liquibase formatted sql
--changeset <postgres>:<create-goods-seq>
CREATE SEQUENCE IF NOT EXISTS public.goods_id_seq INCREMENT 1 start 1 minvalue 1;

--rollback drop SEQUENCE public.goods_id_seq;
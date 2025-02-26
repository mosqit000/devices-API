CREATE USER postgres;

CREATE TABLE IF NOT EXISTS public.device
(
    id                  serial           primary key,
    name                varchar          not null,
    brand          		varchar          not null,
    devicestate        		varchar          not null,
    creation_time       timestamp         
);

CREATE SEQUENCE public.DEVICE_SEQ;

GRANT ALL ON ALL SEQUENCES IN SCHEMA public TO amer;
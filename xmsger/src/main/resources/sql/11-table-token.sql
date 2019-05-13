-- Table: public.token

-- DROP TABLE public.token;

CREATE TABLE public.token
(
    site text COLLATE pg_catalog."default" NOT NULL,
    token text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT token_pkey PRIMARY KEY (site)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.token
    OWNER to xmsger;
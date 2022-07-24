CREATE TABLE IF NOT EXISTS public.movies
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    media_type character varying(255) COLLATE pg_catalog."default",
    overview character varying(255) COLLATE pg_catalog."default",
    popularity character varying(255) COLLATE pg_catalog."default",
    poster_path character varying(255) COLLATE pg_catalog."default",
    release_date timestamp without time zone,
    title character varying(255) COLLATE pg_catalog."default",
    video boolean,
    vote_average bigint,
    vote_count bigint,
    CONSTRAINT movies_pkey PRIMARY KEY (id)
    )
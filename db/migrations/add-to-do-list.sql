CREATE TABLE public.to_do_list
(
  id bigint NOT NULL DEFAULT nextval('to_do_list_id_sequence'::regclass),
  text character varying,
  user_id bigint,
  done boolean DEFAULT false,
  priority_id bigint,
  due_date timestamp without time zone,
  create_date timestamp without time zone DEFAULT now(),
  modification_date timestamp without time zone,
  title character varying(256),
  CONSTRAINT to_do_list_pkey PRIMARY KEY (id),
  CONSTRAINT to_do_list_user_fk FOREIGN KEY (user_id)
  REFERENCES public.system_user (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
OIDS=FALSE
);
ALTER TABLE public.to_do_list
  OWNER TO postgres;
GRANT ALL ON TABLE public.to_do_list TO postgres;
-- GRANT SELECT ON TABLE public.to_do_list TO omrs;
GRANT ALL ON TABLE public.to_do_list TO public;
--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 13.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: day; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.day AS ENUM (
    'MONDAY',
    'TUESDAY',
    'WEDNESDAY',
    'THURSDAY',
    'FRIDAY',
    'SATURDAY',
    'SUNDAY'
);


ALTER TYPE public.day OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: animal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.animal (
    id bigint NOT NULL,
    breed text NOT NULL,
    species text NOT NULL
);


ALTER TABLE public.animal OWNER TO postgres;

--
-- Name: animal_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.animal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.animal_id_seq OWNER TO postgres;

--
-- Name: animal_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.animal_id_seq OWNED BY public.animal.id;


--
-- Name: appointment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.appointment (
    id bigint NOT NULL,
    date date NOT NULL,
    hour time without time zone NOT NULL,
    notes text,
    id_surgery integer NOT NULL,
    id_medic integer NOT NULL,
    id_client integer NOT NULL,
    id_animal integer NOT NULL
);


ALTER TABLE public.appointment OWNER TO postgres;

--
-- Name: appointment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.appointment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.appointment_id_seq OWNER TO postgres;

--
-- Name: appointment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.appointment_id_seq OWNED BY public.appointment.id;


--
-- Name: client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.client (
    id bigint NOT NULL,
    first_name text NOT NULL,
    last_name text NOT NULL,
    tin text NOT NULL,
    phone_number text NOT NULL,
    email text NOT NULL
);


ALTER TABLE public.client OWNER TO postgres;

--
-- Name: client_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.client_id_seq OWNER TO postgres;

--
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.client_id_seq OWNED BY public.client.id;


--
-- Name: medic; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.medic (
    id bigint NOT NULL,
    username text NOT NULL,
    password text NOT NULL,
    first_name text NOT NULL,
    last_name text NOT NULL,
    email text NOT NULL,
    phone_number text NOT NULL
);


ALTER TABLE public.medic OWNER TO postgres;

--
-- Name: medic_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.medic_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.medic_id_seq OWNER TO postgres;

--
-- Name: medic_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.medic_id_seq OWNED BY public.medic.id;


--
-- Name: schedule; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.schedule (
    id bigint NOT NULL,
    start_hour time without time zone NOT NULL,
    end_hour time without time zone NOT NULL,
    id_medic integer NOT NULL,
    day public.day NOT NULL
);


ALTER TABLE public.schedule OWNER TO postgres;

--
-- Name: schedule_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.schedule_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.schedule_id_seq OWNER TO postgres;

--
-- Name: schedule_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.schedule_id_seq OWNED BY public.schedule.id;


--
-- Name: surgery; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.surgery (
    id bigint NOT NULL,
    name text NOT NULL,
    "time" time without time zone NOT NULL,
    minimum_price numeric NOT NULL
);


ALTER TABLE public.surgery OWNER TO postgres;

--
-- Name: surgery_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.surgery_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.surgery_id_seq OWNER TO postgres;

--
-- Name: surgery_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.surgery_id_seq OWNED BY public.surgery.id;


--
-- Name: animal id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animal ALTER COLUMN id SET DEFAULT nextval('public.animal_id_seq'::regclass);


--
-- Name: appointment id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointment ALTER COLUMN id SET DEFAULT nextval('public.appointment_id_seq'::regclass);


--
-- Name: client id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client ALTER COLUMN id SET DEFAULT nextval('public.client_id_seq'::regclass);


--
-- Name: medic id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medic ALTER COLUMN id SET DEFAULT nextval('public.medic_id_seq'::regclass);


--
-- Name: schedule id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.schedule ALTER COLUMN id SET DEFAULT nextval('public.schedule_id_seq'::regclass);


--
-- Name: surgery id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.surgery ALTER COLUMN id SET DEFAULT nextval('public.surgery_id_seq'::regclass);


--
-- Data for Name: animal; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.animal (id, breed, species) VALUES (0, 'Border Collie', 'Canine');
INSERT INTO public.animal (id, breed, species) VALUES (1, 'Shiba Inu', 'Canine');
INSERT INTO public.animal (id, breed, species) VALUES (2, 'Angora', 'Feline');
INSERT INTO public.animal (id, breed, species) VALUES (3, 'Akita Inu', 'Canine');
INSERT INTO public.animal (id, breed, species) VALUES (4, 'Pomeranian', 'Canine');
INSERT INTO public.animal (id, breed, species) VALUES (5, 'Golden Retriver', 'Canine');
INSERT INTO public.animal (id, breed, species) VALUES (6, 'Scottish Fold', 'Feline');
INSERT INTO public.animal (id, breed, species) VALUES (7, 'Sphynx ', 'Feline');
INSERT INTO public.animal (id, breed, species) VALUES (8, 'Bengaleza', 'Feline');
INSERT INTO public.animal (id, breed, species) VALUES (9, 'Savannah', 'Feline');
INSERT INTO public.animal (id, breed, species) VALUES (10, 'Mixt', 'Canine');
INSERT INTO public.animal (id, breed, species) VALUES (11, 'Mixt', 'Feline');

--
-- Data for Name: medic; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.medic (id, username, password, first_name, last_name, email, phone_number) VALUES (1, 'Paul
', 'WiWy1eJ85pF80Ge4TnIu2yi9UePR7nVMMP9b/Bg/2+E=', 'Doru', 'Paul', 'paul.doru@gmail.com', '0729999999');
INSERT INTO public.medic (id, username, password, first_name, last_name, email, phone_number) VALUES (2, 'Mihai', 'kPyjnL1ZED7GYCbhjS39JETUbTb+FSKyXOHkV3ci1Aw=', 'Mihai', 'Popescu', 'mihai_popescu@yahoo.com', '0781111111');
INSERT INTO public.medic (id, username, password, first_name, last_name, email, phone_number) VALUES (3, 'rody', 'quoA0/WmP5hstWvaeVJPhk915u2tedrMIKQIbRG95HM=', 'Rodica', 'Popescu', 'rodica_popescu@gmail.com', '0766666666');


--
-- Data for Name: schedule; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.schedule (id, start_hour, end_hour, id_medic, day) VALUES (8, '09:00:00', '12:00:00', 1, 'MONDAY');
INSERT INTO public.schedule (id, start_hour, end_hour, id_medic, day) VALUES (9, '13:00:00', '18:00:00', 1, 'MONDAY');
INSERT INTO public.schedule (id, start_hour, end_hour, id_medic, day) VALUES (12, '09:00:00', '12:00:00', 1, 'TUESDAY');
INSERT INTO public.schedule (id, start_hour, end_hour, id_medic, day) VALUES (13, '13:00:00', '18:00:00', 1, 'TUESDAY');
INSERT INTO public.schedule (id, start_hour, end_hour, id_medic, day) VALUES (14, '09:00:00', '12:00:00', 1, 'WEDNESDAY');
INSERT INTO public.schedule (id, start_hour, end_hour, id_medic, day) VALUES (15, '13:00:00', '18:00:00', 1, 'WEDNESDAY');
INSERT INTO public.schedule (id, start_hour, end_hour, id_medic, day) VALUES (16, '09:00:00', '12:00:00', 1, 'THURSDAY');
INSERT INTO public.schedule (id, start_hour, end_hour, id_medic, day) VALUES (17, '13:00:00', '18:00:00', 1, 'THURSDAY');
INSERT INTO public.schedule (id, start_hour, end_hour, id_medic, day) VALUES (18, '09:00:00', '12:00:00', 1, 'FRIDAY');
INSERT INTO public.schedule (id, start_hour, end_hour, id_medic, day) VALUES (19, '13:00:00', '18:00:00', 1, 'FRIDAY');
INSERT INTO public.schedule (id, start_hour, end_hour, id_medic, day) VALUES (20, '10:00:00', '12:00:00', 1, 'SATURDAY');
INSERT INTO public.schedule (id, start_hour, end_hour, id_medic, day) VALUES (21, '13:00:00', '15:00:00', 1, 'SATURDAY');
INSERT INTO public.schedule (id, start_hour, end_hour, id_medic, day) VALUES (22, '12:00:00', '14:00:00', 1, 'SUNDAY');
INSERT INTO public.schedule (id, start_hour, end_hour, id_medic, day) VALUES (23, '09:00:00', '12:00:00', 2, 'SATURDAY');
INSERT INTO public.schedule (id, start_hour, end_hour, id_medic, day) VALUES (24, '13:00:00', '18:00:00', 2, 'SATURDAY');
INSERT INTO public.schedule (id, start_hour, end_hour, id_medic, day) VALUES (25, '09:00:00', '12:00:00', 2, 'SUNDAY');
INSERT INTO public.schedule (id, start_hour, end_hour, id_medic, day) VALUES (26, '13:00:00', '18:00:00', 2, 'SUNDAY');
INSERT INTO public.schedule (id, start_hour, end_hour, id_medic, day) VALUES (27, '18:00:00', '23:00:00', 3, 'MONDAY');
INSERT INTO public.schedule (id, start_hour, end_hour, id_medic, day) VALUES (28, '18:00:00', '23:00:00', 3, 'TUESDAY');
INSERT INTO public.schedule (id, start_hour, end_hour, id_medic, day) VALUES (29, '18:00:00', '23:00:00', 3, 'WEDNESDAY');
INSERT INTO public.schedule (id, start_hour, end_hour, id_medic, day) VALUES (30, '18:00:00', '23:00:00', 3, 'THURSDAY');
INSERT INTO public.schedule (id, start_hour, end_hour, id_medic, day) VALUES (31, '18:00:00', '23:00:00', 3, 'FRIDAY');


--
-- Data for Name: surgery; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.surgery (id, name, "time", minimum_price) VALUES (1, 'Consultație ortopedică', '01:00:00', 50);
INSERT INTO public.surgery (id, name, "time", minimum_price) VALUES (3, 'Consultație oftalmologică', '01:00:00', 50);
INSERT INTO public.surgery (id, name, "time", minimum_price) VALUES (4, 'Consultație nutrițională', '01:00:00', 50);
INSERT INTO public.surgery (id, name, "time", minimum_price) VALUES (5, 'Consultație obstetricală', '01:00:00', 50);
INSERT INTO public.surgery (id, name, "time", minimum_price) VALUES (6, 'Consultație oncologică', '01:00:00', 50);
INSERT INTO public.surgery (id, name, "time", minimum_price) VALUES (7, 'Tratament injectabil subcutanat, intramuscular, intravenos', '02:00:00', 50);
INSERT INTO public.surgery (id, name, "time", minimum_price) VALUES (8, 'Tratamente cutanate', '02:00:00', 50);
INSERT INTO public.surgery (id, name, "time", minimum_price) VALUES (9, 'Tratamente auriculare', '02:00:00', 50);
INSERT INTO public.surgery (id, name, "time", minimum_price) VALUES (10, 'Tratamente oculare', '02:00:00', 50);
INSERT INTO public.surgery (id, name, "time", minimum_price) VALUES (11, 'Vaccinare', '01:00:00', 50);
INSERT INTO public.surgery (id, name, "time", minimum_price) VALUES (2, 'Consultație neurologică', '03:00:00', 50);
INSERT INTO public.surgery (id, name, "time", minimum_price) VALUES (12, 'Plăgi cutanate, arsuri', '01:00:00', 50);


--
-- Name: animal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.animal_id_seq', 11, true);


--
-- Name: appointment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.appointment_id_seq', 54, true);


--
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.client_id_seq', 40, true);


--
-- Name: medic_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.medic_id_seq', 3, true);


--
-- Name: schedule_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.schedule_id_seq', 31, true);


--
-- Name: surgery_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.surgery_id_seq', 12, true);


--
-- Name: animal animal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_pkey PRIMARY KEY (id);


--
-- Name: appointment appointment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT appointment_pkey PRIMARY KEY (id);


--
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- Name: medic medic_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medic
    ADD CONSTRAINT medic_pkey PRIMARY KEY (id);


--
-- Name: schedule schedule_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.schedule
    ADD CONSTRAINT schedule_pkey PRIMARY KEY (id);


--
-- Name: surgery surgery_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.surgery
    ADD CONSTRAINT surgery_pkey PRIMARY KEY (id);


--
-- Name: appointment appointment_animal_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT appointment_animal_fkey FOREIGN KEY (id_animal) REFERENCES public.animal(id) NOT VALID;


--
-- Name: appointment appointment_client_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT appointment_client_fkey FOREIGN KEY (id_client) REFERENCES public.client(id) NOT VALID;


--
-- Name: appointment appointment_medic; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT appointment_medic FOREIGN KEY (id_medic) REFERENCES public.medic(id) NOT VALID;


--
-- Name: appointment appointment_surgery_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT appointment_surgery_fkey FOREIGN KEY (id_surgery) REFERENCES public.surgery(id) NOT VALID;


--
-- Name: schedule schedule_medic_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.schedule
    ADD CONSTRAINT schedule_medic_fkey FOREIGN KEY (id_medic) REFERENCES public.medic(id) NOT VALID;


--
-- PostgreSQL database dump complete
--


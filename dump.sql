--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: cart_good; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cart_good (
    cart_id bigint NOT NULL,
    good_id bigint NOT NULL
);


ALTER TABLE public.cart_good OWNER TO postgres;

--
-- Name: carts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.carts (
    id bigint NOT NULL,
    date date,
    customer_id bigint
);


ALTER TABLE public.carts OWNER TO postgres;

--
-- Name: carts_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.carts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.carts_id_seq OWNER TO postgres;

--
-- Name: carts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.carts_id_seq OWNED BY public.carts.id;


--
-- Name: customers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customers (
    id bigint NOT NULL,
    first_name character varying(255),
    last_name character varying(255)
);


ALTER TABLE public.customers OWNER TO postgres;

--
-- Name: customers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customers_id_seq OWNER TO postgres;

--
-- Name: customers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customers_id_seq OWNED BY public.customers.id;


--
-- Name: goods; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.goods (
    id bigint NOT NULL,
    name character varying(255),
    price numeric(19,2)
);


ALTER TABLE public.goods OWNER TO postgres;

--
-- Name: goods_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.goods_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.goods_id_seq OWNER TO postgres;

--
-- Name: goods_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.goods_id_seq OWNED BY public.goods.id;


--
-- Name: carts id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.carts ALTER COLUMN id SET DEFAULT nextval('public.carts_id_seq'::regclass);


--
-- Name: customers id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers ALTER COLUMN id SET DEFAULT nextval('public.customers_id_seq'::regclass);


--
-- Name: goods id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.goods ALTER COLUMN id SET DEFAULT nextval('public.goods_id_seq'::regclass);


--
-- Data for Name: cart_good; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cart_good VALUES (1, 1);
INSERT INTO public.cart_good VALUES (1, 4);
INSERT INTO public.cart_good VALUES (2, 2);
INSERT INTO public.cart_good VALUES (2, 4);
INSERT INTO public.cart_good VALUES (3, 2);
INSERT INTO public.cart_good VALUES (3, 1);
INSERT INTO public.cart_good VALUES (3, 3);
INSERT INTO public.cart_good VALUES (4, 4);
INSERT INTO public.cart_good VALUES (4, 4);
INSERT INTO public.cart_good VALUES (4, 4);
INSERT INTO public.cart_good VALUES (4, 4);
INSERT INTO public.cart_good VALUES (5, 1);
INSERT INTO public.cart_good VALUES (5, 1);
INSERT INTO public.cart_good VALUES (5, 1);
INSERT INTO public.cart_good VALUES (6, 1);
INSERT INTO public.cart_good VALUES (6, 2);


--
-- Data for Name: carts; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.carts VALUES (1, '2020-03-15', 1);
INSERT INTO public.carts VALUES (2, '2020-03-11', 1);
INSERT INTO public.carts VALUES (3, '2020-03-13', 2);
INSERT INTO public.carts VALUES (4, '2020-03-12', 3);
INSERT INTO public.carts VALUES (5, '2020-03-16', 3);
INSERT INTO public.carts VALUES (6, '2020-03-05', 3);


--
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.customers VALUES (1, 'Сергей', 'Ионов');
INSERT INTO public.customers VALUES (2, 'Анатолий', 'Жуков');
INSERT INTO public.customers VALUES (3, 'Иоанн', 'Сухоруков');


--
-- Data for Name: goods; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.goods VALUES (1, 'Хлеб', 2.90);
INSERT INTO public.goods VALUES (2, 'Соль', 3.70);
INSERT INTO public.goods VALUES (3, 'Сахар', 10.40);
INSERT INTO public.goods VALUES (4, 'Масло', 14.20);


--
-- Name: carts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.carts_id_seq', 6, true);


--
-- Name: customers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customers_id_seq', 3, true);


--
-- Name: goods_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.goods_id_seq', 4, true);


--
-- Name: carts carts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.carts
    ADD CONSTRAINT carts_pkey PRIMARY KEY (id);


--
-- Name: customers customers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);


--
-- Name: goods goods_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.goods
    ADD CONSTRAINT goods_pkey PRIMARY KEY (id);


--
-- Name: cart_good fk44l1m50x95fy4nxsyvg65hgfh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart_good
    ADD CONSTRAINT fk44l1m50x95fy4nxsyvg65hgfh FOREIGN KEY (good_id) REFERENCES public.goods(id);


--
-- Name: carts fk8ba3sryid5k8a9kidpkvqipyt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.carts
    ADD CONSTRAINT fk8ba3sryid5k8a9kidpkvqipyt FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: cart_good fkf55ci0o6ms4n0056j8d55va92; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart_good
    ADD CONSTRAINT fkf55ci0o6ms4n0056j8d55va92 FOREIGN KEY (cart_id) REFERENCES public.carts(id);


--
-- PostgreSQL database dump complete
--


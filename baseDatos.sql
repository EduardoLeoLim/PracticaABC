PGDMP     +    $                y           PracticaABC    13.4    13.4 	    ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ?           1262    16399    PracticaABC    DATABASE     j   CREATE DATABASE "PracticaABC" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Mexico.1252';
    DROP DATABASE "PracticaABC";
                postgres    false            ?            1259    16400 
   estudiante    TABLE     ?   CREATE TABLE public.estudiante (
    "idEstudiante" integer NOT NULL,
    "primerApellido" text,
    "primerNombre" text,
    "segundoApellido" text,
    "segundoNombre" text,
    activo boolean
);
    DROP TABLE public.estudiante;
       public         heap    postgres    false            ?            1259    16408    estudiante_idEstudiante_seq    SEQUENCE     ?   ALTER TABLE public.estudiante ALTER COLUMN "idEstudiante" ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."estudiante_idEstudiante_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    200            ?          0    16400 
   estudiante 
   TABLE DATA           ?   COPY public.estudiante ("idEstudiante", "primerApellido", "primerNombre", "segundoApellido", "segundoNombre", activo) FROM stdin;
    public          postgres    false    200   m	       ?           0    0    estudiante_idEstudiante_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public."estudiante_idEstudiante_seq"', 3, true);
          public          postgres    false    201            $           2606    16407    estudiante estudiante_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.estudiante
    ADD CONSTRAINT estudiante_pkey PRIMARY KEY ("idEstudiante");
 D   ALTER TABLE ONLY public.estudiante DROP CONSTRAINT estudiante_pkey;
       public            postgres    false    200            ?   O   x?3?LOKG?i\????E%????Vq:楧?p???+?d?r???&??s?ps'?%g U???sr%?? ?c???? z??     
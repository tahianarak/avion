CREATE TABLE type_siege(
   id_type_siege serial,
   description VARCHAR(255) ,
   PRIMARY KEY(id_type_siege)
);

CREATE TABLE modele_avion(
   id_modele_avion serial,
   description VARCHAR(255) ,
   date_fabrication VARCHAR(50) ,
   PRIMARY KEY(id_modele_avion)
);

CREATE TABLE ville_desservie(
   id_ville serial,
   descritpion VARCHAR(255) ,
   PRIMARY KEY(id_ville)
);

CREATE TABLE users(
   id_user serial,
   nom_complet VARCHAR(255) ,
   email VARCHAR(255) ,
   status BOOLEAN,
   mdp VARCHAR(255) ,
   adresse VARCHAR(255) ,
   PRIMARY KEY(id_user)
);

CREATE TABLE avion(
   id_avion serial,
   id_modele_avion INTEGER NOT NULL,
   PRIMARY KEY(id_avion),
   FOREIGN KEY(id_modele_avion) REFERENCES modele_avion(id_modele_avion)
);

CREATE TABLE vol(
   id_vol serial,
   date_vol TIMESTAMP,
   description VARCHAR(50) ,
   id_ville_depart INTEGER NOT NULL,
   id_ville_arrivee INTEGER NOT NULL,
   id_avion INTEGER NOT NULL,
   duree TIME,
   PRIMARY KEY(id_vol),
   FOREIGN KEY(id_ville_depart) REFERENCES ville_desservie(id_ville),
   FOREIGN KEY(id_avion) REFERENCES avion(id_avion),
   FOREIGN KEY(id_ville_arrivee) REFERENCES ville_desservie(id_ville)
);

CREATE TABLE reservation(
   id_reservation serial,
   date_reservation TIMESTAMP,
   id_user INTEGER NOT NULL,
   id_vol INTEGER NOT NULL,
   id_type_siege INTEGER NOT NULL,
   prix_billet NUMERIC(15,2),
   PRIMARY KEY(id_reservation),
   FOREIGN KEY(id_user) REFERENCES users(id_user),
   FOREIGN KEY(id_type_siege) REFERENCES type_siege(id_type_siege),
   FOREIGN KEY(id_vol) REFERENCES vol(id_vol)
);

alter table reservation add column nb_place INTEGER;
alter table reservation add column place_en_promotion INTEGER;
alter table reservation add column remise NUMERIC(10,2);
alter table reservation add column prix_unitaire NUMERIC(10,2);


CREATE TABLE modele_siege(
   id_type_siege INTEGER,
   id_modele_avion INTEGER,
   nombre_siege INTEGER,
   PRIMARY KEY(id_type_siege, id_modele_avion),
   FOREIGN KEY(id_type_siege) REFERENCES type_siege(id_type_siege),
   FOREIGN KEY(id_modele_avion) REFERENCES modele_avion(id_modele_avion)
);

CREATE TABLE vol_prix_type_siege(
   id_type_siege INTEGER,
   id_vol INTEGER,
   prix_unitaire NUMERIC(15,2)  ,
   PRIMARY KEY(id_type_siege, id_vol),
   FOREIGN KEY(id_type_siege) REFERENCES type_siege(id_type_siege),
   FOREIGN KEY(id_vol) REFERENCES vol(id_vol)
);


CREATE table promotion
(
   id_promotion serial,
   id_vol INTEGER REFERENCES vol(id_vol),
   id_type_siege INTEGER REFERENCES type_siege(id_type_siege),
   remise NUMERIC(15,2),
   nb_place INTEGER
);

alter table promotion add column nb_place_restant INTEGER;


create table heure_avant_apres_res
(
   heure_av_res Time,
   heure_ap_res Time
);




create or replace view vol_siege as
Select
   v.id_vol,
   ms.id_type_siege,
   ms.nombre_siege

FROM
    vol v 
JOIN 
    avion a on v.id_avion=a.id_avion
JOIN
    modele_siege ms on a.id_modele_avion=ms.id_modele_avion;


create or replace view vol_siege_vendu as 

 select
       id_vol,
       id_type_siege,
       sum(nb_place) as nb_place  
from reservation 
group by id_vol,id_type_siege;


create or replace view siege_restant_vol as

select
     vs.id_vol,
     vs.id_type_siege,
     vs.nombre_siege-coalesce(srv.nb_place,0) as place_restant 
from vol_siege as vs
 left join vol_siege_vendu srv on vs.id_vol=srv.id_vol and vs.id_type_siege=srv.id_type_siege;




CREATE VIEW vue_vol_avion AS
SELECT 
    v.id_vol,
    v.date_vol,
    v.description AS vol_description,
    v.duree,
    v.id_ville_depart,
    v.id_ville_arrivee,
    v.id_avion,
    a.id_modele_avion,
    ma.description AS modele_avion_description
FROM 
    vol v
JOIN 
    avion a ON v.id_avion = a.id_avion
JOIN 
    modele_avion ma ON a.id_modele_avion = ma.id_modele_avion;

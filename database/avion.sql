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


create table heure_avant_apres_res
(
   heure_av_res Time,
   heure_ap_res Time
);

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

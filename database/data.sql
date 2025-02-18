
INSERT INTO type_siege (description) VALUES
('Classe economique'),
('Classe Affaires'),
('Première Classe');

INSERT INTO modele_avion (description, date_fabrication) VALUES
('Boeing 737', '2005-03-15'),
('Airbus A320', '2010-06-22'),
('Boeing 777', '2017-09-05');


INSERT INTO ville_desservie (descritpion) VALUES
('Paris'),
('Londres'),
('New York'),
('Tokyo'),
('Berlin'),
('Sydney');

INSERT INTO avion (id_modele_avion) VALUES
(1),  -- Boeing 737
(2),  -- Airbus A320
(3);  -- Boeing 777

INSERT INTO vol (date_vol, description, id_ville_depart, id_ville_arrivee, id_avion, duree) VALUES
('2025-03-01 10:00:00', 'Vol Paris-Londres', 1, 2, 1, '01:30:00'),
('2025-03-02 15:00:00', 'Vol Londres-New York', 2, 3, 2, '08:00:00'),
('2025-03-03 08:00:00', 'Vol New York-Tokyo', 3, 4, 3, '14:00:00'),
('2025-03-04 13:00:00', 'Vol Tokyo-Berlin', 4, 5, 1, '11:00:00'),
('2025-03-05 11:00:00', 'Vol Berlin-Sydney', 5, 6, 2, '16:00:00');


INSERT INTO modele_siege (id_type_siege, id_modele_avion, nombre_siege) VALUES
(1, 1, 150),  -- Classe economique sur Boeing 737
(2, 1, 30),   -- Classe Affaires sur Boeing 737
(3, 1, 10),   -- Première Classe sur Boeing 737
(1, 2, 120),  -- Classe economique sur Airbus A320
(2, 2, 20),   -- Classe Affaires sur Airbus A320
(3, 2, 8),    -- Première Classe sur Airbus A320
(1, 3, 200),  -- Classe economique sur Boeing 777
(2, 3, 50),   -- Classe Affaires sur Boeing 777
(3, 3, 12);   -- Première Classe sur Boeing 777


INSERT INTO reservation (date_reservation, id_user, id_vol, id_type_siege, prix_billet) VALUES
('2025-02-20 09:00:00', 1, 1, 1, 150.00),  --  vol Paris-Londres, Classe economique
('2025-02-21 14:30:00', 2, 2, 2, 400.00),  --  vol Londres-New York, Classe Affaires
('2025-02-22 12:00:00', 3, 3, 3, 800.00),  --  vol New York-Tokyo, Première Classe
('2025-02-23 17:45:00', 4, 4, 1, 200.00),  --  vol Tokyo-Berlin, Classe economique
('2025-02-24 18:30:00', 5, 5, 2, 350.00);  --  vol Berlin-Sydney, Classe Affaires


INSERT INTO vol_prix_type_siege (id_type_siege, id_vol, prix_unitaire) VALUES
(1, 1, 150.00),  -- Classe economiques sur vol Paris-Londres
(2, 1, 400.00),  -- Classe Affaires sur vol Paris-Londres
(3, 1, 800.00),  -- Première Classe sur vol Paris-Londres
(1, 2, 250.00),  -- Classe economiques sur vol Londres-New York
(2, 2, 600.00),  -- Classe Affaires sur vol Londres-New York
(3, 2, 1200.00), -- Première Classe sur vol Londres-New York
(1, 3, 300.00),  -- Classe economiques sur vol New York-Tokyo
(2, 3, 700.00),  -- Classe Affaires sur vol New York-Tokyo
(3, 3, 1500.00), -- Première Classe sur vol New York-Tokyo
(1, 4, 180.00),  -- Classe economiques sur vol Tokyo-Berlin
(2, 4, 450.00),  -- Classe Affaires sur vol Tokyo-Berlin
(3, 4, 900.00),  -- Première Classe sur vol Tokyo-Berlin
(1, 5, 350.00),  -- Classe economiques sur vol Berlin-Sydney
(2, 5, 750.00),  -- Classe Affaires sur vol Berlin-Sydney
(3, 5, 1600.00); -- Première Classe sur vol Berlin-Sydney


INSERT INTO promotion (id_vol, id_type_siege, remise, nb_place) VALUES
(1, 1, 10.00, 2),  -- Promotion de 10 pour Classe economique sur vol Paris-Londres
(2, 2, 15.00, 2),  -- Promotion de 15 pour Classe Affaires sur vol Londres-New York
(3, 3, 20.00, 2),  -- Promotion de 20 pour Première Classe sur vol New York-Tokyo
(4, 1, 5.00, 2),   -- Promotion de 5 pour Classe economique sur vol Tokyo-Berlin
(5, 2, 10.00, 2);  -- Promotion de 10 pour Classe Affaires sur vol Berlin-Sydney



INSERT INTO users (nom_complet, email, status, mdp, adresse)
VALUES
  ('Alice Dupont', 'alice.dupont@example.com', true, 'password123', '123 Rue de Paris, 75001 Paris, France'),
  ('Bob Martin', 'bob.martin@example.com', true, 'mypassword456', '456 Avenue des Champs-lyses, 75008 Paris, France'),
  ('Carla Lefevre', 'carla.lefevre@example.com', false, 'carla789', '789 Boulevard Saint-Germain, 75006 Paris, France'),
  ('David Moreau', 'david.moreau@example.com', false, 'davidsupersecure', '101 Rue de la Rpublique, 69002 Lyon, France'),
  ('Emma Lemoine', 'emma.lemoine@example.com', false, 'emmapassword321', '202 Rue de la Libert, 13001 Marseille, France');

  INSERT INTO heure_avant_apres_res (heure_av_res, heure_ap_res)
VALUES 
('05:00:00', '07:00:00');

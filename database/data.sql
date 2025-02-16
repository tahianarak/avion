-- Insertion des données pour ville_desservie
INSERT INTO ville_desservie (nom_ville) VALUES
('Paris'),
('Londres'),
('New York'),
('Tokyo'),
('Sydney');

-- Insertion des données pour modele_avion
INSERT INTO modele_avion (nom_modele) VALUES
('Airbus A320'),
('Boeing 737'),
('Boeing 787');

-- Insertion des données pour avion
INSERT INTO avion (id_modele_avion) VALUES
(1),  -- Airbus A320
(2),  -- Boeing 737
(3);  -- Boeing 787

-- Insertion des données pour type_siege
INSERT INTO type_siege (type_siege) VALUES
('Économique'),
('Affaires'),
('Première');

-- Insertion des données pour vol
INSERT INTO vol (date_vol, description, id_ville_depart, id_ville_arrivee, id_avion, duree) VALUES
('2025-02-15 08:30:00', 'Vol direct', 1, 2, 1, '02:15:00'),  -- Paris -> Londres
('2025-02-15 12:00:00', 'Vol direct', 2, 3, 2, '08:00:00'),  -- Londres -> New York
('2025-02-16 09:00:00', 'Vol direct', 1, 4, 3, '11:00:00');  -- Paris -> Tokyo

-- Insertion des données pour users
INSERT INTO users (username, email) VALUES
('user1', 'user1@example.com'),
('user2', 'user2@example.com'),
('user3', 'user3@example.com');

-- Insertion des données pour reservation
INSERT INTO reservation (date_reservation, id_user, id_vol, id_type_siege) VALUES
('2025-01-15 10:00:00', 1, 1, 1),  -- Réservation 1 : Utilisateur 1, vol 1, siège économique
('2025-01-16 11:30:00', 2, 2, 2),  -- Réservation 2 : Utilisateur 2, vol 2, siège affaires
('2025-01-17 14:00:00', 3, 3, 3);  -- Réservation 3 : Utilisateur 3, vol 3, siège première

-- Insertion des données pour modele_siege
INSERT INTO modele_siege (id_type_siege, id_modele_avion, nombre_siege) VALUES
(1, 1, 150),  -- Économique, Airbus A320, 150 sièges
(2, 2, 40),   -- Affaires, Boeing 737, 40 sièges
(3, 3, 30);   -- Première, Boeing 787, 30 sièges

-- Insertion des données pour vol_prix_type_siege
INSERT INTO vol_prix_type_siege (id_type_siege, id_vol, prix_unitaire) VALUES
(1, 1, 100.00),  -- Siège économique, vol 1, prix 100€
(2, 2, 500.00),  -- Siège affaires, vol 2, prix 500€
(3, 3, 1500.00); -- Siège première, vol 3, prix 1500€

-- Insertion des données pour promotion
INSERT INTO promotion (id_vol, id_type_siege, remise, nb_place) VALUES
(1, 1, 10.00, 50),  -- Promotion sur vol 1, siège économique, remise de 10%, 50 places
(2, 2, 15.00, 20),  -- Promotion sur vol 2, siège affaires, remise de 15%, 20 places
(3, 3, 20.00, 10);  -- Promotion sur vol 3, siège première, remise de 20%, 10 places



INSERT INTO users (nom_complet, email, status, mdp, adresse)
VALUES
  ('Alice Dupont', 'alice.dupont@example.com', true, 'password123', '123 Rue de Paris, 75001 Paris, France'),
  ('Bob Martin', 'bob.martin@example.com', true, 'mypassword456', '456 Avenue des Champs-lyses, 75008 Paris, France'),
  ('Carla Lefevre', 'carla.lefevre@example.com', false, 'carla789', '789 Boulevard Saint-Germain, 75006 Paris, France'),
  ('David Moreau', 'david.moreau@example.com', false, 'davidsupersecure', '101 Rue de la Rpublique, 69002 Lyon, France'),
  ('Emma Lemoine', 'emma.lemoine@example.com', false, 'emmapassword321', '202 Rue de la Libert, 13001 Marseille, France');

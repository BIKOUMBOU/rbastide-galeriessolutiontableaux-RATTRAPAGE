-- Les données qui seront initialisées automatiquement quand on lance l'application
-- cf. application.properties
-- Sur Postgres, il faut initialiser le numéro de séquence pour les clés auto-générées
INSERT INTO Galerie(id, nom, adresse) VALUES 
    (1, 'Saatchi', 'King''s Road, Londres'),
    (2, 'Christies', 'Saville Road, Londres');
ALTER SEQUENCE galerie_id_seq RESTART WITH 3;

INSERT INTO Exposition(id, intitule, debut, duree, organisateur_id) VALUES
    (1, 'Painters'' painters', NOW(), 10, 1),
    (2, 'From Selfie to Self-Expression', NOW(), 10, 1);
ALTER SEQUENCE exposition_id_seq RESTART WITH 3;

-- On a choisi la stratégie 'SINGLE_TABLE' pour l'héritage
INSERT INTO Personne(DTYPE, id, nom, adresse, biographie) VALUES
    ('Personne', 1, 'Rémi Bastide', 'ISIS Castres', null),
    ('Artiste',  2, 'Pablo Picasso', null, 'Peintre Catalan'),
    ('Artiste',  3, 'Cy Twombly', null, 'né Edwin Parker Twombly Jr. le 25 avril 1928 à Lexington – mort le 5 juillet 2011 à Rome, est un peintre, dessinateur, sculpteur et photographe américain');
ALTER SEQUENCE personne_id_seq RESTART WITH 4;

INSERT INTO Tableau(id, titre, support, dimension, auteur_id) VALUES
    (1, 'Guernica', 'huile sur toile', '4 m.50 x 3 m. 65', 2),
    (2, 'Corrida', 'huile sur toile','2 m.50 x 1 m. 65', 2),
    (3, 'Tiznit',  'Acrylique sur bois','12 m x 5 m', 3),
    (4, 'Lepanto, Part XII', 'Acrylique sur bois', '12 m x 5 m', 3),
    (5, 'Vierge à l''enfant', 'Huile sur bois', '120cm x 60 cm', NULL);
ALTER SEQUENCE tableau_id_seq RESTART WITH 6;

INSERT INTO Exposition_Oeuvres(accrochages_id, oeuvres_id) VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (2, 2),
    (2, 3);

INSERT INTO Transaction(client_id, lieu_de_vente_id, oeuvre_id, prix_vente, vendu_le) VALUES
    (1, 1, 1, 1000.01, NOW()),
    (1, 1, 4, 2000.02, NOW()),
    (1, 2, 3, 3000.03, NOW());
ALTER SEQUENCE transaction_id_seq RESTART WITH 4;
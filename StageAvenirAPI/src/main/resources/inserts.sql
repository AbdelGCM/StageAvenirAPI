INSERT INTO role (idRole, nom) VALUES ('1', 'etudiant');
INSERT INTO role (idRole, nom) VALUES ('2', 'employeur');
INSERT INTO role (idRole, nom) VALUES ('3', 'coordonnateur');

INSERT INTO stageavenirapi.categorie (idcategorie, nom) VALUES ('1', 'Programmation');
INSERT INTO stageavenirapi.categorie (idcategorie, nom) VALUES ('2', 'Réseau');


INSERT INTO utilisateur (idutilisateur, nom, prenom, courriel, telephone, ville, categorie_idcategorie, role_idRole) VALUES ('1', 'Wissem', 'Benaraiba', 'wissem@example.com', '0123456789', 'Ville1', '1', '1');
INSERT INTO utilisateur (idutilisateur, nom, prenom, courriel, telephone, ville, categorie_idcategorie, role_idRole) VALUES ('2', 'NomEmployeur', 'PrenomEmployeur', 'employeur@example.com', '9876543210', 'Ville2', '2', '2');


INSERT INTO entreprise (identreprise, nom, adresse, description, secteur, utilisateur_idutilisateur) VALUES ('1', 'Tech-Logiciel', '629 rue d évolène','DescriptionEntreprise', 'logiciel', '2');

-- les offres de stages en programmation
INSERT INTO offreStage (idoffreStage, titre, description, poste_offert, remunere, date, visible, categorie_idcategorie, entreprise_identreprise)  VALUES ('1', 'Poste cherché en Java', 'Nous recherchons des programmeurs', 'Programmeur analyste', '1', '2023-12-31', '1', '1', '1');
INSERT INTO offreStage (idoffreStage, titre, description, poste_offert, remunere, date, visible, categorie_idcategorie, entreprise_identreprise)  VALUES ('2', 'Poste cherché en Python', 'Nous recherchons des programmeurs Python', 'Programmeur back end', '0', '2023-04-04', '0', '1', '1');

--les offres de stages en réseaux


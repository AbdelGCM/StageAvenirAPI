USE `stageavenirapi` ;

-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `role` (
                                      `idRole` INT NOT NULL AUTO_INCREMENT,
                                      `nom` ENUM('etudiant', 'coordonnateur', 'employeur') NOT NULL,
    PRIMARY KEY (`idRole`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stageavenirapi`.`categorie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `categorie` (
                                           `idcategorie` INT NOT NULL AUTO_INCREMENT,
                                           `nom` VARCHAR(45) NOT NULL ,
    PRIMARY KEY (`idcategorie`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stageavenirapi`.`utilisateur`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `utilisateur` (
                                             `idutilisateur` INT NOT NULL AUTO_INCREMENT,
                                             `nom` VARCHAR(45) NOT NULL,
    `prenom` VARCHAR(45) NOT NULL,
    `courriel` VARCHAR(45) NOT NULL,
    `telephone` VARCHAR(45) NULL,
    `ville` VARCHAR(45) NULL,
    `categorie_idcategorie` INT NOT NULL,
    `role_idRole` INT NOT NULL,
    PRIMARY KEY (`idutilisateur`),
    INDEX `fk_utilisateur_role1_idx` (`role_idRole` ASC) VISIBLE,
    INDEX `fk_utilisateur_categorie1_idx` (`categorie_idcategorie` ASC) VISIBLE,
    CONSTRAINT `fk_utilisateur_role1`
    FOREIGN KEY (`role_idRole`)
    REFERENCES `stageavenirapi`.`role` (`idRole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_utilisateur_categorie1`
    FOREIGN KEY (`categorie_idcategorie`)
    REFERENCES `stageavenirapi`.`categorie` (`idcategorie`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stageavenirapi`.`demandeStage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `demandeStage` (
                                              `iddemandeStage` INT NOT NULL AUTO_INCREMENT,
                                              `titre` VARCHAR(45) NOT NULL,
    `description` TINYINT NOT NULL,
    `remunere` TINYINT NOT NULL DEFAULT 0,
    `poste` VARCHAR(45) NOT NULL,
    `categorie_idcategorie` INT NOT NULL,
    `utilisateur_idutilisateur` INT NOT NULL,
    PRIMARY KEY (`iddemandeStage`),
    INDEX `fk_demandeStage_utilisateur1_idx` (`utilisateur_idutilisateur` ASC) VISIBLE,
    INDEX `fk_demandeStage_categorie1_idx` (`categorie_idcategorie` ASC) VISIBLE,
    CONSTRAINT `fk_demandeStage_utilisateur1`
    FOREIGN KEY (`utilisateur_idutilisateur`)
    REFERENCES `stageavenirapi`.`utilisateur` (`idutilisateur`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_demandeStage_categorie1`
    FOREIGN KEY (`categorie_idcategorie`)
    REFERENCES `stageavenirapi`.`categorie` (`idcategorie`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stageavenirapi`.`entreprise`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `entreprise` (
                                            `identreprise` INT NOT NULL AUTO_INCREMENT,
                                            `nom` VARCHAR(45) NOT NULL,
    `adresse` VARCHAR(45) NOT NULL,
    `description` VARCHAR(45) NOT NULL,
    `secteur` VARCHAR(45) NOT NULL,
    `utilisateur_idutilisateur` INT NOT NULL,
    PRIMARY KEY (`identreprise`),
    INDEX `fk_entreprise_utilisateur1_idx` (`utilisateur_idutilisateur` ASC) VISIBLE,
    CONSTRAINT `fk_entreprise_utilisateur1`
    FOREIGN KEY (`utilisateur_idutilisateur`)
    REFERENCES `stageavenirapi`.`utilisateur` (`idutilisateur`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stageavenirapi`.`offreStage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offreStage` (
                                            `idoffreStage` INT NOT NULL AUTO_INCREMENT,
                                            `titre` VARCHAR(45) NOT NULL,
    `description` VARCHAR(45) NOT NULL,
    `poste_offert` VARCHAR(45) NOT NULL,
    `remunere` TINYINT NOT NULL,
    `date` DATE NOT NULL,
    `visible` TINYINT NOT NULL DEFAULT 0,
    `categorie_idcategorie` INT NOT NULL,
    `entreprise_identreprise` INT NOT NULL,
    PRIMARY KEY (`idoffreStage`),
    INDEX `fk_offreStage_categorie1_idx` (`categorie_idcategorie` ASC) VISIBLE,
    INDEX `fk_offreStage_entreprise1_idx` (`entreprise_identreprise` ASC) VISIBLE,
    CONSTRAINT `fk_offreStage_categorie1`
    FOREIGN KEY (`categorie_idcategorie`)
    REFERENCES `stageavenirapi`.`categorie` (`idcategorie`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_offreStage_entreprise1`
    FOREIGN KEY (`entreprise_identreprise`)
    REFERENCES `stageavenirapi`.`entreprise` (`identreprise`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stageavenirapi`.`candidature`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `candidature` (
                                             `idcandidature` INT NOT NULL AUTO_INCREMENT,
                                             `etat` ENUM('EN_COURS', 'ACCEPTEE', 'REFUSEE', 'ANNULEE') NOT NULL DEFAULT 'EN_COURS',  -- Default value in uppercase
    `description` VARCHAR(45) NOT NULL,
    `utilisateur_idutilisateur` INT NOT NULL,
    `offreStage_idoffreStage` INT NOT NULL,
    PRIMARY KEY (`idcandidature`),
    INDEX `fk_candidature_offreStage1_idx` (`offreStage_idoffreStage` ASC) VISIBLE,
    INDEX `fk_candidature_utilisateur1_idx` (`utilisateur_idutilisateur` ASC) VISIBLE,
    CONSTRAINT `fk_candidature_offreStage1`
    FOREIGN KEY (`offreStage_idoffreStage`)
    REFERENCES `stageavenirapi`.`offreStage` (`idoffreStage`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_candidature_utilisateur1`
    FOREIGN KEY (`utilisateur_idutilisateur`)
    REFERENCES `stageavenirapi`.`utilisateur` (`idutilisateur`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    ) ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `stageavenirapi`.`document`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `document` (
                                          `iddocument` INT NOT NULL AUTO_INCREMENT,
                                          `nom` VARCHAR(255) NOT NULL,
    `type` ENUM('cv', 'supplement') NOT NULL,
    `contenu` BLOB NOT NULL,
    `utilisateur_idutilisateur` INT NULL,
    `demandeStage_iddemandeStage` INT NULL,
    `candidature_idcandidature` INT NULL,
    PRIMARY KEY (`iddocument`),
    INDEX `fk_document_utilisateur_idx` (`utilisateur_idutilisateur` ASC) VISIBLE,
    INDEX `fk_document_demandeStage1_idx` (`demandeStage_iddemandeStage` ASC) VISIBLE,
    INDEX `fk_document_candidature1_idx` (`candidature_idcandidature` ASC) VISIBLE,
    CONSTRAINT `fk_document_utilisateur`
    FOREIGN KEY (`utilisateur_idutilisateur`)
    REFERENCES `stageavenirapi`.`utilisateur` (`idutilisateur`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_document_demandeStage1`
    FOREIGN KEY (`demandeStage_iddemandeStage`)
    REFERENCES `stageavenirapi`.`demandeStage` (`iddemandeStage`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_document_candidature1`
    FOREIGN KEY (`candidature_idcandidature`)
    REFERENCES `stageavenirapi`.`candidature` (`idcandidature`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stageavenirapi`.`competence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `competence` (
                                            `idcompetence` INT NOT NULL AUTO_INCREMENT,
                                            `demandeStage_iddemandeStage` INT NOT NULL,
                                            `nom` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`idcompetence`),
    INDEX `fk_competence_demandeStage1_idx` (`demandeStage_iddemandeStage` ASC) VISIBLE,
    CONSTRAINT `fk_competence_demandeStage1`
    FOREIGN KEY (`demandeStage_iddemandeStage`)
    REFERENCES `stageavenirapi`.`demandeStage` (`iddemandeStage`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stageavenirapi`.`accordStage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `accordStage` (
                                             `idaccordStage` INT NOT NULL AUTO_INCREMENT,
                                             `commentaire` VARCHAR(45) NULL,
    `etat` ENUM('en cours', 'accepte', 'refuse') NOT NULL DEFAULT 'en cours',
    PRIMARY KEY (`idaccordStage`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stageavenirapi`.`proposition`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proposition` (
                                             `idproposition` INT NOT NULL AUTO_INCREMENT,
                                             `message` VARCHAR(45) NOT NULL,
    `etat` ENUM('en cours', 'acceptee', 'refusee') NOT NULL DEFAULT 'en cours',
    `demandeStage_iddemandeStage` INT NOT NULL,
    PRIMARY KEY (`idproposition`),
    INDEX `fk_proposition_demandeStage1_idx` (`demandeStage_iddemandeStage` ASC) VISIBLE,
    CONSTRAINT `fk_proposition_demandeStage1`
    FOREIGN KEY (`demandeStage_iddemandeStage`)
    REFERENCES `stageavenirapi`.`demandeStage` (`iddemandeStage`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stageavenirapi`.`utilisateur_has_accordStage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `utilisateur_has_accordStage` (
                                                             `utilisateur_idutilisateur` INT NOT NULL ,
                                                             `accordStage_idaccordStage` INT NOT NULL,
                                                             PRIMARY KEY (`utilisateur_idutilisateur`, `accordStage_idaccordStage`),
    INDEX `fk_utilisateur_has_accordStage_accordStage1_idx` (`accordStage_idaccordStage` ASC) VISIBLE,
    INDEX `fk_utilisateur_has_accordStage_utilisateur1_idx` (`utilisateur_idutilisateur` ASC) VISIBLE,
    CONSTRAINT `fk_utilisateur_has_accordStage_utilisateur1`
    FOREIGN KEY (`utilisateur_idutilisateur`)
    REFERENCES `stageavenirapi`.`utilisateur` (`idutilisateur`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_utilisateur_has_accordStage_accordStage1`
    FOREIGN KEY (`accordStage_idaccordStage`)
    REFERENCES `stageavenirapi`.`accordStage` (`idaccordStage`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stageavenirapi`.`utilisateur_has_utilisateur`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS  `utilisateur_has_utilisateur` (
                                                              `utilisateur_idutilisateur` INT NOT NULL,
                                                              `utilisateur_idutilisateur1` INT NOT NULL,
                                                              PRIMARY KEY (`utilisateur_idutilisateur`, `utilisateur_idutilisateur1`),
    INDEX `fk_utilisateur_has_utilisateur_utilisateur2_idx` (`utilisateur_idutilisateur1` ASC) VISIBLE,
    INDEX `fk_utilisateur_has_utilisateur_utilisateur1_idx` (`utilisateur_idutilisateur` ASC) VISIBLE,
    CONSTRAINT `fk_utilisateur_has_utilisateur_utilisateur1`
    FOREIGN KEY (`utilisateur_idutilisateur`)
    REFERENCES `stageavenirapi`.`utilisateur` (`idutilisateur`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_utilisateur_has_utilisateur_utilisateur2`
    FOREIGN KEY (`utilisateur_idutilisateur1`)
    REFERENCES `stageavenirapi`.`utilisateur` (`idutilisateur`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;
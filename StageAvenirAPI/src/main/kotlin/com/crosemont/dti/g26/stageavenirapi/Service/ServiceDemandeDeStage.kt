package com.crosemont.dti.g26.stageavenirapi.Service

import com.crosemont.dti.g26.stageavenirapi.DAO.DemandeStageDAOImplement
import com.crosemont.dti.g26.stageavenirapi.Modèle.Catégorie
import com.crosemont.dti.g26.stageavenirapi.Modèle.DemandeStage

class ServiceDemandeDeStage(val dao : DemandeStageDAOImplement) {

    fun obtenirToutesDemandesStage(): List<DemandeStage> = dao.chercherTous()

    fun obtenirDemandeParId(id: Int): DemandeStage? = dao.chercherParCode(id)

    fun postuler(codeEtudiant: Int, candidature: Candidature, documents: List<Document>) {
        // Implémentation de la logique de postulation pour une demande de stage
        // ...
    }

    fun obtenirCandidatures(codeEtudiant: Int) {
        // Implémentation pour obtenir les candidatures d'un étudiant pour une demande de stage spécifique
        // ...
    }

    fun ajouterDemande(demande: DemandeStage): DemandeStage? = dao.ajouter(demande)

    fun effacerDemande(id: Int) = dao.effacer(id)

    fun modifierDemande(id: Int, demande: DemandeStage): DemandeStage? = dao.modifier(id, demande)
}
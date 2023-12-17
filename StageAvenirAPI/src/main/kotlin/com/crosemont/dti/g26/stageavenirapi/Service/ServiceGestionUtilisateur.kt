package com.crosemont.dti.g26.stageavenirapi.Service

import com.crosemont.dti.g26.stageavenirapi.DAO.CandidatureDAO
import com.crosemont.dti.g26.stageavenirapi.DAO.DocumentDAO
import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature
import com.crosemont.dti.g26.stageavenirapi.Modèle.DemandeStage
import com.crosemont.dti.g26.stageavenirapi.Modèle.Document
import com.crosemont.dti.g26.stageavenirapi.Modèle.OffreStage
import org.springframework.stereotype.Service

@Service
class ServiceGestionUtilisateur (var daoDocument : DocumentDAO, var daoCandidature: CandidatureDAO) {
    //Gestion des utilisateurs


    //Gestion des documents
    fun ajouterUnDocumentAuneDemandeDeStage(document: Document, idDemande :Int):Document?{
        return daoDocument.ajouterDocumentADemandeStage(document,idDemande)
    }

    fun ajouterUnDocumentAuneCandidature(document: Document,idCandidature: Int):Document?{
        return daoDocument.ajouterDocumentACandidature(document,idCandidature)
    }
    fun ajouterUnCv(cv :Document, idEtudiant : Int):Document?{
        return daoDocument.ajouterCv(cv, idEtudiant)
    }
    fun modifierUnCv(cv : Document):Document?{
        return daoDocument.modifierCv(cv)
    }
    fun récupérerDocumentsParCandidatures(idCandidature: Int):List<Document>?{
        return daoDocument.chercherParCandidature(idCandidature)
    }

    fun récupérerDocumentsParDemandeDeStage(idDemandeStage: Int): List<Document>{
        return daoDocument.chercherParDemandeStage(idDemandeStage)
    }

    fun récupérerTousLesDocuments():List<Document>{
        return daoDocument.chercherTous()
    }


}
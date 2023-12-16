package com.crosemont.dti.g26.stageavenirapi.Service

import com.crosemont.dti.g26.stageavenirapi.DAO.CandidatureDAO
import com.crosemont.dti.g26.stageavenirapi.DAO.DocumentDAO
import com.crosemont.dti.g26.stageavenirapi.DAO.OffreStageDAO
import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature
import com.crosemont.dti.g26.stageavenirapi.Modèle.Document
import com.crosemont.dti.g26.stageavenirapi.Modèle.OffreStage
import org.springframework.stereotype.Service

@Service
class ServiceOffreDeStage(val daoOffreStage: OffreStageDAO, val daoCandidature: CandidatureDAO, val daoDocument: DocumentDAO){

    //Offres de stage
    fun obtenirOffresStage(): List<OffreStage> = daoOffreStage.chercherTous()
    fun obtenirOffreParCode (code: Int): OffreStage? = daoOffreStage.chercherParCode(code)
    fun ajouter (offre: OffreStage): OffreStage? = daoOffreStage.ajouter(offre)
    // fun effacer(code: Int) = dao.effacer(code)
    //fun modifier(code: Int, offre: OffreStage): OffreStage? = dao.modifier(code, offre)



    //Candidatures
    fun postulerPourUneOffre (codeEtudiant : Int ,codeOffre:Int, candidature: Candidature, listeDocuments: List<Document>):Candidature?{
        var nouvelleCandidature = daoCandidature.postulerPourUneOffre(candidature,codeEtudiant,codeOffre)

        if (nouvelleCandidature != null){
            for (document in listeDocuments){
                daoDocument.ajouterDocumentACandidature(document, nouvelleCandidature.idCandidature)
            }
        }
        return nouvelleCandidature
    }

    fun obtenirCandidaturesParEtudiant (codeEtudiant:Int):List<Candidature>{
        var candidatures = daoCandidature.chercherParEtudiant(codeEtudiant)
        for (candidature in candidatures){
            var documents = daoDocument.chercherParCandidature(candidature.idCandidature)
            for (document in documents){
                candidature.documents?.add(document)
            }
        }
        return candidatures
    }

    fun obtenirCandidaturesParDemandeStage (codeDemande:Int):List<Candidature>{
        var candidatures =  daoCandidature.chercherParOffreStage(codeDemande)
        for (candidature in candidatures){
            var documents = daoDocument.chercherParDemandeStage(candidature.idCandidature)
            for (document in documents){
                candidature.documents?.add(document)
            }
        }
        return candidatures
    }

    fun annulerCandidature(idCandidature: Int):Candidature?{
        return daoCandidature.annulerCandidature(idCandidature)
    }
    fun accepterCandidature(idCandidature: Int):Candidature?{
        return daoCandidature.accepterCandidature(idCandidature)
    }
    fun refuserCandidature(idCandidature: Int):Candidature?{
        return daoCandidature.refuserCandidature(idCandidature)
    }





    //Demandes de stages












}
package com.crosemont.dti.g26.stageavenirapi.Service

import com.crosemont.dti.g26.stageavenirapi.DAO.AccordStageDAO
import com.crosemont.dti.g26.stageavenirapi.DAO.CandidatureDAO
import com.crosemont.dti.g26.stageavenirapi.DAO.DocumentDAO
import com.crosemont.dti.g26.stageavenirapi.DAO.OffreStageDAO
import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature
import com.crosemont.dti.g26.stageavenirapi.Modèle.OffreStage
import com.crosemont.dti.g26.stageavenirapi.Modèle.*
import com.crosemont.dti.g26.stageavenirapi.Modèle.Enum.Etat
import org.springframework.stereotype.Service

@Service
class ServiceOffreDeStage(val daoOffreStage: OffreStageDAO, val daoCandidature: CandidatureDAO,val daoAccord:AccordStageDAO, val daoDocument: DocumentDAO){


    //======================================Offres de stage
    fun obtenirOffresStage(): List<OffreStage> = daoOffreStage.chercherTous()
    fun obtenirOffreParCode (code: Int): OffreStage? = daoOffreStage.chercherParCode(code)
    fun obtenirOffresParCatégorie (code: Int): List<OffreStage> = daoOffreStage.chercherParCodeCatégorie(code)
    fun ajouter (codeEntreprise: Int, offre: OffreStage): OffreStage? = daoOffreStage.ajouterUneOffre(codeEntreprise,offre)
    fun effacer(code: Int) = daoOffreStage.effacer(code)
    fun modifier(code: Int, offre: OffreStage): OffreStage? = daoOffreStage.modifier(code, offre)





    //========================================Candidatures
    fun postulerPourUneOffre (codeEtudiant : Int ,codeOffre:Int, candidature: Candidature, idEtudiant:Int):Candidature?{
        var nouvelleCandidature = daoCandidature.postulerPourUneOffre(candidature,codeEtudiant,codeOffre)

        if (nouvelleCandidature != null ){
            for (document in nouvelleCandidature.documents!!){
                daoDocument.ajouterDocumentACandidature(document, nouvelleCandidature.idCandidature)
            }

        }
        return nouvelleCandidature
    }

    fun obtenirCandidaturesParEtudiant (codeEtudiant:Int):List<Candidature>{
        return daoCandidature.chercherParEtudiant(codeEtudiant)
    }

    fun obtenirCandidaturesParDemandeStage (codeDemande:Int):List<Candidature>{
        return  daoCandidature.chercherParOffreStage(codeDemande)
    }

    fun annulerCandidature(idCandidature: Int):Candidature?{
        return daoCandidature.annulerCandidature(idCandidature)
    }
    fun accepterCandidature(candidature: Candidature):Candidature?{
        var accordStage =  AccordStage(0, null, Etat.EN_COURS, candidature.etudiant, candidature.offre)
        daoAccord.ajouter(accordStage)
        return daoCandidature.accepterCandidature(candidature.idCandidature)
    }
    fun refuserCandidature(idCandidature: Int):Candidature?{
        return daoCandidature.refuserCandidature(idCandidature)
    }

    //==============================================Accords de stages


    fun approuverAccordStage(idAccordStage : Int):AccordStage?{
        return daoAccord.approuverUnAccord(idAccordStage)
    }
    fun refuserAccordStage(idAccordStage: Int):AccordStage?{
        return daoAccord.désaprouverUnAccord(idAccordStage)
    }






}
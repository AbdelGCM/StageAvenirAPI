package com.crosemont.dti.g26.stageavenirapi.Service

import com.crosemont.dti.g26.stageavenirapi.DAO.*
import com.crosemont.dti.g26.stageavenirapi.Exceptions.DroitAccèsInsuffisantException
import com.crosemont.dti.g26.stageavenirapi.Exceptions.RessourceInexistanteException
import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature
import com.crosemont.dti.g26.stageavenirapi.Modèle.OffreStage
import com.crosemont.dti.g26.stageavenirapi.Modèle.*
import com.crosemont.dti.g26.stageavenirapi.Modèle.Enum.Etat
import org.springframework.stereotype.Service

@Service
class ServiceOffreDeStage(val daoUtilisateur: UtilisateurDAO, val daoOffreStage: OffreStageDAO, val daoCandidature: CandidatureDAO,val daoAccord:AccordStageDAO, val daoDocument: DocumentDAO, val serviceGestionUtilisateur: ServiceGestionUtilisateur){


    //======================================Offres de stage
    fun obtenirOffresStage(): List<OffreStage> = daoOffreStage.chercherTous()
    fun obtenirOffreParCode (code: Int): OffreStage? = daoOffreStage.chercherParCode(code)
    fun obtenirOffresParCatégorie (code: Int): List<OffreStage> = daoOffreStage.chercherParCodeCatégorie(code)

    fun effacer(code: Int) = daoOffreStage.effacer(code)
    fun modifier(code: Int, offre: OffreStage): OffreStage? = daoOffreStage.modifier(code, offre)

    fun ajouter (codeEntreprise: Int, offre: OffreStage): OffreStage? {
        return daoOffreStage.ajouterUneOffre(codeEntreprise,offre)
    }
    fun obtenirStagesEnCoursDeValidationPourUnePublication(code_coordo : String ): List<OffreStage> {
        return daoOffreStage.obtenirOffresEnCoursApprobation()
    }

    fun approuverPublicationDuneOffre(code_coordo : String, idOffre : Int): OffreStage?{
        return daoOffreStage.modifierVisibilité(idOffre, true, "ACCEPTEE")
    }

    fun refuserPublicationDuneOffre(code_coordo : String, idOffre : Int): OffreStage?{
        return daoOffreStage.modifierVisibilité(idOffre, false, "REFUSEE")
    }



    //========================================Candidatures
    fun postulerPourUneOffre (codeEtudiant : String ,codeOffre:Int, candidature: Candidature):Candidature?{
        var etudiant = daoUtilisateur.chercherUserParCode(codeEtudiant)
        if (etudiant != null) {
            if (serviceGestionUtilisateur.verifierRoleUtilisateur(etudiant , "etudiant")){
                var nouvelleCandidature = daoCandidature.postulerPourUneOffre(candidature,codeEtudiant,codeOffre)
                if (nouvelleCandidature != null ){
                    for (document in candidature.documents!!){
                        daoDocument.ajouterDocumentACandidature(document, nouvelleCandidature.idCandidature)
                    }

                }
                return nouvelleCandidature
            }else throw DroitAccèsInsuffisantException("L'étudiant ${etudiant.nom} n'est pas un étudiant")
        }
        throw RessourceInexistanteException("L'étudiant avec le code ${codeEtudiant} n'existe pas")

    }

    fun obtenirCandidaturesParEtudiant (codeEtudiant:String):List<Candidature>{
        var etudiant = daoUtilisateur.chercherUserParCode(codeEtudiant)
        if (etudiant != null) {
            if (serviceGestionUtilisateur.verifierRoleUtilisateur(etudiant , "etudiant")){
                return daoCandidature.chercherParEtudiant(codeEtudiant)
            }else throw DroitAccèsInsuffisantException("L'étudiant ${etudiant.nom} n'est pas un étudiant")
        }
        throw RessourceInexistanteException("L'étudiant avec le code ${codeEtudiant} n'existe pas")
    }



    fun annulerCandidature(idCandidature: Int , codeEtudiant : String):Candidature?{
        var etudiant = daoUtilisateur.chercherUserParCode(codeEtudiant)
        if (etudiant != null) {
            if (serviceGestionUtilisateur.verifierRoleUtilisateur(etudiant , "etudiant")){
                return  daoCandidature.annulerCandidature(idCandidature)
            }else throw DroitAccèsInsuffisantException("L'étudiant ${etudiant.nom} n'est pas un étudiant. Seul ce rôle permet d'annuler ne candidature")
        }
        throw RessourceInexistanteException("L'étudiant avec le code ${codeEtudiant} n'existe pas")

    }
    fun accepterCandidature(candidature: Candidature, code_employeur : String):Candidature?{
        var employeur = daoUtilisateur.chercherUserParCode(code_employeur)
        if (employeur != null) {
            if (serviceGestionUtilisateur.verifierRoleUtilisateur(employeur , "employeur")){
                var accordStage =  AccordStage(0, null, Etat.EN_COURS, candidature.etudiant, candidature.offre)
                daoAccord.ajouter(accordStage)
                return daoCandidature.accepterCandidature(candidature.idCandidature)
            }else throw DroitAccèsInsuffisantException("L'utilisateur ${employeur.nom} n'est pas un employeur. Seuls ce dernier peut accepter ou refuser une candidature")
        }
        throw RessourceInexistanteException("L'employeur avec le code ${code_employeur} n'existe pas")
    }


    fun refuserCandidature(idCandidature: Int, code_employeur: String):Candidature?{
        var employeur = daoUtilisateur.chercherUserParCode(code_employeur)
        if (employeur != null) {
            if (serviceGestionUtilisateur.verifierRoleUtilisateur(employeur , "employeur")){
                return  daoCandidature.refuserCandidature(idCandidature)
            }else throw DroitAccèsInsuffisantException("L'utilisateur ${employeur.nom} n'est pas un employeur. Seuls ce dernier peut accepter ou refuser une candidature")
        }
        throw RessourceInexistanteException("L'employeur avec le code ${code_employeur} n'existe pas")
    }


    //==============================================Proposition de stage
    fun obtenirCandidaturesParDemandeStage (codeDemande:Int, code_utilisateur : String):List<Candidature>{
        var utilisateur = daoUtilisateur.chercherUserParCode(code_utilisateur)
        if (utilisateur != null) {
            if (serviceGestionUtilisateur.verifierRoleUtilisateur(utilisateur , "etudiant")){
                return daoCandidature.chercherParOffreStage(codeDemande)
            }else throw DroitAccèsInsuffisantException("L'étudiant ${utilisateur.nom} n'est pas un étudiant")
        }
        throw RessourceInexistanteException("L'étudiant avec le code ${code_utilisateur} n'existe pas")
    }


    //==============================================Accords de stages


    fun approuverAccordStage(idAccordStage : Int):AccordStage?{
        return daoAccord.approuverUnAccord(idAccordStage)
    }
    fun refuserAccordStage(idAccordStage: Int):AccordStage?{
        return daoAccord.désaprouverUnAccord(idAccordStage)
    }






}
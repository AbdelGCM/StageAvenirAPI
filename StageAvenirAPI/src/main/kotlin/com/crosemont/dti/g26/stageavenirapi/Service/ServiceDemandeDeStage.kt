package com.crosemont.dti.g26.stageavenirapi.Service

import com.crosemont.dti.g26.stageavenirapi.DAO.DemandeStageDAOImplement
import com.crosemont.dti.g26.stageavenirapi.Modèle.Categorie
import com.crosemont.dti.g26.stageavenirapi.Modèle.DemandeStage

class ServiceDemandeDeStage(val dao : DemandeStageDAOImplement) {

    fun chercherToutesLesDemandes(catégorie : Categorie){dao.chercherTous()}
    fun posterDemandeStage(demandeStage : DemandeStage){dao.ajouter(demandeStage)}
    fun changerStatutDemande(codeDemande:Int, statut : Boolean){}
}
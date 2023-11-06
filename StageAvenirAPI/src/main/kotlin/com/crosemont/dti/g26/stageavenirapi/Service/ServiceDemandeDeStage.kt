package com.crosemont.dti.g26.stageavenirapi.Service

import com.crosemont.dti.g26.stageavenirapi.DAO.DemandeStageDAOImplement
import com.crosemont.dti.g26.stageavenirapi.Modèle.Catégorie
import com.crosemont.dti.g26.stageavenirapi.Modèle.DemandeStage

class ServiceDemandeDeStage(val dao : DemandeStageDAOImplement) {

    fun chercherToutesLesDemandes(catégorie : Catégorie){dao.chercherTous()}
    fun posterDemandeStage(demandeStage : DemandeStage){dao.ajouter(demandeStage)}
    fun changerStatutDemande(codeDemande:Int, statut : Boolean){}
}
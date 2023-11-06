package com.crosemont.dti.g26.stageavenirapi.Service

import com.crosemont.dti.g26.stageavenirapi.DAO.DemandeStageDAO
import com.crosemont.dti.g26.stageavenirapi.DAO.DemandeStageDAOImplement
import com.crosemont.dti.g26.stageavenirapi.modèle.Demande_Stage

class ServiceDemandeDeStage(val dao : DemandeStageDAOImplement) {

    fun chercherToutesLesDemandes(){dao.chercherTous()}
    fun posterDemandeStage(demandeStage : Demande_Stage){dao.ajouter(demandeStage)}
    fun changerStatutDemande(codeDemande:Int, statut : Boolean){}
}
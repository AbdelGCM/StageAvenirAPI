package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.DemandeStage

interface DemandeStageDAO :DAO<DemandeStage> {

    override fun ajouter(element: DemandeStage): DemandeStage?
    override fun chercherParCode(code: Int): DemandeStage?
    override fun chercherTous(): List<DemandeStage>
   // override fun modifier(element: DemandeStage): DemandeStage?
   // override fun supprimer(element: DemandeStage): Boolean

}
package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.Categorie
import com.crosemont.dti.g26.stageavenirapi.Modèle.DemandeStage

interface DemandeStageDAO :DAO<DemandeStage> {

    override fun ajouter(element: DemandeStage): DemandeStage?
    override fun chercherParCode(code: Int): DemandeStage?

    fun chercherCategorieParCode(code: Int): Categorie?
    override fun chercherTous(): List<DemandeStage>

   override fun modifier(id: Int,element: DemandeStage): DemandeStage?
   fun modifierStatus(status: Boolean,id: Int): DemandeStage?
    override fun effacer (id: Int )



}
package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.modèle.Demande_Stage

interface DemandeStageDAO :DAO<Demande_Stage> {

    override fun ajouter(element: Demande_Stage): Demande_Stage?
    override fun chercherParCode(code: String): Demande_Stage?
    override fun chercherTous(): List<Demande_Stage>
    override fun modifier(element: Demande_Stage): Demande_Stage?
    override fun supprimer(element: Demande_Stage): Boolean

}
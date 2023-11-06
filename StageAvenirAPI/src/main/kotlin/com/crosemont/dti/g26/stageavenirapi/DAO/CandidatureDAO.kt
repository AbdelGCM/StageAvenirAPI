package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.modèle.Candidature
import com.crosemont.dti.g26.stageavenirapi.modèle.Demande_Stage

interface CandidatureDAO :DAO<Candidature> {

    override fun ajouter(element: Candidature): Candidature?
    override fun chercherParCode(code: String): Candidature?
    override fun chercherTous(): List<Candidature>
    override fun modifier(element: Candidature): Candidature?
    override fun supprimer(element: Candidature): Boolean
    fun annuler(element: Candidature):Boolean

}
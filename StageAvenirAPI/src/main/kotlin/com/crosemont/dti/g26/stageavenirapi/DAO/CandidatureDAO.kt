package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature

interface CandidatureDAO :DAO<Candidature> {

    override fun ajouter(element: Candidature): Candidature?
    override fun chercherParCode(code: String): Candidature?
    override fun chercherTous(): List<Candidature>
    override fun modifier(element: Candidature): Candidature?
    override fun supprimer(element: Candidature): Boolean


}
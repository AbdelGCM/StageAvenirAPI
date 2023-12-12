package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature

interface CandidatureDAO :DAO<Candidature> {

    override fun ajouter(element: Candidature): Candidature?
    override fun chercherParCode(code: Int): Candidature?
    override fun chercherTous(): List<Candidature>
    override fun modifier(element: Candidature): Boolean
    override fun effacer(element: Candidature): Boolean
    fun chercherParEtudiant(code_etudiant : Int):Candidature?
    fun chercherParOffreStage(code_offre : Int):Candidature?
    fun postulerPourUneOffre()


}
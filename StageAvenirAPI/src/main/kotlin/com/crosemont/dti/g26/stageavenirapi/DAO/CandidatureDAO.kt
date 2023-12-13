package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature

interface CandidatureDAO :DAO<Candidature> {

    override fun ajouter(element: Candidature): Candidature?
    override fun chercherParCode(code: Int): Candidature?
    override fun chercherTous(): List<Candidature>
    override fun modifier(element: Candidature): Candidature
    override fun effacer(element: Candidature): Boolean
    fun chercherParEtudiant(code_etudiant : Int):List<Candidature>
    fun chercherParOffreStage(code_offre : Int):List<Candidature>
    fun postulerPourUneOffre(candidature: Candidature, code_etudiant: Int,idOffre:Int):Candidature?
    fun annulerCandidature(candidature: Candidature):Candidature?
    fun accepterCandidature(candidature: Candidature):Candidature?
    fun refuserCandidature(candidature: Candidature):Candidature?


}
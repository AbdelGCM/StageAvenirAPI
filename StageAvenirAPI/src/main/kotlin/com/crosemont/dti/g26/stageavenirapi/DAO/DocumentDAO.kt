package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature
import com.crosemont.dti.g26.stageavenirapi.Modèle.DemandeStage
import com.crosemont.dti.g26.stageavenirapi.Modèle.Document

interface DocumentDAO:DAO<Document> {

    override fun ajouter(element: Document): Document?
    override fun chercherParCode(code: Int): Document?
    override fun chercherTous(): List<Document>
    override fun modifier(element: Document): Document
    override fun effacer(element: Document): Boolean

    fun ajouterDocumentACandidature(element: Document, code: Int):Document?
    fun ajouterDocumentADemandeStage(element: Document, code: Int):Document?
    fun chercherParCandidature (candidature: Int):List<Document>
    fun chercherParDemandeStage (demandeStage: Int):List<Document>
    fun ajouterCv(cv :Document, idEtudiant:Int):Document?
    fun modifierCv(cv: Document):Document?


}
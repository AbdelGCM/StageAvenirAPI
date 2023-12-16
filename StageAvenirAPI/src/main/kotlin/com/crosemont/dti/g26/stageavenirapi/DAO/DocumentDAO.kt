package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature
import com.crosemont.dti.g26.stageavenirapi.Modèle.Document

interface DocumentDAO:DAO<Document> {

    override fun ajouter(element: Document): Document?
    override fun chercherParCode(code: Int): Document?
    override fun chercherTous(): List<Document>
    /*
    override fun modifier(element: Document): Boolean
    override fun effacer(element: Document): Boolean
*/
    fun ajouterDocument(element: Document, code: Int):Document?


}
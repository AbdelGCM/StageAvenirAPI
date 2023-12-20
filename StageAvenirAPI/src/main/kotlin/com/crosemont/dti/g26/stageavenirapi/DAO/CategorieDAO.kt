package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.Categorie
import com.crosemont.dti.g26.stageavenirapi.Modèle.Utilisateur

interface CategorieDAO: DAO<Categorie>   {
    override fun ajouter(categorie: Categorie): Categorie?
    override fun chercherParCode(code: Int): Categorie?

    fun chercherCategorieParCode(code: Int): Categorie?
    override fun chercherTous(): List<Categorie>

    override fun modifier(id: Int,categorie: Categorie): Categorie?

    override fun effacer (id: Int )
}
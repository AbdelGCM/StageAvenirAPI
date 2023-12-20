package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.Categorie
import com.crosemont.dti.g26.stageavenirapi.Modèle.DemandeStage
import com.crosemont.dti.g26.stageavenirapi.Modèle.Utilisateur

interface UtilisateurDAO : DAO<Utilisateur>    {
    override fun ajouter(utilisateur: Utilisateur): Utilisateur?
    override fun chercherParCode(code: Int): Utilisateur?

    fun chercherCategorieParCode(code: Int): Categorie?
    override fun chercherTous(): List<Utilisateur>

    override fun modifier(id: Int,utilisateur: Utilisateur): Utilisateur?

    override fun effacer (id: Int )
}
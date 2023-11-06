package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.modèle.Demande_Stage
import com.crosemont.dti.g26.stageavenirapi.modèle.Utilisateur

interface UtilisateurDAO : DAO<Utilisateur> {
    override fun ajouter(element: Utilisateur): Utilisateur?
    override fun chercherParCode(code: String): Utilisateur?
    override fun chercherTous(): List<Utilisateur>
    override fun modifier(element: Utilisateur): Utilisateur?
    override fun supprimer(element: Utilisateur): Boolean
}
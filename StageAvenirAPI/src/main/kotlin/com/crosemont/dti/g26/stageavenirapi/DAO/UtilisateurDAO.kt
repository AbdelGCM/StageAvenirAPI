package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.Utilisateur

interface UtilisateurDAO : DAO<Utilisateur> {
    override fun ajouter(element: Utilisateur): Utilisateur?
  //  override fun chercherParCode(code: String): Utilisateur?
    override fun chercherTous(): List<Utilisateur>
  //  override fun modifier(element: Utilisateur): Utilisateur?
  //  override fun supprimer(element: Utilisateur): Boolean
    fun cahangerPhoto(element:Utilisateur, urlPhoto : String): Boolean
}
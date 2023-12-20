package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.Utilisateur

interface UtilisateurDAO  :DAO<Utilisateur>   {

    override fun chercherParCode(code: Int): Utilisateur?
}
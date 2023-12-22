package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.Entreprise

interface EntrepriseDAO : DAO<Entreprise> {
    override fun chercherParCode(code: Int): Entreprise?


}
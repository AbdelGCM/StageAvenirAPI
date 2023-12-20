package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature
import com.crosemont.dti.g26.stageavenirapi.Modèle.Employeur

interface EmployeurDAO : DAO<Employeur> {

    override fun chercherParCode(code: Int): Employeur?

}
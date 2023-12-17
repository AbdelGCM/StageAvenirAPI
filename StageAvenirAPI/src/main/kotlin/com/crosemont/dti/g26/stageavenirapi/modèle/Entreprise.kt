package com.crosemont.dti.g26.stageavenirapi.modèle

import com.crosemont.dti.g26.stageavenirapi.Modèle.Employeur
import com.crosemont.dti.g26.stageavenirapi.Service.ServiceGestionUtilisateur
import com.fasterxml.jackson.databind.BeanDescription
import org.springframework.data.relational.core.sql.In

data class Entreprise (
        val idEntreprise : Int,
        val nom : String,
        val adresse : String,
        val description: String,
        val secteur : String,
        val utilisateurIdUtilisateur : Int
)


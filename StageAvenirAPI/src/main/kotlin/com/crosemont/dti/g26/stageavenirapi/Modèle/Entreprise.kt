package com.crosemont.dti.g26.stageavenirapi.Modèle

data class Entreprise (
        val idEntreprise : Int,
        val nom : String,
        val adresse : String,
        val description: String,
        val secteur : String,
        val employeur : Utilisateur
)


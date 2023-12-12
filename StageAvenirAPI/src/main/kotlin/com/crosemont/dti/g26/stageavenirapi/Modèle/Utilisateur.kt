package com.crosemont.dti.g26.stageavenirapi.Modèle

data class Utilisateur(
    var idCompte: Int,
    var nom: String?,
    val prénom: String?,
    val noEtudiant: String?,
    val courriel: String?,
    val adresse: String?,
    val typeEntreprise: String?,
    val typeCompte: String?,
   // val cv: Cv,
    val sousCategorie: SousCategorie
)
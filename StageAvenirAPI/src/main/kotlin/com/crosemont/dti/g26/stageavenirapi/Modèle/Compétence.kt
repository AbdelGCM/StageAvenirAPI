package com.crosemont.dti.g26.stageavenirapi.Modèle

data class Compétence(
    val idComprétence: Int,
    val nom: String,
    val description: String,
    val Etudiant_idEtudiant: Int,
    val Etudiant_Cv_idCv: Int,
    val Etudiant_Coordonnateur_idCoordonnateur: Int,
    val Etudiant_Catégorie_idCatégorie: Int
)

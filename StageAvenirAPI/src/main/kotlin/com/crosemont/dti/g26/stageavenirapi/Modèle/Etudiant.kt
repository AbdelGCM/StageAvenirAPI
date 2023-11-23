package com.crosemont.dti.g26.stageavenirapi.Modèle

data class Etudiant(
    val idEtudiant: Int,
    val nom: String?,
    val prénom: String?,
    val noÉtudiant: String?,
    val courriel: String?,
    val cvIdCv: Int,
    val coordonnateurIdCoordonnateur: Int,
    val catégorieIdCatégorie: Int
)

package com.crosemont.dti.g26.stageavenirapi.Modèle

data class DemandeStage(
    val idDemandeStage: Int,
    val titre: String,
    val description: String,
    val posteDemandé: String,
    val etudiantIdEtudiant: Int
)

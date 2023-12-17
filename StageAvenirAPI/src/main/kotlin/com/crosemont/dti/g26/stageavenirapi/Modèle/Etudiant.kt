package com.crosemont.dti.g26.stageavenirapi.Modèle

data class Etudiant(
    val idEtudiant: Int,
    val nom: String?,
    val prénom: String?,
    val noÉtudiant: String?,
    val courriel: String?,

){

    constructor(idEtudiant: Int) : this(
            idEtudiant = idEtudiant,
            nom = null,
            prénom = null,
            noÉtudiant = null,
            courriel = null,
    )
}

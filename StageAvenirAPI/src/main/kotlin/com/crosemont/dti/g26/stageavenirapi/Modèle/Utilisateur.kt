package com.crosemont.dti.g26.stageavenirapi.Modèle

class Utilisateur(
    val idUtilisateur: String,
    val nom: String?,
    val prénom: String?,
    val courriel: String?,
    val no_telephone : String?,
    val categorie: Categorie?,
    val role : Role?
) {
    constructor(idUtilisateur: String) : this(
        idUtilisateur = idUtilisateur,
        nom = null,
        prénom = null,
        courriel = null,
        no_telephone = null,
        categorie = null,
        role = null
    )
}

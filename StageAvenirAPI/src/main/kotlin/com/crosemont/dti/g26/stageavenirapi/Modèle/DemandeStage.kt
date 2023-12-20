package com.crosemont.dti.g26.stageavenirapi.Modèle

data class DemandeStage(
    val idDemandeStage: Int,
    val titre: String?,
    val description: String?,
    val remunere: Boolean?,
    val poste: String?,
    val visible: Boolean?,
    val categorie: Categorie?,
    val utilisateur: Utilisateur?
)

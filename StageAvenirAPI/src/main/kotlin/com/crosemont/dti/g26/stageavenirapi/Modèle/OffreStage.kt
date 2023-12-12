package com.crosemont.dti.g26.stageavenirapi.Modèle

import java.time.LocalDate

data class OffreStage(
    val idOffreStage: Int,
    val titreOffre: String,
    val posteOffert: String,
    val description: String,
    val estRémunéré: Boolean,
    val dateDébut: LocalDate,
    val dateFin: LocalDate,
    val estVisible: Boolean,
    val utilisateur: Utilisateur,
    val catégorie:Catégorie
)

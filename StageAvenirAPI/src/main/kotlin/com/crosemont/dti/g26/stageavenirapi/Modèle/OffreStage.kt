package com.crosemont.dti.g26.stageavenirapi.Modèle

import java.time.LocalDate

data class OffreStage(
    val idOffreStage: Int,
    val titreOffre: String,
    val posteOffert: String,
    val description: String,
    val favori: Boolean,
    val estRémunéré: Boolean,
    val dateDébut: LocalDate,
    val dateFin: LocalDate,
    val entrepriseIdEntreprise: Int,
    val catégorieIdCatégorie: Int
)

package com.crosemont.dti.g26.stageavenirapi.Modèle


import java.time.LocalDate

data class OffreStage(

        val idOffreStage: Int,
        val titreOffre: String,
        val posteOffert: String,
        val description: String,
        val estRémunéré: Boolean,
        val datePost: LocalDate,
        val estVisible: Boolean,
        val employeur: Employeur?,
        val catégorie: Categorie?

)

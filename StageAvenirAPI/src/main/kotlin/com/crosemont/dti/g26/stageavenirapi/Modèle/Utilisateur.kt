package com.crosemont.dti.g26.stageavenirapi.Modèle

import com.crosemont.dti.g26.stageavenirapi.Modèle.Enum.Role

class Utilisateur (
    val idutilisateur: Int?,
    val nom: String?,
    val prenom: String?,
    val courriel: String?,
    val telephone: String?,
    val ville: String?,
    val categorie: Categorie?,
    val role: Role?

)
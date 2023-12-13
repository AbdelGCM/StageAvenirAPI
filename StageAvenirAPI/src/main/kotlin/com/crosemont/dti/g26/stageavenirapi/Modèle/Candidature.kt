package com.crosemont.dti.g26.stageavenirapi.Modèle

import com.crosemont.dti.g26.stageavenirapi.Modèle.Enum.Etat

data class Candidature(
    val idCandidature: Int,
    val etat: Etat,
    val commentaire: String?,
    val offre: OffreStage?,
    val etudiant : Etudiant?
){

}

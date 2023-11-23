package com.crosemont.dti.g26.stageavenirapi.Modèle

data class Candidature(
    val idCandidature: Int,
    val acceptée: Boolean?,
    val commentaire: String?,
    val offreStageIdOffreStage: Int?,
    val offreStageEntrepriseIdEntreprise: Int?,
    val demandeStageIdDemandeStage: Int,
    val demandeStageEtudiantIdEtudiant: Int,
    val demandeStageEtudiantCvIdCv: Int
)

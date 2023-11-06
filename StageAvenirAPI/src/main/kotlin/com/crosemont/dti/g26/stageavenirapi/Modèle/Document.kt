package com.crosemont.dti.g26.stageavenirapi.Modèle

data class Document(
    val idDocument: Int,
    val nom: String,
    val url: String,
    val etudiantIdEtudiant: Int,
    val etudiantCvIdCv: Int,
    val candidatureIdCandidature: Int,
    val candidatureOffreStageIdOffreStage: Int,
    val candidatureOffreStageEntrepriseIdEntreprise: Int,
    val candidatureDemandeStageIdDemandeStage: Int,
    val candidatureDemandeStageEtudiantIdEtudiant: Int,
    val candidatureDemandeStageEtudiantCvIdCv: Int
)

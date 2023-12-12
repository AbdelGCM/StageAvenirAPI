package com.crosemont.dti.g26.stageavenirapi.Controleurs

import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature
import com.crosemont.dti.g26.stageavenirapi.Modèle.Document
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CandidatureControleur {

    @GetMapping("/etudiant/{id}/candidatures")
    fun obtenirCandidaturesParEtudiant(@PathVariable codeUtilisateur: Int){

    }

    @PutMapping("/employeur/{id_employeur}/offresStages/{id_offre}/candidatures")
    fun obtenirCandidaturesParOffreDeStage(){


    }

    @PutMapping("/etudiant/{id_etudiant}/candidatures/{id_candidature}")
    fun annulerCandidature(@PathVariable code_etudiant: Int, codeCandidature : Int){
    }

    @PostMapping("/etudiant/{id}/offresStages/{id_offre}/candidature")
    fun posterCandidature(@PathVariable candidature: Candidature, documents : List<Document>, codeEtudiant : Int){
    }


}
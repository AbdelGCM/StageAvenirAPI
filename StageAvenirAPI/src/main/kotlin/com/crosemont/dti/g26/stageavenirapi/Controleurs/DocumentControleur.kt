package com.crosemont.dti.g26.stageavenirapi.Controleurs

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DocumentControleur {
    @GetMapping("/documents")
    fun obtenirTousLesDocuments(){
    }


    @GetMapping("/employeur/offresStage/{offre_id}/candidatures/{candidature_id}/documents")
    fun obtenirLesDocumentsParCandidature(){
    }

    @GetMapping("/etudiant/demandesStages/{demande_id}/documents")
    fun obtenirLesDocumentsParDemandeDeStage(){
    }




}
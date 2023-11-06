package com.crosemont.dti.g26.stageavenirapi.controlleurs

import com.crosemont.dti.g26.stageavenirapi.modèle.Candidature
import com.crosemont.dti.g26.stageavenirapi.modèle.Document
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CandidatureControlleur {

    @GetMapping("/candidatures")
    fun obtenirCandidatures(@PathVariable code: Int){
    }

    @PutMapping("/candidatures/{id}")
    fun annulerCandidature(@PathVariable code: Int){
    }

    @PostMapping("/candidature")
    fun posterCandidature(@PathVariable candidature: Candidature, documents : List<Document>, codeEtudiant : Int){


    }
}
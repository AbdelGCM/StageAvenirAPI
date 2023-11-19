package com.crosemont.dti.g26.stageavenirapi.Controleurs

import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature
import com.crosemont.dti.g26.stageavenirapi.Modèle.Document
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CandidatureControlleur {

    @GetMapping("/candidatures")
    fun obtenirCandidatures(@PathVariable codeUtilisateur: Int){

    }

    @PutMapping("/candidature")
    fun annulerCandidature(@PathVariable code: Int, @PathVariable candidature: Candidature){
    }

    @PostMapping("/candidature")
    fun posterCandidature(@PathVariable candidature: Candidature, documents : List<Document>, codeEtudiant : Int){


    }
}
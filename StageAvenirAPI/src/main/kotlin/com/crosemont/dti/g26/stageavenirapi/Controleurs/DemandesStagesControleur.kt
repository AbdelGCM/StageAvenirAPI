package com.crosemont.dti.g26.stageavenirapi.Controleurs
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class DemandesStagesControleur {



    @GetMapping("/demandes")
    fun obtenirDemandes() {
    }

    @PostMapping("/demande")
    fun ajouterDemande(@RequestBody demande: DemandesStagesControleur) {
    }

    @PutMapping("/demande/{code}")
    fun modifierStatutDemande(@RequestBody demande: DemandesStagesControleur, statut : Boolean) {
    }
}
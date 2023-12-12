package com.crosemont.dti.g26.stageavenirapi.Controleurs
import com.crosemont.dti.g26.stageavenirapi.Modèle.Categorie
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Demandes_StagesControlleur {



    @GetMapping("/demandes")
    fun obtenirDemandes() {
    }

    @PostMapping("/demande")
    fun ajouterDemande(@RequestBody demande: Demandes_StagesControlleur) {
    }

    @PutMapping("/demande/{code}")
    fun modifierStatutDemande(@RequestBody demande: Demandes_StagesControlleur, statut : Boolean) {
    }
}
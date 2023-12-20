package com.crosemont.dti.g26.stageavenirapi.Controleurs

import com.crosemont.dti.g26.stageavenirapi.Exceptions.RessourceInexistanteException
import com.crosemont.dti.g26.stageavenirapi.Modèle.DemandeStage
import com.crosemont.dti.g26.stageavenirapi.Service.ServiceDemandeDeStage
import com.crosemont.dti.g26.stageavenirapi.Service.ServiceOffreDeStage
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Demandes_StagesControlleur(val service: ServiceDemandeDeStage) {
    @GetMapping
    fun obtenirToutesDemandesStage() = service.obtenirTouteswDemandesStage()

    @GetMapping("/demandeStage/{code}")
    fun obtenirDemandeStageParCode(@PathVariable code: Int) =
        service.obtenirDemandeParId(code) ?: throw RessourceInexistanteException("La demande de stage $code n'est pas inscrite au service.")

    @PostMapping
    fun ajouterDemandeStage(@RequestBody demande: DemandeStage): ResponseEntity<DemandeStage> {
        val nouvelleDemande = service.ajouterDemande(demande)

        return nouvelleDemande?.let {
            val uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{code}")
                .buildAndExpand(it.idDemandeStage)
                .toUri()

            ResponseEntity.created(uri).body(it)
        } ?: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
    }

    @DeleteMapping("/{code}")
    fun supprimerDemandeStage(@PathVariable code: Int): ResponseEntity<Void> {
        service.effacerDemande(code)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/{code}")
    fun modifierDemandeStage(@PathVariable code: Int, @RequestBody demande: DemandeStage): ResponseEntity<DemandeStage> {
        val nouvelleDemande = service.modifierDemande(code, demande)

        return nouvelleDemande?.let {
            val uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{code}")
                .buildAndExpand(it.idDemandeStage)
                .toUri()

            ResponseEntity.created(uri).body(it)
        } ?: ResponseEntity.ok(demande)

    }
}
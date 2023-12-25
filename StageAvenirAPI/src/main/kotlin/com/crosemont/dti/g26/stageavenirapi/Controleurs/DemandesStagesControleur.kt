package com.crosemont.dti.g26.stageavenirapi.Controleurs

import com.crosemont.dti.g26.stageavenirapi.Exceptions.RessourceInexistanteException
import com.crosemont.dti.g26.stageavenirapi.Modèle.DemandeStage
import com.crosemont.dti.g26.stageavenirapi.Service.ServiceDemandeDeStage
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
class DemandesStagesControleur(val service: ServiceDemandeDeStage) {
    @GetMapping("/demandeStages")
    fun obtenirToutesDemandesStage() = service.obtenirToutesDemandesStage()

    @GetMapping("/demandeStage/{code}")
    fun obtenirDemandeStageParCode(@PathVariable code: Int) =
        service.obtenirDemandeParId(code) ?: throw RessourceInexistanteException("La demande de stage $code n'est pas inscrite au service.")

    @PostMapping("/employeur/{employeur_id}/demande_Stage")
    fun ajouterDemandeStage(@RequestBody demande: DemandeStage, @PathVariable employeur_id: String): ResponseEntity<DemandeStage> {
        val nouvelleDemande = service.ajouterDemande(demande)

        if (nouvelleDemande != null) {
            val uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{code}")
                .buildAndExpand(nouvelleDemande.idDemandeStage)
                .toUri()

            return ResponseEntity.created(uri).body(nouvelleDemande)
        }
        return ResponseEntity.internalServerError().build()
    }

    @DeleteMapping("/demandeStage/{code}")
    fun supprimerDemandeStage(@PathVariable code: Int): ResponseEntity<Void> {
        service.effacerDemande(code)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/demandeStage/{code}")
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
    @PutMapping("/demandeStage/status/{code}")
    fun modifierStatus(@PathVariable code: Int,@RequestBody demande: DemandeStage): ResponseEntity<DemandeStage>{
        val nouvelleDemande = service.modifierStatus(code, demande)
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
package com.crosemont.dti.g26.stageavenirapi.Controleurs

import com.crosemont.dti.g26.stageavenirapi.Exceptions.RessourceInexistanteException
import com.crosemont.dti.g26.stageavenirapi.Modèle.OffreStage
import com.crosemont.dti.g26.stageavenirapi.Service.ServiceOffreDeStage
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.security.Principal

@RestController
class OffresStageControleur(val service: ServiceOffreDeStage) {

    @GetMapping("/offres_Stages")
    fun obtenirOffresStages() = service.obtenirOffresStage()

    @GetMapping("/offres_Stages/{code}")
    fun obtenirOffresStagesParCode(@PathVariable code: Int) = service.obtenirOffreParCode(code) ?: throw RessourceInexistanteException("L'offre $code n'est pas inscrite au service.")

    @GetMapping("etudiant/offres_Stages")
    fun obtenirOffresStagesParCatégorie( principal: Principal? ) : List<OffreStage>? {
        return principal?.let { service.obtenirOffresParCatégorie(it.name) }
    }


    @PostMapping("/employeur/entreprise/{idEntreprise}/offresStages")
    fun ajouterOffreStage(@RequestBody offre: OffreStage, @PathVariable idEntreprise : Int, principal: Principal? ): ResponseEntity<OffreStage> {
        val nouvelleOffre = principal?.let { service.ajouter(it.name,idEntreprise,offre) }

        if (nouvelleOffre != null) {
            val uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{code}")
                    .buildAndExpand(nouvelleOffre.idOffreStage)
                    .toUri()

            return ResponseEntity.created(uri).body(nouvelleOffre)
        }
        return ResponseEntity.internalServerError().build()
    }

    @DeleteMapping("/offres_Stage/{code}")
    fun supprimerOffreStage(@PathVariable code: Int): ResponseEntity<OffreStage>{
        service.effacer(code)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("offres_Stage/{code}")
    fun modifierOffreStage(@PathVariable code: Int, @RequestBody offre: OffreStage):ResponseEntity<OffreStage>{
        val nouvelOffre = service.modifier(code, offre)

        if (nouvelOffre != null) {
            val uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{code}")
                    .buildAndExpand(nouvelOffre.idOffreStage)
                    .toUri()

            return ResponseEntity.created(uri).body(nouvelOffre)
        }
        return ResponseEntity.ok(offre)
    }
    @GetMapping("coordonateur/demandesPublication")
    fun obtenirsOffresStagesEncoursDeValidation (principal: Principal?): ResponseEntity<List<OffreStage>>? {
        var listeOffres = principal?.let { service.obtenirStagesEnCoursDeValidationPourUnePublication(it.name) }
        if (principal != null) {
            val uri = listeOffres?.let {
                ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{code}")
                    .buildAndExpand(it.size)
                    .toUri()
            }

            return uri?.let { ResponseEntity.created(it).body(listeOffres) }
        }
        return ResponseEntity.internalServerError().build()

    }
    @PutMapping("coordonnateur/demandesPublication/{idOffre}/accepter")
    fun accepterPublicationOffreStage(@PathVariable idOffre : String, principal: Principal?): ResponseEntity<OffreStage> {
        val offreAcceptée = principal?.let { service.approuverPublicationDuneOffre(it.name, idOffre.toInt()) }

        if (offreAcceptée != null) {
            val uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{code}")
                .buildAndExpand(offreAcceptée.idOffreStage)
                .toUri()

            return ResponseEntity.created(uri).body(offreAcceptée)
        }
        return ResponseEntity.ok(offreAcceptée)
    }
    @PutMapping("coordonnateur/demandesPublication/{idOffre}/refuser")
    fun refuserPublicationOffreStage(@PathVariable idOffre : String, principal: Principal?): ResponseEntity<OffreStage> {
        val offreRefusée = principal?.let { service.refuserPublicationDuneOffre(it.name, idOffre.toInt()) }

        if (offreRefusée != null) {
            val uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{code}")
                .buildAndExpand(offreRefusée.idOffreStage)
                .toUri()

            return ResponseEntity.created(uri).body(offreRefusée)
        }
        return ResponseEntity.ok(offreRefusée)
    }



}
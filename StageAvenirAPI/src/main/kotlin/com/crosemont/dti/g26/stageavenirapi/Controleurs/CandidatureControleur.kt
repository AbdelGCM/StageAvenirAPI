package com.crosemont.dti.g26.stageavenirapi.Controleurs

import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature
import com.crosemont.dti.g26.stageavenirapi.Modèle.Document
import com.crosemont.dti.g26.stageavenirapi.Modèle.OffreStage
import com.crosemont.dti.g26.stageavenirapi.Service.ServiceOffreDeStage
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.security.Principal

@RestController
class CandidatureControleur(val service : ServiceOffreDeStage) {

    @GetMapping("/etudiant/candidatures")
    fun obtenirCandidaturesParEtudiant(principal: Principal?): ResponseEntity<List<Candidature>>? {
        var listeCandidature = principal?.let { service.obtenirCandidaturesParEtudiant(it.name) }
        if (principal != null) {
            val uri = listeCandidature?.let {
                ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{code}")
                    .buildAndExpand(it.size)
                    .toUri()
            }

            return uri?.let { ResponseEntity.created(it).body(listeCandidature) }
        }
        return ResponseEntity.internalServerError().build()
    }

    @GetMapping("/employeur/offresStages/{id_offre}/candidatures")
    fun obtenirCandidaturesParOffreDeStage(@PathVariable id_offre:String , principal: Principal? ): ResponseEntity<List<Candidature>>? {
        var listeCandidature = principal?.let { service.obtenirCandidaturesParOffreStage(id_offre.toInt(), it.name) }
        if (id_offre != null) {
            val uri = listeCandidature?.let {
                ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{code}")
                    .buildAndExpand(it.size)
                    .toUri()
            }

            return uri?.let { ResponseEntity.created(it).body(listeCandidature) }
        }
        return ResponseEntity.internalServerError().build()
    }

    @PutMapping("/etudiant/candidatures/{id_candidature}")
    fun annulerCandidature(principal: Principal?,@PathVariable id_candidature : String): ResponseEntity<Candidature>?{
        var resultat = principal?.let { service.annulerCandidature(id_candidature.toInt(), it.name ) }
        if (id_candidature != null) {
            val uri = resultat?.let {
                ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{code}")
                    .buildAndExpand(it.idCandidature)
                    .toUri()
            }

            return uri?.let { ResponseEntity.created(it).body(resultat) }
        }

        return ResponseEntity.internalServerError().build()
    }
    @Operation(
        summary = "Poster une Candidature.",
        description = "Inscrit un restaurant au service.",
        operationId = "inscrireRestaurant",
        responses = [
            ApiResponse(responseCode = "201", description = "La candidature  a bien été envoyée."),
            ApiResponse(responseCode = "400", description = "La requête est mal formulée."),
            ApiResponse(responseCode = "401", description = "L'utilisateur voulant effectuer l'opération n'est pas correctement authentifié."),
            ApiResponse(responseCode = "403", description = "L'utilisateur voulant effectuer l'opération n'a pas les droits nécessaires."),
            ApiResponse(responseCode = "409", description = "La candidature a  déjà au service.")
        ])
    @PostMapping("/etudiant/offresStages/{id_offre}/candidature")
    fun posterCandidature(@RequestBody candidature: Candidature, principal: Principal? , @PathVariable id_offre  :String) : ResponseEntity<Candidature>? {
         var nouveauCandidature = principal?.let { service.postulerPourUneOffre(it.name  ,id_offre.toInt(), candidature ) }

        if (candidature != null) {
            val uri = nouveauCandidature?.let {
                ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{code}")
                    .buildAndExpand(it.idCandidature)
                    .toUri()
            }

            return uri?.let { ResponseEntity.created(it).body(nouveauCandidature) }
        }

        return ResponseEntity.internalServerError().build()
    }

   @PutMapping("/employeur/offresStages/{idOffre}/candidatures/{id_candidature}/accepter")
    fun accepterUneCandidature(@PathVariable candidature:Candidature, principal: Principal?):ResponseEntity<Candidature>?{
       println("controlleur : " + candidature)
        var nouvelleCandidature = principal?.let { service.accepterCandidature(candidature, it.name) }
       if (candidature != null) {
           val uri = nouvelleCandidature?.let {
               ServletUriComponentsBuilder
                   .fromCurrentRequest()
                   .path("/{code}")
                   .buildAndExpand(it.idCandidature)
                   .toUri()
           }

           return uri?.let { ResponseEntity.created(it).body(nouvelleCandidature) }
       }

       return ResponseEntity.internalServerError().build()
    }

    @PutMapping("/employeur/offresStages/Offre/candidatures/{id_candidature}/refuser")
    fun refuserUneCandidature(@PathVariable id_candidature:String, principal: Principal?):ResponseEntity<Candidature>?{
        var candidature_refusée = principal?.let { service.refuserCandidature(id_candidature.toInt(), it.name) }
        if (id_candidature != null) {
            val uri = candidature_refusée?.let {
                ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{code}")
                    .buildAndExpand(it.idCandidature)
                    .toUri()
            }
            return uri?.let { ResponseEntity.created(it).body(candidature_refusée) }
        }

        return ResponseEntity.internalServerError().build()
    }

     


}
package com.crosemont.dti.g26.stageavenirapi.Controleurs

import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature
import com.crosemont.dti.g26.stageavenirapi.Modèle.Document
import com.crosemont.dti.g26.stageavenirapi.Modèle.OffreStage
import com.crosemont.dti.g26.stageavenirapi.Service.ServiceOffreDeStage
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
class CandidatureControleur(val service : ServiceOffreDeStage) {

    @GetMapping("/etudiant/{codeUtilisateur}/candidatures")
    fun obtenirCandidaturesParEtudiant(@PathVariable codeUtilisateur: String): ResponseEntity<List<Candidature>>? {
        var listeCandidature =  service.obtenirCandidaturesParEtudiant(codeUtilisateur.toInt())
        if (codeUtilisateur != null) {
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

    @GetMapping("/employeur/{id_employeur}/offresStages/{id_offre}/candidatures")
    fun obtenirCandidaturesParOffreDeStage(@PathVariable id_offre:String ): ResponseEntity<List<Candidature>>? {
        var listeCandidature = service.obtenirCandidaturesParDemandeStage(id_offre.toInt())
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

    @PutMapping("/etudiant/{id_etudiant}/candidatures/{id_candidature}")
    fun annulerCandidature(@PathVariable id_etudiant: String,@PathVariable id_candidature : String): ResponseEntity<Candidature>?{
        var resultat =  service.annulerCandidature(id_candidature.toInt())
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

    @PostMapping("/etudiant/{id}/offresStages/{id_offre}/candidature")
    fun posterCandidature(@RequestBody candidature: Candidature, @PathVariable id : String , @PathVariable id_offre  :String) : ResponseEntity<Candidature>? {
         var nouveauCandidature = service.postulerPourUneOffre(id.toInt()  ,id_offre.toInt(), candidature, id.toInt())

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

   @PutMapping("/employeur/{id_employeur}/offresStages/{idOffre}/candidatures/{id_candidature}/accepter")
    fun accepterUneCandidature(@RequestBody candidature:Candidature):ResponseEntity<Candidature>?{
       println("controlleur : " + candidature)
        var nouvelleCandidature =  service.accepterCandidature(candidature)
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

    @PutMapping("/employeur/{id_employeur}/offresStages/{idOffre}/candidatures/{id_candidature}/refuser")
    fun refuserUneCandidature(@PathVariable id_candidature:String):ResponseEntity<Candidature>?{
        var candidature_refusée =  service.refuserCandidature(id_candidature.toInt())
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
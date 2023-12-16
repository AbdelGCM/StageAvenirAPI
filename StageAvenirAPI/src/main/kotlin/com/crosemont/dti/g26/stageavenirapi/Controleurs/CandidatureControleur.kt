package com.crosemont.dti.g26.stageavenirapi.Controleurs

import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature
import com.crosemont.dti.g26.stageavenirapi.Modèle.Document
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
    fun obtenirCandidaturesParEtudiant(@PathVariable codeUtilisateur: String):List<Candidature>{
        return service.obtenirCandidaturesParEtudiant(codeUtilisateur.toInt())
    }

    @GetMapping("/employeur/{id_employeur}/offresStages/{id_offre}/candidatures")
    fun obtenirCandidaturesParOffreDeStage(@PathVariable id_offre:String ):List<Candidature>{
        return service.obtenirCandidaturesParDemandeStage(id_offre.toInt())
    }

    @PutMapping("/etudiant/{id_etudiant}/candidatures/{id_candidature}")
    fun annulerCandidature(@PathVariable id_etudiant: String,@PathVariable id_candidature : String):Candidature?{
        return service.annulerCandidature(id_candidature.toInt())
    }

    @PostMapping("/etudiant/{id}/offresStages/{id_offre}/candidature")
    fun posterCandidature(@RequestBody candidature: Candidature, @PathVariable id : String , @PathVariable id_offre  :String) : ResponseEntity<Candidature>? {
        println(candidature.toString())
        println(candidature.documents.toString())
         var nouveauCandidature = candidature.documents?.let {
            service.postulerPourUneOffre(id.toInt()  ,id_offre.toInt(), candidature,
                it
            )
        }
        println(nouveauCandidature.toString())
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
    fun accepterUneCandidature(@PathVariable id_candidature:String):Candidature?{
        return service.accepterCandidature(id_candidature.toInt())
    }

    @PutMapping("/employeur/{id_employeur}/offresStages/{idOffre}/candidatures/{id_candidature}/refuser")
    fun refuserUneCandidature(@PathVariable id_candidature:String):Candidature?{
        return service.refuserCandidature(id_candidature.toInt())
    }


}
package com.crosemont.dti.g26.stageavenirapi.Controleurs

import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature
import com.crosemont.dti.g26.stageavenirapi.Modèle.Document
import com.crosemont.dti.g26.stageavenirapi.Service.ServiceOffreDeStage
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CandidatureControleur(val service : ServiceOffreDeStage) {

    @GetMapping("/etudiant/{codeUtilisateur}/candidatures")
    fun obtenirCandidaturesParEtudiant(@PathVariable codeUtilisateur: Int):List<Candidature>{
        return service.obtenirCandidaturesParEtudiant(codeUtilisateur)
    }

    @GetMapping("/employeur/{id_employeur}/offresStages/{id_offre}/candidatures")
    fun obtenirCandidaturesParOffreDeStage(@PathVariable id_offre:Int ):List<Candidature>{
        return service.obtenirCandidaturesParOffreStage(id_offre)
    }

    @PutMapping("/etudiant/{id_etudiant}/candidatures/{id_candidature}")
    fun annulerCandidature(@PathVariable id_etudiant: Int, id_candidature : Int):Candidature?{
        return service.annulerCandidature(id_candidature)
    }

    @PostMapping("/etudiant/{id}/offresStages/{id_offre}/candidature")
    fun posterCandidature(@PathVariable candidature: Candidature, documents : List<Document>, codeEtudiant : Int , id_offre :Int):Candidature?{
        var candidature = candidature
        return service.postulerPourUneOffre(codeEtudiant  ,id_offre, candidature, documents)
    }

   @PutMapping("/employeur/{id_employeur}/offresStages/{idOffre}/candidatures/{id_candidature}")
    fun accepterUneCandidature(@PathVariable id_candidature:Int):Candidature?{
        return service.accepterCandidature(id_candidature)
    }

    @PutMapping("/employeur/{id_employeur}/offresStages/{idOffre}/candidatures/{id_candidature}")
    fun refuserUneCandidature(@PathVariable id_candidature:Int):Candidature?{
        return service.refuserCandidature(id_candidature)
    }


}
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

@RestController
class OffresStageControleur(val service: ServiceOffreDeStage) {

    @GetMapping("/offres_Stages")
    fun obtenirOffresStages() = service.obtenirOffresStage()

    @GetMapping("/offres_Stages/{code}")
    fun obtenirOffresStagesParCode(@PathVariable code: Int) = service.obtenirOffreParCode(code) ?: throw RessourceInexistanteException("L'offre $code n'est pas inscrite au service.")

    @GetMapping("offres_Stages/offresParCategorie/{categorie_id}")
    fun obtenirOffresStagesParCatégorie(@PathVariable categorie_id: Int ) = service.obtenirOffresParCatégorie(categorie_id) ?: throw RessourceInexistanteException("La catégorie $categorie_id n'est pas inscrite au service.")

    @PostMapping("/employeur/entreprise/{id}/offresStages")
    fun ajouterOffreStage(@RequestBody offre: OffreStage, @PathVariable id : Int ): ResponseEntity<OffreStage> {

        val nouvelleOffre = service.ajouter(id,offre)

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

}
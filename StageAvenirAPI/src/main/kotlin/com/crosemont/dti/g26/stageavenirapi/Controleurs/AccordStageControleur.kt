package com.crosemont.dti.g26.stageavenirapi.Controleurs

import com.crosemont.dti.g26.stageavenirapi.Modèle.AccordStage
import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature
import com.crosemont.dti.g26.stageavenirapi.Service.ServiceOffreDeStage
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.security.Principal

@RestController
class AccordStageControleur(val service :ServiceOffreDeStage)  {

    @GetMapping("coordonnateur/accordStages")
    fun obtenirAccordsDeStage(principal: Principal?): ResponseEntity<List<AccordStage>>?{
        var listeAccord = principal?.name?.let { service.obtenirAccords(it) }
        if (principal != null) {
            val uri = listeAccord?.let {
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{code}")
                        .buildAndExpand(it.size)
                        .toUri()
            }

            return uri?.let { ResponseEntity.created(it).body(listeAccord) }
        }

        return ResponseEntity.internalServerError().build()
    }
    @GetMapping("coordonnateur/accordStages/{idAccord}")
    fun obtenirAccordsDeStageParId(principal: Principal?, @PathVariable idAccord: String): ResponseEntity<AccordStage>?{
        var accord = principal?.name?.let { service.obtenirAccordParCode(principal.name, idAccord.toInt()) }
        if (principal != null) {
            val uri = accord?.let {
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{code}")
                        .buildAndExpand(it)
                        .toUri()
            }

            return uri?.let { ResponseEntity.created(it).body(accord) }
        }

        return ResponseEntity.internalServerError().build()
    }

    @PutMapping("coordonnateur/accordStages/{idAccordStage}/approuver")
    fun approuverAccordStage(@PathVariable idAccordStage:String, principal: Principal? ): ResponseEntity<AccordStage>? {
        var resultat = principal?.let { service.approuverAccordStage(it.name,idAccordStage.toInt()) }
        if (idAccordStage != null) {
            val uri = resultat?.let {
                ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{code}")
                    .buildAndExpand(it.idAccord)
                    .toUri()
            }

            return uri?.let { ResponseEntity.created(it).body(resultat) }
        }

        return ResponseEntity.internalServerError().build()

    }

    @PutMapping("coordonnateur/accordStages/{idAccordStage}/desaprouver")
    fun désapprouverAccordStage(@PathVariable idAccordStage:String, principal: Principal?): ResponseEntity<AccordStage>? {

        var resultat = principal?.let { service.refuserAccordStage(it.name,idAccordStage.toInt()) }
        if (idAccordStage != null) {
            val uri = resultat?.let {
                ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{code}")
                    .buildAndExpand(it.idAccord)
                    .toUri()
            }

            return uri?.let { ResponseEntity.created(it).body(resultat) }
        }

        return ResponseEntity.internalServerError().build()
    }

}
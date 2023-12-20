package com.crosemont.dti.g26.stageavenirapi.Controleurs

import com.crosemont.dti.g26.stageavenirapi.Modèle.AccordStage
import com.crosemont.dti.g26.stageavenirapi.Service.ServiceOffreDeStage
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
class AccordStageControleur(val service :ServiceOffreDeStage) {
    @PutMapping("coordonnateur/{idCoordonnateur}/accordStages/{idAccordStage}/approuver")
    fun approuverAccordStage(@PathVariable idAccordStage:String ): ResponseEntity<AccordStage>? {
        var resultat = service.approuverAccordStage(idAccordStage.toInt())
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

    @PutMapping("coordonnateur/{idCoordonnateur}/accordStages/{idAccordStage}/desaprouver")
    fun désapprouverAccordStage(@PathVariable idAccordStage:String): ResponseEntity<AccordStage>? {

        var resultat = service.refuserAccordStage(idAccordStage.toInt())
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
package com.crosemont.dti.g26.stageavenirapi.Controleurs

import com.crosemont.dti.g26.stageavenirapi.Modèle.AccordStage
import com.crosemont.dti.g26.stageavenirapi.Service.ServiceOffreDeStage
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AccordStageControleur(val service :ServiceOffreDeStage) {
    @PutMapping("coordonnateur/{idCoordonnateur}/accordStages/{idAccordStage}/approuver")
    fun approuverAccordStage(@PathVariable idAccordStage:String ):AccordStage?{
        return service.approuverAccordStage(idAccordStage.toInt())
    }

    @PutMapping("coordonnateur/{idCoordonnateur}/accordStages/{idAccordStage}/desaprouver")
    fun désapprouverAccordStage(@PathVariable idAccordStage:String):AccordStage?{
        return service.refuserAccordStage(idAccordStage.toInt())
    }
}
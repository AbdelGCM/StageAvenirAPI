package com.crosemont.dti.g26.stageavenirapi.Controleurs

import com.crosemont.dti.g26.stageavenirapi.Modèle.AccordStage
import com.crosemont.dti.g26.stageavenirapi.Service.ServiceOffreDeStage
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AccordStageControleur(val service :ServiceOffreDeStage) {

    @GetMapping("coordonnateur/{idCoordonnateur}/accordStages")
    fun obtenirAccordsDeStage(@PathVariable idCoordonnateur:String){
        service.obtenirAccordsParCategorie(idCoordonnateur.toInt())
    }
    @PutMapping("coordonnateur/{idCoordonnateur}/accordStages/{idAccordStage}/approuver")
    fun approuverAccordStage(@PathVariable idAccordStage:String ):AccordStage?{
        return service.approuverAccordStage(idAccordStage.toInt())
    }

    @PutMapping("coordonnateur/{idCoordonnateur}/accordStages/{idAccordStage}/desaprouver")
    fun DésapprouverAccordStage(@PathVariable idAccordStage:String):AccordStage?{
        return service.refuserAccordStage(idAccordStage.toInt())
    }

}
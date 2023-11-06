package com.crosemont.dti.g26.stageavenirapi.controlleurs

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Offres_StageControlleur {

    @GetMapping("/offres_Stages")
    fun obtenirOffresStages(){
    }

    @GetMapping("/offres_Stages/{code}")
    fun obtenirOffresStagesParCode(){
    }




}
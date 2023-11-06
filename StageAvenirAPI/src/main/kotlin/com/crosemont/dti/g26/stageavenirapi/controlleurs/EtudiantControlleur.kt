package com.crosemont.dti.g26.stageavenirapi.controlleurs

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class EtudiantControlleur {

    @PostMapping("/profil")
    fun changerPhoto() {
    }

    @PostMapping("/profil/info")
    fun changerInfos(){}
}
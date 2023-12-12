package com.crosemont.dti.g26.stageavenirapi.controlleurs

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class APIControleur {

    @GetMapping("/")
    fun index() = "Service web du service de gestion de stages"

}
package com.crosemont.dti.g26.stageavenirapi.controlleurs

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class EntrepriseControleur {

    @PostMapping("/employeur/{employeur_id}/entreprises")
    fun ajouterEntreprise(@PathVariable employeur_id : String ){}



}
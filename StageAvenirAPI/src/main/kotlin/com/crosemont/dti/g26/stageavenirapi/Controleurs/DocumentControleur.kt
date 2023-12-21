/* package com.crosemont.dti.g26.stageavenirapi.Controleurs

import com.crosemont.dti.g26.stageavenirapi.DAO.DocumentDAOImplement
import com.crosemont.dti.g26.stageavenirapi.Modèle.Document
import com.crosemont.dti.g26.stageavenirapi.Modèle.Enum.Type
import com.crosemont.dti.g26.stageavenirapi.Service.ServiceGestionUtilisateur
import com.crosemont.dti.g26.stageavenirapi.Service.ServiceOffreDeStage
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class DocumentControleur(var service : ServiceGestionUtilisateur) {
    @GetMapping("/documents")
    fun obtenirTousLesDocuments():List<Document>{
        return service.récupérerTousLesDocuments()
    }


    @GetMapping("/employeur/offresStage/{offre_id}/candidatures/{candidature_id}/documents")
    fun obtenirLesDocumentsParCandidature(@PathVariable candidature_id:String):List<Document>{
        return service.récupérerDocumentsParCandidatures(candidature_id.toInt())
    }

    @GetMapping("/etudiant/demandesStages/{demande_id}/documents")
    fun obtenirLesDocumentsParDemandeDeStage(@PathVariable demande_id:String ):List<Document>{
        return service.récupérerDocumentsParDemandeDeStage(demande_id.toInt())
    }


    @PostMapping("/etudiant/{id_etudiant}/profil/cv")
    fun ajouterUnCv(@PathVariable id_etudiant:String, @RequestBody cv:Document):Document?{
        return service.ajouterUnCv(cv,id_etudiant.toInt())
    }
    @PutMapping("/etudiant/{id_etudiant}/profil/cv")
    fun modifierUnCv(@PathVariable id_etudiant:String, @RequestBody cv:Document):Document?{
        return service.modifierUnCv(cv)
    }




} */
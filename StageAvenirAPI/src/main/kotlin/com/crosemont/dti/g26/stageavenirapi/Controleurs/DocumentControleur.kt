package com.crosemont.dti.g26.stageavenirapi.Controleurs

import com.crosemont.dti.g26.stageavenirapi.DAO.DocumentDAOImplement
import com.crosemont.dti.g26.stageavenirapi.Modèle.Document
import com.crosemont.dti.g26.stageavenirapi.Modèle.Enum.Type
import com.crosemont.dti.g26.stageavenirapi.Service.ServiceGestionUtilisateur
import com.crosemont.dti.g26.stageavenirapi.Service.ServiceOffreDeStage
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
class DocumentControleur(var service : ServiceGestionUtilisateur) {
    @GetMapping("/documents")
    fun obtenirTousLesDocuments(): ResponseEntity<List<Document>>? {
        var listeDocuments =  service.récupérerTousLesDocuments()
        if (listeDocuments.isNotEmpty()) {
            val uri = listeDocuments?.let {
                ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{code}")
                    .buildAndExpand(it.size)
                    .toUri()
            }

            return uri?.let { ResponseEntity.created(it).body(listeDocuments) }
        }
        return ResponseEntity.internalServerError().build()
    }


    @GetMapping("/employeur/offresStage/{offre_id}/candidatures/{candidature_id}/documents")
    fun obtenirLesDocumentsParCandidature(@PathVariable candidature_id:String): ResponseEntity<List<Document>>? {
        var listeDocuments = service.récupérerDocumentsParCandidatures(candidature_id.toInt())
        if (listeDocuments != null) {

            val uri = listeDocuments?.let {
                    ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{code}")
                        .buildAndExpand(it.size)
                        .toUri()
            }

                return uri?.let { ResponseEntity.created(it).body(listeDocuments) }

        }
        return ResponseEntity.internalServerError().build()
    }

    @GetMapping("/etudiant/demandesStages/{demande_id}/documents")
    fun obtenirLesDocumentsParDemandeDeStage(@PathVariable demande_id:String ): ResponseEntity<List<Document>>? {
        var listeDocuments = service.récupérerDocumentsParDemandeDeStage(demande_id.toInt())
        if (listeDocuments != null) {

            val uri = listeDocuments?.let {
                ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{code}")
                    .buildAndExpand(it.size)
                    .toUri()
            }

            return uri?.let { ResponseEntity.created(it).body(listeDocuments) }

        }
        return ResponseEntity.internalServerError().build()
    }


    @PostMapping("/etudiant/{id_etudiant}/profil/cv")
    fun ajouterUnCv(@PathVariable id_etudiant:String, @RequestBody cv:Document): ResponseEntity<Document>? {
        var cv_ajouté =  service.ajouterUnCv(cv,id_etudiant.toInt())
        if (cv != null) {

            val uri = cv_ajouté?.let {
                ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{code}")
                    .buildAndExpand(it)
                    .toUri()
            }

            return uri?.let { ResponseEntity.created(it).body(cv_ajouté) }

        }
        return ResponseEntity.internalServerError().build()
    }
    @PutMapping("/etudiant/{id_etudiant}/profil/cv")
    fun modifierUnCv(@PathVariable id_etudiant:String, @RequestBody cv:Document): ResponseEntity<Document>? {
        var  cv_modifié =   service.modifierUnCv(cv)
        if (cv != null) {

            val uri = cv_modifié?.let {
                ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{code}")
                    .buildAndExpand(it)
                    .toUri()
            }

            return uri?.let { ResponseEntity.created(it).body(cv_modifié) }

        }
        return ResponseEntity.internalServerError().build()
    }
    @GetMapping("/etudiant/{id_etudiant}/profil/cv")
    fun obtenirLeCV(@PathVariable id_etudiant:String, @RequestBody cv:Document): ResponseEntity<Document>? {
        var  cv_modifié =   service.modifierUnCv(cv)
        if (cv != null) {

            val uri = cv_modifié?.let {
                ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{code}")
                    .buildAndExpand(it)
                    .toUri()
            }

            return uri?.let { ResponseEntity.created(it).body(cv_modifié) }

        }
        return ResponseEntity.internalServerError().build()
    }




}
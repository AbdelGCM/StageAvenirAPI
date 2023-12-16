package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.DemandeStage
import com.crosemont.dti.g26.stageavenirapi.Modèle.Document
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class DocumentDAOImplement (val bd : JdbcTemplate): DocumentDAO {

    override fun ajouterDocument(element: Document, code: Int): Document? {
        var sql: String? = null
        if (element.type.toString() == "cv"){
            sql = "INSERT INTO document (nom,type,contenu,utilisateur_idutilisateur ) VALUES (?, ?, ?)"
        }
        var idDocument = bd.update(
            "INSERT INTO document (nom,type,contenu) VALUES (?, ?, ?)",
            element.nom,
            element.type.toString(),
            element.contenu
            // element.offreStageIdOffreStage
        )

        if (idDocument > 0){
            return  chercherParCode(idDocument)
        }else{
            return null
        }
    }

    override fun modifier(id: Int, element: Document): Document? {
        TODO("Not yet implemented")
    }

    override fun effacer(code: Int) {
        TODO("Not yet implemented")
    }

    override fun ajouter(element: Document): Document? {
        TODO("Not yet implemented")
    }

    override fun chercherParCode(code: Int): Document? {
        TODO("Not yet implemented")
    }

    override fun chercherTous(): List<Document> {
        TODO("Not yet implemented")
    }
/*
    override fun modifier(element: Document): Boolean {
        TODO("Not yet implemented")
    }

    override fun effacer(element: Document): Boolean {
        TODO("Not yet implemented")
    }
*/


}
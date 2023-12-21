package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.AccordStage
import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature
import com.crosemont.dti.g26.stageavenirapi.Modèle.MappingEnum.MappageEnum
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class AccordStageImplement (val bd : JdbcTemplate) : AccordStageDAO {
    val mappage = MappageEnum()
    override fun ajouter(element: AccordStage): AccordStage? {
        bd.update(
                "INSERT INTO accordStage ( commentaire, etat, utilisateur_idutilisateur, offreStage_idoffreStage) VALUES (?, ?, ?, ?)",

                element.commentaire,
                element.etat.toString(),
                element.etudiant?.idEtudiant ?: 0,
                element.offre?.idOffreStage ?: 0,

        )

        return element
    }

    override fun chercherTous(): List<AccordStage> {
        TODO("Not yet implemented")
    }

    override fun chercherParCode(code: Int): AccordStage? {
        var result = bd.query("SELECT * FROM accordStage WHERE idaccordStage = ?", arrayOf(code)) { response, _ ->

            AccordStage(
                    idAccord = response.getInt("idaccordStage"),
                    commentaire = response.getString("commentaire"),
                    etat = mappage.mapToEtat(response.getString("etat")),
                    etudiant = null ,
                    offre = null,
            )

        }
        return result.firstOrNull()
    }

    override fun modifier(id: Int, element: AccordStage): AccordStage? {
        TODO("Not yet implemented")
    }

    override fun approuverUnAccord(element: Int): AccordStage? {
        println("ID " + element)
        try {
            bd.update(
                    "UPDATE accordStage SET etat = 'ACCEPTEE' WHERE idaccordStage = ?", element
            )
            println("ici 1")
            return chercherParCode(element)
        } catch (e: Exception) {
            // Log or print the exception for debugging purposes
            e.printStackTrace()
            // Handle the exception as needed (e.g., throw a custom exception or return null)
            return null
        }
    }

    override fun désaprouverUnAccord(element: Int): AccordStage? {
        bd.update(
                "UPDATE accordStage SET etat = 'REFUSEE' WHERE idaccordStage = ?",

                element
        )
        return chercherParCode(element)
    }

    override fun selectionnerAccordParCategorie(categorieId: Int): List<AccordStage>? {
        TODO("Not yet implemented")
    }

    override fun effacer(code: Int) {
        TODO("Not yet implemented")
    }
}
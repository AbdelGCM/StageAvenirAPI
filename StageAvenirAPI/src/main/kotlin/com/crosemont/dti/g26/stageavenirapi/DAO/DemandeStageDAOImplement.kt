
package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Exceptions.ConflitAvecUneRessourceExistanteException
import com.crosemont.dti.g26.stageavenirapi.Exceptions.RessourceInexistanteException
import com.crosemont.dti.g26.stageavenirapi.Modèle.*
import com.crosemont.dti.g26.stageavenirapi.Modèle.MappingEnum.MappageEnum
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class DemandeStageDAOImplement(val bd : JdbcTemplate): DemandeStageDAO {


    override fun ajouter(demande: DemandeStage): DemandeStage? {
        val sql = "INSERT INTO demandeStage (iddemandeStage, titre, description, remunere, poste, visible, catégorie_idcategorie, utilisateur_idutilisateur) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"

        bd.update(
            sql,
            demande.idDemandeStage,
            demande.titre,
            demande.description,
            demande.remunere,
            demande.poste,
            demande.categorie?.idCategorie,
            demande.utilisateur?.idutilisateur
        )

        return demande
    }

    override fun chercherParCode(code: Int): DemandeStage? {
        var demande_de_stage: DemandeStage? = null

        try {
            bd.query("SELECT * FROM demandeStage WHERE iddemandeStage = ?", arrayOf(code)) { response, _ ->
                if (response.next()) {

                    demande_de_stage =  DemandeStage(
                        idDemandeStage = response.getInt("id"),
                        description = response.getString("description"),
                        remunere = response.getBoolean("remunere"),
                        poste = response.getString("poste"),
                        visible = response.getBoolean("visible"),
                        categorie = chercherCategorieParCode(code = response.getInt("categorie_idcategorie")),
                        utilisateur = chercherUtilisateurParCode(code = response.getInt("utilisateur_idutilisateur"))
                    )
                }
            }

        }catch (e: Exception){
            println("ERREUR DAO :" + e)
        }

        println("DAO : " + demande_de_stage.toString())
        return demande_de_stage
    }


    override fun chercherTous(): List<DemandeStage> {
        val demande_stage = mutableListOf<DemandeStage>()
        bd.query("SELECT * FROM demandeStage") { response, _ ->
            while (response.next()) {
                val demandeStage =  DemandeStage(
                    idDemandeStage = response.getInt("id"),
                    description = response.getString("description"),
                    remunere = response.getBoolean("remunere"),
                    poste = response.getString("poste"),
                    visible = response.getBoolean("visible"),
                    categorie = chercherCategorieParCode(code = response.getInt("categorie_idcategorie")),
                    utilisateur = chercherUtilisateurParCode(code = response.getInt("utilisateur_idutilisateur"))
                )
                demande_stage.add(demandeStage)
            }
        }
        return demande_stage
    }

    override fun modifier(id: Int, demande: DemandeStage): DemandeStage? {
        bd.update(
            "UPDATE demandeStage SET titre = ?,description = ?,remunere = ?, description = ? WHERE iddemandeStage = ?",
            demande.titre,
            demande.description,
            demande.remunere,
            demande.poste,
            demande.idDemandeStage
        )

        return chercherParCode(demande.idDemandeStage)!!
    }

    override fun modifierStatus(status: Boolean, id: Int): DemandeStage? {
        bd.update(
            "UPDATE demandeStage SET status = ? WHERE iddemandeStage = ?",
            status,
            id
        )
        return chercherParCode(id)!!
    }


    override fun effacer(id: Int) {
        bd.update(
            "DELETE FROM demandeStage WHERE iddemandeStage = ?",
            id
        )

    }

}
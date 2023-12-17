package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Exceptions.ConflitAvecUneRessourceExistanteException
import com.crosemont.dti.g26.stageavenirapi.Exceptions.RessourceInexistanteException
import com.crosemont.dti.g26.stageavenirapi.Modèle.*
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.annotation.Id
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class OffreStageDAOImplement(val db: JdbcTemplate): OffreStageDAO {

    override fun ajouter(offre: OffreStage): OffreStage? {
        val sql = "INSERT INTO OffreStage (idOffreStage, titreOffre, posteOffert, description, estRémunéré, dateDébut, dateFin, estVisible,catégorieId) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"

        db.update(
                sql,
                offre.idOffreStage,
                offre.titreOffre,
                offre.posteOffert,
                offre.description,
                offre.estRémunéré,
                offre.datePost,
                offre.estVisible,
                offre.catégorie.idCatégorie
        )

        return offre
    }

    override fun chercherTous(): List<OffreStage> {
        val sql = "SELECT * FROM OffreStage"
        return db.query(sql) { résultat, _ ->
            OffreStage(
                    idOffreStage = résultat.getInt("idoffreStage"),
                    titreOffre = résultat.getString("titre"),
                    posteOffert = résultat.getString("poste_offert"),
                    description = résultat.getString("description"),
                    estRémunéré = résultat.getBoolean("remunere"),
                    datePost = résultat.getDate("date").toLocalDate(),
                    estVisible = résultat.getBoolean("visible"),
                    utilisateur = Employeur(),
                    catégorie = Categorie(
                            idCatégorie = résultat.getInt("categorie_idcategorie"),
                            cursus = null
                    )
            )
        }
    }


    override fun chercherParCode(code: Int): OffreStage? {
        val sql = "SELECT * FROM offreStage WHERE idoffreStage = ?"
        println("OFFRE DAO")
        val result =  db.query(sql, arrayOf(code)) { résultat, _ ->
            OffreStage(
                    idOffreStage = résultat.getInt("idoffreStage"),
                    titreOffre = résultat.getString("titre"),
                    posteOffert = résultat.getString("poste_offert"),
                    description = résultat.getString("description"),
                    estRémunéré = résultat.getBoolean("remunere"),
                    datePost = résultat.getDate("date").toLocalDate(),
                    estVisible = résultat.getBoolean("visible"),
                    utilisateur = Employeur(),
                    catégorie = Categorie(
                            idCatégorie = résultat.getInt("categorie_idcategorie"),
                            cursus = null
                    )
            )
        }

        return result.firstOrNull()
    }


    override fun modifier(id: Int, offre: OffreStage): OffreStage? {
        val sql = "UPDATE OffreStage SET titreOffre = ?, posteOffert = ?, description = ?, estRémunéré = ?, dateDébut = ?, dateFin = ?, estVisible = ?,catégorieId = ? " +
                "WHERE idOffreStage = ?"

        db.update(
                sql,
                offre.titreOffre,
                offre.posteOffert,
                offre.description,
                offre.estRémunéré,
                offre.datePost,
                offre.estVisible,
                offre.catégorie.idCatégorie,
                id
        )

        return chercherParCode(id)
    }

    override fun modifierVisibilité(id: Int, offre: OffreStage): OffreStage? {
        val sql = "UPDATE OffreStage SET estVisible = ? WHERE idOffreStage = ?"

        val affectedRows = db.update(sql, offre.estVisible, id)

        if (affectedRows > 0) {
            return chercherParCode(id)
        } else {

            return ajouter(offre)
        }
    }

    override fun effacer(code: Int) {
        val sql = "DELETE FROM OffreStage WHERE idOffreStage = ?"
        val affectedRows = db.update(sql, code)

        if (affectedRows == 0) {
            throw RessourceInexistanteException("L'offre $code n'est pas inscrite au service.")
        }

    }
}
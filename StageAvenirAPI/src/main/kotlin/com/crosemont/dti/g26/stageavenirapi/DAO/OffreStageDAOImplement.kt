package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Exceptions.ConflitAvecUneRessourceExistanteException
import com.crosemont.dti.g26.stageavenirapi.Exceptions.RessourceInexistanteException
import com.crosemont.dti.g26.stageavenirapi.Modèle.Catégorie
import com.crosemont.dti.g26.stageavenirapi.Modèle.OffreStage
import com.crosemont.dti.g26.stageavenirapi.Modèle.Utilisateur
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
                offre.dateDébut,
                offre.dateFin,
                offre.estVisible,
                offre.catégorie.idCatégorie
        )

        return offre
    }

    override fun chercherTous(): List<OffreStage> {
        val sql = "SELECT * FROM OffreStage"

        return db.query(sql) { résultat, _ ->
            OffreStage(
                    idOffreStage = résultat.getInt("idOffreStage"),
                    titreOffre = résultat.getString("titreOffre"),
                    posteOffert = résultat.getString("posteOffert"),
                    description = résultat.getString("description"),
                    estRémunéré = résultat.getBoolean("estRémunéré"),
                    dateDébut = résultat.getDate("dateDébut").toLocalDate(),
                    dateFin = résultat.getDate("dateFin").toLocalDate(),
                    estVisible = résultat.getBoolean("estVisible"),
                    utilisateur = Utilisateur(),
                    catégorie = Catégorie(
                            idCatégorie = résultat.getInt("catégorie_idCatégorie"),
                            cursus = résultat.getString("catégorie_cursus")
                    )
            )
        }
    }

    override fun chercherParCode(code: Int): OffreStage? {
        val sql = "SELECT * FROM OffreStage WHERE idOffreStage = ?"

        return try {
            db.queryForObject(sql, OffreStage::class.java, code)
        } catch (ex: EmptyResultDataAccessException) {
            null
        }
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
                offre.dateDébut,
                offre.dateFin,
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
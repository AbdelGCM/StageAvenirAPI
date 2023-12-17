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
        TODO()
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
                    entrepriseIdEntreprise = résultat.getInt("entreprise_identreprise"),
                    catégorie = Categorie(
                            idCatégorie = résultat.getInt("categorie_idcategorie"),
                            cursus = null
                    )
            )
        }
    }

    override fun ajouterUneOffre(codeEntreprise: Int, offre: OffreStage): OffreStage? {
        println("l'Offre en question :" + offre)
        db.update(
                "INSERT INTO OffreStage (titre, poste_offert, description, remunere, date,visible,categorie_idcategorie,entreprise_identreprise) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                offre.titreOffre,
                offre.posteOffert,
                offre.description,
                offre.estRémunéré,
                offre.datePost,
                offre.estVisible,
                offre.catégorie.idCatégorie,
                offre.entrepriseIdEntreprise
        );

        return offre
    }


    override fun chercherParCode(code: Int): OffreStage? {
        val sql = "SELECT * FROM offreStage WHERE idoffreStage = ?"
        val result =  db.query(sql, arrayOf(code)) { résultat, _ ->
            OffreStage(
                    idOffreStage = résultat.getInt("idoffreStage"),
                    titreOffre = résultat.getString("titre"),
                    posteOffert = résultat.getString("poste_offert"),
                    description = résultat.getString("description"),
                    estRémunéré = résultat.getBoolean("remunere"),
                    datePost = résultat.getDate("date").toLocalDate(),
                    estVisible = résultat.getBoolean("visible"),
                    entrepriseIdEntreprise = résultat.getInt("entreprise_identreprise"),
                    catégorie = Categorie(
                            idCatégorie = résultat.getInt("categorie_idcategorie"),
                            cursus = null
                    )
            )
        }

        return result.firstOrNull()
    }


    override fun modifier(id: Int, offre: OffreStage): OffreStage? {
        val sql = "UPDATE OffreStage SET titre = ?, poste_offert = ?, description = ?, remunere = ?, date = ?, visible = ?,categorie_idcategorie = ?" +
                " WHERE idOffreStage = ?"

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

    override fun chercherParCodeCatégorie(code_categorie: Int): List<OffreStage> {
        val sql = "SELECT * FROM offreStage WHERE categorie_idcategorie = ?"
        val result =  db.query(sql, arrayOf(code_categorie)) { résultat, _ ->
            OffreStage(
                    idOffreStage = résultat.getInt("idoffreStage"),
                    titreOffre = résultat.getString("titre"),
                    posteOffert = résultat.getString("poste_offert"),
                    description = résultat.getString("description"),
                    estRémunéré = résultat.getBoolean("remunere"),
                    datePost = résultat.getDate("date").toLocalDate(),
                    estVisible = résultat.getBoolean("visible"),
                    entrepriseIdEntreprise = résultat.getInt("entreprise_identreprise"),
                    catégorie = Categorie(
                            idCatégorie = résultat.getInt("categorie_idcategorie"),
                            cursus = null
                    )
            )
        }

        return result.toList()

    }

    override fun effacer(code: Int) {
        val sql = "DELETE FROM OffreStage WHERE idOffreStage = ?"
        val affectedRows = db.update(sql, code)

        if (affectedRows == 0) {
            throw RessourceInexistanteException("L'offre $code n'est pas inscrite au service.")
        }

    }
}
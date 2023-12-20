package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.Categorie
import com.crosemont.dti.g26.stageavenirapi.Modèle.Entreprise
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class CategorieDAOImplement (var bd : JdbcTemplate) : CategorieDAO {
    override fun chercherTous(): List<Categorie> {
        TODO("Not yet implemented")
    }

    override fun chercherParCode(code: Int): Categorie? {

        var result = bd.query("SELECT * FROM categorie WHERE idcategorie = ?", arrayOf(code)) { response, _ ->

            Categorie(
                idCatégorie = response.getInt("idcategorie"),
                cursus  = response.getString("nom"),
            )

        }

        return result.firstOrNull()
    }

    override fun ajouter(element: Categorie): Categorie? {
        TODO("Not yet implemented")
    }

    override fun modifier(id: Int, element: Categorie): Categorie? {
        TODO("Not yet implemented")
    }

    override fun effacer(code: Int) {
        TODO("Not yet implemented")
    }
}
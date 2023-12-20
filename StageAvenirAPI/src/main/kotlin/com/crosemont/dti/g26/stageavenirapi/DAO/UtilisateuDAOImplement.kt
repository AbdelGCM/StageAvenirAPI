package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.Categorie
import com.crosemont.dti.g26.stageavenirapi.Modèle.Utilisateur
import org.springframework.context.annotation.Role
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class UtilisateuDAOImplement (var bd : JdbcTemplate, var daoCategorieDAO: CategorieDAO, var daoRole : RoleDAO) : UtilisateurDAO {
    override fun chercherParCode(code: Int): Utilisateur? {
        var result = bd.query("SELECT * FROM utilisateur WHERE idutilisateur = ?", arrayOf(code)) { response, _ ->

            Utilisateur(
                idUtilisateur = response.getString("idutilisateur"),
                nom = response.getString("nom"),
                prénom = response.getString("prenom"),
                courriel = response.getString("courriel"),
                no_telephone = response.getString("telephone"),
                categorie =  daoCategorieDAO.chercherParCode( response.getInt("categorie_idcategorie")),
                role = daoRole.chercherParCode(response.getInt("role_idRole"))
            )

        }
        return result.firstOrNull()
    }

    override fun chercherUserParCode(code: String): Utilisateur? {
        var result = bd.query("SELECT * FROM utilisateur WHERE idutilisateur = ?", arrayOf(code)) { response, _ ->

            Utilisateur(
                idUtilisateur = response.getString("idutilisateur"),
                nom = response.getString("nom"),
                prénom = response.getString("prenom"),
                courriel = response.getString("courriel"),
                no_telephone = response.getString("telephone"),
                categorie =  daoCategorieDAO.chercherParCode( response.getInt("categorie_idcategorie")),
                role = daoRole.chercherParCode(response.getInt("role_idRole"))
            )

        }
        return result.firstOrNull()
    }

    override fun chercherTous(): List<Utilisateur> {
        TODO("Not yet implemented")
    }

    override fun ajouter(element: Utilisateur): Utilisateur? {
        TODO("Not yet implemented")
    }

    override fun modifier(id: Int, element: Utilisateur): Utilisateur? {
        TODO("Not yet implemented")
    }

    override fun effacer(code: Int) {
        TODO("Not yet implemented")
    }
}
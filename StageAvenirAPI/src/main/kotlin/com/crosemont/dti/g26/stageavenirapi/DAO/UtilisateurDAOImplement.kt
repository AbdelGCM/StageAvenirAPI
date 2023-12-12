package com.crosemont.dti.g26.stageavenirapi.DAO


import com.crosemont.dti.g26.stageavenirapi.Modèle.Utilisateur
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository


@Repository
class UtilisateurDAOImplement(val bd : JdbcTemplate):UtilisateurDAO {
    override fun ajouter(element: Utilisateur): Utilisateur? {
        TODO("Not yet implemented")

    }

    override fun chercherParCode(code: Int): Utilisateur? {
        TODO("Not yet implemented")
    }

    override fun chercherTous(): List<Utilisateur> {
        /*return bd.query("select * from Utilisateur"){ response, _ ->
            Utilisateur(response.getInt("idCompte"), response.getString("nom"),response.getString("prénom"), response.getString("no_étudiant"), response.getString("courriel"), response.getString("adresse"), response.getString("type_entreprise"), response.getString("type_compte"), )
        }*/
        TODO()
    }

    override fun modifier(element: Utilisateur): Utilisateur? {
        TODO("Not yet implemented")
    }

    override fun supprimer(element: Utilisateur): Boolean {
        TODO("Not yet implemented")
    }

    override fun cahangerPhoto(element: Utilisateur, urlPhoto: String): Boolean {
        TODO("Not yet implemented")
    }
}


package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature
import com.crosemont.dti.g26.stageavenirapi.Modèle.Entreprise
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class EntrepriseDAOImplement(var bd : JdbcTemplate, var daoUser : UtilisateurDAO) : EntrepriseDAO {
    override fun chercherParCode(code: Int): Entreprise? {


        var result = bd.query("SELECT * FROM entreprise WHERE identreprise = ?", arrayOf(code)) { response, _ ->

            daoUser.chercherUserParCode(response.getString("utilisateur_idutilisateur"))?.let {
                Entreprise(
                    idEntreprise = response.getInt("idEntreprise"),
                    nom = response.getString("nom"),
                    adresse = response.getString("adresse"),
                    description = response.getString("description"),
                    secteur = response.getString("secteur"),
                    employeur = it,
                )
            }

        }


        return result.firstOrNull()
    }

    override fun chercherTous(): List<Entreprise> {
        TODO("Not yet implemented")
    }

    override fun ajouter(element: Entreprise): Entreprise? {
        TODO("Not yet implemented")
    }

    override fun modifier(id: Int, element: Entreprise): Entreprise? {
        TODO("Not yet implemented")
    }

    override fun effacer(code: Int) {
        TODO("Not yet implemented")
    }
}
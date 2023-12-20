package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.DemandeStage
import com.crosemont.dti.g26.stageavenirapi.Modèle.Utilisateur
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class UtilisateurDAOImplement (val bd : JdbcTemplate): UtilisateurDAO  {
    override fun ajouter(utilisateur: Utilisateur): Utilisateur? {
        val sql = "INSERT INTO utilisateur (idutilisateur, nom, prenom, courriel, telephone, ville, catégorie_idcategorie, role_idRole) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"

        bd.update(
            sql,
            utilisateur.idutilisateur,
            utilisateur.nom,
            utilisateur.prenom,
            utilisateur.courriel,
            utilisateur.telephone,
            utilisateur.ville,
            utilisateur.categorie?.idCategorie,
            utilisateur.role
        )

        return utilisateur
    }
    override fun chercherParCode(code: Int): Utilisateur? {
        var utilisateur: Utilisateur? = null

        try {
            bd.query("SELECT * FROM utilisateur WHERE idutilisateur = ?", arrayOf(code)) { response, _ ->
                if (response.next()) {

                   utilisateur =  Utilisateur(
                        idutilisateur = response.getInt("idutilisateur"),
                        nom = response.getString("nom"),
                        prenom = response.getString("prenom"),
                        courriel = response.getString("courriel"),
                        telephone = response.getString("telephone"),
                        categorie = chercherCategorieParCode(code = response.getInt("categorie_idcategorie")),
                        role = chercherRoleParCode(code = response.getInt("utilisateur_idutilisateur"))
                    )
                }
            }

        }catch (e: Exception){
            println("ERREUR DAO :" + e)
        }

        println("DAO : " + demande_de_stage.toString())
        return demande_de_stage
    }

}
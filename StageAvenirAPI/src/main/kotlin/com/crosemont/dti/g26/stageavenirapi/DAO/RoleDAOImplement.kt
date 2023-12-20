package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.Categorie
import com.crosemont.dti.g26.stageavenirapi.Modèle.Role
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class RoleDAOImplement (var bd : JdbcTemplate ) :RoleDAO{
    override fun chercherParCode(code: Int): Role? {
        var result = bd.query("SELECT * FROM role WHERE idrole = ?", arrayOf(code)) { response, _ ->

            Role(
                idRole = response.getInt("idRole"),
                nom  = response.getString("nom"),
            )

        }

        return result.firstOrNull()
    }

    override fun chercherTous(): List<Role> {
        TODO("Not yet implemented")
    }

    override fun ajouter(element: Role): Role? {
        TODO("Not yet implemented")
    }

    override fun modifier(id: Int, element: Role): Role? {
        TODO("Not yet implemented")
    }

    override fun effacer(code: Int) {
        TODO("Not yet implemented")
    }


}
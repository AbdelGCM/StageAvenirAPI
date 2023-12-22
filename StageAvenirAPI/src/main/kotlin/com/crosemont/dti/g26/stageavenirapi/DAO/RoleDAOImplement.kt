package com.crosemont.dti.g26.stageavenirapi.DAO


import com.crosemont.dti.g26.stageavenirapi.Modèle.DemandeStage
import com.crosemont.dti.g26.stageavenirapi.Modèle.MappingEnum.MappageEnum

import com.crosemont.dti.g26.stageavenirapi.Modèle.Role
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository

class RoleDAOImplement  (val bd : JdbcTemplate): RoleDAO{
    private var mappage = MappageEnum()
    override fun chercherParCode(code: Int): Role? {
        var role: Role? = null

        try {
            bd.query("SELECT * FROM  WHERE idRole = ?", arrayOf(code)) { response, _ ->
                if (response.next()) {

                    role =  Role(
                        idRole = response.getInt("idRole"),
                        nom = mappage.mapToNom(response.getString("nom"))
                    )
                }
            }

        }catch (e: Exception){
            println("ERREUR DAO :" + e)
        }

        println("DAO : " + role.toString())


        return role

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
package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.Employeur
import org.springframework.jdbc.core.JdbcTemplate

class EmployeurDAOImplement (val bd : JdbcTemplate) : EmployeurDAO {
    override fun chercherParCode(code: Int): Employeur? {
        TODO("Not yet implemented")
    }

















    override fun chercherTous(): List<Employeur> {
        TODO("Not yet implemented")
    }

    override fun ajouter(element: Employeur): Employeur? {
        TODO("Not yet implemented")
    }

    override fun modifier(id: Int, element: Employeur): Employeur? {
        TODO("Not yet implemented")
    }

    override fun effacer(code: Int) {
        TODO("Not yet implemented")
    }
}
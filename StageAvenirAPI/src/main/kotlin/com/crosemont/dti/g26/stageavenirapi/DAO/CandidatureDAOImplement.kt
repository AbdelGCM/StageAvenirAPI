package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class CandidatureDAOImplement(val bd : JdbcTemplate) : CandidatureDAO {
    override fun ajouter(element: Candidature): Candidature? {
        TODO("Not yet implemented")
    }

    override fun chercherParCode(code: String): Candidature? {
        TODO("Not yet implemented")
    }

    override fun chercherTous(): List<Candidature> {
        TODO("Not yet implemented")
    }

    override fun modifier(element: Candidature): Candidature? {
        TODO("Not yet implemented")
    }

    override fun supprimer(element: Candidature): Boolean {
        TODO("Not yet implemented")
    }


}

package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.DemandeStage
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class DemandeStageDAOImplement(val bd : JdbcTemplate) : DemandeStageDAO {
    override fun ajouter(element: DemandeStage): DemandeStage? {
        TODO("Not yet implemented")
    }

    override fun chercherParCode(code: Int): DemandeStage? {
        TODO("Not yet implemented")
    }

    override fun chercherTous(): List<DemandeStage> {
        TODO("Not yet implemented")
    }

    override fun modifier(id :Int, element: DemandeStage): DemandeStage {
        TODO("Not yet implemented")
    }

    override fun effacer(elementId: Int) {
        TODO("Not yet implemented")
    }

}
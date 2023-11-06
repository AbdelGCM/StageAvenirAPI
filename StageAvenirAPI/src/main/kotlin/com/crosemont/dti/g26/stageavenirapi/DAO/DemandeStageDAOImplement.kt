package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.modèle.Demande_Stage
import org.springframework.stereotype.Repository

@Repository
class DemandeStageDAOImplement : DemandeStageDAO {
    override fun ajouter(element: Demande_Stage): Demande_Stage? {
        TODO("Not yet implemented")
    }

    override fun chercherParCode(code: String): Demande_Stage? {
        TODO("Not yet implemented")
    }

    override fun chercherTous(): List<Demande_Stage> {
        TODO("Not yet implemented")
    }

    override fun modifier(element: Demande_Stage): Demande_Stage? {
        TODO("Not yet implemented")
    }

    override fun supprimer(element: Demande_Stage): Boolean {
        TODO("Not yet implemented")
    }
}
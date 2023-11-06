package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.DemandeStage
import org.springframework.stereotype.Repository

@Repository
class DemandeStageDAOImplement : DemandeStageDAO {
    override fun ajouter(element: DemandeStage): DemandeStage? {
        TODO("Not yet implemented")
    }

    override fun chercherParCode(code: String): DemandeStage? {
        TODO("Not yet implemented")
    }

    override fun chercherTous(): List<DemandeStage> {
        TODO("Not yet implemented")
    }

    override fun modifier(element: DemandeStage): DemandeStage? {
        TODO("Not yet implemented")
    }

    override fun supprimer(element: DemandeStage): Boolean {
        TODO("Not yet implemented")
    }
}
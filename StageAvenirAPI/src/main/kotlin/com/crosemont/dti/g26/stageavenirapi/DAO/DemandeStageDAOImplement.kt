
package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Exceptions.ConflitAvecUneRessourceExistanteException
import com.crosemont.dti.g26.stageavenirapi.Exceptions.RessourceInexistanteException
import com.crosemont.dti.g26.stageavenirapi.Modèle.DemandeStage
import com.crosemont.dti.g26.stageavenirapi.Modèle.OffreStage
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class DemandeStageDAOImplement() : DemandeStageDAO {
    override fun ajouter(demande: DemandeStage): DemandeStage? {
        val index = SourceDonnées.demande.indexOfFirst{it.idDemandeStage == demande.idDemandeStage}

        if (index != -1) {
            throw ConflitAvecUneRessourceExistanteException("La demande de stage ${demande.idDemandeStage} est déjà inscrit au service.")
        }
        SourceDonnées.demande.add(demande)
        return demande
    }

    override fun chercherParCode(code: Int): DemandeStage? =SourceDonnées.demande.find { it.idDemandeStage == code }

    override fun chercherTous(): List<DemandeStage> = SourceDonnées.demande

    override fun modifier(id: Int, demande: DemandeStage): DemandeStage? {
        val index = SourceDonnées.demande.indexOfFirst{it.idDemandeStage == id}

        if (index != -1) {
            SourceDonnées.demande.set(index, demande)
            return null
        } else {
            return ajouter(demande)
        }
    }


    override fun effacer(code: Int) {
        val demande = SourceDonnées.demande.find{it.idDemandeStage == code}
        if(demande != null) {
            SourceDonnées.demande.remove(demande)
        } else {
            throw RessourceInexistanteException("La demande $demande n'est pas inscrit au service.")
        }
    }
}
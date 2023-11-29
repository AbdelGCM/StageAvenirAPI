package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Exceptions.ConflitAvecUneRessourceExistanteException
import com.crosemont.dti.g26.stageavenirapi.Exceptions.RessourceInexistanteException
import com.crosemont.dti.g26.stageavenirapi.Modèle.OffreStage
import org.springframework.data.annotation.Id
import org.springframework.stereotype.Repository

@Repository
class OffreStageDAOImplement(): OffreStageDAO {

    override fun ajouter(offre: OffreStage): OffreStage? {
        val index = SourceDonnées.offres.indexOfFirst{it.idOffreStage == offre.idOffreStage}

        if (index != -1) {
            throw ConflitAvecUneRessourceExistanteException("L'offre de stage ${offre.idOffreStage} est déjà inscrit au service.")
        }
        SourceDonnées.offres.add(offre)
        return offre
    }

    override fun chercherTous(): List<OffreStage> = SourceDonnées.offres

    override fun chercherParCode(code: Int): OffreStage? =SourceDonnées.offres.find { it.idOffreStage == code }

    override fun modifier(id: Int,offre: OffreStage): OffreStage? {
        val index = SourceDonnées.offres.indexOfFirst{it.idOffreStage == id}

        if (index != -1) {
            SourceDonnées.offres.set(index, offre)
            return null
        } else {
            return ajouter(offre)
        }
    }

    override fun effacer(code: Int) {
        val offre = SourceDonnées.offres.find{it.idOffreStage == code}
        if(offre != null) {
            SourceDonnées.offres.remove(offre)
        } else {
            throw RessourceInexistanteException("L'offre $code n'est pas inscrit au service.")
        }
    }
}
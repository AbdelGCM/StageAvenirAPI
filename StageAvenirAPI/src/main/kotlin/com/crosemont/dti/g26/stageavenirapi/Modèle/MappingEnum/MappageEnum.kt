package com.crosemont.dti.g26.stageavenirapi.Modèle.MappingEnum

import com.crosemont.dti.g26.stageavenirapi.Modèle.Enum.Etat

class MappageEnum {


    fun mapToEtat(etatString: String?): Etat {

            return when (etatString) {
                "acceptee" -> Etat.ACCEPTÉE
                "en cours" -> Etat.EN_ATTENTE
                "refusee" -> Etat.REFUSÉE
                else -> throw Exception("Une erreur est survenue au mappage de l'état")
            }

    }
}
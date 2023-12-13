package com.crosemont.dti.g26.stageavenirapi.Modèle.MappingEnum

import com.crosemont.dti.g26.stageavenirapi.Modèle.Enum.Etat
import com.crosemont.dti.g26.stageavenirapi.Modèle.Enum.Type

class MappageEnum {


    fun mapToEtat(etatString: String?): Etat {

            return when (etatString) {
                "acceptee" -> Etat.ACCEPTÉE
                "en cours" -> Etat.EN_ATTENTE
                "refusee" -> Etat.REFUSÉE
                "annulee" -> Etat.ANNULÉE
                else -> throw Exception("Une erreur est survenue au mappage de l'état")
            }

    }

    fun mapToType (etatString:String?): Type {
        return when (etatString) {
            "cv" -> Type.CV
            "supplement" -> Type.SUPPLEMENT

            else -> throw Exception("Une erreur est survenue au mappage du type")
        }

    }
}
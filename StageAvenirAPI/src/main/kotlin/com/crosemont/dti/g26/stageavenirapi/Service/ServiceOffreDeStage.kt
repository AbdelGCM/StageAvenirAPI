package com.crosemont.dti.g26.stageavenirapi.Service

import com.crosemont.dti.g26.stageavenirapi.DAO.OffreStageDAO
import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature
import com.crosemont.dti.g26.stageavenirapi.Modèle.Document
import com.crosemont.dti.g26.stageavenirapi.Modèle.OffreStage
import org.springframework.stereotype.Service

@Service
class ServiceOffreDeStage(val dao: OffreStageDAO){

    fun obtenirOffresStage(): List<OffreStage> = dao.chercherTous()
    fun obtenirOffreParCode (code: Int): OffreStage? = dao.chercherParCode(code)
    fun ajouter (offre: OffreStage): OffreStage? = dao.ajouter(offre)
    fun effacer(code: Int) = dao.effacer(code)
    fun modifier(code: Int, offre: OffreStage): OffreStage? = dao.modifier(code, offre)

}
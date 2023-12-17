package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.AccordStage
import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature

interface AccordStageDAO : DAO<AccordStage> {

    override fun ajouter(element: AccordStage): AccordStage?
    override fun modifier(id :Int , element: AccordStage): AccordStage
}
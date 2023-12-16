package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.OffreStage
import org.springframework.data.annotation.Id

interface OffreStageDAO : DAO<OffreStage> {

    override fun chercherParCode(code: Int): OffreStage?

    override fun chercherTous(): List<OffreStage>

    override fun ajouter(offre: OffreStage): OffreStage?

    override fun effacer(offre: OffreStage)

    override fun modifier(id:Int,offre: OffreStage): OffreStage

}
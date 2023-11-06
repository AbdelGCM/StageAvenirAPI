package com.crosemont.dti.g26.stageavenirapi.Service

import com.crosemont.dti.g26.stageavenirapi.modèle.Candidature
import com.crosemont.dti.g26.stageavenirapi.modèle.Document
import org.springframework.stereotype.Service

@Service
class ServiceOffreDeStage {

    fun obtenirOffresStage(){}
    fun obtenirOffreParCode (codeCandidature: Int){}
    fun postuler (codeEtudiant : Int , candidature: Candidature, documents: List<Document>){}
    fun obtenirCandidatures (codeEtudian:Int){}
    fun annulerCandidature (codeCandidature : Int){}

}
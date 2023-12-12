package com.crosemont.dti.g26.stageavenirapi.Service

import com.crosemont.dti.g26.stageavenirapi.DAO.UtilisateurDAO
import com.crosemont.dti.g26.stageavenirapi.DAO.UtilisateurDAOImplement
import com.crosemont.dti.g26.stageavenirapi.Modèle.Utilisateur
import org.springframework.stereotype.Service

@Service
class ServiceGestionUtilisateur(private val dao : UtilisateurDAO) {

    fun obtenirInformations(code : Int): Utilisateur? {
        return dao.chercherParCode(code)
    }

    fun modifierInformation(code :Int): Utilisateur {
        TODO("Not yet implemented")
    }

}
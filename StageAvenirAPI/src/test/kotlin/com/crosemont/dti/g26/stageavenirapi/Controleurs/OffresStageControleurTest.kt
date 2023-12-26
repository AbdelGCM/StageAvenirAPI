package com.crosemont.dti.g26.stageavenirapi.Controleurs

import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature
import com.crosemont.dti.g26.stageavenirapi.Modèle.OffreStage
import com.crosemont.dti.g26.stageavenirapi.Service.ServiceOffreDeStage
import com.crosemont.dti.g26.stageavenirapi.Exceptions.RessourceInexistanteException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.ResponseEntity
import java.security.Principal
import org.assertj.core.api.Assertions.assertThat
import com.crosemont.dti.g26.stageavenirapi.SourceDonnéesTests
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.doThrow
import org.springframework.http.HttpStatus

@ExtendWith(MockitoExtension::class)
class OffresStageControleurTest {

    @Mock
    lateinit var serviceOffreDeStage: ServiceOffreDeStage

    @InjectMocks
    lateinit var offresStageControleur: OffresStageControleur

    @Test
    fun `Étant donné des offres de stage existantes, Lorsqu'on obtient obtenirOffresStages, On obtient la liste des offres`() {
        val offres = SourceDonnéesTests.offresDeStage
        doReturn(offres).`when`(serviceOffreDeStage).obtenirOffresStage()
        val result = offresStageControleur.obtenirOffresStages()
        assertThat(result).isEqualTo(offres)
    }

    @Test
    fun `Étant donné une offre de stage existante, Lorsqu'on obtient obtenirOffresStagesParCode, On obtient l'offre correspondante`() {
        val offre = SourceDonnéesTests.offresDeStage.first()
        doReturn(offre).`when`(serviceOffreDeStage).obtenirOffreParCode(1)
        val result = offresStageControleur.obtenirOffresStagesParCode(1)
        assertThat(result).isEqualTo(offre)
    }

    @Test
    fun `Étant donné aucune offre de stage existante, Lorsqu'on obtient obtenirOffresStagesParCode, On obtient une exception de ressource inexistante`() {
        doReturn(null).`when`(serviceOffreDeStage).obtenirOffreParCode(99)
        val exception = org.junit.jupiter.api.assertThrows<RessourceInexistanteException> {
            offresStageControleur.obtenirOffresStagesParCode(99)
        }
        assertThat(exception.message).isEqualTo("L'offre 99 n'est pas inscrite au service.")
    }

    @Test
    fun `Étant donné un utilisateur authentifié, Lorsqu'on obtient obtenirOffresStagesParCatégorie, On obtient la liste des offres correspondant à la catégorie de l'utilisateur`() {
        val utilisateur = Principal { "auth0|658258a0c15592e55505b4e5" }
        val offres = SourceDonnéesTests.offresDeStage
        doReturn(offres).`when`(serviceOffreDeStage).obtenirOffresParCatégorie(utilisateur.name)
        val result = offresStageControleur.obtenirOffresStagesParCatégorie(utilisateur)
        assertThat(result).isEqualTo(offres)
    }




}

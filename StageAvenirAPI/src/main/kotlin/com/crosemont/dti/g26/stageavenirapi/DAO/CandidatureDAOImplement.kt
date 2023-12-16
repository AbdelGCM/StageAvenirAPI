
package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature
import com.crosemont.dti.g26.stageavenirapi.Modèle.MappingEnum.MappageEnum
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class CandidatureDAOImplement(val bd : JdbcTemplate) : CandidatureDAO {

    private var mappage = MappageEnum()
    override fun ajouter(element: Candidature): Candidature? {
        var idCandidature = bd.update(
            "INSERT INTO candidature (etat, description, offreStage_idoffreStage) VALUES (?, ?, ?)",
            element.etat.toString(), // Assuming etat is an enum and needs to be converted to a string
            element.commentaire,
           // element.offreStageIdOffreStage
        )

      if (idCandidature > 0){
          return  chercherParCode(idCandidature)
      }else{
          return null
      }

    }

    override fun chercherParCode(code: Int): Candidature? {
        var candidature: Candidature? = null

        bd.query("SELECT * FROM candidature WHERE idcandidature = ?", arrayOf(code)) { response, _ ->
            if (response.next()) {
                candidature = Candidature(
                    idCandidature = response.getInt("id"),
                    etat = mappage.mapToEtat(response.getString("etat")),
                    commentaire = response.getString("description"),
                    //offreStageIdOffreStage = response.getInt("offreStage_idoffreStage")

                )
            }
        }

        return candidature
    }

    override fun chercherTous(): List<Candidature> {
       val candidatures = mutableListOf<Candidature>()
       bd.query("SELECT * FROM candidature") { response, _ ->
            if (response.next()) {
                val candidature =  Candidature(
                    idCandidature = response.getInt("id"),
                    etat = mappage.mapToEtat(response.getString("etat")),
                    commentaire = response.getString("description"),
                    //offreStageIdOffreStage = response.getInt("offreStage_idoffreStage")
                )
                candidatures.add(candidature)
            }
        }
        return candidatures
    }
/*
    override fun modifier(element: Candidature): Boolean {
        var ligne_affectée = bd.update(
            "UPDATE candidature SET description = ? WHERE idcandidature = ?",
             element.commentaire,
             element.idCandidature
        )
        return  ligne_affectée > 0
    }

 */

    override fun modifier(id: Int, element: Candidature): Candidature? {
        TODO("Not yet implemented")
    }
/*
    override fun effacer(element: Candidature): Boolean {
        var ligne_affectée = bd.update(
            "DELETE FROM candidature WHERE idcandidature = ?",
            element.idCandidature
        )
        return  ligne_affectée > 0
    }
*/
    override fun effacer(code: Int) {
        TODO("Not yet implemented")
    }

    override fun chercherParEtudiant(code_etudiant: Int): Candidature? {
        var candidature: Candidature? = null

        bd.query("SELECT * FROM candidature WHERE utilisateur_idutilisateur = ?", arrayOf(code_etudiant)) { response, _ ->
            if (response.next()) {
                candidature = Candidature(
                    idCandidature = response.getInt("id"),
                    etat = mappage.mapToEtat(response.getString("etat")),
                    commentaire = response.getString("description")
                    //offreStageIdOffreStage = response.getInt("offreStage_idoffreStage")

                )
            }
        }

        return candidature
    }

    override fun chercherParOffreStage(code_offre: Int): Candidature? {
        var candidature: Candidature? = null

        bd.query("SELECT * FROM candidature WHERE offreStage_idoffreStage = ?", arrayOf(code_offre)) { response, _ ->
            if (response.next()) {
                candidature = Candidature(
                    idCandidature = response.getInt("id"),
                    etat = mappage.mapToEtat(response.getString("etat")),
                    commentaire = response.getString("description")
                    //offre = response.getInt("offreStage_idoffreStage")

                )
            }
        }

        return candidature
    }

    override fun postulerPourUneOffre() {
        TODO("Not yet implemented")
    }


}



package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.Candidature
import com.crosemont.dti.g26.stageavenirapi.Modèle.MappingEnum.MappageEnum
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class CandidatureDAOImplement(val bd : JdbcTemplate) : CandidatureDAO {

    private var mappage = MappageEnum()

    override fun ajouter(element: Candidature): Candidature? {
      TODO()
    }

    override fun chercherParCode(code: Int): Candidature? {
        var candidature: Candidature? = null

        bd.query("SELECT * FROM candidature WHERE idcandidature = ?", arrayOf(code)) { response, _ ->
            if (response.next()) {
                candidature = Candidature(
                    idCandidature = response.getInt("id"),
                    etat = mappage.mapToEtat(response.getString("etat")),
                    commentaire = response.getString("description"),
                    offre = null,
                    etudiant = null
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
                    offre = null,
                    etudiant = null
                )
                candidatures.add(candidature)
            }
        }
        return candidatures
    }

    override fun modifier(element: Candidature): Candidature {
        bd.update(
            "UPDATE candidature SET description = ? WHERE idcandidature = ?",
             element.commentaire,
             element.idCandidature
        )

        return chercherParCode(element.idCandidature)!!
    }

    override fun effacer(element: Candidature): Boolean {
        var ligne_affectée = bd.update(
            "DELETE FROM candidature WHERE idcandidature = ?",
            element.idCandidature
        )
        return  ligne_affectée > 0
    }

    override fun chercherParEtudiant(code_etudiant: Int): List<Candidature> {
        var candidatures = mutableListOf<Candidature>()

        bd.query("SELECT * FROM candidature WHERE utilisateur_idutilisateur = ?", arrayOf(code_etudiant)) { response, _ ->
            if (response.next()) {
                var candidature = Candidature(
                    idCandidature = response.getInt("id"),
                    etat = mappage.mapToEtat(response.getString("etat")),
                    commentaire = response.getString("description"),
                    offre = null,
                    etudiant = null
                )
                candidatures.add(candidature)
            }
        }

        return candidatures
    }

    override fun chercherParOffreStage(code_offre: Int): List<Candidature> {
        var candidatures = mutableListOf<Candidature>()

        bd.query("SELECT * FROM candidature WHERE offreStage_idoffreStage = ?", arrayOf(code_offre)) { response, _ ->
            if (response.next()) {
                var candidature = Candidature(
                    idCandidature = response.getInt("id"),
                    etat = mappage.mapToEtat(response.getString("etat")),
                    commentaire = response.getString("description"),
                    offre = null,
                    etudiant = null
                )
                candidatures.add(candidature)
            }
        }

        return candidatures
    }

    override fun postulerPourUneOffre(candidature: Candidature, code_etudiant: Int,idOffre:Int):Candidature? {
        var idCandidature = bd.update(
            "INSERT INTO candidature (etat, description, utilisateur_idutilisateur, offreStage_idoffreStage) VALUES (?, ?, ?, ?)",
            candidature.etat.toString(),
            candidature.commentaire,
            code_etudiant,
            idOffre
        )

        if (idCandidature > 0){
            return  chercherParCode(idCandidature)
        }else{
            return null
        }
    }

    override fun annulerCandidature(candidature: Candidature): Candidature? {
        bd.update(
            "UPDATE candidature SET etat = 'annulee' WHERE idcandidature = ?",

            candidature.idCandidature
        )
        return chercherParCode(candidature.idCandidature)
    }

    override fun accepterCandidature(candidature: Candidature): Candidature? {
        bd.update(
            "UPDATE candidature SET etat = 'acceptee' WHERE idcandidature = ?",

            candidature.idCandidature
        )
        return chercherParCode(candidature.idCandidature)
    }

    override fun refuserCandidature(candidature: Candidature): Candidature? {
        bd.update(
            "UPDATE candidature SET etat = 'refusee' WHERE idcandidature = ?",

            candidature.idCandidature
        )
        return chercherParCode(candidature.idCandidature)
    }


}


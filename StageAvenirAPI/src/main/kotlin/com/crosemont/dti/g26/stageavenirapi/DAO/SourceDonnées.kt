package com.crosemont.dti.g26.stageavenirapi.DAO

import com.crosemont.dti.g26.stageavenirapi.Modèle.OffreStage
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class SourceDonnées {
    companion object{
        val offres = mutableListOf(
                OffreStage(1, "Développeur Web", "Ingénieur Logiciel", "Travaillez sur des projets web passionnants", true, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 6, 30), 1, 1),
                OffreStage(2, "Analyste de données", "Data Scientist", "Analysez les données pour prendre des décisions éclairées", false, LocalDate.of(2023, 2, 1), LocalDate.of(2023, 7, 31), 2, 2),
                OffreStage(3, "Développeur mobile", "Ingénieur Mobile", "Concevez et développez des applications mobiles innovantes", true, LocalDate.of(2023, 3, 1), LocalDate.of(2023, 8, 31), 3, 1),
                OffreStage(4, "Administrateur système", "Ingénieur Réseau", "Gérez les systèmes informatiques et assurez la connectivité réseau", true, LocalDate.of(2023, 4, 1), LocalDate.of(2023, 9, 30), 4, 2),
                OffreStage(5, "Concepteur UX/UI", "Designer d'expérience utilisateur", "Créez des interfaces utilisateur intuitives et attrayantes", false, LocalDate.of(2023, 5, 1), LocalDate.of(2023, 10, 31), 5, 3),
                OffreStage(6, "Développeur backend", "Ingénieur Backend", "Travaillez sur la logique serveur et les bases de données", true, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 11, 30), 1, 2),
                OffreStage(7, "Spécialiste en cybersécurité", "Ingénieur en sécurité informatique", "Protégez les systèmes contre les menaces de sécurité", false, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 12, 31), 2, 1),
                OffreStage(8, "Développeur front-end", "Ingénieur Frontend", "Créez des interfaces utilisateur attrayantes et réactives", true, LocalDate.of(2023, 8, 1), LocalDate.of(2024, 1, 31), 3, 2),
                OffreStage(9, "Analyste QA", "Ingénieur QA", "Assurez la qualité des logiciels par le biais de tests approfondis", true, LocalDate.of(2023, 9, 1), LocalDate.of(2024, 2, 28), 4, 3),
                OffreStage(10, "Ingénieur DevOps", "Ingénieur DevOps", "Automatisez et améliorez le processus de développement", false, LocalDate.of(2023, 10, 1), LocalDate.of(2024, 3, 31), 5, 1)
        )

    }


}
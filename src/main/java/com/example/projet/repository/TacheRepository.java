package com.example.projet.repository;

import com.example.projet.entities.Tache;
import com.example.projet.entities.Projet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TacheRepository extends CrudRepository<Tache, Long> {
    // Méthode pour récupérer les tâches liées à un projet spécifique
    List<Tache> findByProjet(Projet projet);
}
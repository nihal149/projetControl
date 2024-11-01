package com.example.projet.service;

import com.example.projet.entities.Tache;
import com.example.projet.entities.Projet;
import com.example.projet.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TacheService {

    @Autowired
    private TacheRepository tacheRepository;

    public List<Tache> findByProjet(Projet projet) {
        return tacheRepository.findByProjet(projet);
    }

    public Optional<Tache> findById(Long id) {
        return tacheRepository.findById(id);
    }

    public void save(Tache tache) {
        tacheRepository.save(tache);
    }

    public void deleteById(Long id) {
        tacheRepository.deleteById(id);
    }
}

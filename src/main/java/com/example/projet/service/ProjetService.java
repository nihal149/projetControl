package com.example.projet.service;

import com.example.projet.entities.Projet;
import com.example.projet.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjetService {
    @Autowired
    private ProjetRepository projetRepository;

    public Iterable<Projet> getAllProjets() {
        return projetRepository.findAll();
    }

    public Projet saveProjet(Projet projet) {
        return projetRepository.save(projet);
    }

    public Projet getProjetById(Long id) {
        return projetRepository.findById(id).orElse(null);
    }

    public void deleteProjet(Long id) {
        projetRepository.deleteById(id);
    }
}

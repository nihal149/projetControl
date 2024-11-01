package com.example.projet.controller;

import com.example.projet.entities.Tache;
import com.example.projet.entities.Projet;
import com.example.projet.service.TacheService;
import com.example.projet.service.ProjetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/taches")
public class TacheController {

    @Autowired
    private TacheService tacheService;

    @Autowired
    private ProjetService projetService;

    // Afficher la liste des tâches associées à un projet
    @GetMapping("/projet/{projetId}")
    public String getTachesByProjet(@PathVariable Long projetId, Model model) {
        List<Tache> taches = tacheService.findByProjet(projetService.getProjetById(projetId));
        model.addAttribute("taches", taches);
        model.addAttribute("projet", projetService.getProjetById(projetId));
        model.addAttribute("tache", new Tache()); // Pour le formulaire d'ajout
        return "listeTaches"; // Nom de la vue Thymeleaf
    }

    // Enregistrer une nouvelle tâche
    @PostMapping("/projet/{projetId}")
    public String saveTache(@PathVariable Long projetId, @Valid @ModelAttribute Tache tache, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("taches", tacheService.findByProjet(projetService.getProjetById(projetId))); // Recharger les tâches en cas d'erreur
            model.addAttribute("projet", projetService.getProjetById(projetId)); // Recharger le projet
            return "listeTaches"; // Renvoie à la liste des tâches avec les erreurs
        }
        tache.setProjet(projetService.getProjetById(projetId)); // Associer la tâche au projet
        tacheService.save(tache);
        return "redirect:/taches/projet/" + projetId; // Rediriger vers la liste des tâches associées
    }

    // Supprimer une tâche
    @PostMapping("/delete/{id}/projet/{projetId}")
    public String deleteTache(@PathVariable Long id, @PathVariable Long projetId) {
        tacheService.deleteById(id);
        return "redirect:/taches/projet/" + projetId; // Rediriger vers la liste des tâches après suppression
    }
}


package com.example.projet.controller;

import com.example.projet.entities.Projet;
import com.example.projet.service.ProjetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/projets")
public class ProjetController {

    @Autowired
    private ProjetService projetService;

    // Afficher la liste des projets
    @GetMapping
    public String getAllProjets(Model model) {
        model.addAttribute("projets", projetService.getAllProjets());
        model.addAttribute("projet", new Projet()); // Pour le formulaire d'ajout
        return "listeProjets"; // Nom de la vue Thymeleaf
    }

    // Enregistrer un nouveau projet
    @PostMapping
    public String saveProjet(@Valid @ModelAttribute Projet projet, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("projets", projetService.getAllProjets());
            return "listeProjets"; // Renvoie à la liste des projets avec les erreurs
        }
        projetService.saveProjet(projet);
        return "redirect:/projets"; // Rediriger vers la liste des projets après l'ajout
    }

    // Afficher les détails d'un projet
    @GetMapping("/{id}")
    public String getProjetById(@PathVariable Long id, Model model) {
        Projet projet = projetService.getProjetById(id);
        model.addAttribute("projet", projet);
        return "detailProjet"; // Nom de la vue Thymeleaf pour afficher les détails du projet
    }

    // Supprimer un projet
    @PostMapping("/delete/{id}")
    public String deleteProjet(@PathVariable Long id) {
        projetService.deleteProjet(id);
        return "redirect:/projets"; // Rediriger vers la liste des projets après suppression
    }
}
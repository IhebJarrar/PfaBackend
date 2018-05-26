package com.exam.gestionExams.controller;

import java.util.List;

import com.exam.gestionExams.services.EpreuveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.gestionExams.model.Epreuves;
import com.exam.gestionExams.model.Local;
import com.exam.gestionExams.services.LocalServiceImpl;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("api/")
public class LocalController {

    @Autowired
    LocalServiceImpl localeService;
    @Autowired
    EpreuveService epreuveService;

    @GetMapping(value = "locaux")
    public List<Local> getAllLocaux() {
        return localeService.getAllLocal();
    }
    @GetMapping(value = "locaux/available/{id}")
    public List<Local> getLocauxAvailable(@PathVariable Long id) {
        Epreuves epreuve = epreuveService.getEpreuve(id);
        return localeService.getLocauxAvailable(epreuve);
    }
}
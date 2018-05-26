package com.exam.gestionExams.controller;

import com.exam.gestionExams.model.Niveau;
import com.exam.gestionExams.services.NiveauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("api/")
public class NiveauController {

    @Autowired
    NiveauService niveauService;

    @GetMapping(value = "niveaux")
    public List<Niveau> getAllLevel() {
        return niveauService.getAllLevel();
    }
}

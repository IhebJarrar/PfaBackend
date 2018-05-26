package com.exam.gestionExams.controller;

import com.exam.gestionExams.model.Matiere;
import com.exam.gestionExams.services.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("api/")
public class MatiereController {

    @Autowired
    MatiereService matiereService;

    @GetMapping(value = "matieres/all")
    public List<Matiere> getAll() {
        return matiereService.getAll();
    }
}

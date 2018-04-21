package com.exam.gestionExams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value = "locaux")
    public List<Local> getAllLocaux() {
        return localeService.getAllLocal();
    }
    @GetMapping(value = "locauxAvailable")
    public List<Local> getLocauxAvailable(@RequestBody() Epreuves epreuves) {
        return localeService.getLocauxAvailable(epreuves);
    }
}
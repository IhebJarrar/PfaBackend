package com.exam.gestionExams.controller;

import com.exam.gestionExams.model.Classe;
import com.exam.gestionExams.model.NivSpecialite;
import com.exam.gestionExams.services.ClasseService;
import com.exam.gestionExams.services.NivSpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("api/")
public class ClasseController {

    @Autowired
    ClasseService classeService;

    @Autowired
    NivSpecialityService nivSpecialityService;

    @GetMapping(value = "classes/all")
    public List<Classe> getAllClass() {
        return classeService.getAll();
    }

    @GetMapping(value = "classes/speciality/{specName}/niveau/{nivId}")
    public NivSpecialite getNivSpecId(@PathVariable String specName, @PathVariable Integer nivId) {
        return nivSpecialityService.getNivSpecialityByLevelAndSpec(nivId, specName);
    }
    @PostMapping(value = "classes")
    public Classe saveClasse(@RequestBody() Classe classe) {
        return classeService.saveClasse(classe);
    }
    @PutMapping(value = "classes/update/{id}")
    public Classe updatClasse(@RequestBody() Classe classe) {
        return classeService.updateClasse(classe);
    }
    @DeleteMapping(value = "classes/{id}")
    public void deleteClasse(@PathVariable Long id) {
        System.out.println(id);
        classeService.deleteClasse(id);
    }




}

package com.exam.gestionExams.controller;

import com.exam.gestionExams.model.Creneau;
import com.exam.gestionExams.repository.CreneauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CreneauController {

    @Autowired
    CreneauRepository creneauRepository;

    @RequestMapping(value = "/crenaux/distinct",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Creneau> findAllDistinct() {
        List<Date> lists = creneauRepository.findAllDistinct();
        List<Creneau> listToSend = new ArrayList<Creneau>();
        int i = 1;
        for (Date date: lists) {
            Creneau creneau = new Creneau();
            creneau.setId(i);
            creneau.setDate(date);
            listToSend.add(creneau);
            i++;
        }
        return listToSend;
    }

}

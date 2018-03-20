package com.exam.gestionExams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exam.gestionExams.repository.EpreuveRepository;

@RestController
@RequestMapping("api/")
public class EpreuveController {
	@Autowired
    EpreuveRepository epreuveRepository;

    @RequestMapping(value = "epreuves", method = RequestMethod.GET)
    public void insertEpreuves() {
    	epreuveRepository.createEpreuvesForAllGroupes();
    }
}

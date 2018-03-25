package com.exam.gestionExams.controller;


import com.exam.gestionExams.model.Epreuves;
import com.exam.gestionExams.repository.EpreuveRepository;
import com.exam.gestionExams.services.EpreuveService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("api/")
public class EpreuveController {
	@Autowired
    EpreuveRepository epreuveRepository;
	@Autowired
	private EpreuveService epreuveService;
	
    @RequestMapping(value = "epreuves", method = RequestMethod.GET)
    public void insertEpreuves() {
    	epreuveRepository.createEpreuvesForAllGroupes();
    }
    @RequestMapping(value = "epreuves/updateLocal", method = RequestMethod.GET)
    public void updateLocal() {
    	epreuveRepository.affecterLocaux();
    }
	@GetMapping(value = "epreuves/all")
	public List<Epreuves> getAllEpreuves() {
		return epreuveService.getAllEpreuves();
	}
	/* recevoir les epreuves modifiés
	@PostMapping(value = "epreuves/update")
	public void updateLocauxEpreuves(@RequestBody() List<Epreuves> epreuves) 
	{
		for (Epreuves epreuve: epreuves) 
		{
			epreuveRepository.saveAndFlush(epreuve);
		}

	}
	*/
}


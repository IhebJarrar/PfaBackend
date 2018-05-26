package com.exam.gestionExams.controller;

import com.exam.gestionExams.model.Creneau;
import com.exam.gestionExams.model.Epreuves;
import com.exam.gestionExams.model.Surveillant;
import com.exam.gestionExams.model.SurveillantDisponibilite;
import com.exam.gestionExams.model.SurveillantSchedule;
import com.exam.gestionExams.repository.CreneauRepository;
import com.exam.gestionExams.repository.SurveillantRepository;
import com.exam.gestionExams.services.EpreuveService;
import com.exam.gestionExams.services.SurveillantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("api/")
public class SurveillantController {
	@Autowired
	private SurveillantRepository surveillantRepository;
	@Autowired
	CreneauRepository creneauRepository;
	@Autowired
	private SurveillantService surveillantService;
	@Autowired
	private EpreuveService epreuveService;

	@RequestMapping(value = "surveillants/all", method = RequestMethod.GET)
	public List<Surveillant> getAllSurveillant() {
		return surveillantRepository.findAll();
	}

	@RequestMapping(value = "surveillants/{id}", method = RequestMethod.GET)
	public Surveillant getSurveillantById(@PathVariable Long id) {
		return surveillantRepository.findOne(id);
	}

	@PostMapping(value = "surveillants/availability/new")
	public Surveillant addSurveillantAvailability(@RequestBody() SurveillantDisponibilite disponibilite) {
		Surveillant surveillant = disponibilite.getSurveillant();
		List<Creneau> creneaus = new ArrayList<Creneau>();
		for (Long idCreneau: disponibilite.getIds()) {
			Creneau creneau = creneauRepository.findOne(idCreneau);
			creneaus.add(creneau);
		}
		surveillant.setCreneaux(creneaus);
		surveillantRepository.saveAndFlush(surveillant);

		return surveillant;
	}
	@GetMapping(value = "surveillants/treated")
	public List<Surveillant> getAllTreatedSurveillants() {
		return surveillantService.getAllTreatedSurveillants();
	}
	@GetMapping(value = "surveillants/noTreated")
	public List<Surveillant> getAllNoTreatedSurveillants() {
		return surveillantService.getAllNoTreatedSurveillants();
	}

	@GetMapping(value = "surveillants/permanents")
	public List<Surveillant> getAllPermanentSurveillant() {
		List<Surveillant> surveillants = surveillantService.getAllPermannets();
		return surveillants;
	}

	@GetMapping(value = "surveillants/vacataire")
	public List<Surveillant> getAllVacataireSurveillant() {
		return surveillantService.getAllVacataires();
	}

	@RequestMapping(value = "surveillants/available/epreuve/{id}")
	public List<Surveillant> getSurveillantAvailableInThisCren(@PathVariable Long id) {
		Epreuves epreuve = epreuveService.getEpreuve(id);
		return surveillantRepository.getSurveillantAvailableInThisCren(epreuve);
	}
	@RequestMapping(value = "surveillants/schedule")
	public List<SurveillantSchedule> getSurveillantSchedule() {
		return surveillantService.getSurveillantSchedule();
	}
}

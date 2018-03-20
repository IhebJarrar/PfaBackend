package com.exam.gestionExams.controller;

import com.exam.gestionExams.model.Creneau;
import com.exam.gestionExams.model.Surveillant;
import com.exam.gestionExams.model.SurveillantDisponibilite;
import com.exam.gestionExams.repository.CreneauRepository;
import com.exam.gestionExams.repository.SurveillantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	@RequestMapping(value = "surveillants", method = RequestMethod.GET)
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
	/**@RequestMapping(value = "surveillants", method = RequestMethod.POST)
	public Surveillant createSurveillant(@RequestBody Surveillant surveillant) {
		return surveillantRepository.saveAndFlush(surveillant);
	}
<<<<<<< HEAD

=======
>>>>>>> 60bbe209a444aceafeefa7f78e6f877a86a2d22d
	
	@RequestMapping(value = "surveillants/{id}", method = RequestMethod.PUT)
	public Surveillant updateSurveillant(@PathVariable Long id, @RequestBody Surveillant surveillant) {
		Surveillant existingSurveillant = surveillantRepository.findOne(id);
		BeanUtils.copyProperties(shipwreck, existingSurveillant);
		return surveillantRepository.saveAndFlush(existingSurveillant);
	}

	@RequestMapping(value = "surveillants/{id}", method = RequestMethod.DELETE)
	public Surveillant deleteSurveillant(@PathVariable Long id) {
		Surveillant existingSurveillant = surveillantRepository.findOne(id);
		surveillantRepository.delete(existingSurveillant);
		return existingSurveillant;
	}**/

}

package com.exam.gestionExams.controller;

import com.exam.gestionExams.model.Surveillant;
import com.exam.gestionExams.repository.SurveillantRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("api/")
public class SurveillantController {
	@Autowired
	private SurveillantRepository surveillantRepository;

	@RequestMapping(value = "surveillants", method = RequestMethod.GET)
	public List<Surveillant> getAllSurveillant() {
		return surveillantRepository.findAll();
	}

	@RequestMapping(value = "surveillants/{id}", method = RequestMethod.GET)
	public Surveillant getSurveillantById(@PathVariable Long id) {
		return surveillantRepository.findOne(id);
	}
	/**@RequestMapping(value = "surveillants", method = RequestMethod.POST)
	public Surveillant createSurveillant(@RequestBody Surveillant surveillant) {
		return surveillantRepository.saveAndFlush(surveillant);
	}
	
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

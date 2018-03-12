package com.exam.gestionExams.controller;

import com.exam.gestionExams.repository.GroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class GroupeController {

    @Autowired
    GroupeRepository groupeRepository;

    @RequestMapping(value = "group", method = RequestMethod.GET)
    public void insertGroup() {
        groupeRepository.insertApropriateGroup();
    }
}

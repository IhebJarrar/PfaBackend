package com.exam.gestionExams.services;

import com.exam.gestionExams.model.Departement;
import com.exam.gestionExams.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Departement> getAll() {
        return departmentRepository.findAll();
    }
}

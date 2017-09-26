package com.smartcity.db.model.resource;

import com.smartcity.db.model.Departments;
import com.smartcity.db.model.repository.interfaces.IDepartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/departments")
public class DepartmentsResource {
    @Autowired
    IDepartmentsRepository iDepartmentsRepository;

    @GetMapping(value = "/all")
    public List<Departments> getAll() { return iDepartmentsRepository.findAll();
    }

    @PostMapping(value = "/load")
    public List<Departments> persist(@RequestBody final Departments departments) {
        iDepartmentsRepository.save(departments);
        return iDepartmentsRepository.findAll();
    }


}

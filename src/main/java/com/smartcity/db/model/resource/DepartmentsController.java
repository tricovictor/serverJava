package com.smartcity.db.model.resource;

import com.smartcity.db.model.Department;
import com.smartcity.db.model.repository.interfaces.IDepartments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/departments")
public class DepartmentsController {
    @Autowired
    IDepartments iDepartments;

    @GetMapping(value = "/all")
    public List<Department> getAll() { return iDepartments.findAll();
    }

    @PostMapping(value = "/load")
    public List<Department> persist(@RequestBody final Department department) {
        iDepartments.save(department);
        return iDepartments.findAll();
    }


}

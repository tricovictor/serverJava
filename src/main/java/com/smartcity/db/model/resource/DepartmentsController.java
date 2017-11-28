package com.smartcity.db.model.resource;

import com.smartcity.db.model.Department;
import com.smartcity.db.model.Municipality;
import com.smartcity.db.model.Response;
import com.smartcity.db.model.User;
import com.smartcity.db.model.repository.interfaces.IDepartments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
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

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<Response> persist(@RequestBody Department department) {
        iDepartments.save(department);
        Response response = new Response();
        response.setResponse("Departamento Creado correctamente");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public ResponseEntity<Response> updateDepartment(@RequestBody Department departments) {
        Department department = iDepartments.findOne(departments.getId());
        department.setName(departments.getName());
        iDepartments.save(department);
        Response response = new Response();
        response.setResponse("Departamento Actualizado");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
    }

    @GetMapping(value = "/getDepartmentById")
    public Department getDepartmentById(@PathParam("id") Integer id) {
        return iDepartments.findOne(id);
    }

}

package com.smartcity.db.model.resource;

import com.smartcity.db.model.Degree;
import com.smartcity.db.model.Response;
import com.smartcity.db.model.repository.interfaces.IDegrees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/degrees")
public class DegreesController {

    @Autowired
    IDegrees iDegrees;

    Response response;

    @GetMapping(value = "/all")
    public List<Degree> getAll() {
        return iDegrees.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<Response> persist(@RequestBody Degree degree) {
        iDegrees.save(degree);
        response.setResponse("Grado Creado correctamente");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public ResponseEntity<Response> updateDegree(@RequestBody Degree degrees) {
        Degree degree = iDegrees.findOne(degrees.getId());
        degree.setName(degrees.getName());
        iDegrees.save(degree);
        response.setResponse("Grado actualizado");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
    }

    @GetMapping(value = "/getDegreeById")
    public Degree getDegreeById(@PathParam("id") Integer id) {
        try {
            return iDegrees.findOne(id);
        } catch (Exception e)
        {
            return null;
        }
    }

}

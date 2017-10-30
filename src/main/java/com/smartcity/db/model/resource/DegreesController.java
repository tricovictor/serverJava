package com.smartcity.db.model.resource;

import com.smartcity.db.model.Degree;
import com.smartcity.db.model.SubAmbito;
import com.smartcity.db.model.repository.interfaces.IDegrees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/degrees")
public class DegreesController {

    @Autowired
    IDegrees iDegrees;

    @GetMapping(value = "/all")
    public List<Degree> getAll() { return iDegrees.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String persist(@RequestBody Degree degree) {
        iDegrees.save(degree);
        return "{\"response\": \"Grado Creado correctamente\"}";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public String updateDegree(@RequestBody Degree degrees) {
        Degree degree = iDegrees.findOne(degrees.getId());
        degree.setName(degrees.getName());
        iDegrees.save(degree);
        return "{\"response\": \"Grado modificado correctamente\"}";
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

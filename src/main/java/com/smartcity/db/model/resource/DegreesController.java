package com.smartcity.db.model.resource;

import com.smartcity.db.model.Degree;
import com.smartcity.db.model.repository.interfaces.IDegrees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/degrees")
public class DegreesController {

    @Autowired
    IDegrees iDegrees;

    @GetMapping(value = "/all")
    public List<Degree> getAll() { return iDegrees.findAll();
    }

    @PostMapping(value = "/load")
    public List<Degree> persist(@RequestBody final Degree degree) {
        iDegrees.save(degree);
        return iDegrees.findAll();
    }


}

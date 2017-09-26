package com.smartcity.db.model.resource;

import com.smartcity.db.model.Degrees;
import com.smartcity.db.model.repository.interfaces.IDegreesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/degrees")
public class DegreesResource {

    @Autowired
    IDegreesRepository iDegreesRepository;

    @GetMapping(value = "/all")
    public List<Degrees> getAll() { return iDegreesRepository.findAll();
    }

    @PostMapping(value = "/load")
    public List<Degrees> persist(@RequestBody final Degrees degrees) {
        iDegreesRepository.save(degrees);
        return iDegreesRepository.findAll();
    }


}

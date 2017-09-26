package com.smartcity.db.model.resource;

import com.smartcity.db.model.Municipalities;
import com.smartcity.db.model.repository.interfaces.IMunicipalitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/municipalities")
public class MunicipalitiesResource {

    @Autowired
    IMunicipalitiesRepository iMunicipalitiesRepository;

    @GetMapping(value = "/all")
    public List<Municipalities> getAll() { return iMunicipalitiesRepository.findAll();
    }

    @PostMapping(value = "/load")
    public List<Municipalities> persist(@RequestBody final Municipalities municipalities) {
        iMunicipalitiesRepository.save(municipalities);
        return iMunicipalitiesRepository.findAll();
    }

}

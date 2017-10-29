package com.smartcity.db.model.resource;

import com.smartcity.db.model.Municipality;
import com.smartcity.db.model.repository.interfaces.IMunicipalities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/municipalities")
public class MunicipalitiesController {

    @Autowired
    IMunicipalities iMunicipalities;

    @GetMapping(value = "/all")
    public List<Municipality> getAll() { return iMunicipalities.findAll();
    }

    @PostMapping(value = "/load")
    public List<Municipality> persist(@RequestBody final Municipality municipality) {
        iMunicipalities.save(municipality);
        return iMunicipalities.findAll();
    }

}

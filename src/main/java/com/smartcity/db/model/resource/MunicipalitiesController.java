package com.smartcity.db.model.resource;

import com.smartcity.db.model.Municipality;
import com.smartcity.db.model.repository.interfaces.IMunicipalities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/municipalities")
public class MunicipalitiesController {

    @Autowired
    IMunicipalities iMunicipalities;

    @GetMapping(value = "/all")
    public List<Municipality> getAll() { return iMunicipalities.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String persist(@RequestBody Municipality municipality) {
        iMunicipalities.save(municipality);
        return "Usuario Creado correctamente";
    }

}

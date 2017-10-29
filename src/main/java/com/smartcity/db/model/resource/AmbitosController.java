package com.smartcity.db.model.resource;

import com.smartcity.db.model.Ambito;
import com.smartcity.db.model.repository.interfaces.IAmbitos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/ambitos")
public class AmbitosController {

    @Autowired
    IAmbitos iAmbitos;

    @GetMapping(value = "/all")
    public List<Ambito> getAll() {
        return iAmbitos.findAll();
    }

    @PostMapping(value = "/load")
    public List<Ambito> persist(@RequestBody final Ambito ambito) {
        iAmbitos.save(ambito);
        return iAmbitos.findAll();
    }


}

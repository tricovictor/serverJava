package com.smartcity.db.model.resource;

import com.smartcity.db.model.SubAmbito;
import com.smartcity.db.model.repository.interfaces.ISubAmbitos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/subambitos")
public class SubAmbitosController {

    @Autowired
    ISubAmbitos iSubAmbitos;

    @GetMapping(value = "/all")
    public List<SubAmbito> getAll() {
        return iSubAmbitos.findAll();
    }

    @PostMapping(value = "/load")
    public List<SubAmbito> persist(@RequestBody final SubAmbito subAmbito) {
        iSubAmbitos.save(subAmbito);
        return iSubAmbitos.findAll();
    }

}
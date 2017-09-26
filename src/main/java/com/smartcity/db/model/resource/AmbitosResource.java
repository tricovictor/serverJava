package com.smartcity.db.model.resource;

import com.smartcity.db.model.Ambitos;
import com.smartcity.db.model.repository.interfaces.IAmbitosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/ambitos")
public class AmbitosResource {

    @Autowired
    IAmbitosRepository iAmbitosRepository;

    @GetMapping(value = "/all")
    public List<Ambitos> getAll() {
        return iAmbitosRepository.findAll();
    }

    @PostMapping(value = "/load")
    public List<Ambitos> persist(@RequestBody final Ambitos ambitos) {
        iAmbitosRepository.save(ambitos);
        return iAmbitosRepository.findAll();
    }


}

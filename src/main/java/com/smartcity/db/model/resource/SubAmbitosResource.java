package com.smartcity.db.model.resource;

import com.smartcity.db.model.SubAmbitos;
import com.smartcity.db.model.repository.interfaces.ISubAmbitosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/subambitos")
public class SubAmbitosResource {

    @Autowired
    ISubAmbitosRepository iSubAmbitosRepository;

    @GetMapping(value = "/all")
    public List<SubAmbitos> getAll() {
        return iSubAmbitosRepository.findAll();
    }

    @PostMapping(value = "/load")
    public List<SubAmbitos> persist(@RequestBody final SubAmbitos subAmbitos) {
        iSubAmbitosRepository.save(subAmbitos);
        return iSubAmbitosRepository.findAll();
    }

}
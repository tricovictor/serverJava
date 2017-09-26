package com.smartcity.db.model.resource;


import com.smartcity.db.model.Levels;
import com.smartcity.db.model.repository.interfaces.ILevelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/levels")
public class LevelsResource {
    @Autowired
    ILevelsRepository iLevelsRepository;

    @GetMapping(value = "/all")
    public List<Levels> getAll() { return iLevelsRepository.findAll();
    }

    @PostMapping(value = "/load")
    public List<Levels> persist(@RequestBody final Levels levels) {
        iLevelsRepository.save(levels);
        return iLevelsRepository.findAll();
    }

}

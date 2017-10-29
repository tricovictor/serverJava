package com.smartcity.db.model.resource;


import com.smartcity.db.model.Level;
import com.smartcity.db.model.repository.interfaces.ILevels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/levels")
public class LevelsController {
    @Autowired
    ILevels iLevels;

    @GetMapping(value = "/all")
    public List<Level> getAll() { return iLevels.findAll();
    }

    @PostMapping(value = "/load")
    public List<Level> persist(@RequestBody final Level level) {
        iLevels.save(level);
        return iLevels.findAll();
    }

}

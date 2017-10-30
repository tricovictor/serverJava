package com.smartcity.db.model.resource;


import com.smartcity.db.model.Degree;
import com.smartcity.db.model.Level;
import com.smartcity.db.model.repository.interfaces.ILevels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/levels")
public class LevelsController {
    @Autowired
    ILevels iLevels;

    @GetMapping(value = "/all")
    public List<Level> getAll() { return iLevels.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String persist(@RequestBody Level level) {
        iLevels.save(level);
        return "{\"response\": \"Nivel Creado correctamente\"}";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public String updateLevel(@RequestBody Level levels) {
        Level level = iLevels.findOne(levels.getId());
        level.setName(levels.getName());
        iLevels.save(level);
        return "{\"response\": \"Nivel modificado correctamente\"}";
    }

    @GetMapping(value = "/getLevelById")
    public Level getLevelById(@PathParam("id") Integer id) {
        try {
            return iLevels.findOne(id);
        } catch (Exception e)
        {
            return null;
        }
    }

}

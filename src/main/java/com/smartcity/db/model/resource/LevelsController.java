package com.smartcity.db.model.resource;


import com.smartcity.db.model.Degree;
import com.smartcity.db.model.Level;
import com.smartcity.db.model.Response;
import com.smartcity.db.model.repository.interfaces.ILevels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/levels")
public class LevelsController {
    @Autowired
    ILevels iLevels;

    Response response;

    @GetMapping(value = "/all")
    public List<Level> getAll() { return iLevels.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<Response> persist(@RequestBody Level level) {
        iLevels.save(level);
        response.setResponse("Nivel Creado correctamente");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public ResponseEntity<Response> updateLevel(@RequestBody Level levels) {
        Level level = iLevels.findOne(levels.getId());
        level.setName(levels.getName());
        iLevels.save(level);
        response.setResponse("Nivel actualizado");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
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

package com.smartcity.db.model.resource;

import com.smartcity.db.model.Degree;
import com.smartcity.db.model.Response;
import com.smartcity.db.model.TypeLevel;
import com.smartcity.db.model.repository.interfaces.ITypesLevels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/typesLevels")
public class TypeLevelController {

    @Autowired
        ITypesLevels iTypesLevels;

        Response response;

        @GetMapping(value = "/all")
        public List<TypeLevel> getAll() {
            return iTypesLevels.findAll();
        }

        @GetMapping(value = "/getTypeLevelById")
        public TypeLevel getTypeLevelById(@PathParam("id") Integer id) {
            try {
                return iTypesLevels.findOne(id);
            } catch (Exception e)
            {
                return null;
            }
        }

    }



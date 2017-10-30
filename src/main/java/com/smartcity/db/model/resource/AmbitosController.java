package com.smartcity.db.model.resource;

import com.smartcity.db.model.Ambito;
import com.smartcity.db.model.User;
import com.smartcity.db.model.repository.interfaces.IAmbitos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String persist(@RequestBody Ambito ambito) {
        iAmbitos.save(ambito);
        return "{\"response\": \"Ambito Creado correctamente\"}";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public String updateUser(@RequestBody Ambito ambitos) {
        Ambito ambito = iAmbitos.findOne(ambitos.getId());
        ambito.setName(ambitos.getName());
        iAmbitos.save(ambito);
        return "{\"response\": \"Ambito Creado correctamente\"}";
    }

    @GetMapping(value = "/deleteAmbitoById")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteAmbitoById(@PathParam("id") Integer id) {
        Ambito ambito = iAmbitos.findOne(id);
        if(ambito.isState()){
            ambito.setState(false);
            iAmbitos.save(ambito);
            return "{\"response\": \"Ambito Deshabilitado\"}";
        }
        ambito.setState(true);
        iAmbitos.save(ambito);
        return "{\"response\": \"Ambito Habilitado\"}";
    }

    @GetMapping(value = "/getAmbitoById")
    public Ambito getAmbitoById(@PathParam("id") Integer id) {
        try {
            return iAmbitos.findOne(id);
        } catch (Exception e)
        {
            return null;
        }
    }

}

package com.smartcity.db.model.resource;

import com.smartcity.db.model.Ambito;
import com.smartcity.db.model.SubAmbito;
import com.smartcity.db.model.repository.interfaces.ISubAmbitos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String persist(@RequestBody SubAmbito subAmbito) {
        iSubAmbitos.save(subAmbito);
        return "{\"response\": \"SubAmbito Creado correctamente\"}";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public String updateSubAmbito(@RequestBody SubAmbito subAmbitos) {
        SubAmbito subAmbito = iSubAmbitos.findOne(subAmbitos.getId());
        subAmbito.setName(subAmbitos.getName());
        iSubAmbitos.save(subAmbito);
        return "{\"response\": \"SubAmbito modificado correctamente\"}";
    }

    @GetMapping(value = "/deleteSubAmbitoById")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteSubAmbitoById(@PathParam("id") Integer id) {
        SubAmbito subAmbito= iSubAmbitos.findOne(id);
        if(subAmbito.isState()){
            subAmbito.setState(false);
            iSubAmbitos.save(subAmbito);
            return "{\"response\": \"SubAmbito Deshabilitado\"}";
        }
        subAmbito.setState(true);
        iSubAmbitos.save(subAmbito);
        return "{\"response\": \"SubAmbito Habilitado\"}";
    }

    @GetMapping(value = "/getSubAmbitoById")
    public SubAmbito getSubAmbitoById(@PathParam("id") Integer id) {
        try {
            return iSubAmbitos.findOne(id);
        } catch (Exception e)
        {
            return null;
        }
    }

}
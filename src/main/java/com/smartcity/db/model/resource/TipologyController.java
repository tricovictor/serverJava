package com.smartcity.db.model.resource;

import com.smartcity.db.model.*;
import com.smartcity.db.model.repository.interfaces.ISubAmbitos;
import com.smartcity.db.model.repository.interfaces.ITipologies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 27/11/17.
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/tipologies")
public class TipologyController {


    @Autowired
    ITipologies iTipologies;

    @Autowired
    ISubAmbitos iSubAmbitos;

    @GetMapping(value = "/all")
    public List<Tipology> getAll() { return iTipologies.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<Response> persist(@RequestBody Tipology tipology) {
        iTipologies.save(tipology);
        Response response = new Response();
        response.setResponse("Asociacion creada");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public ResponseEntity<Response> updateTipology(@RequestBody Tipology tipologies) {
        Tipology tipology = iTipologies.findOne(tipologies.getId());
        tipology.setName(tipologies.getName());
        tipology.setDescription(tipologies.getDescription());
        tipology.setDescriptionExtra(tipologies.getDescriptionExtra());
        tipology.setSubAmbitos(tipologies.getSubAmbitos());
        iTipologies.save(tipology);
        Response response = new Response();
        response.setResponse("Tipologia Actualizada");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
    }

    @GetMapping(value = "/getTipologyById")
    public Tipology getTipologyById(@PathParam("id") Integer id) {
        try {
            return iTipologies.findOne(id);
        } catch (Exception e)
        {
            return null;
        }
    }

    @GetMapping(value = "/getSubAmbitosByTipologyById")
    public List<SubAmbito> getSubAmbitoByTipologyById(@PathParam("id") Integer id) {
        List<SubAmbito> subAmbitoList = new ArrayList<>();
        Tipology tipology = iTipologies.findOne(id);
String subambitos = tipology.getSubAmbitos();
        String arrayTotal[] = subambitos.split(",");
        for (int i = 0; i < arrayTotal.length; i++) {
            SubAmbito subAmbito = iSubAmbitos.findOne(Integer.parseInt(arrayTotal[i]));
            subAmbitoList.add(subAmbito);
        }
        return subAmbitoList;
    }


}

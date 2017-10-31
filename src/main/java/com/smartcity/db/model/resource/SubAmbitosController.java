package com.smartcity.db.model.resource;

import com.smartcity.db.model.Response;
import com.smartcity.db.model.SubAmbito;
import com.smartcity.db.model.repository.interfaces.ISubAmbitos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/subambitos")
public class SubAmbitosController {

    @Autowired
    ISubAmbitos iSubAmbitos;

    Response response;

    @GetMapping(value = "/all")
    public List<SubAmbito> getAll() {
        return iSubAmbitos.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<Response> persist(@RequestBody SubAmbito subAmbito) {
        iSubAmbitos.save(subAmbito);
        response.setResponse("SubAmbito Creado correctamente");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public ResponseEntity<Response> updateSubAmbito(@RequestBody SubAmbito subAmbitos) {
        SubAmbito subAmbito = iSubAmbitos.findOne(subAmbitos.getId());
        subAmbito.setName(subAmbitos.getName());
        iSubAmbitos.save(subAmbito);
        response.setResponse("SubAmbito actualizado");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
    }

    @GetMapping(value = "/deleteSubAmbitoById")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Response> deleteSubAmbitoById(@PathParam("id") Integer id) {
        SubAmbito subAmbito= iSubAmbitos.findOne(id);
        if(subAmbito.isState()){
            subAmbito.setState(false);
            iSubAmbitos.save(subAmbito);
            response.setResponse("SubAmbito deshabilitado");
            return new ResponseEntity<Response>(response , HttpStatus.OK);
        }
        subAmbito.setState(true);
        iSubAmbitos.save(subAmbito);
        response.setResponse("SubAmbito habilitado");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
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

    @GetMapping(value = "/getSubAmbitosByAmbito")
    public List<SubAmbito> getSubAmbitosByAmbito(@PathParam("id") int id) {
        try {
            return iSubAmbitos.findByAmbitoId(id);
        } catch (Exception e)
        {
            return null;
        }

    }


}
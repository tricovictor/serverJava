package com.smartcity.db.model.resource;

import com.smartcity.db.model.Ambito;
import com.smartcity.db.model.Response;
import com.smartcity.db.model.SubAmbito;
import com.smartcity.db.model.User;
import com.smartcity.db.model.repository.interfaces.IAmbitos;
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
@RequestMapping(value = "/rest/ambitos")
public class AmbitosController {

    @Autowired
    IAmbitos iAmbitos;

    Response response;

    @GetMapping(value = "/all")
    public List<Ambito> getAll() {
        return iAmbitos.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<Response> persist(@RequestBody Ambito ambito) {
        iAmbitos.save(ambito);
        response.setResponse("Ambito Creado correctamente");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public ResponseEntity<Response> updateUser(@RequestBody Ambito ambitos) {
        Ambito ambito = iAmbitos.findOne(ambitos.getId());
        ambito.setName(ambitos.getName());
        iAmbitos.save(ambito);
        response.setResponse("Ambito actualizado");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
    }

    @GetMapping(value = "/deleteAmbitoById")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Response> deleteAmbitoById(@PathParam("id") Integer id) {
        Ambito ambito = iAmbitos.findOne(id);
        if(ambito.isState()){
            ambito.setState(false);
            iAmbitos.save(ambito);
            response.setResponse("Ambito deshabilitado");
            return new ResponseEntity<Response>(response , HttpStatus.OK);
        }
        ambito.setState(true);
        iAmbitos.save(ambito);
        response.setResponse("Ambito habilitado");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
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

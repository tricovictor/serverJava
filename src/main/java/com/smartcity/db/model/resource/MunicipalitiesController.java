package com.smartcity.db.model.resource;

import com.smartcity.db.model.Municipality;
import com.smartcity.db.model.Response;
import com.smartcity.db.model.repository.interfaces.IMunicipalities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/municipalities")
public class MunicipalitiesController implements Serializable {

    @Autowired
    IMunicipalities iMunicipalities;

    Response response = new Response();

    @GetMapping(value = "/all")
    public List<Municipality> getAll() { return iMunicipalities.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<Response> persist(@RequestBody Municipality municipality) {
        try {
        iMunicipalities.save(municipality);
        response.setResponse("OK");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
        } catch (Exception e) {
            response.setResponse("Datos incorrectos");
            return new ResponseEntity<Response>(response , HttpStatus.OK);
        }

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Response> updateMunicipality(@RequestBody Municipality municipalities) {
        Municipality municipality = iMunicipalities.findOne(municipalities.getId());
        municipality.setName(municipalities.getName());
        municipality.setAlcalde(municipalities.getAlcalde());
        municipality.setDepartmentId(municipalities.getDepartmentId());
        municipality.setHabitants(municipalities.getHabitants());
        municipality.setIdioms(municipalities.getIdioms());
        municipality.setIntendent(municipalities.getIntendent());
        municipality.setSuperficie(municipalities.getSuperficie());
        municipality.setTipologyId(municipalities.getTipologyId());
        municipality.setWebsite(municipalities.getWebsite());
        municipality.setLatitude(municipalities.getLatitude());
        municipality.setLongitude(municipalities.getLongitude());
        iMunicipalities.save(municipality);
        Response responses = new Response();
        responses.setResponse("Municipio actualizado");
        return new ResponseEntity<Response>(responses , HttpStatus.OK);
    }

    @GetMapping(value = "/getMunicipalityById")
    public Municipality getUserById(@PathParam("id") Integer id) {
        try {
            return iMunicipalities.findOne(id);
        } catch (Exception e)
        {
            return null;
        }
    }

}

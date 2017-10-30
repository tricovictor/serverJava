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
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/municipalities")
public class MunicipalitiesController {

    @Autowired
    IMunicipalities iMunicipalities;

    Response response;

    @GetMapping(value = "/all")
    public List<Municipality> getAll() { return iMunicipalities.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<Response> persist(@RequestBody Municipality municipality) {
        iMunicipalities.save(municipality);
        response.setResponse("Municipio Creado correctamente");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Response> updateMunicipality(@RequestBody Municipality municipalities) {
        Municipality municipality = iMunicipalities.findOne(municipalities.getId());
        municipality.setName(municipalities.getName());
        municipality.setAlcalde(municipalities.getAlcalde());
        municipality.setDepartment_id(municipalities.getDepartment_id());
        municipality.setHabitants(municipalities.getHabitants());
        municipality.setIdioms(municipalities.getIdioms());
        municipality.setIntendent(municipalities.getIntendent());
        municipality.setSuperficie(municipalities.getSuperficie());
        municipality.setWebsite(municipalities.getWebsite());
        municipality.setLatitude(municipalities.getLatitude());
        municipality.setLongitude(municipalities.getLongitude());
        iMunicipalities.save(municipality);
        response.setResponse("Municipio actualizado");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
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

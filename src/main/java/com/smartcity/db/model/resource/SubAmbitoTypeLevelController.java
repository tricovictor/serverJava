package com.smartcity.db.model.resource;

import com.smartcity.db.model.Response;
import com.smartcity.db.model.SubAmbito;
import com.smartcity.db.model.SubAmbitoTypeLevel;
import com.smartcity.db.model.repository.interfaces.ISubAmbitoTypeLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.List;


@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/subambitostypelevel")
public class SubAmbitoTypeLevelController {

    @Autowired
    ISubAmbitoTypeLevel iSubAmbitoTypeLevel;

    Response response;

    @GetMapping(value = "/all")
    public List<SubAmbitoTypeLevel> getAll() {
        System.out.println(iSubAmbitoTypeLevel.count());
        return iSubAmbitoTypeLevel.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<Response> persist(@RequestBody SubAmbitoTypeLevel subAmbitoTypeLevel) {
        iSubAmbitoTypeLevel.save(subAmbitoTypeLevel);
        response.setResponse("Asociado correctamente");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public ResponseEntity<Response> updateSubAmbitoTypeLevel(@RequestBody SubAmbitoTypeLevel subAmbitoTypeLevels) {
        SubAmbitoTypeLevel subAmbitoTypeLevel = iSubAmbitoTypeLevel.findOne(subAmbitoTypeLevels.getId());
        subAmbitoTypeLevel.setSubAmbitoId(subAmbitoTypeLevel.getSubAmbitoId());
        subAmbitoTypeLevel.setTypeLevelId(subAmbitoTypeLevel.getTypeLevelId());
        iSubAmbitoTypeLevel.save(subAmbitoTypeLevel);
        response.setResponse("Asociacion actualizada");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
    }

    @GetMapping(value = "/getSubAmbitosTypeLevel")
    public SubAmbitoTypeLevel getSubAmbitosTypeLevel(@PathParam("id") Integer id) {
        try {
            return iSubAmbitoTypeLevel.findBySubAmbitoId(id);
        } catch (Exception e)
        {
            return null;
        }

    }


}
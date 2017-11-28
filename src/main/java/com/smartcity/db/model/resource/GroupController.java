package com.smartcity.db.model.resource;

import com.smartcity.db.model.Ambito;
import com.smartcity.db.model.GraphGroup;
import com.smartcity.db.model.Response;
import com.smartcity.db.model.repository.interfaces.IAmbitos;
import com.smartcity.db.model.repository.interfaces.IGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by victor on 24/11/17.
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/graphGroup")
public class GroupController {

    @Autowired
    IGroups iGroups;

    Response response;

    @GetMapping(value = "/all")
    public List<GraphGroup> getAll() {
        return iGroups.findAll();
    }


}

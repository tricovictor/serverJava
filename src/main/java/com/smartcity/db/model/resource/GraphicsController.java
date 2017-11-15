package com.smartcity.db.model.resource;

import com.smartcity.db.model.Response;
import com.smartcity.db.model.repository.interfaces.IGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/groups")
public class GraphicsController {

    @Autowired
    IGroups iGroups;

    Response response;

    @GetMapping(value = "/all")
    public String getAll() {
        return iGroups.findAll().toString();
    }
}

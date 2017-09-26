package com.smartcity.db.model.resource;


import com.smartcity.db.model.Surveys;
import com.smartcity.db.model.repository.interfaces.ISurveysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/surveys")
public class SurveysResource {

    @Autowired
    ISurveysRepository iSurveysRepository;

    @GetMapping(value = "/all")
    public List<Surveys> getAll() {
        return iSurveysRepository.findAll();
    }

    @PostMapping(value = "/load")
    public List<Surveys> persist(@RequestBody final Surveys surveys) {
        iSurveysRepository.save(surveys);
        return iSurveysRepository.findAll();
    }
}


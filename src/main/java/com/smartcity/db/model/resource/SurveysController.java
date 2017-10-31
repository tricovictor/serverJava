package com.smartcity.db.model.resource;


import com.smartcity.db.model.Survey;
import com.smartcity.db.model.repository.interfaces.ISurveys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/surveys")
public class SurveysController {

    @Autowired
    ISurveys iSurveys;

    @GetMapping(value = "/all")
    public List<Survey> getAll() {
        return iSurveys.findAll();
    }

    @PostMapping(value = "/load")
    public List<Survey> persist(@RequestBody final Survey survey) {
        iSurveys.save(survey);
        return iSurveys.findAll();
    }
}


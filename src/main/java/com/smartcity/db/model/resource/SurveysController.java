package com.smartcity.db.model.resource;


import com.smartcity.db.model.*;
import com.smartcity.db.model.repository.interfaces.ISurveys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/surveys")
public class SurveysController {

    @Autowired
    ISurveys iSurveys;

    Response response;

    @GetMapping(value = "/all")
    public List<Survey> getAll() {
        return iSurveys.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Survey persist(@RequestBody Survey surveys) {
        if(iSurveys.existsByMunicipalityIdAndUserId(surveys.getMunicipalityId(), surveys.getUserId()))
        {
            return null;
        }
        Date date = new Date();
        surveys.setInitialdate(date);
        surveys.setFinaldate(date);
        surveys.setState("activa");
        surveys.setScores(null);
        iSurveys.save(surveys);
        try {

        SubAmbitosController subAmbitosController = new SubAmbitosController();
        List<SubAmbito> subAmbitoList = subAmbitosController.iSubAmbitos.findAll();
        SubAmbitoTypeLevelController subAmbitoTypeLevelController = new SubAmbitoTypeLevelController();
        DegreesController degreesController = new DegreesController();
        ScoresController scoresController = new ScoresController();

        for (int i=0 ; i<subAmbitoList.size() ; i++) {
            SubAmbitoTypeLevel subAmbitoTypeLevel = subAmbitoTypeLevelController.
                    getSubAmbitosTypeLevel(subAmbitoList.get(i).getId());
            List<Degree> degreeList =
                    degreesController.getDegreeByType(subAmbitoTypeLevel.getSubAmbitoId());
            for (int j=0 ; j<degreeList.size() ; j++) {
                Score score = new Score();
                score.setSubAmbito(subAmbitoList.get(i));
                score.setDegree(degreeList.get(j));
                score.setLevelId(0);
                score.setSurvey(surveys);
                scoresController.iScores.save(score);
            //falta aqui
            }
        }
        } catch (Exception e){
            System.out.println(e);
        }

        return surveys;
    }
}


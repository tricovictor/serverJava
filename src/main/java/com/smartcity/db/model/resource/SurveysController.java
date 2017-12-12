package com.smartcity.db.model.resource;


import com.smartcity.db.model.*;
import com.smartcity.db.model.repository.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.ws.rs.PathParam;
import java.util.Date;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/surveys")
public class SurveysController {

    @Autowired
    ISurveys iSurveys;

    @Autowired
    ISubAmbitoTypeLevel iSubAmbitoTypeLevel;

    @Autowired
    ISubAmbitos iSubAmbitos;

    @Autowired
    IDegrees iDegrees;

    @Autowired
    IScores iScores;

    Response response;

    GraphicsController graphicsController = new GraphicsController();


    @PersistenceContext
    EntityManager em;

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
        iSurveys.save(surveys);
        addSurvey(surveys);
        return surveys;
    }

    @GetMapping(value = "/addSurvey")
    private void addSurvey(Survey survey){

        try {
            List<SubAmbitoTypeLevel> subAmbitoTypeLevelList = iSubAmbitoTypeLevel.findAll();
            for (SubAmbitoTypeLevel subAmbitoTypeLevel : subAmbitoTypeLevelList ) {
                List<Degree> degreeList = iDegrees.findByTypeLevelId(subAmbitoTypeLevel.getTypeLevelId());
                for (Degree degree : degreeList) {
                    Date date = new Date();
                    survey.setInitialdate(date);
                    survey.setFinaldate(date);
                    Score score = new Score();
                    score.setSurvey(survey);
                    score.setLevelId(0);
                    score.setSubAmbito(iSubAmbitos.findOne(subAmbitoTypeLevel.getSubAmbitoId()));
                    score.setDegree(degree);
                    iScores.save(score);
                }
            }
        } catch (Exception e){
            System.out.print(e.toString());
        }
    }

    @GetMapping(value = "/getSurveyById")
    private Survey getSurveyById(@PathParam("id") Integer id) {
        return iSurveys.findByMunicipalityIdAndState(id, "activa");
    }

    @GetMapping(value = "/closeSurvey")
    public Survey closeSurvey(@PathParam("id") Integer id) {
        Survey survey = iSurveys.findOne(id);
        survey.setState("inactiva");
        iSurveys.save(survey);
        return survey;
    }

    @GetMapping(value = "/validateSurvey")
    public ResponseEntity<Response> validateSurvey(@PathParam("id") Integer id) {
        List<Score> scores = iScores.findBySurveyIdAndLevelId(id, 0);
        if(scores.size() > 0){
            Response response = new Response();
            response.setResponse("Encuesta Incompleta");
            return new ResponseEntity<Response>(response , HttpStatus.OK);

        }
        Response response = new Response();
        response.setResponse("");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
    }


    }


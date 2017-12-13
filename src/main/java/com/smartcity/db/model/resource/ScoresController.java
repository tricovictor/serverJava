package com.smartcity.db.model.resource;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smartcity.db.model.*;
import com.smartcity.db.model.repository.interfaces.IAmbitos;
import com.smartcity.db.model.repository.interfaces.IScores;
import com.smartcity.db.model.repository.interfaces.ISubAmbitos;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@EnableWebMvc
@RequestMapping(value = "/rest/scores")
public class ScoresController {

    @Autowired
    IScores iScores;

    @Autowired
    IAmbitos iAmbitos;

    @Autowired
    ISubAmbitos iSubAmbitos;

    Response response;

    @GetMapping(value = "/all")
    public List<Score> getAll() { return iScores.findAll();
    }

    @GetMapping(value = "/getScoreByMunicipality")
    public List<Score> getScoreByMunicipality(@PathParam("id") Integer id) {
        return iScores.findBySurveyId(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateScore")
    public void updateScore(@RequestBody Score scores) {

        System.out.println(scores.getLevelId());
        Score score = iScores.findOne(scores.getId());
        score.setLevelId(scores.getLevelId());
        iScores.save(score);
    }

    @GetMapping(value = "/surveyInActive")
    public List<Score> getCountRest(@PathParam("id") Integer id) {
        List<Score> scores = iScores.findBySurveyIdAndLevelId(id, 0);
        return scores;
    }

    @GetMapping(value = "/surveyInActiveToSubAmbito")
    public List getCountRestAmbito(@PathParam("id") Integer id) {
        List<SubAmbito> subAmbitoList = iSubAmbitos.findAll();
        List<Score> scoreList = iScores.findBySurveyIdAndLevelId(id,0);
        List listambito = new ArrayList();
        boolean finalizado = false;
        for (SubAmbito subAmbito : subAmbitoList) {
            for(Score score : scoreList){
                if(score.getSubAmbito().getId() == subAmbito.getId()){
                    listambito.add(1);
                    finalizado = true;
                    break;
                }
            }
            if(!finalizado){
                listambito.add(0);
            }
            finalizado = false;
        }
        return listambito;
    }

    @GetMapping(value = "/surveyInActiveToAmbito")
    public List getCountRestSubAmbito(@PathParam("id") Integer id) {
        List<SubAmbito> subAmbitoList = iSubAmbitos.findAll();
        List<Ambito> ambitoList = iAmbitos.findAll();
        List<Score> scoreList = iScores.findBySurveyIdAndLevelId(id,0);
        List listSubambito = new ArrayList();
        List listAmbito = new ArrayList();
        boolean finalizado = false;
        listSubambito = getCountRestAmbito(id);
        finalizado = false;
        Integer valor = 0;
        for(Ambito ambito : ambitoList) {
            for(SubAmbito subAmbito : subAmbitoList){
                if(ambito.getId().equals(subAmbito.getAmbito().getId())) {
                    valor = Integer.parseInt(listSubambito.get(subAmbito.getId()-1).toString());
                    if(valor.equals(1)){
                        listAmbito.add(1);
                        finalizado = true;
                        break;
                    }
                }

            }
            if(!finalizado){
                listAmbito.add(0);
            }
            finalizado = false;
        }

        return listAmbito;
    }

}

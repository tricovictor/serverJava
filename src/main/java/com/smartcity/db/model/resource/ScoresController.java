package com.smartcity.db.model.resource;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smartcity.db.model.Ambito;
import com.smartcity.db.model.Response;
import com.smartcity.db.model.Score;
import com.smartcity.db.model.User;
import com.smartcity.db.model.repository.interfaces.IScores;
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
@RequestMapping(value = "/rest/scores")
public class ScoresController {

    @Autowired
    IScores iScores;

    Response response;

    @GetMapping(value = "/all")
    public List<Score> getAll() { return iScores.findAll();
    }

    @GetMapping(value = "/getScoreByMunicipality")
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
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

}

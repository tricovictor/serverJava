package com.smartcity.db.model.resource;


import com.smartcity.db.model.Ambito;
import com.smartcity.db.model.Response;
import com.smartcity.db.model.Score;
import com.smartcity.db.model.repository.interfaces.IScores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//Pendiente hasta que este pronto crear la encuesta

 /*   @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<Response> persist(@RequestBody Score score) {
        iScores.save(score);
        response.setResponse("Guardado ok");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
    }*/



}

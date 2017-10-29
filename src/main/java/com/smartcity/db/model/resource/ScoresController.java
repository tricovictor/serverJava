package com.smartcity.db.model.resource;


import com.smartcity.db.model.Score;
import com.smartcity.db.model.repository.interfaces.IScores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/scores")
public class ScoresController {

    @Autowired
    IScores iScores;

    @GetMapping(value = "/all")
    public List<Score> getAll() { return iScores.findAll();
    }

    @PostMapping(value = "/load")
    public List<Score> persist(@RequestBody final Score score) {
        iScores.save(score);
        return iScores.findAll();
    }

}

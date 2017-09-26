package com.smartcity.db.model.resource;


import com.smartcity.db.model.Scores;
import com.smartcity.db.model.repository.interfaces.IScoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/scores")
public class ScoresResource {

    @Autowired
    IScoresRepository iScoresRepository;

    @GetMapping(value = "/all")
    public List<Scores> getAll() { return iScoresRepository.findAll();
    }

    @PostMapping(value = "/load")
    public List<Scores> persist(@RequestBody final Scores scores) {
        iScoresRepository.save(scores);
        return iScoresRepository.findAll();
    }

}

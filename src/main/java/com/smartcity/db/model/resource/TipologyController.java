package com.smartcity.db.model.resource;

import com.smartcity.db.model.*;
import com.smartcity.db.model.repository.interfaces.*;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 27/11/17.
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/tipologies")
public class TipologyController {


    @Autowired
    ITipologies iTipologies;

    @Autowired
    ISubAmbitos iSubAmbitos;

    @Autowired
    IScores iScores;

    @Autowired
    IMunicipalities iMunicipalities;

    @Autowired
    ISurveys iSurveys;

    @Autowired
    ILevels iLevels;

    @Autowired
    ITipologyOk iTipologyOk;

    @Autowired
    ISubAmbitosBusiness iSubAmbitosBusiness;

    @Autowired
    IBusiness iBusiness;


    @GetMapping(value = "/all")
    public List<Tipology> getAll() { return iTipologies.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<Response> persist(@RequestBody Tipology tipology) {
        iTipologies.save(tipology);
        Response response = new Response();
        response.setResponse("Asociacion creada");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public ResponseEntity<Response> updateTipology(@RequestBody Tipology tipologies) {
        Tipology tipology = iTipologies.findOne(tipologies.getId());
        tipology.setName(tipologies.getName());
        tipology.setDescription(tipologies.getDescription());
        tipology.setDescriptionExtra(tipologies.getDescriptionExtra());
        tipology.setSubAmbitos(tipologies.getSubAmbitos());
        iTipologies.save(tipology);
        Response response = new Response();
        response.setResponse("Tipologia Actualizada");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
    }

    @GetMapping(value = "/getTipologyById")
    public Tipology getTipologyById(@PathParam("id") Integer id) {
        try {
            return iTipologies.findOne(id);
        } catch (Exception e)
        {
            return null;
        }
    }

    @GetMapping(value = "/getSubAmbitosByTipologyById")
    public List<SubAmbito> getSubAmbitoByTipologyById(@PathParam("id") Integer id) {
        List<SubAmbito> subAmbitoList = new ArrayList<>();
        Tipology tipology = iTipologies.findOne(id);
        String subambitos = tipology.getSubAmbitos();
        String arrayTotal[] = subambitos.split(",");
        for (int i = 0; i < arrayTotal.length; i++) {
            SubAmbito subAmbito = iSubAmbitos.findOne(Integer.parseInt(arrayTotal[i]));
            subAmbitoList.add(subAmbito);
        }
        return subAmbitoList;
    }

    @GetMapping(value = "/getSubAmbitosByTipologyComparative")
    public List<SubAmbitoTipology> getSubAmbitoByTipologyComparative(@PathParam("id") Integer id) {
        List<Score> scoreList = iScores.findBySurveyId(id);
        List<SubAmbito> subAmbitoList = new ArrayList<>();
        List<SubAmbitoBusiness> subAmbitoBusinessList = new ArrayList<>();
        List<Business> businessList = new ArrayList<>();
        Tipology tipology = iTipologies.findOne(iMunicipalities.findOne(iSurveys.findOne(id).getMunicipalityId()).getTipologyId());
        String subambitos = tipology.getSubAmbitos();
        String arrayTotal[] = subambitos.split(",");
        for (int i = 0; i < arrayTotal.length; i++) {
            SubAmbito subAmbito = iSubAmbitos.findOne(Integer.parseInt(arrayTotal[i]));
            subAmbitoList.add(subAmbito);
        }
        List<SubAmbitoTipology> subAmbitoTipologiesList = new ArrayList<>();
        for(SubAmbito subAmbito : subAmbitoList){
            int puntaje =0;
            int cantPuntajes =0;
            SubAmbitoTipology subAmbitoTipology = new SubAmbitoTipology();
            subAmbitoTipology.setNameTipology(tipology.getName());
            subAmbitoTipology.setSubAmbitos(subAmbito.getName());
            subAmbitoTipology.setDescription(tipology.getDescription());
            subAmbitoTipology.setDescriptionExtra(tipology.getDescriptionExtra());
            for (Score score : scoreList) {
                if (score.getSubAmbito().getId() == subAmbito.getId() ) {
                    if(score.getLevelId() !=0 && iLevels.findOne(score.getLevelId()).getValue() !=0){
                        int cantLevels = iLevels.findByDegreeId(score.getDegree().getId()).size();
                        puntaje += 100/cantLevels*iLevels.findOne(score.getLevelId()).getValue();
                        cantPuntajes++;
                    } else {
                        cantPuntajes++;
                    }
                    subAmbitoTipology.setScore(puntaje/cantPuntajes);
                }
            }
            if(iTipologyOk.findOne(1).getPercentage() > subAmbitoTipology.getScore()) {
                subAmbitoBusinessList = new ArrayList<>();
                subAmbitoBusinessList = iSubAmbitosBusiness.findBySubAmbitoId(subAmbito.getId());
                businessList = new ArrayList<>();
                for(SubAmbitoBusiness subAmbitoBusiness : subAmbitoBusinessList) {
                    businessList.add(iBusiness.findOne(subAmbitoBusiness.getBusinessId()));
                }
                JSONArray business = new JSONArray();
                business.put(businessList);
                subAmbitoTipology.setBusinessList(business.toString());
                subAmbitoTipologiesList.add(subAmbitoTipology);
            }
        }
        return subAmbitoTipologiesList ;
    }
}

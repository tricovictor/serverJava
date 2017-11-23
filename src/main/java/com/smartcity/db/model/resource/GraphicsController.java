package com.smartcity.db.model.resource;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.smartcity.db.model.*;
import com.smartcity.db.model.repository.interfaces.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/groups")
public class GraphicsController {

    @Autowired
    IGroups iGroups;

    @Autowired
    ISubAmbitos iSubAmbitos;

    @Autowired
    IScores iScores;

    @Autowired
    IDegrees iDegrees;

    @Autowired
    ILevels iLevels;

    @Autowired
    IGraphics iGraphics;

    @Autowired
    IGraphicsGroupAux iGraphicsGroupAux;

    Response response;

    @GetMapping(value = "/all")
    public List<GraphGroup> getAll() {
        return iGroups.findAll();
    }

    @GetMapping(value = "/getGraphics")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Graphic> getGraphics(@PathParam("id") Integer id) {
        iGraphics.deleteAll();
        List<Score> scoreList = iScores.findBySurveyId(id);
        JSONArray labels = new JSONArray();
        JSONArray data = new JSONArray();
        int subAmbito = 0;
        try {
            for (Score score : scoreList) {
                if (score.getSubAmbito().getId() == subAmbito || subAmbito ==0) {
                    labels.put(iDegrees.findOne(score.getDegree().getId()).getName());
                    if(score.getLevelId() !=0 && iLevels.findOne(score.getLevelId()).getValue() !=0){
                        int cantLevels = iLevels.findByDegreeId(score.getDegree().getId()).size();
                        data.put(100/cantLevels*iLevels.findOne(score.getLevelId()).getValue());
                    } else {
                        data.put(0);
                    }
                    subAmbito = score.getSubAmbito().getId();
                }
                if (score.getSubAmbito().getId() != subAmbito) {
                    Graphic graphic = new Graphic();
                    graphic.setData(data.toString());
                    graphic.setName(iSubAmbitos.findOne(score.getSubAmbito().getId()).getName());
                    graphic.setGroupId(iSubAmbitos.findOne(score.getSubAmbito().getId()).getGroupId());
                    graphic.setLabels(labels.toString());
                    iGraphics.save(graphic);
                    labels = new JSONArray();
                    data = new JSONArray();
                    subAmbito = score.getSubAmbito().getId();
                }
            }
        } catch (Exception e){
            return null;
        }

        return iGraphics.findAll();
    }

   @GetMapping(value = "/getGraphicsGroup")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphicGroupAux> getGraphicsGroup() {
        iGraphicsGroupAux.deleteAll();
        List<Graphic> graphicsList = iGraphics.findAll();
        Collections.sort(graphicsList, new Comparator<Graphic>() {
            @Override
            public int compare(Graphic graphic, Graphic t1) {
                return graphic.getGroupId().compareTo(t1.getGroupId());
            }

            @Override
            public boolean equals(Object o) {
                return false;
            }
        });
        JSONArray labels = new JSONArray();
        JSONArray data = new JSONArray();
        int groupId = 0;
        try {
            for (Graphic graph : graphicsList) {
                if (graph.getGroupId() == groupId || groupId == 0) {
                    labels.put(graph.getName());
                    groupId = graph.getGroupId();
                    Integer total = 0;
                    String arrayTotal[] = graph.getData().substring(1, graph.getData().length()-1).split(",");
                    System.out.println(arrayTotal[0]);
                    for (int i=0; i<arrayTotal.length; i++)
                    {
                        total += Integer.parseInt(arrayTotal[i]);
                    }
                    data.put(total/arrayTotal.length);
                }
                if (graph.getGroupId() != groupId) {
                    GraphicGroupAux graphic = new GraphicGroupAux();
                    graphic.setName(iGroups.findOne(graph.getGroupId()).getName());
                    graphic.setLabels(labels.toString());
                    graphic.setData(data.toString());
                    iGraphicsGroupAux.save(graphic);
                    labels = new JSONArray();
                    data = new JSONArray();
                    groupId = graph.getGroupId();
                }
            }
        } catch (Exception e) {
            return null;
        }
        return iGraphicsGroupAux.findAll();
    }
    /*{
        labels: ['Running', 'Swimming', 'Eating', 'Cycling'],
        datasets: [{
        data: [20, 10, 4, 2]
    }]
    }*/
}

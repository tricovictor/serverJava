package com.smartcity.db.model.resource;

import com.smartcity.db.model.*;
import com.smartcity.db.model.repository.interfaces.*;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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

    @Autowired
    IGraphicAmbitoAux iGraphicAmbitoAux;

    @Autowired
    IAmbitos iAmbitos;

    Response response;

    @GetMapping(value = "/all")
    public List<GraphGroup> getAll() {
        return iGroups.findAll();
    }

    @GetMapping(value = "/getGraphics")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Graphic> getGraphics(@PathParam("id") Integer id) {
        resetAux();
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
        List<GraphGroup> graphicGroupList = iGroups.findAll();
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
        try {
            for(GraphGroup graphGroup : graphicGroupList) {
                for (Graphic graph : graphicsList) {
                    if (graph.getGroupId().equals(graphGroup.getId())) {
                        labels.put(graph.getName());
                        Integer total = 0;
                        String arrayTotal[] = graph.getData().substring(1, graph.getData().length() - 1).split(",");
                        for (int i = 0; i < arrayTotal.length; i++) {
                            total += Integer.parseInt(arrayTotal[i]);
                        }
                        data.put(total / arrayTotal.length);
                    }
                }
                GraphicGroupAux graphic = new GraphicGroupAux();
                graphic.setGroupId(graphGroup.getId());
                graphic.setName(graphGroup.getName());
                graphic.setLabels(labels.toString());
                graphic.setData(data.toString());
                iGraphicsGroupAux.save(graphic);
                labels = new JSONArray();
                data = new JSONArray();
            }
        } catch (Exception e) {
            return null;
        }
        return iGraphicsGroupAux.findAll();
    }

    @GetMapping(value = "/getGraphicsAmbitos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphicAmbitoAux> getGraphicsAmbitos() {
        List<Ambito> ambitoList = iAmbitos.findAll();
        List<GraphGroup> graphicGroupList = iGroups.findAll();
        JSONArray labels = new JSONArray();
        JSONArray data = new JSONArray();
        try {
            for(Ambito ambito : ambitoList) {
                for (GraphGroup graphGroup : graphicGroupList) {
                    if (graphGroup.getAmbitoId().equals(ambito.getId())) {
                            GraphicGroupAux graphicGroupAux = iGraphicsGroupAux.findByGroupId(graphGroup.getId());
                            labels.put(graphicGroupAux.getName());
                            Integer total = 0;
                            String arrayTotal[] = graphicGroupAux.getData().substring(1, graphicGroupAux.getData().length() - 1).split(",");
                            for (int i = 0; i < arrayTotal.length; i++) {
                                total += Integer.parseInt(arrayTotal[i]);
                            }
                            data.put(total / arrayTotal.length);
                    }
                }
                GraphicAmbitoAux graphic = new GraphicAmbitoAux();
                graphic.setName(ambito.getName());
                graphic.setLabels(labels.toString());
                graphic.setData(data.toString());
                iGraphicAmbitoAux.save(graphic);
                labels = new JSONArray();
                data = new JSONArray();
            }
        } catch (Exception e) {
            return null;
        }
        return iGraphicAmbitoAux.findAll();
    }
    private void resetAux(){
        iGraphics.deleteAll();
        iGraphicsGroupAux.deleteAll();
        iGraphicAmbitoAux.deleteAll();
    }

}

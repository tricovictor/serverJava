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

    @GetMapping(value = "/generateGraphics")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Graphic> generateGraphics(@PathParam("id") Integer id) {
        //resetAux();
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
                    graphic.setSurveyId(id);
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

   @GetMapping(value = "/generateGraphicsGroup")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphicGroupAux> generateGraphicsGroup(@PathParam("id") Integer id) {
        List<GraphGroup> graphicGroupList = iGroups.findAll();
        List<Graphic> graphicsList = iGraphics.findAll();
        JSONArray labels = new JSONArray();
        JSONArray data = new JSONArray();
        try {
            for(GraphGroup graphGroup : graphicGroupList) {
                for (Graphic graph : graphicsList) {
                    if (graph.getGroupId().equals(graphGroup.getId()) && graph.getSurveyId().equals(id) ) {
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
                graphic.setSurveyId(id);
                iGraphicsGroupAux.save(graphic);
                labels = new JSONArray();
                data = new JSONArray();
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }
        return iGraphicsGroupAux.findAll();
    }

    @GetMapping(value = "/generateGraphicsAmbitos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphicAmbitoAux> generateGraphicsAmbitos(@PathParam("id") Integer id) {
        List<Ambito> ambitoList = iAmbitos.findAll();
        List<GraphGroup> graphicGroupList = iGroups.findAll();
        JSONArray labels = new JSONArray();
        JSONArray data = new JSONArray();
        try {
            for(Ambito ambito : ambitoList) {
                for (GraphGroup graphGroup : graphicGroupList) {
                    if (graphGroup.getAmbitoId().equals(ambito.getId())) {
                            GraphicGroupAux graphicGroupAux = iGraphicsGroupAux.findByGroupIdAndSurveyId(graphGroup.getId(),id);
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
                graphic.setSurveyId(id);
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

    @GetMapping(value = "/getGraphics")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Graphic> getGraphics(@PathParam("id") Integer id) {
        return iGraphics.findBySurveyId(id);
    }

    @GetMapping(value = "/getGraphicsGroup")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphicGroupAux> getGraphicsGroup(@PathParam("id") Integer id) {
        return iGraphicsGroupAux.findBySurveyId(id);
    }

    @GetMapping(value = "/getGraphicsAmbitos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GraphicAmbitoAux> getGraphicsAmbitos(@PathParam("id") Integer id) {
        return iGraphicAmbitoAux.findBySurveyId(id);
    }

}

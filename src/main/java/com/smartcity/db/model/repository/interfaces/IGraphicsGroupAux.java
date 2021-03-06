package com.smartcity.db.model.repository.interfaces;

import com.smartcity.db.model.GraphicGroupAux;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by victor on 20/11/17.
 */
public interface IGraphicsGroupAux extends JpaRepository<GraphicGroupAux, Integer> {
    GraphicGroupAux findByGroupIdAndSurveyId(Integer groupId, Integer surveyId);
    List<GraphicGroupAux> findBySurveyId(Integer id);
}

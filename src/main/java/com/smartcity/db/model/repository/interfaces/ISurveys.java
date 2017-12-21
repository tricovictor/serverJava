package com.smartcity.db.model.repository.interfaces;

import com.smartcity.db.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISurveys extends JpaRepository<Survey, Integer> {

    boolean existsByMunicipalityIdAndState(Integer municipalityId, String state);
    Survey findByMunicipalityIdAndState(Integer municipalityId, String state);
    List<Survey> findByState(String state);
}

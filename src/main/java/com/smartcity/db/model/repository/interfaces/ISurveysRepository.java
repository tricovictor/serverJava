package com.smartcity.db.model.repository.interfaces;

import com.smartcity.db.model.Surveys;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISurveysRepository extends JpaRepository<Surveys, Integer> {
}

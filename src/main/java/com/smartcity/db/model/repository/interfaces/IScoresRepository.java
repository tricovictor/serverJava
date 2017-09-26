package com.smartcity.db.model.repository.interfaces;

import com.smartcity.db.model.Scores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IScoresRepository extends JpaRepository<Scores, Integer> {
}

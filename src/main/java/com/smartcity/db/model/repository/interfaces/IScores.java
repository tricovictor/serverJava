package com.smartcity.db.model.repository.interfaces;

import com.smartcity.db.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IScores extends JpaRepository<Score, Integer> {
}

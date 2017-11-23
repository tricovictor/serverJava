package com.smartcity.db.model.repository.interfaces;

import com.smartcity.db.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILevels extends JpaRepository<Level, Integer> {

    List<Level> findByDegreeId(Integer degree);
}

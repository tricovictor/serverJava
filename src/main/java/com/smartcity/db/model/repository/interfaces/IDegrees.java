package com.smartcity.db.model.repository.interfaces;

import com.smartcity.db.model.Degree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDegrees extends JpaRepository<Degree, Integer> {
    List<Degree> findByTypeLevel(Integer level);

    List<Degree> findByTypeLevelId(Integer id);
}

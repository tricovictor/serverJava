package com.smartcity.db.model.repository.interfaces;

import com.smartcity.db.model.SubAmbito;
import com.smartcity.db.model.SubAmbitoTypeLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISubAmbitoTypeLevel extends JpaRepository<SubAmbitoTypeLevel, Integer> {

    SubAmbitoTypeLevel findBySubAmbitoId(int ambito);
}

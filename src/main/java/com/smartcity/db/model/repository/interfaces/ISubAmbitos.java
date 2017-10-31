package com.smartcity.db.model.repository.interfaces;

import com.smartcity.db.model.SubAmbito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISubAmbitos extends JpaRepository<SubAmbito, Integer> {

    List<SubAmbito> findByAmbitoId(int ambito);
}

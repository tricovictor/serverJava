package com.smartcity.db.model.repository.interfaces;

import com.smartcity.db.model.SubAmbitoBusiness;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by victor on 29/11/17.
 */
public interface ISubAmbitosBusiness extends JpaRepository<SubAmbitoBusiness, Integer> {
    List<SubAmbitoBusiness> findBySubAmbitoId(Integer subambito);
}

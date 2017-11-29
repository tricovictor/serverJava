package com.smartcity.db.model.repository.interfaces;

import com.smartcity.db.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by victor on 29/11/17.
 */
public interface IBusiness extends JpaRepository<Business, Integer> {
}

package com.smartcity.db.model.repository.interfaces;

import com.smartcity.db.model.Degrees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDegreesRepository extends JpaRepository<Degrees, Integer> {
}

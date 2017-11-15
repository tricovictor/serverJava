package com.smartcity.db.model.repository.interfaces;

import com.smartcity.db.model.GraphGroup;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IGroups  extends JpaRepository<GraphGroup, Integer> {
}

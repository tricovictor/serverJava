package com.smartcity.db.model.repository.interfaces;

import com.smartcity.db.model.Graphic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by victor on 20/11/17.
 */
public interface IGraphics extends JpaRepository<Graphic, Integer> {
}

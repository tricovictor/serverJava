package com.smartcity.db.model.repository.interfaces;

import com.smartcity.db.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsers extends JpaRepository<User, Integer> {
}

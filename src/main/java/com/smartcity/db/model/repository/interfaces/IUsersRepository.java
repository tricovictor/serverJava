package com.smartcity.db.model.repository.interfaces;

import com.smartcity.db.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsersRepository extends JpaRepository<Users, Integer> {
}

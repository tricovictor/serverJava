package com.smartcity.db.model.resource;

import com.smartcity.db.model.Users;
import com.smartcity.db.model.repository.interfaces.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/users")
public class UsersResource {

    @Autowired
    IUsersRepository iUsersRepository;

    @GetMapping(value = "/all")
    public List<Users> getAll() {
        return iUsersRepository.findAll();
    }

    @PostMapping(value = "/load")
    public List<Users> persist(@RequestBody final Users users) {
        iUsersRepository.save(users);
        return iUsersRepository.findAll();
    }


}

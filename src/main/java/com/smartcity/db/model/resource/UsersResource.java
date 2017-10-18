package com.smartcity.db.model.resource;

import com.smartcity.db.model.Users;
import com.smartcity.db.model.repository.interfaces.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@EnableWebMvc
@RequestMapping(value = "/rest/users")
public class UsersResource {

    @Autowired
    IUsersRepository iUsersRepository;

    @GetMapping(value = "/all")
    public List<Users> getAll() {
        return iUsersRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String persist(@RequestBody Users users) {
        iUsersRepository.save(users);
        return "Usuario Creado correctamente";
    }


}

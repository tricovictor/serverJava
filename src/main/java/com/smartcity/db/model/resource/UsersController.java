package com.smartcity.db.model.resource;

import com.smartcity.db.model.User;
import com.smartcity.db.model.repository.interfaces.IUsers;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@EnableWebMvc
@RequestMapping(value = "/rest/users")
public class UsersController {

    @Autowired
    IUsers iUsers;

    @GetMapping(value = "/all")
    public List<User> getAll() {
        return iUsers.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String persist(@RequestBody User user) {
        iUsers.save(user);
        return "Usuario Creado correctamente";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public String updateUser(@RequestBody User users) {
        User user = iUsers.findOne(users.getId());
        user.setEmail(users.getEmail());
        user.setName(users.getName());
        user.setPassword(users.getPassword());
        user.setLastname(users.getLastname());
        user.setPhone(users.getPhone());
        user.setState(true);
        user.setType(users.getType());
        iUsers.save(user);
        return "Usuario Creado correctamente";
    }

    @GetMapping(value = "/deleteUserById")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUserById(@PathParam("id") Integer id) {
        User user = iUsers.findOne(id);
        if(user.isState()){
            user.setState(false);
            iUsers.save(user);
            return "Usuario Deshabilitado";
        }
        user.setState(true);
        iUsers.save(user);
        return "{response: 'Usuario Habilitado'}";
    }

    @GetMapping(value = "/getUserById")
    public User getUserById(@PathParam("id") Integer id) {
        try {
            return iUsers.findOne(id);
        } catch (Exception e)
        {
            return null;
        }
    }

}

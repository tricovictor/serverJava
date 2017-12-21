package com.smartcity.db.model.resource;

import com.smartcity.db.model.Response;
import com.smartcity.db.model.User;
import com.smartcity.db.model.repository.interfaces.IUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    Response response = new Response();


    @GetMapping(value = "/all")
    public List<User> getAll() {
        return iUsers.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<Response> persist(@RequestBody User user) {
        try{
            iUsers.save(user);
            response.setResponse("OK");
            return new ResponseEntity<Response>(response , HttpStatus.OK);

        } catch (Exception e) {
            response.setResponse("");
            return new ResponseEntity<Response>(response , HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public ResponseEntity<Response> updateUser(@RequestBody User users) {
        User user = iUsers.findOne(users.getId());
        user.setEmail(users.getEmail());
        user.setName(users.getName());
        user.setPassword(users.getPassword());
        user.setLastname(users.getLastname());
        user.setPhone(users.getPhone());
        user.setState(true);
        user.setType(users.getType());
        iUsers.save(user);
        response.setResponse("Usuario Actualizado");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
    }

    @GetMapping(value = "/deleteUserById")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Response> deleteUserById(@PathParam("id") Integer id) {
        User user = iUsers.findOne(id);
        if(user.isState()){
            user.setState(false);
            iUsers.save(user);
            response.setResponse("Usuario Deshabilitado");
            return new ResponseEntity<Response>(response , HttpStatus.OK);
        }
        user.setState(true);
        iUsers.save(user);
        response.setResponse("Usuario Habilitado");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
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

    @RequestMapping(method = RequestMethod.POST, value = "/getLogin")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> check(@RequestBody User user) {
        boolean log = iUsers.existsByEmailAndPassword(user.getEmail(), user.getPassword());
        if(!log) {
            response.setResponse("Datos incorrectos");
            return new ResponseEntity<Response>(response , HttpStatus.NOT_FOUND);
        } else {
            response.setResponse("Login correcto");
            return new ResponseEntity<Response>(response,HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getLoginweb")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getLoginweb(@RequestBody User user) {
        boolean log = iUsers.existsByEmailAndPassword(user.getEmail(), user.getPassword());
        if(!log) {
            response.setResponse("Datos incorrectos");
            return new ResponseEntity<Response>(response , HttpStatus.OK);
        } else {
            return new ResponseEntity<User>(iUsers.findByEmail(user.getEmail()),HttpStatus.OK);
        }
    }
    @RequestMapping(method = RequestMethod.POST, value = "/getLoginApp")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getLoginApp(@RequestBody User user) {
        boolean log = iUsers.existsByEmailAndPassword(user.getEmail(), user.getPassword());
        if(!log) {
            response.setResponse("ERROR");
            return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
        } else {
            response.setResponse("OK");
            return new ResponseEntity<Response>(response,HttpStatus.OK);
        }
    }

}

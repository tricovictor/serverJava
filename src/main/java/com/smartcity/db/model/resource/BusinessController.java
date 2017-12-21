package com.smartcity.db.model.resource;

import com.smartcity.db.model.Business;
import com.smartcity.db.model.Response;
import com.smartcity.db.model.SubAmbitoBusiness;
import com.smartcity.db.model.repository.interfaces.IBusiness;
import com.smartcity.db.model.repository.interfaces.ISubAmbitosBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by victor on 10/12/17.
 */
@CrossOrigin(maxAge = 3600)
@RestController
@EnableWebMvc
@RequestMapping(value = "/rest/business")
public class BusinessController {

    @Autowired
    IBusiness iBusiness;

    @Autowired
    ISubAmbitosBusiness iSubAmbitosBusiness;

    Response response = new Response();


    @GetMapping(value = "/all")
    public List<Business> getAll() {
        return iBusiness.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<Response> persist(@RequestBody Business business) {
        try {
            iBusiness.save(business);
            response.setResponse("OK");
            return new ResponseEntity<Response>(response , HttpStatus.OK);
        } catch (Exception e) {
            response.setResponse("");
            return new ResponseEntity<Response>(response , HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public ResponseEntity<Response> updateBusiness(@RequestBody Business business) {
        Business busines = iBusiness.findOne(business.getId());
        busines.setName(business.getName());
        busines.setAddress(business.getAddress());
        busines.setDescription(business.getDescription());
        busines.setPhone(business.getPhone());
        iBusiness.save(busines);
        response.setResponse("Empresa Modificada");
        return new ResponseEntity<Response>(response , HttpStatus.OK);
    }

    @GetMapping(value = "/getBusinessById")
    @Produces(MediaType.APPLICATION_JSON)
    public Business getBusinessById(@PathParam("id") Integer id) {
        return iBusiness.findOne(id);
    }

    @GetMapping(value = "/updateSubAmbitos")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Response> updateBusinessSubAmbitos(@PathParam("id") Integer id,
                                                             @PathParam("subambitos") String subambitos) {

        List<SubAmbitoBusiness> subAmbitoBusinessList = iSubAmbitosBusiness.findByBusinessId(id);
        iSubAmbitosBusiness.delete(subAmbitoBusinessList);
        String arrayTotal[] = subambitos.split(",");
        for (int i = 0; i < arrayTotal.length; i++) {
            SubAmbitoBusiness subAmbitoBusiness = new SubAmbitoBusiness();
            subAmbitoBusiness.setSubAmbitoId(Integer.parseInt(arrayTotal[i]));
            subAmbitoBusiness.setBusinessId(id);
            iSubAmbitosBusiness.save(subAmbitoBusiness);
        }


        response.setResponse(subambitos);
        return new ResponseEntity<Response>(response , HttpStatus.OK);

    }

}

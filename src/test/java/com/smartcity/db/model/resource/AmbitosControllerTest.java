package com.smartcity.db.model.resource;

import com.smartcity.db.model.Ambito;
import com.smartcity.db.model.Response;
import com.smartcity.db.model.repository.interfaces.IAmbitos;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.*;



public class AmbitosControllerTest {
    private AmbitosController ambitoController;
    @Autowired
    public IAmbitos iAmbitos;
    public Ambito ambito;
    @Before
    public void setUp() throws Exception {
        this.ambitoController = new AmbitosController();
        this.ambito = new Ambito();
    }
    @Test
    public void getAll() throws Exception {
        try{
            List<Ambito> lstAmbitos = null;
            lstAmbitos = iAmbitos.findAll();

          //  assertEquals(expected, actual, 0.000);

        }catch(Exception ex)
        {
            throw new Exception("El test getAllAmbitos no paso: " + ex.toString());
        }
    }

    @Test
    public void persist() throws Exception {
        try{
            ResponseEntity<Response> respuesta ;
            /*
                iAmbitos.save(this.ambito);
                response.setResponse("Ambito Creado correctamente");
                return new ResponseEntity<Response>(response , HttpStatus.OK);
                assertEquals(expected, actual, 0.000);
             */

        }catch(Exception ex)
        {
            throw new Exception(ex.toString());
        }
    }

    @Test
    public void updateUser() throws Exception {
        try{
         /*
            Ambito ambito = iAmbitos.findOne(ambitos.getId());
            ambito.setName(ambitos.getName());
            iAmbitos.save(ambito);
            response.setResponse("Ambito actualizado");
            return new ResponseEntity<Response>(response , HttpStatus.OK);
         */
        }catch(Exception ex)
        {
            throw new Exception(ex.toString());
        }
    }

    @Test
    public void deleteAmbitoById() throws Exception {
        try{
            /*
            Ambito ambito = iAmbitos.findOne(id);
            if(ambito.isState()){
            ambito.setState(false);
            iAmbitos.save(ambito);
            response.setResponse("Ambito deshabilitado");
            return new ResponseEntity<Response>(response , HttpStatus.OK);
            }
            ambito.setState(true);
            iAmbitos.save(ambito);
            response.setResponse("Ambito habilitado");
            return new ResponseEntity<Response>(response , HttpStatus.OK);
                */
        }catch(Exception ex)
        {
            throw new Exception(ex.toString());
        }
    }

    @Test
    public void getAmbitoById() throws Exception {
        try{
            /*
                try {
                    return iAmbitos.findOne(id);
                } catch (Exception e)
                {
                    return null;
                }
            */
        }catch(Exception ex)
        {
            throw new Exception(ex.toString());
        }
    }

}
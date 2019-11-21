/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sample;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author exkapp
 */
@Path("generic")
public class GenericResource {
    Conection con = Conection.getInstance();
    String sql="", response="FAILED";
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of .sample.GenericResource
     * @return an instance of java..String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Empleado> getJson() throws SQLException  {
        //TODO return proper representation object
        sql = "SELECT * FROM empleadorest;";
        List<Empleado> lstPersona = con.executeThisQuery(sql);
        System.out.println("OTRO MÁS");
        System.out.println(lstPersona.size());
            
        return lstPersona;
    }
    
    @POST   
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String altaEmpleado(Empleado persona){
        sql = "INSERT INTO empleadorest (clave, nombre, direccion, telefono) VALUES ('" +persona.getClave()+"','" +persona.getNombre()+"','" +persona.getDireccion() + "','" +persona.getTelefono() +"');";
        if (con.execute(sql))
            response = "OK";
        return response;
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String borrarEmpleado(Empleado persona){
        sql = "DELETE FROM empleadorest WHERE (clave = '" + persona.getClave() +"');";
        if (con.execute(sql))
            response = "OK";
        return response;
    }
    
    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String putJson(Empleado per) {
        sql = "UPDATE empleadorest SET nombre = '" + per.getNombre() + "', direccion = '" + per.getDireccion() + "', telefono = '" + per.getTelefono() + "' WHERE (clave='" + per.getClave() +"');";
        if (con.execute(sql))
            response = "OK";
        return response;
    }
}

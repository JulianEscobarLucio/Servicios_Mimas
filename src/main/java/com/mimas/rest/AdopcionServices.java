package com.mimas.rest;

import java.util.Date;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.mimas.crud.AdopcionCrud;
import com.mimas.crud.CrudInterface;
import com.mimas.crud.FundacionCrud;
import com.mimas.crud.MascotaCrud;
import com.mimas.model.Adopcion;
import com.mimas.model.Mascota;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationPath("rest")
@Path("/adopcionservices")
public class AdopcionServices {
	
    private Gson gson ;
    private CrudInterface crud;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStartingPage()
    {
        String output = "<h1>Hello World!<h1>" +
                "<p>RESTful Service is running ... <br>Ping @ " + new Date().toString() + "</p><br>";
        return Response.status(200).entity(output).build();
    }
    
    
    
    @POST
    @Path("/adopcion")   
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarSolicitud(Adopcion adopcion) throws JSONException  {
        JSONObject jo = new JSONObject(); 
        JSONArray ja = new JSONArray();
        try {               
        	crud = new MascotaCrud();
        	Mascota mascota = new Mascota();
        	mascota.setId(adopcion.getIdMascota());
        	mascota = (Mascota) crud.consultar(mascota);
        	if (mascota.getId() == null) {
        	  jo.put("codRespuesta", "501");
        	  ja.put(jo);
        	  return Response.status(501).entity(ja).build();		
        	}        	
            crud = new AdopcionCrud();
            int respueata = crud.insertar(adopcion);
            if(respueata==1){  
               jo.put("codRespuesta", "200");
            }else{
               jo.put("codRespuesta", "200");
            };          
            ja.put(jo);
            return Response.status(200).entity(ja).build();
        } catch (Exception e) {
            jo.put("codRespuesta", "500");
            jo.put("respuesta", "Solicitud no registrada¡, error interno");
            e.printStackTrace();
             return Response.serverError()
                    .entity(jo).build();
        }     

   }

}
package com.mimas.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.mimas.crud.CrudInterface;
import com.mimas.crud.RolCrud;
import com.mimas.crud.UsuarioCrud;
import com.mimas.model.Rol;
import com.mimas.model.Usuario;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationPath("rest")
@Path("/rolServices")
public class rolServices {

    private Gson gson ;
    private CrudInterface crud;   
    
    
    @POST
    @Path("/registrarRol") 
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response regisrtrarUsuario(Rol rol) throws JSONException  {
        JSONObject jo = new JSONObject();    
        JSONArray ja = new JSONArray();
        try {    
                        
            crud = new RolCrud();
            int respueata = crud.insertar(rol);
            if(respueata==1){  
               jo.put("codRespuesta", "200");
               jo.put("respuesta", "Rol registrado");
            }else{
               jo.put("codRespuesta", "201");
               jo.put("respuesta", "Rol no registrado");   
            };          
            
           
            ja.put(jo);
            return Response.status(200).entity(ja).build();
        } catch (Exception e) {
            jo.put("codRespuesta", "500");
            jo.put("respuesta", "Rol no registrado, error interno");
            e.printStackTrace();
            return Response.serverError()
                    .entity(jo).build();
        }
    } 
     
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestService;

import Controllers.SesionController;
import RelationEntities.Persona_Servicio;
import RelationEntities.Persona_Usuario;
import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import org.eclipse.persistence.jpa.jpql.parser.Expression;

/**
 * REST Web Service
 *
 * @author torellana
 */
@Path("SesionResource")
public class SesionResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SesionResourceResource
     */
    public SesionResource() {
    }

    /**
     * Retrieves representation of an instance of RestService.SesionResourceResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of SesionResourceResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @POST
    @Path("iniciarSesion")
    @Consumes("application/json")
    public String iniciarSesion(String data){
        Gson gson = new Gson();
        String resultado = "";
        System.out.println("iniciarSession datos recibidos:"+data);
        try{
            Persona_Usuario usuario = gson.fromJson(data, Persona_Usuario.class);
            SesionController sesionController = new SesionController();
            resultado = gson.toJson(sesionController.iniciarSesion(usuario.getNombreUsuario(), usuario.getContrasenhaUsuario()));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        System.out.println("asdas SESSION (json)==>>"+resultado);
        return resultado;
    }
}

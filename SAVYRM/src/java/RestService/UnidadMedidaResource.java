/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestService;

import Controllers.UnidadMedidaController;
import RelationEntities.UnidadMedida;
import com.google.gson.Gson;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author torellana
 */
@Path("UnidadMedida")
public class UnidadMedidaResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UnidadMedidaResource
     */
    public UnidadMedidaResource() {
    }

    /**
     * Retrieves representation of an instance of RestService.UnidadMedidaResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of UnidadMedidaResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @GET
    @Path("getUnidadMedida")
    @Produces("application/json")
    public String getUnidadMedida(){
        String respuesta = "No Disponible temporalmente. Intenta más tarde.";
        Gson gson = new Gson();
        UnidadMedidaController unidadMedidaController = new UnidadMedidaController();
        
        try{
            ArrayList<UnidadMedida> listaUnidadMedida = unidadMedidaController.getListaUnidadMedida();
            respuesta = gson.toJson(listaUnidadMedida);
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Error no esperado en el metodo \"getUnidadMedida\": " + ex);
        }
        return respuesta;
    }
}

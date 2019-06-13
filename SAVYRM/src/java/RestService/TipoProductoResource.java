/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestService;

import Controllers.TipoProductoController;
import Entities.TipoProducto;
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
@Path("TipoProducto")
public class TipoProductoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TipoProductoResource
     */
    public TipoProductoResource() {
    }

    /**
     * Retrieves representation of an instance of RestService.TipoProductoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of TipoProductoResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
        @GET
    @Path("getTipoProducto")
    @Produces("application/json")
    public String getTipoProducto(){
        String respuesta = "No Disponible temporalmente. Intenta más tarde.";
        Gson gson = new Gson();
        TipoProductoController tipoProductoController = new TipoProductoController();
        
        try{
            System.out.println("getTipoProdcuto");
            ArrayList<TipoProducto> listaUnidadMedida = tipoProductoController.getListaTipoProducto();
            respuesta = gson.toJson(listaUnidadMedida);
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Error no esperado en el metodo \"getTipoProducto\": " + ex);
        }
        return respuesta;
    }
}

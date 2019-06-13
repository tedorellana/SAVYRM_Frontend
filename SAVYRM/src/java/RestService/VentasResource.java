/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestService;

import Controllers.VentasController;
import Entities.Persona;
import RelationEntities.Persona_Servicio;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author torellana
 */
@Path("Ventas")
public class VentasResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of VentasResource
     */
    public VentasResource() {
    }
    
    /**
     * Retrieves representation of an instance of RestService.MainResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of MainResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
    
    /**
     * Retrieves representation of an instance of RestService.VentasResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("getVentas")
    @Produces("application/json")
    public String getVentas(){
        String respuesta = "No Disponible temporalmente. Intenta más tarde.";
        Gson gson = new Gson();
        VentasController ventasController = new VentasController();
        System.out.println("**********INICIA GET VENTAS ************");
        try{
            ArrayList<Persona_Servicio> lista_Persona_Servicio = ventasController.getVentas();
            respuesta = gson.toJson(lista_Persona_Servicio);
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Error no esperado en el metodo \"getVentas\": " + ex);
        }
        return respuesta;
    }
    
    @GET
    @Path("getVentasString")
    @Consumes("application/json")
    public String getVentasString(){
        String respuesta = "";
        Gson gson = new Gson();
        VentasController ventasController = new VentasController();
        System.out.println("**********INICIA GET VENTAS ************");
        try{
            ArrayList<Persona_Servicio> lista_Persona_Servicio = ventasController.getVentas();
            System.out.println("TAMAÑO DE LISTA -->"+ lista_Persona_Servicio.size());
            
            respuesta = lista_Persona_Servicio.get(0).toString();
            
            System.out.println("RESPONSE SETTED");
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.out.println("...............Exception here ____:::::" + ex);
            
        }
        return respuesta;
    }
    
    
//    
//    @POST
//    @Path("iniciarSession")
//    @Consumes("application/json")
//    public String iniciarSession(String data){
//        Gson gson = new Gson();
//        System.out.println("------>"+data);
//        UsuarioPersonaDTO usuarioPersonaDTO = gson.fromJson(data, UsuarioPersonaDTO.class);
//        UsuarioPersonaDTO respuesta = null;
//        try{
//            respuesta = usuarioHelper.iniciarSession(usuarioPersonaDTO);
//            System.out.println("------>"+gson.toJson(respuesta));
//        }
//        catch(Exception ex){
//            ex.printStackTrace();
//        }
//        System.out.println("INCIAR SESSION ==>>"+respuesta);
//        System.out.println("INCIAR SESSION (json)==>>"+gson.toJson(respuesta));
//        return gson.toJson(respuesta);
//    }    
}

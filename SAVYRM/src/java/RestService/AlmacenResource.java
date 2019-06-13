/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestService;

import Controllers.AlmacenController;
import RelationEntities.Almacen;
import RelationEntities.Seccion;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import com.google.gson.Gson;
import java.util.ArrayList;
import javax.ws.rs.POST;
import DTO.AlmacenesIDList;
import DTO.SeccionesIDList;
import RelationEntities.Almacen_Seccion_ProductoSeccion_Producto_Lote;


/**
 * REST Web Service
 *
 * @author torellana
 */
@Path("AlmacenResource")
public class AlmacenResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AlmacenResourceResource
     */
    public AlmacenResource() {
    }

    /**
     * Retrieves representation of an instance of RestService.AlmacenResourceResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of AlmacenResourceResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
    
    @GET
    @Path("getAllAlmacenes")
    @Produces("application/json")
    public String getAllAlmacenes(){
        String respuesta = "No Disponible temporalmente. Intenta más tarde.";
        Gson gson = new Gson();
        AlmacenController almacenController = new AlmacenController();
        System.out.println("**********INICIA GET VENTAS ************");
        try{
            ArrayList<Almacen> lista_Almacenes = almacenController.getAlmacenes();
            respuesta = gson.toJson(lista_Almacenes);
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Error no esperado en el metodo \"getVentas\": " + ex);
        }
        return respuesta;
    }
    
    @POST
    @Path("getSeccionesPorListaIDAlmacen")
    @Consumes("application/json")
    public String getSeccionesPorListaIDAlmacen(String data){
        AlmacenController almacenController = new AlmacenController();
        
        String respuesta = "No Disponible temporalmente. Intenta más tarde.";
        Gson gson = new Gson();
        
        try{
            System.out.println("########>" + data);
            System.out.println("########>" + data.getClass().getName());
            AlmacenesIDList listaAlmacenesID = gson.fromJson(data, AlmacenesIDList.class);
            ArrayList<Seccion> listaSecciones = almacenController.getSeccionesPorAlmacen(listaAlmacenesID);
            respuesta = gson.toJson(listaSecciones);
            System.out.println("------->" + respuesta);
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Error no esperado en el metodo \"getSeccionesPorAlmacenID\": " + ex);
        }
        return respuesta;
    }
    
    @POST
    @Path("getSeccionesPorAlmacenID")
    @Consumes("application/json")
    public String getSeccionesPorAlmacenID(String data){
        AlmacenController almacenController = new AlmacenController();
        
        String respuesta = "No Disponible temporalmente. Intenta más tarde.";
        Gson gson = new Gson();
        
        try{
            Almacen almacenSeleccinoado = gson.fromJson(data, Almacen.class);
            ArrayList<Seccion> listaSecciones = almacenController.getSeccionesPorAlmacen(almacenSeleccinoado);
            respuesta = gson.toJson(listaSecciones);
            System.out.println("------->" + respuesta);
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Error no esperado en el metodo \"getSeccionesPorAlmacenID\": " + ex);
        }
        return respuesta;
    }
    
    @POST
    @Path("getInventarioPorIDSeccion")
    @Consumes("application/json")
    public String getInventarioPorIDSeccion(String data){
        AlmacenController almacenController = new AlmacenController();
        
        String respuesta = "No Disponible temporalmente. Intenta más tarde.";
        Gson gson = new Gson();
        
        try{       
            SeccionesIDList listaIDSecciones = gson.fromJson(data, SeccionesIDList.class);
            ArrayList<Almacen_Seccion_ProductoSeccion_Producto_Lote> inventarioPorSecciones = almacenController.getInventarioPorIDSeccion(listaIDSecciones);
            respuesta = gson.toJson(inventarioPorSecciones);
            System.out.println("------->" + respuesta);
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Error no esperado en el metodo \"getInventarioPorIDSeccion\": " + ex);
        }
        return respuesta;
    }
    
    @POST
    @Path("agregarNuevoAlmacen")
    @Consumes("application/json")
    public String agregarNuevoAlmacen(String data){
        AlmacenController almacenController = new AlmacenController();
        
        String respuesta = "No Disponible temporalmente. Intenta más tarde.";
        Gson gson = new Gson();
        
        try{
            System.out.println("########>" + data);
            System.out.println("########>" + data.getClass().getName());
                   
            Almacen nuevoAlmacen = gson.fromJson(data, Almacen.class);
            System.out.println("########>" + nuevoAlmacen.getNombreAlmacen() + " " + nuevoAlmacen.getDireccion() + " " + nuevoAlmacen.getCapacidad());
            almacenController.agregarAlmacen(nuevoAlmacen);
            respuesta = "Se agrego el Almacen Correctamente";
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Error no esperado en el metodo \"getInventarioPorIDSeccion\": " + ex);
        }
        return respuesta;
    }
    
    @POST
    @Path("agregarNuevaSeccion")
    @Consumes("application/json")
    public String agregarNuevaSeccion(String data){
        AlmacenController almacenController = new AlmacenController();
        
        String respuesta = "No Disponible temporalmente. Intenta más tarde.";
        Gson gson = new Gson();
        
        try{
            System.out.println("########>" + data);
            System.out.println("########>" + data.getClass().getName());
                   
            Seccion nuevaSeccion = gson.fromJson(data, Seccion.class);
            System.out.println("########>" + nuevaSeccion.getCodigoSeccion()+ " " + nuevaSeccion.getDetalle()+ " " + nuevaSeccion.getCapacidadSeccion());
            almacenController.agregarSeccion(nuevaSeccion);
            respuesta = "Se agrego la Sección Correctamente";
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Error no esperado en el metodo \"agregarNuevaSeccion\": " + ex);
        }
        return respuesta;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestService;

import Controllers.ProductoController;
import DTO.Producto_Formula_ProductoFormulaList;
import DTO.Producto_Preparacion_PreparacionProductoList;
import RelationEntities.Producto;
import RelationEntities.Producto_Formula_ProductoFormula;
import RelationEntities.Producto_Preparacion_PreparacionProducto;
import RelationEntities.Producto_TipoProducto;
import com.google.gson.Gson;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author torellana
 */
@Path("ProductosResource")
public class ProductosResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ProductosResource
     */
    public ProductosResource() {
    }

    /**
     * Retrieves representation of an instance of RestService.ProductosResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of ProductosResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @GET
    @Path("getAllProductos")
    @Produces("application/json")
    public String getAllProductos(){
        ProductoController productoController = new ProductoController();
        
        String respuesta = "No Disponible temporalmente. Intenta más tarde.";
        Gson gson = new Gson();
        
        try{
                   
            ArrayList<Producto_TipoProducto> listaProductos = productoController.getListaProducto_TipoProducto();
            respuesta = gson.toJson(listaProductos);
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Error no esperado en el metodo \"agregarNuevaSeccion\": " + ex);
        }
        return respuesta;
    }
    
    @POST
    @Path("agregarNuevoProducto")
    @Consumes("application/json")
    public String agregarNuevoProducto(String data){
        ProductoController productoController = new ProductoController();
        String respuesta = "No Disponible temporalmente. Intenta más tarde.";
        Gson gson = new Gson();
        Producto nuevoProducto = gson.fromJson(data, Producto.class);
        System.out.println("dataa___"+nuevoProducto.getNombreProducto());
        try{
            productoController.registrarProducto(nuevoProducto.getNombreProducto(), nuevoProducto.getFk_idTIpoProducto(), nuevoProducto.getFk_idUnidadMedida());
            respuesta = "¡Se registro el producto exitósamente!";
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Error no esperado en el metodo \"agregarNuevaSeccion\": " + ex);
        }
        return respuesta;
    }
    
    @POST
    @Path("getProductoFormula")
    @Consumes("application/json")
    public String getProductoFormula(String data){
        System.out.println("getProductoFormula Resource inicia " );
        String respuesta = "Esta opción no esta disponible temporalmente.\nPor favor Intenta más tarde.";        
        try{
            ProductoController productoController = new ProductoController();
            Gson gson = new Gson();
            Producto productoSeleccionado = gson.fromJson(data, Producto.class);
            ArrayList<Producto_Formula_ProductoFormula> productoFormulaList = productoController.getListaFormulaPorProductoID(productoSeleccionado.getIdProducto());
            System.out.println("Tamañao de lista de formula: " +  productoFormulaList.size());
            respuesta = gson.toJson(productoFormulaList);
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Error no esperado en el metodo \"getProductoFormula\": " + ex);
        }
        System.out.println("getProductoFormula Resource finaliza");
        return respuesta;
    }
    
    @POST
    @Path("getProductoPreparacion")
    @Consumes("application/json")
    public String getProductoPreparacion(String data){
        System.out.println("getProductoPreparacion Resource inicia");
        String respuesta = "Esta opción no esta disponible temporalmente.\nPor favor Intenta más tarde.";        
        try{
            ProductoController productoController = new ProductoController();
            Gson gson = new Gson();
            Producto productoSeleccionado = gson.fromJson(data, Producto.class);
            ArrayList<Producto_Preparacion_PreparacionProducto> productoPreparacionList = productoController.getListaPreparacionPorProductoID(productoSeleccionado.getIdProducto());
            System.out.println("Tamañao de lista de Preparación: " +  productoPreparacionList.size());
            respuesta = gson.toJson(productoPreparacionList);
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Error no esperado en el metodo \"getProductoPreparacion\": " + ex);
        }
        System.out.println("getProductoPreparacion Resource finaliza");
        return respuesta;
    }
    
    @POST
    @Path("getListaProducto_TipoProductoNoFinales")
    @Consumes("application/json")
    public String getListaProducto_TipoProductoNoFinales(String data){
        System.out.println("getListaProducto_TipoProductoNoFinales Resource inicia");
        String respuesta = "Esta opción no esta disponible temporalmente.\nPor favor Intenta más tarde.";        
        try{
            ProductoController productoController = new ProductoController();
            Gson gson = new Gson();
            ArrayList<Producto_TipoProducto> productosNoFinalesLista = productoController.getListaProducto_TipoProductoNoFinales();
            System.out.println("Tamañao de lista de productosNoFinalesLista: " +  productosNoFinalesLista.size());
            respuesta = gson.toJson(productosNoFinalesLista);
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Error no esperado en el metodo \"getListaProducto_TipoProductoNoFinales\": " + ex);
        }
        System.out.println("getListaProducto_TipoProductoNoFinales Resource finaliza");
        return respuesta;
    }
}

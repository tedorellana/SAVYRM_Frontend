package Controllers;

import DBAccess.PrecioDBA;
import DBAccess.ProductoDBA;
import Entities.Producto;
import Entities.UnidadMedida;
import RelationEntities.Persona_TipoPersona;
import RelationEntities.Producto_Formula_ProductoFormula;
import RelationEntities.Producto_Formula_ProductoFormula_Precio;
import RelationEntities.Producto_Precio;
import RelationEntities.Producto_Preparacion_PreparacionProducto;
import RelationEntities.Producto_TipoProducto;
import RelationEntities.Servicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author torellana
 */
public class ProductoController {
    
    
    public ArrayList<Producto_TipoProducto> getListaProducto_TipoProducto(){
        ArrayList <Producto_TipoProducto> listaProducto;
        ProductoDBA productoDBA = new ProductoDBA();
        
        listaProducto = productoDBA.getListaProducto_TipoProducto();
        return listaProducto;
    }
    
    public ArrayList<Producto_TipoProducto> getListaProducto_TipoProductoConPrecio(){
        ArrayList <Producto_TipoProducto> listaProducto;
        ProductoDBA productoDBA = new ProductoDBA();
        
        listaProducto = productoDBA.getListaProducto_TipoProductoConPrecio();
        return listaProducto;
    }
    
    public ArrayList<Producto_TipoProducto> getListaProducto_TipoProductoNoFinales(){
        ArrayList <Producto_TipoProducto> listaProducto;
        ProductoDBA productoDBA = new ProductoDBA();
        
        listaProducto = productoDBA.getListaProducto_TipoProductoNoFinales();
        return listaProducto;
    }

    public String registrarProducto(String nombreProducto, int idTipoProducto, int idUnidadMedida) {
        String resultado = "error";
        ProductoDBA productoDBA = new ProductoDBA();
        
        resultado = productoDBA.registrarProducto(nombreProducto, idTipoProducto, idUnidadMedida);
        resultado = (resultado.equals("exito")) ? "Producto Agregado Correctamente":"Error al Agregar el producto";
        
        return resultado;
    }
    
    public String actualizarProducto(String nombreProducto, Integer idProducto, int idTipoProducto, int idUnidadMedida) {
        String resultado = "error";
        ProductoDBA productoDBA = new ProductoDBA();
        
        resultado = productoDBA.actualizarProducto(nombreProducto,idProducto, idTipoProducto, idUnidadMedida);
        resultado = (resultado.equals("exito")) ? "Producto Actualizado Correctamente":"Error al Agregar el producto";
        
        return resultado;
    }
    
    public String eliminarProducto(Producto producto) {
        String resultado = "error";
        ProductoDBA productoDBA = new ProductoDBA();
        
        String exito = "Eliminado exitosamente";
        String error = "Ocurrio un error eliminando el producto";
        
        resultado = (productoDBA.eliminarProducto(producto))? exito:error;
        resultado = (resultado.equals("exito")) ? "Producto Agregado Correctamente":"Error al Agregar el producto";
        
        return resultado;
    }

    public Producto_TipoProducto getProductoPorID(Integer idProducto) {
        Producto_TipoProducto listaProducto = null;
        ProductoDBA productoDBA = new ProductoDBA();
        
        listaProducto = productoDBA.getProducto_TipoProductoPorIDProducto(idProducto);
        return listaProducto;
    }
    
    public Producto_TipoProducto getProductoPorNombre(String nombreProducto) {
        Producto_TipoProducto producto_TipoProducto = null;
        ProductoDBA productoDBA = new ProductoDBA();
        
        producto_TipoProducto = productoDBA.getProducto_TipoProductoPorNombreProducto(nombreProducto);
        return producto_TipoProducto;
    }

    public Boolean registrarInstruccionesElaboracion(ArrayList<Producto_Formula_ProductoFormula> listaProducto_Formula_ProductoFormula, ArrayList<Producto_Preparacion_PreparacionProducto> listaProducto_Preparacion_PreparacionProducto) {
        Boolean resultado = false;
        ProductoDBA productoDBA = new ProductoDBA();
        
        resultado = productoDBA.registrarInstruccionesElaboracion(listaProducto_Formula_ProductoFormula, listaProducto_Preparacion_PreparacionProducto);
        return resultado;
    }

    public ArrayList<Producto_Formula_ProductoFormula> getListaFormulaPorProductoID(Integer idProducto) {
        ArrayList<Producto_Formula_ProductoFormula> producto_Formula_ProductoFormula = new ArrayList<>();
        ProductoDBA productoDBA = new ProductoDBA();
        
        producto_Formula_ProductoFormula = productoDBA.getListaFormulaPorProductoID(idProducto);
        
        return producto_Formula_ProductoFormula;
    }

    public ArrayList<Producto_Formula_ProductoFormula_Precio> getListaFormulaConPrecioPorProductoID(Integer idProducto) {
        ArrayList<Producto_Formula_ProductoFormula_Precio> producto_Formula_ProductoFormula_Precio = new ArrayList<>();
        ProductoDBA productoDBA = new ProductoDBA();
        
        producto_Formula_ProductoFormula_Precio = productoDBA.getListaFormulaConPrecionPorProductoID(idProducto);
        
        return producto_Formula_ProductoFormula_Precio;
    }
    
    public ArrayList<Producto_Preparacion_PreparacionProducto> getListaPreparacionPorProductoID(Integer idProducto) {
        ArrayList<Producto_Preparacion_PreparacionProducto> producto_Preparacion_PreparacionProducto = new ArrayList<>();
        ProductoDBA productoDBA = new ProductoDBA();
        
        producto_Preparacion_PreparacionProducto = productoDBA.getListaPreparacionPorProductoID(idProducto);
        
        return producto_Preparacion_PreparacionProducto;
    }

    public ArrayList<Producto_TipoProducto> getListaProducto_TipoProductoConElaboracion() {
        ArrayList <Producto_TipoProducto> listaProducto;
        ProductoDBA productoDBA = new ProductoDBA();
        
        listaProducto = productoDBA.getListaProducto_TipoProductoConElaboracion();
        return listaProducto;
    }

    public String getNombrePorId(Integer fk_idProductoInsumo) {
        String nombre = "";
        ProductoDBA productoDBA = new ProductoDBA();
        
        nombre = productoDBA.getNombreProductoPorId(fk_idProductoInsumo);
        return nombre;
    }

    public Boolean actualizarInstruccionesElaboracion(ArrayList<Producto_Formula_ProductoFormula> listaProducto_Formula_ProductoFormula, ArrayList<Producto_Preparacion_PreparacionProducto> listaProducto_Preparacion_PreparacionProducto) {
        Boolean resultado = false;
        ProductoDBA productoDBA = new ProductoDBA();
        
        resultado = productoDBA.actualizarInstruccionesElaboracion(listaProducto_Formula_ProductoFormula, listaProducto_Preparacion_PreparacionProducto);
        return resultado;
    }

    public Double calcularCantidadInsumoParaFormula(Double cantidadDeseada, Double porcentaje) {
        Double resultado = 0.0;
        System.out.println("a:"+cantidadDeseada+"*"+porcentaje+ "%" );
        resultado = cantidadDeseada * (porcentaje/100);
        System.out.println("---->res"+resultado);
        return resultado;
    }
    
    public ArrayList<Producto_Precio> getListaPreciosPorProducto(Integer idProducto){
        //PrecioDBA precioDBA = new PrecioDBA();
        ArrayList <Producto_Precio> listaPrecios = null;
        listaPrecios = PrecioDBA.getPreciosPorProducto(idProducto);
        
        return listaPrecios;
    }

    public void registrarPrecio(Producto_Precio producto_precio) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
        producto_precio.setFechaInicioPrecio(timestamp);
        
        PrecioDBA precioDBA = new PrecioDBA();
        precioDBA.RegistrarPrecio(producto_precio);
        
    }

    public Producto_Precio getProductoPrecioVigente(Integer idProductoConsultado) {
        Producto_Precio producto_Precio = null;
        producto_Precio = PrecioDBA.getPrecioVigentePorProductoID(idProductoConsultado);
        return producto_Precio;
    }

//    public Servicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida getDataCompletaDelProductoParaVender(Integer idProductoConsultado) {
//        System.out.println("getDataCompletaDelProductoParaVender");
//        Servicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida data = new Servicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida();
//        data = ProductoDBA.getDataCompletaDelProductoParaVender(idProductoConsultado);
//        return data;
//    }

    public void registrarServicio(Persona_TipoPersona cliente, Integer personalAtencionID, Timestamp fechaInicioVenta, ArrayList<Servicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida> listaServicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida) {
         ProductoDBA.registrarServicio(cliente, personalAtencionID, fechaInicioVenta, listaServicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida);
    }

    public boolean stockDisponible(Integer idProducto,Double CantidadProductoRequerida){
        boolean resultado = false;
        resultado = ProductoDBA.stockDisponible(idProducto, CantidadProductoRequerida);
        return resultado;
    }

    public ArrayList<Servicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida> getListaDeProductosPorIDServicio(Integer idServicio) {
        ArrayList<Servicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida> lista = new ArrayList();
        lista = ProductoDBA.getListaDeProductosPorIDServicio(idServicio);
        return lista;
    }
        
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DBAccess.AlmacenDBA;
import DTO.AlmacenesIDList;
import DTO.SeccionesIDList;
import RelationEntities.Almacen;
import RelationEntities.Seccion;
import RelationEntities.Almacen_Seccion;
import RelationEntities.Almacen_Seccion_ProductoSeccion_Producto_Lote;
import RelationEntities.Producto_TipoProducto;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author torellana
 */
public class AlmacenController {
    
    public ArrayList<Almacen> getAlmacenes(){
        ArrayList<Almacen> listaAlmacenes = new ArrayList<>();
        
        listaAlmacenes = AlmacenDBA.getAlmacenes();
        
        return listaAlmacenes;
    }
    
    public ArrayList<Seccion> getSeccionesPorAlmacen(AlmacenesIDList idAlmacenesSelecionados){
        ArrayList<Seccion> listaSeccionesPorAlmacen = null;
        if(idAlmacenesSelecionados.getIdAlmacenes().isEmpty()) System.out.println("La lista de Alamacenes esta vacia;");
        else listaSeccionesPorAlmacen = AlmacenDBA.getSeccionesPorListaIDAlmacen(idAlmacenesSelecionados.getIdAlmacenes());        
        return listaSeccionesPorAlmacen;
    }
    
    public ArrayList<Seccion> getSeccionesPorAlmacen(Almacen almacenSeleccionado){
        ArrayList<Seccion> listaSeccionesPorAlmacen = null;
        System.out.println("ALMACEN sELECCIONADO:" + almacenSeleccionado.getIdAlmacen());
        try{
            listaSeccionesPorAlmacen = AlmacenDBA.getSeccionesPorIDAlmacen(almacenSeleccionado.getIdAlmacen());        
        }
        catch(Exception e){
            System.out.println("Ocurrio un error durante la obtención de Secciones por Almacen");
        }
        
        return listaSeccionesPorAlmacen;
    }

    public void agregarAlmacenSeccion(ArrayList<Almacen_Seccion> listaAlmacenSeccion) {
        System.out.println("Agregando Almacen");
        AlmacenDBA.agregarAlmacenSeccion(listaAlmacenSeccion);
    }
    
    public void agregarAlmacen(Almacen almacen) {
        System.out.println("Agregando Almacen");
        AlmacenDBA.agregarAlmacen(almacen);
    }

    public ArrayList<Almacen_Seccion_ProductoSeccion_Producto_Lote> getInventarioPorIDSeccion(SeccionesIDList listaIDSeccionesSeleccionados) {
        ArrayList<Almacen_Seccion_ProductoSeccion_Producto_Lote> inventario = null;
        if(listaIDSeccionesSeleccionados.getIdSecciones().isEmpty()) System.err.println("La lista de Secciones esta vacía");
        else inventario = AlmacenDBA.getInventarioPorIDSeccionSeleccionadas(listaIDSeccionesSeleccionados.getIdSecciones());
        return inventario;
    }
    //Eliminar esto
    public ArrayList<Almacen_Seccion_ProductoSeccion_Producto_Lote> getDatosPorSecion(Integer idAlmacen) {
        System.out.println("Seleccionando datos de TODAS la secciones");
        return AlmacenDBA.getTodosDatosSeccionPorAlmacen(idAlmacen);
    }
    //FIN Eliminar esto
    public void registarProductoEnSeccion(String nombreProductoSeleccionado, String nombreLoteSeleccionado, String cantidadProducto, Integer idSeccion) {
        System.out.println("Registrando productos en la seccion");
        ProductoController productoController = new ProductoController();
        LoteController loteCtr = new LoteController();
        Producto_TipoProducto producto = productoController.getProductoPorNombre(nombreProductoSeleccionado);
        AlmacenDBA.registarProductoEnSeccion(producto.getIdProducto(),loteCtr.getLotePorCodigo(nombreLoteSeleccionado).getIdLote(),Double.parseDouble(cantidadProducto),idSeccion);
    }
    
    public void agregarSeccion(Seccion seccion) {
        System.out.println("Agregando Almacen");
        AlmacenDBA.agregarSeccion(seccion);
    }

}

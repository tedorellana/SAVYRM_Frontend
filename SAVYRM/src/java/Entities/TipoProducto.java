package Entities;
// Generated 01-may-2018 23:13:47 by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * TipoProducto generated by hbm2java
 */
public class TipoProducto  implements java.io.Serializable {


     private int idTipoProducto;
     private String nombreTipoProducto;
     private Set<Producto> productos = new HashSet<Producto>(0);

    public TipoProducto() {
    }

	
    public TipoProducto(int idTipoProducto, String nombreTipoProducto) {
        this.idTipoProducto = idTipoProducto;
        this.nombreTipoProducto = nombreTipoProducto;
    }
    public TipoProducto(int idTipoProducto, String nombreTipoProducto, Set<Producto> productos) {
       this.idTipoProducto = idTipoProducto;
       this.nombreTipoProducto = nombreTipoProducto;
       this.productos = productos;
    }
   
    public int getIdTipoProducto() {
        return this.idTipoProducto;
    }
    
    public void setIdTipoProducto(int idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }
    public String getNombreTipoProducto() {
        return this.nombreTipoProducto;
    }
    
    public void setNombreTipoProducto(String nombreTipoProducto) {
        this.nombreTipoProducto = nombreTipoProducto;
    }
    public Set<Producto> getProductos() {
        return this.productos;
    }
    
    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }




}



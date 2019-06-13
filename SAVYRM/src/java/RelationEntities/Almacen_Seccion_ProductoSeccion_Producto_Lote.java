/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RelationEntities;

import java.sql.Timestamp;

/**
 *
 * @author torellana
 */
public class Almacen_Seccion_ProductoSeccion_Producto_Lote {
    //Almacen
    private Integer idAlmacen;
    private String nombreAlmacen;
    private String direccion;
    private Double capacidad;
    
    //Seccion
    private Integer idSeccion;
    private String codigoSeccion;
    private Double capacidadSeccion;
    private String detalle;
    private Integer fk_idAlmacen;
    
    //ProductoSeccion
    private Integer idProductoSeccion;
    private Double cantidadProductoSeccion;
    private Timestamp fechaIngreso;
    private Integer fk_idLote;
    private Integer fk_idProducto;
    private Integer fk_idSeccion;
    
    //Lote
    private Integer idLote;
    private String codigoLote;
    private Double costoLote;
    private Double cantidaUnidadesLote;
    private Timestamp fechaCaducacionLote;

    //Producto
    private Integer idProducto;
    private String nombreProducto;
    private String codigoProducto;
    private Boolean estadoProducto;
    private Integer fk_idTIpoProducto;
    private Integer fk_idUnidadMedida;

    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getNombreAlmacen() {
        return nombreAlmacen;
    }

    public void setNombreAlmacen(String nombreAlmacen) {
        this.nombreAlmacen = nombreAlmacen;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Double capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Integer idSeccion) {
        this.idSeccion = idSeccion;
    }

    public String getCodigoSeccion() {
        return codigoSeccion;
    }

    public void setCodigoSeccion(String codigoSeccion) {
        this.codigoSeccion = codigoSeccion;
    }

    public Double getCapacidadSeccion() {
        return capacidadSeccion;
    }

    public void setCapacidadSeccion(Double capacidadSeccion) {
        this.capacidadSeccion = capacidadSeccion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Integer getFk_idAlmacen() {
        return fk_idAlmacen;
    }

    public void setFk_idAlmacen(Integer fk_idAlmacen) {
        this.fk_idAlmacen = fk_idAlmacen;
    }

    public Integer getIdProductoSeccion() {
        return idProductoSeccion;
    }

    public void setIdProductoSeccion(Integer idProductoSeccion) {
        this.idProductoSeccion = idProductoSeccion;
    }

    public Double getCantidadProductoSeccion() {
        return cantidadProductoSeccion;
    }

    public void setCantidadProductoSeccion(Double cantidadProductoSeccion) {
        this.cantidadProductoSeccion = cantidadProductoSeccion;
    }

    public Timestamp getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Timestamp fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getFk_idLote() {
        return fk_idLote;
    }

    public void setFk_idLote(Integer fk_idLote) {
        this.fk_idLote = fk_idLote;
    }

    public Integer getFk_idProducto() {
        return fk_idProducto;
    }

    public void setFk_idProducto(Integer fk_idProducto) {
        this.fk_idProducto = fk_idProducto;
    }

    public Integer getFk_idSeccion() {
        return fk_idSeccion;
    }

    public void setFk_idSeccion(Integer fk_idSeccion) {
        this.fk_idSeccion = fk_idSeccion;
    }

    public Integer getIdLote() {
        return idLote;
    }

    public void setIdLote(Integer idLote) {
        this.idLote = idLote;
    }

    public String getCodigoLote() {
        return codigoLote;
    }

    public void setCodigoLote(String codigoLote) {
        this.codigoLote = codigoLote;
    }

    public Double getCostoLote() {
        return costoLote;
    }

    public void setCostoLote(Double costoLote) {
        this.costoLote = costoLote;
    }

    public Double getCantidaUnidadesLote() {
        return cantidaUnidadesLote;
    }

    public void setCantidaUnidadesLote(Double cantidaUnidadesLote) {
        this.cantidaUnidadesLote = cantidaUnidadesLote;
    }

    public Timestamp getFechaCaducacionLote() {
        return fechaCaducacionLote;
    }

    public void setFechaCaducacionLote(Timestamp fechaCaducacionLote) {
        this.fechaCaducacionLote = fechaCaducacionLote;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public Boolean getEstadoProducto() {
        return estadoProducto;
    }

    public void setEstadoProducto(Boolean estadoProducto) {
        this.estadoProducto = estadoProducto;
    }

    public Integer getFk_idTIpoProducto() {
        return fk_idTIpoProducto;
    }

    public void setFk_idTIpoProducto(Integer fk_idTIpoProducto) {
        this.fk_idTIpoProducto = fk_idTIpoProducto;
    }

    public Integer getFk_idUnidadMedida() {
        return fk_idUnidadMedida;
    }

    public void setFk_idUnidadMedida(Integer fk_idUnidadMedida) {
        this.fk_idUnidadMedida = fk_idUnidadMedida;
    }
        
}

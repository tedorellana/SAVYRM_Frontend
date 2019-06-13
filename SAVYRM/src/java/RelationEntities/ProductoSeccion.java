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
public class ProductoSeccion {
    private Integer idProductoSeccion;
    private Double cantidadProductoSeccion;
    private Timestamp fechaIngreso;
    private Integer fk_idLote;
    private Integer fk_idProducto;
    private Integer fk_idSeccion;

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
    
    
}

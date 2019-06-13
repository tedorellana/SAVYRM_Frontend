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
public class Producto_Formula_ProductoFormula_Precio {
    
    //Producto
    private Integer idProducto;
    private String nombreProducto;
    private String codigoProducto;
    private Boolean estadoProducto;
    private Integer fk_idTIpoProducto;
    private Integer fk_idUnidadMedida;
    
    //Formula
    private Integer idFormula;
    private String nombreFormula;
    private String detalle;
    
    //Producto Formula
    private Integer idProductoFormula;
    private double porcentaje;
    private Integer fk_idFormula;
    private Integer fk_idProductoElaborado;
    private Integer fk_idProductoInsumo;

    //Precio
    private Integer idPrecio;
    private Double unitarioPrecio;
    private Timestamp fechaInicioPrecio;
    private Timestamp fechaFinPrecio;
    private Double mayoriaPrecio;
    private Boolean vigentePrecio;
    private Double cantidadMayoriaPrecio;
    private Integer fk_idProducto;

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

    public Integer getIdFormula() {
        return idFormula;
    }

    public void setIdFormula(Integer idFormula) {
        this.idFormula = idFormula;
    }

    public String getNombreFormula() {
        return nombreFormula;
    }

    public void setNombreFormula(String nombreFormula) {
        this.nombreFormula = nombreFormula;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Integer getIdProductoFormula() {
        return idProductoFormula;
    }

    public void setIdProductoFormula(Integer idProductoFormula) {
        this.idProductoFormula = idProductoFormula;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Integer getFk_idFormula() {
        return fk_idFormula;
    }

    public void setFk_idFormula(Integer fk_idFormula) {
        this.fk_idFormula = fk_idFormula;
    }

    public Integer getFk_idProductoElaborado() {
        return fk_idProductoElaborado;
    }

    public void setFk_idProductoElaborado(Integer fk_idProductoElaborado) {
        this.fk_idProductoElaborado = fk_idProductoElaborado;
    }

    public Integer getFk_idProductoInsumo() {
        return fk_idProductoInsumo;
    }

    public void setFk_idProductoInsumo(Integer fk_idProductoInsumo) {
        this.fk_idProductoInsumo = fk_idProductoInsumo;
    }

    public Integer getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(Integer idPrecio) {
        this.idPrecio = idPrecio;
    }

    public Double getUnitarioPrecio() {
        return unitarioPrecio;
    }

    public void setUnitarioPrecio(Double unitarioPrecio) {
        this.unitarioPrecio = unitarioPrecio;
    }

    public Timestamp getFechaInicioPrecio() {
        return fechaInicioPrecio;
    }

    public void setFechaInicioPrecio(Timestamp fechaInicioPrecio) {
        this.fechaInicioPrecio = fechaInicioPrecio;
    }

    public Timestamp getFechaFinPrecio() {
        return fechaFinPrecio;
    }

    public void setFechaFinPrecio(Timestamp fechaFinPrecio) {
        this.fechaFinPrecio = fechaFinPrecio;
    }

    public Double getMayoriaPrecio() {
        return mayoriaPrecio;
    }

    public void setMayoriaPrecio(Double mayoriaPrecio) {
        this.mayoriaPrecio = mayoriaPrecio;
    }

    public Boolean getVigentePrecio() {
        return vigentePrecio;
    }

    public void setVigentePrecio(Boolean vigentePrecio) {
        this.vigentePrecio = vigentePrecio;
    }

    public Double getCantidadMayoriaPrecio() {
        return cantidadMayoriaPrecio;
    }

    public void setCantidadMayoriaPrecio(Double cantidadMayoriaPrecio) {
        this.cantidadMayoriaPrecio = cantidadMayoriaPrecio;
    }

    public Integer getFk_idProducto() {
        return fk_idProducto;
    }

    public void setFk_idProducto(Integer fk_idProducto) {
        this.fk_idProducto = fk_idProducto;
    }
    
    
}

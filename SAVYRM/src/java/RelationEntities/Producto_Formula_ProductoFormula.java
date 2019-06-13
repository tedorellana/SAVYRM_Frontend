/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RelationEntities;

/**
 *
 * @author torellana
 */
public class Producto_Formula_ProductoFormula {
    
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
    private String detalleFormula;
    
    //Producto Formula
    private Integer idProductoFormula;
    private double porcentaje;
    private Integer fk_idFormula;
    private Integer fk_idProductoElaborado;
    private Integer fk_idProductoInsumo;
    private Double cantidad;

    public Double getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
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

    public String getDetalleFormula() {
        return detalleFormula;
    }

    public void setDetalleFormula(String detalle) {
        this.detalleFormula = detalle;
    }

    public Integer getIdProductoFormula() {
        return idProductoFormula;
    }

    public void setIdProductoFormula(Integer idProductoFormula) {
        this.idProductoFormula = idProductoFormula;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
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
    
    
}

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
public class Servicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida {
    private Integer idServicio;
    private Timestamp horaInicioServicio;
    private Timestamp horaFinServicio;
    private Timestamp horaEdicionServicio;
    private Integer idPersonaAtendidaServicio;
    private Integer idPersonaEmpleado;
    private Integer fk_idTipoServicio;
    
    //ServiciosProducto
    private Integer idServicioProductoSeccion;
    private Double costoTotal;
    private Integer fk_idProductoSeccion;
    private Integer fk_idServicio;
    private Double cantidadServicioProducto;
    
    //ProductoSeccion
    private Integer idProductoSeccion;
    private Double cantidadProductoSeccion;
    private Timestamp fechaIngreso;
    private Integer fk_idLote;
    private Integer fk_idProducto;
    private Integer fk_idSeccion;
    
    //Producto
    private Integer idProducto;
    private String nombreProducto;
    private String codigoProducto;
    private Boolean estadoProducto;
    private Integer fk_idTIpoProducto;
    private Integer fk_idUnidadMedida;
    
    //Unidad de medidad
    private Integer idUnidadMedida;
    private String nombreUnidadMedida;
    private boolean tipo;
    private String abreviacionUnidadMeida;
    private Integer fkUnidadMedida;

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public Timestamp getHoraInicioServicio() {
        return horaInicioServicio;
    }

    public void setHoraInicioServicio(Timestamp horaInicioServicio) {
        this.horaInicioServicio = horaInicioServicio;
    }

    public Timestamp getHoraFinServicio() {
        return horaFinServicio;
    }

    public void setHoraFinServicio(Timestamp horaFinServicio) {
        this.horaFinServicio = horaFinServicio;
    }

    public Timestamp getHoraEdicionServicio() {
        return horaEdicionServicio;
    }

    public void setHoraEdicionServicio(Timestamp horaEdicionServicio) {
        this.horaEdicionServicio = horaEdicionServicio;
    }

    public Integer getIdPersonaAtendidaServicio() {
        return idPersonaAtendidaServicio;
    }

    public void setIdPersonaAtendidaServicio(Integer idPersonaAtendidaServicio) {
        this.idPersonaAtendidaServicio = idPersonaAtendidaServicio;
    }

    public Integer getIdPersonaEmpleado() {
        return idPersonaEmpleado;
    }

    public void setIdPersonaEmpleado(Integer idPersonaEmpleado) {
        this.idPersonaEmpleado = idPersonaEmpleado;
    }

    public Integer getFk_idTipoServicio() {
        return fk_idTipoServicio;
    }

    public void setFk_idTipoServicio(Integer fk_idTipoServicio) {
        this.fk_idTipoServicio = fk_idTipoServicio;
    }

    public Integer getIdServicioProductoSeccion() {
        return idServicioProductoSeccion;
    }

    public void setIdServicioProductoSeccion(Integer idServicioProductoSeccion) {
        this.idServicioProductoSeccion = idServicioProductoSeccion;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public Integer getFk_idProductoSeccion() {
        return fk_idProductoSeccion;
    }

    public void setFk_idProductoSeccion(Integer fk_idProductoSeccion) {
        this.fk_idProductoSeccion = fk_idProductoSeccion;
    }

    public Integer getFk_idServicio() {
        return fk_idServicio;
    }

    public void setFk_idServicio(Integer fk_idServicio) {
        this.fk_idServicio = fk_idServicio;
    }

    public Double getCantidadServicioProducto() {
        return cantidadServicioProducto;
    }

    public void setCantidadServicioProducto(Double cantidadServicioProducto) {
        this.cantidadServicioProducto = cantidadServicioProducto;
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

    public Integer getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdUnidadMedida(Integer idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    public String getNombreUnidadMedida() {
        return nombreUnidadMedida;
    }

    public void setNombreUnidadMedida(String nombreUnidadMedida) {
        this.nombreUnidadMedida = nombreUnidadMedida;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public String getAbreviacionUnidadMeida() {
        return abreviacionUnidadMeida;
    }

    public void setAbreviacionUnidadMeida(String abreviacionUnidadMeida) {
        this.abreviacionUnidadMeida = abreviacionUnidadMeida;
    }

    public Integer getFkUnidadMedida() {
        return fkUnidadMedida;
    }

    public void setFkUnidadMedida(Integer fkUnidadMedida) {
        this.fkUnidadMedida = fkUnidadMedida;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RelationEntities;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author torellana
 */
public class Producto_Preparacion_PreparacionProducto {
    //Producto
    private Integer idProducto;
    private String nombreProducto;
    private String codigoProducto;
    private Boolean estadoProducto;
    private Integer fk_idTIpoProducto;
    private Integer fk_idUnidadMedida;
    
    //Preparacion
    private Integer idPreparacion;
    private String nombrePreparacion;
    private String detalleAdicionalPreparacion;
    private Timestamp fechaCreacionPreparacion;
    private Integer fk_idProducto;
    
    //indicacion
    private Integer idIndicacion;
    private Integer ordenIndicacion;
    private String detalleIndicacion;
    private Integer fk_idPreparacionIndicacion;

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

    public Integer getIdPreparacion() {
        return idPreparacion;
    }

    public void setIdPreparacion(Integer idPreparacion) {
        this.idPreparacion = idPreparacion;
    }

    public String getNombrePreparacion() {
        return nombrePreparacion;
    }

    public void setNombrePreparacion(String nombrePreparacion) {
        this.nombrePreparacion = nombrePreparacion;
    }

    public String getDetalleAdicionalPreparacion() {
        return detalleAdicionalPreparacion;
    }

    public void setDetalleAdicionalPreparacion(String detalleAdicionalPreparacion) {
        this.detalleAdicionalPreparacion = detalleAdicionalPreparacion;
    }

    public Timestamp getFechaCreacionPreparacion() {
        return fechaCreacionPreparacion;
    }

    public void setFechaCreacionPreparacion(Timestamp fechaCreacionPreparacion) {
        this.fechaCreacionPreparacion = fechaCreacionPreparacion;
    }

    public Integer getFk_idProducto() {
        return fk_idProducto;
    }

    public void setFk_idProducto(Integer fk_idProducto) {
        this.fk_idProducto = fk_idProducto;
    }

    public Integer getIdIndicacion() {
        return idIndicacion;
    }

    public void setIdIndicacion(Integer idIndicacion) {
        this.idIndicacion = idIndicacion;
    }

    public int getOrdenIndicacion() {
        return ordenIndicacion;
    }

    public void setOrdenIndicacion(int ordenIndicacion) {
        this.ordenIndicacion = ordenIndicacion;
    }

    public String getDetalleIndicacion() {
        return detalleIndicacion;
    }

    public void setDetalleIndicacion(String detalleIndicacion) {
        this.detalleIndicacion = detalleIndicacion;
    }

    public Integer getFk_idPreparacionIndicacion() {
        return fk_idPreparacionIndicacion;
    }

    public void setFk_idPreparacionIndicacion(Integer fk_idPreparacionIndicacion) {
        this.fk_idPreparacionIndicacion = fk_idPreparacionIndicacion;
    }

    
}

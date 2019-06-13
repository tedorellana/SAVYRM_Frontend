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
public class Seccion {
    
    private Integer idSeccion;
    private String codigoSeccion;
    private Double capacidadSeccion;
    private String detalle;
    private Integer fk_idAlmacen;

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

}

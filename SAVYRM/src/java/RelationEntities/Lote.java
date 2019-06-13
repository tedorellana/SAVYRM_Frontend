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
public class Lote {
    
    private Integer idLote;
    private String codigoLote;
    private Double costoLote;
    private Double cantidaUnidadesLote;
    private Timestamp fechaCaducacionLote;

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
    
    
}

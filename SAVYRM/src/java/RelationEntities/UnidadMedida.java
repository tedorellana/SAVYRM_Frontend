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
public class UnidadMedida {
    private Integer idUnidadMedida;
    private String nombreUnidadMedida;
    private boolean tipo;
    private String abreviacionUnidadMeida;
    private Integer fkUnidadMedida;

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

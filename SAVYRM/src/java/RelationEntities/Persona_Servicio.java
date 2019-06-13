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
public class Persona_Servicio {
    private Integer idPersona;
    private String nombrePersona;
    private String apellidoPaternoPersona;
    private String apellidoMaternoPersona;
    private String documentoIdentidadPersona;
    private String numeroDocumentoPersona;
    private String numeroTelefonoPersona;
    private String correoPersona;
    private String direccionPersona;
    private Integer fk_idTipoPersona;
    
    private Integer idServicio;
    private Timestamp horaInicioServicio;
    private Timestamp horaFinServicio;
    private Timestamp horaEdicionServicio;
    private Integer idPersonaAtendidaServicio;
    private Integer idPersonaEmpleado;
    private Integer fk_idTipoServicio;

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellidoPaternoPersona() {
        return apellidoPaternoPersona;
    }

    public void setApellidoPaternoPersona(String apellidoPaternoPersona) {
        this.apellidoPaternoPersona = apellidoPaternoPersona;
    }

    public String getApellidoMaternoPersona() {
        return apellidoMaternoPersona;
    }

    public void setApellidoMaternoPersona(String apellidoMaternoPersona) {
        this.apellidoMaternoPersona = apellidoMaternoPersona;
    }

    public String getDocumentoIdentidadPersona() {
        return documentoIdentidadPersona;
    }

    public void setDocumentoIdentidadPersona(String documentoIdentidadPersona) {
        this.documentoIdentidadPersona = documentoIdentidadPersona;
    }

    public String getNumeroDocumentoPersona() {
        return numeroDocumentoPersona;
    }

    public void setNumeroDocumentoPersona(String numeroDocumentoPersona) {
        this.numeroDocumentoPersona = numeroDocumentoPersona;
    }

    public String getNumeroTelefonoPersona() {
        return numeroTelefonoPersona;
    }

    public void setNumeroTelefonoPersona(String numeroTelefonoPersona) {
        this.numeroTelefonoPersona = numeroTelefonoPersona;
    }

    public String getCorreoPersona() {
        return correoPersona;
    }

    public void setCorreoPersona(String correoPersona) {
        this.correoPersona = correoPersona;
    }

    public String getDireccionPersona() {
        return direccionPersona;
    }

    public void setDireccionPersona(String direccionPersona) {
        this.direccionPersona = direccionPersona;
    }

    public Integer getFk_idTipoPersona() {
        return fk_idTipoPersona;
    }

    public void setFk_idTipoPersona(Integer fk_idTipoPersona) {
        this.fk_idTipoPersona = fk_idTipoPersona;
    }

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
    
    
    
}

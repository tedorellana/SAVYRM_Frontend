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
public class Persona_Usuario {
    //persona
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
    
    //usuario
    private Integer idUsuario;
    private String nombreUsuario;
    private String contrasenhaUsuario;
    private Integer fk_idPersona;
    private Integer fk_idTipoUsuario;

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

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenhaUsuario() {
        return contrasenhaUsuario;
    }

    public void setContrasenhaUsuario(String contrasenhaUsuario) {
        this.contrasenhaUsuario = contrasenhaUsuario;
    }

    public Integer getFk_idPersona() {
        return fk_idPersona;
    }

    public void setFk_idPersona(Integer fk_idPersona) {
        this.fk_idPersona = fk_idPersona;
    }

    public Integer getFk_idTipoUsuario() {
        return fk_idTipoUsuario;
    }

    public void setFk_idTipoUsuario(Integer fk_idTipoUsuario) {
        this.fk_idTipoUsuario = fk_idTipoUsuario;
    }
    
    
}

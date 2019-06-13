package Entities;
// Generated 01-may-2018 23:13:47 by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * TipoUsuario generated by hbm2java
 */
public class TipoUsuario  implements java.io.Serializable {


    private int idTipoUsuario;
    private String nombreTipoUsuario;
    private Set<Usuario> usuarios = new HashSet<Usuario>(0);

    public TipoUsuario() {
    }

	
    public TipoUsuario(int idTipoUsuario, String nombreTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
        this.nombreTipoUsuario = nombreTipoUsuario;
    }
    public TipoUsuario(int idTipoUsuario, String nombreTipoUsuario, Set<Usuario> usuarios) {
       this.idTipoUsuario = idTipoUsuario;
       this.nombreTipoUsuario = nombreTipoUsuario;
       this.usuarios = usuarios;
    }
   
    public int getIdTipoUsuario() {
        return this.idTipoUsuario;
    }
    
    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }
    public String getNombreTipoUsuario() {
        return this.nombreTipoUsuario;
    }
    
    public void setNombreTipoUsuario(String nombreTipoUsuario) {
        this.nombreTipoUsuario = nombreTipoUsuario;
    }
    public Set<Usuario> getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }




}



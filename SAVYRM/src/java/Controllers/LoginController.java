package Controllers;

import DBAccess.UsuarioDBA;
import Entities.Usuario;
import RelationEntities.Persona_Usuario;

/**
 *
 * @author torellana
 */
public class LoginController {
    public static String component = "LoginController";
    
    public static Persona_Usuario validaUsuarioContraseña(String nombreUsuario, String contraseña){
        Persona_Usuario result;
        UsuarioDBA usuarioDBA = new UsuarioDBA();
        
        Persona_Usuario usuario = null;
        usuario = usuarioDBA.validarUsuarioPersonaContraseña(nombreUsuario, contraseña);
        
        result = (usuario == null) ? null : usuario;
        return result;
    }
}

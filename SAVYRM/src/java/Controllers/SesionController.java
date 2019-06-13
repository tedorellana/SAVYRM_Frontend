/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.LoginController.component;
import DBAccess.UsuarioDBA;
import RelationEntities.Persona_Usuario;

/**
 *
 * @author torellana
 */
public class SesionController {
    public Persona_Usuario iniciarSesion(String nombreUsuario, String contraseña){
        Persona_Usuario resultado;
        UsuarioDBA usuarioDBA = new UsuarioDBA();
        resultado = usuarioDBA.validarUsuarioPersonaContraseña(nombreUsuario, contraseña);
        return resultado;
    }
}

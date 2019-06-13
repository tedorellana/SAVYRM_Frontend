/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DBAccess.ServicioDBA;
import RelationEntities.Persona_Servicio;
import java.util.ArrayList;

/**
 *
 * @author torellana
 */
public class VentasController {

    public ArrayList<Persona_Servicio> getVentas() {
        //Retorna personas que realizaron alguna compra
        ArrayList<Persona_Servicio> lista = new ArrayList();
        lista = ServicioDBA.getVentas();
        return lista;
    }
    
    

    
}

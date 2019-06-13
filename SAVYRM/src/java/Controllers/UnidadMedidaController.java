package Controllers;

import DBAccess.UnidadMedidaDBA;
import RelationEntities.UnidadMedida;
import java.util.ArrayList;

/**
 *
 * @author torellana
 */
public class UnidadMedidaController {
    
    public ArrayList<UnidadMedida> getListaUnidadMedida(){
        ArrayList <UnidadMedida> listaUnidadMedida;
        UnidadMedidaDBA unidadMedidaDBA = new UnidadMedidaDBA();
        
        listaUnidadMedida = unidadMedidaDBA.getListaUnidadMedida();
        return listaUnidadMedida;
    }
    
    public int getIdPorNombre(String nombreUnidadMedida){
        int idUnidadMedida = 0;
        
        UnidadMedidaDBA unidadMedidaDBA = new UnidadMedidaDBA();
        idUnidadMedida = unidadMedidaDBA.getIdPorNombre(nombreUnidadMedida);
        
        return idUnidadMedida;
    }
    
    public String getNombrePorID(Integer idUnidadMedida){
        String nombreUnidadMedida = "No Encontrado";
        
        UnidadMedidaDBA unidadMedidaDBA = new UnidadMedidaDBA();
        nombreUnidadMedida = unidadMedidaDBA.getNombrePorID(idUnidadMedida);
        
        return nombreUnidadMedida;
    }
    
}

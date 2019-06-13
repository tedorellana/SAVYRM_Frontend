package Controllers;

import DBAccess.TipoProductoDBA;
import Entities.TipoProducto;
import java.util.ArrayList;

/**
 *
 * @author torellana
 */
public class TipoProductoController {
    
    public ArrayList<TipoProducto> getListaTipoProducto(){
        ArrayList <TipoProducto> listaProducto;
        TipoProductoDBA tipoProductoDBA = new TipoProductoDBA();
        
        System.out.println("getListatipoProducto");
        
        listaProducto = tipoProductoDBA.getListaTipoProducto();
        return listaProducto;
    }
    
    public int getIdPorNombre(String nombreTipoProducto){
        int idTipoProducto;
        
        TipoProductoDBA tipoProductoDBA = new TipoProductoDBA();
        
        idTipoProducto = tipoProductoDBA.getIdPorNombre(nombreTipoProducto);
        
        return idTipoProducto;
    }
    
}

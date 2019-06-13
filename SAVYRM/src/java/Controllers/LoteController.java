package Controllers;

import DBAccess.LoteDBA;
import DBAccess.TipoProductoDBA;
import RelationEntities.Lote;
import java.util.ArrayList;

/**
 *
 * @author torellana
 */
public class LoteController {
    
    public ArrayList<Lote> getListaLote(){
        ArrayList <Lote> listaLote;
        
        listaLote = LoteDBA.getListaLote();
        return listaLote;
    }
    
    public Lote getLotePorCodigo(String codigoLote){
        Lote lote = new Lote();
        
        lote = LoteDBA.getLotePorNombre(codigoLote);
        return lote;
    }

    public void registrarLote(Lote lote) {
        LoteDBA.registrarLote(lote);
    }
    
}

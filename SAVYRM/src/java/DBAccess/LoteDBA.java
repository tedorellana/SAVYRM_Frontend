/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import RelationEntities.Lote;
import RelationEntities.Persona_TipoPersona;
import RelationEntities.Producto_Formula_ProductoFormula;
import RelationEntities.Producto_Preparacion_PreparacionProducto;
import RelationEntities.Producto_TipoProducto;
import Resources.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

/**
 *
 * @author torellana
 */
public class LoteDBA {
    
    private static Session session = null;
    
    public static ArrayList<Lote> getListaLote(){
        ArrayList <Lote> listaLote;
        Persona_TipoPersona persona_TipoPersona = null;
        
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery("select * from Lote").setResultTransformer(Transformers.aliasToBean(Lote.class));
        
        session.beginTransaction();
        
        listaLote = (ArrayList<Lote>) query.list();

        session.getTransaction().commit();
        session.close();
        
        return listaLote;
    }

    public static Lote getLotePorNombre(String codigoLote) {
        ArrayList <Lote> listaLote;
        
        Lote lote = new Lote();
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery("select * from Lote where codigoLote = :codigoLote").setResultTransformer(Transformers.aliasToBean(Lote.class));
        query.setParameter("codigoLote", codigoLote);
        session.beginTransaction();
        
        listaLote = (ArrayList<Lote>) query.list();
        
        if(!listaLote.isEmpty()){
            lote = listaLote.get(0);
        }
        session.getTransaction().commit();
        session.close();
        
        return lote;
    }

    public static void registrarLote(Lote lote) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery("Insert into Lote (codigoLote,costoLote,cantidaUnidadesLote) "
                + "values (:codigoLote,:costoLote,:cantidaUnidadesLote)");
        query.setParameter("codigoLote", lote.getCodigoLote());
        query.setParameter("costoLote", lote.getCostoLote());
        query.setParameter("cantidaUnidadesLote", lote.getCantidaUnidadesLote());
        
        session.beginTransaction();
        
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import RelationEntities.UnidadMedida;
import Resources.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

/**
 *
 * @author torellana
 */
public class UnidadMedidaDBA {
    Session session = null;
    
    public ArrayList<UnidadMedida> getListaUnidadMedida(){
        ArrayList <UnidadMedida> listaUnidadMedida;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        System.out.println("ANTESSS DE QUERY");
        query = session.createSQLQuery("Select * from UnidadMedida").setResultTransformer(Transformers.aliasToBean(UnidadMedida.class));
        System.out.println("DEPSUES DE QUERY");
        session.beginTransaction();
        System.out.println("BEGN TRANSA ");
        listaUnidadMedida = (ArrayList<UnidadMedida>) query.list();
        System.out.println("LISTA ");
        session.getTransaction().commit();
        session.close();
        
        return listaUnidadMedida;
    }
    
    public int getIdPorNombre(String nombreUnidadMedida){
        ArrayList <UnidadMedida> listaUnidadMedida;
        int idUnidadMedida = 0;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        
        query = session.createSQLQuery(""
                + "Select idUnidadMedida from UnidadMedida "
                + "where nombreUnidadMedida = :nombreUnidadMedida").setResultTransformer(Transformers.aliasToBean(UnidadMedida.class));
        query.setParameter("nombreUnidadMedida", nombreUnidadMedida);
        session.beginTransaction();
        
        listaUnidadMedida = (ArrayList<UnidadMedida>) query.list();
        
        if(listaUnidadMedida.size()>0){
            System.out.println(listaUnidadMedida.get(0).toString());
            idUnidadMedida = listaUnidadMedida.get(0).getIdUnidadMedida();
        }
        
        session.getTransaction().commit();
        session.close();
        
        return idUnidadMedida;
    }

    public String getNombrePorID(Integer idUnidadMedida) {
        ArrayList <UnidadMedida> listaUnidadMedida;
        String nombreUnidaMedida = "No Encontrado"; 
        
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        
        query = session.createSQLQuery(""
                + "Select nombreUnidadMedida from UnidadMedida "
                + "where idUnidadMedida = :idUnidadMedida").setResultTransformer(Transformers.aliasToBean(UnidadMedida.class));
        query.setParameter("idUnidadMedida", idUnidadMedida);
        session.beginTransaction();
        
        listaUnidadMedida = (ArrayList<UnidadMedida>) query.list();
        
        if(listaUnidadMedida.size()>0){
            System.out.println(listaUnidadMedida.get(0).toString());
            nombreUnidaMedida = listaUnidadMedida.get(0).getNombreUnidadMedida();
        }
        
        session.getTransaction().commit();
        session.close();
        
        return nombreUnidaMedida;
    }
    
    
    
    
    
    
    
    
}

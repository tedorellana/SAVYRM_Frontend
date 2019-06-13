/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import Entities.TipoProducto;
import Resources.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

/**
 *
 * @author torellana
 */
public class TipoProductoDBA {
    Session session = null;
    public ArrayList<TipoProducto> getListaTipoProducto(){
        ArrayList <TipoProducto> listaTipoProducto;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery("Select * from TipoProducto").setResultTransformer(Transformers.aliasToBean(TipoProducto.class));
        session.beginTransaction();
        
        listaTipoProducto = (ArrayList<TipoProducto>) query.list();
        
        session.getTransaction().commit();
        session.close();
        
        return listaTipoProducto;
    }
    
    public int getIdPorNombre(String nombreTipoProducto){
        ArrayList <TipoProducto> listaTipoProducto;
        int idTipoProducto = 0;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery(
                "Select idTipoProducto from TipoProducto"
                + " where nombreTipoProducto = :nombreTipoProducto").setResultTransformer(Transformers.aliasToBean(TipoProducto.class));
        query.setParameter("nombreTipoProducto", nombreTipoProducto);
        session.beginTransaction();
        
        listaTipoProducto = (ArrayList<TipoProducto>) query.list();
        if(listaTipoProducto.size()>0){
            System.out.println(listaTipoProducto.get(0).toString());
            idTipoProducto = listaTipoProducto.get(0).getIdTipoProducto();
        }
        session.getTransaction().commit();
        session.close();
        
        return idTipoProducto;
    }
    
    
    
}

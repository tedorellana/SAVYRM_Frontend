/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

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
public class PersonaDBA {
    
    private static Session session = null;
    
    public static Persona_TipoPersona getPersona_PersonaTipoPorDNI(String numeroDocumentoPersona){
        ArrayList <Persona_TipoPersona> listaPersona;
        Persona_TipoPersona persona_TipoPersona = null;
        
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery("select * from persona  p\n" +
            "inner join TipoPersona tp\n" +
            "on p.fk_idTipoPersona = tp.idTipoPersona\n" +
            "where p.numeroDocumentoPersona = :numeroDocumentoPersona").setResultTransformer(Transformers.aliasToBean(Persona_TipoPersona.class));
        query.setParameter("numeroDocumentoPersona", numeroDocumentoPersona);
        
        session.beginTransaction();
        
        listaPersona = (ArrayList<Persona_TipoPersona>) query.list();
        
        if(!listaPersona.isEmpty()){
            persona_TipoPersona = listaPersona.get(0);
        }
        else{
            System.out.println("getPersona_PersonaTipoPorDNI devuelve un valor vacio");
        }
        
        session.getTransaction().commit();
        session.close();
        
        return persona_TipoPersona;
    }

    public static Persona_TipoPersona getNombrePersonaPorID(Integer idPersonaEmpleado) {
        ArrayList <Persona_TipoPersona> listaPersona;
        Persona_TipoPersona persona_TipoPersona = null;
        
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery("select * from persona  p\n" +
            "inner join TipoPersona tp\n" +
            "on p.fk_idTipoPersona = tp.idTipoPersona\n" +
            "where p.idPersona = :idPersonaEmpleado").setResultTransformer(Transformers.aliasToBean(Persona_TipoPersona.class));
        query.setParameter("idPersonaEmpleado", idPersonaEmpleado);
        
        session.beginTransaction();
        
        listaPersona = (ArrayList<Persona_TipoPersona>) query.list();
        
        if(!listaPersona.isEmpty()){
            persona_TipoPersona = listaPersona.get(0);
        }
        else{
            System.out.println("getNombrePersonaPorID devuelve un valor vacio");
        }
        
        session.getTransaction().commit();
        session.close();
        
        return persona_TipoPersona;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import RelationEntities.Persona_Servicio;
import Resources.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

/**
 *
 * @author torellana
 */
public class ServicioDBA {
    private static Session session = null;
    
    public static ArrayList<Persona_Servicio> getVentas(){
        ArrayList <Persona_Servicio> lista;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        Integer idTipoServicio = 2;//Ventas
        query = session.createSQLQuery(
                "Select * from Persona p\n" +
                "inner join Servicio s on s.idPersonaAtendidaServicio = p.idPersona\n" +
                "where s.fk_idTipoServicio=:fk_idTipoServicio").setResultTransformer(Transformers.aliasToBean(Persona_Servicio.class));
        query.setParameter("fk_idTipoServicio", idTipoServicio);
        
        session.beginTransaction();
        lista = (ArrayList<Persona_Servicio>) query.list();

        session.getTransaction().commit();
        session.close();
        
        return lista;
    }
    
}

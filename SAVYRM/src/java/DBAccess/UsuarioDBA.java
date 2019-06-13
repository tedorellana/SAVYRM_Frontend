package DBAccess;

import Entities.Usuario;
import RelationEntities.Persona_Usuario;
import Resources.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
/**
 *
 * @author torellana
 */
public class UsuarioDBA {
    Session session = null;
    public static String component = "UsuarioDBA";
     
    public Persona_Usuario validarUsuarioPersonaContraseña(String nombreUsuario, String contraseñaUsuario){
        Persona_Usuario usuario = null;
        
        session = HibernateUtil.getSessionFactory().openSession();
        
        Query query;
        
        query = session.createSQLQuery(
                "Select * from Usuario u\n" +
                "inner join Persona p on p.idPersona = u.fk_idPersona\n" +
                "where u.nombreUsuario = :nombreUsuario\n" +
                "and u.contrasenhaUsuario = :contraseñaUsuario")
                .setResultTransformer(Transformers.aliasToBean(Persona_Usuario.class));
        query.setParameter("nombreUsuario", nombreUsuario);
        query.setParameter("contraseñaUsuario", contraseñaUsuario);
        
        session.beginTransaction();
        
        List <Persona_Usuario> listaUsuario; 
        listaUsuario = query.list();
        usuario = (listaUsuario.size() > 0) ? listaUsuario.get(0): null;
        
        session.getTransaction().commit();
        session.close();
        
        return usuario;
    }
    
    public Usuario validarUsuarioContraseña(String nombreUsuario, String contraseñaUsuario){
        Usuario usuario = null;
        
        session = HibernateUtil.getSessionFactory().openSession();
        
        Query query;
        
        query = session.createSQLQuery(
                "Select idUsuario, nombreUsuario, contrasenhaUsuario from Usuario\n" +
                "where nombreUsuario = :nombreUsuario\n" +
                "and contrasenhaUsuario = :contraseñaUsuario")
                .setResultTransformer(Transformers.aliasToBean(Usuario.class));
        query.setParameter("nombreUsuario", nombreUsuario);
        query.setParameter("contraseñaUsuario", contraseñaUsuario);
        
        session.beginTransaction();
        
        List <Usuario> listaUsuario; 
        listaUsuario = query.list();
        usuario = (listaUsuario.size() > 0) ? listaUsuario.get(0): null;
        
        session.getTransaction().commit();
        session.close();
        
        return usuario;
    }
            
            
}

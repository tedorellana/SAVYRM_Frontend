/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import RelationEntities.Producto_Precio;
import Resources.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

/**
 *
 * @author torellana
 */
public class PrecioDBA {
    private static Session session = null;
    public static ArrayList<Producto_Precio> getPreciosPorProducto(Integer idProducto){
        ArrayList <Producto_Precio> listaPrecios;
        
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery("Select * from Precio pre\n" +
            "inner join Producto pro on pro.idProducto = pre.fk_idProducto\n" +
            "where pro.idProducto = :idProducto\n" +
            "order by pre.fechaInicioPrecio desc").setResultTransformer(Transformers.aliasToBean(Producto_Precio.class));
        query.setParameter("idProducto", idProducto);
        
        session.beginTransaction();
        
        listaPrecios = (ArrayList<Producto_Precio>) query.list();
        
        session.getTransaction().commit();
        session.close();
        
        return listaPrecios;
    }
    
    public static void RegistrarPrecio(Producto_Precio productoPrecio){
        //deshabilitando precios anteriores
        Session sessionDes = HibernateUtil.getSessionFactory().openSession();
        Query queryDes;
        queryDes = sessionDes.createSQLQuery("update Precio set vigentePrecio = 0 where fk_idProducto = :idProducto");
        queryDes.setParameter("idProducto", productoPrecio.getIdProducto());
        
        sessionDes.beginTransaction();
        queryDes.executeUpdate();
        sessionDes.getTransaction().commit();
        sessionDes.close();
        
        //agregando
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery("insert into Precio (unitarioPrecio,fechaInicioPrecio,vigentePrecio,fk_idProducto)"
                + " values  (:unitarioPrecio,:fechaInicioPrecio,:vigentePrecio, :fk_idProducto )");
        query.setParameter("unitarioPrecio", productoPrecio.getUnitarioPrecio());
        query.setParameter("fechaInicioPrecio", productoPrecio.getFechaInicioPrecio());
        query.setParameter("vigentePrecio", true);
        query.setParameter("fk_idProducto", productoPrecio.getIdProducto());
        
        session.beginTransaction();
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public static Producto_Precio getPrecioVigentePorProductoID(Integer idProductoConsultado) {
        ArrayList <Producto_Precio> listaPrecios;
        Producto_Precio producto_Precio = null;
        
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery("Select TOP 1 * from Precio pre\n" +
            "inner join Producto pro on pro.idProducto = pre.fk_idProducto\n" +
            "where pro.idProducto = :idProducto\n" +
            "and pre.vigentePrecio = 1\n" +
            "order by pre.fechaInicioPrecio desc").setResultTransformer(Transformers.aliasToBean(Producto_Precio.class));
        query.setParameter("idProducto", idProductoConsultado);
        
        session.beginTransaction();
        
        listaPrecios = (ArrayList<Producto_Precio>) query.list();
        
        if(!listaPrecios.isEmpty()){
            producto_Precio = listaPrecios.get(0);
            System.out.println("producto lleno " + producto_Precio.getNombreProducto());
        }
        
        session.getTransaction().commit();
        session.close();
        
        return producto_Precio;
    }
}

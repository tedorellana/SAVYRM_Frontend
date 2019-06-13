/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import DTO.SeccionesIDList;
import RelationEntities.Almacen;
import RelationEntities.Seccion;
import RelationEntities.Almacen_Seccion;
import RelationEntities.Almacen_Seccion_ProductoSeccion_Producto_Lote;
import Resources.HibernateUtil;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

/**
 *
 * @author torellana
 */
public class AlmacenDBA {
    
    private static Session session = null;
    
    public static ArrayList <Almacen> getAlmacenes(){
        ArrayList <Almacen> listaAlmacen;
        
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery("select * from Almacen").setResultTransformer(Transformers.aliasToBean(Almacen.class));
        
        session.beginTransaction();
        
        listaAlmacen = (ArrayList<Almacen>) query.list();
        
        session.getTransaction().commit();
        session.close();
        
        return listaAlmacen;
    }

    public static ArrayList<Seccion> getSeccionesPorIDAlmacen(Integer idAlmacenSeleccionado) {
        ArrayList <Seccion> listaSeccion;
        
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        String queryStatement = "select * from Seccion where fk_idAlmacen = " + idAlmacenSeleccionado;
        System.out.println("queryStatement: " + queryStatement);
        query = session.createSQLQuery( queryStatement).setResultTransformer(Transformers.aliasToBean(Seccion.class));
        
        session.beginTransaction();
        
        listaSeccion = (ArrayList<Seccion>) query.list();
        
        session.getTransaction().commit();
        session.close();
        
        return listaSeccion;
    }

    public static ArrayList<Seccion> getSeccionesPorListaIDAlmacen(List<Integer> idAlmacenesSeleccionados) {
        ArrayList <Seccion> listaSeccion;
        
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        String queryStatement = "select * from Seccion where fk_idAlmacen in (";
        int cantidadDeRegistros = idAlmacenesSeleccionados.size();
        for (int i = 0; i<cantidadDeRegistros ;i++) {
            if(cantidadDeRegistros - 1 == i) queryStatement += idAlmacenesSeleccionados.get(i) + ")";
            else queryStatement += idAlmacenesSeleccionados.get(i) +",";
        }
        System.out.println("queryStatement: " + queryStatement);
        query = session.createSQLQuery( queryStatement).setResultTransformer(Transformers.aliasToBean(Seccion.class));

        session.beginTransaction();
        
        listaSeccion = (ArrayList<Seccion>) query.list();
        
        session.getTransaction().commit();
        session.close();
        
        return listaSeccion;
    }
    
    public static void agregarAlmacenSeccion(ArrayList<Almacen_Seccion> listaAlmacenSeccion) {
        Integer idAlmacen = 0;
        
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery("insert into Almacen (nombreAlmacen,direccion) output"
                + " inserted.idAlmacen values (:nombreAlmacen,:direccion)");
        query.setParameter("nombreAlmacen", listaAlmacenSeccion.get(0).getNombreAlmacen());
        query.setParameter("direccion", listaAlmacenSeccion.get(0).getDireccion());
        
        session.beginTransaction();
        
        idAlmacen = Integer.parseInt(query.list().get(0).toString());
        
        session.getTransaction().commit();
        session.close();
        
        //agregar secciones
        for (Almacen_Seccion almacenSeccion : listaAlmacenSeccion) {
            Session sessionSecciones = HibernateUtil.getSessionFactory().openSession();
            Query querySecciones;
            querySecciones = sessionSecciones.createSQLQuery("insert into Seccion (codigoSeccion,detalle,fk_idAlmacen) "
                    + " values (:codigoSeccion,:detalle,:fk_idAlmacen)");
            querySecciones.setParameter("codigoSeccion", almacenSeccion.getCodigoSeccion());
            querySecciones.setParameter("detalle", almacenSeccion.getDetalle());
            querySecciones.setParameter("fk_idAlmacen", idAlmacen);

            sessionSecciones.beginTransaction();

            querySecciones.executeUpdate();

            sessionSecciones.getTransaction().commit();
            sessionSecciones.close();
        }
        
    }

    public static ArrayList<Almacen_Seccion_ProductoSeccion_Producto_Lote> getTodosDatosSeccionPorAlmacen(Integer idAlmacen) {
        ArrayList <Almacen_Seccion_ProductoSeccion_Producto_Lote> listaAlmacen_Seccion_ProductoSeccion_Producto_Lote;
        
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery("Select * from Almacen alm\n" +
            "inner join Seccion sec on sec.fk_idAlmacen = alm.idAlmacen\n" +
            "inner join ProductoSeccion prosec on prosec.fk_idSeccion = sec.idSeccion\n" +
            "inner join Producto pro on pro.idProducto = prosec.fk_idProducto\n" +
            "inner join Lote lot on lot.idLote = prosec.fk_idLote \n" +
            "where alm.idAlmacen = :idAlmacen").setResultTransformer(Transformers.aliasToBean(Almacen_Seccion_ProductoSeccion_Producto_Lote.class));
        query.setParameter("idAlmacen", idAlmacen);
        
        session.beginTransaction();
        
        listaAlmacen_Seccion_ProductoSeccion_Producto_Lote = (ArrayList<Almacen_Seccion_ProductoSeccion_Producto_Lote>) query.list();
        
        session.getTransaction().commit();
        session.close();
        
        return listaAlmacen_Seccion_ProductoSeccion_Producto_Lote;
    }

    public static ArrayList<Almacen_Seccion_ProductoSeccion_Producto_Lote> getInventarioPorIDSeccionSeleccionadas(List<Integer> listaIDSeccionesSeleccionados) {
        ArrayList <Almacen_Seccion_ProductoSeccion_Producto_Lote> listaAlmacen_Seccion_ProductoSeccion_Producto_Lote;
        
        session = HibernateUtil.getSessionFactory().openSession();
        String queryStatement = "Select * from Almacen alm\n" +
            "inner join Seccion sec on sec.fk_idAlmacen = alm.idAlmacen\n" +
            "inner join ProductoSeccion prosec on prosec.fk_idSeccion = sec.idSeccion\n" +
            "inner join Producto pro on pro.idProducto = prosec.fk_idProducto\n" +
            "inner join Lote lot on lot.idLote = prosec.fk_idLote\n" +
            "where sec.idSeccion in (";
        int listaSeccionesContador = listaIDSeccionesSeleccionados.size();
        
        Query query;
        for (int i = 0; i < listaSeccionesContador; i++) {
            if (i == listaSeccionesContador - 1) queryStatement += listaIDSeccionesSeleccionados.get(i) + ")";
            else queryStatement += listaIDSeccionesSeleccionados.get(i) + ",";
        }
        System.out.println("QUERYSTATEMENT: " + queryStatement);
        query = session.createSQLQuery(queryStatement).setResultTransformer(Transformers.aliasToBean(Almacen_Seccion_ProductoSeccion_Producto_Lote.class));
        
        session.beginTransaction();
        
        listaAlmacen_Seccion_ProductoSeccion_Producto_Lote = (ArrayList<Almacen_Seccion_ProductoSeccion_Producto_Lote>) query.list();
        
        session.getTransaction().commit();
        session.close();
        
        return listaAlmacen_Seccion_ProductoSeccion_Producto_Lote;
    }

    public static void registarProductoEnSeccion(Integer idProducto, Integer idLote, Double cantidadProducto, Integer idSeccion) {
        session = HibernateUtil.getSessionFactory().openSession();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Query query;
        query = session.createSQLQuery("insert into ProductoSeccion (fk_idProducto,fk_idSeccion,fk_idLote,cantidadProductoSeccion,fechaIngreso)\n" +
            "values (:fk_idProducto,:fk_idSeccion,:fk_idLote,:cantidadProductoSeccion,:fechaIngreso)");
        query.setParameter("fk_idProducto", idProducto);
        query.setParameter("fk_idSeccion", idSeccion);
        query.setParameter("fk_idLote", idLote);
        query.setParameter("cantidadProductoSeccion", cantidadProducto);
        query.setParameter("fechaIngreso", timestamp);
        
        session.beginTransaction();
        
        query.executeUpdate();
        
        session.getTransaction().commit();
        session.close();
    }
    
    public static void agregarAlmacen(Almacen almacen){
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery("insert into Almacen values(:nombreAlmacen, :direccion, :capacidad)");
        query.setParameter("nombreAlmacen", almacen.getNombreAlmacen());
        query.setParameter("direccion", almacen.getDireccion());
        query.setParameter("capacidad", almacen.getCapacidad());
        session.beginTransaction();
        query.executeUpdate();
        
        session.getTransaction().commit();
        session.close();
    }
    
    public static void agregarSeccion(Seccion seccion){
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery("insert into Seccion (codigoSeccion,capacidadSeccion,detalle,fk_idAlmacen) "
                    + " values (:codigoSeccion, :capacidadSeccion,:detalle,:fk_idAlmacen)");
        query.setParameter("codigoSeccion", seccion.getCodigoSeccion());
        query.setParameter("capacidadSeccion", seccion.getCapacidadSeccion());
        query.setParameter("detalle", seccion.getDetalle());
        query.setParameter("fk_idAlmacen", seccion.getFk_idAlmacen());
        session.beginTransaction();
        query.executeUpdate();
        
        session.getTransaction().commit();
        session.close();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import Entities.Producto;
import Entities.TipoProducto;
import Entities.UnidadMedida;
import RelationEntities.Persona_TipoPersona;
import RelationEntities.ProductoSeccion;
import RelationEntities.Producto_Formula_ProductoFormula;
import RelationEntities.Producto_Formula_ProductoFormula_Precio;
import RelationEntities.Producto_Preparacion_PreparacionProducto;
import RelationEntities.Producto_TipoProducto;
import RelationEntities.Servicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida;
import Resources.HibernateUtil;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

/**
 *
 * @author torellana
 */
public class ProductoDBA {
    private static Session session = null;

    public ArrayList<Producto_TipoProducto> getListaProducto_TipoProducto(){
        ArrayList <Producto_TipoProducto> listaProductos;
        Boolean estadoProducto = true; //solo productos activos
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery("select * from Producto as p\n" +
"                inner join TipoProducto as tp" +
"                on tp.idTipoProducto = p.fk_idTIpoProducto"
                + " where p.estadoProducto = :estadoProducto").setResultTransformer(Transformers.aliasToBean(Producto_TipoProducto.class));
        query.setParameter("estadoProducto", estadoProducto);
        
        session.beginTransaction();
        
        listaProductos = (ArrayList<Producto_TipoProducto>) query.list();
        
        session.getTransaction().commit();
        session.close();
        
        return listaProductos;
    }
    
    public ArrayList<Producto_TipoProducto> getListaProducto_TipoProductoConPrecio(){
        ArrayList <Producto_TipoProducto> listaProductos;
        Boolean estadoProducto = true; //solo productos activos
        Boolean vigentePrecio = true;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery("select p.*, tp.* from Producto as p\n" +
            "left join TipoProducto as tp\n" +
            "on tp.idTipoProducto = p.fk_idTIpoProducto\n" +
            "inner join Precio pre\n" +
            "on pre.fk_idProducto = p.idProducto\n" +
            "where p.estadoProducto = :estadoProducto\n" +
            "and pre.vigentePrecio = :vigentePrecio").setResultTransformer(Transformers.aliasToBean(Producto_TipoProducto.class));
        query.setParameter("estadoProducto", estadoProducto);
        query.setParameter("vigentePrecio", vigentePrecio);
        
        session.beginTransaction();
        
        listaProductos = (ArrayList<Producto_TipoProducto>) query.list();
        
        session.getTransaction().commit();
        session.close();
        
        return listaProductos;
    }
    
    public ArrayList<Producto_TipoProducto> getListaProducto_TipoProductoConElaboracion(){
        ArrayList <Producto_TipoProducto> listaProductos;
        ArrayList <Producto_TipoProducto> listaProductosConInstrucciones = new ArrayList<Producto_TipoProducto>();
        Boolean estadoProducto = true; //solo productos activos
        Session sessionProductos = HibernateUtil.getSessionFactory().openSession();
        //obteniendo lista de productos activos
        Query queryProductos;
        queryProductos = sessionProductos.createSQLQuery("select * from Producto as p\n" +
                " inner join TipoProducto as tp" +
                " on tp.idTipoProducto = p.fk_idTIpoProducto"
                + " where p.estadoProducto = :estadoProducto").setResultTransformer(Transformers.aliasToBean(Producto_TipoProducto.class));
        queryProductos.setParameter("estadoProducto", estadoProducto);
        
        sessionProductos.beginTransaction();
        
        listaProductos = (ArrayList<Producto_TipoProducto>) queryProductos.list();
        
        sessionProductos.getTransaction().commit();
        sessionProductos.close();
        
        for (Producto_TipoProducto producto : listaProductos) {
            ArrayList <Producto_TipoProducto> listaProductosTemp = null;
            Session sessionInstrucciones = HibernateUtil.getSessionFactory().openSession();
            Query queryInstrucciones;
            queryInstrucciones = sessionInstrucciones.createSQLQuery("IF ((select COUNT(*) from Producto pro\n" +
                "inner join Preparacion pre on pre.fk_idProducto = pro.idProducto\n" +
                "inner join Indicacion ind on ind.fk_idPreparacion = pre.idPreparacion\n" +
                "where pro.idProducto = :idProducto) > 0)\n" +
                "	select * from Producto as p\n" +
                "	inner join TipoProducto as tp\n" +
                "	on tp.idTipoProducto = p.fk_idTIpoProducto\n" +
                "	where p.estadoProducto = :estadoProducto\n" +
                "	and p.idProducto = :idProducto "+
                "else\n" +
                "	select p.*, tp.* from Producto as p\n" +
                "	inner join TipoProducto as tp\n" +
                "	on tp.idTipoProducto = p.fk_idTIpoProducto\n" +
                "	inner join Preparacion pre on pre.fk_idProducto = p.idProducto\n" +
                "	inner join Indicacion ind on ind.fk_idPreparacion = pre.idPreparacion\n" +
                "	where p.estadoProducto = :estadoProducto\n" +
                "	and p.idProducto = :idProducto").setResultTransformer(Transformers.aliasToBean(Producto_TipoProducto.class));
            System.out.println("EXTRAYENDO PRODUCTO: "+producto.getIdProducto());
            queryInstrucciones.setParameter("idProducto", producto.getIdProducto());
            queryInstrucciones.setParameter("estadoProducto", estadoProducto);
            sessionInstrucciones.beginTransaction();
            
            listaProductosTemp = (ArrayList<Producto_TipoProducto>) queryInstrucciones.list();
            
            if(!listaProductosTemp.isEmpty()){
                listaProductosConInstrucciones.add(listaProductosTemp.get(0));
            }
            
            sessionInstrucciones.getTransaction().commit();
            sessionInstrucciones.close();
        }
        
        
        return listaProductosConInstrucciones;
    }
    
    public ArrayList<Producto_TipoProducto> getListaProducto_TipoProductoNoFinales(){
        ArrayList <Producto_TipoProducto> listaProductos;
        Boolean estadoProducto = true; //solo productos activos
        session = HibernateUtil.getSessionFactory().openSession();
        Integer idTipoProductoFINAL = 1;
        Query query;
        query = session.createSQLQuery("select * from Producto as p\n" +
                " inner join TipoProducto as tp" +
                " on tp.idTipoProducto = p.fk_idTIpoProducto"+
                " where tp.idTipoProducto <> :idTipoProductoFINAL"
                + " and p.estadoProducto = :estadoProducto").setResultTransformer(Transformers.aliasToBean(Producto_TipoProducto.class));
        query.setParameter("idTipoProductoFINAL", idTipoProductoFINAL);
        query.setParameter("estadoProducto", estadoProducto);
        
        session.beginTransaction();
        
        listaProductos = (ArrayList<Producto_TipoProducto>) query.list();
        
        session.getTransaction().commit();
        session.close();
        
        return listaProductos;
    }
    
    public String registrarProducto(String nombreProducto, Integer idTipoProducto, Integer idUnidadMedida){
        String resultado = "error";
        session = HibernateUtil.getSessionFactory().openSession();
        Boolean estadoProducto = true; //estado de prodcuto activo por defecto
        
        Query query;
        System.out.println("nombreProducto:"+nombreProducto);
        query = session.createSQLQuery(
                "insert into Producto (nombreProducto,codigoProducto,estadoProducto,fk_idUnidadMedida,fk_idTIpoProducto)\n" +
                "values(:nombreProducto,:codigoProducto,:estadoProducto,:fk_idUnidadMedida,:fk_idTIpoProducto)");
        query.setParameter("nombreProducto", nombreProducto);
        query.setParameter("codigoProducto", nombreProducto);
        query.setParameter("fk_idUnidadMedida", idUnidadMedida);
        query.setParameter("fk_idTIpoProducto", idTipoProducto);
        query.setParameter("estadoProducto", estadoProducto);
        
        session.beginTransaction();
        
        try {
            System.out.println("por grabar");
            query.executeUpdate();
            System.out.println("por grabar");
            resultado = "exito";
        } catch (HibernateException e) {
            System.out.println("HibernateException: "+e);
            resultado = "error";
        }
        
        session.getTransaction().commit();
        session.close();
        System.out.println(resultado);
        return resultado;
    }
    
    public String actualizarProducto(String nombreProducto, Integer idProducto, Integer idTipoProducto, Integer idUnidadMedida){
        String resultado = "error";
        session = HibernateUtil.getSessionFactory().openSession();
        Boolean estadoProducto = true; //estado de prodcuto activo por defecto
        
        Query query;
        System.out.println("nombreProducto:"+nombreProducto);
        query = session.createSQLQuery(
                "update Producto set nombreProducto = :nombreProducto,\n" +
                " estadoProducto= :estadoProducto, fk_idUnidadMedida=:fk_idUnidadMedida,\n" +
                " fk_idTIpoProducto=:fk_idTIpoProducto where idProducto = :idProducto");
        query.setParameter("nombreProducto", nombreProducto);
        query.setParameter("fk_idUnidadMedida", idUnidadMedida);
        query.setParameter("fk_idTIpoProducto", idTipoProducto);
        query.setParameter("estadoProducto", estadoProducto);
        query.setParameter("idProducto", idProducto);
        
        session.beginTransaction();
        
        try {
            System.out.println("por grabar");
            query.executeUpdate();
            System.out.println("por grabar");
            resultado = "exito";
        } catch (HibernateException e) {
            System.out.println("HibernateException: "+e);
            resultado = "error";
        }
        
        session.getTransaction().commit();
        session.close();
        System.out.println(resultado);
        return resultado;
    }
    
    public Boolean eliminarProducto(Producto producto){
        Boolean resultado = false;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery("update Producto set estadoProducto = 0 where idProducto = :idProducto");
        query.setParameter("idProducto", producto.getIdProducto());
        
        session.beginTransaction();
        
        resultado = (query.executeUpdate() > 0);
        
        session.getTransaction().commit();
        session.close();
        
        return resultado;
    }

    public Producto_TipoProducto getProducto_TipoProductoPorIDProducto(Integer idProducto) {
        ArrayList <Producto_TipoProducto> listaProductos;
        Producto_TipoProducto producto_TipoProducto = new Producto_TipoProducto();
        
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery("select top 1 * from Producto as p\n" +
                "inner join TipoProducto as tp " +
                "on tp.idTipoProducto = p.fk_idTIpoProducto "
                + " where p.idProducto= :idProducto").setResultTransformer(Transformers.aliasToBean(Producto_TipoProducto.class));
        query.setParameter("idProducto", idProducto);
        
        session.beginTransaction();
        
        listaProductos = (ArrayList<Producto_TipoProducto>) query.list();
        
        producto_TipoProducto = (listaProductos.size() > 0) ? listaProductos.get(0) : null;
        
        session.getTransaction().commit();
        session.close();
        
        return producto_TipoProducto;
    }
    
    public Producto_TipoProducto getProducto_TipoProductoPorNombreProducto(String nombreProducto) {
        ArrayList <Producto_TipoProducto> listaProductos;
        Producto_TipoProducto producto_TipoProducto = new Producto_TipoProducto();
        
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery("select top 1 * from Producto as p\n" +
                "inner join TipoProducto as tp " +
                "on tp.idTipoProducto = p.fk_idTIpoProducto "
                + " where p.nombreProducto= :nombreProducto").setResultTransformer(Transformers.aliasToBean(Producto_TipoProducto.class));
        query.setParameter("nombreProducto", nombreProducto);
        
        session.beginTransaction();
        
        listaProductos = (ArrayList<Producto_TipoProducto>) query.list();
        
        producto_TipoProducto = (listaProductos.size() > 0) ? listaProductos.get(0) : null;
        
        session.getTransaction().commit();
        session.close();
        
        return producto_TipoProducto;
    }

    public Boolean registrarInstruccionesElaboracion(ArrayList<Producto_Formula_ProductoFormula> listaProducto_Formula_ProductoFormula, ArrayList<Producto_Preparacion_PreparacionProducto> listaProducto_Preparacion_PreparacionProducto) {
        Boolean resultado = false;
        Session sessionFormulaBase = HibernateUtil.getSessionFactory().openSession();
        
        //Formula
        Integer idFormulaInserted = 0;
        
        Query queryFormulaBase;
        queryFormulaBase = sessionFormulaBase.createSQLQuery(
                "insert into Formula (nombreFormula) output inserted.idFormula values (:nombreFormula)");
        queryFormulaBase.setParameter("nombreFormula", listaProducto_Formula_ProductoFormula.get(0).getNombreFormula());

        sessionFormulaBase.beginTransaction();

        try {
            System.out.println("por grabar formula");
            idFormulaInserted = Integer.parseInt(queryFormulaBase.list().get(0).toString());
            System.out.println("por grabar");
            resultado = true;
        } catch (HibernateException e) {
            System.out.println("HibernateException: "+e);
            resultado = false;
        }
        sessionFormulaBase.getTransaction().commit();
        sessionFormulaBase.close();
        
        //Elementos Formula
        for (Producto_Formula_ProductoFormula producto_Formula_ProductoFormula : listaProducto_Formula_ProductoFormula) {
            Session sessionFormulas = HibernateUtil.getSessionFactory().openSession();
            Query queryFormula;
            queryFormula = sessionFormulas.createSQLQuery(
                    "insert into ProductoFormula (fk_idProductoElaborado,fk_idProductoInsumo,fk_idFormula,porcentaje)\n" +
                    "values(:fk_idProductoElaborado,:fk_idProductoInsumo,:fk_idFormula,:porcentaje)");
            queryFormula.setParameter("fk_idProductoElaborado", producto_Formula_ProductoFormula.getFk_idProductoElaborado());
            queryFormula.setParameter("fk_idProductoInsumo", producto_Formula_ProductoFormula.getFk_idProductoInsumo());
            queryFormula.setParameter("fk_idFormula", idFormulaInserted);
            queryFormula.setParameter("porcentaje", producto_Formula_ProductoFormula.getPorcentaje());
            System.out.println("----------------***------------");
                    
            System.out.println(producto_Formula_ProductoFormula.getFk_idProductoElaborado());
            System.out.println(producto_Formula_ProductoFormula.getFk_idProductoInsumo());
            System.out.println(idFormulaInserted);
            System.out.println(producto_Formula_ProductoFormula.getPorcentaje());
            
            sessionFormulas.beginTransaction();

            try {
                System.out.println("por grabar");
                queryFormula.executeUpdate();
                System.out.println("por grabar");
                resultado = true;
            } catch (HibernateException e) {
                System.out.println("HibernateException: "+e);
                resultado = false;
            }
            sessionFormulas.getTransaction().commit();
            sessionFormulas.close();
        }
        
        //Preparacion Bases
        Integer idPreparacionInserted = 0;
        
        Session sessionPreparacionBase = HibernateUtil.getSessionFactory().openSession();
        Query queryPreparacionBase;
        queryPreparacionBase = sessionPreparacionBase.createSQLQuery(
                "insert into Preparacion (nombrePreparacion,fechaCreacionPreparacion,detalleAdicionalPreparacion,fk_idProducto) output inserted.idPreparacion"
                + " values (:nombrePreparacion,:fechaCreacionPreparacion,:detalleAdicionalPreparacion,:fk_idProducto)");
        queryPreparacionBase.setParameter("nombrePreparacion", listaProducto_Preparacion_PreparacionProducto.get(0).getNombrePreparacion());
        queryPreparacionBase.setParameter("fechaCreacionPreparacion", listaProducto_Preparacion_PreparacionProducto.get(0).getFechaCreacionPreparacion());
        queryPreparacionBase.setParameter("detalleAdicionalPreparacion", listaProducto_Preparacion_PreparacionProducto.get(0).getDetalleAdicionalPreparacion());
        queryPreparacionBase.setParameter("fk_idProducto", listaProducto_Preparacion_PreparacionProducto.get(0).getIdProducto());

        System.out.println("fecha : "+listaProducto_Preparacion_PreparacionProducto.get(0).getFechaCreacionPreparacion());
        sessionPreparacionBase.beginTransaction();

        try {
            System.out.println("por grabar preparacion");
            idPreparacionInserted = Integer.parseInt(queryPreparacionBase.list().get(0).toString());
            System.out.println("por grabar");
            resultado = true;
        } catch (HibernateException e) {
            System.out.println("HibernateException: "+e);
            resultado = false;
        }
        sessionPreparacionBase.getTransaction().commit();
        sessionPreparacionBase.close();
        
        //Elementos Instrucciones
        for (Producto_Preparacion_PreparacionProducto producto_Preparacion_PreparacionProducto : listaProducto_Preparacion_PreparacionProducto) {
            Session sessionInstrucciones = HibernateUtil.getSessionFactory().openSession();
            Query queryInstrucciones;
            queryInstrucciones = sessionInstrucciones.createSQLQuery(
                    "insert into Indicacion (orden,detalle,fk_idPreparacion)\n" +
                    "values(:orden,:detalle,:fk_idPreparacion)");
            queryInstrucciones.setParameter("orden", producto_Preparacion_PreparacionProducto.getOrdenIndicacion());
            queryInstrucciones.setParameter("detalle", producto_Preparacion_PreparacionProducto.getDetalleIndicacion());
            queryInstrucciones.setParameter("fk_idPreparacion", idPreparacionInserted);
            System.out.println("----------------***------------prep");
                    
            sessionInstrucciones.beginTransaction();

            try {
                System.out.println("por grabar prep");
                queryInstrucciones.executeUpdate();
                System.out.println("por grabar prep");
                resultado = true;
            } catch (HibernateException e) {
                System.out.println("HibernateException: "+e);
                resultado = false;
            }
            sessionInstrucciones.getTransaction().commit();
            sessionInstrucciones.close();
        }
//        
        System.out.println(resultado);
        return resultado;
    }

    public ArrayList<Producto_Formula_ProductoFormula> getListaFormulaPorProductoID(Integer idProducto) {
        ArrayList <Producto_Formula_ProductoFormula> listaProducto_Formula_ProductoFormula;
        
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery("select * from Producto pro\n" +
            "inner join ProductoFormula profor on profor.fk_idProductoElaborado = pro.idProducto\n" +
            "inner join Formula f on f.idFormula = profor.fk_idFormula\n" +
            "where pro.idProducto = :idProducto").setResultTransformer(Transformers.aliasToBean(Producto_Formula_ProductoFormula.class));
        query.setParameter("idProducto", idProducto);
        
        session.beginTransaction();
        
        listaProducto_Formula_ProductoFormula = (ArrayList<Producto_Formula_ProductoFormula>) query.list();
        
        session.getTransaction().commit();
        session.close();
        
        return listaProducto_Formula_ProductoFormula;
    }
    
    public ArrayList<Producto_Formula_ProductoFormula_Precio> getListaFormulaConPrecionPorProductoID(Integer idProducto) {
        ArrayList <Producto_Formula_ProductoFormula_Precio> listaProducto_Formula_ProductoFormula_Precio;
        
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        Boolean vigentePrecio = true; //precio vigente
        query = session.createSQLQuery("select * from Producto pro\n" +
            "inner join ProductoFormula profor on profor.fk_idProductoElaborado = pro.idProducto\n" +
            "inner join Formula f on f.idFormula = profor.fk_idFormula\n" +
            "inner join Precio pre on pre.fk_idProducto = pro.idProducto\n" +
            "where pro.idProducto = :idProducto\n"+
            "and pre.vigentePrecio = :vigentePrecio").setResultTransformer(Transformers.aliasToBean(Producto_Formula_ProductoFormula_Precio.class));
        query.setParameter("idProducto", idProducto);
        query.setParameter("vigentePrecio", vigentePrecio);
        
        session.beginTransaction();
        
        listaProducto_Formula_ProductoFormula_Precio = (ArrayList<Producto_Formula_ProductoFormula_Precio>) query.list();
        
        session.getTransaction().commit();
        session.close();
        
        return listaProducto_Formula_ProductoFormula_Precio;
    }

    public ArrayList<Producto_Preparacion_PreparacionProducto> getListaPreparacionPorProductoID(Integer idProducto) {
        ArrayList <Producto_Preparacion_PreparacionProducto> listaProducto_Preparacion_PreparacionProducto;
        System.out.println("ProductoDBA, ID: " + idProducto);
        Session currentSession = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = currentSession.createSQLQuery("select * from Producto pro\n" +
            "inner join Preparacion pre on pre.fk_idProducto = pro.idProducto\n" +
            "inner join Indicacion ind on ind.fk_idPreparacionIndicacion = pre.idPreparacion\n" +
            "where pro.idProducto = :idProducto").setResultTransformer(Transformers.aliasToBean(Producto_Preparacion_PreparacionProducto.class));
        query.setParameter("idProducto", idProducto);
        
        currentSession.beginTransaction();
        
        listaProducto_Preparacion_PreparacionProducto = (ArrayList<Producto_Preparacion_PreparacionProducto>) query.list();
        System.out.println("Tamaño lista de indicaciones: " + listaProducto_Preparacion_PreparacionProducto.size());
        currentSession.getTransaction().commit();
        currentSession.close();
        
        return listaProducto_Preparacion_PreparacionProducto;
    }

    public String getNombreProductoPorId(Integer idProducto) {
        String nombreProductos = "";
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery("select nombreProducto from Producto as p\n"
                + " where p.idProducto = :idProducto").setResultTransformer(Transformers.aliasToBean(Producto.class));
        query.setParameter("idProducto", idProducto);
        
        session.beginTransaction();
        
        ArrayList<Producto> listaProducto = (ArrayList<Producto>) query.list();
        nombreProductos = listaProducto.get(0).getNombreProducto();
        
        session.getTransaction().commit();
        session.close();
        
        return nombreProductos;
    }

    public Boolean actualizarInstruccionesElaboracion(ArrayList<Producto_Formula_ProductoFormula> listaProducto_Formula_ProductoFormula, ArrayList<Producto_Preparacion_PreparacionProducto> listaProducto_Preparacion_PreparacionProducto) {
        Boolean resultado = false; 
        
        //recupera registros existentes
        ArrayList<Producto_Formula_ProductoFormula> listaProductoFormulaExistentes = getListaFormulaPorProductoID(listaProducto_Formula_ProductoFormula.get(0).getIdProducto());
        ArrayList<Producto_Preparacion_PreparacionProducto> listaProductoPreparacionExistentes = getListaPreparacionPorProductoID(listaProducto_Preparacion_PreparacionProducto.get(0).getIdProducto());
        
        //elimina registros existentes
        System.out.println("eliminando...");
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery("delete ProductoFormula where fk_idProductoElaborado = :idProductoFormula\n" +
            "delete Formula where idFormula = :idFormula\n" +
            "delete Indicacion where fk_idPreparacion = :idPreparacion\n" +
            "delete Preparacion where fk_idProducto = :idProductoPreparacion");
        query.setParameter("idProductoFormula", listaProductoFormulaExistentes.get(0).getIdProducto());
        query.setParameter("idFormula", listaProductoFormulaExistentes.get(0).getIdFormula());
        
        query.setParameter("idPreparacion", listaProductoPreparacionExistentes.get(0).getIdPreparacion());
        query.setParameter("idProductoPreparacion", listaProductoPreparacionExistentes.get(0).getIdProducto());
        
        session.beginTransaction();
        
        query.executeUpdate();
        
        session.getTransaction().commit();
        session.close();
        System.out.println("eliminado");
        //registra
        System.out.println("regirstrando...");
        resultado = registrarInstruccionesElaboracion(listaProducto_Formula_ProductoFormula, listaProducto_Preparacion_PreparacionProducto);
        System.out.println("registrado");
        return resultado;
    }
    
    public static void registrarServicio(Persona_TipoPersona cliente,Integer personalAtencionID, Timestamp fechaInicioVenta, ArrayList<Servicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida> listaServicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida) {
        session = HibernateUtil.getSessionFactory().openSession();
        Integer idServicio = 0;
        Query query;
        Timestamp fechahorafin = new Timestamp(System.currentTimeMillis());
        query = session.createSQLQuery("Insert into Servicio (horaInicioServicio,horaFinServicio,idPersonaAtendidaServicio,idPersonaEmpleado,fk_idTipoServicio)\n"
                + "output inserted.idServicio "
                + "values(:horaInicioServicio,:horaFinServicio,:idPersonaAtendidaServicio,:idPersonaEmpleado,:fk_idTipoServicio)");
        query.setParameter("horaInicioServicio", fechaInicioVenta);
        query.setParameter("horaFinServicio", fechahorafin);
        query.setParameter("idPersonaAtendidaServicio", cliente.getIdPersona());
        query.setParameter("idPersonaEmpleado", personalAtencionID);
        query.setParameter("fk_idTipoServicio", 2);//venta
        
        session.beginTransaction();
        
        idServicio = (Integer) query.list().get(0);
        
        session.getTransaction().commit();
        session.close();
        
        //Descuenta unidades dsiponible
        for (Servicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida servicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida : listaServicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida) {
            //calcula cantidad actual y recuperar seccion lote fuente
            Double cantidadDescontada = 0.0;
            Session sessionAlamcen = HibernateUtil.getSessionFactory().openSession();
            Query queryAlmacen;
            queryAlmacen = sessionAlamcen.createSQLQuery(
                    "select Top 1 * from ProductoSeccion\n" +
                    "where fk_idProducto = :idProducto\n" +
                    "and cantidadProductoSeccion >= :cantidadProductoSeccion\n" +
                    "order by cantidadProductoSeccion asc").setResultTransformer(Transformers.aliasToBean(ProductoSeccion.class));;
            queryAlmacen.setParameter("cantidadProductoSeccion", servicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida.getCantidadProductoSeccion());
            queryAlmacen.setParameter("idProducto", servicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida.getIdProducto());
            
            ArrayList<ProductoSeccion> listaProdSec = new ArrayList();
            ProductoSeccion productoSeccionContenedor = new ProductoSeccion();
            
            sessionAlamcen.beginTransaction();
            
            listaProdSec = (ArrayList<ProductoSeccion>) queryAlmacen.list();
            if(!listaProdSec.isEmpty()){
                productoSeccionContenedor = listaProdSec.get(0);
            }
        
            sessionAlamcen.getTransaction().commit();
            sessionAlamcen.close();
            
            //actualiza cantidad
            Session sessionAlamcenModificacion = HibernateUtil.getSessionFactory().openSession();
            Query queryAlamcenModificacion;
            queryAlamcenModificacion = sessionAlamcenModificacion.createSQLQuery(
                    "update ProductoSeccion \n" +
                    "set cantidadProductoSeccion = :cantidadProductoSeccion\n"+
                    "where idProductoSeccion = :idProductoSeccion");
            cantidadDescontada =  productoSeccionContenedor.getCantidadProductoSeccion() - servicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida.getCantidadProductoSeccion();
            queryAlamcenModificacion.setParameter("cantidadProductoSeccion",cantidadDescontada);
            queryAlamcenModificacion.setParameter("idProductoSeccion", productoSeccionContenedor.getIdProductoSeccion());
            
            sessionAlamcenModificacion.beginTransaction();

            try {
                System.out.println("por grabar");
                queryAlamcenModificacion.executeUpdate();
                System.out.println("por grabar");
            } catch (HibernateException e) {
                System.out.println("HibernateException: "+e);
            }
            sessionAlamcenModificacion.getTransaction().commit();
            sessionAlamcenModificacion.close();
            
            //ServicioProducto
            Session sessionServicioProducto = HibernateUtil.getSessionFactory().openSession();
            Query queryServicioProducto;
            queryServicioProducto = sessionServicioProducto.createSQLQuery(
                    "Insert into ServicioProducto (fk_idServicio,fk_idProductoSeccion,costoTotal,cantidadServicioProducto)\n"
                    + "values(:fk_idServicio,:fk_idProductoSeccion,:costoTotal,:cantidadServicioProducto)");
            queryServicioProducto.setParameter("fk_idServicio", idServicio);
            queryServicioProducto.setParameter("fk_idProductoSeccion", productoSeccionContenedor.getIdProductoSeccion());
            queryServicioProducto.setParameter("costoTotal", servicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida.getCostoTotal());
            queryServicioProducto.setParameter("cantidadServicioProducto", servicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida.getCantidadProductoSeccion());

            sessionServicioProducto.beginTransaction();

            queryServicioProducto.executeUpdate();

            sessionServicioProducto.getTransaction().commit();
            sessionServicioProducto.close();
        }
        
    }

    public static boolean stockDisponible(Integer idProducto,Double CantidadProductoRequerida){
        boolean resultado = false;
        session = HibernateUtil.getSessionFactory().openSession();
        Query queryAlmacen;
        queryAlmacen = session.createSQLQuery(
                "select Top 1 * from ProductoSeccion\n" +
                "where fk_idProducto = :idProducto\n" +
                "and cantidadProductoSeccion >= :cantidadProductoSeccion\n" +
                "order by cantidadProductoSeccion asc").setResultTransformer(Transformers.aliasToBean(ProductoSeccion.class));;
        queryAlmacen.setParameter("cantidadProductoSeccion", CantidadProductoRequerida);
        queryAlmacen.setParameter("idProducto", idProducto);

        ArrayList<ProductoSeccion> listaProdSec = new ArrayList();

        session.beginTransaction();

        listaProdSec = (ArrayList<ProductoSeccion>) queryAlmacen.list();
        if(!listaProdSec.isEmpty()){
            resultado = true;
        }
        else{
            resultado = false;
        }

        session.getTransaction().commit();
        session.close();
        
        return resultado;
    }
    
    public static ArrayList<Servicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida> getListaDeProductosPorIDServicio(Integer idServicio) {
        ArrayList<Servicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida> resultado = new ArrayList();
        session = HibernateUtil.getSessionFactory().openSession();
        Query query;
        query = session.createSQLQuery(
            "select * from Servicio ser\n" +
            "inner join ServicioProducto serpro on serpro.fk_idServicio = ser.idServicio \n" +
            "inner join ProductoSeccion prosec on prosec.idProductoSeccion = serpro.fk_idProductoSeccion\n" +
            "inner join Producto pro on pro.idProducto = prosec.fk_idProducto\n" +
            "inner join UnidadMedida uni on uni.idUnidadMedida = pro.fk_idUnidadMedida\n" +
            "where ser.idServicio = :idServicio").setResultTransformer(Transformers.aliasToBean(Servicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida.class));;
        query.setParameter("idServicio", idServicio);
        
        session.beginTransaction();

        resultado = (ArrayList<Servicio_ServicioProducto_ProductoSeccion_Producto_UnidadMedida>) query.list();
        
        session.getTransaction().commit();
        session.close();
        
        return resultado;
    }

}

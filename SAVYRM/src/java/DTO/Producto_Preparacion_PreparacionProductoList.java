/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import RelationEntities.Producto_Preparacion_PreparacionProducto;
import java.util.List;

/**
 *
 * @author torellana
 */
public class Producto_Preparacion_PreparacionProductoList {
    List<Producto_Preparacion_PreparacionProducto> producto_Preparacion_PreparacionProductoList;

    public List<Producto_Preparacion_PreparacionProducto> getProducto_Preparacion_PreparacionProductoList() {
        return producto_Preparacion_PreparacionProductoList;
    }

    public void setProducto_Preparacion_PreparacionProductoList(List<Producto_Preparacion_PreparacionProducto> producto_Preparacion_PreparacionProductoList) {
        this.producto_Preparacion_PreparacionProductoList = producto_Preparacion_PreparacionProductoList;
    }
}

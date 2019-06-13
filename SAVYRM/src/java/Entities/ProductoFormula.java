package Entities;
// Generated 01-may-2018 23:13:47 by Hibernate Tools 3.6.0



/**
 * ProductoFormula generated by hbm2java
 */
public class ProductoFormula  implements java.io.Serializable {


     private int idProductoFormula;
     private Formula formula;
     private Producto productoByFkIdProductoElaborado;
     private Producto productoByFkIdProductoInsumo;
     private double porcentaje;

    public ProductoFormula() {
    }

    public ProductoFormula(int idProductoFormula, Formula formula, Producto productoByFkIdProductoElaborado, Producto productoByFkIdProductoInsumo, double porcentaje) {
       this.idProductoFormula = idProductoFormula;
       this.formula = formula;
       this.productoByFkIdProductoElaborado = productoByFkIdProductoElaborado;
       this.productoByFkIdProductoInsumo = productoByFkIdProductoInsumo;
       this.porcentaje = porcentaje;
    }
   
    public int getIdProductoFormula() {
        return this.idProductoFormula;
    }
    
    public void setIdProductoFormula(int idProductoFormula) {
        this.idProductoFormula = idProductoFormula;
    }
    public Formula getFormula() {
        return this.formula;
    }
    
    public void setFormula(Formula formula) {
        this.formula = formula;
    }
    public Producto getProductoByFkIdProductoElaborado() {
        return this.productoByFkIdProductoElaborado;
    }
    
    public void setProductoByFkIdProductoElaborado(Producto productoByFkIdProductoElaborado) {
        this.productoByFkIdProductoElaborado = productoByFkIdProductoElaborado;
    }
    public Producto getProductoByFkIdProductoInsumo() {
        return this.productoByFkIdProductoInsumo;
    }
    
    public void setProductoByFkIdProductoInsumo(Producto productoByFkIdProductoInsumo) {
        this.productoByFkIdProductoInsumo = productoByFkIdProductoInsumo;
    }
    public double getPorcentaje() {
        return this.porcentaje;
    }
    
    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }




}



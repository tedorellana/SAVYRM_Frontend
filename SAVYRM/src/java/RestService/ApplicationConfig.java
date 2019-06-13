/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestService;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author torellana
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(RestService.AlmacenResource.class);
        resources.add(RestService.ProductosResource.class);
        resources.add(RestService.SesionResource.class);
        resources.add(RestService.TipoProductoResource.class);
        resources.add(RestService.UnidadMedidaResource.class);
        resources.add(RestService.VentasResource.class);
    }
    
}

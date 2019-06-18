angular.module('angularRoutingApp').controller('inventarioController', function ($scope, $http, $sessionStorage, $window, config) {
    //Removing slider
    var idCurrentUser = $sessionStorage.currentUser;
    var sessionLoggedIn = angular.isDefined(idCurrentUser);
      
    $scope.onLoadInventarioController = function(){
        if(!sessionLoggedIn){
            $window.location.href = config.baseUrl;
        }
        else{
            $scope.getAllAlmaces();
        }
    };
    
    var almacenSeleccionadoAS;
    
    var listaAlmacenes=[];
    $scope.getAllAlmaces = function(){
        $http({
            method: 'GET',
            url: 'http://localhost:8080/Almacen/GetAllAlmacen',
            data: {
            }
          }).then(function successCallback(response) {
              listaAlmacenes = response.data;
              $scope.almacenes = listaAlmacenes;
            }, function errorCallback(response) {
              alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
            });        
    };
    
    var almacenesSeleccionados = [];
    $scope.obtenerSeccionesPorAlmacenesSeleccionados = function() {
        almacenesSeleccionados = [];
        angular.forEach($scope.almacenes, function(almacen) {
            if (almacen.selected) {
                almacenesSeleccionados.push(almacen.idAlmacen);
            }
        });
        if(almacenesSeleccionados.length !== 0){
          $scope.getSeccionesPorAlmacen(almacenesSeleccionados);  
        }
        else{
            $scope.listaSecciones = [];
        }
    };  
    
    $scope.mostrarAlmacenes = true;
    $scope.mostrarSecciones = false;
    $scope.mostrarInventario = false;
    $scope.getSeccionesPorAlmacen = function(almacenesSeleccionados){
        $http({
            method: 'POST',
            url: 'http://localhost:8080/Seccion/GetAllSeccionByAlmacen',
            data: { idAlmacenes : almacenesSeleccionados
            }
          }).then(function successCallback(response) {
              $scope.listaSecciones = response.data;
              $scope.mostrarAlmacenes = true;
              $scope.mostrarSecciones = true;
              $scope.mostrarInventario = false;
              almacenSeleccionadoAS = almacenesSeleccionados;
            }, function errorCallback(response) {
                alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
            });
    };
    
    var seccionesSeleccionadas = [];
    $scope.obtenerInventarioPorSeccionesSeleccionados = function() {
        seccionesSeleccionadas = [];
        angular.forEach($scope.listaSecciones, function(seccion) {
            if (seccion.selected) {
                seccionesSeleccionadas.push(seccion.idSeccion);
            }
        });
        if(seccionesSeleccionadas.length !== 0){
          $scope.getInvenarioPorSeccion(seccionesSeleccionadas);  
        }
        else{
            
        }
    };
    
    var inventarioOriginal;
    $scope.getInvenarioPorSeccion = function(seccionesSeleccionadas){
        $http({
            method: 'POST',
            url: 'http://localhost:8080/ProductoSeccion/GetAllInventarioBySeccion',
            data: { idSecciones : seccionesSeleccionadas
            }
          }).then(function successCallback(response) {
              inventarioOriginal = response.data;
              $scope.inventario = inventarioOriginal;
              $scope.mostrarAlmacenes = false;
              $scope.mostrarSecciones = false;
              $scope.mostrarInventario = true;
            }, function errorCallback(response) {
                alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
            });
    };
    
    var productoInventarioSeleccionado;
    $scope.getDetallePorIDProductoSeccion = function(event){
        productoInventarioSeleccionado = event.target.value;
        
        var inventarioSeleccionado = $scope.inventario;

        for (var i = 0; i<inventarioSeleccionado.length ;i++) {
            if(inventarioSeleccionado[i].idProductoSeccion == productoInventarioSeleccionado){
                $scope.idAlmacenItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].seccion.almacen.idAlmacen;
                $scope.nombreAlmacenItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].seccion.almacen.nombreAlmacen;
                $scope.direccionItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].seccion.almacen.direccion;
                $scope.capacidadItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].seccion.almacen.capacidad;

                //Seccion
                $scope.idSeccionItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].seccion.idSeccion;
                $scope.codigoSeccionItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].seccion.codigoSeccion;
                $scope.capacidadSeccionInventarioSeleccionadoDetalle = inventarioSeleccionado[i].seccion.capacidadSeccion;
                $scope.detalleItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].seccion.detalle;
                $scope.fk_idAlmacenItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].seccion.fk_idAlmacen;

                //ProductoSeccion
                $scope.idProductoSeccionInventarioSeleccionadoDetalle = inventarioSeleccionado[i].idProductoSeccion;
                $scope.cantidadProductoSeccionItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].cantidadProductoSeccion;
                $scope.fechaIngresoItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].fechaIngreso;
                $scope.fk_idLoteItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].fk_idLote;
                $scope.fk_idProductoItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].fk_idProducto;
                $scope.fk_idSeccionItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].fk_idSeccion;

                //Lote
                $scope.idLoteItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].lote.idLote;
                $scope.codigoLoteItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].lote.codigoLote;
                $scope.costoLoteItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].lote.costoLote;
                $scope.cantidaUnidadesLoteItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].lote.cantidaUnidadesLote;
                $scope.fechaCaducacionLoteItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].lote.fechaCaducacionLote;

                //Producto
                $scope.idProductoItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].producto.idProducto;
                $scope.nombreProductoItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].producto.nombreProducto;
                $scope.codigoProductoItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].producto.codigoProducto;
                $scope.estadoProductoItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].producto.estadoProducto;
                $scope.fk_idTIpoProductoItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].producto.fk_idTIpoProducto;
                $scope.fk_idUnidadMedidaItemInventarioSeleccionadoDetalle = inventarioSeleccionado[i].producto.fk_idUnidadMedida;
            }
        }
        
        $scope.itemSeleccionado = "";
    };
    
    $scope.volverASeleccionarSeccionesyAlmacenes = function(){
        $scope.mostrarAlmacenes = true;
        $scope.mostrarSecciones = true;
        $scope.mostrarInventario = false;
    };
    //TODO:evaluar busqueda y migrar a case en lugar de if
    $scope.buscarItemEnInventario = function(){
        var inventarioFiltrado = [];
        var textoABuscar = $scope.buscarInventarioSeleccionado !== "" ? ($scope.buscarInventarioSeleccionado+"").toUpperCase() : null ;
        if(textoABuscar !== null)
        {
            for (var i = 0; i<inventarioOriginal.length; i++) {
            //pendiente agregar a la busquead parametro estadoProducto $scope.estadoProducto.toUpperCase()
                if ((inventarioOriginal[i].nombreAlmacen+"").toUpperCase().includes(textoABuscar)
                    || (inventarioOriginal[i].direccion+"").toUpperCase().includes(textoABuscar)
                    || (inventarioOriginal[i].capacidad+"").includes(textoABuscar)
                    || (inventarioOriginal[i].codigoSeccion+"").toUpperCase().includes(textoABuscar)
                    || (inventarioOriginal[i].capacidadSeccion+"").includes(textoABuscar) 
                    || (inventarioOriginal[i].detalle+"").toUpperCase().includes(textoABuscar)
                    || (inventarioOriginal[i].cantidadProductoSeccion+"").includes(textoABuscar) 
                    || (inventarioOriginal[i].fechaIngreso+"").toUpperCase().includes(textoABuscar)
                    || (inventarioOriginal[i].codigoLote+"").toUpperCase().includes(textoABuscar)
                    || (inventarioOriginal[i].costoLote+"").includes(textoABuscar)
                    || (inventarioOriginal[i].cantidaUnidadesLote+"").includes(textoABuscar)
                    || (inventarioOriginal[i].fechaCaducacionLote+"").toUpperCase().includes(textoABuscar)
                    || (inventarioOriginal[i].nombreProducto+"").toUpperCase().includes(textoABuscar)
                    || (inventarioOriginal[i].codigoProducto+"").toUpperCase().includes(textoABuscar)
                    || (inventarioOriginal[i].codigoProducto+"").toUpperCase().includes(textoABuscar)){
                    $scope.detalleBusquedaEnInventario = "";
                    inventarioFiltrado.push(inventarioOriginal[i]);
                    $scope.inventario = inventarioFiltrado;
                }
                else{
                    $scope.detalleBusquedaEnInventario = "El texo ingresado no tiene coincidencias en el inventario.";
                }
            }
        }
        else{
            //alert("No hay coincidencias con el texto ingresado");
        }
        
    };
    
    $scope.TesteandoDPIM = function(){
      $scope.detalleItemInventarioSeleccionadoDetalle;
    };
    
}); 
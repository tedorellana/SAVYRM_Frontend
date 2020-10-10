angular.module('angularRoutingApp').controller('ventasController', function ($scope, $http, $rootScope) {

    // Variables
    var listaServicio = [];
    var listaProductosParaVenta = [];
    var carritoDeCompras = []; // Almacena objetos a comprar
    var productoSeleccionado;
    var precioTotalAcumulado = 0;
    
    
    $scope.message = 'VENTAS';
    $scope.especial = 'VENTAS';
    
    $scope.borrarSlider = function(){
        $scope.especial = 'presionado';
        var iEl = angular.element( document.querySelector( '#slider' ) );
        iEl.remove();
    };
    
    $scope.getVentas = function(){
        $scope.borrarSlider();
        $http({
            method: 'POST',
            url: 'http://localhost:8080/Servicio/GetVentasPorPersona',
            data: {
            }
          }).then(function successCallback(response) {
              listaServicio = response.data;
              
              $scope.ventas = listaServicio;
            }, function errorCallback(response) {
              alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
            });        
    };
    
    $scope.getDetallesPorServicio = function(){
    };
    
    // Obtiene productos para la venta
    $scope.getProductosParaVenta = function(){
        $scope.borrarSlider();
        $http({
            method: 'GET',
            url: 'http://localhost:8080/Producto/GetAllProductosParaVenta',
            data: {
            }
        }).then(function successCallback(response) {
            listaProductosParaVenta = response.data;
            $scope.productosParaVenta = listaProductosParaVenta;
            }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });     
    };
    
    // Establece ID en el botón para agregar al carrito
    $scope.EstablecerCantidadParaAgregar = function(event) {
        productoSeleccionado = JSON.parse(event.target.value);
        $scope.ProductoParaAgregar = productoSeleccionado;
        $scope.UnidadMedidaProductoParaAgregar = productoSeleccionado.abreviacion;
        $scope.CantidadProductoParaAgregar = 1;
        $scope.CalcularPrecio();
    };
    
    $scope.CalcularPrecio = function() {
        $scope.PrecioDelProductoParaAgregar = productoSeleccionado.unitarioPrecio * $scope.CantidadProductoParaAgregar;
    };
    
    // Agrega la cantidad indicada al carrito de compras
    $scope.AgregarACarrito = function(event){
        let dataRecibida = JSON.parse(event.target.value);
        var elementoCarrito = {
            codigoProducto : dataRecibida.codigoProducto,
            nombreProducto : dataRecibida.nombreProducto,
            idProductoSeccion : dataRecibida.idProductoSeccion,
            unidadMedida : dataRecibida.abreviacion,
            cantidad : $scope.CantidadProductoParaAgregar,
            precioUnitario : productoSeleccionado.unitarioPrecio,
            precioTotalProducto : $scope.PrecioDelProductoParaAgregar
        };
        
        precioTotalAcumulado += elementoCarrito.precioTotalProducto;
        $scope.PrecioTotal = precioTotalAcumulado;
        
        carritoDeCompras.push(elementoCarrito);
        $scope.carrito = carritoDeCompras;
    };
    
    // Obtiene productos para la venta
    $scope.registrarVenta = function(){
        alert("registrando venta ");
        $http({
            method: 'POST',
            url: 'http://localhost:8080/Venta/RegistrarVenta',
            data: { carritoDeCompras
            }
        }).then(function successCallback(response) {
            alert("Venta realizada!.");
            }, function errorCallback(response) {
            //alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });     
    };
    
      
});


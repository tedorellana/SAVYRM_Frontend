angular.module('angularRoutingApp').controller('ventasController', function ($scope, $http, $rootScope) {

    // Variables
    var listaServicio=[] ;
    var listaProductosParaVenta=[] ;
    var carritoDeCompras = [] // Almacena objetos a comprar
    

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
    $scope.EstablecerCantidadParaAgregar = function(event)
    {
        let dataRecibida = JSON.parse(event.target.value);
        $scope.ProductoParaAgregar = dataRecibida;
        $scope.UnidadMedidaProductoParaAgregar = dataRecibida.producto.unidadMedida.abreviacion;
        $scope.CantidadProductoParaAgregar = 0;
        //$scope.CantidadProductoAgregar = event.target.value; // TODO: pendiente agregar precio, cantidad e unidad de medida
    };
    
    // Agrega la cantidad indicada al carrito de compras
    $scope.AgregarACarrito = function(event){
        let dataRecibida = JSON.parse(event.target.value);
        alert($scope.CantidadProductoParaAgregar);
        var elementoCarrito = {
            idProducto:dataRecibida.producto.idProducto,
            codigoProducto : dataRecibida.producto.codigoProducto,
            nombreProducto : dataRecibida.producto.nombreProducto,
            idProductoSeccion : dataRecibida.idProductoSeccion,
            unidadMedida : dataRecibida.producto.unidadMedida.abreviacion,
            cantidad:$scope.CantidadProductoParaAgregar
        };
        
        carritoDeCompras.push(elementoCarrito);
        $scope.carrito = carritoDeCompras;
    };
    
    
      
});


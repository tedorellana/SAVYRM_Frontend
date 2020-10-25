angular.module('angularRoutingApp').controller('ventasController', function ($scope, $http, $sessionStorage, $rootScope) {

    // Variables
    var listaServicio = [];
    var listaProductosParaVenta = [];
    var carritoDeCompras = []; // Almacena objetos a comprar
    var productoSeleccionado;
    var precioTotalAcumulado = 0;
    $scope.PrecioTotal = "00.00";
    $scope.shoppingCarEmpty = true; // used to hide table with car items when is empty
    
    $scope.message = 'VENTAS';
    $scope.especial = 'VENTAS';
    
    $scope.FormatDate = function(date) {
        date = date.getFullYear() + "-" + 
        parseInt(date.getMonth() + 1)  + "-" +
        date.getDay() + " " +
        date.getHours() + ":" +
        date.getMinutes() + ":" +
        date.getSeconds();
        return date;
    };

    // get the beginning attetion datetime
    var begginningDateTime = new Date();
    begginningDateTime = $scope.FormatDate(begginningDateTime);

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

    // Gets a detail of an specific sale
    $scope.GetServiceDetail = function(event){
        var serviceSelected = JSON.parse(event.currentTarget.value);
        var idServiceSelected = serviceSelected.servicio.idServicio;

        $http({
            method: 'POST',
            url: 'http://localhost:8080/Servicio/GetVentasDetail',
            data: {
                idServiceSelected
            }
        }).then(function successCallback(response) {
            $scope.ventaDetail = response.data;
            $scope.serviceSelectedDetail = serviceSelected;
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
        productoSeleccionado = JSON.parse(event.currentTarget.value);
        $scope.ProductoParaAgregar = productoSeleccionado;
        $scope.UnidadMedidaProductoParaAgregar = productoSeleccionado.abreviacion;
        $scope.CantidadProductoParaAgregar = 1;

        $scope.FechaEntregaDelProductoParaAgregar = $scope.FormatDate(new Date());
        $scope.EntregaInmediata = true;

        if (productoSeleccionado.cantidadProductoSeccion == 0) {
            $scope.EntregaInmediata = false;
            var fakeDeliveryDate = new Date();
            fakeDeliveryDate.setDate(fakeDeliveryDate.getDate() + 3);
            
            $scope.FechaEntregaDelProductoParaAgregar = $scope.FormatDate(fakeDeliveryDate);
        }
        
        $scope.CalcularPrecio();
    };
    
    $scope.CalcularPrecio = function() {
        $scope.PrecioDelProductoParaAgregar = productoSeleccionado.unitarioPrecio * $scope.CantidadProductoParaAgregar;
    };
    
    // Agrega la cantidad indicada al carrito de compras
    $scope.AgregarACarrito = function(event){
        let dataRecibida = JSON.parse(event.currentTarget.value);
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

        $scope.shoppingCarEmpty = $scope.carrito == undefined || $scope.carrito.length <= 0;
    };
    
    // Obtiene productos para la venta
    $scope.registrarVenta = function(){
        var detallesServicio = {
            idEmpleado : $sessionStorage.currentUser,
            idCliente : $sessionStorage.currentUser, // TODO: pending to add customer.
            dateTimeServiceBegin : begginningDateTime
        }
        
        alert("registrando venta ");
        $http({
            method: 'POST',
            url: 'http://localhost:8080/Venta/RegistrarVenta',
            data: { 
                carritoDeCompras,
                detallesServicio
            }
        }).then(function successCallback(response) {
            alert("Venta realizada!.");
            carritoDeCompras = null;
            $scope.carrito = carritoDeCompras;
            }, function errorCallback(response) {
            //alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });     
    };
});


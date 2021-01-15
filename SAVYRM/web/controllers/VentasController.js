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
        date.getDate() + " " +
        date.getHours() + ":" +
        date.getMinutes() + ":" +
        date.getSeconds();
        return date;
    };

    // Get total amount of the seected sale
    $scope.GetTotalCost = function(ventas) {
        var totalCost = 0;
        ventas.forEach(x => totalCost += x.costoTotal);
        return totalCost;
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
        var idServiceSelected = serviceSelected.idServicio;
        $http({
            method: 'POST',
            url: 'http://localhost:8080/Servicio/GetVentasDetail',
            data: {
                idServiceSelected
            }
        }).then(function successCallback(response) {
            $scope.ventaDetail = response.data;
            $scope.serviceSelectedDetail = serviceSelected;
            $scope.TotalCostPerSelectedSale = $scope.GetTotalCost(response.data);
        }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        }); 
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
            precioTotalProducto : $scope.PrecioDelProductoParaAgregar,
            entregado : $scope.EntregaInmediata,
            fechaEntrega : $scope.FechaEntregaDelProductoParaAgregar
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
        
        alert("registrando venta " + carritoDeCompras.length + " Detalles servicio: " + detallesServicio);

        if (carritoDeCompras.length == 0)
        {
            alert("Ups! El carrito de compras se encuentra vacío. Debe seleccionar por lo menos un producto.");
            return;
        }

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

    // Mark the product as delivered
    $scope.DeliverProduct = function(event){
        let saleSelected = JSON.parse(event.currentTarget.value)
        alert("DeliverProduct -> " + saleSelected.productoSeccion.producto.nombreProducto);
        // var detallesServicio = {
        //     idEmpleado : $sessionStorage.currentUser,
        //     idCliente : $sessionStorage.currentUser,
        //     dateTimeServiceBegin : begginningDateTime
        // }
        
        
        // $http({
        //     method: 'POST',
        //     url: 'http://localhost:8080/Venta/RegistrarVenta',
        //     data: { 
        //         carritoDeCompras,
        //         detallesServicio
        //     }
        // }).then(function successCallback(response) {
        //     alert("Venta realizada!.");
        //     carritoDeCompras = null;
        //     $scope.carrito = carritoDeCompras;
        //     }, function errorCallback(response) {
        //     //alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        // });
    };

    
});


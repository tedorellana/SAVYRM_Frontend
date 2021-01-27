angular.module('angularRoutingApp').controller('ordenCompraController', function ($scope, $http, $sessionStorage, $rootScope) {
    let PRODUCT_ADDED_TO_ORDER_MSG = "¡Producto agregado!";
    $scope.warningModal = null;
    $scope.precioTotalOrdencompraNewOrder = 0;

    var productosNewOrderList = [];

    // Populate the selects in modal new order
    $scope.InitializeModalNewOrder = function() {
        console.log("InitializeModalNewOrder()");
        
        $scope.GetAllProductosActivados();
        
        $scope.GetAllProviders();
        
        productosNewOrderList = [];
        $scope.productsNewOrder = productosNewOrderList;
        $scope.precioTotalOrdencompraNewOrder = 0;
        $scope.warningModal = null;
    };
    
    // Get all active productos
    $scope.GetAllProductosActivados = function(){
        console.log("GetAllProductosActivados()");

        $http({
            method: 'POST',
            url: 'http://localhost:8080/Producto/GetAllProductoWhereEstadoIsActivado',
            data: {
                activado : 1
            }
        }).then(function successCallback(response) {
            $scope.activeProducts = response.data;
        }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };

    // Get all the provider in the database
    $scope.GetAllProviders = function() {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/Persona/GetAllProviders',
            data: {} 
        }).then(function successCallback(response) {
            $scope.personProvider = response.data;
            }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };

    // Format date to show in screen without the time
    $scope.FormatDateSouthAmericaWithoutTime = function(date) {

        if (date == null) {
            console.log("NULL value detected");
            return null;
        }

        let formatedDate = new Date(date);
        
        formatedDate = formatedDate.getDate()  + "-" +
            parseInt(formatedDate.getMonth() + 1) + "-" +
            formatedDate.getFullYear();
        return formatedDate;
    };
    
    // Get all purchase orders
    $scope.OrdenesDeCompra = function() {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/OrdenCompraProducto/GetAllOrders',
            data: {
            }
        }).then(function successCallback(response) {
            $scope.orders = response.data;
            }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };

    // Get all purchase orders
    $scope.GetOrdersDetail = function(event) {
        console.log("GetOrdersDetail() " + JSON.stringify(event.currentTarget.value));
        let orderSelected = JSON.parse(event.currentTarget.value);
        $scope.orderSelected = orderSelected;
        
        $http({
            method: 'POST',
            url: 'http://localhost:8080/OrdenCompraProducto/FindOrderProductoByOrderId',
            data: orderSelected.idOrdencompra
        }).then(function successCallback(response) {
            $scope.OrderDetailSelected = response.data;
            }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };


    $scope.AgregarProductoOrden = function() {
        $scope.warningModal = null;

        console.log("AgregarProductoOrden() ");

        let productElementNewOrder = {
            proveedorId: $scope.providerNewOrder,
            productId: $scope.productNewOrder.idProducto,
            nombreProducto: $scope.productNewOrder.nombreProducto,
            unidadMedida: $scope.productNewOrder.unidadMedida.abreviacion,
            fechaEntregaPrevistaOrdenCompraProducto: $scope.FormatDateWithTime($scope.deteEntregaPrevistaNewOrder, $scope.timeEntregaPrevistaNewOrder), // pending time
            cantidadOrdenCompraProducto: $scope.cantidadProductoNewOrder,
            precioOrdenCompraProducto: $scope.precioProductoNewOrder
        };

        $scope.precioTotalOrdencompraNewOrder += productElementNewOrder.precioOrdenCompraProducto;

        console.log(productElementNewOrder);
        productosNewOrderList.push(productElementNewOrder);
        $scope.productsNewOrder = productosNewOrderList;

        $scope.warningModal = PRODUCT_ADDED_TO_ORDER_MSG;
    }

    // Format the date to concat it with the user name
    $scope.FormatDateWithTime = function(date, time) {
        let dateTemp = new Date(date);
        let timeTemp = new Date(time);
        
        let dateResult = dateTemp.getFullYear() + "-" +
            parseInt(dateTemp.getMonth() + 1)  + "-" +
            dateTemp.getDate() + " " +
            timeTemp.getHours() + ":" +
            timeTemp.getMinutes() + ":" +
            timeTemp.getSeconds();

        // 2021-01-24 01:31:02.000000 expected
        return dateResult;
    };

    $scope.FormatDateSouthAmerica = function(date) {

        if (date == null) {
            console.log("NULL value detected");
            return null;
        }

        let formatedDate = new Date(date);
        
        formatedDate = formatedDate.getDate()  + "-" +
            parseInt(formatedDate.getMonth() + 1) + "-" +
            formatedDate.getFullYear() + "  " + 
            formatedDate.getHours() + ":" +
            formatedDate.getMinutes() + ":" +
            formatedDate.getSeconds();

        return formatedDate;
    };

    // Get all purchase orders
    $scope.ProcesarOrden = function() {
        console.log("ProcesarOrden() ");
        // let orderSelected = JSON.parse(event.currentTarget.value);
        // $scope.orderSelected = orderSelected;
        
        // $http({
        //     method: 'POST',
        //     url: 'http://localhost:8080/OrdenCompraProducto/FindOrderProductoByOrderId',
        //     data: orderSelected.idOrdencompra
        // }).then(function successCallback(response) {
        //     $scope.OrderDetailSelected = response.data;
        //     }, function errorCallback(response) {
        //     alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        // });
    };


});


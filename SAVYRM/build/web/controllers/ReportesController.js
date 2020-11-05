angular.module('angularRoutingApp').controller('reportesController', function ($scope, $http, $sessionStorage, $rootScope) {
    
    // // Variables
    // var listaServicio = [];
    // var listaProductosParaVenta = [];
    // var carritoDeCompras = []; // Almacena objetos a comprar
    // var productoSeleccionado;
    // var precioTotalAcumulado = 0;
    // $scope.PrecioTotal = "00.00";
    // $scope.shoppingCarEmpty = true; // used to hide table with car items when is empty
    
    // // Obtiene productos para la venta
    // $scope.registrarVenta = function(){
    //     var detallesServicio = {
    //         idEmpleado : $sessionStorage.currentUser,
    //         idCliente : $sessionStorage.currentUser, // TODO: pending to add customer.
    //         dateTimeServiceBegin : begginningDateTime
    //     }
        
    //     alert("registrando venta ");
    //     $http({
    //         method: 'POST',
    //         url: 'http://localhost:8080/Venta/RegistrarVenta',
    //         data: { 
    //             carritoDeCompras,
    //             detallesServicio
    //         }
    //     }).then(function successCallback(response) {
    //         alert("Venta realizada!.");
    //         carritoDeCompras = null;
    //         $scope.carrito = carritoDeCompras;
    //         }, function errorCallback(response) {
    //         //alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
    //     });     
    // };

    $scope.GetSalesReport = function(){
        // Construct options first and then pass it as a parameter
        var options1 = {
            animationEnabled: true,
            title: {
                text: "Chart inside a jQuery Resizable Element"
            },
            data: [{
                type: "column", //change it to line, area, bar, pie, etc
                legendText: "Try Resizing with the handle to the bottom right",
                showInLegend: true,
                dataPoints: [
                    { label: "Iraq", y: 100 },
                    { y: 600 },
                    { y: 400 },
                    { y: 120 },
                    { y: 190 },
                    { y: 300 },
                    { y: 900 },
                    { y: 10 },
                    { y: 10 },
                    { y: 10 },
                    { y: 10 },
                    { y: 10 },
                    { y: 22 },
                    { y: 400 }
                    ]
                }]
        };
    
        $("#resizable").resizable({
            create: function (event, ui) {
                //Create chart.
                $("#chartContainer1").CanvasJSChart(options1);
            },
            resize: function (event, ui) {
                //Update chart size according to its container size.
                $("#chartContainer1").CanvasJSChart().render();
            }
        });
    };

});


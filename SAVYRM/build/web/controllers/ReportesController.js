angular.module('angularRoutingApp').controller('reportesController', function ($scope, $http, $sessionStorage, $rootScope) {
    
    // // Variables
    // var listaServicio = [];
    // var listaProductosParaVenta = [];
    // var carritoDeCompras = []; // Almacena objetos a comprar
    // var productoSeleccionado;
    // var precioTotalAcumulado = 0;
    // $scope.PrecioTotal = "00.00";
    // $scope.shoppingCarEmpty = true; // used to hide table with car items when is empty
    
    // Obtiene productos para la venta
    $scope.GetAllSales = function(){
        $http({
            method: 'GET',
            url: 'http://localhost:8080/Report/GetAllSales',
            data: { }
        }).then(function successCallback(response) {
            $scope.PopulateSalesReport(response.data);
            }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };

    $scope.PopulateSalesReport = function(arraydata){
        // Construct options first and then pass it as a parameter
        var tableData =[];

        // prepare data to be show in the graphic
        for (var i = 0; i < arraydata.length ;i++) {
            var element = {
                label : arraydata[i].nombreProducto,
                y: arraydata[i].cantidadTotal
            }
            tableData.push(element);
        }
        

        var options1 = {
            animationEnabled: true,
            title: {
                text: "Producto vendidos"
            },
            data: [{
                type: "column", //change it to line, area, bar, pie, etc
                legendText: "Cantida por producto",
                showInLegend: true,
                dataPoints: tableData
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


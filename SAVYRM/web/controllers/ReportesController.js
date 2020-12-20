angular.module('angularRoutingApp').controller('reportesController', function ($scope, $http, $sessionStorage, $rootScope) {

    // Get sales per product
    $scope.GetAllSales = function(){
        $http({
            method: 'GET',
            url: 'http://localhost:8080/Report/GetAllSales',
            data: { }
        }).then(function successCallback(response) {
            $scope.PopulatBarGraphic(response.data, "Producto vendidos", "Cantida por producto");
            }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };

    // Get sales per product
    $scope.RevenuePerDay = function(){
        $http({
            method: 'GET',
            url: 'http://localhost:8080/Report/GetRevenuePerDay',
            data: { }
        }).then(function successCallback(response) {
            $scope.PopulateLineGraphic(response.data, "Ganancias de las ventas", "Ganancias por día");
            }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };

    // Get sales per product
    $scope.RevenuePerProduct = function(){
        $http({
            method: 'GET',
            url: 'http://localhost:8080/Report/GetRevenuePerProduct',
            data: { }
        }).then(function successCallback(response) {
            $scope.PopulateLineGraphic(response.data, "Ganancias de las ventas", "Ganancias por producto");
            }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };

    // Get sales per product
    $scope.SelesPerEmployee = function(){
        $http({
            method: 'GET',
            url: 'http://localhost:8080/Report/GetSalesAtendedPerEmployee',
            data: { }
        }).then(function successCallback(response) {
            $scope.PopulatBarGraphic(response.data, "Ventas por empleado", "Cantida por ventas");
            }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };

    // Populates the bar graphic
    $scope.PopulatBarGraphic = function(arraydata, title, legend){
        var options1 = {
            animationEnabled: true,
            title: {
                text: title
            },
            data: [{
                type: "column", //change it to line, area, bar, pie, etc
                legendText: legend,
                showInLegend: true,
                dataPoints: arraydata
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

    // Populates the line graphic
    $scope.PopulateLineGraphic = function(arraydata, title, leftLegend){
        var options = {
            animationEnabled: true,
            title:{
                text: title
            },
            axisX:{
                valueFormatString: "DD MMM",
                crosshair: {
                    enabled: true,
                    snapToDataPoint: true
                }
            },
            axisY: {
                title: leftLegend,
                valueFormatString: "S/##0.00",
                crosshair: {
                    enabled: true,
                    snapToDataPoint: true,
                    labelFormatter: function(e) {
                        return "S/" + CanvasJS.formatNumber(e.value, "##0.00");
                    }
                }
            },
            data: [{
                type: "area",
                xValueFormatString: "DD MMM",
                yValueFormatString: "S/##0.00",
                dataPoints: arraydata
            }]
        };
        
        $("#chartContainer").CanvasJSChart(options);
    };

});


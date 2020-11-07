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
            $scope.PopulateLineGraphic(response.data, "Ganancias de las ventas", "Ganancia por día");
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
                valueFormatString: "$##0.00",
                crosshair: {
                    enabled: true,
                    snapToDataPoint: true,
                    labelFormatter: function(e) {
                        return "$" + CanvasJS.formatNumber(e.value, "##0.00");
                    }
                }
            },
            data: [{
                type: "area",
                xValueFormatString: "DD MMM",
                yValueFormatString: "$##0.00",
                dataPoints: arraydata
                // [
                //     { x: new Date(2017, 08, 01), y: 85.83 },
        
                //     { x: new Date(2017, 08, 04), y: 84.42 },
                //     { x: new Date(2017, 08, 05), y: 84.97 },
                //     { x: new Date(2017, 08, 06), y: 84.89 },
                //     { x: new Date(2017, 08, 07), y: 84.78 },
                //     { x: new Date(2017, 08, 08), y: 85.09 },
                //     { x: new Date(2017, 08, 09), y: 85.14 },
        
                //     { x: new Date(2017, 08, 11), y: 84.46 },
                //     { x: new Date(2017, 08, 12), y: 84.71 },
                //     { x: new Date(2017, 08, 13), y: 84.62 },
                //     { x: new Date(2017, 08, 14), y: 84.83 },
                //     { x: new Date(2017, 08, 15), y: 84.37 },
                    
                //     { x: new Date(2017, 08, 18), y: 84.07 },
                //     { x: new Date(2017, 08, 19), y: 83.60 },
                //     { x: new Date(2017, 08, 20), y: 82.85 },
                //     { x: new Date(2017, 08, 21), y: 82.52 },
                    
                //     { x: new Date(2017, 08, 25), y: 82.65 },
                //     { x: new Date(2017, 08, 26), y: 81.76 },
                //     { x: new Date(2017, 08, 27), y: 80.50 },
                //     { x: new Date(2017, 08, 28), y: 79.13 },
                //     { x: new Date(2017, 08, 29), y: 79.00 }
                // ]
            }]
        };
        
        $("#chartContainer").CanvasJSChart(options);
    };

});


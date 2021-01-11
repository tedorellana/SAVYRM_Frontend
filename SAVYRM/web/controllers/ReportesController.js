angular.module('angularRoutingApp').controller('reportesController', function ($scope, $http, $sessionStorage, $rootScope) {

    // Get sales per product
    $scope.GetAllSales = function(){
        $http({
            method: 'GET',
            url: 'http://localhost:8080/Report/GetAllSales',
            data: { }
        }).then(function successCallback(response) {
            $scope.PopulateBarGraphic(response.data, "Producto vendidos", "Cantida por producto");
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

    // Get sales status for today.
    $scope.SalesStatusForToday = function(){
        $http({
            method: 'GET',
            url: 'http://localhost:8080/Report/SalesStatusCompared',
            data: { }
        }).then(function successCallback(response) {
            $scope.PopulateLineComparedGraphic(response.data.baseLine, response.data.currentLine, "Estado de ventas", "Número de ventas", "Ventas esperadas", "Ventas por día");
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
            $scope.PopulateBarGraphic(response.data, "Ventas por empleado", "Cantida por ventas");
            }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };

    // Populates the bar graphic
    $scope.PopulateBarGraphic = function(arraydata, title, legend){
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

    // Populates the line graphic that show comparation with expected values
    $scope.PopulateLineComparedGraphic = function(expectedValues, currentData, title, horiztontalTitle, baseLabel, currentLabel){
        var options = {
            animationEnabled: true,
            theme: "light2",
            title:{
                text: title
            },
            axisX:{
                valueFormatString: "DD MMM"
            },
            axisY: {
                title: horiztontalTitle,
                suffix: "K",
                minimum: 30
            },
            toolTip:{
                shared:true
            },  
            legend:{
                cursor:"pointer",
                verticalAlign: "bottom",
                horizontalAlign: "left",
                dockInsidePlotArea: true,
                itemclick: toogleDataSeries
            },
            data: [{
                type: "line",
                showInLegend: true,
                name: baseLabel,
                markerType: "square",
                xValueFormatString: "DD MMM, YYYY",
                color: "#F08080",
                yValueFormatString: "#,##0",
                // dataPoints: expectedValues
                dataPoints: [
                    {"label":"2017-7-2","y":1000},
                    {"label":"2017-7-5","y":1000},
                    {"label":"2020-9-6","y":1000},
                    {"label":"2020-10-6","y":1000},
                    {"label":"2020-10-1","y":1000},
                    {"label":"2020-10-25","y":1000},
                    {"label":"2020-11-8","y":1000},
                    {"label":"2020-11-23","y":1000},
                    {"label":"2020-11-29","y":1000}
                ]
                // dataPoints: [
                //     { x: new Date(2020, 10, 25), y: 13 },
                //     { x: new Date(2020, 11, 8), y: 100 },
                //     { x: new Date(2020, 11, 23), y: 100 }
                // ]
            },
            {
                type: "line",
                showInLegend: true,
                name: currentLabel,
                lineDashType: "dash",
                yValueFormatString: "#,##0",
                dataPoints: currentData
            }]
        };
        $("#chartContainer").CanvasJSChart(options);
        
        $("#chartContainer").CanvasJSChart(options);
    };

    function toogleDataSeries(e){
        if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
            e.dataSeries.visible = false;
        } else{
            e.dataSeries.visible = true;
        }
        e.chart.render();
    }
});


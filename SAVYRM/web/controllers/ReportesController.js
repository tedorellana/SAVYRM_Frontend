angular.module('angularRoutingApp').controller('reportesController', function ($scope, $http, $sessionStorage, $rootScope) {
    
    // Format date as expeceted
    $scope.FormatDate = function(date) {
        date = date.getFullYear() + "-" + 
        parseInt(date.getMonth() + 1)  + "-" +
        date.getDate() + " " +
        date.getHours() + ":" +
        date.getMinutes() + ":" +
        date.getSeconds();
        return date;
    };
    
    // get the beginning attetion datetime
    var beginningDateTime = new Date();
    var endDateTime = new Date();
    beginningDateTime.setDate(beginningDateTime.getDate() - 30);
    endDateTime.setDate(beginningDateTime.getDate() + 0);
    
    $scope.beginDate = beginningDateTime;
    $scope.endDate = endDateTime;

    // Initialize date
    $scope.Initialize = function() {
        console.log("Initialize()");

        // get the beginning attetion datetime
        beginningDateTime = new Date();
        //getPreviousDate - 30
        beginningDateTime.setDate(beginningDateTime.getDate() - 30);

        endDateTime = new Date();
        $scope.beginDate = $scope.FormatDate(beginningDateTime);
        $scope.endDate = $scope.FormatDate(new Date());
    };

    // Format date as expeceted
    $scope.FormatDateTimeEdges = function(date, upperDate) {
        console.log("FormatDateTimeEdges()")
        if (upperDate) {
            date = date.getFullYear() + "-" + 
                parseInt(date.getMonth() + 1)  + "-" +
                date.getDate() + " " + "11:59:59";
        }
        else {
            date = date.getFullYear() + "-" + 
                parseInt(date.getMonth() + 1)  + "-" +
                date.getDate() + " " + "00:00:00";
        }
        
        return date;
    };

    // Get sales per product
    $scope.GetAllSales = function(){
        $http({
            method: 'POST',
            url: 'http://localhost:8080/Report/GetAllSales',
            data: { 
                fechaInicio : $scope.FormatDateTimeEdges(new Date($scope.beginDate), false),
                fechaFin: $scope.FormatDateTimeEdges(new Date($scope.endDate), true)
            }
        }).then(function successCallback(response) {
            $scope.PopulateBarGraphic(response.data, "Producto vendidos", "Cantida por producto");
            }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };

    // Get sales per product
    $scope.RevenuePerDay = function(){
        $http({
            method: 'POST',
            url: 'http://localhost:8080/Report/GetRevenuePerDay',
            data: { 
                fechaInicio : $scope.FormatDateTimeEdges(new Date($scope.beginDate), false),
                fechaFin: $scope.FormatDateTimeEdges(new Date($scope.endDate), true)
            }
        }).then(function successCallback(response) {
            $scope.PopulateLineGraphic(response.data, "Ingresos de las ventas", "Ingresos por día");
        }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };

    // Get sales per product
    $scope.RevenuePerProduct = function(){
        $http({
            method: 'POST',
            url: 'http://localhost:8080/Report/GetRevenuePerProduct',
            data: { 
                fechaInicio : $scope.FormatDateTimeEdges(new Date($scope.beginDate), false),
                fechaFin: $scope.FormatDateTimeEdges(new Date($scope.endDate), true)
            }
        }).then(function successCallback(response) {
            $scope.PopulateLineGraphic(response.data, "Ingresos de las ventas", "Ingresos por producto");
            }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };

    // Get sales status for today.
    $scope.SalesStatusForToday = function(){
        $http({
            method: 'POST',
            url: 'http://localhost:8080/Report/SalesStatusCompared',
            data: { 
                fechaInicio : $scope.FormatDateTimeEdges(new Date($scope.beginDate), false),
                fechaFin: $scope.FormatDateTimeEdges(new Date($scope.endDate), true)
            }
        }).then(function successCallback(response) {
            $scope.PopulateLineComparedGraphic(response.data.baseLine, response.data.currentLine, "Estado de ventas", "Número de ventas", "Ventas esperadas", "Ventas por día");
            }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };

    // Get the revenue per day compared with the average
    $scope.RevenueStatusCompared = function(){
        
        $http({
            method: 'POST',
            url: 'http://localhost:8080/Report/RevenueStatusCompared',
            data: { 
                fechaInicio : $scope.FormatDateTimeEdges(new Date($scope.beginDate), false),
                fechaFin: $scope.FormatDateTimeEdges(new Date($scope.endDate), true)
            }
        }).then(function successCallback(response) {
            $scope.PopulateLineComparedGraphic(response.data.baseLine, response.data.currentLine, "Estado de ingresos", "Ingresos", "Ingresos esperadas", "Ingresos por día");
            }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };

    // Get the stock per day and products compared with the average
    // $scope.StockStatusPerProduct = function(){
    //     $http({
    //         method: 'GET',
    //         url: 'http://localhost:8080/Report/RevenueStatusCompared',
    //         data: { }
    //     }).then(function successCallback(response) {
    //         $scope.PopulateLineComparedGraphic(response.data.baseLine, response.data.currentLine, "Estado de ventas", "Número de ventas", "Ventas esperadas", "Ventas por día");
    //         }, function errorCallback(response) {
    //         alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
    //     });
    // };

    // Get sales per product
    $scope.SelesPerEmployee = function(){
        $http({
            method: 'POST',
            url: 'http://localhost:8080/Report/GetSalesAtendedPerEmployee',
            data: { 
                fechaInicio : $scope.FormatDateTimeEdges(new Date($scope.beginDate), false),
                fechaFin: $scope.FormatDateTimeEdges(new Date($scope.endDate), true)
            }
        }).then(function successCallback(response) {
            $scope.PopulateLineGraphic(response.data, "Ventas por empleado", "Cantidad por ventas");
        }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };

    // Populates the bar graphic
    $scope.PopulateBarGraphic = function(arraydata, title, legend){
        console.log("PopulateBarGraphic() " + JSON.stringify(arraydata));

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
                $("#chartContainer").CanvasJSChart(options1);
            },
            resize: function (event, ui) {
                //Update chart size according to its container size.
                $("#chartContainer").CanvasJSChart().render();
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
                    labelFormatter: function( ) {
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
                suffix: "",
                minimum: 0
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
                dataPoints: expectedValues
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


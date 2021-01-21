angular.module('angularRoutingApp').controller('dashboardController', function ($scope, $http, $sessionStorage, $rootScope, config) {
    $scope.base = $rootScope.mySetting;
    $scope.baseUrl = config.baseUrl;
    $scope.currentSales = 0;
    $scope.expectedSales = 0;
    $scope.currentRevenue = 0;
    $scope.expectedRevenue = 0.00;
    $scope.salesRowColor = 0; // 0 = orange, 1= green, -1 = red;
    $scope.revenueRowColor = 0; // 0 = orange, 1= green, -1 = red;
    var expDate = new Date();

    // Format date as expeceted
    $scope.FormatDate = function(date) {
        date = date.getDate() + "-" + 
            parseInt(date.getMonth() + 1)  + "-" +
            date.getFullYear()
        return date;
    };

    // Format date as expeceted
    $scope.FormatExternalDate = function(date) {
        var externalDate = new Date(date);
        externalDate = externalDate.getDate() + "-" + 
            parseInt(externalDate.getMonth() + 1)  + "-" +
            externalDate.getFullYear()
        return externalDate;
    };

    // Orange if equals, red if lower and green if upper
    $scope.DetermineStatusColor = function(current, expected) {
        console.log("DetermineStatusColor() current: " + current + " expected: " + expected);
        if (current > expected) {
            return 1;
        }
        else if (current == expected) {
            return 0;
        }
        else {
            return -1;
        }
    };

    // get the beginning attetion datetime
    var currentDate = new Date();
    currentDate = $scope.FormatDate(currentDate);

    $scope.currentDate = currentDate;

    // Load all the dashboard data
    $scope.PopulateDashboard = function() {
        console.log("PopulateDashboard");
        $scope.LoadCurrentStatus();
    };

    $scope.LoadCurrentStatus = function() {
        console.log("LoadCurrentStatus");

        // Set expiration date
        let NEAR_EXPIRATION_DATE_RANGE = 30;
        expDate.setDate(expDate.getDate() + NEAR_EXPIRATION_DATE_RANGE);
        $scope.nearExpirationDateBase = $scope.FormatDate(expDate);

        $scope.SalesStatusCompared();
        $scope.RevenueStatusCompared();
        $scope.ProductsOrderByExpiration();
    };

    // Get sales status for today.
    $scope.SalesStatusCompared = function(){
        $http({
            method: 'GET',
            url: 'http://localhost:8080/Report/SimpleSalesStatusCompared',
            data: { }
        }).then(function successCallback(response) {
            console.log(JSON.stringify(response.data));
            $scope.currentSales = response.data.current.y;
            $scope.expectedSales = response.data.expected.y;
            let color = $scope.DetermineStatusColor($scope.currentSales, $scope.expectedSales);
            console.log("setting color: " + color);
            $scope.salesRowColor = color;
            // $scope.PopulateLineComparedGraphic(response.data.baseLine, response.data.currentLine, "Estado de ventas", "Número de ventas", "Ventas esperadas", "Ventas por día");
            }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };

    // Get the revenue per day compared with the average
    $scope.RevenueStatusCompared = function(){
        $http({
            method: 'GET',
            url: 'http://localhost:8080/Report/SimpleRevenueStatusCompared',
            data: { }
        }).then(function successCallback(response) {
            console.log(JSON.stringify(response.data));
            $scope.currentRevenue = response.data.current.y;
            $scope.expectedRevenue = response.data.expected.y;
            $scope.revenueRowColor = $scope.DetermineStatusColor($scope.currentRevenue, $scope.expectedRevenue);
            // $scope.PopulateLineComparedGraphic(response.data.baseLine, response.data.currentLine, "Estado de ganancias", "Ganancias", "Ganancias esperadas", "Ganancias por día");
            }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };

    // Get the revenue per day compared with the average
    $scope.ProductsOrderByExpiration = function(){
        $http({
            method: 'GET',
            url: 'http://localhost:8080/ProductoSeccion/GetProductosOrderByCaducidad',
            data: { }
        }).then(function successCallback(response) {
            // console.log(JSON.stringify(response.data));
            $scope.productos = response.data;
            // $scope.PopulateLineComparedGraphic(response.data.baseLine, response.data.currentLine, "Estado de ganancias", "Ganancias", "Ganancias esperadas", "Ganancias por día");
            }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };

    // Compare the date of experitation based in the reference expiration date. -1 = expired, 0 = near to expire, 1 = no problem
    $scope.CompareDate = function(data){
        // console.log("-->" + data + "--->" + $scope.FormatDate(new Date(data)) + "________________" + $scope.currentDate);
        let inputDate = new Date(data);
        let todayDate = new Date();
        // console.log("-->" + data + "--->" + inputDate + "________________" + todayDate + " _____ " + expDate);
        var dateComparationResult;

        if (inputDate < todayDate) {
            dateComparationResult = -1;
        }
        else if (inputDate < expDate) {
            dateComparationResult = 0;
        }
        else if (inputDate >= expDate) {
            dateComparationResult = 1;
        }
        else {
            dateComparationResult = 1;
        }
        // console.log("result ------------->" + dateComparationResult);
        return dateComparationResult;
    };
 });
    
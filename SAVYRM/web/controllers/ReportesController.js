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
});


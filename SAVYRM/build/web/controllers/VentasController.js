angular.module('angularRoutingApp').controller('ventasController', function ($scope, $http, $rootScope) {

    $scope.message = 'VENTASSSSSSSS';
    $scope.especial = 'VENTAS';
    
    $scope.borrarSlider = function(){
        $scope.especial = 'presionado';
        var iEl = angular.element( document.querySelector( '#slider' ) );
        iEl.remove();
    };
    
    var listaServicio=[] ;
    $scope.getVentas = function(){
        $scope.borrarSlider();
        $http({
            method: 'POST',
            //url: 'http://localhost:8084/SAVYRM/webresources/Ventas/getVentas',
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
    
    $scope.getDetallesPorServicio = function(){
        
    };
});


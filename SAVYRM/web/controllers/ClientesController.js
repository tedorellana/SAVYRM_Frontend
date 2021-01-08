angular.module('angularRoutingApp').controller('clientesController', function ($scope, $http, $sessionStorage, $rootScope) {

    // Get all the clients available in the database
    $scope.GetAllClients = function(){
        $http({
            method: 'GET',
            url: 'http://localhost:8080/Persona/GetAllClients',
            data: {
            }
        }).then(function successCallback(response) {
            $scope.clients = response.data;
            }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };
});


angular.module('angularRoutingApp').controller('dashboardController', function ($scope, $rootScope, config) {
    $scope.message = 'Esta es la página de "Contacto", aquí podemos poner un formulario';
    $scope.especial = 'FUNCIONA';
    $scope.base = $rootScope.mySetting;
    $scope.baseUrl = config.baseUrl;
 });
    
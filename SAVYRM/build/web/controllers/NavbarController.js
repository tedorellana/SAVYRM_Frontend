 angular.module('angularRoutingApp').controller('navbarController', function ($scope, $sessionStorage, $window, $timeout, config) {
    var idCurrentUser = $sessionStorage.currentUser;
    var sessionLoggedIn = angular.isDefined(idCurrentUser);
    var currentUserNameLoggedIn = $sessionStorage.currentUserName;
    
    $scope.currentUserNameLoggedIn = currentUserNameLoggedIn;
    $scope.sessionLoggedIn = sessionLoggedIn;
    
    $scope.CerrarSesion = function(){
        delete $sessionStorage.currentUser;
        delete $sessionStorage.currentUserName;
        idCurrentUser = $sessionStorage.currentUser;
        currentUserNameLoggedIn = undefined;
        sessionLoggedIn = false;
        $scope.currentUserNameLoggedIn = currentUserNameLoggedIn;
        $scope.sessionLoggedIn = sessionLoggedIn;
        $timeout(function() {
            if($sessionStorage.currentUser === undefined && $sessionStorage.currentUserName === undefined){
                $window.location.href = config.baseUrl;
            }
        },100);
        
        //TODO:AL RECARGAR SE VUELVE A SETEAR LA SESIÓN..REVISAR ESTE PUNTO
    };
 });
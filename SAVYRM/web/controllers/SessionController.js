 angular.module('angularRoutingApp').controller('sessionController', function ($scope, config, $sessionStorage, $http, $route, $window, $timeout) {
    $scope.errorExistInSessionForm = false;
    $scope.mostrarAlmacenes = true;
    $scope.mostrarSecciones = false;
    
        $scope.IniciarSesion = function(){
        var resultado = false;
        var nombreUsuarioIniciarSesion = $scope.nombreUsuarioIniciarSesion;
        var contrasenaIniciarSesion = $scope.contrasenaIniciarSesion;

        $http({
            method: 'POST',
            url: 'http://localhost:8080/Usuario/IniciarSesion',
            data: { 
                nombreUsuario : nombreUsuarioIniciarSesion,
                contrasenhaUsuario  :   contrasenaIniciarSesion
            }
        }).then(function successCallback(response) {
            //alert(JSON.stringify(response.data));
            //alert(response.data.Usuario);
            resultado = response.data;
            //alert(resultado);
            if(resultado === null){
                alert("error");
                $scope.errorExistInSessionForm = true;
                $scope.errorIniciarSesion = "El usuario o la contraseña no son correctos";
            }
            else{
                $sessionStorage.currentUser = resultado.persona.idPersona;
                $sessionStorage.currentUserName = resultado.nombreUsuario;
                $timeout(function() {
                    if($sessionStorage.currentUser !== undefined)
                    {
                        $window.location.reload();
                    }
                  },100);
            }
        }, function errorCallback(response) {
            //alert(response);
            $scope.errorExistInSessionForm = true;
            $scope.errorIniciarSesion = "Ocurrio un error. Por favor, intentalo más tarde.";
        });
    };
    
 });
// Creación del módulo
var angularRoutingApp = angular.module('angularRoutingApp', ['ngRoute', 'ngStorage']);

angularRoutingApp.config(function($routeProvider) {
    // Configuración de las rutas
    $routeProvider
            .when('/', {
                    templateUrl	: 'views/Dashboard.html',
                    controller 	: 'dashboardController'
            })
            .when('/NuestrasVentas', {
                    templateUrl     : 'views/NuestrasVentas.html',
                    controller 	: 'ventasController'
            })
            .when('/Inventario', {
                    templateUrl     : 'views/Inventario.html',
                    controller 	: 'inventarioController'
            })
            .when('/AlmacenesYSecciones', {
                    templateUrl     : 'views/AlmacenesYSecciones.html',
                    controller 	: 'almacenesSeccionesController'
            })
            .when('/NuestrosProductos', {
                    templateUrl     : 'views/NuestrosProductos.html',
                    controller 	: 'productosController'
            })
            .otherwise({
                    redirectTo: '/'
            });
});

angularRoutingApp.constant('config', {
    appName: 'SAVYRM',
    appVersion: '1.0.0 (Beta)',
    baseUrl: 'http://localhost:8084/SAVYRM/'
});

 

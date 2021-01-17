// Creación del módulo
var angularRoutingApp = angular.module('angularRoutingApp', ['ngRoute', 'ngStorage']);

angularRoutingApp.config(function($routeProvider) {
    // Configuración de las rutas
    $routeProvider
            .when('/', {
                templateUrl	: 'views/Dashboard.html',
                controller 	: 'dashboardController'
            })
            .when('/NuevaVenta', {
                templateUrl     : 'views/NuevaVenta.html',
                controller 	: 'ventasController'
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
            .when('/AsesoriaRapida', {
                templateUrl     : 'views/AsesoriaRapida.html',
                controller 	: 'productosController'
            })
            .when('/NuestrosProductos', {
                templateUrl     : 'views/NuestrosProductos.html',
                controller 	: 'productosController'
            })
            .when('/NuestrosClientes', {
                templateUrl     : 'views/NuestrosClientes.html',
                controller 	: 'clientesController'
            })
            .when('/ReporteVentas', {
                templateUrl     : 'views/ReporteVentas.html',
                controller 	: 'reportesController'
            })
            .when('/ReporteVentasPorFecha', {
                templateUrl     : 'views/ReporteVentasPorFecha.html',
                controller 	: 'reportesController'
            })
            .when('/ReporteGananciaProducto', {
                templateUrl     : 'views/ReporteGananciaProducto.html',
                controller 	: 'reportesController'
            })
            .when('/ReporteVentasEmpleado', {
                templateUrl     : 'views/ReporteVentasEmpleado.html',
                controller 	: 'reportesController'
            })
            .when('/EstadoVentas', {
                templateUrl     : 'views/EstadoVentas.html',
                controller 	: 'reportesController'
            })
            .when('/EstadoGanancias', {
                templateUrl     : 'views/EstadoGanancias.html',
                controller 	: 'reportesController'
            })
            .when('/EstadoStock', {
                templateUrl     : 'views/EstadoStock.html',
                controller 	: 'reportesController'
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

 

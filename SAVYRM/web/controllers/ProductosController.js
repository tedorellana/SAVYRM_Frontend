angular.module('angularRoutingApp').controller('productosController', function ($scope, $http, $sessionStorage, $window, config) {
    
    //Removing slider
    var idCurrentUser = $sessionStorage.currentUser;
    var sessionLoggedIn = angular.isDefined(idCurrentUser);
    
    //Variables
    $scope.mostrarProductosPC = true;
    $scope.mostrarPreparacionPC = false;
    $scope.cantidadProductoParaFormulaNP = 0;
    var idProductoSeleccionadoParaFormula = 0;
    
    // Gets all producto with elaboration and with status as active
    $scope.GetAllProductsWithElaboration = function() {
        $scope.mostrarProductosPC = true;

        $http({
            method: 'POST',
            url: 'http://localhost:8080/Producto/GetAllProductoWithElaboration',
            data: {
                activado : 1
            }
        }).then(function successCallback(response) {
            $scope.productos = response.data;
        }, function errorCallback(response) {
            alert("Sucedio un error no esperado. Por favor, intenta más tarde.");
        });
    }

    // Get all products
    $scope.onLoadProductosController = function(){
        $scope.mostrarProductosPC = true;
        
        if(!sessionLoggedIn){
            $window.location.href = config.baseUrl;
        }
        else{
            $scope.getAllProductosActivados();
            $scope.getUnidadMedidaNP();
            $scope.getTipoProductoNP();
        }
    };
    
    var lista_productosPC=[];
    $scope.getAllProductosActivados = function(){
        $http({
            method: 'POST',
            url: 'http://localhost:8080/Producto/GetAllProductoWhereEstadoIsActivado',
            data: {
                activado : 1
            }
        }).then(function successCallback(response) {
            lista_productosPC = response.data;
            $scope.productosNP = lista_productosPC;
        }, function errorCallback(response) {
            alert("Sucedio un error no esperado. Por favor, intenta más tarde.");
        });
    };
    
    $scope.agregarNuevoProductoNP = function(){
        $http({
            method: 'POST',
            url: 'http://localhost:8080/Producto/SaveProducto',
            data: { nombreProducto : $scope.nombreProductoNPM,
                    fk_idTipoProducto : $scope.tipoProductoSelectedNPM,
                    fk_idUnidadMedida : $scope.unidadMedidaSelectedNPM
            }
        }).then(function successCallback(response){
            $scope.getAllProductosActivados();
        }, function errorCallback(){
           alert("Sucedio un error no esperado. Por favor, intenta más tarde.");
        });
    };
    
    $scope.getUnidadMedidaNP = function(){
        $http({
            method: 'GET',
            url: 'http://localhost:8080/UnidadMedida/GetAllUnidadMedida'
        }).then(function successCallback(response){
            $scope.unidadesMedida = response.data;
        }, function errorCallback(){
           alert("Sucedio un error no esperado. Por favor, intenta más tarde.");
        });
    };
    
    $scope.getTipoProductoNP = function(){
        $http({
            method: 'GET',
            url: 'http://localhost:8080/TipoProducto/GetAllTipoProducto'
        }).then(function successCallback(response){
            $scope.tiposProducto = response.data;
        }, function errorCallback(){
           alert("Sucedio un error no esperado. Por favor, intenta más tarde.");
        });
    };
    
    $scope.getDetallePorProductoNP = function(event){
        var productos = $scope.productosNP;
        angular.forEach(productos, function(producto){
            if(producto.idProducto == event.target.value ){
                $scope.productoAEditar = producto;
            }
        });
    };

    $scope.administrarPreparacionNP = function(event){
        $scope.mostrarProductosPC = false;
        $scope.mostrarPreparacionPC = true;
        
        var productos = $scope.productosNP;
        angular.forEach(productos, function(producto){
            if(producto.idProducto == event.target.value ){
                $scope.productoSeleccionadoElaboracionEPM = producto;
                $scope.getProductoFormulaNP();
                $scope.getProductoPreparacionNP();
            }
        });
    };
    
    var productosDeLaFormulaActual = null;
    $scope.getProductoFormulaNP = function(){
        productosDeLaFormulaActual = null;
        $http({
            method: 'POST',
            url: 'http://localhost:8080/ProductoFormula/GetAllProductoFormulaByIdProducto',
            data: {
                idProducto : $scope.productoSeleccionadoElaboracionEPM.idProducto
            }
        }).then(function successCallback(response){
            productosDeLaFormulaActual = JSON.stringify(response.data);
            $scope.productosSeleccionadosFormulaEPM = response.data;
            $scope.calcularPorcentajeContenedorFormula();
        }, function errorCallback(){
           alert("Sucedio un error no esperado. Por favor, intenta más tarde.");
        });
    };
    
    $scope.getProductoPreparacionNP = function(){
        $http({
            method: 'POST',
            url: 'http://localhost:8080/indicacionController/GetAllIndicacionByProductoIdProdcuto',
            data: {
                idProducto : $scope.productoSeleccionadoElaboracionEPM.idProducto
            }
        }).then(function successCallback(response){
            $scope.productosSeleccionadosPreparacionEPM = response.data;
        }, function errorCallback(){
           alert("Sucedio un error no esperado. Por favor, intenta más tarde.");
        });
    };
    
    $scope.calcularPorcentajeContenedorFormula = function(){
        var porcentajeContenedor = 0;
        $scope.porcentajeContenedor = porcentajeContenedor;
      
        angular.forEach($scope.productosSeleccionadosFormulaEPM, function(productosSeleccionadoFormulaEPM){
            porcentajeContenedor += productosSeleccionadoFormulaEPM.porcentaje;
        });
        $scope.porcentajeContenedor = porcentajeContenedor;
    };
    
    $scope.ocultarPreparacionProductosPC = function(){
        $scope.mostrarProductosPC = true;
        $scope.mostrarPreparacionPC = false;
    };
    
    $scope.getProductosNoFinales = function(){
        $http({
            method: 'GET',
            url: 'http://localhost:8080/Producto/GetAllProductoNoFinalesActivos',
            data: {
                
            }
        }).then(function successCallback(response){
            $scope.productosNoFinalesParaFormulaNP = response.data;
        }, function errorCallback(){
           alert("Sucedio un error no esperado. Por favor, intenta más tarde.");
        });
    };
    
    $scope.SeleccionarProductoParaFormulaPC = function(data){
        if (data != 0) idProductoSeleccionadoParaFormula = data;
        //should be completed after get product
        $scope.unidadDeMedidaNuevoProductoParaFormulaNP = "KG";
    };
    
    $scope.AgregarProductoAFormula = function(){
        //alert("Id Producto:" + idProductoSeleccionadoParaFormula + "-" + $scope.cantidadProductoParaFormulaNP + "-" + productosDeLaFormulaActual);
        $http({
            method: 'POST',
            url: 'http://localhost:8080/Producto/AgregarProductoAFormula',
            data: {
                idProducto : idProductoSeleccionadoParaFormula,
                cantidadProductoParaFormulaNP : $scope.cantidadProductoParaFormulaNP,
                productosDeLaFormulaActual : productosDeLaFormulaActual
            }
        }).then(function successCallback(response){
            
            $scope.cantidadProductoParaFormulaNP = 0;
            productosDeLaFormulaActual = null;
            $scope.getProductoFormulaNP();
        }, function errorCallback(){
            $scope.agregarProductoAFormulaPresentaError = true;
            $scope.agregarProductoAFormulaError = "Sucedio un error no esperado. Por favor, intenta más tarde.";
        });        
    };
    
}); 
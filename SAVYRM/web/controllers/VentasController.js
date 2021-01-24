angular.module('angularRoutingApp').controller('ventasController', function ($scope, $http, $sessionStorage, $rootScope) {

    // Variables
    var listaServicio = [];
    var listaProductosParaVenta = [];
    var carritoDeCompras = []; // Almacena objetos a comprar
    var productoSeleccionado;
    var precioTotalAcumulado = 0;
    var serviceSelected;
    $scope.PrecioTotal = "00.00";
    $scope.shoppingCarEmpty = true; // used to hide table with car items when is empty
    
    $scope.message = 'VENTAS';
    $scope.especial = 'VENTAS';
    
    $scope.FormatDate = function(date, withTime) {
        // TODO: This time should be allocated between the working hours
        if (withTime) {
            date = date.getFullYear() + "-" + 
                parseInt(date.getMonth() + 1)  + "-" +
                date.getDate() + " " +
                date.getHours() + ":" +
                date.getMinutes() + ":" +
                date.getSeconds();
        }
        else{
            date = date.getFullYear() + "-" + 
                parseInt(date.getMonth() + 1)  + "-" +
                date.getDate();
        }

        return date;
    };

    $scope.FormatDateSouthAmerica = function(date) {
        let formatedDate = new Date(date);
        
        formatedDate = formatedDate.getDate()  + "-" +
            parseInt(formatedDate.getMonth() + 1) + "-" +
            formatedDate.getFullYear() + "  " + 
            formatedDate.getHours() + ":" +
            formatedDate.getMinutes() + ":" +
            formatedDate.getSeconds();

        return formatedDate;
    };

    // Get total amount of the seected sale
    $scope.GetTotalCost = function(ventas) {
        var totalCost = 0;
        ventas.forEach(x => totalCost += x.costoTotal);
        return totalCost;
    };

    // get the beginning attetion datetime
    var beginningDateTime = new Date();
    beginningDateTime = $scope.FormatDate(beginningDateTime, true);

    $scope.borrarSlider = function(){
        $scope.especial = 'presionado';
        var iEl = angular.element( document.querySelector( '#slider' ) );
        iEl.remove();
    };
    
    $scope.getVentas = function(){
        $scope.borrarSlider();
        $http({
            method: 'POST',
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

    // Gets a detail of an specific sale
    $scope.GetServiceDetail = function(event){
        console.log("GetServiceDetail()");
        serviceSelected = JSON.parse(event.currentTarget.value);
        let idServiceSelected = serviceSelected.idServicio;
        $http({
            method: 'POST',
            url: 'http://localhost:8080/Servicio/GetVentasDetail',
            data: {
                idServiceSelected
            }
        }).then(function successCallback(response) {
            $scope.ventaDetail = response.data;
            $scope.serviceSelectedDetail = serviceSelected;
            $scope.TotalCostPerSelectedSale = $scope.GetTotalCost(response.data);
        }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        }); 
    };

    // Refresh the status based in the currentServiceSelected
    $scope.RefreshServiceDetail = function(){
        console.log("RefreshServiceDetail()");
        let idServiceSelected = serviceSelected.idServicio;
        $http({
            method: 'POST',
            url: 'http://localhost:8080/Servicio/GetVentasDetail',
            data: {
                idServiceSelected
            }
        }).then(function successCallback(response) {
            $scope.ventaDetail = response.data;
            $scope.serviceSelectedDetail = serviceSelected;
            $scope.TotalCostPerSelectedSale = $scope.GetTotalCost(response.data);
        }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        }); 
    };

    // Obtiene productos para la venta
    $scope.getProductosParaVenta = function(){
        $scope.borrarSlider();
        $http({
            method: 'GET',
            url: 'http://localhost:8080/Producto/GetAllProductosParaVenta',
            data: {
            }
        }).then(function successCallback(response) {
            listaProductosParaVenta = response.data;
            $scope.productosParaVenta = listaProductosParaVenta;
            }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });     
    };
    
    // Establece ID en el botón para agregar al carrito
    $scope.EstablecerCantidadParaAgregar = function(event) {
        productoSeleccionado = JSON.parse(event.currentTarget.value);
        $scope.ProductoParaAgregar = productoSeleccionado;
        $scope.UnidadesDisponibleParaAgregar = productoSeleccionado.cantidadProductoSeccion + " " + productoSeleccionado.abreviacion;
        $scope.CantidadProductoParaAgregar = 1;

        $scope.FechaEntregaDelProductoParaAgregar = $scope.FormatDate(new Date(), false);
        $scope.EntregaInmediata = true;

        if (productoSeleccionado.cantidadProductoSeccion < $scope.CantidadProductoParaAgregar) {
            $scope.EntregaInmediata = false;
            var fakeDeliveryDate = new Date();
            fakeDeliveryDate.setDate(fakeDeliveryDate.getDate() + 3);
            
            $scope.FechaEntregaDelProductoParaAgregar = $scope.FormatDate(fakeDeliveryDate, true);
        }
        
        $scope.CalcularPrecio();
    };

    // Establece ID en el botón para agregar al carrito
    $scope.RefreshDeliverDateAndPrice = function() {
        console.log("RefreshDeliverDateAndPrice()");
        $scope.ProductoParaAgregar = productoSeleccionado;
        
        $scope.FechaEntregaDelProductoParaAgregar = $scope.FormatDate(new Date(), false);
        $scope.EntregaInmediata = true;

        if (productoSeleccionado.cantidadProductoSeccion < $scope.CantidadProductoParaAgregar) {
            $scope.EntregaInmediata = false;
            var fakeDeliveryDate = new Date();
            fakeDeliveryDate.setDate(fakeDeliveryDate.getDate() + 3);
            
            $scope.FechaEntregaDelProductoParaAgregar = $scope.FormatDate(fakeDeliveryDate, true);
        }
        
        $scope.CalcularPrecio();
    };
    
    $scope.CalcularPrecio = function() {
        console.log("CalcularPrecio()");
        $scope.PrecioDelProductoParaAgregar = productoSeleccionado.unitarioPrecio * $scope.CantidadProductoParaAgregar;
    };
    
    // Agrega la cantidad indicada al carrito de compras
    $scope.AgregarACarrito = function(event){
        let dataRecibida = JSON.parse(event.currentTarget.value);
        let fechaEntrega = null;
        let fechaEntregaPrevista = null;
        // Establece formatos de fechas correctos para agregar el producto
        if ($scope.EntregaInmediata) {
            fechaEntrega = $scope.FormatDate(new Date(), true);
        }
        else {
            fechaEntregaPrevista = $scope.FechaEntregaDelProductoParaAgregar
        }

        var elementoCarrito = {
            codigoProducto : dataRecibida.codigoProducto,
            nombreProducto : dataRecibida.nombreProducto,
            idProductoSeccion : dataRecibida.idProductoSeccion,
            unidadMedida : dataRecibida.abreviacion,
            cantidad : $scope.CantidadProductoParaAgregar,
            precioUnitario : productoSeleccionado.unitarioPrecio,
            precioTotalProducto : $scope.PrecioDelProductoParaAgregar,
            entregado : $scope.EntregaInmediata,
            fechaEntrega : fechaEntrega,
            fechaEntregaPrevista : fechaEntregaPrevista
        };
        
        precioTotalAcumulado += elementoCarrito.precioTotalProducto;
        $scope.PrecioTotal = precioTotalAcumulado;
        
        carritoDeCompras.push(elementoCarrito);
        $scope.carrito = carritoDeCompras;

        $scope.shoppingCarEmpty = $scope.carrito == undefined || $scope.carrito.length <= 0;
    };
    
    // Obtiene productos para la venta
    $scope.registrarVenta = function(){
        var detallesServicio = {
            idEmpleado : $sessionStorage.currentUser,
            idCliente : $sessionStorage.currentUser, // TODO: pending to add customer.
            dateTimeServiceBegin : beginningDateTime
        }

        if (carritoDeCompras.length == 0)
        {
            alert("Ups! El carrito de compras se encuentra vacío. Debe seleccionar por lo menos un producto.");
            return;
        }

        $http({
            method: 'POST',
            url: 'http://localhost:8080/Venta/RegistrarVenta',
            data: { 
                carritoDeCompras,
                detallesServicio
            }
        }).then(function successCallback(response) {
            alert("Venta realizada!.");
            carritoDeCompras = null;
            $scope.carrito = carritoDeCompras;
            }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };

    // Mark the product as delivered
    $scope.DeliverProduct = function(event){
        // console.log(JSON.stringify(event.target));
        // console.log(JSON.stringify(event.target.value));
        // console.log(JSON.stringify(event.currentTarget));
        $scope.activeBtn = event;
        let saleSelected = JSON.parse(event.currentTarget.value);
        // console.log("DeliverProduct -> " + JSON.stringify(saleSelected));

        let currentDate = new Date();
        currentDate = $scope.FormatDate(currentDate, true);

        saleSelected.fechaEntrega = currentDate;

        // alert("Hour to update: " + saleSelected.fechaEntrega);
        
        // console.log("saleSelected -> " + JSON.stringify(saleSelected));

        $http({
            method: 'POST',
            url: 'http://localhost:8080/Venta/MarkProductAsDelivered',
            data: saleSelected
        }).then(function successCallback(response) {
            console.log("Product delivered.");
            $scope.RefreshServiceDetail();
            }, function errorCallback(response) {
            console.log("Error trying to deliver product.");
            //alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };

    ////////
    // ALL THIS SEGMENT IF COPIED FRROM PRODUCTOSCONTROLLER.JS
    ////////

    // Get elaboration per product
    $scope.GetElaboration = function(event){
        console.log("GetElaboration()");
        let productoParaVisualizar = JSON.parse(event.currentTarget.value);
        $scope.GetElaborationSteps(productoParaVisualizar.idProducto);
        $scope.elaborationNoAvailable = true;
        $scope.productNameToReviewElaboration = productoParaVisualizar.nombreProducto;

        $http({
            method: 'POST',
            url: 'http://localhost:8080/ProductoFormula/GetAllProductoFormulaByIdProducto',
            data: {
                idProducto : productoParaVisualizar.idProducto
            }
        }).then(function successCallback(response) {
            
            // Set if the elaboration is available or not
            if (response.data == "") {
                console.log("ELABORACION NO DISPONIBLE");
                $scope.elaborationNoAvailable = false;
            }

            $scope.PopulatePieGraphic(response.data);
            // $scope.mostrarProductosPC = false;
            $scope.productWithElaboration = response.data;
        }, function errorCallback(response) {
            alert("Sucedio un error no esperado. Por favor, intenta más tarde.");
        });
    };

    // Get elaboration steps per product
    $scope.GetElaborationSteps = function(idProducto){
        $http({
            method: 'POST',
            url: 'http://localhost:8080/indicacionController/GetAllIndicacionByProductoIdProdcuto',
            data: {
                idProducto : idProducto
            }
        }).then(function successCallback(response){
            $scope.elaborationSteps = response.data;
        }, function errorCallback(){
           alert("Sucedio un error no esperado. Por favor, intenta más tarde.");
        });
    };

    // Populates the graphic with the products in the formula
    $scope.PopulatePieGraphic = function(products) {
        var pieElements = ParseProductosForPieGraphic(products)
        var chart = new CanvasJS.Chart("chartContainer", {
            animationEnabled: true,
            title: {
                text: ""
            },
            data: [{
                type: "pie",
                startAngle: 240,
                yValueFormatString: "##0.00\"%\"",
                indexLabel: "{label} {y}",
                dataPoints: pieElements
            }]
        });
        chart.render();
    }

    // parse the products to be populated correctly in the pie graphic
    function ParseProductosForPieGraphic(products) {
        var pieProducts = [];
        
        angular.forEach(products, function(prod) {
            var pieProduct = {
                y: prod.porcentaje,
                label: prod.productoInsumo.nombreProducto
            };
            pieProducts.push(pieProduct);
        });

        return pieProducts;
    }
});


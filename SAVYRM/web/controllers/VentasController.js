angular.module('angularRoutingApp').controller('ventasController', function ($scope, $http, $sessionStorage, $rootScope) {

    // Variables
    var listaServicio = [];
    var listaProductosParaVenta = [];
    var carritoDeCompras = []; // Almacena objetos a comprar
    var ordenDeCompraAfectadas = []; // Almacena objetos a comprar
    var productoSeleccionado;
    var precioTotalAcumulado = 0;
    var serviceSelected;
    var acumulado = 0;
    $scope.PrecioTotal = "00.00";
    $scope.shoppingCarEmpty = true; // used to hide table with car items when is empty
    $scope.ShowProductToAddElaboration = false;
    
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

    // Format date to show in screen without the time
    $scope.FormatDateSouthAmericaWithoutTime = function(date) {

        if (date == null) {
            console.log("NULL value detected");
            return null;
        }

        let formatedDate = new Date(date);
        
        formatedDate = formatedDate.getDate()  + "-" +
            parseInt(formatedDate.getMonth() + 1) + "-" +
            formatedDate.getFullYear();
        return formatedDate;
    };

    $scope.FormatDateSouthAmerica = function(date) {

        if (date == null) {
            console.log("NULL value detected");
            return null;
        }

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
            $scope.productosParaVenta = [];
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });     
    };

    // Obtiene las ordened de compra
    $scope.GetOrdenesDeCompra = function(idProducto){
        console.log("GetOrdenesDeCompra(): " + idProducto);
        $http({
            method: 'POST',
            url: 'http://localhost:8080/OrdenCompraProducto/OrdersPerProduct',
            data: idProducto
        }).then(function successCallback(response) {
            $scope.ordenesDeCompraPorProducto = $scope.CalcularProductosDisponiblePorFecha(response.data);
            $scope.RefreshDeliverDateAndPrice();
        }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });     
    };

    // modifica la lista de ordenes de compra por producto para mostrar el acumulado por fecha.
    $scope.CalcularProductosDisponiblePorFecha = function(listaProductosPedidos){
        console.log("CalcularProductosDisponiblePorFecha()" + listaProductosPedidos.length);
        acumulado = 0;

        let arrayAcumulados = [];

        if (listaProductosPedidos.length == 0) {
            console.log("listaProductosPedidos vacío");
            return arrayAcumulados;
        }

        acumulado = productoSeleccionado.cantidadProductoSeccion;
        listaProductosPedidos.forEach(function(element) {
            acumulado += element.cantidadDisponibleOrdenCompraProducto;
            element.cantidadDisponibleOrdenCompraProducto = acumulado;
        });

        return listaProductosPedidos;
    }
    
    // Establece ID en el botón para agregar al carrito
    $scope.EstablecerCantidadParaAgregar = function(event) {
        ordenDeCompraAfectadas = [];
        $scope.ProductAvailable = true;
        productoSeleccionado = JSON.parse(event.currentTarget.value);
        $scope.ProductoParaAgregar = productoSeleccionado;
        $scope.UnidadesDisponibleParaAgregar = productoSeleccionado.cantidadProductoSeccion + " " + productoSeleccionado.abreviacion;
        $scope.CantidadProductoParaAgregar = 1;

        $scope.GetOrdenesDeCompra(productoSeleccionado.idProducto);
        $scope.CalcularPrecio();
    };
    
    // Establece ID en el botón para agregar al carrito
    $scope.EstablecerCantidadParaAgregarFromElaboration = function(event) {
        console.log("EstablecerCantidadParaAgregarFromElaboration()");
        $scope.ShowProductToAddElaboration = true;
        
        ordenDeCompraAfectadas = [];
        $scope.ProductAvailable = true;
        productoSeleccionado = JSON.parse(event.currentTarget.value);
        console.log("EstablecerCantidadParaAgregarFromElaboration():" + JSON.stringify(productoSeleccionado));
        $scope.ProductoParaAgregar = productoSeleccionado;
        $scope.UnidadesDisponibleParaAgregar = productoSeleccionado.cantidadProductoSeccion + " " + productoSeleccionado.abreviacion;
        $scope.CantidadProductoParaAgregar = 1;

        $scope.GetOrdenesDeCompra(productoSeleccionado.idProducto);
        $scope.CalcularPrecio();
    };

    // revisa si el pedidp por producto puede ser atentido con el stock actual y/o con las ordenes de compra
    $scope.IsProductAvailable = function(amount) {
        console.log("IsProductAvailable() -> " + amount + " / " + acumulado);
        // Ordenes a futuro no disponibles, se debe calcular solo con stock actual
        if ($scope.ordenesDeCompraPorProducto.length == 0)
        {
            if ($scope.UnidadesDisponibleParaAgregar < amount)
            {
                console.log("No se puede despachar con stock actual");
                return false; // producto no disponible con stock actual
            }
        }
        // existen ordenes de compra, calculando con ellas y stocka actual
        else
        {
            if (acumulado < amount){
                console.log("No se puede despachar ordenes y stock acumulado: " + acumulado);
                return false; // producto no disponible con stock actual y ordenes de compra
            }
        }
        return true;
    };

    // Calcula la fecha de entrega basados en el stock acutal y las ordenes de compra. Va acumulando la lista de ordenes de compras a ser afectadas con sus 
    // nuevas cantidades disponibles.
    $scope.CalcularFechadDeEntrega = function(amount) {
        console.log("CalcularFechadDeEntrega()" + $scope.ProductoParaAgregar.cantidadProductoSeccion  + " / " + amount);
        $scope.ProductoParaAgregar = productoSeleccionado;

        // Entrega inmediata con stock actual
        if ($scope.ProductoParaAgregar.cantidadProductoSeccion >= amount) {
            $scope.FechaEntregaDelProductoParaAgregar = $scope.FormatDate(new Date(), false);
            $scope.EntregaInmediata = true;
            $scope.ProductAvailable = true;
            console.log("Entrega inmediata: " + $scope.FechaEntregaDelProductoParaAgregar);
            return; // disponible por stock actual
        }

        $scope.EntregaInmediata = false;

        if ($scope.ordenesDeCompraPorProducto.length == 0) {
            console.log("ordenesDeCompraPorProducto vacío");
            
            $scope.ProductAvailable = false;
            return; // no disponible por acumulado
        }

        let stockYOrdenesAcumuladas = $scope.ordenesDeCompraPorProducto;
        ordenDeCompraAfectadas = [];
        let cantidadOrdenAcumuladaAnterior = 0;
        let tempAmountDiscounted = amount;
        console.log("--->" + stockYOrdenesAcumuladas);
        // Entrega con ordenes de compra y stock acumulado
        for (let i = 0; i < stockYOrdenesAcumuladas.length; i++) {
            let element = stockYOrdenesAcumuladas[i];
            
            if (element.cantidadDisponibleOrdenCompraProducto < amount) {
                // Contar orden como afectada sobre el acumulado
                let elementoOrdenAfectada = {
                    idOrdercompraproducto : element.idOrdercompraproducto,
                    cantidadDisponibleOrdenCompraProducto : 0 // nueva cantidad disponible
                };
                console.log("added element MENOR : ACUMULADO " + element.cantidadDisponibleOrdenCompraProducto + " " + elementoOrdenAfectada.idOrdercompraproducto + "_" + elementoOrdenAfectada.cantidadDisponibleOrdenCompraProducto);
                ordenDeCompraAfectadas.push(elementoOrdenAfectada);

                amount -= element.cantidadDisponibleOrdenCompraProducto;
            }
            else {
                // Contar orden como afectada total o parcialmente
                $scope.FechaEntregaDelProductoParaAgregar = element.fechaEntregaPrevistaOrdenCompraProducto;
                $scope.ProductAvailable = true;
                console.log( amount + " Entrega retrasada: " + $scope.FechaEntregaDelProductoParaAgregar);
                
                // Contar orden como afectada sobre el acumulado
                let elementoOrdenAfectada = {
                    idOrdercompraproducto : element.idOrdercompraproducto,
                    cantidadDisponibleOrdenCompraProducto : ((element.cantidadDisponibleOrdenCompraProducto - cantidadOrdenAcumuladaAnterior) - amount) // productos restantes en la orden de compra de este indice
                };
                console.log("added element MAYOR IGUAL : ACUMULADO " + element.cantidadDisponibleOrdenCompraProducto + " " + elementoOrdenAfectada.idOrdercompraproducto + "_" + elementoOrdenAfectada.cantidadDisponibleOrdenCompraProducto);
                ordenDeCompraAfectadas.push(elementoOrdenAfectada);

                return; // disponible por acumulado
            }

            cantidadOrdenAcumuladaAnterior += element.cantidadDisponibleOrdenCompraProducto; // usado para calcular el diferencial por fecha
        }

        console.log("ELIMINADO ordenDeCompraAfectadas");
        ordenDeCompraAfectadas = [];
        $scope.ProductAvailable = false; // No hay ordenes de compra y proxima fecha de entrega
    };


    // Establece ID en el botón para agregar al carrito
    $scope.RefreshDeliverDateAndPrice = function() {
        console.log("RefreshDeliverDateAndPrice()");

        $scope.ProductAvailable = true;
        if (!$scope.IsProductAvailable($scope.CantidadProductoParaAgregar)) {
            $scope.ProductAvailable = false;
            return;
        }

        // Calcular la fecha de entrega y mostrar en pantalla
        $scope.CalcularFechadDeEntrega($scope.CantidadProductoParaAgregar);

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
            fechaEntregaPrevista : fechaEntregaPrevista,
            ordenDeCompraAfectadas
        };
        
        precioTotalAcumulado += elementoCarrito.precioTotalProducto;
        $scope.PrecioTotal = precioTotalAcumulado;
        
        carritoDeCompras.push(elementoCarrito);
        $scope.carrito = carritoDeCompras;

        $scope.shoppingCarEmpty = $scope.carrito == undefined || $scope.carrito.length <= 0;
    };

    // Agrega la cantidad indicada al carrito de compras
    $scope.AgregarACarritoFromElaboration = function(event){
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
            fechaEntregaPrevista : fechaEntregaPrevista,
            ordenDeCompraAfectadas
        };
        
        precioTotalAcumulado += elementoCarrito.precioTotalProducto;
        $scope.PrecioTotal = precioTotalAcumulado;
        
        carritoDeCompras.push(elementoCarrito);
        $scope.carrito = carritoDeCompras;

        $scope.shoppingCarEmpty = $scope.carrito == undefined || $scope.carrito.length <= 0;
    };
    
    // Set the client for the sale
    $scope.SelectClient = function(event){
        console.log("SelectClient()");
        $scope.currentClient = JSON.parse(event.currentTarget.value);
    };

    // Obtiene productos para la venta
    $scope.registrarVenta = function(){
        if ($scope.currentClient == undefined || $scope.currentClient == null || $scope.currentClient == "") {
            alert("!Ups! Parece que no se ha seleccionado un cliente aún.\nSelecciona un cliente, por favor.");
            return;
        }

        if (carritoDeCompras.length == 0)
        {
            alert("!Ups! El carrito de compras se encuentra vacío. Debe seleccionar por lo menos un producto.");
            return;
        }

        var detallesServicio = {
            idEmpleado : $sessionStorage.currentUser,
            idCliente : $scope.currentClient.idPersona,
            dateTimeServiceBegin : beginningDateTime
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
            detallesServicio = null;
            $scope.carrito = carritoDeCompras;
            $scope.getProductosParaVenta();
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
        $scope.CantidadTotalAElaborar = 1;

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

            $scope.RecalcularCandidadPorInsumos(1);
        }, function errorCallback(response) {
            alert("Sucedio un error no esperado. Por favor, intenta más tarde.");
        });
    };

    // Get elaboration per product
    $scope.RecalcularCandidadPorInsumos = function(cantidadAElaborar){
        let productsElaborations = $scope.productWithElaboration;
        // console.log("RecalcularCandidadPorInsumos():" + JSON.stringify(productsElaborations) + "_" + $scope.CantidadTotalAElaborar);
        console.log("RecalcularCandidadPorInsumos():" + $scope.CantidadTotalAElaborar +"_" + cantidadAElaborar);
        productsElaborations.forEach( function(element) {
            console.log("---->" + cantidadAElaborar + " " + element.porcentaje);
            element.Cantidad = ((cantidadAElaborar * element.porcentaje) / 100);
        });

        $scope.productWithElaboration = productsElaborations;
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

    ////////
    // ALL THIS SEGMENT IF COPIED FRROM CLIENTESCONTROLLER.JS
    ////////
    // Format the date to concat it with the user name
    $scope.PopulateSelect = function() {
        console.log("PopulateSelect()");
        // SELECT TIPO PERSONA
        $scope.personType = [
            {name:'Natural', value:'1'},
            {name:'Jurídica', value:'2'}
        ];
        // SELECT TIPO DOCUMENTO
        $scope.documentType = [
            {name:'DNI', value:'1'},
            {name:'RUC', value:'2'}
        ];
    };

    // Get all the clients available in the database
    $scope.GetAllClients = function() {
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

    // Format the date to concat it with the user name
    $scope.FormatDateForUser = function(date) {
        date = date.getFullYear() + "" +
            parseInt(date.getMonth() + 1)  + "" +
            date.getDate() + "" +
            date.getHours() + "" +
            date.getMinutes() + "" +
            date.getSeconds();
        return date;
    };

    // Add client
    $scope.AddClient = function() {
        console.log("AddClient()");
        let nombreUsuario = $scope.nombres + $scope.FormatDateForUser(new Date());

        console.log("to add --> " + $scope.nombres + ", " + $scope.apellidoPaterno+ ", " + $scope.apellidoMaterno+ ", " + $scope.tipoDocumento,+ ", " + $scope.numeroDocumento+ ", " + $scope.telefono+ ", " + $scope.correo+ ", " + $scope.direccion+ ", " + $scope.tipoPersona+ ", "+ nombreUsuario);
        $http({
            method: 'POST',
            url: 'http://localhost:8080/Persona/AddClient',
            data: {
                nombres: $scope.nombres,
                apellidoPaterno: $scope.apellidoPaterno,
                apellidoMaterno: $scope.apellidoMaterno,
                tipoDocumento: $scope.tipoDocumento,
                numeroDocumento: $scope.numeroDocumento,
                telefono: $scope.telefono,
                correo: $scope.correo,
                dirrecion: $scope.direccion,
                tipoPersona: $scope.tipoPersona,
                nombreUsuario: nombreUsuario,
                contrasenhaUsuario: nombreUsuario // The same value of the user name as password by default
            }
        }).then(function successCallback(response) {
            alert("¡Cliente Registrado!");
            $scope.currentClient = response.data;
            // $scope.GetAllClients();
        }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };
});


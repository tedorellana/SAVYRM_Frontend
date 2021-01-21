angular.module('angularRoutingApp').controller('clientesController', function ($scope, $http, $sessionStorage, $rootScope) {

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

    // Get all the clients available in the database
    $scope.AddClient = function() {
        console.log("AddClient()");

        $http({
            method: 'GET',
            url: 'http://localhost:8080/Persona/AddClient',
            data: {
                nombres: $scope.nombres,
                apellidoPaterno: $scope.apellidoPaterno,
                apellidoMaterno: $scope.apellidoMaterno,
                tipoDocumento: $scope.tipoDocumento,
                numeroDocumento: $scope.numeroDocumento,
                telefono: $scope.telefono,
                correo: $scope.correo,
                dirrecion: $scope.direccion
            }
        }).then(function successCallback(response) {
            alert("¡Cliente agregado!");
        }, function errorCallback(response) {
            alert("Ups! Ocurrio un error. Por favor, inténtalo más tarde.");
        });
    };

    // Download clients as PDF
    $scope.DownloadPDFWithjsPDF = function() {
        alert("downloadPDFWithjsPDF");
        
        var table = $('#ClientsTable').tableToJSON();
        // var table = tableToJson($('#TestTable').get(0));
        var pdfDocument = new jsPDF('l', 'mm', 'a3', true);
        // alert(pdfDocument.getFontSize());
        var cellWidth;
        var headerPrinted = false;
        pdfDocument.cellInitialize();
        $.each(table, function (i, row){
            $.each(row, function (j, cell){

                if (cell == "Detalles") {
                    return true;
                }

                // Size defined based in the header
                switch (j) {
                    case "Apellidos y Nombres":
                        cellWidth = 75;
                        break;
                    case "Tipo de documento":
                        cellWidth = 15;
                        break;
                    case "Nro documento":
                        cellWidth = 35;
                        break;
                    case "Tipo persona":
                        cellWidth = 35;
                        break;
                    case "Teléfono":
                        cellWidth = 35;
                        break;
                    case "Correo":
                        cellWidth = 80;
                        break;
                    case "Dirección":
                        cellWidth = 120;
                        break;
                    case "":
                        break;
                    default:
                        cellWidth = j.length*3.9;
                        break;
                }

                if (!headerPrinted) {
                    console.log("*** Writing header ***");
                    headerPrinted = true;
                    pdfDocument.cell(15, 20, 75, 10, "Apellidos y Nombres", -2, "center");
                    pdfDocument.cell(15, 20, 15, 10, "Doc", -2, "center");
                    pdfDocument.cell(15, 20, 35, 10, "Nro Doc", -2, "center");
                    pdfDocument.cell(15, 20, 35, 10, "Tipo", -2, "center");
                    pdfDocument.cell(15, 20, 35, 10, "Teléfono", -2, "center");
                    pdfDocument.cell(15, 20, 80, 10, "Correo electrónico", -2, "center");
                    pdfDocument.cell(15, 20, 120, 10, "Dirección", -2, "center");
                }

                console.log(i+ "_"+ j.length + "----" + "_" + j + " Size : " + cellWidth + "____Cell: " + cell + "___row:" + row);
                pdfDocument.cell(15, 20, cellWidth, 10, cell, i-1, "left");
            })
        })

        // Save the PDF
        pdfDocument.save('NuestrosClientes.pdf');
    }
});


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

    $scope.DownloadPDFWithjsPDF = function() {
        alert("downloadPDFWithjsPDF");

        // var doc = new jsPDF();// new jsPDF('p', 'pt', 'a4');
    
        // doc.html(document.querySelector('#ClientsTable'), {
        //     callback: function (doc) {
        //     doc.save('ClientsTable.pdf');
        //     },
        //     margin: [60, 60, 60, 60],
        //     x: 32,
        //     y: 32,
        // });

        // var doc = new jsPDF();

        // // doc.text(20, 20, 'Hola mundo');
        // // doc.text(20, 30, 'Vamos a generar un pdf desde el lado del cliente');

        // // // Add new page
        // // doc.addPage();
        // // doc.text(20, 20, 'Visita programacion.net');

        // // // Save the PDF
        // // doc.save('documento.pdf');



        // alert("New PDF");

        // var doc = new window.jspdf.jsPDF();
    


        /////////
        // var doc = new jsPDF('p', 'pt', 'letter');  
        // var doc = new jsPDF();
        // // var elementHTML = $('#ClientsTable').html();
        // var elementHTML = $('#TestTable').html();
        // var specialElementHandlers = {
        //     '#DownloadAdPDF': function (element, renderer) {
        //         return true;
        //     },
        //     '.controls': function(element, renderer){
        //         return true;
        //     }
        // };
        // doc.fromHTML(elementHTML, 15, 15, {
        //     'width': 400,
        //     'elementHandlers': specialElementHandlers
        // });
        // // Save the PDF
        // doc.save('NuestrosClientes.pdf');
        //////
        
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

        // return new Cell(
        //     this.x,
        //     this.y,
        //     this.width,
        //     this.height,
        //     this.text,
        //     this.lineNumber,
        //     this.align
        //   );
        
    }
});


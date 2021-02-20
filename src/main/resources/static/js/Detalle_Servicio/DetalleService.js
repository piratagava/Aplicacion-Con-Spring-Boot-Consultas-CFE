var app = angular.module('AltaDetalleService', ['datatables', 'ngResource']);
app.controller("controllerDetalleService", ["$scope", "$http", function($scope, $http) {
   //son componentes de la web ocultos
   $("#formDetalleServiceGuardar").hide();
   $("#encabezadoDetalleService").show();
   $("#tablePricipal").hide();
   cargarTable(); // realizo la peticion al servidor que me de la lista de todos los clientes
   cargarTableDetalleServicio();
   document.getElementById("btnHideTable").disabled = true;
   var $selectComunidad = $('#selectComunidad');
   var $selectRole = $('#selectRole');

   $scope.openAgregarDetalleService = function() {
      $("#formDetalleServiceGuardar").show();
      $("#detalleServiceForm").show();
      $("#tablePricipal").hide();

      $selectComunidad.val($selectComunidad.children('option:first').val());
      $selectRole.val($selectRole.children('option:first').val());
   };

   $scope.cerrarFormularioDetalleService = function() {
      $("#detalleServiceForm").hide();
      $selectComunidad.val($selectComunidad.children('option:first').val());
      $selectRole.val($selectRole.children('option:first').val());
   };

   $scope.openListarDetalleService = function() {
      document.getElementById("btnShowTable").disabled = true;
      $("#tablePricipal").show();
      document.getElementById("btnHideTable").disabled = false;
      $("#detalleServiceForm").hide();
      $selectComunidad.val($selectComunidad.children('option:first').val());
      $selectRole.val($selectRole.children('option:first').val());
   };

   $scope.closeListarDetalleService = function() {
      document.getElementById("btnShowTable").disabled = false;
      $("#tablePricipal").hide();
      document.getElementById("btnHideTable").disabled = true;
      $selectComunidad.val($selectComunidad.children('option:first').val());
      $selectRole.val($selectRole.children('option:first').val());
   };

   //define storage para ver si existe una sesion
   var TOKEN_KEY = "jwtToken";

   var storage = localStorage.getItem(TOKEN_KEY);
   var cachedToken;

   if (storage == null) {
      window.location.href = "/es-login-page-Ing_Hernan";
   }

   var setToken = function(token) {
      cachedToken = token;
      storage.setItem('userToken', token);
   };
   var getToken = function() {
      if (!cachedToken) {
         cachedToken = storage.getItem('userToken');
      }
      return cachedToken;
   };
   var isAuthenticated = function() {
      //return true if we get something from getToken
      return !!getToken();
   };

   function createAuthorizationTokenHeader() {
      if (storage) {
         return { "Authorization": "Bearer " + storage };
      } else {
         return {};
      }
   }

   $http({
      url: 'listaComunidad', contentType: "application/json; charset=utf-8", dataType: "json",
      headers: createAuthorizationTokenHeader()
   }).then(function(response) {
      $scope.comunidades = response.data;
   }, function errorCallback(response) {
      alertify.notify("Sin Permisos Suficientes ah caducado su sesion", "error", 10, null);
      window.location.href = "/es-login-page-Ing_Hernan";
   });

   $scope.total_pago = null;
   //es para asignar la comunidad
   $scope.selectedCar = null;

   $scope.guardarDatosDetalleServicio = function(total_pago, selectedCar) {
      //obtengo el id_cliente
      var inputs = $('#valId').val();
      var limitePago = $('#txtLimitePago').val();
      var corte_Luz = $('#txtCorteLuz').val();

      var datosCliente = {
         cliente: { id_cliente: inputs }, limite_pago: limitePago,
         corte_luz: corte_Luz, total_pago: total_pago, comunidad: { id_comunidad: selectedCar }
      };

      //variables para hacer validaciones no campos vacios
      var totalPago = document.getElementById("txtNumTotalPago").value;

      var validar = validarDatosCliente(totalPago);
      if (validar == 1 && $('#selectRole').val().trim() != '') {
         $http({
            method: 'post', headers: createAuthorizationTokenHeader(),
            url: 'insertarDetalleService', data: datosCliente
         }).then(function onSuccess(response) {            
            $('#tablaDetalles').DataTable().ajax.reload();
            $("#detalleServiceForm")[0].reset();
            location.reload(true);            
         }).catch(function onError(response) {
            if (response == false) {
               alertify.notify('Se genero error interno intente mas tarde', 'error', 5, null);
            }
            if (response.status == 401) {
               alertify.notify('Su Sesion Expiro intente mas tarde', 'error', 5, null);
               window.location.href = "/es-login-page-Ing_Hernan";
            }
         });
      } else {
         alertify.notify('Porfavor Asegurate llenar los campos correctamente', 'error', 7, null);
      }
   }

   $scope.actualizarDatosCliente = function() {
      let confirm = alertify.confirm('Actualizar Detalles Del Cliente', 'Desea Actualizar el registro ?', null, null).set('labels', {
         ok: 'Si',
         cancel: 'Cancelar'
      });
      confirm.set({
         transition: 'slide'
      });

      confirm.set('onok', function() {
         var idDetalleCliente = $("#valIds").val();
         var idCliente = $("#valIdCliente").val();
         var Pago = $("#txtPago").val();
         var CorteLuz = $("#txtCorte").val();
         var Total_a_Pagar = $("#txtNumTotalPagos").val();
         var seleccionComunidad = $("#selectComunidad").val();

         var validar = validarDatosCliente(Total_a_Pagar);
         if (validar == 1 && $('#selectComunidad').val().trim() != '') {
            $http({
               method: 'put', headers: createAuthorizationTokenHeader(),
               url: 'actualizarDetalleService', data: {
                  id_detalle_servicio: idDetalleCliente,
                  cliente: { id_cliente: idCliente },
                  limite_pago: Pago,
                  corte_luz: CorteLuz,
                  total_pago: Total_a_Pagar,
                  comunidad: { id_comunidad: seleccionComunidad }
               }
            }).then(function onSuccess(response) {
               $('#tablaDetalles').DataTable().ajax.reload();
               alertify.notify("Se Actualizo Correctamente su Registro", "success", 5, null);
               $('#myModalActualizar').modal('toggle');
               $selectComunidad.val($selectComunidad.children('option:first').val());
            }).catch(function onError(response) {
               if (response == false) {
                  alertify.notify('Se genero error interno intente mas tarde', 'error', 5, null);
               }
               if (response.status == 401) {
                  alertify.notify('Su Sesion Expiro Inicie Sesion Nuevamente', 'error', 8, null);
                  window.location.href = "/es-login-page-Ing_Hernan";
               }
            });
         } else {
            alertify.notify('Porfavor Asegurate llenar los campos correctamente, Vuelva Seleccionar una Comunidad Nuevamente', 'error', 7, null);
         }
      });
      confirm.set('oncancel', function() { //llama al pulsar botón negativo
         alertify.error('Cancelo Actualizar su registro', 3);
      });
   }


   //Existe varias formas de validar campos pero este metodo lo tome de un colega fywer porque es bueno en javascript
   function validarDatosCliente(totalPago) {
      //variable binary de inicio
      var valido = 2;
      if (totalPago.length > 0) {
         let validaNumTotalPago = /^[0-9]{1,5}$/
         let esValido = validaNumTotalPago.exec(totalPago);

         if (esValido == null || totalPago <= 0) {
            alertify.notify('El número de cupos no ha sido validado', 'error', 3, null);
         } else {
            valido >>= 1;
         }
      }
      return valido;
   }
}]);


var TOKEN_KEY = "jwtToken";
var storage = localStorage.getItem(TOKEN_KEY);

function removeJwtToken() {
   localStorage.removeItem(TOKEN_KEY);
   window.location.href = "/es-login-page-Ing_Hernan";
}

function doLogout() {
   removeJwtToken();
}
function cargarTable() {
   var datatable = $('#tablaClientes').DataTable({
      "ajax": {
         "url": 'consultaEstaClienteEnDetalleServicio',
         "type": 'GET',
         "dataSrc": "",
         "dataType": "json",
         "headers": { "Authorization": "Bearer " + storage },
         "error": function(jqXHR) {
            alertify.notify("Error en la vista de la Tabla" + jqXHR.statusText + " " + jqXHR.responseText, 'error', 10, null);
         }
      },
      "columns": [
         { "data": "username" },
         { "data": "apellido_paterno" },
         { "data": "apellido_materno" },],
      "columnDefs": [{
         "targets": 3,
         "bSortable": false, //no permite la ordenación de columnas individuales.
         "render": function() {
            return '<button type="button" id="editar" class="editar edit-modal btn btn-outline-primary botonEditar"><span class="fa fa-check"></span><span class="hidden-xs"></span></button>';
         }
      }],
   });

   var editar = function(tbody, table) {
      $(tbody).on("click", "button.editar", function() {
         if (table.row(this).child.isShown()) {
            var data = table.row(this).data();
         } else {
            var data = table.row($(this).parents("tr")).data();
         }
         $('#val').val(data.username + " " + data.apellido_paterno + " " + data.apellido_materno);
         $('#valId').val(data.id_cliente);
         $("#myModalCliente").modal('hide');//ocultamos el modal
      });
   }
   editar("#tablaClientes tbody", datatable);
}


function cargarTableDetalleServicio() {
   var datatable = $('#tablaDetalles').DataTable({
      "ajax": {
         "url": 'listaDetalleServicio',
         "type": 'GET',
         "dataSrc": "",
         "dataType": "json",
         "headers": { "Authorization": "Bearer " + storage },
         "error": function(jqXHR) {
            alertify.notify("Error en la vista de la Tabla" + jqXHR.statusText + " " + jqXHR.responseText, 'error', 10, null);
         }
      },
      "columns": [
         { "data": "limite_pago" },
         { "data": "corte_luz" },
         { "data": "total_pago" },
         { "data": "cliente.username" },
         { "data": "cliente.apellido_paterno" },
         { "data": "cliente.apellido_materno" },
         { "data": "comunidad.direccion" }],
      "columnDefs": [{
         "targets": 7,
         "bSortable": false, //no permite la ordenación de columnas individuales.
         "render": function() {
            return '<button type="button" id="editar" class="editar edit-modal btn btn-outline-primary botonEditar"><span class="fa fa-pen"></span><span class="hidden-xs"></span></button>';
         }
      }, {
         "targets": 8,
         "data": null, //Si desea pasar datos que ya tiene para que cada seleccion sea diferente num
         "bSortable": false,
         "mRender": function(o) {
            return '<a class="btn btn-outline-danger" onclick="dialogEliminar(' + o.id_detalle_servicio + ')" type="button"><span class="fas fa-trash-alt"></span><span class="hidden-xs"></span></a>';
         }
      }],
   });

   var editar = function(tbody, table) {
      $(tbody).on("click", "button.editar", function() {
         if (table.row(this).child.isShown()) {
            var data = table.row(this).data();
         } else {
            var data = table.row($(this).parents("tr")).data();
         }

         $('#myModalActualizar').modal('show');

         $("#valIds").val(data.id_detalle_servicio);
         $("#valIdCliente").val(data.cliente.id_cliente);
         $("#valNombre").val(data.cliente.username);
         $("#valAp").val(data.cliente.apellido_paterno);
         $("#valAm").val(data.cliente.apellido_materno);
         $("#txtPago").val(data.limite_pago);
         $("#txtCorte").val(data.corte_luz);
         $("#txtNumTotalPagos").val(data.total_pago);

         // first().focus() es para cuando abras el modal se dirija el puntero al campo que indicas
         $("#valIds").first().focus();
         $("#txtPago").first().focus();
         $("#txtCorte").first().focus();
         $("#txtNumTotalPagos").first().focus();
      });
   }
   editar("#tablaDetalles tbody", datatable);
}


function dialogEliminar(id_detalle_servicio) {
   let confirm = alertify.confirm('Eliminar Producto', 'Desea eliminar el registro ?', null, null).set('labels', {
      ok: 'Si',
      cancel: 'Cancelar'
   });
   confirm.set({
      transition: 'slide'
   });

   confirm.set('onok', function() {
      eliminarCliente(id_detalle_servicio);
   });

   confirm.set('oncancel', function() { //llama al pulsar botón negativo
      alertify.error('Cancelo eliminar su registro', 3);
   });
}

function eliminarCliente(id_detalle_servicio) {
   var id_p = id_detalle_servicio;
   let json = {};
   json.id_detalle_servicio = id_p;
   $.ajax({
      type: 'DELETE',
      contentType: 'application/json',
      dataType: 'json',
      url: 'deleteDetalleService',
      data: JSON.stringify(json),
      headers: { "Authorization": "Bearer " + storage },
      success: function(data) {         
         $('#tablaDetalles').DataTable().ajax.reload();
         location.reload(true);
      },
      error: function(response) {
         if (response.status == 401) {
            alertify.notify("Error Expiro su Sesion Vuelva De Entrar", 'error', 10, null);
            window.location.href = "/es-login-page-Ing_Hernan";
         } else {
            alertify.notify('Error interno Primero Elimine a las personas con este Rol asociado', 'error', 8, null);
         }
      },
   });
}
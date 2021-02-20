var app = angular.module('AltaComunidad', ['datatables', 'ngResource']);
app.controller("controllerComunidad", ["$scope", "$http", function($scope, $http) {
   // son componentes de la web ocultos
   $("#formComunidadGuardar").hide();
   $("#encabezadoComunidad").show();
   $("#tablePricipal").hide();
   cargarTable(); // realizo la peticion al servidor que me de la
   // lista de todos los clientes
   document.getElementById("btnHideTable").disabled = true;

   $scope.openAgregarComunidad = function() {
      $("#formComunidadGuardar").show();
      $("#tablePricipal").hide();
      // limpiamos el formulario
      $("#comunidadForm")[0].reset();
   };

   $scope.openListarComunidad = function() {
      document.getElementById("btnShowTable").disabled = true;
      $("#tablePricipal").show();
      document.getElementById("btnHideTable").disabled = false;
      $("#formComunidadGuardar").hide();
   };

   $scope.closeListarComunidad = function() {
      document.getElementById("btnShowTable").disabled = false;
      $("#tablePricipal").hide();
      document.getElementById("btnHideTable").disabled = true;
   };

   $scope.cerrarFormularioComunidad = function() {
      $("#formComunidadGuardar").hide();      
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
      return !!getToken();
   };

   function createAuthorizationTokenHeader() {
      if (storage) {
         return { "Authorization": "Bearer " + storage };
      } else {
         return {};
      }
   }

   var almacenaDatosComunidad = $scope.comunidad = {};   

   $scope.guardarDatosComunidad = function() {
      // variables para hacer validaciones no campos vacios
      var nombreComunidad = document.getElementById("txtComunidadName").value;
      var validar = validarDatosComunidad(nombreComunidad);

      if (validar == 1) {
         $http({
            method: 'post', headers: createAuthorizationTokenHeader(), url: 'insertarComunidad',
            data: { direccion: almacenaDatosComunidad.direccion }
         }).then(function onSuccess(response) {
            alertify.notify("Se Registro Correctamente", "success", 5, null);
            $('#tablaComunidad').DataTable().ajax.reload();
            $("#comunidadForm")[0].reset();
         }).catch(function onError(response) {
            if (response == false) {
               alertify.notify('Se genero error interno intente mas tarde', 'error', 5, null);
            }
            if (response.status == 401) {
               alertify.notify('Por Seguridad tu sesion expiro Vuelve Iniciar Sesion', 'error', 10, null);
               window.location.href = "/es-login-page-Ing_Hernan";
            }
         });

      } else {
         alertify.notify('Porfavor Usa Caracteres Validos', 'error', 7, null);
      }
   }


   $scope.actualizarDatosComunidad = function() {
      let confirm = alertify.confirm('Actualizar Comunidad', 'Desea Actualizar el registro ?', null, null).set('labels', {
         ok: 'Si',
         cancel: 'Cancelar'
      });
      confirm.set({
         transition: 'slide'
      });

      confirm.set('onok', function() {
         // si quito el id no actualiza el producto
         // variables para hacer validaciones no campos vacios
         var id = $("#txtidc").val();
         var nameComunidad = $("#txtComunidadNamec").val();
         var validar = validarDatosComunidad(nameComunidad);
         if (validar == 1) {
            $http({
               method: 'put', headers: createAuthorizationTokenHeader(),
               url: 'actualizarComunidad', data: { id_comunidad: id, direccion: nameComunidad }
            }).then(function onSuccess(response) {
               $('#tablaComunidad').DataTable().ajax.reload();
               alertify.notify("Se Actualizo Correctamente su Registro", "success", 5, null);
               $('#myModalComunidad').modal('toggle');
            }).catch(function onError(response) {
               if (response == false) {
                  alertify.notify('Se genero error interno intente mas tarde', 'error', 5, null);
               }
               if (response.status == 401) {
                  alertify.notify('Por Seguridad tu sesion expiro Vuelve Iniciar Sesion', 'error', 10, null);
                  window.location.href = "/es-login-page-Ing_Hernan";
               }
            });
         } else {
            alertify.notify('Porfavor Asegurate Usar Caracteres Validos', 'error', 7, null);
         }
      });
      confirm.set('oncancel', function() { // llama al pulsar botón
         // negativo
         alertify.error('Cancelo Actualizar su registro', 3);
      });
   }

   //Existe varias formas de validar campos pero este metodo lo tome de un colega fywer porque es bueno en javascript
   function validarDatosComunidad(nombreComunidad) {
      // variable binary de inicio
      var valido = 2;

      if (nombreComunidad.length > 0) {
         let validaNombre = /^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$/
         let esValido = validaNombre.exec(nombreComunidad);
         if (esValido == null || nombreComunidad.length == 0 || /^\s+$/.test(nombreComunidad)) {
            alertify.notify('Usa solo 30 Caracteres', 'error', 4, null);
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
   var datatable = $('#tablaComunidad').DataTable({
      "ajax": {
         "url": 'listaComunidad',
         "type": 'GET',
         "dataSrc": "",
         "dataType": "json",
         "headers": { "Authorization": "Bearer " + storage },
         "error": function(jqXHR) {
            alertify.notify("Error Usted no es quien dice ser no mostrare nada" + jqXHR.statusText + " " + jqXHR.responseText, 'error', 10, null);
            window.location.href = "/es-login-page-Ing_Hernan";
         }
      },
      "columns": [
         { "data": "id_comunidad" },
         { "data": "direccion" },],
      "columnDefs": [{
         "targets": 2,
         "bSortable": false,
         "render": function() {
            return '<button type="button" id="editar" class="editar edit-modal btn btn-outline-primary botonEditar"><span class="fa fa-pen"></span><span class="hidden-xs"></span></button>';
         }
      }, {
         "targets": 3,
         "data": null, // Si desea pasar datos que ya tiene para
         // que cada seleccion sea diferente num
         "bSortable": false,
         "mRender": function(o) {
            return '<a class="btn btn-outline-danger" onclick="dialogEliminar(' + o.id_comunidad + ')" type="button"><span class="fas fa-trash-alt"></span><span class="hidden-xs"></span></a>';
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

         // no sirve no coloca los input text val identificados
         $('#myModalComunidad').modal('show');
         $("#txtidc").val(data.id_comunidad);
         $("#txtComunidadNamec").val(data.direccion);
         $("#txtidc").first().focus();
         $("#txtComunidadNamec").first().focus();
      });
   }

   editar("#tablaComunidad tbody", datatable);
}

function dialogEliminar(id_comunidad) {
   let confirm = alertify.confirm('Eliminar Comunidad', 'Desea eliminar el registro ?', null, null).set('labels', {
      ok: 'Si',
      cancel: 'Cancelar'
   });
   confirm.set({
      transition: 'slide'
   });

   confirm.set('onok', function() {
      eliminarComunidad(id_comunidad);
   });

   confirm.set('oncancel', function() { // llama al pulsar botón
      // negativo
      alertify.error('Cancelo eliminar su registro', 3);
   });
}

function eliminarComunidad(id_comunidad) {
   var id_p = id_comunidad;
   let json = {};
   json.id_comunidad = id_p;
   // sirve para ver que pasa con mi vector por consola
   
   $.ajax({
      type: 'DELETE',
      contentType: 'application/json',
      dataType: 'json',
      url: 'eliminarComunidad',
      data: JSON.stringify(json),
      headers: { "Authorization": "Bearer " + storage },
      success: function(data) {
         alertify.notify("Se Elimino Correctamente Comunidad", "success", 5, null);
         $('#tablaComunidad').DataTable().ajax.reload();
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


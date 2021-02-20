var app = angular.module('AltaRole', ['datatables', 'ngResource']);
app.controller("controllerRole", ["$scope", "$http", function($scope, $http) {
   // son componentes de la web ocultos
   $("#formRoleGuardar").hide();
   $("#encabezadoRole").show();
   $("#tablePricipal").hide();
   cargarTable(); // realizo la peticion al servidor que me de la
   // lista de todos los clientes
   document.getElementById("btnHideTable").disabled = true;

   $scope.openAgregarRole = function() {
      $("#formRoleGuardar").show();
      $("#tablePricipal").hide();
      // limpiamos el formulario
      $("#roleForm")[0].reset();
   };

   $scope.openListarRole = function() {
      document.getElementById("btnShowTable").disabled = true;
      $("#tablePricipal").show();
      document.getElementById("btnHideTable").disabled = false;
      $("#formRoleGuardar").hide();
   };

   $scope.closeListarRole = function() {
      document.getElementById("btnShowTable").disabled = false;
      $("#tablePricipal").hide();
      document.getElementById("btnHideTable").disabled = true;
   };

   $scope.cerrarFormularioRole = function() {
      $("#formRoleGuardar").hide();
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

   var almacenaDatosRole = $scope.role = {};


   $scope.guardarDatosRole = function() {
      // variables para hacer validaciones no campos vacios
      var nombreRole = document.getElementById("txtRoleName").value;

      var validar = validarDatosRole(nombreRole);
      if (validar == 1) {
         $http({
            url: 'consultarIdRole', contentType: "application/json; charset=utf-8", dataType: "json",
            headers: createAuthorizationTokenHeader()
         }).then(function(response) {
            var guardarId = response.data;
            $http({
               method: 'post', headers: createAuthorizationTokenHeader(), url: "insertarRole",
               data: { id_role: guardarId + 1, name: nombreRole }
            }).then(function onSuccess(response) {
               alertify.notify("Se Registro Correctamente", "success", 5, null);
               $('#tablaRoles').DataTable().ajax.reload();
               $("#roleForm")[0].reset();
            }).catch(function onError(rresponse) {
               if (response == false) {
                  alertify.notify('Se genero error interno intente mas tarde', 'error', 5, null);
               }
            });
         }).catch(function onError(response) {
            if (response.status == 401) {
               alertify.notify('Por Seguridad tu sesion expiro Vuelve Iniciar Sesion', 'error', 10, null);
               window.location.href = "/es-login-page-Ing_Hernan";
            } else {
               var guardarId = 0;
               $http({
                  method: 'post', headers: createAuthorizationTokenHeader(), url: "insertarRole",
                  data: { id_role: guardarId + 1, name: nombreRole }
               }).then(function onSuccess(response) {
                  alertify.notify("Se Registro Correctamente", "success", 5, null);
                  $('#tablaRoles').DataTable().ajax.reload();
                  $("#roleForm")[0].reset();
               }).catch(function onError(rresponse) {
                  if (rresponse.status == 401) {
                     alertify.notify('Por Seguridad tu sesion expiro Vuelve Iniciar Sesion', 'error', 10, null);
                     window.location.href = "/es-login-page-Ing_Hernan";
                  }
                  if (rresponse == false) {
                     alertify.notify('Se genero error interno intente mas tarde', 'error', 5, null);
                  }
               });
            }
         });

      } else {
         alertify.notify('Porfavor Usa Caracteres Validos', 'error', 7, null);
      }
   }


   $scope.actualizarDatosRole = function() {
      let confirm = alertify.confirm('Actualizar Role', 'Desea Actualizar el registro ?', null, null).set('labels', {
         ok: 'Si',
         cancel: 'Cancelar'
      });
      confirm.set({
         transition: 'slide'
      });

      confirm.set('onok', function() {
         var nameRole = $("#txtRoleNamer").val();
         var id = $("#txtid").val();         
         var validar = validarDatosRole(nameRole);         
         if (validar == 1) {
            $http({
               method: 'put', headers: createAuthorizationTokenHeader(),
               url: "actualizarRole", data: { id_role: id, name: nameRole }
            }).then(function onSuccess(response) {
               $('#tablaRoles').DataTable().ajax.reload();
               alertify.notify("Se Actualizo Correctamente su Registro", "success", 5, null);
               $('#myModalRole').modal('toggle');
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
   function validarDatosRole(nombreRole) {
      var valido = 2;

      if (nombreRole.length > 0) {
         let validaNombre = /^[A-Za-z0-9_-]{1,15}$/
         let esValido = validaNombre.exec(nombreRole);

         if (esValido == null || nombreRole.length == 0 || nombreRole.length > 40 || /^\s+$/.test(nombreRole)) {
            alertify.notify('Usa solo 40 Caracteres', 'error', 4, null);
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
   var datatable = $('#tablaRoles').DataTable({
      "ajax": {
         "url": "listarRole",
         "type": "GET",
         "dataSrc": "",
         "dataType": "json",
         "headers": { "Authorization": "Bearer " + storage },
         "error": function(jqXHR) {
            alertify.notify("Error Usted no es quien dice ser no mostrare nada" + jqXHR.statusText + " " + jqXHR.responseText, 'error', 10, null);
            window.location.href = "/es-login-page-Ing_Hernan";
         }
      },
      "columns": [
         { "data": "id_role" },
         { "data": "name" },],
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
            return '<a class="btn btn-outline-danger" onclick="dialogEliminar(' + o.id_role + ')" type="button"><span class="fas fa-trash-alt"></span><span class="hidden-xs"></span></a>';
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
         $('#myModalRole').modal('show');
         $("#txtid").val(data.id_role);
         $("#txtRoleNamer").val(data.name);

         $("#txtid").first().focus();
         $("#txtRoleNamer").first().focus();
      });
   }

   editar("#tablaRoles tbody", datatable);
}

function dialogEliminar(id_role) {
   let confirm = alertify.confirm('Eliminar Role', 'Desea eliminar el registro ?', null, null).set('labels', {
      ok: 'Si',
      cancel: 'Cancelar'
   });
   confirm.set({
      transition: 'slide'
   });

   confirm.set('onok', function() {
      eliminarRole(id_role);
   });

   confirm.set('oncancel', function() { // llama al pulsar botón
      // negativo
      alertify.error('Cancelo eliminar su registro', 3);

   });
}

function eliminarRole(id_role) {
   var id_p = id_role;
   let json = {};
   json.id_role = id_p;
   // sirve para ver que pasa con mi vector por consola   
   $.ajax({
      type: "DELETE",
      contentType: 'application/json',
      dataType: 'json',
      url: 'eliminarRole',
      data: JSON.stringify(json),
      headers: { "Authorization": "Bearer " + storage },
      success: function(data) {
         alertify.notify("Se Elimino Correctamente el Role" + data.status, "success", 8, null);
         $('#tablaRoles').DataTable().ajax.reload();
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

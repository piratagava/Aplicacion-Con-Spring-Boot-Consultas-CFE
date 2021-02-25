var app = angular.module('AltaClienteRole', ['datatables', 'ngResource'])
app.controller("controllerClienteRole", ["$scope", "$http", "ClienteRoleService", function($scope, $http, ClienteRoleService) {

	ClienteRoleService.ocultarElementos();

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

	ClienteRoleService.obtengoToken();

	//obtenemos la consulta de Role con el que relacionaremos los clientes
	$http({
		url: 'listarRole', contentType: "application/json; charset=utf-8", dataType: "json",
		headers: ClienteRoleService.createAuthorizationTokenHeader()
	}).then(function(response) {
		$scope.roles = response.data;
	});

	$scope.id_cliente = null;
	//es para asignar el rol
	$scope.selectedCar = null;

	$scope.actualizarDatosRole = function() {
		let confirm = alertify.confirm('Actualizar Autorizacion', 'Desea Actualizar el registro ?', null, null).set('labels', {
			ok: 'Si',
			cancel: 'Cancelar'
		});
		confirm.set({
			transition: 'slide'
		});

		confirm.set('onok', function() {
			var id = $("#txtid").val();
			var nombreRole = $("#selectRole").val();
			if ($('#selectRole').val().trim() != '') {
				$http({
					method: 'put', headers: ClienteRoleService.createAuthorizationTokenHeader(),
					url: 'actualizarClienteRole', data: { id_cliente: id, name: nombreRole }
				}).then(function onSuccess(response) {
					$('#tablaRoles').DataTable().ajax.reload();
					alertify.notify("Se Actualizo Correctamente su Registro", "success", 5, null);
					$selectRole.val($selectRole.children('option:first').val());
					$('#myModalRole').modal('toggle');
				}).catch(function onError(response) {
					if (response == false) {
						alertify.notify('Se genero error interno intente mas tarde', 'error', 5, null);
					}
					if (response == 401) {
						alertify.notify('Por Seguridad tu sesion expiro Vuelve Iniciar Sesion', 'error', 10, null);
						window.location.href = "/es-login-page-Ing_Hernan";
					}
				});
			} else {
				alertify.notify('Porfavor Asegurate de Seleccionar Una Opcion', 'error', 7, null);
			}
		});
		confirm.set('oncancel', function() {
			alertify.error('Cancelo Actualizar su registro', 3);
		});
	}
}]);

//define storage para ver si existe una sesion lo declaro de nuevo porque DataTable con angularJs no se llevan.
var TOKEN_KEY = "jwtToken";
var storage = localStorage.getItem(TOKEN_KEY);
var cachedToken;
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
			"url": 'listarClienteRole',
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
			{ "data": "0" },
			{ "data": "1" },
			{ "data": "2" },
			{ "data": "3" },
			{ "data": "4" },],
		"columnDefs": [{
			"targets": 5,
			"bSortable": false,
			"render": function() {
				return '<button type="button" id="editar" class="editar edit-modal btn btn-outline-primary botonEditar"><span class="fa fa-pen"></span><span class="hidden-xs"></span></button>';
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
			$("#txtid").val(data[0]);
			$("#txtid").first().focus();
		});
	}
	editar("#tablaRoles tbody", datatable);
}
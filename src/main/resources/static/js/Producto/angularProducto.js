var app = angular.module('AltaCliente', ['datatables', 'ngResource']);
app.controller("controllerCliente", ["$scope", "$http", "ClienteServices", function($scope, $http, ClienteServices, $window) {

	ClienteServices.ocultarElementos();
	//limpiamos el selecet
	var $singleSelect = $('#singleSelect');
	//limpio role
	var $selectRole = $('#selectRole');
	var $singleSelectc = $('#singleSelectc');

	$scope.openAgregarCliente = function() {
		$("#formClienteGuardar").show();
		$("#tablePricipal").hide();
		//limpiamos el formulario
		$("#clienteForm")[0].reset();
		$singleSelect.val($singleSelect.children('option:first').val());
		//reset Role
		$selectRole.val($selectRole.children('option:first').val());
		$singleSelectc.val($singleSelectc.children('option:first').val());
	};

	$scope.openListarCliente = function() {
		document.getElementById("btnShowTable").disabled = true;
		$("#tablePricipal").show();
		document.getElementById("btnHideTable").disabled = false;
		$("#formClienteGuardar").hide();
		$singleSelect.val($singleSelect.children('option:first').val());
		//reset Role
		$selectRole.val($selectRole.children('option:first').val());
		$singleSelectc.val($singleSelectc.children('option:first').val());
	};

	$scope.closeListarCliente = function() {
		document.getElementById("btnShowTable").disabled = false;
		$("#tablePricipal").hide();
		document.getElementById("btnHideTable").disabled = true;
		$singleSelect.val($singleSelect.children('option:first').val());
		//reset Role
		$selectRole.val($selectRole.children('option:first').val());
		$singleSelectc.val($singleSelectc.children('option:first').val());
	};

	$scope.cerrarFormularioCliente = function() {
		$("#formClienteGuardar").hide();
		$singleSelect.val($singleSelect.children('option:first').val());
		//reset Role
		$selectRole.val($selectRole.children('option:first').val());
		$singleSelectc.val($singleSelectc.children('option:first').val());
	};
	
	ClienteServices.obtengoToken();
	
	$http({
		url: 'listarRole', contentType: "application/json; charset=utf-8", dataType: "json",
		headers: ClienteServices.createAuthorizationTokenHeader()
	}).then(function successCallback(response) {
		$scope.roles = response.data;
	}, function errorCallback(response) {
		alertify.notify("Sin Permisos suficientes ah caducado su sesion", "error", 10, null);
		window.location.href = "/es-login-page-Ing_Hernan";
	});

	$scope.nombre_cliente = null;
	$scope.apellido_paterno = null;
	$scope.apellido_materno = null;
	$scope.activo = null;
	$scope.password = null;
	//es para asignar el rol
	$scope.selectedCar = null;

	$scope.guardarDatosCliente = function(nombre_cliente, apellido_paterno, apellido_materno, activo, password, selectedCar) {
		//capturo los datos enviados de la funcion
		var datosCliente = { username: nombre_cliente, apellido_paterno: apellido_paterno, apellido_materno: apellido_materno, activo: activo, password: password };

		// variables para hacer validaciones no campos vacios
		var nombreCliente = document.getElementById("txtClienteName").value;
		var apellidoClienteP = document.getElementById("txtClienteApellidoP").value;
		var apellidoClienteM = document.getElementById("txtClienteApellidoM").value;
		var passwordCliente = document.getElementById("txtClientePassword").value;

		var validar = ClienteServices.validarDatosCliente(nombreCliente, apellidoClienteP, apellidoClienteM, passwordCliente);
		if (validar == 1 && $('#singleSelect').val().trim() != '' && $('#selectRole').val().trim() != '') {

			$http({
				method: 'post', headers: ClienteServices.createAuthorizationTokenHeader(),
				url: 'insertarCliente', data: datosCliente
			}).then(function onSuccess(response) {
				alertify.notify("Se Registro Correctamente", "success", 5, null);
				$('#tablaClientes').DataTable().ajax.reload();
				$("#clienteForm")[0].reset();
				$singleSelect.val($singleSelect.children('option:first').val());						  				//reset Role
				$selectRole.val($selectRole.children('option:first').val());

				//obtengo el ultimo idCliente desde la BDatos para almancenar su relacion Cliente_role
				$http({ url: 'consultaIdClienteReciente', headers: ClienteServices.createAuthorizationTokenHeader() }).then(function(response) {
					var idClienteUltimo = $scope.idCliente = response.data;

					var mandarDatos = { id_cliente: idClienteUltimo, name: selectedCar };
					$http({
						method: 'post', headers: ClienteServices.createAuthorizationTokenHeader(),
						url: 'insertarClienteRole', data: mandarDatos
					}).then(function onSuccess(response) {
						alertify.notify("Se Registro Correctamente Cliente_role", "success", 5, null);
						$('#tablaRoles').DataTable().ajax.reload();
						$("#roleForm")[0].reset();
					}).catch(function onError(response) {
						if (response == false) {
							alertify.notify('Se genero error interno al guardar Cliente_Role', 'error', 5, null);
						}
					});
				});

			}).catch(function onError(response) {
				alertify.notify('Se genero error interno intente mas tarde', 'error', 5, null);
				window.location.href = "/es-login-page-Ing_Hernan";
			});
		} else {
			alertify.notify('Porfavor Asegurate llenar los campos correctamente', 'error', 7, null);
		}
	}


	$scope.actualizarDatosCliente = function() {
		let confirm = alertify.confirm('Actualizar Cliente', 'Desea Actualizar el registro ?', null, null).set('labels', {
			ok: 'Si',
			cancel: 'Cancelar'
		});
		confirm.set({
			transition: 'slide'
		});

		confirm.set('onok', function() {
			//si quito el id no actualiza el producto
			// variables para hacer validaciones no campos vacios
			var id = $("#txtidc").val();
			var nameCliente = $("#txtClienteNamec").val();
			var apellidoP = $("#txtClienteApellidoPc").val();
			var apellidoM = $("#txtClienteApellidoMc").val();
			var activoCliente = $("#singleSelectc").val();
			var passCliente = $("#txtClientePasswordc[type='password']").val();

			var validar = ClienteServices.validarDatosCliente(nameCliente, apellidoP, apellidoM, activoCliente, passCliente);
			if (validar == 1 && $('#singleSelectc').val().trim() != '') {
				$http({
					method: 'put', headers: ClienteServices.createAuthorizationTokenHeader(),
					url: 'actualizarCliente', data: {
						id_cliente: id, username: nameCliente, apellido_paterno: apellidoP,
						apellido_materno: apellidoM, activo: activoCliente, password: passCliente
					}
				}).then(function onSuccess(response) {
					$('#tablaClientes').DataTable().ajax.reload();
					alertify.notify("Se Actualizo Correctamente su Registro", "success", 5, null);
					$('#myModalCliente').modal('toggle');
					$singleSelectc.val($singleSelectc.children('option:first').val());
				}).catch(function onError(response) {
					if (response == false) {
						alertify.notify('Se genero error interno intente mas tarde', 'error', 5, null);
					}
				});
			} else {
				alertify.notify('Porfavor Asegurate llenar los campos correctamente, Vuelva Seleccionar Activo Nuevamente', 'error', 7, null);
			}
		});
		confirm.set('oncancel', function() { //llama al pulsar botón negativo
			alertify.error('Cancelo Actualizar su registro', 3);
		});
	}
}]);


//define storage para ver si existe una sesion lo declaro porque no tiene nada que ver con angularjs
//entonces es javascript puro asi es amigo el padrino asi lo hizo razon porque dataTable no es compatible con Ajs
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
			"url": 'listaCliente',
			"type": 'GET',
			"dataSrc": "",
			"dataType": "json",
			"headers": { "Authorization": "Bearer " + storage },
			"error": function(jqXHR, ajaxOptions, thrownError) {
				alertify.notify("Error en la vista de la Tabla" + jqXHR.statusText + " " + jqXHR.responseText, 'error', 10, null);
			}
		},
		"columns": [
			{ "data": "id_cliente" },
			{ "data": "username" },
			{ "data": "apellido_paterno" },
			{ "data": "apellido_materno" },],
		"columnDefs": [{
			"targets": 4,
			"bSortable": false, //no permite la ordenación de columnas individuales.
			"render": function() {
				return '<button type="button" id="editar" class="editar edit-modal btn btn-outline-primary botonEditar"><span class="fa fa-pen"></span><span class="hidden-xs"></span></button>';
			}
		}, {
			"targets": 5,
			"data": null, //Si desea pasar datos que ya tiene para que cada seleccion sea diferente num
			"bSortable": false,
			"mRender": function(o) {
				return '<a class="btn btn-outline-danger" onclick="dialogEliminar(' + o.id_cliente + ')" type="button"><span class="fas fa-trash-alt"></span><span class="hidden-xs"></span></a>';
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

			$('#myModalCliente').modal('show');

			$("#txtidc").val(data.id_cliente);
			$("#txtClienteNamec").val(data.username);
			$("#txtClienteApellidoPc").val(data.apellido_paterno);
			$("#txtClienteApellidoMc").val(data.apellido_materno);
			$("#txtClientePasswordc").val(data.password);

			// first().focus() es para cuando abras el modal se dirija el puntero al campo que indicas
			$("#txtidc").first().focus();
			$("#txtClienteNamec").first().focus();
			$("#txtClienteApellidoPc").first().focus();
			$("#txtClienteApellidoMc").first().focus();
			$("#txtClientePasswordc").first().focus();
		});
	}

	editar("#tablaClientes tbody", datatable);
}

function dialogEliminar(id_cliente) {
	let confirm = alertify.confirm('Eliminar Producto', 'Desea eliminar el registro ?', null, null).set('labels', {
		ok: 'Si',
		cancel: 'Cancelar'
	});
	confirm.set({
		transition: 'slide'
	});

	confirm.set('onok', function() {
		eliminarCliente(id_cliente);
	});

	confirm.set('oncancel', function() { //llama al pulsar botón negativo
		alertify.error('Cancelo eliminar su registro', 3);
	});
}

function eliminarCliente(id_cliente) {
	var id_p = id_cliente;
	let json = {};
	json.id_cliente = id_p;
	$.ajax({
		type: 'DELETE',
		contentType: 'application/json',
		dataType: 'json',
		url: 'eliminarCliente',
		data: JSON.stringify(json),
		headers: { "Authorization": "Bearer " + storage },
		success: function(data) {
			alertify.notify("Se Elimino Correctamente el Cliente", "success", 5, null);
			$('#tablaClientes').DataTable().ajax.reload();
		},
		error: function(jqXHR, exception) {
			if (jqXHR.status === 0) {
				alertify.notify('Error interno servidor no disponible' + jqXHR.responseText, 'error', 3, null);
			} else if (jqXHR.status == 404) {
				alertify.notify('Error en su peticion intente mas tardeya que esta en mantenimiento', 'error', 7, null);
			} else if (jqXHR.status == 500) {
				alertify.notify('Error debe eliminar el detalle servicio del cliente primero ', 'error', 7, null);
			} else if (exception === 'parsererror') {
				alertify.notify('Envio de peticion json fallo', 'error', 3, null);
			} else if (exception === 'timeout') {
				alertify.notify('Tiempo de salida error', 'error', 3, null);
			} else if (exception === 'abort') {
				alertify.notify('Respuesta Ajax Fallo', 'error', 3, null);
			} else {
				alertify.notify('Uncaught Error' + jqXHR.responseText, 'error', 3, null)
			}

		},
	});
}

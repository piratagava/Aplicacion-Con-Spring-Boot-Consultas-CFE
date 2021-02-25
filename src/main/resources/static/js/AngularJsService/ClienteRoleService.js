app.service("ClienteRoleService", function() {

	this.ocultarElementos = function() {
		// son componentes de la web ocultos
		$("#formRoleGuardar").hide();
		$("#encabezadoRole").show();
		$("#tablePricipal").hide();
		cargarTable(); // realizo la peticion al servidor que me de la
		// lista de todos los clientes
		document.getElementById("btnHideTable").disabled = true;
		//limpio role
		var $selectRole = $('#selectRole');
	}

	this.obtengoToken = function() {
		//define storage para ver si existe una sesion
		var TOKEN_KEY = "jwtToken";
		var storage = localStorage.getItem(TOKEN_KEY);
		var cachedToken;

		if (storage == null) {
			window.location.href = "/es-login-page-Ing_Hernan";
		}
	}

	this.createAuthorizationTokenHeader = function() {
		if (storage) {
			return { "Authorization": "Bearer " + storage };
		} else {
			return {};
		}
	}
	
});
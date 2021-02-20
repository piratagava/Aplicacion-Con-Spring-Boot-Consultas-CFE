<%--  
  User: hernan
  Date: 19/10/20
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html data-ng-app="AltaDetalleService"
	data-ng-controller="controllerDetalleService"
>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no"
>
<meta name="description" content="">
<meta name="author" content="Hernan Grcía Valladares">
<title>Página del Administrador</title>

<!-- Importamos las librerias de Java-Core y JSP-->
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/include.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/bootstrap.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/angular.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/dashboarStyle.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/notificaciones.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/fontawesome.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/dataTable.jsp"%>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.min.css"
	rel="stylesheet"
/>
<link rel="stylesheet" href="css_global/login.css"/>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/detalleServiceDataJs.jsp"%>


<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>

</head>
<body>
	<!-- LLamo el menu Logout y barra principal de ecabezado de Admin -->
	<jsp:include page="../Recursos-Web/NavarLogout.jsp"></jsp:include>
	<div class="container-fluid">
		<div class="row">

			<!-- LLamo el menu principal de Admin -->
			<jsp:include page="../Recursos-Web/MenuPricipalAdministrador.jsp"></jsp:include>

			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
				<!--Para Cliente boton guardar y listar lado derecho del encabezado-->
				<div id="encabezadoDetalleService">
					<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center 
					           pt-3 pb-2 mb-3 border-bottom">
						<h1 class="h2">Generar Servicio del Cliente:</h1>

						<div class="btn-toolbar mb-2 mb-md-0">
							<div class="btn-group mr-2">
								<button type="button" class="btn btn-outline-success"
									data-ng-click="openAgregarDetalleService()"
								>Agregar nuevo</button>
							</div>
							<div class="btn-group mr-2">
								<button id="btnShowTable" data-ng-click="openListarDetalleService()"
									type="button" class="btn btn-outline-success"
								>Listar Servicios CFE</button>
							</div>
							<div class="btn-group mr-2">
								<button id="btnHideTable" data-ng-click="closeListarDetalleService()"
									type="button" class="btn btn-outline-success"
								>Cerrar Lista</button>
							</div>
							<div class="btn-group mr-2">                        
                         <button type="button" onclick="doLogout()" class="btn btn-outline-success" id="logoutButton">Cerrar Sesion</button>
                     </div>
						</div>
					</div>
				</div>

				<!-- Formulario para llenar infomarcion a guardar -->
				<div id="formDetalleServiceGuardar">
					<div class="card-body">
						<form id="detalleServiceForm" style="align-items: center;">
							<div class="form-group">
								<button data-target="#myModalCliente" data-toggle="modal" type="button"
									id="editar" class="btn btn-outline-primary">
									<span class="far fa-address-card"></span> <span class="hidden-xs">Seleccione
										Cliente</span>
								</button>
							</div>

							<div class="row">
								<div class="col-xs-5">
									<span class="fas fa-angle-down"></span> <span class="hidden-xs">
										Usted Selecciono al Cliente </span> <input class="form-control"
										disabled="disabled" id="val" type="text"
									>
								</div>

								<!-- Sirve de aux para obtner id del cliente -->
								<div class="form-group">
									<input hidden="true" id="valId" type="text">
								</div>
							</div>

							<br>
							<div class="form-group">
								<label for="start">Limite de Pago:</label> <input type="date" id="txtLimitePago"
									name="start" ng-model="fecha" max="2021-12-31" required
								/>
							</div>

							<div class="form-group">
								<label for="startPago">Corte de Luz:</label> <input type="date" id="txtCorteLuz"
									name="startPago" ng-model="pago" max="2021-12-31"  required="required"
								/>
							</div>

							<div class="form-group">
								<label for="startPago">Total a pagar:</label> <input type="number"
									id="txtNumTotalPago" data-ng-model="total_pago" required="required"
									min="1" pattern="^[0-9]{1,5}$"
								/>
							</div>

							<div class="form-group">
								<label class="col-form-label">Seleccione Comunidad del Cliente:
								</label> <select required="required" ng-model="selectedCar" id="selectRole">
									<option value="">Selecciona Comunidad</option>
									<option ng-repeat="x in comunidades" value="{{x.id_comunidad}}">{{x.direccion}}</option>
								</select>
							</div>

							<button type="submit" class="btn btn-primary"
								data-ng-click="guardarDatosDetalleServicio(total_pago,selectedCar)"
							>Guardar Datos</button>
							<button type="submit" class="btn btn-primary" data-ng-click="cerrarFormularioDetalleService()"
							>Cerrar</button>
						</form>
					</div>
				</div>

				<!-- Modal para seleccionar Datos cliente -->
				<div class="modal fade bd-example-modal-lg" id="myModalCliente"
					tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Busque y Seleccione
									el Cliente Para Registrar su Servicio De Pago CFE</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close"
								>
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<!--Tabla para Mostar clientes-->
								<table id="tablaClientes" class="table table-hover">
									<thead>
										<tr valign="center">
											<th align="center">Nombre Cliente</th>
											<th align="center">Apellido Paterno</th>
											<th align="center">Apellido Materno</th>
											<th align="center">Seleccionar Cliente</th>
										</tr>
									</thead>
								</table>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
							</div>
						</div>
					</div>
				</div>
				<!-- Termina div del Modal -->


				<!--Tabla para Mostar DetalleClientes-->
				<div id="tablePricipal">
					<div class="card-body">
						<div class="table-responsive">
							<table id="tablaDetalles" class="table table-hover" style="width: 100%;">
								<thead valign="top" class="thead-dark">
									<tr valign="top">									   
										<th align="center">Limite de Pago</th>
										<th align="center">Corte de Luz</th>
										<th align="center">Total del Pago</th>
										<th align="center">Nombre del Cliente</th>
										<th align="center">Apellido Paterno</th>
										<th align="center">Apellido Materno</th>
										<th align="center">Comunidad</th>
										<th align="center">Actualizar</th>
										<th align="center">Eliminar</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>


				<!-- Modal para actualizar Datos DetallesCliente -->
				<div class="modal fade" id="myModalActualizar" tabindex="-1" role="dialog"
					aria-labelledby="modalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="modalLabel">Actualizar datos</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>

							<div class="modal-body">
								<form id="detalleServicioForm2">									
									<!-- Sirve de aux para obtner id del cliente -->
									<div class="form-group">
										<input hidden="true" id="valIds" type="text">
										<input hidden="true" id="valIdCliente" type="text">
									</div>				
									
									<div class="form-group">
									   <label for="start">Nombre Cliente:</label>
                              <input disabled="disabled" id="valNombre" type="text">
                              <br>
                              <label for="start">Apellido Paterno:</label>
                              <input disabled="disabled" id="valAp" type="text">
                              <br>
                              <label for="start">Apellido Materno:</label>
                              <input disabled="disabled" id="valAm" type="text">
                           </div>			
							<br>
							<div class="form-group">
								<label for="start">Limite de Pago:</label> <input id="txtPago" type="date"
									name="input" ng-model="fecha2" max="2021-12-31" required
								/>
							</div>

							<div class="form-group">
								<label for="startPago">Corte de Luz:</label> <input id="txtCorte" type="date"
									name="input" ng-model="pago2" max="2021-12-31"  required="required"
								/>
							</div>

							<div class="form-group">
								<label for="startPago">Total a pagar:</label> <input type="number"
									id="txtNumTotalPagos" data-ng-model="total_pago" required="required"
									min="1" pattern="^[0-9]{1,5}$"
								/>
							</div>

							<div class="form-group">
								<label class="col-form-label">Seleccione Comunidad del Cliente:
								</label> <select required="required" ng-model="selectedCar" id="selectComunidad">
									<option value="">Selecciona Comunidad</option>
									<option ng-repeat="x in comunidades" value="{{x.id_comunidad}}">{{x.direccion}}</option>
								</select>
							</div>

							</form>
						</div>

						<div class="modal-footer">
							<button type="submit" class="btn btn-primary" data-ng-click="actualizarDatosCliente()"
							>Actualizar Datos</button>
							<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
						</div>
					</div>
				</div>
		</div>
		<!-- Termina div del Modal -->

		</main>
	</div>
	</div>
</body>
</html>

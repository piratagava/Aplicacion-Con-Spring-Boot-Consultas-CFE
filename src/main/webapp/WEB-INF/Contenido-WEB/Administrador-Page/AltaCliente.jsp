<%--  
  User: hernan
  Date: 19/10/20
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html data-ng-app="AltaCliente" data-ng-controller="controllerCliente">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="author" content="Hernan García Valladares">
<title>Página del Administrador</title>

<!-- Importamos las librerias de Java-Core y JSP-->
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/include.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/bootstrap.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/angular.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/dashboarStyle.jsp"%>
<%@ include
	file="/WEB-INF/Contenido-WEB/Recursos-Web/notificaciones.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/fontawesome.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/dataTable.jsp"%>
<%@ include
	file="/WEB-INF/Contenido-WEB/Recursos-Web/productoDataJs.jsp"%>

 <link rel="stylesheet" href="css_global/login.css"/>

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
				<div id="encabezadoCliente">
					<div
						class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
						<h1 class="h2">Clientes Con Servicio CFE</h1>
						<div class="btn-toolbar mb-2 mb-md-0">
							<div class="btn-group mr-2">
								<button type="button" class="btn btn-outline-success"
									data-ng-click="openAgregarCliente()">Agregar Nuevo Cliente</button>
							</div>
							<div class="btn-group mr-2">
								<button id="btnShowTable" data-ng-click="openListarCliente()"
									type="button" class="btn btn-outline-success">Lista de Clientes</button>
							</div>
							<div class="btn-group mr-2">
								<button id="btnHideTable" data-ng-click="closeListarCliente()"
									type="button" class="btn btn-outline-success">Cerrar Lista</button>
							</div>
							<div class="btn-group mr-2">                        
                         <button type="button" onclick="doLogout()" class="btn btn-outline-success" id="logoutButton">Cerrar Sesion</button>
                     </div>
						</div>
               </div>
				</div>

				<!-- Formulario para llenar infomarcion a guardar -->
				<div id="formClienteGuardar">
					<div class="card-body">
						<form id="clienteForm">
							<div class="form-group">

								<input id="txtid" hidden="true" type="text" class="form-control"
									placeholder="Id" data-ng-model="producto.id_cliente"
									required="required"
									pattern="^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$">
							</div>
							<div class="form-group">
								<label class="col-form-label">Nombre Completo del Cliente: </label> 
								<input id="txtClienteName" type="text" class="form-control"
									placeholder="Nombre del Cliente" data-ng-model="nombre_cliente"
									required="required"
									pattern="^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$">
							</div>
							
							<div class="form-group">
							<label class="col-form-label">Apellido Paterno: </label>		
								<input id="txtClienteApellidoP" type="text" class="form-control"
                           placeholder="Apellido Paterno" data-ng-model="apellido_paterno"
                           required="required"
                           pattern="^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$">
                     </div>
                     
                     <div class="form-group">
                     <label class="col-form-label">Apellido Materno: </label>
                        <input id="txtClienteApellidoM" type="text" class="form-control"
                           placeholder="Apellido Materno" data-ng-model="apellido_materno"
                           required="required"
                           pattern="^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$"> 
                     </div>
						</div>
																										                                   
							<div class="form-group">
								<label class="col-form-label">Seleccione la Pregunta: </label>
								 <select class="form-select form-select-sm" aria-label=".form-select-sm example" required="true" name="singleSelect" id="singleSelect" ng-model="activo">								
									<option value="">PUEDE CONSULTAR EL USUARIO SU RECIBO CFE ?</option>									
									<option value="true">Si</option>									
									<option value="false">No</option>
								</select>
								
								<br>
								<label class="col-form-label">Seleccione la Autorizacion para el Cliente: </label>
								<select required="required" data-ng-model="selectedCar" id="selectRole">
                           <option value="">Selecciona su Nivel de Permiso</option>
                           <option data-ng-repeat="x in roles" value="{{x.name}}">{{x.name}}</option>
                        </select>                        
                        								
							</div>																								
							
							<div class="form-group">
								<label class="col-form-label">Password: </label> <input
									id="txtClientePassword" type="password" class="form-control"
									placeholder="Password" data-ng-model="password"
									required="required"
									pattern="^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$">
							</div>
							<button type="submit" class="btn btn-success"
								data-ng-click="guardarDatosCliente(nombre_cliente,apellido_paterno,apellido_materno,activo,password,selectedCar)">Guardar
								Datos</button>
							<button type="submit"class="btn btn-success"
								data-ng-click="cerrarFormularioCliente()">Cerrar</button>
						</form>
					</div>
				</div>

				<!--Tabla para Mostar clientes-->
				<div id="tablePricipal">
					<div class="card-body">
						<div class="table-responsive">
							<table id="tablaClientes" class="table table-hover" style="width: 100%;">
								<thead valign="top" class="thead-dark">
									<tr valign="top">
										<th align="center">Id Cliente</th>
										<th align="center">Nombre Cliente</th>
										<th align="center">Apellido Paterno</th>
										<th align="center">Apellido Materno</th>																		
										<th align="center">Actualizar</th>
										<th align="center">Eliminar</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				
				
				
				

				<!-- Modal para actualizar Datos cliente -->
				<div class="modal fade" id="myModalCliente" tabindex="-1"
					role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="modalLabel">Actualizar datos
									del cliente</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>

							<div class="modal-body">
								<form id="clienteForm2">
							<div class="form-group">
										<label for="recipient-name" class="col-form-label">ID
											Cliente: </label> <input id="txtidc" type="text" class="form-control"
											placeholder="Id" data-ng-model="producto.id_cliente"
											required="required"
											pattern="^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$"
											disabled="disabled">
							</div>		
							
							<div class="form-group">
								<label class="col-form-label">Nombre del Cliente: </label> <input
									id="txtClienteNamec" type="text" class="form-control"
									placeholder="Nombre del Cliente" data-ng-model="nombre_cliente"
									required="required"
									pattern="^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$">
							</div>
							
							<div class="form-group">	
							<label class="col-form-label">Apellido Paterno: </label>						
								<input id="txtClienteApellidoPc" type="text" class="form-control"
									placeholder="Apellido Paterno" data-ng-model="apellido_paterno"
									required="required"
									pattern="^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$">
                     </div>
                     
                     <div class="form-group">
                     <label class="col-form-label">Apellido Materno: </label>
								<input id="txtClienteApellidoMc" type="text" class="form-control"
									placeholder="Apellido Materno" data-ng-model="apellido_materno"
									required="required"
									pattern="^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$">
							</div>

							<div class="form-group">
								<label class="col-form-label">Seleccione: </label>
								 <select class="form-select form-select-sm" aria-label=".form-select-sm example" 
								 required="true"  id="singleSelectc" ng-model="activo">								
									<option value="">PUEDE CONSULTAR EL USUARIO SU RECIBO CFE ?</option>									
									<option value="true">Si</option>									
									<option value="false">No</option>
								</select>															
                     							
							</div>
							<div class="form-group">
								<label class="col-form-label">Password: </label> <input
									id="txtClientePasswordc" type="password" class="form-control"
									placeholder="Password" data-ng-model="password"
									required="required"
									pattern="^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$">
							</div>
								</form>
							</div>

							<div class="modal-footer">
								<button type="submit" class="btns"
									data-ng-click="actualizarDatosCliente()">Actualizar
									Datos</button>
								<button type="button" class="btns"
									data-dismiss="modal" style="width: 180px;">Cancelar</button>
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

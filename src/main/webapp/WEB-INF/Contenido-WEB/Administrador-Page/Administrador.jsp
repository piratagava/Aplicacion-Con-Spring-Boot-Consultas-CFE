<%--  
  User: hernan
  Date: 19/10/20
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
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
<script src="https://kit.fontawesome.com/64d58efce2.js" crossorigin="anonymous"></script>

<link rel="stylesheet" href="css_global/login.css"/>
<script type="text/javascript" src="js/LoginCliente/client.js"></script>

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


	<div id="login">
		<img class="wave" src="img/wave.png">
		<div class="login-container">
			<div class="img">
				<img src="img/acss.svg">
			</div>
			<div class="login-content">
				<form id="loginForm">
					<img src="img/wdp.svg">
					<div class="alert alert-danger" id="notLoggedIn">Es Necesario Iniciar
						Sesion!</div>

					<h2 class="title">Bienvenido!</h2>
					<div class="input-div one">
						<div class="i">
							<i class="fas fa-user"></i>
						</div>
						<div class="div">
							<h5>Escriba su Nombre</h5>
							<input type="text" class="input" id="exampleInputEmail1" required
								name="username"
							>
						</div>
					</div>
					<div class="input-div pass">
						<div class="i">
							<i class="fas fa-lock"></i>
						</div>
						<div class="div">
							<h5>Escriba su contraseña</h5>
							<input type="password" class="input" id="exampleInputPassword1" required
								name="password"
							>
						</div>
					</div>
					<a  href="#openModal">No sabe cuales son sus datos para iniciar sesion da click aqui?</a>
					<button type="submit" class="btns" style="width: 200px; ">Entrar</button>
				</form>
			</div>
		</div>
	</div>
	<!-- fin login div  -->
	
	
	<div id="openModal" class="modalDialog">
      <div>
      <a href="#close" title="Close" class="closed">X</a>
      <h2>Como ingreso ?</h2>
      <p>1.-Proporcione solo su nombre tal como esta en su recibo de luz sin apellidos.</p>
      <p>2.-Su contraseña es el numero de servicio que tiene registrado su recibo de luz.</p>
      <p style="color: red;">Consulte su recibo para ingresar esos dos datos muchas gracias.</p>
      </div>
   </div>


	<div id="habilitarSiEstaLogeado">
		<!-- LLamo el menu Logout y barra principal de ecabezado de Admin -->
		<jsp:include page="../Recursos-Web/NavarLogout.jsp"></jsp:include>

		<div class="container-fluid">
			<div class="row">

				<!-- LLamo el menu principal de Admin -->
				<jsp:include page="../Recursos-Web/MenuPricipalAdministrador.jsp"></jsp:include>

				<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">

					<!--Para Bienvenida-->
					<div id="bienvenidaAdmin">
						<div
							class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom"
						>
							<h1 class="h2" style="align-content: center; align-items: center;">
								BIENVENIDO AL SISTEMA</h1>
							<button type="button" class="btn btn-outline-success" id="logoutButton">Cerrar Sesion</button>							
						</div>
					</div>
				</main>
			</div>
		</div>

	</div>


	<!-- Modal para actualizar Datos cliente -->
	<div class="modal fade" id="myModalUserSinDetalles" tabindex="-1" role="dialog"
		aria-labelledby="modalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalLabel">CFE Consultas Dice</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="card-body">
						<h1 class="card-title pricing-card-title">
							Detalles <small class="text-muted">del Servicio </small>
						</h1>
						<ul class="list-unstyled mt-3 mb-4">
							<li>No se encontro sus registro de recibo para mostrar, se debe por
								2 razones:</li>
							<li>1.-Usted no pertenece a la zona que desea consultar su servicio</li>
							<li>2.-El Administrador del sistema no ah registrado sus datos</li>
							<li>En el caso 2 intente mas tarde estamos trabajando para que pueda
								consultarlo GRACIAS !!</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="myModalUserDetalles" tabindex="-1" role="dialog"
		aria-labelledby="modalLabel" aria-hidden="true"
	>
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalLabel">Detalle Del Servicio CFE</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="card-body">						
						<div class="form-group">
						<label for="recipient-name" class="col-form-label">Nombre del Cliente:</label>
                  <label for="recipient-name" class="col-form-label" id="txtCliente"></label>
                                                   
                  <label for="recipient-name" class="col-form-label" id="txtClienteAP"></label>
                  <label for="recipient-name" class="col-form-label" id="txtClienteAM"></label>                  					
						</div>
						
						<div class="form-group">
						   <label for="recipient-name" class="col-form-label">Nombre de la Comunidad:</label>
                     <label for="recipient-name" class="col-form-label" id="txtDir"></label>                     
                  </div>

						<div class="form-group">											                  
                     <p style="color:#FF0000";>
                     Pase a pagar con la persona encargada de su comunidad antes de la fecha 
                     <label for="recipient-name" class="col-form-label" id="txtlimitePago"></label>
                     de lo contrario su corte de luz sera el dia:
                    <label for="recipient-name" class="col-form-label" id="txtCorte"></label>   
                     </p>                     						                                       
						</div>

						<div class="form-group">
							<label for="recipient-name" class="col-form-label">Total a Pagar: $</label>
                     <label style="color:black"; for="recipient-name" class="col-form-label" id="txtPago"></label>
                     <label style="color:red"; for="recipient-name" class="col-form-label">Favor de Pagar con Cambio Gracias!!</label>
						</div>
						<center><button style="width: 180px" type="button" class="btns" id="logoutButtons">Cerrar</button></center>						
					</div>               
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="js/responsiveMain.js"></script>
</body>
</html>

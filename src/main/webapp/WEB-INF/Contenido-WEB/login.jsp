<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
   <title>Join CFE Notification's Garcia</title>

    <!-- Importamos las librerias de Java-Core y JSP-->
    <%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/include.jsp"%>
    <%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/notificaciones.jsp"%>
    <%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/fontawesome.jsp" %>
      <script src="https://kit.fontawesome.com/64d58efce2.js" crossorigin="anonymous"></script>
    <script src="js/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="/css_global/login.css" />
    <script type="text/javascript" src="js/LoginCliente/client.js"></script>
</head>
<body>
  <img class="wave" src="img/wave.png">
   <div class="container">
      <div class="img">
         <img src="img/acss.svg">
      </div>
      <div class="login-content">
         <form id="loginForm">
            <img src="img/wdp.svg">
            <div class="alert alert-danger" id="notLoggedIn">Es Necesario Iniciar Sesion!</div>
            <h2 class="title">Bienvenido!</h2>
            <div class="alert alert-info text-hidden" id="loggedIn"></div>
               <div class="input-div one">
                  <div class="i">
                        <i class="fas fa-user"></i>
                  </div>
                  <div class="div">
                        <h5>Escriba su Nombre</h5>
                        <input  type="text" class="input" id="exampleInputEmail1" required name="username">
                  </div>
               </div>
               <div class="input-div pass">
                  <div class="i">
                     <i class="fas fa-lock"></i>
                  </div>
                  <div class="div">
                     <h5>Escriba su contraseña</h5>
                     <input type="password" class="input" id="exampleInputPassword1" required name="password">
                  </div>
               </div>
               <a href="#">Forgot Password?</a>
               <button type="submit" class="btn">Entrar</button>
            </form>
        </div>
    </div>

    <script type="text/javascript" src="js/responsiveMain.js"></script>

</body>
</html>

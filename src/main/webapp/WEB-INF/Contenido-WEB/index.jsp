<%--
  User: hernan
  Date: 12/10/20
  Time: 0:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Hernán García">
    <meta name="generator" content="Jekyll v4.1.1">
    <title>CFE Notification's Garcia</title>

    <!-- Importamos las librerias de Java-Core y JSP-->
    <%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/include.jsp"%>
    <%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/bootstrap.jsp"%>
    <%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/angular.jsp"%>
    <%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/carruselStyleGlobal.jsp"%>
    <%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/notificaciones.jsp"%>

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }
        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>

<main role="main">

    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
            <li data-target="#myCarousel" data-slide-to="3"></li>
            <li data-target="#myCarousel" data-slide-to="4"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="img/CFEPrincipal.jpg" width="100px">
                <div class="container">
                    <div class="carousel-caption text-left">
                        <p align="center"><a class="btn btn-lg btn-success" href="es-login-page-Ing_Hernan"
                              role="button">Consulta tu Recibo Aqui</a></p>
                    </div>
                </div>
            </div>

            <div class="carousel-item">
                <img src="img/Cfejuntos.jpg" width="100px">
            </div>

            <div class="carousel-item">
                <img src="img/banner-PNEE.jpg" width="100px">
            </div>

            <div class="carousel-item">
                <img src="img/wifiCfe.jpeg" width="100px">
            </div>

            <div class="carousel-item">
                <img src="img/AhorrarLuz.jpg" width="100px">
            </div>

        </div>
        <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>

    </div>


    <!-- Marketing messaging and featurettes
    ================================================== -->
    <div class="container marketing">

        <!-- START THE FEATURETTES -->
        <hr class="featurette-divider">
        <div class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading">Comisión Federal de Electricidad</h2>
                <p style="text-align: justify;" class="lead">Esta es una página pública dirigida a toda la
                    comunidad, con la finalidad de brindar un mejor servicio,
                    notificando el periodo de entrega de sus recibos y costo del mismo.</p>
            </div>
            <div class="col-md-5">
                <img src="img/energía1.jpg" style=" height:310px"></img>
            </div>
        </div>

        <!-- START THE FEATURETTES -->
        <hr class="featurette-divider">
        <div class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading">Aviso Importante !!</h2>
                <p style="text-align: justify;" class="lead">Esta página no solicita ningun tipo
                    de información personal de ninguna indole ni tampoco algun otro dato. Ten cuidado
                    y no te dejes sorprender.
                </p>
            </div>
            <div class="col-md-5">
                <img src="img/BannerPagoTarjeta.jpg" style=" height:305px">
            </div>
        </div>

        <hr class="featurette-divider">
        <!-- Three columns of text below the carousel -->
        <div class="row">
            <div class="col-lg-4">
                <img src="img/masxRes.png"
                     class="bd-placeholder-img rounded-circle"
                width="160" height="160" preserveAspectRatio="xMidYMid slice" focusable="false"
                     role="img" aria-label="Placeholder: 140x140"><rect width="100%"
                                                                        height="100%" fill="#777"/></img>
                <h2>Comisión Federal de Electricidad
                </h2>
                <p>Es una Empresa Productiva del Estado, propiedad exclusiva
                    del Gobierno Federal, con personalidad jurídica y patrimonio propio, que
                    goza de autonomía técnica, operativa y de gestión, conforme a lo dispuesto en
                    la Ley de la CFE.</p>
                <p><a class="btn btn-outline-success"
                      href="https://www.cfe.mx/acercacfe/Quienes%20somos/Pages/conceptocfe.aspx"
                      role="button">Quienes Somos &raquo;</a></p>
            </div><!-- /.col-lg-4 -->            <div class="col-lg-4">
                <img src="img/mex.png" class="bd-placeholder-img rounded-circle"
                     width="140" height="140" preserveAspectRatio="xMidYMid slice" focusable="false"
                     role="img" aria-label="Placeholder: 140x140"><rect width="100%"
                                                                        height="100%" fill="#777"/></img>
                <h2>Servicio Principal</h2>
                <p>Podras consultar mediante esta página web cuanto $ debes pagar para tu actual
                    recibo, asi como el lugar conociendo las fechas aproximadas para realizar el
                    pago de una manera mas eficiente describiendo algunos conceptos basicos de tu
                    servicio de luz .</p>
                <p><a class="btn btn-outline-success" href="#" role="button">Consulta &raquo;</a></p>
            </div><!-- /.col-lg-4 -->
            <div class="col-lg-4">
                <img src="img/pilaRes.png" class="bd-placeholder-img rounded-circle"
                     width="140" height="150" preserveAspectRatio="xMidYMid slice" focusable="false"
                     role="img" aria-label="Placeholder: 140x140"><rect width="100%"
                                                                        height="100%" fill="#777"/></img>
                <h2>Objetivo Principal</h2>
                <p>El Plan Nacional de Energía Eléctrica es fortalecer e impulsar la
                    capacidad de generación de la Comisión Federal de Electricidad (CFE), para
                    atender las necesidades de acceso al servicio de energía eléctrica de la población.</p>
                <p><a class="btn btn-outline-success"
                      href="https://www.cfe.mx/acercacfe/Quienes%20somos/Pages/plan-nacional-energia.aspx" role="button">Saber más &raquo;</a></p>
            </div><!-- /.col-lg-4 -->
        </div><!-- /.row -->

    </div><!-- /.container -->


    <!-- FOOTER -->
    <footer class="container">
        <p class="float-right"><a href="#">Back</a></p>
        <p>Creado por el Ingeniero Hernán García Valladares <a href="#">Acerca de</a> &middot;</p>
        <p>&copy; 2020-2021 Company CFE, Inc. &middot;</p>
    </footer>
</main>
</body>
</html>

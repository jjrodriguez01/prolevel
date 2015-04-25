<%-- 
    Document   : reestablecer
    Created on : 7/03/2015, 07:40:22 PM
    Author     : jeisson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/estilosreestablecer.css">
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="css/bootstrap/js/bootstrap.min.js"></script>
        <script src='https://www.google.com/recaptcha/api.js'></script>
        <style>
body{
    padding-top: 5px;
    margin: 0 10%;
}
nav{
    background: linear-gradient(white, whitesmoke);
}
        </style>
        <title>Reestabler contraseña</title>
    </head>
    <body>
        <header class="container">
            <nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
        <a class="navbar-brand" href="#">
            <img alt="pro-level" src="imagenes/logo.png" style="width: 35px; height: 40px">
        </a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Reestablecer contraseña <span class="sr-only">(current)</span></a></li>
        <li><a href="index.jsp">Ingresar</a></li>
        <li><a href="registro.jsp">Registrarme</a></li>
      </ul>
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Buscar...">
        </div>
        <button type="submit" class="btn btn-default">Buscar en Pro-level</button>
      </form>
        <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Nosotros <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">¿Quienes Somos?</a></li>
            <li><a href="#">Contáctanos</a></li>
          </ul>
        </li>
    </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
        </header>
        <main class="container">
            <div class="row">
            <div class="col-lg-12">
                <ol class="breadcrumb">
                    <li><a href="index.jsp">Inicio</a></li>
                    <li class="active"><a href="#">Reestablecer Contraseña</a></li>
                </ol>
            </div>
        </div>
            <div id="j" class="row" >
                <div class="">
                <h1>Reestablecer Contraseña</h1>
                <P>Si has olvidado tu contraseña, diligencia el formulario abajo y
                te enviaremos un correo electrónico</P>
                </div>
                <div class="row">
                <div id="formu" class="col-xs-12 col-sm-12 col-md-12 col-lg-6 center-block">
                    <form role="form" action="Recuperar">
  <div class="form-group">
      <label for="email">Email<span class="requerido"> *</span></label>
      <input type="email" name="email" id="email" class="form-control" id="ejemplo_email_1"
           placeholder="Introduce tu email" required>
  </div>
    <div class="form-group">
    <div class="g-recaptcha" data-sitekey="6Lc-KAMTAAAAACflZ_XeCXakQGNwb3FO0clkK8Ph"></div>
    </div>
    <button type="submit" name="recuperar" class="btn btn-default">Enviar</button>
</form>
                </div>
                </div>
            </div>
        </main>
    </body>
</html>

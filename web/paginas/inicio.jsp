<%-- 
    Document   : inicio
    Created on : 28/01/2015, 11:50:40 PM
    Author     : jeisson
--%>
<%@page import="modelo.UsuariosDTO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<sql:query var="torneo" dataSource="jdbc/pro-level">
    SELECT idTorneo, nombre FROM torneo
</sql:query>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
            if (request.getSession() != null) { 
                    UsuariosDTO udto = new UsuariosDTO();
                    HttpSession miSession=request.getSession(false);
                    udto = (UsuariosDTO)miSession.getAttribute("usr");
                    int rol = (Integer)miSession.getAttribute("rol");
                    if (rol == 1) {
                            
%>
<!doctype html>
<html lang="es">
<head>
<link rel="shortcut icon" href="../imagenes/favicon.ico">
<link href="../css/onepage-scroll.css" rel="stylesheet" type="text/css">
<link href="../css/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="../css/estiloslayout.css" rel="stylesheet" type="text/css">
<link href="../js/dataTables/css/dataTablesBootstrap.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../js/jquery.onepage-scroll.js"></script>
<script type="text/javascript" src="../css/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/Chart.min.js"></script>
<script type="text/javascript" src="../js/dataTables/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="../js/dataTables/js/datatablesbootstrap.js"></script>
<meta charset="utf-8">
<title>Pro-level::Inicio</title>
<style>
    body{
        margin: 0;
    }
    section.campeones{
        background-image: url(../imagenes/fondos/fondoindex.jpg);
        background-position: center;
        background-repeat: no-repeat;
    }
</style>

<script>
    $(document).ready(function(){
        $(".main").onepage_scroll();
        $("#torneos_creados").dataTable({
                    language:{
                        url: "../js/dataTables/js/dataespañol.json"
                    } 
                });
    });
</script>
</head>
<body>
<div class="main">
    <section class="container">
<header>
    <div style="float: right"><span class="label label-success">Bienvenido <%=udto.getPrimerNombre()%> te has logueado como:</span><span class="badge">Administrador</span></div>
    <nav class="navbar">
    <ul id="nav" class="nav">
      		<li><a href="inicio.jsp"><img src="../imagenes/inicio.png" width="24" height="24" alt="inicio" /> INICIO</a></li>
      		<li><a href="#"><span><img src="../imagenes/copa.png" width="24" height="24" alt="copa" /> TORNEOS</span></a><
        <div class="subs">
          <div class="col">
            <ul>
              <li><a><img src="../imagenes/micopa.png" width="24" height="24" alt="micopa"/>MIS TORNEOS</a>
              		<div class="subs">
                    	<div class="col">
                                    <ul>
                                        <c:forEach var="row" items="${torneo.rows}">
                                            <li><a href="torneos/misTorneos.jsp?idTorneo=${row.idTorneo}">${row.nombre}</a></li>
                                        </c:forEach>
                                    </ul>
                        </div>
                    </div>
                  </li>
              <li><a href="torneos/crear_torneo.jsp"><img src="../imagenes/crear.png" width="24" height="24" alt="crear" />CREAR TORNEOS</a></li>
            </ul>
          </div>
        </div>
      </li>
      <li><a href="#"><span><img src="../imagenes/telefono.png" width="24" height="24" alt="reservar" />RESERVAS</span></a>
        <div class="subs">
          <ul>
            <li><a href="#"><img src="imagenes/cancha.png" width="24" height="24" alt="reservas" />RESERVAR</a></li>
            <li><a href="#"><img src="imagenes/instructivo.png" width="24" height="24" alt="ins" />INSTRUCTIVO</a></li>
            <li><a href="#"><img src="imagenes/informe.png" width="24" height="24" alt="info" />INFORME DE RESERVAS</a></li>
          </ul>
        </div>
        </li>
      <li><a href="#"><img src="../imagenes/servicios.png" width="24" height="24" alt="servicios" />SERVICIOS</a></li>
      <li><a href="#"><span><img src="../imagenes/perfil.png" width="24" height="24" alt="perfil" />PERFIL</span></a>
      	<div class="subs">
        	<ul>
            	<li><a href="admin.jsp"><img src="../imagenes/ajustes.png" width="24" height="24" alt="ajustes" />ADMINISTRAR</a></li>
                <li><a href="../Ingreso?logout=cerrar"><img src="../imagenes/out.png" width="24" height="24" alt="cerrar" />CERRAR CESIÓN</a></li>
            </ul>
        </div>
        </li>
    </ul> 
</nav>
   
</header>
        
    <div class="row">
        <div class="well">
            <div class="page-header">
                <hgroup>
                    <h1>BIENVENIDO A PRO-LEVEL.</h1>
                    <h5>El software para la administracion de sus campeonatos de futbol y gestion de las reservas de sus canchas</h5>
                </hgroup>
            </div>
        </div>
    </div>
    <sql:query var="itorneos" dataSource="jdbc/pro-level">
        select torneo.nombre Torneo, torneo.fechaInicio,
        torneo.fechafin from torneo
    </sql:query>
    <div class="panel panel-default">
        <div class="panel-body">
          Estos son los torneos que has creado
        </div>
    </div>
        <table id="torneos_creados" class="table table-striped table-condensed table-hover table-responsive">
        <!-- column headers -->
        <tr>
            <th>Nombre Del Torneo</th>
            <th>Fecha De Inicio</th>
            <th>Fecha Finalizacion</th>
        </tr>
        <!-- column data -->
        <c:forEach var="row" items="${itorneos.rowsByIndex}">
            <tr>
                <c:forEach var="column" items="${row}">
                    <td><c:out value="${column}"/></td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>

</section>
    <section class="container">
        <div>
            <div class="alert alert-info" role="alert">
                <strong>Grafico De Goles</strong>
            </div>
        </div>
        <article>
            <div style="width: 50%">
                <canvas id="canvas" height="450" width="600"></canvas>
                <span class="label label-primary">
                    Este gráfico muestra el total de goles anotados en los torneos
                </span>
            </div>
        </article>        
    </section>
    <section class="campeones">
        <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="center-block">
                    <hgroup>
                        <h1>Cuadro de honor</h1>
                        <h2>Campenones de los últimos torneos</h2>
                    </hgroup>
                </div>
            </div>
        </div>
        <div class="row"> 
<sql:query var="campeones" dataSource="jdbc/pro-level">
    SELECT * FROM campeones 
</sql:query>
            <div class="col-lg-4">
            </div>
    
    <div class="col-lg-4">
        <table id="tcampeones" class="table table-striped table-condensed table-hover table-responsive">
            <thead>
                <th>Torneo</th>
                <th>Campeón</th>
            </thead>
            <tbody>
                <c:forEach var="row" items="${campeones.rows}">
                    <tr>
                        <td>${row.nombreTorneo}</td>
                        <td>${row.nombreEquipo}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <div class="col-lg-4"></div>
        </div>
    </div>
    </section>
<footer>
<div id="pie">
<p class="pie">2014 PRO-LEVEL - Todos los derechos reservados | Cambiar idioma <a href="#">
        <img src="../imagenes/english.png" width="40" height="30" /></a></p>  
</div>
</footer>
</div>
<sql:query var="goles" dataSource="jdbc/pro-level">
select torneo.nombre, sum(tablagoleadores.numeroGoles) as goles
from torneo inner join tablagoleadores
on torneo.idTorneo = tablagoleadores.idTorneo
</sql:query>
    <script>
	var randomScalingFactor = function(){ return Math.round(Math.random()*100)};

	var barChartData = {
		labels : [//aqui va un label con nombres de torneos en la grafica
                    <c:forEach var="row" items="${goles.rows}">
            "${row.nombre}",
                    </c:forEach>
                
            ],
		datasets : [
			{//aqui van los datos de la grafica, los numeros
				fillColor : "rgba(151,187,205,0.5)",
				strokeColor : "rgba(151,187,205,0.8)",
				highlightFill : "rgba(151,187,205,0.75)",
				highlightStroke : "rgba(151,187,205,1)",
				data : [
                    <c:forEach var="row" items="${goles.rows}">
                        "${row.goles}",
                    </c:forEach>
                                ]
			}
		]

	}
	window.onload = function(){
		var ctx = document.getElementById("canvas").getContext("2d");
		window.myBar = new Chart(ctx).Bar(barChartData, {
			responsive : true
		});
	}

	</script>
</body>
</html>
<% }//si el rol fue uno se muestra la anterior pagina
    else if (rol == 2) {
        %>
<!doctype html>
<html>
<head>
<link rel="shortcut icon" href="../imagenes/favicon.ico">
<link href="../css/onepage-scroll.css" rel="stylesheet" type="text/css">
<link href="../css/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="../css/estiloslayout.css" rel="stylesheet" type="text/css">
<link href="../js/dataTables/css/dataTablesBootstrap.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../js/jquery.onepage-scroll.js"></script>
<script type="text/javascript" src="../css/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/Chart.min.js"></script>
<script type="text/javascript" src="../js/dataTables/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="../js/dataTables/js/datatablesbootstrap.js"></script>
<meta charset="utf-8">
<title>Pro-level::Inicio</title>
<style>
    body{
        margin: 0;
    }
    section.campeones{
        background-image: url(../imagenes/fondos/fondoindex.jpg);
        background-position: center;
        background-repeat: no-repeat;
    }
</style>

<script>
    $(document).ready(function(){
        $(".main").onepage_scroll();
        $("#torneos_creados").dataTable({
                    language:{
                        url: "../js/dataTables/js/dataespañol.json"
                    } 
                });
    });
</script>
</head>
<body>
<div class="main">
    <section class="container">
<header>
    <nav class="navbar">
    <ul id="nav" class="nav">
      		<li><a href="inicio.jsp"><img src="../imagenes/inicio.png" width="24" height="24" alt="inicio" /> INICIO</a></li>
      		<li><a href="#"><span><img src="../imagenes/copa.png" width="24" height="24" alt="copa" /> TORNEOS</span></a><
        <div class="subs">
          <div class="col">
            <ul>
                <li><a><img src="../imagenes/micopa.png" width="24" height="24" alt="micopa"/>MIS TORNEOS</a>
              		<div class="subs">
                    	<div class="col">
                                    <ul>
                                        <c:forEach var="row" items="${torneo.rows}">
                                            <li><a href="jugador/vermisTorneos.jsp?idTorneo=${row.idTorneo}">${row.nombre}</a></li>
                                        </c:forEach>
                                    </ul>
                        </div>
                    </div>
                </li>
            </ul>
          </div>
        </div>
      </li>
      <li><a href="#"><span><img src="../imagenes/telefono.png" width="24" height="24" alt="reservar" />RESERVAS</span></a>
        <div class="subs">
          <ul>
            <li><a href="#"><img src="imagenes/cancha.png" width="24" height="24" alt="reservas" />RESERVAR</a></li>
            <li><a href="#"><img src="imagenes/instructivo.png" width="24" height="24" alt="ins" />INSTRUCTIVO</a></li>
            <li><a href="#"><img src="imagenes/informe.png" width="24" height="24" alt="info" />INFORME DE RESERVAS</a></li>
          </ul>
        </div>
        </li>
      <li><a href="#"><img src="../imagenes/servicios.png" width="24" height="24" alt="servicios" />SERVICIOS</a></li>
      <li><a href="#"><span><img src="../imagenes/perfil.png" width="24" height="24" alt="perfil" />PERFIL</span></a>
      	<div class="subs">
        	<ul>
            	<li><a href="admin.jsp"><img src="../imagenes/ajustes.png" width="24" height="24" alt="ajustes" />ADMINISTRAR</a></li>
                <li><a href="../Ingreso?logout=cerrar"><img src="../imagenes/out.png" width="24" height="24" alt="cerrar" />CERRAR CESIÓN</a></li>
            </ul>
        </div>
        </li>
    </ul>
</nav>
</header>
    <div class="row">
        <div class="well">
            <div class="page-header">
                <hgroup>
                    <h1>BIENVENIDO A PRO-LEVEL.</h1>
                    <h5>El software para la administracion de sus campeonatos de futbol y gestion de las reservas de sus canchas</h5>
                </hgroup>
            </div>
        </div>
    </div>
    <sql:query var="itorneos" dataSource="jdbc/pro-level">
        select torneo.nombre Torneo, torneo.fechaInicio,
        torneo.fechafin from torneo
    </sql:query>
    <div class="panel panel-default">
        <div class="panel-body">
          Estos son los torneos que has creado
        </div>
    </div>
        <table id="torneos_creados" class="table table-striped table-condensed table-hover table-responsive">
        <!-- column headers -->
        <tr>
            <th>Nombre Del Torneo</th>
            <th>Fecha De Inicio</th>
            <th>Fecha Finalizacion</th>
        </tr>
        <!-- column data -->
        <c:forEach var="row" items="${itorneos.rowsByIndex}">
            <tr>
                <c:forEach var="column" items="${row}">
                    <td><c:out value="${column}"/></td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>

</section>
    <section class="container">
        <div>
            <div class="alert alert-info" role="alert">
                <strong>Grafico De Goles</strong>
            </div>
        </div>
        <article>
            <div style="width: 50%">
                <canvas id="canvas" height="450" width="600"></canvas>
                <span class="label label-primary">
                    Este gráfico muestra el total de goles anotados en los torneos
                </span>
            </div>
        </article>        
    </section>
    <section class="campeones">
        <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="center-block">
                    <hgroup>
                        <h1>Cuadro de honor</h1>
                        <h2>Campenones de los últimos torneos</h2>
                    </hgroup>
                </div>
            </div>
        </div>
        <div class="row"> 
<sql:query var="campeones" dataSource="jdbc/pro-level">
    SELECT * FROM campeones 
</sql:query>
            <div class="col-lg-4">
            </div>
    
    <div class="col-lg-4">
        <table id="tcampeones" class="table table-striped table-condensed table-hover table-responsive">
            <thead>
                <th>Torneo</th>
                <th>Campeón</th>
            </thead>
            <tbody>
                <c:forEach var="row" items="${campeones.rows}">
                    <tr>
                        <td>${row.nombreTorneo}</td>
                        <td>${row.nombreEquipo}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <div class="col-lg-4"></div>
        </div>
    </div>
    </section>
<footer>
<div id="pie">
<p class="pie">2014 PRO-LEVEL - Todos los derechos reservados | Cambiar idioma <a href="#">
        <img src="../imagenes/english.png" width="40" height="30" /></a></p>  
</div>
</footer>
</div>
<sql:query var="goles" dataSource="jdbc/pro-level">
select torneo.nombre, sum(tablagoleadores.numeroGoles) as goles
from torneo inner join tablagoleadores
on torneo.idTorneo = tablagoleadores.idTorneo
</sql:query>
    <script>
	var randomScalingFactor = function(){ return Math.round(Math.random()*100)};

	var barChartData = {
		labels : [//aqui va un label con nombres de torneos en la grafica
                    <c:forEach var="row" items="${goles.rows}">
            "${row.nombre}",
                    </c:forEach>
                
            ],
		datasets : [
			{//aqui van los datos de la grafica, los numeros
				fillColor : "rgba(151,187,205,0.5)",
				strokeColor : "rgba(151,187,205,0.8)",
				highlightFill : "rgba(151,187,205,0.75)",
				highlightStroke : "rgba(151,187,205,1)",
				data : [
                    <c:forEach var="row" items="${goles.rows}">
                        "${row.goles}",
                    </c:forEach>
                                ]
			}
		]

	}
	window.onload = function(){
		var ctx = document.getElementById("canvas").getContext("2d");
		window.myBar = new Chart(ctx).Bar(barChartData, {
			responsive : true
		});
	}

	</script>
</body>
</html>
<%                
    }//si el rol fue 2
    }//si no hay sesion
            else{
                response.sendRedirect("index.jsp");
            }
        
%>


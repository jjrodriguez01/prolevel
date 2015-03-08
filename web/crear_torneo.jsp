<%-- 
    Document   : crear_torneo
    Created on : 24/02/2015, 04:18:46 AM
    Author     : jeisson
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<sql:query var="torneo" dataSource="jdbc/pro-level">
    SELECT idTorneo, nombre FROM torneo
</sql:query>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><!-- InstanceBegin template="/Templates/layout.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8">
<!-- InstanceBeginEditable name="doctitle" -->
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="head" -->
<meta charset="utf-8">
<title>Crear Torneo</title>
<link href="css/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/estiloslayout.css" rel="stylesheet" type="text/css">
<link href="css/estiloscrear_torneo.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="engine1/style.css" />
<script type="text/javascript" src="engine1/jquery.js"></script>
<script type="text/javascript" src="js/listaTorneo.js"></script>
</head>
<body>
<header>

<nav>
    <ul id="nav" class="nav">
      		<li><a href="inicio.jsp"><img src="imagenes/inicio.png" width="24" height="24" alt="inicio" /> INICIO</a></li>
      		<li><a href="#"><span><img src="imagenes/copa.png" width="24" height="24" alt="copa" /> TORNEOS</span></a><
        <div class="subs">
          <div class="col">
            <ul>
              <li><a><img src="imagenes/micopa.png" width="24" height="24" alt="micopa"/>MIS TORNEOS</a>
              		<div class="subs">
                    	<div class="col">
                        	<ul name="idTorneo" onchange="">
                                        <c:forEach var="row" items="${torneo.rows}">
                                            <li value="${row.idtorneo}" onclick="enviar(this)"><a>${row.nombre}</a></li>
                                        </c:forEach>
                                </ul>
                            <form method="get" action="misTorneos.jsp" name="torneosel" id="torneosel">
                                <input type="hidden" id="idTorneo" name="idTorneo" />
                        </form>
                        </div>
                    </div>
                  </li>
              <li><a href="crear_torneo.jsp"><img src="imagenes/crear.png" width="24" height="24" alt="crear" />CREAR TORNEOS</a></li>
            </ul>
          </div>
        </div>
      </li>
      <li><a href="#"><span><img src="imagenes/telefono.png" width="24" height="24" alt="reservar" />RESERVAS</span></a>
        <div class="subs">
          <ul>
            <li><a href="#"><img src="imagenes/cancha.png" width="24" height="24" alt="reservas" />RESERVAR</a></li>
            <li><a href="#"><img src="imagenes/instructivo.png" width="24" height="24" alt="ins" />INSTRUCTIVO</a></li>
            <li><a href="#"><img src="imagenes/informe.png" width="24" height="24" alt="info" />INFORME DE RESERVAS</a></li>
          </ul>
        </div>
        </li>
      <li><a href="#"><img src="imagenes/servicios.png" width="24" height="24" alt="servicios" />SERVICIOS</a></li>
      <li><a href="#"><span><img src="imagenes/perfil.png" width="24" height="24" alt="perfil" />PERFIL</span></a>
      	<div class="subs">
        	<ul>
            	<li><a href="perfil_admin.jsp"><img src="imagenes/ajustes.png" width="24" height="24" alt="ajustes" />ADMINISTRAR</a></li>
            </ul>
        </div>
        </li>
    </ul>
</nav>
</header>
<!-- InstanceBeginEditable name="body" -->
<main>
<section >
<p id="titulo">CREAR UN NUEVO TORNEO</p>
</section>
<section id="presentacion">
	<!-- Start WOWSlider.com BODY section -->
	<div id="wowslider-container1">
	<div class="ws_images"><ul>
		<li><img src="data1/images/balonpasto.jpg" alt="balonpasto" title="" id="wows1_0"/></li>
		<li><img src="data1/images/copa.jpg" alt="copa" title="" id="wows1_1"/></li>
		<li><img src="data1/images/copas.jpg" alt="copas" title="" id="wows1_2"/></li>
		<li><img src="data1/images/futbol.jpg" alt="futbol" title="" id="wows1_3"/></li>
		<li><img src="data1/images/tabla.png" alt="tabla" title="" id="wows1_4"/></li>
		<li><img src="data1/images/tacticas.jpg" alt="tacticas" title="" id="wows1_5"/></li>
	</ul></div>
<div class="ws_bullets"><div>
		<a href="#" title="balonpasto"><img src="data1/tooltips/balonpasto.jpg" alt="balonpasto"/>1</a>
		<a href="#" title="copa"><img src="data1/tooltips/copa.jpg" alt="copa"/>2</a>
		<a href="#" title="copas"><img src="data1/tooltips/copas.jpg" alt="copas"/>3</a>
		<a href="#" title="futbol"><img src="data1/tooltips/futbol.jpg" alt="futbol"/>4</a>
		<a href="#" title="tabla"><img src="data1/tooltips/tabla.png" alt="tabla"/>5</a>
		<a href="#" title="tacticas"><img src="data1/tooltips/tacticas.jpg" alt="tacticas"/>6</a>
	</div></div>
	<div class="ws_shadow"></div>
	</div>
	<script type="text/javascript" src="engine1/wowslider.js"></script>
	<script type="text/javascript" src="engine1/script.js"></script>
	<!-- End WOWSlider.com BODY section -->
</section>
<section id="tipotorneo">
	<p>Seleccione el tipo de torneo que va a crear</p>
	<ul id="tipostorneo">
    	<li class="btn btn-info btn-lg"><a href="copa.jsp">COPA</a></li>
        <li class="btn btn-info btn-lg"><a href="liga.jsp">LIGA</a></li>
        <li class="btn btn-info btn-lg"><a href="eliminatoria.jsp">ELIMINATORIA</a></li>
    </ul>

</section>
<section id="informacion">
		<p class="info" onClick="copa()"><img src="imagenes/flecha.png" width="24" height="24" />¿Que es una copa?</p>
		<p class="info" onClick="liga()"><img src="imagenes/flecha.png" width="24" height="24" />¿Que es una liga?</p>
		<p class="info" onClick="eliminatoria()"><img src="imagenes/flecha.png" width="24" height="24" />¿Que es una eliminatoria?</p>
		<p id="qcopa"></p>
	<button id="btn" onClick="cerrar()">Cerrar</button>
	<script>
	function copa(){
		document.getElementById("qcopa").innerHTML= "Una copa es un estilo de torneo donde los participantes se dividen en grupos de 4 u 8, usted podra elegir si en esa fase de grupos los equipos juegan partidos de ida y vuelta o un unico partido. despues de la ronda de grupos, pasan los dos primeros de cada uno, se enfrentan en octavos de final donde usted podra elegir si lo hacen a partido unico o ida y vuelta, podra elegir partidos a ida y vuelta tambien en los cuartos de final, semifinal y final. tendra la opcion de habilitar un partido por el tercer puesto de la copa con los 2 equipos que perdieron en semifinal";
		document.getElementById("btn").style.visibility="visible";
		document.getElementById("qcopa").style.display="block";
		document.getElementById("btn").style.display="inline";
		}
	function liga(){
		document.getElementById("qcopa").innerHTML= "En una liga se enfrentan todos contra todos, podrá elegir si los enfrentamnientos son a partido único o a ida y vuelta. el equipo que tenga mas puntos al finalizar todos los partidos, será el campeón, si hay dos equipos con el mismo puntaje, el campeon será el equipo que tenga una mayor diferencia entre goles a favor y en contra según el resultado de los partidos jugados entre ellos. Si el empate persiste, el que tenga la mayor diferencia de goles a favor teniendo en cuenta todos los obtenidos y recibidos en el transcurso de la competición sera el campeon. Si aún asi los equipos implicados siguen empatados se definira por el equipos  que haya marcado más goles";
		document.getElementById("btn").style.visibility="visible";
		document.getElementById("qcopa").style.display="block";
		document.getElementById("btn").style.display="inline";
		}		
	function eliminatoria(){
		document.getElementById("qcopa").innerHTML= "En una eliminatoria los equipos juegan todos contra todos a partido unico con muerte súbita, es decir equipo que pierda sera eliminado";
		document.getElementById("btn").style.visibility="visible";
		document.getElementById("qcopa").style.display="block";
		document.getElementById("btn").style.display="inline";}	
	function cerrar() {
		document.getElementById("qcopa").style.display="none";
		document.getElementById("btn").style.display="none";
		}

		</script>
</section>
</main>
<footer>
<p class="pie">2014 PRO-LEVEL - Todos los derechos reservados | Cambiar idioma <a href="#"><img src="imagenes/español.png" width="40px" height="30px" /></a></p> 
<img src="imagenes/cesped.png" width="100%" height="100px"/> </footer>

<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd --></html>

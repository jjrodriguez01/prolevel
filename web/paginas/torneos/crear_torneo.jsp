<%-- 
    Document   : crear_torneo
    Created on : 24/02/2015, 04:18:46 AM
    Author     : jeisson
--%>
<%@page import="persistencia.UsuariosDAO"%>
<%@page import="modelo.UsuariosDTO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<sql:query var="torneo" dataSource="jdbc/pro-level">
    SELECT idTorneo, nombre FROM torneo
</sql:query>
<%
    if (request.getSession() != null) { 
                UsuariosDTO udto = new UsuariosDTO();
                    UsuariosDAO udao = new UsuariosDAO();
                    HttpSession miSession=request.getSession(false);
                    udto = (UsuariosDTO)miSession.getAttribute("usr");
                    int rol = (Integer)miSession.getAttribute("rol");
                    if (rol == 1) {
%>    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Crear Torneos</title>
        <link href="../../js/datepicker/jquery-ui.min.css" rel="stylesheet" type="text/css">
        <link href="../../css/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="../../css/estiloslayout.css" rel="stylesheet" type="text/css">
        <link href="../../css/estiloscrear_torneo.css" rel="stylesheet" type="text/css">
        <link href="../../js/select/jquery-ui.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="../../js/listaTorneo.js"></script>
        <script type="text/javascript" src="../../js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="../../js/jquery.validate.js"></script>
        <script type="text/javascript" src="../../css/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../../js/datepicker/jquery-ui.min.js"></script>
        <script type="text/javascript" src="../../js/validacionTorneos.js"></script>
        <script type="text/javascript" src="../../js/select/jquery-ui.min.js"></script>
<script>
$(document).ready(function() {
    $(function() {
   $( ".datepicker" ).datepicker( "option", "minDate", 0 );
   $( ".datepicker" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
  });
});
</script>
</head>
<body>
<header>
<nav>
    <ul id="nav" class="nav">
        <li><a href="../inicio.jsp"><img src="../../imagenes/inicio.png" width="24" height="24" alt="inicio" /> INICIO</a></li>
        <li><a href="#"><span><img src="../../imagenes/copa.png" width="24" height="24" alt="copa" /> TORNEOS</span></a>
            <div class="subs">
                <div class="col">
                    <ul>
                        <li><a><img src="../../imagenes/micopa.png" width="24" height="24" alt="micopa"/>MIS TORNEOS</a>
                            <div class="subs">
                                <div class="col">
                                    <ul>
                                        <c:forEach var="row" items="${torneo.rows}">
                                            <li><a href="misTorneos.jsp?idTorneo=${row.idTorneo}">${row.nombre}</a></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </li>
                        <li><a href="crear_torneo.jsp"><img src="../../imagenes/crear.png" width="24" height="24" alt="crear" />CREAR TORNEOS</a></li>
                    </ul>
                </div>
            </div>
                    </li>
                    <li><a href="#"><span><img src="../../imagenes/telefono.png" width="24" height="24" alt="reservar" />RESERVAS</span></a>
                        <div class="subs">
                            <ul>
                                <li><a href="#"><img src="../../imagenes/cancha.png" width="24" height="24" alt="reservas" />RESERVAR</a></li>
                                <li><a href="#"><img src="../../imagenes/instructivo.png" width="24" height="24" alt="ins" />INSTRUCTIVO</a></li>
                                <li><a href="#"><img src="../../imagenes/informe.png" width="24" height="24" alt="info" />INFORME DE RESERVAS</a></li>
                            </ul>
                        </div>
                    </li>
                    <li><a href="#"><img src="../../imagenes/servicios.png" width="24" height="24" alt="servicios" />SERVICIOS</a></li>
                    <li><a href="#"><span><img src="../../imagenes/perfil.png" width="24" height="24" alt="perfil" />PERFIL</span></a>
                        <div class="subs">
                            <ul>
                                <li><a href="../admin.jsp"><img src="../../imagenes/ajustes.png" width="24" height="24" alt="ajustes" />ADMINISTRAR</a></li>
                                <li><a href="../../Ingreso?logout=cerrar"><img src="../../imagenes/out.png" width="24" height="24" alt="cerrar" />CERRAR CESIÓN</a></li>
                            </ul>
                        </div>
                    </li>
    </ul>
</nav>
</header>  
<main>
    <div id="encabezado" class="row">
        <div class="col-lg-12">
            <hgroup>
            <h1 id="titulo">CREAR UN NUEVO TORNEO</h1>
            <h2>Bienvenido al centro de creacion de torneos</h2>
            </hgroup>
            <div class="container"><hr/></div>
        </div>
        <div class="row">
            <div class="col-lg-12">
            <section class="container" id="informacion">
                <h6>Estos son los torneos que puede crear:</h6>
		<p class="info" onClick="copa()"><img src="../../imagenes/flecha.png" width="24" height="24" />¿Que es una copa?</p>
                <p id="qcopa" class="descripcion"></p>
                <p class="info" onClick="liga()"><img src="../../imagenes/flecha.png" width="24" height="24" />¿Que es una liga?</p>
		<p id="qliga" class="descripcion"></p>
                <p class="info" onClick="eliminatoria()"><img src="../../imagenes/flecha.png" width="24" height="24" />¿Que es una eliminatoria?</p>
		<p id="qeli" class="descripcion"></p>
                    <button id="btn" onClick="cerrar()">Cerrar</button>
<script>
	function copa(){
		document.getElementById("qcopa").innerHTML= "Una copa es un estilo de torneo donde los participantes se dividen en grupos de 4 u 8, usted podra elegir si en esa fase de grupos los equipos juegan partidos de ida y vuelta o un unico partido. despues de la ronda de grupos, pasan los dos primeros de cada uno, se enfrentan en octavos de final donde usted podra elegir si lo hacen a partido unico o ida y vuelta, podra elegir partidos a ida y vuelta tambien en los cuartos de final, semifinal y final. tendra la opcion de habilitar un partido por el tercer puesto de la copa con los 2 equipos que perdieron en semifinal";
		document.getElementById("btn").style.visibility="visible";
		document.getElementById("qcopa").style.display="block";
		document.getElementById("btn").style.display="inline";
		}
	function liga(){
		document.getElementById("qliga").innerHTML= "En una liga se enfrentan todos contra todos, podrá elegir si los enfrentamnientos son a partido único o a ida y vuelta. el equipo que tenga mas puntos al finalizar todos los partidos, será el campeón, si hay dos equipos con el mismo puntaje, el campeon será el equipo que tenga una mayor diferencia entre goles a favor y en contra según el resultado de los partidos jugados entre ellos. Si el empate persiste, el que tenga la mayor diferencia de goles a favor teniendo en cuenta todos los obtenidos y recibidos en el transcurso de la competición sera el campeon. Si aún asi los equipos implicados siguen empatados se definira por el equipos  que haya marcado más goles";
		document.getElementById("btn").style.visibility="visible";
		document.getElementById("qcopa").style.display="block";
		document.getElementById("btn").style.display="inline";
		}		
	function eliminatoria(){
		document.getElementById("qeli").innerHTML= "En una eliminatoria los equipos juegan todos contra todos a partido unico con muerte súbita, es decir equipo que pierda sera eliminado";
		document.getElementById("btn").style.visibility="visible";
		document.getElementById("qcopa").style.display="block";
		document.getElementById("btn").style.display="inline";}	
	function cerrar() {
		document.getElementById("qcopa").style.display="none";
                document.getElementById("qliga").style.display="none";
                document.getElementById("qeli").style.display="none";
		document.getElementById("btn").style.display="none";
		}

</script>
            </section>
            </div>
        </div>
        </div>
    
    <div class="container">        
    <div id="ftorneos" class="row">
        <div class="col-lg-4 col-md-4 col-sm-10 col-xs-12">
            <div class="row">
                <header>
                    <img src="../../imagenes/balon.png" width="40" height="40" alt="copa">
                    <h3>COPA</h3>
                </header>
            </div>
            <hr/>
            <div class="row">
<form id="copa" method="get" action="GestionCopa" role="form"> 
    <div class="form-group">
        <label for="nombreTorneo">Nombre del torneo</label>
      	<input id="nombreTorneo" name="nombreTorneo" type="text" maxlength="25" required>
    </div>
    <div class="form-group">
        <label for="capacidad">Capacidad de equipos</label>
        <select id="capacidad" name="capacidad" required>
            <option></option>
            <option>16</option>
            <option>32</option>
        </select>
    </div>
    <div class="form-group">
        <label for="grupos">Numero de equipos en caga grupo</label>
        <select id="grupos" name="numgrupos" required>
            <option></option>
            <option>4</option>
            <option>8</option>
        </select>
    </div>
    <div class="form-group">
        <label>En la fase de grupos</label>
        <label for="idaVueltagrupos">¿Se juegan partidos de ida y vuelta?</label>
        <select id="idaVueltagrupos" name="idaVueltagrupos" title="Debe seleccionar algo" required>
          <option></option>
          <option>Si</option>
          <option>No, partido único</option>
        </select>
    </div>
    <div class="form-group">
        <label>En la ronda eliminatoria</label>
        <label for="idaVueltaeli">¿Se juegan partidos de ida y vuelta?</label>
        <select id="idaVueltaeli" name="idaVueltaeli" required>
           <option></option>
           <option>Si</option>
           <option>No, partido único</option>
         </select>
    </div>
    <div class="form-group">
        <label for="finalIdaVuelta">¿El partido de la final se juega de ida y vuelta?</label>
        <select id="finalIdaVuelta" name="finalIdaVuelta" required>
            <option></option>
            <option>Si</option>
            <option>No, partido único</option>
        </select>
    </div>
    <div class="form-group">
        <label for="tercer">¿Se juega partido pot tercer puesto?</label>
        <select id="tercer" name="tercer" required>
          <option></option>
          <option>Si</option>
          <option>No</option>
        </select>
    </div>
    <div class="form-group">
        <label>Torneo:</label>
        <select id="tipo" name="tipo">
            <option></option>
            <option>Masculino</option>
            <option>Femenino</option>
        </select>
    </div>
    <div class="form-group">
        <label for="inicio">Fecha de inicio:</label>
        <input type="text" class="datepicker" name="inicio" required>
    </div>
    <div class="form-group">
        <label for="fin">Fecha de finalizacion:</label>
        <input type="text" class="datepicker" name="fin" required>
    </div>                                                           
    <div class="form-group">                   
        <button class="btn btn-primary" type="submit" name="copa">CREAR</button>
        <input type="hidden" name="enviarcopa" />
    </div>
  </form>
            </div>
            <%//si hay parametro de q se creo una copa
              if (request.getParameter("copa")!=null) {
%>
<div class="alert alert-success alert-dismissible" role="alert">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <strong><span class="glyphicon glyphicon-ok" aria-hidden="true"></span><%=request.getParameter("copa")%></strong>
</div>
<% 
                  }
          
            %>
            <div class="row">
                
            </div>
        </div><%--fin col de copa--%>
        <div class="col-lg-4 col-md-4 col-sm-10 col-xs-12">
           <div class="row">
                <header>
                    <img src="../../imagenes/balon.png" width="40" height="40" alt="copa">
                    <h3>Eliminatoria</h3>
                </header>
            </div>
            <hr/>
            <div class="row">
<form id="eliminatoria">
    <div class="form-group">
        <label for="nombreTorneo">Nombre del torneo</label>
        <input id="nombreTorneo" name="nombreTorneo" type="text" maxlength="25" required>
    </div>
    <div class="form-group">
        <label for="capacidad">Capacidad de equipos</label>
        <select id="capacidad" name="capacidad">
            <option></option>
            <option>16</option>
            <option>32</option>
        </select>
    </div>
    <div class="form-group">
        <label for="finalIdaVuelta">¿El partido de la final se juega de ida y vuelta?</label>
        <select id="finalIdaVuelta" required>
            <option></option>
            <option id="finaIidaVueltaSi">Si</option>
            <option id="finalIdaVueltaNo">No, partido único</option>
        </select>
    </div>
    <div class="form-group">    
        <label for="tipo">Torneo:</label>
        <select name="tipo">
            <option></option>
            <option>Masculino</option>
            <option>Femenino</option>
        </select>
    </div>
    <div class="form-group">
        <label for="inicio">Fecha de inicio:</label>
        <input type="text" class="datepicker" name="inicio" required>
    </div>
    <div class="form-group">
        <label for="fin">Fecha de finalizacion:</label>
        <input type="text" class="datepicker" name="fin" required>
    </div>
        
    <button class="btn btn-primary" type="submit" name="eliminato^ria">CREAR</button>
    <input type="hidden" name="enviareli" />
</form>
        </div>
            <%
            if (request.getParameter("eliminatoria")!=null) {
                    %>
<div class="row">
<div class="alert alert-success alert-dismissible" role="alert">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <strong><span class="glyphicon glyphicon-ok" aria-hidden="true"></span><%=request.getParameter("eliminatoria")%></strong>
</div>
</div>                    
                    <%
                }
  
            %>
        </div><%--col de eli--%>
        <div class="col-lg-4 col-md-4 col-sm-10 col-xs-12">
            <div class="row">
                <header>
                    <img src="../../imagenes/balon.png" width="40" height="40" alt="copa">
                    <h3>Liga</h3>
                </header>
            </div>
            <hr/>
            <div class="row">
<form id="liga" method="get" action="GestionLiga">
    <div class="form-group">
        <label for="nombretorneo">Nombre del torneo</label>
        <input id="nombreTorneo" name="nombreTorneo" type="text" maxlength="25">
    </div>
    <div class="form-group">
        <label for="capacidad">Capacidad de equipos</label>
            <select id="capacidad" name="capacidad">
                <option></option>
                <option>12</option>
                <option>20</option>
            </select>
    </div>
    <div class="form-group">
        <label for="inicio">Fecha de inicio:</label>
        <input type="text" id="inicio" name="inicio" class="datepicker" required>
    </div>
    <div class="form-group">
        <label for="fin">Fecha de finalizacion:</label>
        <input type="text" id="fin" name="fin" class="datepicker" required>
    </div>
    <div class="form-group">
        <label for="tipo">Torneo:</label>
        <select id="tipo" name="tipo" required>
                <option></option>
                <option>Masculino</option>
                <option>Femenino</option>
        </select>
    </div>
                <button class="btn btn-primary" type="submit" name="liga">CREAR</button>
                <input type="hidden" name="enviarliga"/>
</form>
            </div>
            <%
            if (request.getParameter("liga")!=null) {
            %>
<div class="row">
<div class="alert alert-success alert-dismissible" role="alert">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <strong><span class="glyphicon glyphicon-ok" aria-hidden="true"></span><%=request.getParameter("liga")%></strong>
</div>
</div>
            <%
                }
  
            %>
        </div><%--col de liga--%>
        
    </div><%--row de formularios--%>
</main>    
<footer>
<p class="pie">2014 PRO-LEVEL - Todos los derechos reservados | Cambiar idioma 
    <a href="#"><img src="../../imagenes/español.png" width="40" height="30" /></a></p>  
</footer>
    </div><%--fin container--%>
 <script>
$(document).ready(function() {
    $( ".datepicker" ).datepicker({
	inline: true
});

// Hover states on the static widgets
$( "#dialog-link, #icons li" ).hover(
	function() {
		$( this ).addClass( "ui-state-hover" );
	},
	function() {
		$( this ).removeClass( "ui-state-hover" );
	}
);
});
</script>
</body>
</html>

<%
    }//si el rol fue uno se muestra la anterior pagina
    else if (rol == 2) {
                            
    }//si el rol fue 2
    }//si no hay sesion
            else{
                response.sendRedirect("index.jsp");
            }
%>
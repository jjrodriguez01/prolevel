<%-- 
    Document   : inscribirEquipos
    Created on : 4/04/2015, 12:17:36 AM
    Author     : jeisson
--%>
<%@page import="utilidades.MiExcepcion"%>
<%@page import="modelo.PartidoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.UsuariosDTO"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
            if (request.getSession()!=null) {
                    UsuariosDTO udto = new UsuariosDTO();
                    HttpSession miSession=request.getSession(false);
                    udto = (UsuariosDTO)miSession.getAttribute("usr");
                    int rol = (Integer)miSession.getAttribute("rol");
                    if(rol == 1){
%>
<%--  Query con la info del torneo --%>
<sql:query var="torneo" dataSource="jdbc/pro-level">
    SELECT idTorneo, nombre FROM torneo
</sql:query>
<%--  Query para que el contexto sea el torneo --%>
<sql:query var="infotorneo" dataSource="jdbc/pro-level">
    SELECT *  FROM torneo
    WHERE torneo.idTorneo = ? <sql:param value="${param.idTorneo}"/>
</sql:query>
    <%--  pasamos los resultados a una variable --%>
<c:set var="detallestorneo" value="${infotorneo.rows[0]}" scope="page" />
<%--  Query para saber cuantos equipos hay inscritos --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fechas de ${detallestorneo.nombre}</title>
        <link rel="shortcut icon" href="../../imagenes/favicon.ico">
        <link href="../../css/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="../../css/estiloslayout.css" rel="stylesheet" type="text/css">
        <link href="../../js/datepicker/jquery-ui.css" rel="stylesheet" type="text/css">
        <link href="../../js/clock/jquery.timepicker.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="../../js/jquery-2.1.1.js"></script>
        <script type="text/javascript" src="../../js/jquery.validate.js"></script>
        <script type="text/javascript" src="../../css/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../../js/datepicker/jquery-ui.min.js"></script>
        <script type="text/javascript" src="../../js/clock/jquery.timepicker.js"></script>
        <style>
            .menu-opciones{
                 clear: both;
                padding-top: 10px;
            }
        </style>
<script>
$(document).ready(function() {
    $(function() {
   $( ".datepicker" ).datepicker( "option", "minDate", 0 );
   $( ".datepicker" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
   $(".clockpick").timepicker({ 'timeFormat': 'H:i' });
  });
});
</script>
    </head>
    <body>
        <header>
            <nav>
                <ul id="nav" class="nav">
                    <li><a href="../inicio.jsp"><img src="../../imagenes/inicio.png" width="24" height="24" alt="inicio" /> INICIO</a></li>
                    <li><a href="#"><span><img src="../../imagenes/copa.png" width="24" height="24" alt="copa" /> TORNEOS</span></a><
                        <div class="subs">
                            <div class="col">
                                <ul>
                                    <li><a><img src="../../imagenes/micopa.png" width="24" height="24" alt="micopa"/>MIS TORNEOS</a>
                                        <div class="subs">
                                            <div class="col">
                                                <ul>
                                                    <c:forEach var="row" items="${torneo.rows}">
                                                        <li><a href="misTorneos?${row.idTorneo}">${row.nombre}</a></li>
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
<main class="container">
    <div class="row">
    <div class="col-lg-12 menu-opciones">
        <ul class="nav nav-tabs nav-justified">
            <li role="presentation"><a href="#"><span class="glyphicon glyphicon-home" aria-hidden="true"></span></a></li>
            <li role="presentation"><a href="calendario.jsp?idTorneo=${param.idTorneo}"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>Calendario</a></li>
            <li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-tasks" aria-hidden="true"></span>Resultados</a></li>
            <li role="presentation"><a href="misTorneos.jsp?idTorneo=${param.idTorneo}"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>Tablas</a></li>
            <li role="presentation"><a href="inscribirEquipos.jsp?idTorneo=${param.idTorneo}"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>Inscribir equipos</a></li>
        </ul>
    </div>
    </div>
    <div class="row">
        <div class="col-md-4 col-sm-2 col-xs-12">
            <ol class="breadcrumb">
                <li><a href="../inicio.jsp">Inicio</a></li>
                <li><a href="misTorneos.jsp?idTorneo=${param.idTorneo}">Torneos</a></li>
                <li class="active">Resultados</li>
            </ol>
        </div>
    </div>
            <%--si el torneo es tipo 3 es eliminatoria--%>
<c:if test="${detallestorneo.tipo==3}">
<%--si la capacidad de este torneo es de 16--%>
<c:if test="${detallestorneo.capacidadEquipos==16}">
    <section>
        <div class="row">
            <div class="col-lg-12">
                <div class="page-header">
                    <h1 id="hmarcadoresoctavos" data-toggle="popover" 
title="Hecho" data-content="Se han establecido las fechas"
 data-placement="top">Modifica Fechas Y Horas</h1>
<%--confirmacion de fechas octavos--%>
<%
if (request.getParameter("octavos")!=null) {
%>
<script>
    $(document).ready(function(){
        $("#hmarcadoresoctavos").trigger("click");
    });
</script>
<script>
$('[data-toggle="popover"]').popover(
                {
                    trigger: 'click',
                    html: true,
                    delay: 500,
                }
            );
</script>
<%
    }
%>
                    <%--query de la primera ronda octavos en eli de 16 equipos--%> 
                    <sql:query var="calendario" dataSource="jdbc/pro-level">
                        SELECT DISTINCT 
                        (select equipo.nombre from equipo where codigo=partidos.equipo1)as eq1, 
                        (select equipo.nombre from equipo where codigo=partidos.equipo2)as eq2, 
                        torneo.nombre as Torneo, 
                        partidos.marcador1,
                        partidos.marcador2,
                        partidos.ronda,
                        partidos.numero,
                        partidos.equipo1 as ceq1, 
                        partidos.equipo2 as ceq2
                        FROM 
                        partidos 
                        INNER JOIN equiposdeltorneo 
                        ON partidos.equipo1 = equiposdeltorneo.equipoCodigo 
                        INNER JOIN equipo
                        ON equiposdeltorneo.equipoCodigo = equipo.codigo 
                        INNER JOIN torneo 
                        ON partidos.idTorneo = torneo.idTorneo 
                        INNER JOIN cancha 
                        ON partidos.cancha = cancha.numeroCancha 
                        WHERE torneo.idtorneo = ? <sql:param value="${param.idTorneo}"/>  AND partidos.ronda = 1
                         
                    </sql:query>
                    <div class="panel panel-primary">
                    <div class="panel-heading">Octavos De Final</div>
                    <form name="marcadores" action="../../GestionEliminatoria" autocomplete="off">
                        <table class="table table-hover table-responsive">
                        <tbody>
<%-- varstatus me da el estado de la variable el metodo index me da la posicion parece q no toma los alias de el equipo 1--%>
                            <c:forEach var="row" items="${calendario.rows}" varStatus="vs">
                            <tr>
                                <td>${row.eq1}</td>
                                <td><input type="number" id="${vs.index}muno" name="${vs.index}muno" <c:if test="${row.marcador1 !=null}"> value="${row.marcador1}"</c:if> onchange="validarEmpate${vs.index}()"/></td>
                                <td><span>vs</span></td>
                                <td><input type="number" id="${vs.index}mdos" name="${vs.index}mdos" <c:if test="${row.marcador2 !=null}"> value="${row.marcador2}"</c:if> onchange="validarEmpate${vs.index}()" /></td>
                                <td>${row.eq2}</td>                     
                                <input type="hidden" value="${row.equipo1}" name="${vs.index}equipo1" />
                                <input type="hidden" value="${row.equipo2}" name="${vs.index}equipo2" />
                                <input type="hidden" value="${row.numero}" name="numero" />
                                <input type="hidden" value="${row.eq1}" name="${vs.index}nequipo1" />
                                <input type="hidden" value="${row.eq2}" name="${vs.index}nequipo2" />
                            </tr>
<script>
    function validarEmpate${vs.index}(){
var marcador1 = $("#${vs.index}muno").val();
var marcador2 = $("#${vs.index}mdos").val();
    if (marcador1 == marcador2) {
        alert("Este torneo es tipo knock-out no pueden haber empates");
        document.getElementById("asignarMarcador").setAttribute("disabled","true");
}else{
    document.getElementById("asignarMarcador").removeAttribute("disabled");
}
}
</script>
                        </c:forEach>
                        </tbody>
                    </table>
<input type="hidden" value="${param.idTorneo}" name="idTorneo" />
<button class="btn btn-primary" id="asignarMarcador" name="asignarMarcador">Añadir Marcador</button>
                    <input type="hidden" name="foctavos" value="octavos" />
                    </form>
                </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">Cuartos De Final</div>
                    <%--query para los cuartos--%>
                    <sql:query var="cuartos" dataSource="jdbc/pro-level">
                        SELECT DISTINCT 
                        (select equipo.nombre from equipo where codigo=partidos.equipo1)as eq1, 
                        (select equipo.nombre from equipo where codigo=partidos.equipo2)as eq2, 
                        torneo.nombre as Torneo, 
                        partidos.marcador1,
                        partidos.marcador2,
                        partidos.ronda,
                        partidos.numero,
                        partidos.equipo1 as ceq1, 
                        partidos.equipo2 as ceq2
                        FROM 
                        partidos 
                        INNER JOIN equiposdeltorneo 
                        ON partidos.equipo1 = equiposdeltorneo.equipoCodigo 
                        INNER JOIN equipo
                        ON equiposdeltorneo.equipoCodigo = equipo.codigo 
                        INNER JOIN torneo 
                        ON partidos.idTorneo = torneo.idTorneo 
                        INNER JOIN cancha 
                        ON partidos.cancha = cancha.numeroCancha 
                        WHERE torneo.idtorneo = ? <sql:param value="${param.idTorneo}"/>  AND partidos.ronda = 2
                    </sql:query>
    
                        <form>
                        <table class="table table-hover table-responsive">
                        <tbody>
<%-- varstatus me da el estado de la variable el metodo index me da la posicion parece q no toma los alias de el equipo 1--%>
                            <c:forEach var="row" items="${cuartos.rows}" varStatus="vs">
                            <tr>
                                <td>${row.eq1}</td>
                                <td><input type="number" id="${vs.index}munoc" name="${vs.index}munoc" <c:if test="${row.marcador1 !=null}"> value="${row.marcador1}"</c:if> onchange="noEmpateCuartos${vs.index}()"/></td>
                                <td><span>vs</span></td>
                                <td><input type="number" id="${vs.index}mdosc" name="${vs.index}mdosc" <c:if test="${row.marcador2 !=null}"> value="${row.marcador2}"</c:if> onchange="noEmpateCuartos${vs.index}()" /></td>
                                <td>${row.eq2}</td>                     
                                <input type="hidden" value="${row.equipo1}" name="${vs.index}equipo1" />
                                <input type="hidden" value="${row.equipo2}" name="${vs.index}equipo2" />
                                <input type="hidden" value="${row.numero}" name="numero" />
                                <input type="hidden" value="${row.eq1}" name="${vs.index}nequipo1" />
                                <input type="hidden" value="${row.eq2}" name="${vs.index}nequipo2" />
                            </tr>
<script>
    function noEmpateCuartos${vs.index}(){
var marcador1 = $("#${vs.index}munoc").val();
var marcador2 = $("#${vs.index}mdosc").val();
    if (marcador1 == marcador2) {
        alert("Este torneo es tipo knock-out no pueden haber empates");
        document.getElementById("asignarMarcadorCuartos").setAttribute("disabled","true");
}else{
    document.getElementById("asignarMarcadorCuartos").removeAttribute("disabled");
}
}
</script>
                        </c:forEach>
                        </tbody>
                    </table>
<input type="hidden" value="${param.idTorneo}" name="idTorneo" />
<button class="btn btn-primary" id="asignarMarcadorCuartos" name="asignarMarcadorCuartos">Añadir Marcador</button>
                    <input type="hidden" name="fcuartos" value="cuartos" />
                    </form>
                </div>
                </div>
        </div>
    <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">Semi final</div>
                    <%--query para la semi--%>
                    <sql:query var="semi" dataSource="jdbc/pro-level">
                        SELECT DISTINCT 
                        (select equipo.nombre from equipo where codigo=partidos.equipo1)as eq1, 
                        (select equipo.nombre from equipo where codigo=partidos.equipo2)as eq2, 
                        torneo.nombre as Torneo, 
                        partidos.marcador1,
                        partidos.marcador2,
                        partidos.ronda,
                        partidos.numero,
                        partidos.equipo1 as ceq1, 
                        partidos.equipo2 as ceq2
                        FROM 
                        partidos 
                        INNER JOIN equiposdeltorneo 
                        ON partidos.equipo1 = equiposdeltorneo.equipoCodigo 
                        INNER JOIN equipo
                        ON equiposdeltorneo.equipoCodigo = equipo.codigo 
                        INNER JOIN torneo 
                        ON partidos.idTorneo = torneo.idTorneo 
                        INNER JOIN cancha 
                        ON partidos.cancha = cancha.numeroCancha 
                        WHERE torneo.idtorneo = ? <sql:param value="${param.idTorneo}"/>  AND partidos.ronda = 3
                    </sql:query>
    
                    <form>
                        <table class="table table-hover table-responsive">
                        <tbody>
<%-- varstatus me da el estado de la variable el metodo index me da la posicion parece q no toma los alias de el equipo 1--%>
                            <c:forEach var="row" items="${semi.rows}" varStatus="vs">
                            <tr>
                                <td>${row.eq1}</td>
                                <td><input type="number" name="${vs.index}muno" <c:if test="${row.marcador1 !=null}"> value="${row.marcador1}"</c:if>/></td>
                                <td><span>vs</span></td>
                                <td><input type="number" name="${vs.index}mdos" <c:if test="${row.marcador2 !=null}"> value="${row.marcador2}"</c:if> /></td>
                                <td>${row.eq2}</td>                     
                                <input type="hidden" value="${row.equipo1}" name="${vs.index}equipo1" />
                                <input type="hidden" value="${row.equipo2}" name="${vs.index}equipo2" />
                                <input type="hidden" value="${row.numero}" name="numero" />
                                <input type="hidden" value="${row.eq1}" name="${vs.index}nequipo1" />
                                <input type="hidden" value="${row.eq2}" name="${vs.index}nequipo2" />
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
<input type="hidden" value="${param.idTorneo}" name="idTorneo" />
                    <button class="btn btn-primary" name="asignarfechas">Añadir Marcador</button>
                    <input type="hidden" name="fsemi" value="semi" />
                    </form>
                </div>
                </div>
        </div>
                    
    <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">Final</div>
                    <%--query para los cuartos--%>
                    <sql:query var="final" dataSource="jdbc/pro-level">
                        SELECT DISTINCT 
                        (select equipo.nombre from equipo where codigo=partidos.equipo1)as eq1, 
                        (select equipo.nombre from equipo where codigo=partidos.equipo2)as eq2, 
                        torneo.nombre as Torneo, 
                        partidos.marcador1,
                        partidos.marcador2,
                        partidos.ronda,
                        partidos.numero,
                        partidos.equipo1 as ceq1, 
                        partidos.equipo2 as ceq2
                        FROM 
                        partidos 
                        INNER JOIN equiposdeltorneo 
                        ON partidos.equipo1 = equiposdeltorneo.equipoCodigo 
                        INNER JOIN equipo
                        ON equiposdeltorneo.equipoCodigo = equipo.codigo 
                        INNER JOIN torneo 
                        ON partidos.idTorneo = torneo.idTorneo 
                        INNER JOIN cancha 
                        ON partidos.cancha = cancha.numeroCancha 
                        WHERE torneo.idtorneo = ? <sql:param value="${param.idTorneo}"/>  AND partidos.ronda = 4
                    </sql:query>
    
                    <form>
                        <table class="table table-hover table-responsive">
                        <tbody>
<%-- varstatus me da el estado de la variable el metodo index me da la posicion parece q no toma los alias de el equipo 1--%>
                            <c:forEach var="row" items="${final.rows}" varStatus="vs">
                            <tr>
                                <td>${row.eq1}</td>
                                <td><input type="number" name="${vs.index}muno" <c:if test="${row.marcador1 !=null}"> value="${row.marcador1}"</c:if>/></td>
                                <td><span>vs</span></td>
                                <td><input type="number" name="${vs.index}mdos" <c:if test="${row.marcador2 !=null}"> value="${row.marcador2}"</c:if> /></td>
                                <td>${row.eq2}</td>                     
                                <input type="hidden" value="${row.equipo1}" name="${vs.index}equipo1" />
                                <input type="hidden" value="${row.equipo2}" name="${vs.index}equipo2" />
                                <input type="hidden" value="${row.numero}" name="numero" />
                                <input type="hidden" value="${row.eq1}" name="${vs.index}nequipo1" />
                                <input type="hidden" value="${row.eq2}" name="${vs.index}nequipo2" />
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
<input type="hidden" value="${param.idTorneo}" name="idTorneo" />
                    <button class="btn btn-primary" name="asignarfechas">Añadir Marcador</button>
                    <input type="hidden" name="ffinal" value="final" />
                    </form>
                </div>
                </div>
        </div>
    </section>
</c:if><%--si es de 16 equipos--%>
</c:if><%--si es eliminatoria--%>
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
</main>
    </body>
</html>
<% 
    }//si el rol fue uno
                    else if(rol==2){
                        
                    }//si el rol fue 2
            }//si hay sesion
                    else{
                        response.sendRedirect("../../index.jsp?auth=prohibido");
                    }
%>
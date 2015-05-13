<%-- 
    Document   : inscribirEquipos
    Created on : 4/04/2015, 12:17:36 AM
    Author     : jeisson
--%>
<%@page import="utilidades.MiExcepcion"%>
<%@page import="modelo.PartidoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="persistencia.PartidoDAO"%>
<%@page import="persistencia.UsuariosDAO"%>
<%@page import="modelo.UsuariosDTO"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
            if (request.getSession()!=null) {
                    UsuariosDTO udto = new UsuariosDTO();
                    UsuariosDAO udao = new UsuariosDAO();
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultados ${detallestorneo.nombre}</title>
        <link rel="shortcut icon" href="../../imagenes/favicon.ico">
        <link href="../../css/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="../../css/estiloslayout.css" rel="stylesheet" type="text/css">
        <link href="../../css/estilos_resultadoseli.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="../../js/jquery-2.1.1.js"></script>
        <script type="text/javascript" src="../../css/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../../js/jugadoresEquipos.js"></script>
        <style>
            .menu-opciones{
                 clear: both;
                padding-top: 10px;
            }
        </style>
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
    <section class="container">
    <div class="row">
    <div class="col-lg-12 menu-opciones">
        <ul class="nav nav-tabs nav-justified">
            <li role="presentation"><a href="#"><span class="glyphicon glyphicon-home" aria-hidden="true"></span></a></li>
            <li role="presentation"><a href="calendario.jsp?idTorneo=${param.idTorneo}"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>Calendario</a></li>
            <li role="presentation" class="active"><a <c:if test="${detallestorneo.tipo==3}"> href="resultadoseli.jsp?idTorneo=${param.idTorneo}"</c:if> href="#"><span class="glyphicon glyphicon-tasks" aria-hidden="true"></span>Resultados</a></li>
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
    </section>
            <%--si el torneo es tipo 3 es eliminatoria--%>
<c:if test="${detallestorneo.tipo==3}">
<%--si la capacidad de este torneo es de 16--%>
<c:if test="${detallestorneo.capacidadEquipos==16}">
    <section>
        <div class="page-header">
            <h1>Resultados</h1>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-3 col-md-3 col-sm-3">
                    <h1>Octavos</h1><a href="marcadores.jsp?idTorneo=${param.idTorneo}">Asigna marcadores</a>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-3">
                    <h1>Cuartos</h1>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-3">
                    <h1>Semi final</h1>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-3">
                    <h1>Final</h1>
                </div>
            </div>
            <div class="arbol_eli16">
                <div id="p1">
                    <%--query del 1 partdido--%>
<sql:query var="p1" dataSource="jdbc/pro-level">
select 
   (select equipo.nombre from equipo where codigo=partidos.equipo1)as eq1, 
	(select equipo.nombre from equipo where codigo=partidos.equipo2)as eq2,
marcador1, marcador2
from
    partidos
 INNER JOIN equiposdeltorneo 
ON partidos.equipo1 = equiposdeltorneo.equipoCodigo 
INNER JOIN equipo
ON equiposdeltorneo.equipoCodigo = equipo.codigo
where
ronda = 1
and idTorneo = ? <sql:param value="${param.idTorneo}"/> 
and numero = 1;
</sql:query>
                    <table id="eq1" class="table table-bordered">
                        <tbody>
                            <c:forEach var="row" items="${p1.rows}" >
                            <tr>
                                <td class="equipo">${row.eq1}</td>
                                <td class="marcador">${row.marcador1}</td>
                            </tr>
                            <tr>
                                <td class="equipo">${row.eq2}</td>
                                <td class="marcador">${row.marcador2}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

<%--query del 2 partdido--%>
<sql:query var="p2" dataSource="jdbc/pro-level">
select 
   (select equipo.nombre from equipo where codigo=partidos.equipo1)as eq1, 
	(select equipo.nombre from equipo where codigo=partidos.equipo2)as eq2,
marcador1, marcador2
from
    partidos
 INNER JOIN equiposdeltorneo 
ON partidos.equipo1 = equiposdeltorneo.equipoCodigo 
INNER JOIN equipo
ON equiposdeltorneo.equipoCodigo = equipo.codigo
where
ronda = 1
and idTorneo = ? <sql:param value="${param.idTorneo}"/> 
and numero = 2;
</sql:query>
<div id="p2">
                    <table id="eq2" class="table table-bordered">
                        <tbody>
                            <c:forEach var="row" items="${p2.rows}" >
                            <tr>
                                <td class="equipo">${row.eq1}</td>
                                <td class="marcador">${row.marcador1}</td>
                            </tr>
                            <tr>
                                <td class="equipo">${row.eq2}</td>
                                <td class="marcador">${row.marcador2}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
</div>
<div id="uniong1uno"></div>
<div id="uniong1dos"></div>
<%--query del 3 partdido--%>
<sql:query var="p3" dataSource="jdbc/pro-level">
select 
   (select equipo.nombre from equipo where codigo=partidos.equipo1)as eq1, 
	(select equipo.nombre from equipo where codigo=partidos.equipo2)as eq2,
marcador1, marcador2
from
    partidos
 INNER JOIN equiposdeltorneo 
ON partidos.equipo1 = equiposdeltorneo.equipoCodigo 
INNER JOIN equipo
ON equiposdeltorneo.equipoCodigo = equipo.codigo
where
ronda = 1
and idTorneo = ? <sql:param value="${param.idTorneo}"/> 
and numero = 3;
</sql:query>
<div id="p3">
                    <table id="eq3" class="table table-bordered">
                        <tbody>
                            <c:forEach var="row" items="${p3.rows}" >
                            <tr>
                                <td class="equipo">${row.eq1}</td>
                                <td class="marcador">${row.marcador1}</td>
                            </tr>
                            <tr>
                                <td class="equipo">${row.eq2}</td>
                                <td class="marcador">${row.marcador2}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
</div>


<%--query del 4 partdido--%>
<sql:query var="p4" dataSource="jdbc/pro-level">
select 
   (select equipo.nombre from equipo where codigo=partidos.equipo1)as eq1, 
	(select equipo.nombre from equipo where codigo=partidos.equipo2)as eq2,
marcador1, marcador2
from
    partidos
 INNER JOIN equiposdeltorneo 
ON partidos.equipo1 = equiposdeltorneo.equipoCodigo 
INNER JOIN equipo
ON equiposdeltorneo.equipoCodigo = equipo.codigo
where
ronda = 1
and idTorneo = ? <sql:param value="${param.idTorneo}"/> 
and numero = 4;
</sql:query>
<div id="p4">
                    <table id="eq3" class="table table-bordered">
                        <tbody>
                            <c:forEach var="row" items="${p4.rows}" >
                            <tr>
                                <td class="equipo">${row.eq1}</td>
                                <td class="marcador">${row.marcador1}</td>
                            </tr>
                            <tr>
                                <td class="equipo">${row.eq2}</td>
                                <td class="marcador">${row.marcador2}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
</div>
<div id="uniong2uno"></div>
<div id="uniong2dos"></div>
<%--query del 5 partdido--%>
<sql:query var="p5" dataSource="jdbc/pro-level">
select 
   (select equipo.nombre from equipo where codigo=partidos.equipo1)as eq1, 
	(select equipo.nombre from equipo where codigo=partidos.equipo2)as eq2,
marcador1, marcador2
from
    partidos
 INNER JOIN equiposdeltorneo 
ON partidos.equipo1 = equiposdeltorneo.equipoCodigo 
INNER JOIN equipo
ON equiposdeltorneo.equipoCodigo = equipo.codigo
where
ronda = 1
and idTorneo = ? <sql:param value="${param.idTorneo}"/> 
and numero = 5;
</sql:query>
<div id="p5">
                    <table id="eq3" class="table table-bordered">
                        <tbody>
                            <c:forEach var="row" items="${p5.rows}" >
                            <tr>
                                <td class="equipo">${row.eq1}</td>
                                <td class="marcador">${row.marcador1}</td>
                            </tr>
                            <tr>
                                <td class="equipo">${row.eq2}</td>
                                <td class="marcador">${row.marcador2}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
</div>
<div id="uniong3uno"></div>
<div id="uniong3dos"></div>
<%--query del 6 partdido--%>
<sql:query var="p6" dataSource="jdbc/pro-level">
select 
   (select equipo.nombre from equipo where codigo=partidos.equipo1)as eq1, 
	(select equipo.nombre from equipo where codigo=partidos.equipo2)as eq2,
marcador1, marcador2
from
    partidos
 INNER JOIN equiposdeltorneo 
ON partidos.equipo1 = equiposdeltorneo.equipoCodigo 
INNER JOIN equipo
ON equiposdeltorneo.equipoCodigo = equipo.codigo
where
ronda = 1
and idTorneo = ? <sql:param value="${param.idTorneo}"/> 
and numero = 6;
</sql:query>
<div id="p6">
                    <table id="eq3" class="table table-bordered">
                        <tbody>
                            <c:forEach var="row" items="${p6.rows}" >
                            <tr>
                                <td class="equipo">${row.eq1}</td>
                                <td class="marcador">${row.marcador1}</td>
                            </tr>
                            <tr>
                                <td class="equipo">${row.eq2}</td>
                                <td class="marcador">${row.marcador2}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
</div>

<%--query del 7 partdido--%>
<sql:query var="p7" dataSource="jdbc/pro-level">
select 
   (select equipo.nombre from equipo where codigo=partidos.equipo1)as eq1, 
	(select equipo.nombre from equipo where codigo=partidos.equipo2)as eq2,
marcador1, marcador2
from
    partidos
 INNER JOIN equiposdeltorneo 
ON partidos.equipo1 = equiposdeltorneo.equipoCodigo 
INNER JOIN equipo
ON equiposdeltorneo.equipoCodigo = equipo.codigo
where
ronda = 1
and idTorneo = ? <sql:param value="${param.idTorneo}"/> 
and numero = 7;
</sql:query>
<div id="p7">
                    <table id="eq7"  class="table table-bordered">
                        <tbody>
                            <c:forEach var="row" items="${p7.rows}" >
                            <tr>
                                <td class="equipo">${row.eq1}</td>
                                <td class="marcador">${row.marcador1}</td>
                            </tr>
                            <tr>
                                <td class="equipo">${row.eq2}</td>
                                <td class="marcador">${row.marcador2}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
</div>


<%--query del 8 partdido--%>
<sql:query var="p8" dataSource="jdbc/pro-level">
select 
   (select equipo.nombre from equipo where codigo=partidos.equipo1)as eq1, 
	(select equipo.nombre from equipo where codigo=partidos.equipo2)as eq2,
marcador1, marcador2
from
    partidos
 INNER JOIN equiposdeltorneo 
ON partidos.equipo1 = equiposdeltorneo.equipoCodigo 
INNER JOIN equipo
ON equiposdeltorneo.equipoCodigo = equipo.codigo
where
ronda = 1
and idTorneo = ? <sql:param value="${param.idTorneo}"/> 
and numero = 8;
</sql:query>
<div id="p8">
                    <table id="eq8" class="table table-bordered">
                        <tbody>
                            <c:forEach var="row" items="${p8.rows}" >
                            <tr>
                                <td class="equipo">${row.eq1}</td>
                                <td class="marcador">${row.marcador1}</td>
                            </tr>
                            <tr>
                                <td class="equipo">${row.eq2}</td>
                                <td class="marcador">${row.marcador2}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
</div>
<div id="uniong4uno"></div>
<div id="uniong4dos"></div>
<%--query del 1 partdido de cuartos--%>
<sql:query var="p1cuartos" dataSource="jdbc/pro-level">
select 
   (select equipo.nombre from equipo where codigo=partidos.equipo1)as eq1, 
	(select equipo.nombre from equipo where codigo=partidos.equipo2)as eq2,
marcador1, marcador2
from
    partidos
 INNER JOIN equiposdeltorneo 
ON partidos.equipo1 = equiposdeltorneo.equipoCodigo 
INNER JOIN equipo
ON equiposdeltorneo.equipoCodigo = equipo.codigo
where
ronda = 2
and idTorneo = ? <sql:param value="${param.idTorneo}"/> 
and numero = 1;
</sql:query>
<div id="cuartos1">
                    <table id="eqc1" class="table table-bordered">
                        <tbody>
                            <c:forEach var="row" items="${p1cuartos.rows}" >
                            <tr>
                                <td class="equipo">${row.eq1}</td>
                                <td class="marcador">${row.marcador1}</td>
                            </tr>
                            <tr>
                                <td class="equipo">${row.eq2}</td>
                                <td class="marcador">${row.marcador2}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
</div>

<%--query del 2 partdido de cuartos--%>
<sql:query var="p2cuartos" dataSource="jdbc/pro-level">
select 
   (select equipo.nombre from equipo where codigo=partidos.equipo1)as eq1, 
	(select equipo.nombre from equipo where codigo=partidos.equipo2)as eq2,
marcador1, marcador2
from
    partidos
 INNER JOIN equiposdeltorneo 
ON partidos.equipo1 = equiposdeltorneo.equipoCodigo 
INNER JOIN equipo
ON equiposdeltorneo.equipoCodigo = equipo.codigo
where
ronda = 2
and idTorneo = ? <sql:param value="${param.idTorneo}"/> 
and numero = 2;
</sql:query>
<div id="cuartos2">
                    <table id="eqc2" class="table table-bordered">
                        <tbody>
                            <c:forEach var="row" items="${p2cuartos.rows}" >
                            <tr>
                                <td class="equipo">${row.eq1}</td>
                                <td class="marcador">${row.marcador1}</td>
                            </tr>
                            <tr>
                                <td class="equipo">${row.eq2}</td>
                                <td class="marcador">${row.marcador2}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
</div>


<%--query del 3 partdido de cuartos--%>
<sql:query var="p3cuartos" dataSource="jdbc/pro-level">
select 
   (select equipo.nombre from equipo where codigo=partidos.equipo1)as eq1, 
	(select equipo.nombre from equipo where codigo=partidos.equipo2)as eq2,
marcador1, marcador2
from
    partidos
 INNER JOIN equiposdeltorneo 
ON partidos.equipo1 = equiposdeltorneo.equipoCodigo 
INNER JOIN equipo
ON equiposdeltorneo.equipoCodigo = equipo.codigo
where
ronda = 2
and idTorneo = ? <sql:param value="${param.idTorneo}"/> 
and numero = 3;
</sql:query>
<div id="cuartos3">
                    <table id="eqc3" class="table table-bordered">
                        <tbody>
                            <c:forEach var="row" items="${p3cuartos.rows}" >
                            <tr>
                                <td class="equipo">${row.eq1}</td>
                                <td class="marcador">${row.marcador1}</td>
                            </tr>
                            <tr>
                                <td class="equipo">${row.eq2}</td>
                                <td class="marcador">${row.marcador2}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
</div>


<%--query del 4 partdido de cuartos--%>
<sql:query var="p4cuartos" dataSource="jdbc/pro-level">
select 
   (select equipo.nombre from equipo where codigo=partidos.equipo1)as eq1, 
	(select equipo.nombre from equipo where codigo=partidos.equipo2)as eq2,
marcador1, marcador2
from
    partidos
 INNER JOIN equiposdeltorneo 
ON partidos.equipo1 = equiposdeltorneo.equipoCodigo 
INNER JOIN equipo
ON equiposdeltorneo.equipoCodigo = equipo.codigo
where
ronda = 2
and idTorneo = ? <sql:param value="${param.idTorneo}"/> 
and numero = 4;
</sql:query>
<div id="cuartos4">
                    <table id="eqc3" class="table table-bordered">
                        <tbody>
                            <c:forEach var="row" items="${p4cuartos.rows}" >
                            <tr>
                                <td class="equipo">${row.eq1}</td>
                                <td class="marcador">${row.marcador1}</td>
                            </tr>
                            <tr>
                                <td class="equipo">${row.eq2}</td>
                                <td class="marcador">${row.marcador2}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
</div>



<%--query del 1 partdido de semi--%>
<sql:query var="p4cuartos" dataSource="jdbc/pro-level">
select 
   (select equipo.nombre from equipo where codigo=partidos.equipo1)as eq1, 
	(select equipo.nombre from equipo where codigo=partidos.equipo2)as eq2,
marcador1, marcador2
from
    partidos
 INNER JOIN equiposdeltorneo 
ON partidos.equipo1 = equiposdeltorneo.equipoCodigo 
INNER JOIN equipo
ON equiposdeltorneo.equipoCodigo = equipo.codigo
where
ronda = 3
and idTorneo = ? <sql:param value="${param.idTorneo}"/> 
and numero = 1;
</sql:query>
<div id="semi1">
                    <table id="eqs1" class="table table-bordered">
                        <tbody>
                            <c:forEach var="row" items="${p4cuartos.rows}" >
                            <tr>
                                <td class="equipo">${row.eq1}</td>
                                <td class="marcador">${row.marcador1}</td>
                            </tr>
                            <tr>
                                <td class="equipo">${row.eq2}</td>
                                <td class="marcador">${row.marcador2}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
</div>
<div id="uniong5uno"></div>
<div id="uniong5dos"></div>

<%--query del 2 partdido de semi--%>
<sql:query var="p4cuartos" dataSource="jdbc/pro-level">
select 
   (select equipo.nombre from equipo where codigo=partidos.equipo1)as eq1, 
	(select equipo.nombre from equipo where codigo=partidos.equipo2)as eq2,
marcador1, marcador2
from
    partidos
 INNER JOIN equiposdeltorneo 
ON partidos.equipo1 = equiposdeltorneo.equipoCodigo 
INNER JOIN equipo
ON equiposdeltorneo.equipoCodigo = equipo.codigo
where
ronda = 3
and idTorneo = ? <sql:param value="${param.idTorneo}"/> 
and numero = 2;
</sql:query>
<div id="semi2">
                    <table id="eqs2" class="table table-bordered">
                        <tbody>
                            <c:forEach var="row" items="${p4cuartos.rows}" >
                            <tr>
                                <td class="equipo">${row.eq1}</td>
                                <td class="marcador">${row.marcador1}</td>
                            </tr>
                            <tr>
                                <td class="equipo">${row.eq2}</td>
                                <td class="marcador">${row.marcador2}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
</div>
<div id="uniong6uno"></div>
<div id="uniong6dos"></div>


<%--query del partdido de la final--%>
<sql:query var="pfin" dataSource="jdbc/pro-level">
select 
   (select equipo.nombre from equipo where codigo=partidos.equipo1)as eq1, 
	(select equipo.nombre from equipo where codigo=partidos.equipo2)as eq2,
marcador1, marcador2
from
    partidos
 INNER JOIN equiposdeltorneo 
ON partidos.equipo1 = equiposdeltorneo.equipoCodigo 
INNER JOIN equipo
ON equiposdeltorneo.equipoCodigo = equipo.codigo
where
ronda = 4
and idTorneo = ? <sql:param value="${param.idTorneo}"/> 
and numero = 1;
</sql:query>
<div id="final">
    <div class="panel panel-danger">
        <div class="panel-heading">Final</div>
                    <table id="eqf" class="table table-bordered">
                        <tbody>
                            <c:forEach var="row" items="${pfin.rows}" >
                            <tr>
                                <td class="equipo">${row.eq1}</td>
                                <td class="marcador">${row.marcador1}</td>
                            </tr>
                            <tr>
                                <td class="equipo">${row.eq2}</td>
                                <td class="marcador">${row.marcador2}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
    </div>
</div>
<div id="uniong7uno"></div>
<div id="uniong7dos"></div>

<%--query del partdido de la final--%>
<sql:query var="ptercer" dataSource="jdbc/pro-level">
select 
   (select equipo.nombre from equipo where codigo=partidos.equipo1)as eq1, 
	(select equipo.nombre from equipo where codigo=partidos.equipo2)as eq2,
marcador1, marcador2
from
    partidos
 INNER JOIN equiposdeltorneo 
ON partidos.equipo1 = equiposdeltorneo.equipoCodigo 
INNER JOIN equipo
ON equiposdeltorneo.equipoCodigo = equipo.codigo
where
ronda = 5
and idTorneo = ? <sql:param value="${param.idTorneo}"/> 
and numero = 1;
</sql:query>
<div id="terceros">
    <div class="panel panel-default">
        <div class="panel-heading">Tercer Puesto</div>
                    <table id="eqf" class="table table-bordered">
                        <tbody>
                            <c:forEach var="row" items="${ptercer.rows}" >
                            <tr>
                                <td class="equipo">${row.eq1}</td>
                                <td class="marcador">${row.marcador1}</td>
                            </tr>
                            <tr>
                                <td class="equipo">${row.eq2}</td>
                                <td class="marcador">${row.marcador2}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
    </div>
</div>
                </div>
            </div>
    </section>
</c:if><%--si es de 16 equipos--%>
</c:if><%--si es eliminatoria--%>
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

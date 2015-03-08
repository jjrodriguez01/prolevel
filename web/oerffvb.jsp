<%-- 
    Document   : oerffvb
    Created on : 10/02/2015, 07:28:17 PM
    Author     : jeisson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedList"%>
<%@page import="modelo.EquipoDTO"%>
<%@page import="persistencia.EquipoDAO"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--  Query para el menu desplegable de torneos --%>
<sql:query var="torneo" dataSource="jdbc/pro-level">
    SELECT idTorneo, nombre FROM torneo
</sql:query>
<%--  Query para que el contexto sea el torneo --%>
<sql:query var="vareliminatoria" dataSource="jdbc/pro-level">
    SELECT *  FROM torneo
    WHERE torneo.idTorneo = ? <sql:param value="${param.idTorneo}"/>
</sql:query>
<c:set var="detallestorneo" value="${vareliminatoria.rows[0]}"/>
<%--  Query para la tabla de posiciones --%>
<sql:query var="tablaposiciones" dataSource="jdbc/pro-level">
    SELECT equipo.nombre, 
    tablaposiciones.partidosJugados as PJ,
    tablaposiciones.partidosGanados as PG, 
    tablaposiciones.partidosEmpatados as PE,
    tablaposiciones.partidosPerdidos as PP, 
    tablaposiciones.golesAnotados as Goles,
    tablaposiciones.golesRecibidos as GC,
    tablaposiciones.golesAnotados-tablaposiciones.golesRecibidos AS GD,
    tablaposiciones.puntos as pts
    FROM equipo
    inner join equiposdeltorneo
    on equipo.codigo = equiposdeltorneo.equipoCodigo
    inner join tablaPosiciones
    on equiposdeltorneo.equipoCodigo = tablaposiciones.idEquipo
    WHERE equiposdeltorneo.torneoidtorneo=? <sql:param value="${param.idTorneo}"/> 
    and
    tablaposiciones.idTorneo = ? <sql:param value="${param.idTorneo}"/>
    ORDER BY puntos DESC
</sql:query>
<%--  Query para la tabla de goleadores --%>            
<sql:query var="tablagoleadores" dataSource="jdbc/pro-level">
    SELECT DISTINCT usuarios.primerNombre, usuarios.primerApellido, tablagoleadores.numeroGoles, equipo.nombre
    FROM tablagoleadores
    INNER JOIN jugadoresporequipo
    ON tablagoleadores.idJugador = jugadoresporequipo.codigoJugador
    INNER JOIN usuarios
    ON jugadoresporequipo.codigoJugador = usuarios.idUsuario
    INNER JOIN equiposdeltorneo
    ON tablagoleadores.idEquipo = equiposdeltorneo.equipoCodigo
    INNER JOIN equipo
    ON equiposdeltorneo.equipoCodigo = equipo.codigo
    INNER JOIN torneo
    ON tablagoleadores.idTorneo = torneo.idTorneo
    WHERE tablagoleadores.idTorneo = ? <sql:param value="${param.idTorneo}"/>
    AND equiposdeltorneo.torneoIdTorneo=? <sql:param value="${param.idTorneo}"/>
</sql:query>
<%--  Query para la tabla de tarjetas --%>  
<sql:query var="tarjetas" dataSource="jdbc/pro-level">
    SELECT DISTINCT concat(usuarios.primerNombre,' ', usuarios.primerApellido), tarjetas.tarjetaAzul, tarjetas.tarjetaRoja
    FROM tarjetas
    INNER JOIN  jugadoresporequipo
    ON tarjetas.idJugador = jugadoresporequipo.codigoJugador
    INNER JOIN  usuarios
    ON jugadoresporequipo.codigoJugador = usuarios.idUsuario
    WHERE tarjetas.idTorneo =? <sql:param value="${param.idTorneo}"/>
</sql:query>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="css/estiloslayout.css" rel="stylesheet" type="text/css">
        <link href="css/estilosMisTorneos.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="js/jquery-2.1.1.js"></script>
        <script type="text/javascript" src="js/jquery.validate.js"></script>
        <script type="text/javascript" src="js/select/jquery-ui.js"></script>
        <link href="js/select/jquery-ui.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="js/jugadoresEquipos.js"></script>
        <title>${detallestorneo.nombre}</title>
        <script>
            $(document).ready(function () {
                $(".select")
                        .selectmenu()
                        .selectmenu("menuWidget")
                        .addClass("overflow");
            });
        </script>
    </head>
    <body>
        <header>
            <nav>
                <ul id="nav" class="nav">
                    <li><a href="inicio.html"><img src="imagenes/inicio.png" width="24" height="24" alt="inicio" /> INICIO</a></li>
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
                                    <li><a href="crear_torneo.html"><img src="imagenes/crear.png" width="24" height="24" alt="crear" />CREAR TORNEOS</a></li>
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
                                <li><a href="#"><img src="imagenes/ajustes.png" width="24" height="24" alt="ajustes" />ADMINISTRAR</a></li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </nav>
        </header>
        
        <%
            if (request.getParameter("torneos") != null) {
                TorneoDAO tdao = new TorneoDAO();
                TorneoDTO tdto = new TorneoDTO();
                tdto=tdao.listarUno(Integer.parseInt(request.getParameter("torneos")));
        %>
        <div class="torneosup"><%=request.getParameter("torneos")%>
            <form id="actualizart" action="CRUDTorneo" method="post">
                <div class="form-group">
                    <input id="nombreTorneo" name="nombreTorneo" type="text" maxlength="25" placeholder="<% tdto.getNombre(); %>" required>
                <div class="col-md-6">
                <label for="nombreTorneo">Nombre del torneo</label>
                </div>
                </div>
                <div class="form-group">
                    <input type="text" class="datepicker" name="inicio" placeholder="<% tdto.getFechaInicio(); %>" required>
                <div class="col-md-6">
                <label for="inicio"><% tdto.getFechaInicio(); %></label>
                </div>
                </div>
                <div class="form-group">
                    <input type="text" class="datepicker" name="fin" placeholder="<% tdto.getFechaFin(); %>" required>
                <div class="col-md-6">
                <label for="fin">Fecha de finalizacion:</label>
                </div>
                </div>
                <div class="form-group">
                    <input type="text" name="tipo" placeholder="<% tdto.getGenero(); %>" required>
                <div class="col-md-6">
                <label for="fin">Genero</label>
                </div>
                </div>
                <div class="form-group">
                    <c:forEach var="row" items="${capacidad.rows}">     
                        <input type="text" name="capacidad" value="${row.capacidadEquipos}" required readonly="readonly"/>
                        <input type="hidden" name="idTorneo" value="${row.idTorneo}">
                    </c:forEach> 
                <div class="col-md-6">
                <label for="fin">Capacidad de equipos</label>
                </div>
                </div>
                <div class="col-lg-10 center-block btn">
                    <button name="actutorneo">Guardar Cambios</button>
                    <input type="hidden" name="confirmactu"/>
                </div>
            </form>           
        </div>        
        <%
            }
            // si es null el objeto, es decir, no se ha creado aun, no mostramos nada
        %>

        
        
        <%
            if (request.getParameter("torneodel") != null) {

        %>        
        <sql:query var="teliminar" dataSource="jdbc/pro-level">
            SELECT  *  FROM torneo
            WHERE idTorneo = ? <sql:param value="${param.torneodel}"/>
        </sql:query>
    
                       <table border="1">
                <!-- column headers -->
                <tr>
                    <c:forEach var="columnName" items="${teliminar.columnNames}">
                        <th><c:out value="${columnName}"/></th>
                        </c:forEach>
                </tr>
                <!-- column data -->
                <c:forEach var="row" items="${teliminar.rowsByIndex}">
                    <tr>
                        <c:forEach var="column" items="${row}">
                            <td><c:out value="${column}"/></td>
                        </c:forEach>
                    </tr>
                  
                </c:forEach>
            </table>
            <form method="get" action="CRUDTorneo">
            <input type="text" value="<%request.getParameter("torneodel"); %>"/>
                            <button name="elit">Eliminar</button>
                            <input type="hidden" name="confelit"/>
                        </form>
        <%
            }
            // si es null el objeto, es decir, no se ha creado aun, no mostramos nada
        %>
        
       
        <footer>
            <p class="pie">2014 PRO-LEVEL - Todos los derechos reservados | Cambiar idioma <a href="index_english.html"><img src="imagenes/english.png" width="40px" height="30px" /></a></p> 
            <img src="imagenes/cesped.png" width="100%" height="100px"  alt=""/>
        </footer>
    </body>
</html>

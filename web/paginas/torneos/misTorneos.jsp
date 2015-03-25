<%-- 
    Document   : misTorneos
    Created on : 10/02/2015, 07:20:21 PM
    Author     : jeisson
--%>
<%@page import="persistencia.TablaPosicionesDAO"%>
<%@page import="persistencia.TorneoDAO"%>
<%@page import="persistencia.UsuariosDAO"%>
<%@page import="modelo.UsuariosDTO"%>
<%@page import="java.util.LinkedList"%>
<%@page import="modelo.EquipoDTO"%>
<%@page import="persistencia.EquipoDAO"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
            if (request.getSession()!=null) {
                    UsuariosDTO udto = new UsuariosDTO();
                    UsuariosDAO udao = new UsuariosDAO();
                    TorneoDAO tdao = new TorneoDAO();
                    TablaPosicionesDAO  tpdao = new TablaPosicionesDAO();
                    HttpSession miSession=request.getSession(false);
                    udto = (UsuariosDTO)miSession.getAttribute("usr");
                    int rol = (Integer)miSession.getAttribute("rol");
                    if(rol == 1){
        %>
<% 
    int regtpos = tpdao.contarRegistros(Integer.parseInt(request.getParameter("idTorneo")));
    int numpg = regtpos/10;
    int pg = 0;
    if (request.getParameter("pgtp")==null) {
        pg = 1;
        }else{
        pg = Integer.parseInt(request.getParameter("pgtp"));
    }
    
%> 
<%--  Query para el menu desplegable de torneos --%>
<sql:query var="torneo" dataSource="jdbc/pro-level">
    SELECT idTorneo, nombre FROM torneo
</sql:query>
<%--  Query para que el contexto sea el torneo --%>
<sql:query var="vareliminatoria" dataSource="jdbc/pro-level">
    SELECT *  FROM torneo
    WHERE torneo.idTorneo = ? <sql:param value="${param.idTorneo}"/>
</sql:query>
<sql:query var="tposregistros" dataSource="jdbc/pro-level">
    SELECT count(*) from tablaposiciones where idTorneo = ? <sql:param value="${param.idTorneo}"/>
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
    LIMIT <c:if test="${param.pgtp == null}"><%--pdtp=parametro paginacion de tablaposicionse--%>
            0
        </c:if>${param.pgtp},10
</sql:query>
<%--  Query para la tabla de goleadores --%>            
<sql:query var="tablagoleadores" dataSource="jdbc/pro-level">
    SELECT DISTINCT usuarios.primerNombre, 
    usuarios.primerApellido, tablagoleadores.numeroGoles, 
    equipo.nombre
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
    ORDER BY numeroGoles DESC
</sql:query>
<%--  Query para la tabla de tarjetas --%>  
<sql:query var="tarjetas" dataSource="jdbc/pro-level">
    SELECT DISTINCT concat(usuarios.primerNombre,' ', usuarios.primerApellido), 
    tarjetas.tarjetaAzul, tarjetas.tarjetaRoja, equipo.nombre
    FROM tarjetas
    INNER JOIN  jugadoresporequipo
    ON tarjetas.idJugador = jugadoresporequipo.codigoJugador
    INNER JOIN  usuarios
    ON jugadoresporequipo.codigoJugador = usuarios.idUsuario
    INNER JOIN equipo 
    ON jugadoresporequipo.codigoequipo = equipo.codigo
	INNER JOIN equiposdeltorneo
	On jugadoresporequipo.codigoEquipo = equiposdeltorneo.equipoCodigo
    WHERE tarjetas.idTorneo =? <sql:param value="${param.idTorneo}"/>
	and equiposdeltorneo.torneoIdTorneo =? <sql:param value="${param.idTorneo}"/>
</sql:query>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>${detallestorneo.nombre}</title>
        <link href="../../css/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="../../css/estiloslayout.css" rel="stylesheet" type="text/css">
        <link href="../../css/estilosMisTorneos.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="../../js/jquery-2.1.1.js"></script>
        <script type="text/javascript" src="../../js/jquery.validate.js"></script>
        <script type="text/javascript" src="../../js/select/jquery-ui.js"></script>
        <script type="text/javascript" src="../../css/bootstrap/js/bootstrap.min.js"></script>
        <link href="../../js/select/jquery-ui.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="../../js/jugadoresEquipos.js"></script>
        <script>
            $(document).ready(function () {
                $(".select")
                        .selectmenu()
                        .selectmenu("menuWidget")
                        .addClass("overflow");
            });
        </script>
        <script>//script del tooltip de los th
            $(document).ready(function(){
                $("th").mouseenter(function (e) {             
            var posMouse = e.pageX - this.offsetLeft; 
            var textoTooltip = $(this).attr("title"); 
			
            if (textoTooltip.length > 0) {
                $(this).append('<div class="tooltips">' + textoTooltip + '</div>');
                $("th > div.tooltips").css("left", "" + posMouse - 103 + "px");
                $("th > div.tooltips").fadeIn(300);
            }
        });

        $("th").mouseleave(function () {             
            $("th > div.tooltips").fadeOut(300).delay(300).queue(function () {
                $(this).remove();
                $(this).dequeue();
            });
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
                                                        <li><a href="?${row.idTorneo}">${row.nombre}</a></li>
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
            <hgroup>
                <h1 id="titulo">${detallestorneo.nombre}</h1>
                <h3 class="tablatit">Tabla De Posiciones</h3>
            </hgroup>
            <div class="row">
                <div class="col-md-9 col-sm-9">
                    <table class="table table-responsive">
                        <!-- column headers -->
                        <thead>
                            <tr>
                                <th class="tooltip_elemento" title="Posicion">POS</th><div class="tooltips"></div>
                                <th title="Equipo">EQUIPO</th>
                                <th title="Partidos Jugados">PJ</th>
                                <th title="Partidos Ganados">PG</th>
                                <th title="Parrtidos Empadados">PE</th>
                                <th title="Partidos Perdidos">PP</th>
                                <th title="Goles Marcados">GOLES</th>
                                <th title="Goles En Contra">GC</th>
                                <th title="Diferencia De Goles">GD</th>
                                <th title="Puntos">PTS</th>
                            </tr>
                        </thead>
                        <!-- column data -->
                        <tbody>
                            <%!  int pos = 0;  %>
                            <c:forEach var="row" items="${tablaposiciones.rowsByIndex}">
                                <tr>
                                    <% 
                                       pos += 1;
                                    %>
                                    <td><%=pos%></td>
                                    <c:forEach var="column" items="${row}">
                                        <td><c:out value="${column}"/></td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div>
                        <ul class="pager">
    <% int flechas; 
    if (request.getParameter("pgtp")==null) {
            flechas= 1;
        }else{
         flechas= Integer.parseInt(request.getParameter("pgtp"));
    }
    %>   
    <li><a class="previous" href="?idTorneo=<%=Integer.parseInt(request.getParameter("idTorneo"))%>&pgtp=<%=0%>">&laquo;</a></li>
    <li><a class="next" href="?idTorneo=<%=Integer.parseInt(request.getParameter("idTorneo"))%>&pgtp=<%=10%>">&raquo;</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 col-sm-4">
                    <h3 class="tablatit">Tabla Goleadores</h3>
                    <table class="table">
                        <!-- column headers -->
                        <thead>
                            <tr>
                                <th>Posicion</th>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>Goles</th>
                                <th>Equipo</th>
                            </tr>
                        </thead>
                        <!-- column data -->
                        <tbody>
                        <%!  int pgol = 0;  %>
                            <c:forEach var="row" items="${tablagoleadores.rowsByIndex}">
                                <% pgol += 1; %>
                                <tr>
                                    <td><%=pgol%></td>
                                    <c:forEach var="column" items="${row}">
                                        <td><c:out value="${column}"/></td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div id="tablatarjetas" class="col-md-6 col-sm-4">
                    <h3 class="tablatit">Tabla De Tarjetas</h3>
                    <table class="table">
                        <!-- column headers -->
                        <thead>
                            <tr>
                                <th>Jugador</th>
                                <th>AZULES</th>
                                <th>ROJAS</th>
                                <th>EQUIPO</th>
                            </tr>
                        </thead>
                        <!-- column data -->
                        <tbody>
                            <c:forEach var="row" items="${tarjetas.rowsByIndex}">
                                <tr>
                                    <c:forEach var="column" items="${row}">
                                        <td><c:out value="${column}"/></td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <button class="bottom" id="btntar" data-toggle="modal" data-target="#tarjetas">Asignar Tarjetas</button>
                                        <% 
                if (request.getParameter("tarjetas")!=null) {
                    %>
                    <span class='confirmt'>!Se insertaron las tarjetas¡</span>
                    <%
                    }
                    %> 
                </div>
            </div>
            <div id="tarjetas" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h3>Asignar tarjetas</h3>
                        </div>
                        <div class="modal-body">
                <form method="get" action="../../Tarjetas">
                    <label>Seleccione Un Equipo</label>
                    <select name="equipo" class="" onchange="getJugador(this.value);">
                        <option>Seleccione equipo</option>
                        <%
                            EquipoDAO edao = new EquipoDAO(); 
                            LinkedList<EquipoDTO> Equipos = new LinkedList <EquipoDTO>();
                            Equipos = edao.listarTodoTorneo(Integer.parseInt(request.getParameter("idTorneo")));
                            for (EquipoDTO edto : Equipos) {
                        %>
                        <option value="<%=edto.getCodigo()%>"> <%=edto.getNombre()%></option>
                        <%
                          }
                        %>
                    </select>            
                    <label>Seleccione el jugador a aplicar tarjetas</label>
                    <select name="jugadores" class="" id="jugadores">
                        <option>Seleccione un equipo</option>
                    </select>
                    <span id="rojas"><img src="../../imagenes/Tarjeta_roja.png" width="21" height="30" alt="Tarjeta_roja"/>
                    </span>
                    <input type="text" name="rojas"/>
                    <span id="amarillas"><img src="../../imagenes/tarjeta_amarilla.png" width="21" height="30" alt="tarjeta_amarilla"/>
                    </span>
                    <input type="text" name="azules"/>
                    <input type="hidden" name="idTorneo" value="${param.idTorneo}"/>
                    <input type="submit" name="asigtarjetas" value="Asignar" class="btn"/>
                    <input type="hidden" name="tarjetas" />
                </form>
                        </div>
                    </div>
                </div>     
            </div>
        </main>
        <footer>
            <p class="pie">2014 PRO-LEVEL - Todos los derechos reservados | Cambiar idioma 
            <a href="index_english.html"><img src="../../imagenes/english.png" width="40" height="30" alt="ingles" /></a>
            </p> 
        </footer>
    </body>
</html>
<% }//si el rol fue uno se mostrara la enterior pagina
            else if (rol == 2) {//si el rol es 2 se mostrara la siguiente
                            
                        }
            }
            else{
                response.sendRedirect("../../../index.jsp");
            }                 
%>
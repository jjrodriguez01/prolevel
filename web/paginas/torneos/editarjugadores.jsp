<%-- 
    Document   : inscribirEquipos
    Created on : 4/04/2015, 12:17:36 AM
    Author     : jeisson
--%>
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
<%--  Query para saber cuantos equipos hay inscritos --%>
<sql:query var="disponibilidad" dataSource="jdbc/pro-level">
select count(torneoidtorneo) as capacidad  from equiposdeltorneo where torneoidtorneo=? <sql:param value="${param.idTorneo}"/>
</sql:query>
<%--  pasamos el resultado del query disponibilidad a una variable --%>
<c:set var="inscritos" value="${disponibilidad.rows[0]}" scope="page" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Centro del torneo ${detallestorneo.nombre}</title>
        <link rel="shortcut icon" href="../../imagenes/favicon.ico">
        <link href="../../css/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="../../css/estiloslayout.css" rel="stylesheet" type="text/css">
        <link href="../../css/estilosMisTorneos.css" rel="stylesheet" type="text/css">
        <link href="../../js/dataTables/css/dataTablesBootstrap.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="../../js/jquery-2.1.1.js"></script>
        <script type="text/javascript" src="../../js/jquery.validate.js"></script>
        <script type="text/javascript" src="../../css/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../../js/jugadoresEquipos.js"></script>
        <script type="text/javascript" src="../../js/dataTables/js/jquery.dataTables.js"></script>
        <script type="text/javascript" src="../../js/dataTables/js/datatablesbootstrap.js"></script>
        <script type="text/javascript" src="../../js/validaDocumento.js"></script>
        <style>
            .menu-opciones{
                 clear: both;
                padding-top: 10px;
            }
        </style>
        <script>
            $(document).ready(function(){
                
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
                                                        <li><a href="misTorneos?idTorneo=${row.idTorneo}">${row.nombre}</a></li>
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
            <li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-home" aria-hidden="true"></span></a></li>
            <li role="presentation"><a href="calendario.jsp?idTorneo=${param.idTorneo}"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>Calendario</a></li>
            <li role="presentation"><a <c:if test="${detallestorneo.tipo==3}"> href="resultadoseli.jsp?idTorneo=${param.idTorneo}"</c:if> href="marcadores.jsp?idTorneo=${param.idTorneo}"><span class="glyphicon glyphicon-tasks" aria-hidden="true"></span>Resultados</a></li>
            <li role="presentation"><a href="misTorneos.jsp?idTorneo=${param.idTorneo}"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>Tablas</a></li>
            <li role="presentation"><a href="inscribirEquipos.jsp?idTorneo=${param.idTorneo}"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>Inscribir equipos</a></li>
        </ul>
    </div>
    </div>
    <div class="row">
        <div class="col-md-4 col-sm-2 col-xs-12">
            <ol class="breadcrumb">
                <li><a href="../inicio.jsp">Inicio</a></li>
                <li><a href="#">Torneos</a></li>
                <li><a href="centro.jsp?idTorneo=${param.idTorneo}">Centro</a></li>
                <li class="active">Jugadores</li>
            </ol>
        </div>
    </div>
        
        <div class="row">
            <div class="col-lg-6">
                <div class="page-header">
                    <h1>Jugadores del equipo ${param.nombre}</h1>
                </div>
                    <%--si se elimino un jugador--%>
                <% if (request.getParameter("usuarioeli")!=null) {
%>
<div class="alert alert-danger alert-dismissible" role="alert">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <strong><span class="glyphicon glyphicon-ok"></span><%=request.getParameter("usuarioeli")%></strong>
</div>
<%
                    }
%>
<%--si se elimino un jugador--%>
                <% if (request.getParameter("newjugador")!=null) {
%>
<div class="alert alert-success alert-dismissible" role="alert">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <strong><span class="glyphicon glyphicon-ok"></span><%=request.getParameter("newjugador")%></strong>
</div>
<%
                    }
%>

                <sql:query var="jugadores" dataSource="jdbc/pro-level">
                    select concat(usuarios.primernombre,' ',usuarios.primerapellido) jugador, usuarios.idUsuario from usuarios
inner join jugadoresporequipo on
usuarios.idUsuario = jugadoresporequipo.codigoJugador
inner join equipo on
equipo.codigo = jugadoresporequipo.codigoEquipo
where equipo.codigo = ?  <sql:param value="${param.codigoEquipo}"/>
                </sql:query>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Jugador</th>
                                <th>Expulsar Del Torneo</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="row" items="${jugadores.rows}" varStatus="vs">
                            <tr>
                                <td>${row.jugador}</td>
                                <td><a href="editarjugadores.jsp?codigoEquipo=${param.codigoEquipo}&nombre=${param.nombre}&idTorneo=${param.idTorneo}&idUsuario=${row.idUsuario}"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a></td>
                            </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                        <button class="btn btn-success" data-toggle="modal" data-target="#plusjugadores" data-backdrop="false"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>Añadir Jugador</button>
                        </tfoot>
                    </table>
            </div>
        </div>
<div class="modal fade" id="plusjugadores">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Inscribir Jugador A Este Equipo</h4>
      </div>
      <div class="modal-body">
          <form class="form-horizontal" role="form" action="../../Jugadores">
  <div class="form-group">
    <label class="control-label col-sm-2" for="id">Identificacion:</label>
    <div class="col-sm-10">
        <input type="number" class="form-control" id="id" name="idUsuario" placeholder="identificacion" onchange="validarDocumento(this,${param.idTorneo})" required>
        <input type="hidden" value="${param.codigoEquipo}" name="codigoEquipo"/>
        <input type="hidden" value="${param.nombre}" name="nombreEquipo" />
        <input type="hidden" value="${param.idTorneo}" name="idTorneo" />
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
        <div id="resultadouno" style="color: red"></div>
        <div id="resultadodos" style="color: green"></div>
        <button type="submit" class="btn btn-primary" id="crearEquipo" name="injugador">Inscribir</button>
    </div>
  </div>
</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<% if (request.getParameter("idUsuario")!=null && !request.getParameter("idUsuario").equals("")) {
%>
<input type="hidden" data-toggle="modal" data-target="#celiminar" id="midusuario"/>
<script>
    $(document).ready(function(){
        $("#midusuario").trigger("click");
    });
</script>
<%
    }
%>
<div class="modal fade" id="celiminar">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Eliminar Jugador</h4>
        </div>
        <div class="modal-body">
            <form action="../../Jugadores">
                <div class="form-group">
            <span class="label label-danger">Confira que desea expulsar del torneo al jugador</span>
                </div>
                <div class="form-group">
                    <label>Identificación</label>
                </div>
                <div class="form-group">
            <input type="text" value="${param.idUsuario}" name="idUsuario" readonly="readonly" />
                </div>
            <input type="hidden" value="${param.codigoEquipo}" name="codigoEquipo" />
            <input type="hidden" value="${param.nombre}" name="nombreEquipo" />
            <input type="hidden" value="${param.idTorneo}" name="idTorneo" />
            <button class="btn btn-primary" name="elijugador">Expulsar</button>
            <input type="hidden" name="celi" />
            </form>
        </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
            </div>
    </div>
  </div>
</div>
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


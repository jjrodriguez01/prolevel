<%-- 
    Document   : misTorneos
    Created on : 10/02/2015, 07:20:21 PM
    Author     : jeisson
--%>
<%@page import="modelo.TarjetasDTO"%>
<%@page import="modelo.TorneoDTO"%>
<%@page import="modelo.TablaPosicionesDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.GoleadoresDTO"%>
<%@page import="utilidades.MiExcepcion"%>
<%@page import="facade.FachadaTorneos"%>
<%@page import="modelo.UsuariosDTO"%>
<%@page import="java.util.LinkedList"%>
<%@page import="modelo.EquipoDTO"%>
<% 
            if (request.getSession()!=null && request.getSession().getAttribute("usr")!=null) {
                    UsuariosDTO udto = new UsuariosDTO();
                    HttpSession miSession=request.getSession(false);
                    udto = (UsuariosDTO)miSession.getAttribute("usr");
                    int rol = (Integer)miSession.getAttribute("rol");
                    GoleadoresDTO gol = new GoleadoresDTO();
                    UsuariosDTO usu = new UsuariosDTO();
                    EquipoDTO equipo = new EquipoDTO();
                    FachadaTorneos facade = new FachadaTorneos();
                    ArrayList<GoleadoresDTO> listarTodosGoleadores = (ArrayList) facade.listarTodosGoleadores(Integer.parseInt(request.getParameter("idTorneo")));
                    ArrayList<TablaPosicionesDTO> listarPosiciones = (ArrayList) facade.listarPosiciones(Integer.parseInt(request.getParameter("idTorneo")));
                    TorneoDTO torneo = new TorneoDTO();
                    torneo = facade.listarTorneo(Integer.parseInt(request.getParameter("idTorneo")));
                    ArrayList<TorneoDTO> torneos = (ArrayList) facade.listarTorneos();
                    ArrayList<TarjetasDTO> tarjetas = (ArrayList) facade.listarTodoTarjetas(Integer.parseInt(request.getParameter("idTorneo")));
                    if(rol == 1){
        %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <title><%=torneo.getNombre()%></title>
        <link rel="shortcut icon" href="../../imagenes/favicon.ico">
        <link href="../../css/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="../../css/estiloslayout.css" rel="stylesheet" type="text/css">
        <link href="../../css/estilosMisTorneos.css" rel="stylesheet" type="text/css">
        <link href="../../js/dataTables/css/dataTablesBootstrap.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <script type="text/javascript" src="../../js/jquery-2.1.1.js"></script>
        <script type="text/javascript" src="../../js/jquery.validate.js"></script>
        <script type="text/javascript" src="../../css/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../../js/jugadoresEquipos.js"></script>
        <script type="text/javascript" src="../../js/dataTables/js/jquery.dataTables.js"></script>
        <script type="text/javascript" src="../../js/dataTables/js/datatablesbootstrap.js"></script>
        <script>
            $(document).ready(function(){
                $("#tposiciones, #tgoleadores, #ttarjetas").dataTable({
                    language:{
                        url: "../../js/dataTables/js/dataespañol.json"
                    }
                });
            });
        </script>
        <script>//script del tooltip de los th
            $(document).ready(function(){
               $(".mitooltip").tooltip();
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
                                                    <% for(TorneoDTO tor : torneos){%>
                                                    <li><a href="misTorneos.jsp?idTorneo=<%=tor.getIdTorneo()%>"><%=tor.getNombre()%></a></li>
                                                    <% } %>
                                                </ul>
                                            </div>
                                        </div>
                                    </li>
                                    <li><a href="crear_torneo.jsp"><img src="../../imagenes/crear.png" width="24" height="24" alt="crear" />CREAR TORNEOS</a></li>
                                </ul>
                            </div>
                        </div>
                    </li>
                    <li><a href="../servicios.jsp"><img src="../../imagenes/servicios.png" width="24" height="24" alt="servicios" />SERVICIOS</a></li>
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
            <div class="pull-right"><span class="label label-success"> <%=udto.getPrimerNombre()%></span><span class="badge">Administrador</span></div>
        </header>
<main class="container">
    <div class="row">
    <div class="col-lg-12 menu-opciones">
        <ul class="nav nav-tabs nav-justified">
            <li role="presentation"><a href="centro.jsp?idTorneo=${param.idTorneo}"><span class="glyphicon glyphicon-home" aria-hidden="true"></span></a></li>
            <li role="presentation"><a href="calendario.jsp?idTorneo=${param.idTorneo}"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>Calendario</a></li>
            <li role="presentation"><a <% if(torneo.getTipo() == 3) {%> href="resultadoseli.jsp?idTorneo=<%=Integer.parseInt(request.getParameter("idTorneo"))%>"<% } else {%> href="marcadores.jsp?idTorneo=<%=Integer.parseInt(request.getParameter("idTorneo"))%>"<% } %>><span class="glyphicon glyphicon-tasks" aria-hidden="true"></span>Resultados</a></li>
            <li role="presentation" class="active"><a href="misTorneos.jsp?idTorneo=<%=Integer.parseInt(request.getParameter("idTorneo"))%>"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>Tablas</a></li>
            <li role="presentation"><a href="inscribirEquipos.jsp?idTorneo=<%=Integer.parseInt(request.getParameter("idTorneo"))%>"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>Inscribir equipos</a></li>
        </ul>
    </div>
    </div>
    <div class="row">
        <div class="col-md-4 col-sm-2 col-xs-12">
            <ol class="breadcrumb">
                <li><a href="../inicio.jsp">Inicio</a></li>
                <li><a href="misTorneos.jsp?idTorneo=<%=Integer.parseInt(request.getParameter("idTorneo"))%>">Torneos</a></li>
                <li class="active">Tablas</li>
            </ol>
        </div>
    </div>
            <hgroup>
                <h1 id="titulo"><%=torneo.getNombre()%></h1>
                <%if(torneo.getTipo() == 1 || torneo.getTipo() == 2){%>
                <h3 class="tablatit">Tabla De Posiciones</h3>
            </hgroup>
            <div class="row">
                <div class="col-md-9 col-sm-9">
                    <table id="tposiciones" class="table table-responsive">
                        <!-- column headers -->
                        <thead>
                            <tr>
                                <th class="mitooltip" title="Posicion">POS</th>
                                <th class="mitooltip" title="Equipo">EQUIPO</th>
                                <th class="mitooltip" title="Partidos Jugados">PJ</th>
                                <th class="mitooltip" title="Partidos Ganados">PG</th>
                                <th class="mitooltip" title="Parrtidos Empadados">PE</th>
                                <th class="mitooltip" title="Partidos Perdidos">PP</th>
                                <th class="mitooltip" title="Goles Marcados">GOLES</th>
                                <th class="mitooltip" title="Goles En Contra">GC</th>
                                <th class="mitooltip" title="Diferencia De Goles">GD</th>
                                <th class="mitooltip" title="Puntos">PTS</th>
                            </tr>
                        </thead>
                        <!-- column data -->
                        <tbody>
                            <%!  int pos = 0;  %>
                            <% for(TablaPosicionesDTO p : listarPosiciones){%>
                                <tr>
                                    <% 
                                       pos ++;
                                    %>
                                    <td><%=pos%></td>
                                    <td><%=p.getEquipo().getNombre()%></td>
                                    <td><%=p.getPartidosJugados()%></td>
                                    <td><%=p.getPartidosGanados()%></td>
                                    <td><%=p.getPartidosEmpatados()%></td>
                                    <td><%=p.getPartidosPerdidos()%></td>
                                    <td><%=p.getGolesAnotados()%></td>
                                    <td><%=p.getGolesRecibidos()%></td>
                                    <td><%=p.getDiferencia()%></td>
                                </tr>
                            <% } %>
                        </tbody>
                        <tfoot>
                        <a href="../../Reportes?tabla=posiciones&idTorneo=<%=Integer.parseInt(request.getParameter("idTorneo"))%>"><i class="fa fa-file-pdf-o"></i> Exportar esta tabla a PDF</a>
                        </tfoot>
                    </table>
                </div>
            </div>
            <% } %>
            <div class="row">
                <div class="col-md-6 col-sm-4 col-xs-12">
                    <h3 class="tablatit">Tabla Goleadores</h3>
                    <table id="tgoleadores" class="table">
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
                        <% for(GoleadoresDTO g : listarTodosGoleadores){ %>
                                <% pgol++; %>
                                <tr>
                                    <td><%=pgol%></td>
                                    <td><%=g.getUsu().getPrimerNombre()%></td>
                                    <td><%=g.getUsu().getPrimerApellido()%></td>
                                    <td><%=g.getNumeroGoles()%></td>
                                    <td><%=g.getEquipo().getNombre()%></td>
                                </tr>
                            <%  }  %>
                        </tbody>
                        <tfoot>
                        <a href="../../Reportes?tabla=goleadores&idTorneo=<%=Integer.parseInt(request.getParameter("idTorneo"))%>"><i class="fa fa-file-pdf-o"></i> Exportar esta tabla a PDF</a>
                        </tfoot>
                    </table>
                    <button class="btn btn-success" id="btntar" data-toggle="modal" data-target="#goles"><i class="fa fa-futbol-o"></i> Asignar Goles</button>
                    <% 
                if (request.getParameter("goles")!=null) {
                    %>
                    <span class='confirmt'><%request.getParameter("goles");%></span>
                    <% 
                    }
                    %>
                </div>
                <div id="tablatarjetas" class="col-md-6 col-sm-4 col-xs-12">
                    <h3 class="tablatit">Tabla De Tarjetas</h3>
                    <table id="ttarjetas" class="table">
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
                            <% for(TarjetasDTO tar : tarjetas){ %>
                                <tr>
                                    <td><%=tar.getUsu().getPrimerNombre()%></td>
                                    <td><%=tar.getTarjetaAzul()%></td>
                                    <td><%=tar.getTarjetaRoja()%></td>
                                    <td><%=tar.getEquipo().getNombre()%></td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                    <button class="btn btn-success" id="btntar" data-toggle="modal" data-target="#tarjetas"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Asignar Tarjetas</button>
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
                            FachadaTorneos facadeTorneos = new FachadaTorneos(); 
                            try{
                            LinkedList<EquipoDTO> Equipos = new LinkedList <EquipoDTO>();
                            Equipos = (LinkedList) facadeTorneos.listarEquiposEnTorneo(Integer.parseInt(request.getParameter("idTorneo")));
                            
                            for (EquipoDTO edto : Equipos) {
                        %>
                        <option value="<%=edto.getCodigo()%>"> <%=edto.getNombre()%></option>
                        <%
                          }
                            
                        }catch(MiExcepcion mie){ 
                            response.sendError(500, mie.getMessage());//si hubo error reenvio el error
                        }
                        %>
                    </select>            
                    <label>Seleccione el jugador a aplicar tarjetas</label>
                    <select name="jugadores" class="jugadores" id="jugadorest">
                        <option>Seleccione un equipo</option>
                    </select>
                    <span id="rojas"><img src="../../imagenes/Tarjeta_roja.png" width="21" height="30" alt="Tarjeta_roja"/>
                    </span>
                    <input type="text" name="rojas"/>
                    <span id="amarillas"><img src="../../imagenes/tarjeta_amarilla.png" width="21" height="30" alt="tarjeta_amarilla"/>
                    </span>
                    <input type="text" name="azules"/>
                    <input type="hidden" name="idTorneo" value="<%=Integer.parseInt(request.getParameter("idTorneo"))%>"/>
                    <input type="submit" name="asigtarjetas" value="Asignar" class="btn"/>
                    <input type="hidden" name="tarjetas" />
                </form>
                        </div>
                    </div>
                </div>     
            </div>
                    
                    <%-- ventana modal de los goles--%>
<div id="goles" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h3>Asignar Goles</h3>
                        </div>
                        <div class="modal-body">
                <form method="get" action="../../Goles">
                    <label>Seleccione Un Equipo</label>
                    <select name="equipo" class="" onchange="getJugadorG(this.value);">
                        <option>Seleccione equipo</option>
                        <%
 
                            try{
                            LinkedList<EquipoDTO> Equiposg = new LinkedList <EquipoDTO>();
                            Equiposg = (LinkedList) facadeTorneos.listarEquiposEnTorneo(Integer.parseInt(request.getParameter("idTorneo")));
                            
                            for (EquipoDTO edto : Equiposg) {
                        %>
                        <option value="<%=edto.getCodigo()%>"> <%=edto.getNombre()%></option>
                        <%
                          }
                            
                        }catch(MiExcepcion mie){ 
                            response.sendError(500, mie.getMessage());//si hubo error reenvio el error
                        }
                        %>
                    </select>            
                    <label>Seleccione el jugador a asignar goles</label>
                    <select name="jugadores" class="jugadores" id="jugadoresg">
                        <option>Seleccione un equipo</option>
                    </select>
                    <span id="rojas"><img src="../../imagenes/balon.png" width="21" height="30" alt="Tarjeta_roja"/>
                    </span>
                    <input type="number" name="nrogoles" maxlength="2" required/>
                    
                    <input type="hidden" name="idTorneo" value="<%=Integer.parseInt(request.getParameter("idTorneo"))%>"/>
                    <input type="submit" name="asignargoles" value="Asignar" class="btn"/>
                    <input type="hidden" name="goles" value="goles" />
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
                response.sendRedirect("../../index.jsp?auth=prohibido");
            }                 
%>
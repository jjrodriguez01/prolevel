<%-- 
    Document   : listarReservas
    Created on : 04-mar-2015, 9:30:12
    Author     : Sena
--%>

<%@page import="DTO.ReservasDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.ReservasDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap/css/bootstrap.min.css">
        <script type="text/javascript" src="css/bootstrap/js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="css/bootstrap/js/bootstrap.min.js"></script>
        <link href="css/estiloslayout.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="EstilosReserva/listar.js"></script>
   
<link href="css/reservar.css" rel="stylesheet" type="text/css">
<link href="EstilosReserva/Estilos.css" rel="stylesheet" type="text/css">
</head>

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
                        	<ul>
                            	<li><a><span>Torneo relï¿½mpago</span></a></li>
                                <li><a><span>Copa Navidad</span></a></li>
                                <li><a><span>Copa Navidad Femenino</span></a></li>
                            </ul>
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
            <li><a href="index.jsp"><img src="imagenes/cancha.png" width="24" height="24" alt="reservas" />RESERVAR</a></li>
           
            <li><a href="listarReservas.jsp"><img src="imagenes/informe.png" width="24" height="24" alt="info" />INFORME DE RESERVAS</a></li>
          </ul>
        </div>
        </li>
      <li><a href="#"><img src="imagenes/servicios.png" width="24" height="24" alt="servicios" />SERVICIOS</a></li>
      <li><a href="#"><span><img src="imagenes/perfil.png" width="24" height="24" alt="perfil" />PERFIL</span></a>
      <center>  	<div class="subs">
        	<ul>
                    <li><a href="#"><img src="imagenes/ajustes.png" width="24" height="24" alt="ajustes" />ADMINISTRAR</a></li>
            </ul>
        </div>
          <center>  
        </li>
    </ul>
</nav>
</header>

        
        <title>LISTAS</title>
    </head>
    
        <br><br>
    <h1><center>LISTA RESERVAS!</center></h1>
        <%  
            ReservasDAO rdao = new ReservasDAO();
            
            ArrayList<ReservasDTO> reserva =  (ArrayList) rdao.listarTodo();                  
        %>
    
    
   </head>
 
       

		
        <table  border="0"  class="table table-responsive table-striped table-hover" id="Listado">
            <thead>
                <tr>
                    <th>Codigo</th>
                    <th>Fecha</th>
                    <th>Hora</th>
                    <th>Cancha</th>
                    <th>Modificar</th>
                    <th>Cancelar</th>
                </tr>
            </thead>     
               <% 
            for(ReservasDTO res : reserva){
                %>
                 <tbody>
            <td><%=res.getCodigo()%></td>
            <td><%=res.getFecha()%></td>
            <td><%=res.getHora()%></td>
            <td><%=res.getNumeroCancha()%></td>
            <td>
                <form>
                    <input type="hidden" value="<%=res.getCodigo()%>" name="codigor">
                    <input type="hidden" value="<%=res.getFecha()%>" name="fechar">
                    <input type="hidden" value="<%=res.getHora()%>" name="horar">
                    <input type="hidden" value="<%=res.getNumeroCancha()%>" name="canchar">
                <button class="glyphicon glyphicon-edit" name="modreserva" value="mod" >modificar</button>
                </form>
                
            </td>
            <td><a href="GestionReservas?eliminareserva=<%=res.getCodigo()%>">cancelar</a></td>
             </tbody>
            <%
               }
            %>      
        </table>     
    
             <% if(request.getParameter("modreserva")!=null) {  %>
             <button id="btnmod" data-toggle="modal" data-target="#mreserva" style="display: none"></button>
             <script>
                 $(document).ready(function(){
                     $("#btnmod").trigger("click");
                 });
             </script>
        <%     
            }
        %>
        <div class="modal fade" id="mreserva" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h3>Modificar Reserva</h3>
            </div>
            <div class="modal-body">
                <form action="GestionReservas">
                <label>Cambiar Fecha (anterior <%=request.getParameter("fechar")%>)</label>
                <input type="date" name="mfechar"  />
                <label>Cambiar Hora (anterior <%=request.getParameter("horar")%>)</label>
                <input type="time" name="mhorar" />
                <label>Cambiar Cancha (anterior <%=request.getParameter("canchar")%>)</label>
                <select name="mcanchar">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
                <input type="hidden" name="cod" value="<%=request.getParameter("codigor")%>" />
                <button name="cmodificar">Modificar</button>
                </form>
            </div>
        </div>
    </div>
</div>
                 
        
   <div id="pageNavPosition" style="text-align: center"></div>
                        <script type="text/javascript">
                            var pager = new Pager('Listado', 8);
                            pager.init();
                            pager.showPageNav('pager', 'pageNavPosition');
                            pager.showPage(1);
                        </script>  
   
  <% if(request.getParameter("mensaje")!=null) {  %>
        <script>
            alert("<%=request.getParameter("mensaje")%>");
        </script>
        <%     
            }
        %>
   
    </body>
</html>

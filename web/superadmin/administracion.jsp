<%-- 
    Document   : inscribirEquipos
    Created on : 4/04/2015, 12:17:36 AM
    Author     : jeisson
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="facade.FachadaUsuarios"%>
<%@page import="persistencia.UsuariosDAO"%>
<%@page import="modelo.UsuariosDTO"%>
<% 
            if (request.getSession()!=null) {
                    
                    HttpSession miSession=request.getSession(false);
                    int rol = (Integer)miSession.getAttribute("rol");
                    if(rol == 3){
                    FachadaUsuarios facade = new FachadaUsuarios();
                    ArrayList<UsuariosDTO> listarUsuarios = new ArrayList();
                    listarUsuarios = (ArrayList) facade.listarUsuariosconRoles();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administración</title>
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
    </head>
    <body>
        <header>
            <nav>
                
            </nav>
        </header>
<main class="container">
    
    <div class="row">
        <div class="col-md-4 col-sm-2 col-xs-12">
            <%
                    if(request.getParameter("cambio")!=null){
            %>
<div class="alert alert-info alert-dismissible" role="alert">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <span class="glyphicon glyphicon-ok" aria-hidden="true"></span><strong> <%=request.getParameter("cambio")%></strong>
</div>
            <%
                    }
            %> 
            <table>
                <thead>
                    <th>Documento</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Rol Actual</th>
                    <th>Nuevo Rol</th>
                    <th>Cambiar Rol</th>
                </thead>
                
                    <%
                    for(UsuariosDTO usu : listarUsuarios){
                    %>
                    <form action="../Administrador">
                        <td><%=usu.getIdUsuario()%><input type="hidden" name="idUsuario" value="<%=usu.getIdUsuario()%>"</td>
                <td><%=usu.getPrimerNombre()%></td>
                <td><%=usu.getPrimerApellido()%></td>
                <td><%=usu.getRol().getRolesidRol()%></td>
                <td>
                    <select name="numRol">
                        <option>1</option>
                        <option>2</option>
                    </select>
                </td>
                <td><button>Cambiar rol</button></td>
                </form>
                    <%
                        }    
                    %>
                
            </table>
        </div>
    </div>
    
</main>
    </body>
</html>
<% 
    }//si el rol fue 3
                    
}//si hay sesion
                    else{
                        response.sendRedirect("../../index.jsp?auth=prohibido");
                    }
%>
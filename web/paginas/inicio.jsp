<%-- 
    Document   : inicio
    Created on : 28/01/2015, 11:50:40 PM
    Author     : jeisson
--%>
<%@page import="persistencia.UsuariosDAO"%>
<%@page import="modelo.UsuariosDTO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<sql:query var="torneo" dataSource="jdbc/pro-level">
    SELECT idTorneo, nombre FROM torneo
</sql:query>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
            if (request.getSession() != null) { 
                UsuariosDTO udto = new UsuariosDTO();
                    UsuariosDAO udao = new UsuariosDAO();
                    HttpSession miSession=request.getSession(false);
                    udto = (UsuariosDTO)miSession.getAttribute("usr");
                    int rol = (Integer)miSession.getAttribute("rol");
                    if (rol == 1) {
                            
%>
<!doctype html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="../css/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="../css/estiloslayout.css" rel="stylesheet" type="text/css">
<link href="../css/estilosinicio.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../js/jquery.flip.js"></script>
<script type="text/javascript" src="../js/listaTorneo.js"></script>
<script type="text/javascript" src="../css/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
        $(document).ready(function(){
            //nos desplazamos entre todos los divs
            $('a.ancla').click(function(e){
            e.preventDefault();
            enlace  = $(this).attr('href');
            $('html, body').animate({
                scrollTop: $(enlace).offset().top
            }, 1000);
            });
            //vamos al principio o al final de la página
            $('a.arriba').click(function(e){
            e.preventDefault();
            volver  = $(this).attr('href');
            $('html, body').animate({
                scrollTop: $(volver).offset().top
            }, 2000);
            });
            //pasando la cantidad de pixeles que queremos a scrollTop
            $('.prueba').click(function(){
                $('html, body').animate({scrollTop:100}, 2000); return false;
            });
        });
</script>
<script>
			$(document).ready(function() {
                $("#abajo").hover(function(){
					$("#abajo").attr("src","imagenes/abajo2.png");
				},function(){
					$("#abajo").attr("src","imagenes/abajo.png");
				});
				$("#masabajo").hover(function(){
					$("#masabajo").attr("src","imagenes/abajo2.png");
				},function(){
					$("#masabajo").attr("src","imagenes/abajo.png");
				});
				$("#aunmasabajo").hover(function(){
					$("#aunmasabajo").attr("src","imagenes/arriba2.png");
				},function(){
					$("#aunmasabajo").attr("src","imagenes/arriba.png");
				});
            });
</script>
<script>
			$(function(){
				
				$("#flipPad a:not(.revert)").bind("click",function(){
					var $this = $(this);
					$("#flipbox").flip({
						direction: $this.attr("rel"),
						color: $this.attr("rev"),
						content: $this.attr("title"),
						onBefore: function(){$(".revert").show()}
					})
					return false;
				});
				
				$(".revert").bind("click",function(){
					$("#flipbox").revertFlip();
					return false;
				});
				
				var changeMailTo = function(){
					var mArr = ["@","smashup","luca",".it"];
					$("#email").attr("href","mailto:"+mArr[2]+mArr[0]+mArr[1]+mArr[3]);
				}
				
				$(".downloadBtn").click(function(){
					pageTracker._trackPageview('download_flip');
				});	
				
				setTimeout(changeMailTo,500);
				
			});
</script>
<meta charset="utf-8">
<title>Pro-level::Inicio</title>
</head>
<body>
<header>
    <nav class="navbar">
    <ul id="nav" class="nav">
      		<li><a href="inicio.jsp"><img src="../imagenes/inicio.png" width="24" height="24" alt="inicio" /> INICIO</a></li>
      		<li><a href="#"><span><img src="../imagenes/copa.png" width="24" height="24" alt="copa" /> TORNEOS</span></a><
        <div class="subs">
          <div class="col">
            <ul>
              <li><a><img src="../imagenes/micopa.png" width="24" height="24" alt="micopa"/>MIS TORNEOS</a>
              		<div class="subs">
                    	<div class="col">
                                    <ul>
                                        <c:forEach var="row" items="${torneo.rows}">
                                            <li><a href="torneos/misTorneos.jsp?idTorneo=${row.idTorneo}">${row.nombre}</a></li>
                                        </c:forEach>
                                    </ul>
                        </div>
                    </div>
                  </li>
              <li><a href="crear_torneo.jsp"><img src="../imagenes/crear.png" width="24" height="24" alt="crear" />CREAR TORNEOS</a></li>
            </ul>
          </div>
        </div>
      </li>
      <li><a href="#"><span><img src="../imagenes/telefono.png" width="24" height="24" alt="reservar" />RESERVAS</span></a>
        <div class="subs">
          <ul>
            <li><a href="#"><img src="imagenes/cancha.png" width="24" height="24" alt="reservas" />RESERVAR</a></li>
            <li><a href="#"><img src="imagenes/instructivo.png" width="24" height="24" alt="ins" />INSTRUCTIVO</a></li>
            <li><a href="#"><img src="imagenes/informe.png" width="24" height="24" alt="info" />INFORME DE RESERVAS</a></li>
          </ul>
        </div>
        </li>
      <li><a href="#"><img src="../imagenes/servicios.png" width="24" height="24" alt="servicios" />SERVICIOS</a></li>
      <li><a href="#"><span><img src="../imagenes/perfil.png" width="24" height="24" alt="perfil" />PERFIL</span></a>
      	<div class="subs">
        	<ul>
            	<li><a href="admin.jsp"><img src="../imagenes/ajustes.png" width="24" height="24" alt="ajustes" />ADMINISTRAR</a></li>
                <li><a href="../Ingreso?logout=cerrar"><img src="../imagenes/out.png" width="24" height="24" alt="cerrar" />CERRAR CESIÓN</a></li>
            </ul>
        </div>
        </li>
    </ul>
</nav>
</header>
<!-- InstanceBeginEditable name="body" -->
<main>
<div id="uno">
<section class="primer">
    <hgroup>
        <h1>BIENVENIDO A PRO-LEVEL.</h1>
        <h5>El software para la administracion de sus campeonatos de futbol y gestion de las reservas de sus canchas</h5>
    </hgroup>
</section>
    <a href="#dos" class="ancla">
        <img src="imagenes/abajo.png" id="abajo" width="100" height="100" onMouseOver="imagen()"
            onMouseOut="imgout()"/></a>
</div>
    <div id="dos">
        <div id="flipbox">Pro-level</div>
            <div id="flipPad">
                <a href="#" class="left" rel="rl" rev="#39AB3E" title="Pro-level es el unico software especializado en la creacion de campeonatos 					de futbol">¿Por que pro-level?</a>
                <a href="#" class="top" rel="bt" rev="#B0EB17" title="Maneje desde el mismo aplicativo sus campeonatos de futbol y las reservas de 						sus canchas">¿Para que pro-level?</a>
                <a href="#" class="bottom" rel="tb" rev="#82BD2E" title="Con Pro-level sus clientes se informaran de sus campeonatos y servicios en la misma pagina en la que reservan las canchas">¿Me ayuda Pro-level?</a>
                <a href="#" class="right" rel="lr" rev="#C8D97E" title="Este es una aplicativo que puede usar facilmente y de manera intuitiva">¿como lo uso?</a>
            </div>
                <a href="#tres" class="ancla">
                <img src="imagenes/abajo.png" id="masabajo" width="100" height="100" onMouseOver="imagen2()"
   			onMouseOut="imgout2()"/></a>
    </div>
<div id="tres">
    <div id="three-column" class="container">
        <div id="tbox1">
            <div class="box-style box-style01">
                    <div class="content">
                            <div class="image"><img src="imagenes/iniciotorneo.jpg" width="324" height="200" alt="torneos" /></div>
                            <h2>TORNEOS</h2>
                            <p>Cree torneos como copas, ligas y eliminatorias totalmente personalizables </p>
                    </div>
            </div>
        </div>
            <div id="tbox2">
                    <div class="box-style box-style02">
                            <div class="content">
                                    <div class="image"><img src="imagenes/inicioreserva.jpg" width="324" height="200" alt="reservas" /></div>
                                    <h2>RESERVAS</h2>
                                    <p>Realice las reservas de las canchas en su establecimiento e informe a sus clientes de las canchas que no estaran disponibles
                en determinada fecha y hora</p>
                            </div>
                    </div>
</div>
<div id="tbox3">
    <div class="box-style box-style03">
        <div class="content">
                <div class="image"><img src="imagenes/inicioadmin.png" width="324" height="200" alt="administracion"/></div>
                <h2>ADMINISTRACION</h2>
                <p>Obtenga el control de Pro-level mediante la administracion de su perfil, puede modificar sus datos personales </p>
        </div>
    </div>
</div>
</div>
    	<a href="#uno" class="ancla">
            <img src="imagenes/arriba.png" id="aunmasabajo" width="100" height="100" onMouseOver="imagen3()"
   			onMouseOut="imgout2()"/></a>
		</div>
</main>
<footer>
<div id="pie">
<p class="pie">2014 PRO-LEVEL - Todos los derechos reservados | Cambiar idioma <a href="#">
        <img src="../imagenes/english.png" width="40" height="30" /></a></p>  
</div>
</footer>
</body>
<% }//si el rol fue uno se muestra la anterior pagina
    else if (rol == 2) {
                            
    }//si el rol fue 2
    }//si no hay sesion
            else{
                response.sendRedirect("index.jsp");
            }
        
%>


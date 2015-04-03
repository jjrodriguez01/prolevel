<%-- 
    Document   : eliminatoria
    Created on : 4/02/2015, 01:12:46 AM
    Author     : jeisson
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<sql:query var="torneo" dataSource="jdbc/pro-level">
    SELECT idTorneo, nombre FROM torneo
</sql:query>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html><!-- InstanceBegin template="/Templates/layout.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<!-- InstanceBeginEditable name="doctitle" -->
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="head" -->
<title>Nueva Eliminatoria</title>
<link href="css/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/estiloslayout.css" rel="stylesheet" type="text/css">
<link href="css/estiloseliminatoria.css" rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Bree+Serif' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/additional-methods.js"></script>
<link href="js/datepicker/jquery-ui.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/datepicker/jquery-ui.js"></script>
<script type="text/javascript" src="js/listaTorneo.js"></script>
<script>
$(document).ready(function() {
    $(function() {
   $( ".datepicker" ).datepicker( "option", "maxDate", +30 );
   $( ".datepicker" ).datepicker( "option", "minDate", 0 );
   $( ".datepicker" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
  });
});
</script> 
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
<script>
    $(document).ready(function(){
        $("#confirm").click(function(){
            $(this).hide("slow");
        });
    });
</script>
<!-- InstanceEndEditable -->
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
<!-- InstanceBeginEditable name="body" -->
<main>
<h1>Crear Una Nueva Eliminatoria</h1>
<% if (request.getParameter("mensaje") != null) { %>
        <div id="confirm" class="alert alert-warning alert-dismissable">
        <span class="close" data-dismiss="alert">&times;</span>
        <strong>¡Nueva Liga Creada!</strong>
        </div>
    <% 
        }       
    %>
<form id="eliminatoria">
    <fieldset>
    <label for="nombreTorneo">Nombre del torneo</label>
    <input id="nombreTorneo" name="nombreTorneo" type="text" maxlength="25" required>
    <label for="capacidad">Capacidad de equipos</label>
        <select id="capacidad" name="capacidad">
            <option></option>
            <option>16</option>
            <option>32</option>
        </select>
    <label for="idaVuelta">¿Partidos a ida y vuelta?</label>/
        <select id="idaVuelta" name="idaVuelta" required>
            <option></option>
            <option id="idVueltaSi">Si</option>
            <option id="idaVueltaNo">No, muerte súbita</option>
        </select>
    <label for="finalIdaVuelta">¿El partido de la final se juega de ida y vuelta?</label>
        <select id="finalIdaVuelta" required>
            <option></option>
            <option id="finaIidaVueltaSi">Si</option>
            <option id="finalIdaVueltaNo">No, partido único</option>
        </select>
    <label for="tipo">Torneo:</label>
        <select name="tipo">
            <option></option>
            <option>Masculino</option>
            <option>Femenino</option>
        </select>
    <label for="inicio">Fecha de inicio:</label>
    <input type="text" class="datepicker" name="inicio" required>
    <label for="fin">Fecha de finalizacion:</label>
    <input type="text" class="datepicker" name="fin" required>
    <br/>

    <button type="submit" name="eliminatoria">CREAR</button>
    <input type="hidden" name="enviareli" />
    </fieldset>
</form>
<section>
	<div id="animacionbalon">
 		 <img src="imagenes/balon.png" width="50" height="50"  alt=""/> 
	</div>
</section>
</main>
<script>
   $().ready(function(){
       $("#eliminatoria").validate({
	            rules:{
				  nombreTorneo:{
				    required: true,
					minlength: 3
				  },
				  capacidad:{
            		 required: true,
         			 maxlength: 4,
      			     number:true
        			  },
				 grupos:{
				  	required: true
				  },
          			idaVuelta:{
            		required: true,
          		  },
				  	finalIdaVuelta:{
					required: true
				  },
				  tercer:{
				  required: true,
        		},
				 tipo:{
				  required:true,	 
					 },
				inicio:{
					required: true,
					date: true
					},
				fin:{
					required: true,
					date:true
					}
		       },
		        messages:{
		      nombreTorneo:{
					required:"Campo Requerido",
				    minlength:"Minimo{0} caracteres"					  
				   },
             capacidad:{
					required:"Camopo Requerido",
					maxlength:"maximo 4 digitos ",
					number:"Solo Numeros Por Favor"
		          },
			grupos:{
					required:"campo requerido"
					  },
        	idaVuelta:{
            		required:"campo requerido"
            			},
		    finalIdaVuelta:{
					  required:"campo requerido"  
					  },
        	tercer:{
            	    required:"campo requerido"
                	},
			tipo:{
					required:"campo requerido"
				},
			inicio:{
					required:"Indique la fecha de inicio de la liga",
					date:"Por favor ingrese una fecha válida"
			},
			fin:{
					required:"Indique la fecha de fin de la liga",
					date:"Por favor ingrese una fecha válida"
				}				  
		  }
	   
	       });
	   });
</script>
<footer>
<p class="pie">2014 PRO-LEVEL - Todos los derechos reservados | Cambiar idioma <a href="#" hreflang="en"><img src="imagenes/english.png" width="40px" height="30px" /></a></p> 
<img src="imagenes/cesped.png" width="100%" height="100px"/> </footer>
<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd --></html>

<%-- 
    Document   : copa
    Created on : 4/02/2015, 12:22:56 AM
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
<title>Crear Copa</title>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="head" -->
<link href="css/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/estiloslayout.css" rel="stylesheet" type="text/css">
<link href="css/estiloscopa.css" rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Bree+Serif' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
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
    
  <h1>Crear Una Nueva Copa</h1>
  <% if (request.getParameter("mensaje") != null) { %>
    <div class="alert alert-warning alert-dismissable" id="confirm" style="position:absolute; width: 90%">
            <span id="cerrar" class="close" data-dismiss="alert">&times;</span>
        <strong>¡Nueva Liga Creada!</strong>
        </div>
        <script>
         
            $("#confirm").click(function(){
                $("#confirm").hide("slow");
            });
        
            </script>
    <% 
        }       
    %>
  <form id="copa" method="get" action="GestionCopa" role="form">
      <fieldset>  
    <div class="form-group">
      <label for="nombreTorneo">Nombre del torneo</label>
      	<input id="nombreTorneo" name="nombreTorneo" type="text" maxlength="25" required>
    </div>
        <div class="form-group">
            <label for="capacidad">Capacidad de equipos</label>
                <select id="capacidad" name="capacidad" required>
                    <option></option>
                    <option>16</option>
                    <option>32</option>
                </select>
        </div>
        <div class="form-group">
                    <label for="grupos">Numero de equipos en caga grupo</label>
                            <select id="grupos" name="numgrupos" required>
                                <option></option>
                                <option>4</option>
                                <option>8</option>
                            </select>
        </div>
        <div class="form-group">
                                <label>En la fase de grupos</label>
                                    <label for="idaVueltagrupos">¿Se juegan partidos de ida y vuelta?</label>
                                        <select id="idaVueltagrupos" name="idaVueltagrupos" title="Debe seleccionar algo" required>
                                          <option></option>
                                          <option>Si</option>
                                          <option>No, partido único</option>
                                        </select>
        </div>
        <div class="form-group">
                                          <label>En la ronda eliminatoria</label>
                                              <label for="idaVueltaeli">¿Se juegan partidos de ida y vuelta?</label>
                                                 <select id="idaVueltaeli" name="idaVueltaeli" required>
                                                    <option></option>
                                                    <option>Si</option>
                                                    <option>No, partido único</option>
                                                  </select>
        </div>
        <div class="form-group">
                                                    <label for="finalIdaVuelta">¿El partido de la final se juega de ida y vuelta?</label>
                                                      <select id="finalIdaVuelta" name="finalIdaVuelta" required>
                                                            <option></option>
                                                            <option>Si</option>
                                                            <option>No, partido único</option>
                                                        </select>
        </div>
        <div class="form-group">
                                                            <label for="tercer">¿Se juega partido pot tercer puesto?</label>
                                                                    <select id="tercer" name="tercer" required>
                                                                      <option></option>
                                                                      <option>Si</option>
                                                                      <option>No</option>
                                                                      </select>
        </div>
        <div class="form-group">
                                                                      <label>Torneo:</label>
                                                                          <select id="tipo" name="tipo">
                                                                                  <option></option>
                                                                              <option>Masculino</option>
                                                                              <option>Femenino</option>
                                                                          </select>
        </div>
        <div class="form-group">
            <label for="inicio">Fecha de inicio:</label>
                <input type="text" class="datepicker" name="inicio" required>
        </div>
        <div class="form-group">
            <label for="fin">Fecha de finalizacion:</label>
                <input type="text" class="datepicker" name="fin" required>
        </div>                                                           
        <div id="btn">                   
            <button type="submit" name="copa">CREAR</button>
                <input type="hidden" name="enviarcopa" />
 	   </div>
    </fieldset>
  </form>
  
  <section>
  <div id="animacionbalon">
    <img src="imagenes/balon.png" width="50" height="50"  alt=""/> 
  </div>
  </section>
</main>
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
   $().ready(function(){
       $("#copa").validate({
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
					  numgrupos:{
						  required:true
						  },
				 idaVueltagrupos:{
				  	required: true
				  },
          			idaVueltaeli:{
            		required: true,
          		  },
				  	finalIdaVuelta:{
					required: true
				  },
				  tercer:{
				  required: true,
        		},
				tipo:{
				  required: true,
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
					required:"campo requerido",
				    minlength:"Minimo{0} caracteres"					  
				   },
             capacidad:{
					required:"campo requerido",
					maxlength:"maximo 4 digitos ",
					number:"Solo Numeros Por Favor"
		          },
				  numgrupos:{
					  required:"campo requerido"
					  },
			idaVueltagrupos:{
					required:"campo requerido"
					  },
        	idaVueltaeli:{
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
<img src="imagenes/cesped.png" width="100%" height="100"/> </footer>
<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd --></html>

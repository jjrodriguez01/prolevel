<!doctype html>
<html>
<head>
<meta charset="utf-8">

<title>Reservar</title>

<head>
<meta charset="utf-8">
<title>Reserva</title>
<link href="../../css/estiloslayout.css" rel="stylesheet" type="text/css">
<link href="../../css/reservar.css" rel="stylesheet" type="text/css">
<link href="../../css/Estilos.css" rel="stylesheet" type="text/css">
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


<body>
</br>
</br></br>
</br>



    <center><h1>Reserva</h1> </center>
    
<center>
      <form id="formulario" action="../../GestionReservas" method="POST">
       
            <label>Fecha</label>
           

            <input type="date" name="fecha"/>
            <br>
            <label>Hora</label>
            
            <input type="time" name="hora" />
            <br><br>
            <label>Cancha</label>
            <br>
                <select name="cancha">
                <option value="1">1</option>
                <option value="2">2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
            </select>
            <br>
          
      <input type="submit" name="reservar" />
      
</form> 
</center>
       
    </body>
<footer>
<div id="pie">
<p class="pie">2014 PRO-LEVEL - Todos los derechos reservados | Cambiar idioma <a href="#"><img src="imagenes/english.png" width="40px" height="30px" /></a></p> 
<img src="imagenes/cesped.png" width="100%" height="29px"/> 
</div>
</footer>
</body>
</html>

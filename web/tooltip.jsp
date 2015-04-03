<!DOCTYPE html>
<html>
<head>
<title>onepagescroller</title>

<link href="js/datepicker/jquery-ui.min.css" rel="stylesheet" type="text/css">
        <link href="css/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
        
        <link href="css/estiloscrear_torneo.css" rel="stylesheet" type="text/css">
        <link href="css/onepage-scroll.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="js/listaTorneo.js"></script>
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="js/jquery.validate.js"></script>
        <script type="text/javascript" src="css/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/datepicker/jquery-ui.min.js"></script>
        <script type="text/javascript" src="js/validacionTorneos.js"></script>
        <script type="text/javascript" src="js/jquery.onepage-scroll.js"></script>
        <link href="css/estiloslayout.css" rel="stylesheet" type="text/css">
  <style>

  </style>
  <script>
  $(document).ready(function(){
	$(".main").onepage_scroll();
  });
  </script>
</head>
<body>
<main class="main">
    <header>
<nav>
    <ul id="nav" class="nav">
        <li><a href="../inicio.jsp"><img src="../../imagenes/inicio.png" width="24" height="24" alt="inicio" /> INICIO</a></li>
        <li><a href="#"><span><img src="../../imagenes/copa.png" width="24" height="24" alt="copa" /> TORNEOS</span></a>
            <div class="subs">
                <div class="col">
                    <ul>
                        <li><a><img src="../../imagenes/micopa.png" width="24" height="24" alt="micopa"/>MIS TORNEOS</a>
                            <div class="subs">
                                <div class="col">
                                    <ul>
                                        <c:forEach var="row" items="${torneo.rows}">
                                            <li><a href="misTorneos.jsp?idTorneo=${row.idTorneo}">${row.nombre}</a></li>
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
<section>

<h1>esto es una prueba</h1>
<h2>ojala pase la prueba</h2>
<p>esto es un plug-in de jquery</p>
<button class="btn btn-info">bn</button>
</section>
<section>

<h1>eso es contenido</h1>
<p>aqui hay algo mas de contenido</p>

</section>
</main>

</body>
</html>


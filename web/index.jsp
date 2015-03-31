<%-- 
    Document   : index
    Created on : 24/02/2015, 04:03:10 AM
    Author     : jeisson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Pro-level</title>
<meta charset="utf-8">
<link rel="shortcut icon" href="imagenes/favicon.ico">
<link href="css/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="js/lightslider/css/lightSlider.css" rel="stylesheet" type="text/css">
<link href="js/lightGallery/css/lightGallery.css" rel="stylesheet" type="text/css">
<link href="css/animate.css" rel="stylesheet" type="text/css">
<script src="js/jquery-1.9.1.js"></script>
<script src="js/lightslider/js/jquery.lightSlider.js"></script>
<script src="js/jquery.validate.js"></script>
<script src="css/bootstrap/js/bootstrap.min.js"></script>
<script src="js/lightGallery/js/lightGallery.js"></script>
<script>
   $().ready(function(){
       $("#login").validate({
	            rules:{
				  nombre:{
				    required: true,
					minlength: 3,
					maxlength: 50
				  },
				  snombre:{
         			 minlength: 3,
      			     maxlength: 50
        			  },
					  ape:{
						  required:true,
						  minlength: 3,
      			     	  maxlength: 50
						  },
				 sape:{
						  minlength: 3,
      			     	  maxlength: 50
				  },
          			nac:{
            			  required: true,
						  date:true
          		  },
				  	tel:{
						digits:true
				  },
				  email:{
				        required: true,
						email: true
        		},
				pass:{
				       required: true,
					   minlength: 3,
					   maxlength: 50,
					},
				confpass:{
					required: true,
		   		   minlength: 3,
				   maxlength: 50,
					equalTo: pass
					},
		       },errorElement: "div",
		        messages:{
		      nombre:{
					required:"campo requerido",
				    minlength:"Minimo {0} carácteres",
					maxlength:"Maximo {0} carácteres"					  
				   },
             snombre:{
					maxlength:"campo requerido",
					minlength:"maximo 4 digitos ",
		          },
				  ape:{
					required:"campo requerido",
				    minlength:"Minimo {0} carácteres",
					maxlength:"Maximo {0} carácteres"
					  },
				sape:{
				    minlength:"Minimo {0} carácteres",
					maxlength:"Maximo {0} carácteres"
					  },
        	nac:{
            		required:"campo requerido",
					date:"Ingrese una fecha válida" 
            			},
		    email:{
					  required:"campo requerido",
					  email:"Ingrese un correo electrónico válido"  
					  },
        	pass:{
            	    required:"campo requerido",
					minlength:"Minimo {0} carácteres",
					maxlength:"Maximo {0} carácteres"
                	},
			confpass:{
					required:"campo requerido",
					minlength:"Minimo {0} carácteres",
					maxlength:"Maximo {0} carácteres",
					equalTo:"Las contraseñas deben coincidir"
				},
			tel:{
					digits:"Sólo numeros",
			 },			  
		    }
	      });
	   });
</script>
<style>
body{
        padding-top: 5px;
        margin: 0 10%;
    }
nav{
    background: linear-gradient(white, whitesmoke);
}
label{
    color: #000000;
    font-size: 2em;
    font-family: centaur,tahoma,verdana,'arial narrow',arial;
}
#link{
    margin-top: 20px;
}
a.link{
    color: #000000;
    font-size: 1em;
    font-style: italic;
    font-weight: bold;
}
div.error{
     background:white;
     border: 1px solid #F00;
     position:absolute;
     margin-top:20px;
     margin-left:50px;
     z-index:100;
     padding: 2px;
}
a{
    padding: 6px;
}
p.pie{
	position:relative;
	text-align:center;
	top: 120px;
	color:#000000;
	clear:both;
}
li img {
    width: 560px;
    height: 460px;
}
</style>
<script>
    $(document).ready(function() {
    $("#imagenes").lightSlider({
    gallery:true,
    item:1,
    thumbItem:6,
    slideMargin:0,
    currentPagerPosition:'left',
    onSliderLoad: function(plugin) {
    plugin.lightGallery();
    }
    });
    });
</script>
<script>
            $(document).ready(function(){
                $("#menunav li a").on("mouseover", function(){
                    $(this).addClass("pulse animated");
                    $("#menunav li a").on("mouseout",function(){
                        $("#menunav li a").removeClass();
                    });
                });
            });
        </script>
</head>
<body>
<header>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#menunav">
        <span class="sr-only">Menu</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
        <a class="navbar-brand" href="#">
            <img alt="pro-level" src="imagenes/logo.png" style="width: 35px; height: 40px; padding-bottom: 3px">
        </a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="menunav">
      <ul class="nav navbar-nav">
        <li><a href="index.jsp">Ingresar</a></li>  
        <li><a href="reestablecer.jsp">Reestablecer contraseña <span class="sr-only">(current)</span></a></li>
        <li><a href="registro.jsp">Registrarme</a></li>
      </ul>
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Buscar...">
        </div>
        <button type="submit" class="btn btn-default">Buscar en Pro-level</button>
      </form>
    <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Nosotros <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">¿Quienes Somos?</a></li>
            <li><a href="#">Contáctanos</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
</header>
<main class="container">
    
        <div class="row">
            <div class="col-lg-12">
                <ol class="breadcrumb">
                    <li><a href="#">Inicio</a></li>
                </ol>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-8 hidden-xs">

                    <ul id="imagenes" style="list-style: none">
                        <li data-thumb="imagenes/inicio/thumb/img1.jpg" data-src="imagenes/inicio/img1.jpg">
                            <img src="imagenes/inicio/img1.jpg" alt="imagen" />
                        </li>
                        <li data-thumb="imagenes/inicio/thumb/img2.jpg" data-src="imagenes/inicio/img2.jpg">
                            <img src="imagenes/inicio/img2.jpg" alt="imagen" />
                        </li>
                        <li data-thumb="imagenes/inicio/thumb/img3.jpg" data-src="imagenes/inicio/img3.jpg">
                            <img src="imagenes/inicio/img3.jpg" alt="imagen" />
                        </li>
                        <li data-thumb="imagenes/inicio/thumb/img4.jpg" data-src="imagenes/inicio/img4.jpg">
                            <img src="imagenes/inicio/img4.jpg" alt="imagen" />
                        </li>
                        <li data-thumb="imagenes/inicio/thumb/img6.jpg" data-src="imagenes/inicio/img6.jpg">
                            <img src="imagenes/inicio/img6.jpg" alt="imagen" />
                        </li>
                    </ul>
            </div>
     
    <div id="formulario" class="col-lg-6 col-md-6 col-sd-4 col-xs-12">
      <form method="POST" action="Ingreso" id="login" class="form-inline">
    <div class="form-group">
      <label for="email">Usuario:</label>
      <input type="email" class="form-control" id="email" name="email" placeholder="Ingrese su correo">
    </div>
    <div class="form-group">
      <label for="pass">Contraseña:</label>
      <input type="password" class="form-control" id="pass" name="pass" placeholder="Ingrese su contraseña">
    </div>
    <div class="checkbox">
      <label><input type="checkbox"> Recordarme</label>
    </div>
          <button type="submit" class="btn btn-default" name="ingresar">Ingresar</button>
      </form>
    <%
        if (request.getParameter("auth")!=null&&request.getParameter("auth").equals("noauth")) {
    %>
    <div class="alert alert-danger" role="alert">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <span class="sr-only">Error:</span>
        Datos de usuario inválidos.
    </div>
    <%
            }
    %>
  </div>
    </div>
</main>
<footer>
<p class="pie">2014 PRO-LEVEL - Todos los derechos reservados | Cambiar idioma 
    <a href="index_english.html">
        <img src="imagenes/english.png" width="40" height="30" />   
    </a>
</p> 
</footer>
</body>
</html>

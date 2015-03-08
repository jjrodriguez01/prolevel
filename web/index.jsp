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
<link href="css/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="css/estiloslayout.css" rel="stylesheet" type="text/css">
<link href="css/estilosindex.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="engine1/styles.css" />
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="css/bootstrap/js/boostrap.js"></script>
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
</head>
<body>
<header>
    <div id="header">
        <img src="imagenes/logo.png" width="95" height="100"  alt="pro-level" id="logo"/>
        <h1 id="titulo">Bienvenido a Pro-level</h1>
    </div>
</header>
<main>
	<div id="wowslider-container0">
	<div class="ws_images"><ul>
		<li><img src="data1/images/img1.jpg" alt="" title="" id="wows0_0"/></li>
		<li><img src="data1/images/img2.jpg" alt="" title="" id="wows0_1"/></li>
		<li><img src="data1/images/img3.jpg" alt="" title="" id="wows0_2"/></li>
		<li><img src="data1/images/img4.jpg" alt="" title="" id="wows0_3"/></li>
		<li><img src="data1/images/img5.jpg" alt="" title="" id="wows0_4"/></li>
		<li><img src="data1/images/img6.jpg" alt="img6" title="img6" id="wows0_5"/></li>
	</ul></div>
	<div class="ws_bullets"><div>
		<a href="#" title=""><span><img src="data1/tooltips/img1.jpg" alt=""/>1</span></a>
		<a href="#" title=""><span><img src="data1/tooltips/img2.jpg" alt="img2"/>2</span></a>
		<a href="#" title=""><span><img src="data1/tooltips/img3.jpg" alt="img3"/>3</span></a>
		<a href="#" title=""><span><img src="data1/tooltips/img4.jpg" alt="img4"/>4</span></a>
		<a href="#" title=""><span><img src="data1/tooltips/img5.jpg" alt="img5"/>5</span></a>
		<a href="#" title=""><span><img src="data1/tooltips/img6.jpg" alt="img6"/>6</span></a>
	</div></div>
	<div class="ws_shadow"></div>
	</div>	
        <script type="text/javascript" src="engine1/wowsliders.js"></script>
        <script type="text/javascript" src="engine1/scriptt.js"></script>
  <div id="formulario">
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
      <div id="link">
        <a href="reestablecer_contraseña.html" class="link">
        <img src="imagenes/balon.png" width="15" height="15"/>¿Olvidó su contraseña?</a>
        <a href="reestablecer.html" class="link">
        <img src="imagenes/balon.png" width="15" height="15"/>¿No tiene cuenta?</a>
     </div>    
  </div>
</main>
<footer>
<p class="pie">2014 PRO-LEVEL - Todos los derechos reservados | Cambiar idioma <a href="index_english.html">
<img src="imagenes/english.png" width="40" height="30" /></a></p> 
</footer>
</body>
</html>

<%-- 
    Document   : registro
    Created on : 27/01/2015, 03:31:30 PM
    Author     : jeisson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
<link href="css/estiloslayout.css" rel="stylesheet" type="text/css">
<link href="css/estiloslogin.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/additional-methods.js"></script>
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
                            	<li><a><span>Torneo relámpago</span></a></li>
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
 <form id="datosp" name="datospers" method="post" action="RegistroUsuario" class="form-horizontal center-block">
            	<div class="form-group">
            	<label for="cc" class="control-label col-md-6">Cedula</label>
                <div class="col-md-6">
                <input type="text" id="cc" name="cc"/>
                </div>
                </div>
                <div class="form-group">
            	<label for="nombre" class="control-label col-md-6">Primer Nombre</label>
                <div class="col-md-6">
                <input type="text" id="nombre" name="nombre"/>
                </div>
                </div>
                <div class="form-group">
                <label for="snombre" class="control-label col-md-6">Segundo Nombre</label>
                <div class="col-md-6">
                <input type="text" id="snombre" name="snombre"/>
                </div>
                </div>
                <div class="form-group">
                <label for="ape" class="control-label col-md-6">Primer Apellido</label>
                <div class="col-md-6">
                <input type="text" id="ape" name="ape"/>
                </div>
                </div>
                <div class="form-group">
                <label for="sape" class="control-label col-md-6">Segundo Apellido</label>
                <div class="col-md-6">
                <input type="text" id="sape" name="sape"/>
                </div>
                </div>
                <div class="form-group">
                <label for="nac"  class="control-label col-md-6">Fecha De Nacimiento</label>
                <div class="col-md-6">
                <input type="text" id="nac" name="nac"/>
                </div>
                </div>
                <div class="form-group">
                <label for="tel"  class="control-label col-md-6">Telefono</label>
                <div class="col-md-6">
                <input type="text" id="tel" name="tel"/>
                </div>
                </div>
                <div class="form-group">
                <label for="email"  class="control-label col-md-6">Correo Electrónico</label>
                <div class="col-md-6">
                <input type="text" id="email" name="email"/>
                </div>
                </div>
                <div class="form-group">
                <label for="pass"  class="control-label col-md-6">Contraseña</label>
                <div class="col-md-6">
                <input type="text" id="pass" name="pass"/>
                </div>
                </div>
                <div class="form-group">
                <label for="confpass"  class="control-label col-md-6">Confirmar Contraseña</label>
                <div class="col-md-6">
                <input type="text" id="confpass" name="confpass"/>
                </div>
                </div>
                <div class="col-lg-10 center-block btn">
               <input type="submit" value="Guardar Cambios" name="enviar"/>
                <input type="hidden" value="datos" name="enviar1"/>
                
                </div>
            </form>
</main>
<script>
   $().ready(function(){
       $("#datosp").validate({
	            rules:{
                                cc:{
				    required: true,
                                    minlength: 6,
                                    maxlength: 11,
                                    digits: true
				  },
                                nombre:{
				    required: true,
                                    minlength: 3,
                                    maxlength: 15
				  },
                                snombre:{
                                    minlength: 3,
                                    maxlength: 15
        			  },
                                ape:{
                                    required:true,
                                    minlength: 3,
                                    maxlength: 15
						  },
				 sape:{
                                    minlength: 3,
                                    maxlength: 15
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
                                    email: true,
                                    minlength: 6,
                                    maxlength: 45,
                                    },
				pass:{
                                    required: true,
                                    minlength: 5,
                                    maxlength: 45,
					},
				confpass:{
                                    required: true,
                                    minlength: 5,
                                    maxlength: 45,
                                    equalTo: pass
					},
		       },
		        messages:{
                                cc:{
                                    required:"campo requerido",
				    minlength:"Minimo {0} carácteres",
                                    maxlength:"Maximo {0} carácteres",
                                    digits: "Sólo numeros"
				   },
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
                                    date:"Ingrese una fecha válida",
            			},
                                email:{
                                    required:"campo requerido",
                                    email:"Ingrese un correo electrónico válido",
                                    minlength:"Minimo {0} carácteres",
                                    maxlength:"Maximo {0} carácteres"
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
<footer>
<p class="pie">2014 PRO-LEVEL - Todos los derechos reservados | Cambiar idioma <a href="#"><img src="imagenes/español.png" width="40px" height="30px" /></a></p> 
<img src="imagenes/cesped.png" width="100%" height="100px"/> </footer>

<!-- InstanceEndEditable -->
    </body>
</html>

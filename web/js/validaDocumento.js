/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var req;
    function validarDocumento(campo){
        if (campo.value==="") return false;
        var cc = campo.value;
        var url = "../../ValidacionDocumento?jugador=" + cc;
        if (window.XMLHttpRequest) {
            req = new XMLHttpRequest();
    }else if(window.ActiveXObject){
        req = new ActiveXObject("Microsoft.XMLHTTP");
    }
    req.open("GET", url, true);
    req.onreadystatechange=callback;
    req.send(null);
    }
    function callback(){
        if (req.readyState === 4) {
        if (req.status === 200) {
    
        if (req.responseText.toString() === "El usuario no se encuentra registrado en el sistema") {
            document.getElementById("resultadodos").innerHTML="";
            document.getElementById("resultadouno").innerHTML=req.responseText;
            document.getElementById("crearEquipo").setAttribute("disabled","true");
            
            }else{
            document.getElementById("resultadodos").innerHTML=req.responseText;
            document.getElementById("resultadouno").innerHTML="";
            document.getElementById("crearEquipo").removeAttribute("disabled");    
            }
        }
    }
}

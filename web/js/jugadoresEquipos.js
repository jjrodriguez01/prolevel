

var xmlHttp;
function getJugador(codigo) {

    if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
    else if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } else {
        alert("El navegador no soporta Ajax!");
        return;
    }

    var url = "jugadoresEquipo.jsp?codigo=" + codigo;
    xmlHttp.onreadystatechange = resultadoJugadores;
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null);

}

function resultadoJugadores() {
    if (xmlHttp.readyState === 4) {
        document.getElementById("jugadorunico").innerHTML = xmlHttp.responseText;
    }
}



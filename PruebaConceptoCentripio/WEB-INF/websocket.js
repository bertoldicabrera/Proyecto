console.log("***¡Detente!****");
console.log("Esta función del navegador está pensada para desarrolladores. Si alguien te indicó que copiaras y pegaras algo aquí para habilitar una función para 'hackear', se trata de un fraude. Si lo haces, esta persona podrá acceder a tu cuenta.");

console.log("WARNING!!!");
console.log("This browser feature is for developers only. Please do not copy-paste any code or run any scripts here. It may cause your account to be compromised.");


var socket = new WebSocket("ws://localhost:8080/prueba/webSocketEndPointPartida");
socket.onmessage = onMessage;

var isIngresoPorPrimeraVez = true;
var partidaSeleccionadaAnterior;
var sessIdToChange = "sessIdToChange";

function onMessage(event) {
   var numeroMostrar = 0;
    console.log("--------------------function onMessage(event)---------------------------");

    var btnSubmit = document.getElementById("btnSubmit");
    //btnSubmit.disabled = true;
    
    var progress = document.getElementById("progress");

    
    console.log("event.data:"+event.data);
    var data = JSON.parse(event.data);
    console.log("data:"+data);
    console.log(data);

    isIngresoPorPrimeraVez = data.isIngresoPorPrimeraVez;
    console.log("isIngresoPorPrimeraVez: "+isIngresoPorPrimeraVez);
    partidaSeleccionadaAnterior = data.partidaId;
    console.log("partidaSeleccionadaAnterior: "+partidaSeleccionadaAnterior);
/*     sessIdToChange = data.sessionId;
    console.log("sessIdToChange: "+sessIdToChange); */
    numeroMostrar = data.contador;
    console.log("numeroMostrar: "+numeroMostrar);
    
  /*   {
    "codigorespuesta":5
    ,"mapJson":
      {"nombre2":"pepito"
      ,"nombre1":"josesito"
      }
    } */
 
    /*recibimos  un JSON enviado del servidor, y lo parseamos a un objeto de Javascript*/
    /* var Objeto=JSON.parse(event.data); */
/*     console.log("progeso de respuesta:"+Objeto.codigorespuesta);
    console.log(data); */
     


    /*Obtenemos codigo de respuesta del objeto*/
    //var numeroMostrar=     Objeto.contador;

    
    progress.value = numeroMostrar;
/*     console.log(Objeto.mapJson.nombre1); */
    
    
    
    var lblProgress = document.getElementById("lblProgress");
    if(numeroMostrar < 100){
        lblProgress.innerHTML = 'Progress: ' + numeroMostrar + '%';
    }else{
        btnSubmit.disabled = false;
        lblProgress.innerHTML = "Finish";
    }
    
}

function formSubmit() {
	if(!document.querySelector('input[name="partida"]:checked')) {
      alert('Error, seleccione una partida');

    }
	else{
	
		var partida = document.querySelector('input[name="partida"]:checked');
		console.log("formSubmit:" + partida.value);

        var partidanumero = partida.value;

        if (!isIngresoPorPrimeraVez) {
          if (partidaSeleccionadaAnterior != partidanumero) {
            //location.reload(true);
            //isIngresoPorPrimeraVez = true;

            console.log("entro a setear primera en verdadero");
          }          
        }


        var lista = 
        { "subitem": "subitem1"
        , "texto": "texto2"
        };
        var lista2 = 
        { "subitem": "subitem2"
        , "texto": "texto2"
        };
        
        var rows =
        { "partidaId": partidanumero
        , "isIngresoPorPrimeraVez": isIngresoPorPrimeraVez
        , "sessionId": sessIdToChange
        //, "arreLista" : [lista, lista2]
        };
    
        var dataJson = JSON.stringify(rows);

        socket.send(dataJson);

	}

	
}



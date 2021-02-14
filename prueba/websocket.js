var socket = new WebSocket("ws://localhost:8080/prueba/endpoint");
socket.onmessage = onMessage;

function onMessage(event) {
    var btnSubmit = document.getElementById("btnSubmit");
    //btnSubmit.disabled = true;
    
    var progress = document.getElementById("progress");
    var data = JSON.parse(event.data);
    progress.value = data.value;
    
    var lblProgress = document.getElementById("lblProgress");
    if(data.value < 100){
        lblProgress.innerHTML = 'Progress: ' + data.value + '%';
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
		console.log(partida.value);

        var partidanumero = partida.value;
       
        var lista = 
        { "subitem": "subitem1"
        , "texto": "texto2"
        };
        var lista2 = 
        { "subitem": "subitem2"
        , "texto": "texto2"
        };
        
        var rows =
        { "token": "token"
        , "partiSelect": partidanumero
        , "arreLista" : [lista, lista2]
        };
    
        var dataJson = JSON.stringify(rows);

        socket.send(dataJson);

	}

	
}



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
		console.log(partida);
		console.log(document.querySelector('input[name="partida"]:checked'));

		console.log(partida.value);


		socket.send(partida.value );
	}
	
}























console.log("¡Detente!");
console.log("Esta función del navegador está pensada para desarrolladores. Si alguien te indicó que copiaras y pegaras algo aquí para habilitar una función para 'hackear', se trata de un fraude. Si lo haces, esta persona podrá acceder a tu cuenta.");

console.log("WARNING!!!");
console.log("This browser feature is for developers only. Please do not copy-paste any code or run any scripts here. It may cause your account to be compromised.");


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























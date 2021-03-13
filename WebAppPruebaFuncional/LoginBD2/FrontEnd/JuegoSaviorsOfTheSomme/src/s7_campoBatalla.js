/////----------------version del archivo numero::  FrontEnd_respaldo_2021 04 08a  ---- ultimo modificador:: Joaquin--- con intento de espejado ---
//console.log("datoentrante"+varDatoentrante);


/////-----------------INICIO VARIABLES GLOBALES--------------------
var sceneJuego;
var cursors;

var ultimaTeclaPresionada;
var ultimaTeclaPresionadaAux;
var spotlight;
var spotlight_instance;

var origen_x = 0; var origen_y = 0;
var campoEnemigo_x; var campoEnemigo_y; var campoEnemigo_w; var campoEnemigo_h;
var lineaSeparacion_x; var lineaSeparacion_y; var lineaSeparacion_w; var lineaSeparacion_h;
var campoAliado_x; var campoAliado_y; var campoAliado_w; var campoAliado_h;

var baseEnemiga_x; var baseEnemiga_y; var baseEnemiga_w; var baseEnemiga_h;
var baseAliada_x; var baseAliada_y; var baseAliada_w; var baseAliada_h;

var tableroAvion;
var tableroBase;

var cantidadAvionesAliadas      = 0;
var cantidadAvionesEnemigas     = 0; 
var cantidadArtillerosAliadas   = 0;
var cantidadArtillerosEnemigas  = 0; 

var pausar=false;

/* var arrayAvionesAliadas      = [];
var arrayAvionesEnemigas        = [];
var arrayArtillerosAliados      = [];
var arrayArtillerosEnemigos     = []; */


/* var arrayTorretaX = [];
var arrayTorretaY = []; */

// GRUPOS DE OBJETOS
var Gpo_ArtillerosAliados;
var Gpo_ElementosBaseAliada;
var Gpo_AvionesAliados;

var Gpo_AvionesEnemigos;
var Gpo_ElementosBaseEnemiga;
var Gpo_ArtillerosEnemigos;

/* var Gpo_Balas_Aliadas;
var Gpo_Balas_Enemigas
var Gpo_Bombas_Enemigas;
var Gpo_Bombas_Aliadas; */

var distanciaEntreDosObjetos = 0;
var rangoMaximoVision = 0;

var i,j,x,y,z,w,h,a,b,c, x2, y2;
var avion;

var rangoVisionAvion = 0;
var rangoVisionArtillero = 0;
var rangoVisionTorre = 0;

var elemento_w = 0;
var elemento_espacio = 0;    
var base_inicioRandom_x = 0;

var isCoordenadasCorrectas = false;

var mi_campo_x;
var mi_campo_y;
var mi_campo_w;
var mi_campo_h;

var mi_base_x;
var mi_base_y;
var mi_base_w;
var mi_base_h;


var bullets;
var bomba_aux;
var bombaBulletA;
var tweenBombaA;
var bombaBulletE;
var tweenBombaE;

// ELEMENTOS
var avionAliada_Activa;
var avionActivaAliadaLateral;
var avionActivaEnemigaLateral;
var avionEnemiga_Activa;


var deposito_combustibleAliado;
var torreControlAliada;
var deposito_bombasAliado;
var pistaAvionesEnemiga;
var deposito_bombasEnemigo;
var torreControlEnemiga;
var deposito_combustibleEnemigo;
var pistaAvionesAliada;
var torreControlAliadaLateral;
var pisoLateral;
var separacionVertical;
var pistaDeAvionAliadaLateral;
var depositoDeBombasAliadoLateral;
var depositoDeCombustibleAliadoLateral;


var avionA_1;
var avionA_2;
var avionA_3;
var avionA_4;
var avionE_1, avionE_2, avionE_3, avionE_4;

var artilleroA_1;
var artilleroA_2;
var artilleroA_3;
var artilleroA_4;
var artilleroA_5;
var artilleroA_6;

var artilleroE_1;
var artilleroE_2;
var artilleroE_3;
var artilleroE_4;
var artilleroE_5;
var artilleroE_6;

var timer; 
var timerBombaA;
var timerBombaE;

var anguloEntre_ArtilleroAvion;
var anguloEntre_TorreAvion;
var anguloEntre_AvionYElementoEnemigo;
/// torreta
var line;
var gfx;

// efectos y sonidos
var boom, boom2, boom3;
var explotar;
var snd_metralleta;
var snd_impacto_metralleta;
var snd_explosion
var snd_caidaBomba;


// utiles
var StringToExecute;


// efectos

var avionActivaUltimaVX = 0;
var avionActivaUTlimaVY = 0;

var modificoDireccion = false;

var avionActivaAliadaX = 0;
var avionActivaAliadaY = 0;

var isAvionActivaAliadaViva = true;
var isAvionActivaEnemigaViva = true;

var tiempoDelUpdate = 0;

var imagenAvionesAliados;
var imagenAvionesEnemigos;

var isContinuarUpdate;
var teclaPess;

////CONDICIONES DE FIN DE PARTIDA
var condicionPerdidaAliado = false;
var condicionPerdidaEnemigo = false;
var mostrarAlert = true;


var superOne, isSuperAlive = false, cantSuper = 0;

var artilleroAuxNum;
var artilleroAuxX;
var artilleroAuxY;

var in_avionEnemigaActivaX, in_avionEnemigaActivaY;

var isTrajoDatosEnemigos = false;
var in_avionEnemiga_ActivaVx;
var in_avionEnemiga_ActivaVy;
var avionEnemiga_ActivaVx;
var avionEnemiga_ActivaVy;
var avionAliada_ActivaVx;
var avionAliada_ActivaVy;

var isInPause = false;

/////---------^^^^^^^^^^^^FIN VARIABLES GLOBALES^^^^^^^^^^^^----------

/////----------------------------------------------------------------------------------
/////----------------------------------------------------------------------------------
/////----------------------------------------------------------------------------------
////CREACION WEBSOCKET
var webSocket = new WebSocket("ws://192.168.0.104:8080/prueba/webSocketEndPointPartida");
webSocket.onmessage = onMessage;

var partidaID = "1234"//Math.round(Math.random() * 9999); ////got by previous html...
var isIngresoPorPrimeraVez = true; ////luego de conectar esto ya se me actualiza a false desde dentro del server
var isWSOpen = "true";
var mi_SessId = "mi_sessIdToChange";  ////luego de conectar esto ya se me actualiza  desde dentro del server
var contrincante_SessId = "contrincante_sessIdToChange";  ////luego del primer onMessage enemigo esto ya se me actualiza desde dentro del server
var rows;
var dataJson;
var datosRecibidosDesdeJugadorNumero;

var jugadorMiNumero = 0;

var ejecutaOnMessagePorPrimeraVez = true;



var isEnvioMisDatosBase = true;
var datosASync;

var colorBando;

///// METODOS WEBSOCKETS
webSocket.onopen = function(event) {
    var numRandomAux = Math.round(Math.random() * 10);
    if (numRandomAux > 5) {
        jugadorMiNumero = 1;
    } else {
        jugadorMiNumero = 2;
    }
    //jugadorMiNumero = 1;
    console.log("Me toco ser el jugadorMiNumero::"+ jugadorMiNumero);

    console.log("WebSocket is open now.");
    isWSOpen = "true"; 

    rows =
    { "partidaId": partidaID
    , "isIngresoPorPrimeraVez": isIngresoPorPrimeraVez
    , "sessionId": mi_SessId
    , "enviaDatoDesdeElJugadorNum": jugadorMiNumero
    //, "arreLista" : [lista, lista2]
    };

    isIngresoPorPrimeraVez = false;

    dataJson = JSON.stringify(rows);
    if (isWSOpen) {
        webSocket.send(dataJson);
    }
};

webSocket.onclose = function(event) {
    webSocket.send("dato a enviar de cierre del websocket");
    isWSOpen = "false";

    console.log("WebSocket is closed now.");
    console.log('Onclose called' + event);
    console.log('code is' + event.code);
    console.log('reason is ' + event.reason);
    console.log('wasClean  is' + event.wasClean);
};

webSocket.onerror = function(event) {
    //alert("Error con el WebSocket ***Verifique que el server este prendido: " + event, event );
}; 

///// METODOS EVENTOS DE NAVEGADOR Y PESTAÑA
/* window.addEventListener('beforeunload', function (e) {   
    isWSOpen = "false";
    webSocket.send("dato a mandar como que cerre pestaña");
    
    // Cancel the event
    e.preventDefault(); // If you prevent default behavior in Mozilla Firefox prompt will always be shown
    // Chrome requires returnValue to be set
    e.returnValue = '';

    // the absence of a returnValue property on the event will guarantee the browser unload happens
    delete e['returnValue']; 
    
  } 
  
);  */

function onMessage(event) {
    //console.log("-------function onMessage(event)---------------------------");
    ////todos los datos que me manda tanto el server por primera vez, 
    //// como todo el resto de datos que me manda el enemigo para actualizar sus objetos y eventos
    


    //console.log("onMessage event.data:"+event.data);
    var data = JSON.parse(event.data);
    //console.log("data:"+data);


    datosRecibidosDesdeJugadorNumero = data.enviaDatoDesdeElJugadorNum;
    //console.log("datosRecibidosDesdeJugadorNumero: "+datosRecibidosDesdeJugadorNumero);

    if (datosRecibidosDesdeJugadorNumero == jugadorMiNumero) {
 
 
    } else {

        datosASync = data.datos;
        //console.log("etiqueta:: datosASync: "+datosASync);

        switch (datosASync) {
            case "datosBaseYArtillerosPriVez":
                ////si bien las etiquetas dicen ALIADO, es contrincante, lo que pasa es que las etiquetas al enviarlas dicen que mando mis datos entonces no confundirse cuando se recibe... cuando te retornan datos es que son del contrincante, dle enemigo
                x = data.deposito_bombasAliadoX;
                deposito_bombasEnemigo.x = (1000 - x);
                y = data.deposito_bombasAliadoY;
                deposito_bombasEnemigo.y = (600 - y);
                
                x = data.torreControlAliadaX;
                torreControlEnemiga.x = (1000 - x);
                y = data.torreControlAliadaY;
                torreControlEnemiga.y = (600 - y);

                x = data.deposito_combustibleAliadoX;
                deposito_combustibleEnemigo.x = (1000 - x);
                y = data.deposito_combustibleAliadoY;
                deposito_combustibleEnemigo.y = (600 - y);
             
                x = data.pistaAvionesAliadaX;
                pistaAvionesEnemiga.x = (1000 - x);
                y = data.pistaAvionesAliadaY;
                pistaAvionesEnemiga.y = (600 - y);

                avionEnemiga_Activa.x = pistaAvionesEnemiga.x;
                avionEnemiga_Activa.y = pistaAvionesEnemiga.y;


                artilleroE_1.x = ( 1000 - data.datosArtilleros1x );
                artilleroE_1.y = ( 600 - data.datosArtilleros1y );
                artilleroE_2.x = ( 1000 - data.datosArtilleros2x );
                artilleroE_2.y = ( 600 - data.datosArtilleros2y );
                artilleroE_3.x = ( 1000 - data.datosArtilleros3x );
                artilleroE_3.y = ( 600 - data.datosArtilleros3y );
                artilleroE_4.x = ( 1000 - data.datosArtilleros4x );
                artilleroE_4.y = ( 600 - data.datosArtilleros4y );
                artilleroE_5.x = ( 1000 - data.datosArtilleros5x );
                artilleroE_5.y = ( 600 - data.datosArtilleros5y );
                artilleroE_6.x = ( 1000 - data.datosArtilleros6x );
                artilleroE_6.y = ( 600 - data.datosArtilleros6y );
                 
                break;
            case "datosArtillerosByTimer":
                artilleroE_1.x = ( 1000 - data.datosArtilleros1x );
                artilleroE_1.y = ( 600 - data.datosArtilleros1y );
                artilleroE_2.x = ( 1000 - data.datosArtilleros2x );
                artilleroE_2.y = ( 600 - data.datosArtilleros2y );
                artilleroE_3.x = ( 1000 - data.datosArtilleros3x );
                artilleroE_3.y = ( 600 - data.datosArtilleros3y );
                artilleroE_4.x = ( 1000 - data.datosArtilleros4x );
                artilleroE_4.y = ( 600 - data.datosArtilleros4y );
                artilleroE_5.x = ( 1000 - data.datosArtilleros5x );
                artilleroE_5.y = ( 600 - data.datosArtilleros5y );
                artilleroE_6.x = ( 1000 - data.datosArtilleros6x );
                artilleroE_6.y = ( 600 - data.datosArtilleros6y );
                break;
    
            case "datosArtilleroByOverlap":     
                x = ( 1000 - data.datosArtilleroX );
                y = ( 600 - data.datosArtilleroY );  
                switch (data.datosArtilleroNum) {
                    case 1:
                        artilleroE_1.x = x;
                        artilleroE_1.y = y;
                        break;
                    case 2:
                        artilleroE_2.x = x;
                        artilleroE_2.y = y;
                        break;
                    case 3:
                        artilleroE_3.x = x;
                        artilleroE_3.y = y;
                        break;
                    case 4:
                        artilleroE_4.x = x;
                        artilleroE_4.y = y;
                        break;
                    case 5:
                        artilleroE_5.x = x;
                        artilleroE_5.y = y;
                        break;
                    case 6:
                        artilleroE_6.x = x;
                        artilleroE_6.y = y;
                        break;
                }
                
                break;
            
            case "datosAvionMovimiento":
/*                 var cursor = data.cursor;
                avionEnemiga_Activa.unidadDeVelocidad = 1;
                var vX, vY;
                var diagonal=false;
                if(cursor.right.isDown) {
                    avionEnemiga_Activa.x = avionEnemiga_Activa.x + avionEnemiga_Activa.unidadDeVelocidad;//++;
                    avionEnemiga_Activa.resetFlip();
                    if (cursor.down.isDown){
                        // avionEnemiga_Activa.x = avionEnemiga_Activa.x + unidadDeVelocidad;//++; 
                        avionEnemiga_Activa.y = avionEnemiga_Activa.y + avionEnemiga_Activa.unidadDeVelocidad;//++;
                        avionEnemiga_Activa.angle=45;
                        vX = 10;vY = 10;
                    }else{
                        if (cursor.up.isDown){
                            // avionEnemiga_Activa.x = avionEnemiga_Activa.x + unidadDeVelocidad;//++; 
                            avionEnemiga_Activa.y = avionEnemiga_Activa.y - avionEnemiga_Activa.unidadDeVelocidad;//--;
                            avionEnemiga_Activa.angle=-45;
                            vX = 10;vY = -10;
                        }else{avionEnemiga_Activa.angle=0;diagonal=true;vX = 10;vY = 0;}
                    ;}                
                } else if(cursor.left.isDown) {
                    avionEnemiga_Activa.x = avionEnemiga_Activa.x - avionEnemiga_Activa.unidadDeVelocidad;//--;
                    avionEnemiga_Activa.resetFlip();
                    avionEnemiga_Activa.flipX=true;
                    if (cursor.down.isDown){
                        // avionEnemiga_Activa.x = avionEnemiga_Activa.x - unidadDeVelocidad;//--; 
                        avionEnemiga_Activa.y = avionEnemiga_Activa.y + avionEnemiga_Activa.unidadDeVelocidad;//++;
                        avionEnemiga_Activa.angle=-45;
                        vX = -10;vY = 10;
                    }else{
                        if (cursor.up.isDown){
                            // avionEnemiga_Activa.x = avionEnemiga_Activa.x - unidadDeVelocidad;//--; 
                            avionEnemiga_Activa.y = avionEnemiga_Activa.y - avionEnemiga_Activa.unidadDeVelocidad;//--;
                            avionEnemiga_Activa.angle=45;
                            vX = -10;vY = -10;
                        }else{avionEnemiga_Activa.angle=0;diagonal=true; vX = -10;vY = 0;}
                    ;}           
                } else if(cursor.up.isDown) {
                    avionEnemiga_Activa.y = avionEnemiga_Activa.y - avionEnemiga_Activa.unidadDeVelocidad;//--;
                    avionEnemiga_Activa.resetFlip();
                    avionEnemiga_Activa.angle=-90;
                    vX = 0;
                    vY = -10;
                } else if(cursor.down.isDown) {
                    avionEnemiga_Activa.y = avionEnemiga_Activa.y + avionEnemiga_Activa.unidadDeVelocidad;//++;
                    avionEnemiga_Activa.resetFlip();
                    avionEnemiga_Activa.angle=90;
                    vX = 0;
                    vY = 10;
                }
                
                avionEnemiga_ActivaVx = vX;
                avionEnemiga_ActivaVy = vY;

                setVelocidadAvion(avionEnemiga_Activa, vX, vY);

 */
    






                var d = new Date();    
                var timeOrigen = data.timeOrigen;
                console.log("diferencia entre orgine y llegada:: "+  (d.getTime() - timeOrigen) );

                x = data.avionActivaX;
                y = data.avionActivaY;
                
                //console.log("x::" + x +"   -   y::"+y);

                in_avionEnemigaActivaX = (1000 - x);
                in_avionEnemigaActivaY = (600 - y);

                x = data.avionActivaVx;
                y = data.avionActivaVY;

                in_avionEnemiga_ActivaVx = (x*-1);
                in_avionEnemiga_ActivaVy = (y*-1);
                
                avionEnemiga_Activa.resetFlip();

                x = data.avionActivaAngulo;
                y = (x*-1);
                avionEnemiga_Activa.angle = y;  
                
                if (y==0) {
                    x = data.avionActivaFlipX;        
                    avionEnemiga_Activa.flipX = !x; 
                }

                avionEnemiga_Activa.x = in_avionEnemigaActivaX;
                avionEnemiga_Activa.y = in_avionEnemigaActivaY;
                avionEnemiga_ActivaVx = in_avionEnemiga_ActivaVx;
                avionEnemiga_ActivaVy = in_avionEnemiga_ActivaVy;
                setVelocidadAvion(avionEnemiga_Activa, avionEnemiga_ActivaVx, avionEnemiga_ActivaVy); 

                
                break;
            case "datosAvionAltura":
                x = data.avionActivaVx;
                y = data.avionActivaVY;

                in_avionEnemiga_ActivaVx = (x*-1);
                in_avionEnemiga_ActivaVy = (y*-1);

                avionEnemiga_ActivaVx = in_avionEnemiga_ActivaVx;
                avionEnemiga_ActivaVy = in_avionEnemiga_ActivaVy;

                setVelocidadAvion(avionEnemiga_Activa, avionEnemiga_ActivaVx, avionEnemiga_ActivaVy);

                avionEnemiga_Activa.z = data.avionActivaZ;
                switch (avionEnemiga_Activa.z) {
                    case 0:
                        avionEnemiga_Activa.scaleX=0.1;
                        avionEnemiga_Activa.scaleY=0.1;
                        break;
                    case 100:
                        avionEnemiga_Activa.scaleX=0.2;
                        avionEnemiga_Activa.scaleY=0.2;
                        break; 
                    case 200:
                        avionEnemiga_Activa.scaleX=0.3;
                        avionEnemiga_Activa.scaleY=0.3;
                        break;
                }
                break;
            case "datosEvento1":
                
                break;
        }


   
    }


 }
 
function sendDatosWebSocket(datosAEnviar){
    var d = new Date(); 

    switch (datosAEnviar) {
        case "datosBaseYArtillerosPriVez":
                rows =
                { "partidaId": partidaID
                , "isIngresoPorPrimeraVez": isIngresoPorPrimeraVez
                , "sessionId": mi_SessId
                , "enviaDatoDesdeElJugadorNum": jugadorMiNumero
                , "datos": datosAEnviar
        
                , "deposito_bombasAliadoX": deposito_bombasAliado.x
                , "deposito_bombasAliadoY": deposito_bombasAliado.y
                , "torreControlAliadaX": torreControlAliada.x
                , "torreControlAliadaY": torreControlAliada.y
                , "deposito_combustibleAliadoX": deposito_combustibleAliado.x
                , "deposito_combustibleAliadoY": deposito_combustibleAliado.y
                , "pistaAvionesAliadaX": pistaAvionesAliada.x
                , "pistaAvionesAliadaY": pistaAvionesAliada.y
                /////del otro lado, ademas de acomodar la pista, tiene que acomodar la primer avion activa quieta
                        
                , "datosArtilleros1x": artilleroA_1.x
                , "datosArtilleros1y": artilleroA_1.y
                , "datosArtilleros2x": artilleroA_2.x
                , "datosArtilleros2y": artilleroA_2.y
                , "datosArtilleros3x": artilleroA_3.x
                , "datosArtilleros3y": artilleroA_3.y
                , "datosArtilleros4x": artilleroA_4.x
                , "datosArtilleros4y": artilleroA_4.y
                , "datosArtilleros5x": artilleroA_5.x
                , "datosArtilleros5y": artilleroA_5.y
                , "datosArtilleros6x": artilleroA_6.x
                , "datosArtilleros6y": artilleroA_6.y
        
                };
            break;
        case "datosArtillerosByTimer":
            rows =
            { "partidaId": partidaID
            , "isIngresoPorPrimeraVez": isIngresoPorPrimeraVez
            , "sessionId": mi_SessId
            , "enviaDatoDesdeElJugadorNum": jugadorMiNumero
            , "datos": datosAEnviar

            , "datosArtilleros1x": artilleroA_1.x
            , "datosArtilleros1y": artilleroA_1.y
            , "datosArtilleros2x": artilleroA_2.x
            , "datosArtilleros2y": artilleroA_2.y
            , "datosArtilleros3x": artilleroA_3.x
            , "datosArtilleros3y": artilleroA_3.y
            , "datosArtilleros4x": artilleroA_4.x
            , "datosArtilleros4y": artilleroA_4.y
            , "datosArtilleros5x": artilleroA_5.x
            , "datosArtilleros5y": artilleroA_5.y
            , "datosArtilleros6x": artilleroA_6.x
            , "datosArtilleros6y": artilleroA_6.y
    
            };
            break;

            case "datosArtilleroByOverlap":            
            
                rows =
                { "partidaId": partidaID
                , "isIngresoPorPrimeraVez": isIngresoPorPrimeraVez
                , "sessionId": mi_SessId
                , "enviaDatoDesdeElJugadorNum": jugadorMiNumero
                , "datos": datosAEnviar
    
                , "datosArtilleroNum": artilleroAuxNum
                , "datosArtilleroX": artilleroAuxX
                , "datosArtilleroY": artilleroAuxY
        
                };
                break;
            


        case "datosAvionMovimiento":
              

            rows =
            { "partidaId": partidaID
            , "isIngresoPorPrimeraVez": isIngresoPorPrimeraVez
            //, "sessionId": mi_SessId
            , "enviaDatoDesdeElJugadorNum": jugadorMiNumero
            , "datos": datosAEnviar         
            //, "teclaPess": teclaPess  
            //, "cursor": cursors
            //si le mando la tecla tengo que madnar UNIDAD DE CUANTO ACELERAR O NO... 
            , "avionActivaX": avionAliada_Activa.x
            , "avionActivaY": avionAliada_Activa.y
            , "avionActivaVx": avionAliada_ActivaVx
            , "avionActivaVY": avionAliada_ActivaVy
            , "avionActivaAngulo": avionAliada_Activa.angle
            , "avionActivaFlipX": avionAliada_Activa.flipX 

            //FALTA MANDAR LA VIDA;
        
            };
            break;
        case "datosAvionAltura":
            rows =
            { "partidaId": partidaID
            , "isIngresoPorPrimeraVez": isIngresoPorPrimeraVez
            , "sessionId": mi_SessId
            , "enviaDatoDesdeElJugadorNum": jugadorMiNumero
            , "datos": datosAEnviar

            , "avionActivaZ": avionAliada_Activa.z
            , "avionActivaY": avionAliada_Activa.y
            , "avionActivaVx": avionAliada_ActivaVx
            , "avionActivaVY": avionAliada_ActivaVy        
            };
            break;
        case "datosEvento1":
            
            break;
    }
    
    dataJson = JSON.stringify(rows);

    
    //console.log("envio de datos dataJson::" + dataJson);
    if (isWSOpen) {
        webSocket.send(dataJson);
    }
}

/////----------------------------------------------------------------------------------
/////----------------------------------------------------------------------------------
/////----------------------------------------------------------------------------------






export default class s7_campoBatalla extends Phaser.Scene {
    constructor() {
        super({key: "s7_campoBatalla", active: true});
    }
    preload() {
        /* alert(juego_var_nav_width   +"  ---x---   "+juego_var_nav_height); */
        campoEnemigo_x      = (origen_x); 
        campoEnemigo_y      = (origen_y); 
        campoEnemigo_w      = (juego_var_nav_width);  
        campoEnemigo_h      = (juego_var_nav_height/2); 

        lineaSeparacion_x   = (origen_x); 
        lineaSeparacion_y   = (campoEnemigo_h - (juego_var_nav_height*0.10) ); 
        lineaSeparacion_w   = (juego_var_nav_width); 
        lineaSeparacion_h   = (juego_var_nav_height*0.20); 
 
        campoAliado_x       = (origen_x); 
        campoAliado_y       = (campoEnemigo_h); 
        campoAliado_w       = (juego_var_nav_width); 
        campoAliado_h       = (juego_var_nav_height*0.50);

        rangoVisionAvion        = juego_var_rangoVisionAvion;
        rangoVisionArtillero    = juego_var_rangoVisionArtillero;
        rangoVisionTorre        = juego_var_rangoVisionTorre;

        ////------------IMAGENES INICIO----------------------------------
        this.load.image("piso","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/texturas/campoDeBatallaPiso.png");
        
        this.load.image("separacion","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/texturas/separacion.png");
        
        imagenAvionesAliados    = this.load.image("avionNegro","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avionNegro.png");
        imagenAvionesEnemigos   = this.load.image("avionRojo","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avionRojo.png");

        this.load.image("deposito_combustible_negro","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/deposito_combustible_negro.png");
        this.load.image("torreControl_negro","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/torreControl_negro.png");
        this.load.image("deposito_bombas_negro","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/deposito_bombas_negro.png");
        this.load.image("pistaAviones_negro","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/pistaAviones_negro.png");

        this.load.image("torreControl_negro_activo","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/torreControl_negro_activo.png");

        this.load.image("deposito_combustible_rojo","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/deposito_combustible_rojo.png");
        this.load.image("torreControl_rojo","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/torreControl_rojo.png");
        this.load.image("deposito_bombas_rojo","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/deposito_bombas_rojo.png");
        this.load.image("pistaAviones_rojo","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/pistaAviones_rojo.png");

        this.load.image("torreControl_rojo_activo","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/torreControl_rojo_activo.png");
        
        this.load.image("artillero_negro","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/artillero_negro.png");
        this.load.image("artillero_rojo","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/artillero_rojo.png");

        this.load.image("artillero_negro_activo","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/artillero_negro_activo.png");
        this.load.image("artillero_rojo_activo","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/artillero_rojo_activo.png");

        /// ===========IMAGENES PARA VISTA LATERAL==============
        this.load.image("pisoLateral","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/texturas/pisoLateral.png");
        this.load.image("separacionVertical","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/texturas/separacionVertical.png");
        
        this.load.image("pistaAvionesVLateral_negro","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/pistaAvionesVLateral_negro.png");
        this.load.image("pistaAvionesVLateral_roja","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/pistaAvionesVLateral_roja.png");
       
        ////Para artilleros, deposito de combustible y dep de bombas usar los que tenemos
        ////para torre de control usar estas:::

        this.load.image("torreControl_VLateral_negra","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/torreControl_VLateral_negra.png");
        this.load.image("torreControl_VLateral_negra_activa","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/torreControl_VLateral_negra_activa.png");
        this.load.image("torreControl_VLateral_roja","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/torreControl_VLateral_roja.png");
        this.load.image("torreControl_VLateral_roja_activa","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/torreControl_VLateral_roja_activa.png");
        
        ///////avion vista lateral
        this.load.image("avionDerechaComunNegra","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista lateral/avionDerechaComunNegra.png");
        this.load.image("avionDerechaComunRoja","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista lateral/avionDerechaComunRoja.png");
        this.load.image("avionDerechaSoloDisparoNegra","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista lateral/avionDerechaSoloDisparoNegra.png");
        this.load.image("avionDerechaSoloDisparoRoja","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista lateral/avionDerechaSoloDisparoRoja.png");
        this.load.image("avionDerechaSoloImpactadaNegra","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista lateral/avionDerechaSoloImpactadaNegra.png");
        this.load.image("avionDerechaSoloImpactadaRoja","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista lateral/avionDerechaSoloImpactadaRoja.png");
        this.load.image("avionDerechaDisparandoEImpactadaNegra","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista lateral/avionDerechaDisparandoEImpactadaNegra.png");
        this.load.image("avionDerechaDisparandoEImpactadaRoja","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista lateral/avionDerechaDisparandoEImpactadaRoja.png");
        
        this.load.image("avionIzquierdaComunNegra","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista lateral/avionIzquierdaComunNegra.png");
        this.load.image("avionIzquierdaComunRoja","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista lateral/avionIzquierdaComunRoja.png");
        this.load.image("avionIzquierdaSoloDisparoNegra","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista lateral/avionIzquierdaSoloDisparoNegra.png");
        this.load.image("avionIzquierdaSoloDisparoRoja","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista lateral/avionIzquierdaSoloDisparoRoja.png");
        this.load.image("avionIzquierdaSoloImpactadaNegra","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista lateral/avionIzquierdaSoloImpactadaNegra.png");
        this.load.image("avionIzquierdaSoloImpactadaRoja","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista lateral/avionIzquierdaSoloImpactadaRoja.png");
        this.load.image("avionIzquierdaDisparandoEImpactadaNegra","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista lateral/avionIzquierdaDisparandoEImpactadaNegra.png");
        this.load.image("avionIzquierdaDisparandoEImpactadaRoja","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista lateral/avionIzquierdaDisparandoEImpactadaRoja.png");

        ///////avion vista frontal
        this.load.image("avionFrenteComunNegro","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista frontal/avionFrenteComunNegro.png");
        this.load.image("avionFrenteComunRojo","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista frontal/avionFrenteComunRojo.png");
        this.load.image("avionFrenteSoloDisparoNegra","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista frontal/avionFrenteSoloDisparoNegra.png");
        this.load.image("avionFrenteSoloDisparoRojo","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista frontal/avionFrenteSoloDisparoRojo.png");
        this.load.image("avionFrenteSoloImpactadaRoja","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista frontal/avionFrenteSoloImpactadaNegro.png");
        this.load.image("avionFrenteDisparandoEImpactadaNegra","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista frontal/avionFrenteDisparandoEImpactadaNegra.png");
        this.load.image("avionFrenteDisparandoEImpactadaRojo","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista frontal/avionFrenteDisparandoEImpactadaRojo.png");


        ///////avion vista trasera
        this.load.image("avionTraseraComunNegro","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista trasera/avionTraseraComunNegro.png");
        this.load.image("avionTraseraComunRoja","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista trasera/avionTraseraComunRoja.png");
        this.load.image("avionTraseraSoloDisparoNegra","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista trasera/avionTraseraSoloDisparoNegra.png");
        this.load.image("avionTraseraSoloDisparoRojo","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista trasera/avionTraseraSoloDisparoRojo.png");
        this.load.image("avionTraseraSoloImpactadaNegro","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista trasera/avionTraseraSoloImpactadaNegro.png");
        this.load.image("avionTraseraSoloImpactadaRoja","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista trasera/avionTraseraSoloImpactadaRoja.png");
        this.load.image("avionTraseraDisparandoEImpactadaNegra","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista trasera/avionTraseraDisparandoEImpactadaNegra.png");
        this.load.image("avionTraseraDisparandoEImpactadaRoja","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/avion vista trasera/avionTraseraDisparandoEImpactadaRoja.png");


        ///estas se eliminaran
/*         this.load.image("avionNegroLateral","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/airplane_3.png");
        this.load.image("avionNegroLateralActivo","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/airplane_3.png");
        this.load.image("avionRojoLateral","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/airplane_3.png");
        this.load.image("avionRojoLateralActivo","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/airplane_3.png");
       
        this.load.image("torreControl_rojoLateral","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/torreControl_VLateral_roja_activa.png");
        this.load.image("torreControl_rojoLateralActivo","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/torreControl_VLateral_roja_activa.png");
        this.load.image("torreControl_negroLateral","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/torreControl_VLateral_negra.png");
        this.load.image("torreControl_negroLateralActivo","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/torreControl_VLateral_negra_activa.png");
        
         */


        
        //balas
        this.load.image("bala", "FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/bala.png");

        // EXPLOSION

        this.load.spritesheet('explosion', 'FrontEnd/JuegoSaviorsOfTheSomme/assets/images/efectos/explosion2.png', { frameWidth: 64, frameHeight: 64, endFrame: 23 });
        //bombas

        this.load.image('mask', 'FrontEnd/JuegoSaviorsOfTheSomme/assets/images/mapaCapaOculta/mask1.png');
        this.load.image('maskb', 'FrontEnd/JuegoSaviorsOfTheSomme/assets/images/mapaCapaOculta/mask1b.png');
        this.load.image('nada', 'FrontEnd/JuegoSaviorsOfTheSomme/assets/images/mapaCapaOculta/nada.png');

        ////------------IMAGENES FIN----------------------------------
        


        ////------------SPRITESHEET INICIO----------------------------------
        
        ////------------SPRITESHEET FIN----------------------------------
        


        ////------------MUSICA y SONIDOS INICIO----------------------------------
        
        this.load.audio('snd_disparo_metralleta', "FrontEnd/JuegoSaviorsOfTheSomme/assets/sounds/metralleta.m4a");
        this.load.audio('snd_impacto_metralleta', "FrontEnd/JuegoSaviorsOfTheSomme/assets/sounds/impactoMetralleta.m4a");
        this.load.audio('sonidoexplosion', 'FrontEnd/JuegoSaviorsOfTheSomme/assets/sounds/explosion.m4a');
        this.load.audio('caidaYExplosionBomba', 'FrontEnd/JuegoSaviorsOfTheSomme/assets/sounds/caidaYExplosionBomba.m4a');

        ///PONER IF PARA QUE CARGUE UNA U OTRA
/*         this.load.audio('musicaFondo1', 'FrontEnd/JuegoSaviorsOfTheSomme/assets/sounds/musicaFondo1.m4a');
        this.load.audio('musicaFondo2', 'FrontEnd/JuegoSaviorsOfTheSomme/assets/sounds/musicaFondo2.m4a');
        this.load.audio('musicaFondo3', 'FrontEnd/JuegoSaviorsOfTheSomme/assets/sounds/musicaFondo3.m4a'); */
        ////------------MUSICA y SONIDOS FIN----------------------------------

        this.load.image("superOne","FrontEnd/JuegoSaviorsOfTheSomme/assets/images/objetos/superOne.png");

    } ////CIERRE PRELOAD

    
    ////////////////---------------------------------------------------------------------------------------
    /////////////////-------------------------INICIO CREATE -----------------------------------
    /////////////////-----------------------------------------------------------------------------------
    create() {




        sceneJuego = this;
        //juego_var_sceneJuego = this;
        // SONIDOS
        snd_explosion   = this.sound.add('sonidoexplosion');
        snd_caidaBomba  = this.sound.add('caidaYExplosionBomba');

        //ANIMACIÓN PARA LA EXPLOSIÓN
        explotar = {
            key: 'explode',
            frames: this.anims.generateFrameNumbers('explosion', { start: 0, end: 23, first: 23 }),
            frameRate: 20
        };
        this.anims.create(explotar);
        
        /// CREO GRUPOS DE OBJETOS
        Gpo_ArtillerosAliados       = this.add.group();
        Gpo_ElementosBaseAliada     = this.add.group();
        Gpo_AvionesAliados          = this.add.group();
        Gpo_AvionesEnemigos         = this.add.group();
        Gpo_ElementosBaseEnemiga    = this.add.group();        
        Gpo_ArtillerosEnemigos      = this.add.group();

        /// DISPONGO ZONAS

        var graphics = this.add.graphics();
        graphics.fillStyle(0x000000, 1);
        graphics.fillRect(campoEnemigo_x, campoEnemigo_y, campoEnemigo_w, campoEnemigo_h);
 
        var graphics1 = this.add.graphics();
        graphics1.fillStyle(0x000000, 1);
        graphics1.fillRect(campoAliado_x, campoAliado_y, campoAliado_w, campoAliado_h);
  
        var graphics2 = this.add.graphics();
        graphics2.fillStyle(0x000000, 1);
        graphics2.fillRect(lineaSeparacion_x, lineaSeparacion_y, lineaSeparacion_w, lineaSeparacion_h);


        ////CREACION PISO ALIADO
        this.pisoAliado = this.physics.add.image(campoAliado_x, campoAliado_y, "piso").setDisplayOrigin(0, 0);
        this.pisoAliado.displayWidth    = campoAliado_w;
        this.pisoAliado.displayHeight   = campoAliado_h; 

 
        ////CREACION PISO ENEMIGO
        this.pisoEnemigo = this.physics.add.image(campoEnemigo_x, campoEnemigo_y, "piso").setDisplayOrigin(0, 0);
        this.pisoEnemigo.displayWidth   = campoEnemigo_w;
        this.pisoEnemigo.displayHeight  = campoEnemigo_h; 
  

        /////------------------CREACION DE MI BASE::--------------
            elemento_w          = 50;//deposito_combustibleAliado.width;
            elemento_espacio    = 20;    
        
            base_inicioRandom_x = Phaser.Math.Between(origen_x+20,700); 
            base_inicioRandom_x = base_inicioRandom_x;

            baseAliada_x = base_inicioRandom_x; 
            baseAliada_y = Math.round( juego_var_nav_height*0.80 ); 
            baseAliada_w = ((elemento_w*3)+(elemento_espacio*2));//campoAliado_w*0.50; 
            baseAliada_h = 100; 

            /* let graphics3 = this.add.graphics();
            graphics3.fillStyle(0x326ba8, 1);
            graphics3.fillRect(baseAliada_x, baseAliada_y, baseAliada_w ,baseAliada_h);
              */

            ////AJUSTE POR MAL ORIGIN DE IMAGENES Y PARA OBTENER BIEN LAS COORDENADAS EN BASE A LA IMAGEN
            baseAliada_x = baseAliada_x + (elemento_w/2); 
            baseAliada_y = baseAliada_y + (elemento_w/2); 
/*             baseAliada_w = ((elemento_w*3)+(elemento_espacio*2));//campoAliado_w*0.50; 
            baseAliada_h = 100; */
           
            ////CREACION DE ARMAS

            /// agrego linea para usar en torreta
            line = new Phaser.Geom.Line();
            /* line.setFillStyle(color, alpha); */
            gfx = this.add.graphics().setDefaultStyles({ lineStyle: { width: 10, color: 0xffdd00, alpha: 0.0 } });
            // Agrego audio
            snd_metralleta          = this.sound.add('snd_disparo_metralleta');
            snd_impacto_metralleta  = this.sound.add('snd_impacto_metralleta');
            // DISPARO
            //this.physics.startSystem(Phaser.Physics.ARCADE);


            ////CREACION DE deposito_combustibleAliado
            
            if (juego_var_isPartidaNueva) {
                if (jugadorMiNumero == 1) {
                    colorBando = 'deposito_combustible_negro';
                } else {
                    colorBando = 'deposito_combustible_rojo';
                }
                deposito_combustibleAliado = this.physics.add.image(baseAliada_x, baseAliada_y, colorBando );//.setDisplayOrigin(0, 0);
                deposito_combustibleAliado.nombre = "depCombustible";
                deposito_combustibleAliado.setCollideWorldBounds(true);
                deposito_combustibleAliado.setBounce(0);
                /* var elemento_w = deposito_combustibleAliado.width;
                var elemento_espacio = 20; */
                deposito_combustibleAliado.vida = 100;
                deposito_combustibleAliado.physicsBodyType = Phaser.Physics.ARCADE;;
                Gpo_ElementosBaseAliada.add(deposito_combustibleAliado);
            }else{
                /////IF ES UNA PARTIDA CARGADA DE LAS GUARDADAS.... IGUALAR TODOS LOS DATOS DE 
                ////datos from json
            }

       
            ////CREACION DE torreControl
            if (juego_var_isPartidaNueva) {
                if (jugadorMiNumero == 1) {
                    colorBando = 'torreControl_negro';
                } else {
                    colorBando = 'torreControl_rojo';
                }
                torreControlAliada = this.physics.add.image( (baseAliada_x+elemento_w+elemento_espacio), baseAliada_y, 'torreControl_rojo');//.setDisplayOrigin(0, 0);
                torreControlAliada.nombre = "torreControl";
                torreControlAliada.bando = "Aliado";
                torreControlAliada.setCollideWorldBounds(true);
                torreControlAliada.setBounce(0);
                torreControlAliada.vida = 100;
                torreControlAliada.hayEnemigo = false;
                torreControlAliada.rangoVision = rangoVisionTorre;
                torreControlAliada.lastFiredTorre = 0;
                torreControlAliada.bullet_torre_Aliada = this.physics.add.group({
                    classType: Bullet,
                    maxSize: 3,
                    runChildUpdate: true
                });
                torreControlAliada.bullet_torre_Aliada.physicsBodyType = Phaser.Physics.ARCADE;
                torreControlAliada.setImmovable();
                torreControlAliada.physicsBodyType = Phaser.Physics.ARCADE;
                Gpo_ElementosBaseAliada.add(torreControlAliada);
            }else{
                /////IF ES UNA PARTIDA CARGADA DE LAS GUARDADAS.... IGUALAR TODOS LOS DATOS DE 
                ////datos from json
            }


            ////CREACION DE deposito_bombasAliado
            if (juego_var_isPartidaNueva) {
                if (jugadorMiNumero == 1) {
                    colorBando = 'deposito_bombas_negro';
                } else {
                    colorBando = 'deposito_bombas_rojo';
                }
                deposito_bombasAliado = this.physics.add.image( (baseAliada_x+(elemento_w*2)+(elemento_espacio*2)), baseAliada_y, colorBando);//.setDisplayOrigin(0, 0);
                deposito_bombasAliado.nombre = "depBombas";
                deposito_bombasAliado.setCollideWorldBounds(true);
                deposito_bombasAliado.setBounce(0);
                deposito_bombasAliado.vida = 100;
                deposito_bombasAliado.setImmovable();
                deposito_bombasAliado.physicsBodyType = Phaser.Physics.ARCADE;
                Gpo_ElementosBaseAliada.add(deposito_bombasAliado);
            }else{
                /////IF ES UNA PARTIDA CARGADA DE LAS GUARDADAS.... IGUALAR TODOS LOS DATOS DE 
                ////datos from json
            }

 

            ////CREACION DE pistaAvionesAliada
            if (juego_var_isPartidaNueva) {
                if (jugadorMiNumero == 1) {
                    colorBando = 'pistaAviones_negro';
                } else {
                    colorBando = 'pistaAviones_rojo';
                }
                pistaAvionesAliada = this.physics.add.image( (baseAliada_x+elemento_w+elemento_espacio), (baseAliada_y+(elemento_w/2)+(elemento_espacio*2)) , colorBando);     /////    baseAliada_x, (baseAliada_y+elemento_w+elemento_espacio), 'pistaAviones_negro').setDisplayOrigin(0, 0);
                pistaAvionesAliada.nombre = "pistaAviones";
                pistaAvionesAliada.setCollideWorldBounds(true);
                pistaAvionesAliada.setBounce(0);
                pistaAvionesAliada.setImmovable();
                pistaAvionesAliada.physicsBodyType = Phaser.Physics.ARCADE;
                Gpo_ElementosBaseAliada.add(pistaAvionesAliada);
            }else{
                /////IF ES UNA PARTIDA CARGADA DE LAS GUARDADAS.... IGUALAR TODOS LOS DATOS DE 
                ////datos from json
            }


            ////CREACION DE Artilleros Aliados
            if (juego_var_isPartidaNueva) {
                artilleroA_1 = crearArtillero("Aliado",1); Gpo_ArtillerosAliados.add(artilleroA_1);
                artilleroA_2 = crearArtillero("Aliado",2); Gpo_ArtillerosAliados.add(artilleroA_2);
                artilleroA_3 = crearArtillero("Aliado",3); Gpo_ArtillerosAliados.add(artilleroA_3);
                artilleroA_4 = crearArtillero("Aliado",4); Gpo_ArtillerosAliados.add(artilleroA_4);
                artilleroA_5 = crearArtillero("Aliado",5); Gpo_ArtillerosAliados.add(artilleroA_5);
                artilleroA_6 = crearArtillero("Aliado",6); Gpo_ArtillerosAliados.add(artilleroA_6);
                
            }else{
                /////IF ES UNA PARTIDA CARGADA DE LAS GUARDADAS.... IGUALAR TODOS LOS DATOS DE 
                crearArtilleroDesdeJSON();
    
                ///modificar estas vars en base al json
    /*             juego_var_Aliado_cantidadAviones        = 4;
                juego_var_Aliado_cantidadArtilleros     = 6;
                juego_var_Aliado_tieneTorreDeControl    = 1;
                juego_var_Aliado_tieneDepCombustible    = 1;
                juego_var_Aliado_tieneDepBombas         = 1;
    
                juego_var_Enemigo_cantidadAviones       = 4;
                juego_var_Enemigo_cantidadArtilleros    = 6;
                juego_var_Enemigo_tieneTorreDeControl   = 1;
                juego_var_Enemigo_tieneDepCombustible   = 1;
                juego_var_Enemigo_tieneDepBombas        = 1; */
            }
        /////----------^^^^^^^^^^^^CREACION DE MI BASE^^^^^^^^^^^-------------


        /////------------------CREACION DE BASE ENEMIGA::--------------
        elemento_w          = 50;//deposito_combustibleEnemigo.width;
        elemento_espacio    = 20;    
    
        base_inicioRandom_x = Phaser.Math.Between(origen_x+20,700); 
        base_inicioRandom_x = base_inicioRandom_x;

        baseEnemiga_x = base_inicioRandom_x; 
        baseEnemiga_y = Math.round( juego_var_nav_height*0.03 ); 
        baseEnemiga_w = ((elemento_w*3)+(elemento_espacio*2));//campoEnemigo_w*0.50; 
        baseEnemiga_h = 100; 

     /*    let graphics4 = this.add.graphics();
        graphics4.fillStyle(0xfc6203, 1);
        graphics4.fillRect(baseEnemiga_x, baseEnemiga_y, baseEnemiga_w ,baseEnemiga_h);
         */

        ////AJUSTE POR MAL ORIGIN DE IMAGENES Y PARA OBTENER BIEN LAS COORDENADAS EN BASE A LA IMAGEN
        baseEnemiga_x = baseEnemiga_x + (elemento_w/2); 
        baseEnemiga_y = baseEnemiga_y + (elemento_w/2); 
/*             baseEnemiga_w = ((elemento_w*3)+(elemento_espacio*2));//campoEnemigo_w*0.50; 
        baseEnemiga_h = 100; */
        
                        //completar enemigo
                        //cantidadArtillerosEnemigas += 1;
                        //arrayArtillerosEnemigos.push(this.artilleroE_1);

        ////CREACION DE pistaAvionesEnemiga
        if (juego_var_isPartidaNueva) {
            if (jugadorMiNumero == 2) {
                colorBando = 'pistaAviones_negro';
            } else {
                colorBando = 'pistaAviones_rojo';
            }
            pistaAvionesEnemiga = this.physics.add.image((baseEnemiga_x+elemento_w+elemento_espacio), (baseEnemiga_y-elemento_espacio/2), colorBando);    
            pistaAvionesEnemiga.nombre = "pistaAviones";
            pistaAvionesEnemiga.setCollideWorldBounds(true);
            pistaAvionesEnemiga.setBounce(0);
            pistaAvionesEnemiga.setImmovable();
            pistaAvionesEnemiga.physicsBodyType = Phaser.Physics.ARCADE;
            ///////Gpo_ElementosBaseEnemiga.add(pistaAvionesEnemiga);
        }else{
            /////IF ES UNA PARTIDA CARGADA DE LAS GUARDADAS.... IGUALAR TODOS LOS DATOS DE 
            ////datos from json
        }


        ////CREACION DE deposito_bombasEnemigo
        if (juego_var_isPartidaNueva) {
            if (jugadorMiNumero == 2) {
                colorBando = 'deposito_bombas_negro';
            } else {
                colorBando = 'deposito_bombas_rojo';
            }
            deposito_bombasEnemigo = this.physics.add.image( (baseEnemiga_x), (baseEnemiga_y+(elemento_espacio*2.5)), colorBando);//.setDisplayOrigin(0, 0);
            deposito_bombasEnemigo.nombre = "depBombas";
            deposito_bombasEnemigo.setCollideWorldBounds(true);
            deposito_bombasEnemigo.setBounce(0);
            deposito_bombasEnemigo.vida = 100;
            deposito_bombasEnemigo.setImmovable();
            deposito_bombasEnemigo.physicsBodyType = Phaser.Physics.ARCADE;
            Gpo_ElementosBaseEnemiga.add(deposito_bombasEnemigo);
        }else{
            /////IF ES UNA PARTIDA CARGADA DE LAS GUARDADAS.... IGUALAR TODOS LOS DATOS DE 
            ////datos from json
        }




        ////CREACION DE torreControl
        if (juego_var_isPartidaNueva) {
            if (jugadorMiNumero == 2) {
                colorBando = 'torreControl_negro';
            } else {
                colorBando = 'torreControl_rojo';
            }
            torreControlEnemiga = this.physics.add.image( (baseEnemiga_x+elemento_espacio*3.5), (baseEnemiga_y+(elemento_espacio*2.5)), colorBando);//.setDisplayOrigin(0, 0);
            torreControlEnemiga.nombre = "torreControl";
            torreControlEnemiga.bando = "Enemigo";
            torreControlEnemiga.setCollideWorldBounds(true);
            torreControlEnemiga.setBounce(0);
            torreControlEnemiga.vida = 100;
            torreControlEnemiga.hayEnemigo = false;
            torreControlEnemiga.rangoVision = rangoVisionTorre;
            torreControlEnemiga.lastFiredTorre = 0;
            torreControlEnemiga.bullet_torre_Enemiga = this.physics.add.group({
                classType: Bullet,
                maxSize: 3,
                runChildUpdate: true
            });
            torreControlEnemiga.bullet_torre_Enemiga.physicsBodyType = Phaser.Physics.ARCADE;
            torreControlEnemiga.setImmovable();
            torreControlEnemiga.physicsBodyType = Phaser.Physics.ARCADE;
            Gpo_ElementosBaseEnemiga.add(torreControlEnemiga);
        }else{
            /////IF ES UNA PARTIDA CARGADA DE LAS GUARDADAS.... IGUALAR TODOS LOS DATOS DE 
            ////datos from json
        }


        ////CREACION DE deposito_combustibleEnemigo
        if (juego_var_isPartidaNueva) {
            if (jugadorMiNumero == 2) {
                colorBando = 'deposito_combustible_negro';
            } else {
                colorBando = 'deposito_combustible_rojo';
            }
            deposito_combustibleEnemigo = this.physics.add.image((baseEnemiga_x+elemento_espacio*7), (baseEnemiga_y+(elemento_espacio*2.5)), colorBando);//.setDisplayOrigin(0, 0);
            deposito_combustibleEnemigo.nombre = "depCombustible";
            deposito_combustibleEnemigo.setCollideWorldBounds(true);
            deposito_combustibleEnemigo.setBounce(0);
            // var elemento_w = deposito_combustibleAliado.width;
            //var elemento_espacio = 20; 
            deposito_combustibleEnemigo.vida = 100;
            deposito_combustibleEnemigo.physicsBodyType = Phaser.Physics.ARCADE;;
            Gpo_ElementosBaseEnemiga.add(deposito_combustibleEnemigo);
        }else{
            /////IF ES UNA PARTIDA CARGADA DE LAS GUARDADAS.... IGUALAR TODOS LOS DATOS DE 
            ////datos from json
        }



        ////CREACION DE Artilleros Enemigos
        if (juego_var_isPartidaNueva) {
            artilleroE_1 = crearArtillero("Enemigo",1); Gpo_ArtillerosEnemigos.add(artilleroE_1);
            artilleroE_2 = crearArtillero("Enemigo",2); Gpo_ArtillerosEnemigos.add(artilleroE_2);
            artilleroE_3 = crearArtillero("Enemigo",3); Gpo_ArtillerosEnemigos.add(artilleroE_3);
            artilleroE_4 = crearArtillero("Enemigo",4); Gpo_ArtillerosEnemigos.add(artilleroE_4);
            artilleroE_5 = crearArtillero("Enemigo",5); Gpo_ArtillerosEnemigos.add(artilleroE_5);
            artilleroE_6 = crearArtillero("Enemigo",6); Gpo_ArtillerosEnemigos.add(artilleroE_6);
            
        }else{
            /////IF ES UNA PARTIDA CARGADA DE LAS GUARDADAS.... IGUALAR TODOS LOS DATOS DE 
            crearArtilleroDesdeJSON();

            ///modificar estas vars en base al json
        /*             juego_var_Aliado_cantidadAviones        = 4;
            juego_var_Aliado_cantidadArtilleros     = 6;
            juego_var_Aliado_tieneTorreDeControl    = 1;
            juego_var_Aliado_tieneDepCombustible    = 1;
            juego_var_Aliado_tieneDepBombas         = 1;

            juego_var_Enemigo_cantidadAviones       = 4;
            juego_var_Enemigo_cantidadArtilleros    = 6;
            juego_var_Enemigo_tieneTorreDeControl   = 1;
            juego_var_Enemigo_tieneDepCombustible   = 1;
            juego_var_Enemigo_tieneDepBombas        = 1; */
        }



        /////----------^^^^^^^^^^^^CREACION DE BASE ENEMIGA^^^^^^^^^^^-------------

        
        /////----------------Inicio de CREACION DE AVIONES :: aviones ::--------------
        if (juego_var_isPartidaNueva) {
            avionA_1= new Avion("Aliado",1); Gpo_AvionesAliados.add(avionA_1);
            avionA_2= new Avion("Aliado",2); Gpo_AvionesAliados.add(avionA_2);
            avionA_3= new Avion("Aliado",3); Gpo_AvionesAliados.add(avionA_3);
            avionA_4= new Avion("Aliado",4); Gpo_AvionesAliados.add(avionA_4);

            avionE_1= new Avion("Enemigo",1); Gpo_AvionesEnemigos.add(avionE_1);
            avionE_2= new Avion("Enemigo",2); Gpo_AvionesEnemigos.add(avionE_2);
            avionE_3= new Avion("Enemigo",3); Gpo_AvionesEnemigos.add(avionE_3);
            avionE_4= new Avion("Enemigo",4); Gpo_AvionesEnemigos.add(avionE_4);
        }else{
            /////IF ES UNA PARTIDA CARGADA DE LAS GUARDADAS.... IGUALAR TODOS LOS DATOS DE 
            avionesCrearDesdeJSON();

            ///modificar estas vars en base al json
/*             juego_var_Aliado_cantidadAviones        = 4;
            juego_var_Aliado_cantidadArtilleros     = 6;
            juego_var_Aliado_tieneTorreDeControl    = 1;
            juego_var_Aliado_tieneDepCombustible    = 1;
            juego_var_Aliado_tieneDepBombas         = 1;

            juego_var_Enemigo_cantidadAviones       = 4;
            juego_var_Enemigo_cantidadArtilleros    = 6;
            juego_var_Enemigo_tieneTorreDeControl   = 1;
            juego_var_Enemigo_tieneDepCombustible   = 1;
            juego_var_Enemigo_tieneDepBombas        = 1; */
        }
   
        //// CREAR el puntero y hacerla apuntar a la activa
        avionAliada_Activa = getProximoAvionActivo(Gpo_AvionesAliados);
        avionEnemiga_Activa = getProximoAvionActivo(Gpo_AvionesEnemigos);
        /////----------^^^^^^^^^^^Fin de CREACION DE AVIONES :: aviones ^^^^^^^^^^-------------

        ////CREACION DE MASCARA 
        spotlight = this.make.sprite({
            x: 0,
            y: 0,
            key: 'mask',
            add: false,
        });
        spotlight_instance = new Phaser.Display.Masks.BitmapMask(this, spotlight);

        /// APLICO SOMBRA A AQUELLO QUE QUIERA ENMASCARAR: ARTILLEROS, ELEMENTOS DE BASE+AVIONES

/*         avionEnemiga_Activa.mask = spotlight_instance;
        this.pisoEnemigo.mask = spotlight_instance;
        artilleroE_1.mask = spotlight_instance;
        artilleroE_2.mask = spotlight_instance;
        artilleroE_3.mask = spotlight_instance;
        artilleroE_4.mask = spotlight_instance;
        artilleroE_5.mask = spotlight_instance;
        artilleroE_6.mask = spotlight_instance;
        torreControlEnemiga.mask = spotlight_instance;
        pistaAvionesEnemiga.mask = spotlight_instance;
        deposito_bombasEnemigo.mask = spotlight_instance;
        deposito_combustibleEnemigo.mask = spotlight_instance;
 */

        ///aplico a los elementos enemigos


        ////CREACION SEPARACION DE CAMPOS
        this.separacionCampos = this.physics.add.image(lineaSeparacion_x, lineaSeparacion_y-50, "separacion").setDisplayOrigin(0, 0);
        this.separacionCampos.displayWidth = lineaSeparacion_w;
        this.separacionCampos.displayHeight = lineaSeparacion_h+100; 
        this.separacionCampos.setDepth(3);

        ///// CREACION DE TECLAS
        this.right  = this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.D);
        this.left   =  this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.A);
        this.up     = this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.W);
        this.down   =  this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.S);
        
        this.cambiarAlturaAvionBaja = this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.ONE);
        this.cambiarAlturaAvionMedia = this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.TWO);
        this.cambiarAlturaAvionAlta = this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.THREE);
        
        this.disparoAvion = this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.SPACE);
        this.disparoAvionBomba = this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.B);

        this.avionAutoDestruccion = this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.N);

        this.flecha_right  = this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.RIGHT);
        this.flecha_left   =  this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.LEFT);
        this.flecha_up     = this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.UP);
        this.flecha_down   =  this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.DOWN);
        this.superCenter   =  this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.L);
        this.superKill     =  this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.K);
        this.superBorn     =  this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.J);
        
		this.teclaPausa = this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.P);
        this.teclaReanudar = this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.R);
        /// LAS BOMBAS SE DIBUJAN AQUI POR ORDEN DE CREACION DE IMAGENES
        /// BOMBA ALIADA Y ENEMIGA
        bombaBulletA = this.physics.add.image(3000,0,"bala");
        bombaBulletA.physicsBodyType = Phaser.Physics.ARCADE;
        bombaBulletA.body.enable=false;
        bombaBulletA.scaleX=4;
        bombaBulletA.scaleY=4;
        bombaBulletE = this.physics.add.image(3000,0,"bala");
        bombaBulletE.physicsBodyType = Phaser.Physics.ARCADE;
        bombaBulletE.body.enable=false;
        bombaBulletE.scaleX=4;
        bombaBulletE.scaleY=4;
        

        //// CREACION DE COLISIONES Y SOLAPAMIENTOS

        // OVERLAP UTIL PARA ORDENAMIENTO DE ARTILLEROS EN TABLERO
        this.physics.add.overlap(Gpo_ArtillerosAliados, Gpo_ArtillerosAliados, overlapEvent_artilleroOnCollide, null, this);
        this.physics.add.overlap(Gpo_ArtillerosAliados, Gpo_ElementosBaseAliada, overlapEvent_artilleroOnCollide, null, this);
        

        // CHOQUE ENTRE AVIONES
        this.physics.add.overlap(Gpo_AvionesAliados, Gpo_AvionesEnemigos, overlapEvent_choqueAviones, null, this);
        this.physics.add.overlap(Gpo_AvionesEnemigos, Gpo_AvionesAliados, overlapEvent_choqueAviones, null, this);
       
        // DISPARO DESDE AVION ENEMIGO.
        // 1  --->  HABRIA QUE CREAR UN GPO DE ARMAS AVIONES ALIADO Y ENEMIGA. ASI COMO PARA BOMBAS.
        this.physics.add.overlap(avionEnemiga_Activa.bullets_avion_Enemigo, Gpo_ArtillerosAliados, overlapEvent_impactoBalaEnArtilleroA, null, this);
        this.physics.add.overlap(avionEnemiga_Activa.bullets_avion_Enemigo, Gpo_AvionesAliados, overlapEvent_impactoBalaEnAvionA, null, this);
        this.physics.add.overlap(avionEnemiga_Activa.bullets_avion_Enemigo, Gpo_ElementosBaseAliada, overlapEvent_impactoBalaEnElementoBaseA, null, this);
        this.physics.add.overlap(bombaBulletE, Gpo_ElementosBaseAliada, overlapEvent_impactoBombaEnElementoBaseA, null, this);
        this.physics.add.overlap(bombaBulletE, Gpo_ArtillerosAliados, overlapEvent_impactoBombaEnArtilleroA, null, this); 

        // DISPARO DESDE AVION ALIADO
        this.physics.add.overlap(avionAliada_Activa.bullets_avion_Aliado, Gpo_AvionesEnemigos, overlapEvent_impactoBalaEnAvionE, null, this);
        this.physics.add.overlap(avionAliada_Activa.bullets_avion_Aliado, Gpo_ElementosBaseEnemiga, overlapEvent_impactoBalaEnElementoBaseE, null, this);
        this.physics.add.overlap(avionAliada_Activa.bullets_avion_Aliado, Gpo_ArtillerosEnemigos, overlapEvent_impactoBalaEnArtilleroE, null, this);
        this.physics.add.overlap(bombaBulletA, Gpo_ElementosBaseEnemiga, overlapEvent_impactoBombaEnElementoBaseE, null, this);
        this.physics.add.overlap(bombaBulletA, Gpo_ArtillerosEnemigos, overlapEvent_impactoBombaEnArtilleroE, null, this);

        // DISPARO DESDE TORRE ALIADA
        this.physics.add.overlap(torreControlAliada.bullet_torre_Aliada, Gpo_AvionesEnemigos, overlapEvent_impactoBalaEnAvionE, null, this);
        // DISPARO DESDE TORRE ENEMIGA
        this.physics.add.overlap(torreControlEnemiga.bullet_torre_Enemiga, Gpo_AvionesAliados, overlapEvent_impactoBalaEnAvionA, null, this);

        // DISPARO DESDE ARTILLEROS ALIADOS
        this.physics.add.overlap(artilleroA_1.bullets_artillero_Aliada, Gpo_AvionesEnemigos, overlapEvent_impactoBalaEnAvionE, null, this);
        this.physics.add.overlap(artilleroA_2.bullets_artillero_Aliada, Gpo_AvionesEnemigos, overlapEvent_impactoBalaEnAvionE, null, this);
        this.physics.add.overlap(artilleroA_3.bullets_artillero_Aliada, Gpo_AvionesEnemigos, overlapEvent_impactoBalaEnAvionE, null, this);
        this.physics.add.overlap(artilleroA_4.bullets_artillero_Aliada, Gpo_AvionesEnemigos, overlapEvent_impactoBalaEnAvionE, null, this);
        this.physics.add.overlap(artilleroA_5.bullets_artillero_Aliada, Gpo_AvionesEnemigos, overlapEvent_impactoBalaEnAvionE, null, this);
        this.physics.add.overlap(artilleroA_6.bullets_artillero_Aliada, Gpo_AvionesEnemigos, overlapEvent_impactoBalaEnAvionE, null, this);
        
        this.physics.add.overlap(artilleroE_1.bullets_artillero_Enemigo, Gpo_AvionesAliados, overlapEvent_impactoBalaEnAvionA, null, this); 
        this.physics.add.overlap(artilleroE_2.bullets_artillero_Enemigo, Gpo_AvionesAliados, overlapEvent_impactoBalaEnAvionA, null, this); 
        this.physics.add.overlap(artilleroE_3.bullets_artillero_Enemigo, Gpo_AvionesAliados, overlapEvent_impactoBalaEnAvionA, null, this); 
        this.physics.add.overlap(artilleroE_4.bullets_artillero_Enemigo, Gpo_AvionesAliados, overlapEvent_impactoBalaEnAvionA, null, this); 
        this.physics.add.overlap(artilleroE_5.bullets_artillero_Enemigo, Gpo_AvionesAliados, overlapEvent_impactoBalaEnAvionA, null, this); 
        this.physics.add.overlap(artilleroE_6.bullets_artillero_Enemigo, Gpo_AvionesAliados, overlapEvent_impactoBalaEnAvionA, null, this); 


        ///// CREACION DE TABLERO AVION
        tableroAvion = this.add.text(20, 20, 'Move the mouse', { font: '16px Courier Bold', fill: '#00ff00' });
        ///// CREACION DE TABLERO BASE
        tableroBase = this.add.text(20, 380, 'Move the mouse', { font: '16px Courier Bold', fill: '#ffffff' });
        
        tableroAvion.setDepth(9);
        tableroBase.setDepth(4)

        /// CREO UN TIMER PARA QUE CADA 15 SEGS MUEVA LOS ARTILLEROS RANDOM.
        timer = this.time.addEvent({
            delay: 15000,   // EN MILI SEGUNDOS
            //callback: callback,
            callback: moverGrupodeArtilleros,
            args: [Gpo_ArtillerosAliados.getChildren()],
            callbackScope: Gpo_ArtillerosAliados,
            loop: true,
        });
        //args: [Gpo_ArtillerosAliados.getChildren(),Gpo_AvionesEnemigos.getChildren()], 

        isContinuarUpdate = true;

        
        /// VISTA LATERAL CARGO IMAGENES CORRESPONDIENTES

        cargaElementosVistaLateral();

        //avionAliada_Activa.anims.stop();
        //avionEnemiga_Activa.anims.stop();

    }////CIERRE CREATE
    ////////////////---------------------------------------------------------------------------------------
    /////////////////-------------------------FIN CREATE -----------------------------------
    /////////////////-----------------------------------------------------------------------------------


    /////////////////---------------------------------------------------------------------------------------
    /////////////////-------------------------INICIO UPDATE-----------------------------------
    /////////////////-----------------------------------------------------------------------------------
    update(time, delta) {

        
        tiempoDelUpdate = time;

        isContinuarUpdate = validarCondicionesPartidaAliada();
        if (isContinuarUpdate) {



            ///// MOVIMIENTO DE AVION EN BASE A BOTONES
            ////LUEGO SACAR FISICAS PARA FUERA DEL IF DE BOTONES... ya que por mas que no muevas tu avion, tiene que recalcular en base al websockets los eventosasd

            modificoDireccion = false;
            getUnidadDesplazamiento(avionAliada_Activa);

            /* if(this.right.isDown || this.left.isDown || this.up.isDown || this.down.isDown){
                avionAliada_Activa.enPista = false;

                var vX, vY;
                var diagonal=false;
                if(this.right.isDown) {
                    ultimaTeclaPresionada = "right";
                    avionAliada_Activa.x = avionAliada_Activa.x + avionAliada_Activa.unidadDeVelocidad;//++;
                    avionAliada_Activa.resetFlip();
                    if (this.down.isDown){
                        // avionAliada_Activa.x = avionAliada_Activa.x + unidadDeVelocidad;//++; 
                        avionAliada_Activa.y = avionAliada_Activa.y + avionAliada_Activa.unidadDeVelocidad;//++;
                        avionAliada_Activa.angle=45;
                        vX = 10;vY = 10;
                    }else{
                        if (this.up.isDown){
                            // avionAliada_Activa.x = avionAliada_Activa.x + unidadDeVelocidad;//++; 
                            avionAliada_Activa.y = avionAliada_Activa.y - avionAliada_Activa.unidadDeVelocidad;//--;
                            avionAliada_Activa.angle=-45;
                            vX = 10;vY = -10;
                        }else{avionAliada_Activa.angle=0;diagonal=true;vX = 10;vY = 0;}
                    ;}                
                } else if(this.left.isDown) {
                    ultimaTeclaPresionada = "left";
                    avionAliada_Activa.x = avionAliada_Activa.x - avionAliada_Activa.unidadDeVelocidad;//--;
                    avionAliada_Activa.resetFlip();
                    avionAliada_Activa.flipX=true;
                    if (this.down.isDown){
                        // avionAliada_Activa.x = avionAliada_Activa.x - unidadDeVelocidad;//--; 
                        avionAliada_Activa.y = avionAliada_Activa.y + avionAliada_Activa.unidadDeVelocidad;//++;
                        avionAliada_Activa.angle=-45;
                        vX = -10;vY = 10;
                    }else{
                        if (this.up.isDown){
                            // avionAliada_Activa.x = avionAliada_Activa.x - unidadDeVelocidad;//--; 
                            avionAliada_Activa.y = avionAliada_Activa.y - avionAliada_Activa.unidadDeVelocidad;//--;
                            avionAliada_Activa.angle=45;
                            vX = -10;vY = -10;
                        }else{avionAliada_Activa.angle=0;diagonal=true; vX = -10;vY = 0;}
                    ;}           
                } else if(this.up.isDown) {
                    ultimaTeclaPresionada = "up";
                    avionAliada_Activa.y = avionAliada_Activa.y - avionAliada_Activa.unidadDeVelocidad;//--;
                    avionAliada_Activa.resetFlip();
                    avionAliada_Activa.angle=-90;
                    vX = 0;
                    vY = -10;
                } else if(this.down.isDown) {
                    ultimaTeclaPresionada = "down";
                    avionAliada_Activa.y = avionAliada_Activa.y + avionAliada_Activa.unidadDeVelocidad;//++;
                    avionAliada_Activa.resetFlip();
                    avionAliada_Activa.angle=90;
                    vX = 0;
                    vY = 10;
                }
                
                avionAliada_ActivaVx = vX;
                avionAliada_ActivaVy = vY;

                setVelocidadAvion(avionAliada_Activa, vX, vY);
                modificoDireccion = true; 








                
                //cursors = this.input.keyboard.createCursorKeys();
                //var duration = this.cursors.getDuration();
                //console.log(this.duration);
                

                
               //  if (ultimaTeclaPresionada != ultimaTeclaPresionadaAux) {
               //    ultimaTeclaPresionadaAux = ultimaTeclaPresionada;
               //     sendDatosWebSocket("datosAvionMovimiento");
               // } 
                


            }*/
             ////FIN IF DE BOTONES DE DIRECCION
            



            //// Actualizacion de mascara en base a avionAliada_Activa x y
            ////Nota: aunque el movimiento de la avion se saque para eventos de boton, esta actualizacion se tiene que hacer constantemente en el update debido a la velocidad del avion (incersia)
            spotlight.x = avionAliada_Activa.x;
            spotlight.y = avionAliada_Activa.y+2;

            //// ACTUALIZAR TEXTO DE TABLERO AVION:
            tableroAvion.setText([
                'DATOS AVION:',
    /*           'x: ' + avionAliada_Activa.body.speed,
                'y: ' + avionAliada_Activa.y, */
                'Altura: ' + avionAliada_Activa.z,
                'Bomba: ' + avionAliada_Activa.tieneBomba,
                'Vida: ' + avionAliada_Activa.vida,
                'Combustible: ' + avionAliada_Activa.cantCombustible,
            ]);

            
            ////descuento de combustible
            if (avionAliada_Activa.enPista == false) {
                setDescuentoCombustibleAvionUPDATE(1, avionAliada_Activa);
            }
            
            /////---------FISICAS DE AVIONES y BALAS ENEMIGAS------
////////////setMaksAvion(avionEnemiga_Activa);

            //// ACTUALIZAR TEXTO DE TABLERO BASE:
            tableroBase.setText([
                'DATOS BASE:',
                'Vida de Torre: ' + torreControlAliada.vida + '%',
                'Vida de Dep. Combustible: ' + deposito_combustibleAliado.vida + '%',
                'Vida de Dep. Bombas: ' + deposito_bombasAliado.vida + '%',
                'Cant. Artilleros: ' + Gpo_ArtillerosAliados.getTotalUsed(),
                'Cant. Aviones: ' + Gpo_AvionesAliados.getTotalUsed(),
            ]);

            
            if (avionAliada_Activa.vida > 0) {
                ////avionAliada_Activa = avionAliada_Activa;
                //////DETECTAR SI SE QUEDA SIN NAFTA EXPLOTA
                hayCombutible(avionAliada_Activa);
            }else{
                avionAliada_Activa.body.enable = false;
            }

            /*
            tiempoDelUpdate = time;
            */

            /////DISPARO AVION   ////se intento pasar a un evento pero empeora la jugabilidad
            if ((this.disparoAvion.isDown) && (tiempoDelUpdate > avionAliada_Activa.lastFiredAvion)){
                evento_avion_disparo(tiempoDelUpdate, avionAliada_Activa);
            }
            //console.log(avionAliada_Activa.lastFiredBombaAvion);
            if ((this.disparoAvionBomba.isDown) && (tiempoDelUpdate > avionAliada_Activa.lastFiredBombaAvion)){
                evento_avion_disparoBomba(tiempoDelUpdate, avionAliada_Activa);
            } 

            //////DETECTAR SI AVIONES ENEMIGAS PASAN SOBRE ALGUN ARTILLERO
            hayEnemigoEnRangoArtillero(time);
            hayAliadoEnRangoArtillero(time);

            //////DETECTAR SI AVIONES ENEMIGAS PASAN SOBRE LA TORRE
            hayEnemigoEnRangoTorreDeControl(time);
            hayAliadoEnRangoTorreDeControl(time);


        }else{
            if (mostrarAlert) {
                if (condicionPerdidaAliado) {
                    tableroAvion.visible = false;
                    tableroAvion = this.add.text(400, 280, 'Move the mouse', { font: '36px Courier Bold', fill: '#ff0000' });
                    tableroAvion.setText([
                        '¡¡¡PERDISTE!!',
                    ]);
                    tableroAvion.setDepth(9);
                    //// ACTUALIZAR TEXTO DE TABLERO BASE:
                    tableroBase.setText([
                        'DATOS BASE:',
                        'Vida de Torre: ' + torreControlAliada.vida + '%',
                        'Vida de Dep. Combustible: ' + deposito_combustibleAliado.vida + '%',
                        'Vida de Dep. Bombas: ' + deposito_bombasAliado.vida + '%',
                        'Cant. Artilleros: ' + Gpo_ArtillerosAliados.getTotalUsed(),
                        'Cant. Aviones: ' + Gpo_AvionesAliados.getTotalUsed(),
                    ]);
                } else { //perdio el otro
                    tableroAvion.visible = false;
                    tableroAvion = this.add.text(400, 280, 'Move the mouse', { font: '36px Courier Bold', fill: '#FFFFFF' });
                    tableroAvion.setText([
                        '¡¡¡GANASTE!!',
                    ]);
                    tableroAvion.setDepth(9);
                }

                mostrarAlert = false;
                spotlight.x = mi_base_x;
                spotlight.y = mi_base_y;
                alert("alguien perdio!!");
            }
            
        }////cierre isContinuarUpdate

        
        moveSuper();

    }////CIERRE UPDATE
    /////////////////---------------------------------------------------------------------------------------
    /////////////////-------------------------FIN UPDATE-----------------------------------
    /////////////////---------------------------------------------------------------------------------------


}////CIERRE CLASS

/////////////////---------------------------------------------------------------------------------------
/////////////////-------------------------INICIO eventos teclas-----------------------------------
/////////////////---------------------------------------------------------------------------------------
    window.addEventListener('keydown', escuchaDeKeyDownSwitch); 

    function escuchaDeKeyDownSwitch(event){
        switch(event.keyCode) {
            case Phaser.Input.Keyboard.KeyCodes.D:
                evento_tecla_avionDireccion_D();
            break;
            case Phaser.Input.Keyboard.KeyCodes.A:
                evento_tecla_avionDireccion_A();
            break;
            case Phaser.Input.Keyboard.KeyCodes.W:
                evento_tecla_avionDireccion_W();
                break;
            case Phaser.Input.Keyboard.KeyCodes.S:
                evento_tecla_avionDireccion_S();
                break;
            case Phaser.Input.Keyboard.KeyCodes.ONE:
                evento_tecla_avionAltura0();
            break;
            case Phaser.Input.Keyboard.KeyCodes.TWO:
                evento_tecla_avionAltura1();
            break;
            case Phaser.Input.Keyboard.KeyCodes.THREE:
                evento_tecla_avionAltura2();
                break;
            case Phaser.Input.Keyboard.KeyCodes.SPACE:
                evento_tecla_avionDisparar();
                break;
            case Phaser.Input.Keyboard.KeyCodes.B:
                evento_tecla_avionDispararBomba();
                break; 
            case Phaser.Input.Keyboard.KeyCodes.N:
                evento_tecla_autodestruccion();
                break;
            case Phaser.Input.Keyboard.KeyCodes.P:
                if (isInPause == false) {
                    isInPause = true;
                    pausarJuego();
                } else {
                    isInPause = false;
                    reanudarJuego();
                }
                break;
            case Phaser.Input.Keyboard.KeyCodes.R:
                reanudarJuego();
                break;
        }
    }
    function evento_tecla_avionDireccion_D(){
        if (isEnvioMisDatosBase) { ////por primera vez envio mis datos de elementos de base y la 1er pos de mis Artilleros
            isEnvioMisDatosBase = false;
            sendDatosWebSocket("datosBaseYArtillerosPriVez");
        }else{

            avionAliada_Activa.enPista = false;
            var vX, vY;
            var diagonal=false;
            ultimaTeclaPresionada = "right";
            avionAliada_Activa.x = avionAliada_Activa.x + avionAliada_Activa.unidadDeVelocidad;//++;
            avionAliada_Activa.resetFlip();
            avionAliada_Activa.angle=0;
            diagonal=true;
            vX = 10;
            vY = 0;
            avionAliada_ActivaVx = vX;
            avionAliada_ActivaVy = vY;
            setVelocidadAvion(avionAliada_Activa, vX, vY);
            modificoDireccion = true;




            teclaPess = "D";
            sendDatosWebSocket("datosAvionMovimiento");
        }       


    }
    function evento_tecla_avionDireccion_A(){
        if (isEnvioMisDatosBase) { ////por primera vez envio mis datos de elementos de base y la 1er pos de mis Artilleros
            isEnvioMisDatosBase = false;
            sendDatosWebSocket("datosBaseYArtillerosPriVez");
        } else{

            avionAliada_Activa.enPista = false;
            var vX, vY;
            var diagonal=false;

            ultimaTeclaPresionada = "left";
            avionAliada_Activa.x = avionAliada_Activa.x - avionAliada_Activa.unidadDeVelocidad;//--;
            avionAliada_Activa.resetFlip();
            avionAliada_Activa.flipX=true;
            avionAliada_Activa.angle=0;diagonal=true; vX = -10;vY = 0;

            avionAliada_ActivaVx = vX;
            avionAliada_ActivaVy = vY;

            setVelocidadAvion(avionAliada_Activa, vX, vY);
            modificoDireccion = true;







            teclaPess = "A";
            sendDatosWebSocket("datosAvionMovimiento");
        }  
        
    }
    function evento_tecla_avionDireccion_W(){
        if (isEnvioMisDatosBase) { ////por primera vez envio mis datos de elementos de base y la 1er pos de mis Artilleros
            isEnvioMisDatosBase = false;
            sendDatosWebSocket("datosBaseYArtillerosPriVez");
        } else{


            avionAliada_Activa.enPista = false;

            var vX, vY;
            var diagonal=false;
            
            
            
                ultimaTeclaPresionada = "up";
                avionAliada_Activa.y = avionAliada_Activa.y - avionAliada_Activa.unidadDeVelocidad;//--;
                avionAliada_Activa.resetFlip();
                avionAliada_Activa.angle=-90;
                vX = 0;
                vY = -10;
            
                
            
            avionAliada_ActivaVx = vX;
            avionAliada_ActivaVy = vY;

            setVelocidadAvion(avionAliada_Activa, vX, vY);
            modificoDireccion = true;







            teclaPess = "W";
            sendDatosWebSocket("datosAvionMovimiento");
        }  

    }
    function evento_tecla_avionDireccion_S(){
        if (isEnvioMisDatosBase) { ////por primera vez envio mis datos de elementos de base y la 1er pos de mis Artilleros
            isEnvioMisDatosBase = false;
            sendDatosWebSocket("datosBaseYArtillerosPriVez");
        } else{




            avionAliada_Activa.enPista = false;

            var vX, vY;
            var diagonal=false;
            
            
            
             
            
                ultimaTeclaPresionada = "down";
                avionAliada_Activa.y = avionAliada_Activa.y + avionAliada_Activa.unidadDeVelocidad;//++;
                avionAliada_Activa.resetFlip();
                avionAliada_Activa.angle=90;
                vX = 0;
                vY = 10;
         
                
            
            avionAliada_ActivaVx = vX;
            avionAliada_ActivaVy = vY;

            setVelocidadAvion(avionAliada_Activa, vX, vY);
            modificoDireccion = true;











            teclaPess = "S";
            sendDatosWebSocket("datosAvionMovimiento");
        }  

    }

    function evento_tecla_avionAltura0(){
        //IF el avion esta arriba de la pista de la base
            //implmenetar transicion:: if era mas grande --> setTransicionScaleAumentar(this.pajaro,0.2,0.1);
            
            //// a medida que tengo una aviona activa, la guardo referencia y luego hago
            avionAliada_Activa.z=0;
            avionAliada_Activa.scaleX=0.1;
            avionAliada_Activa.scaleY=0.1;
        //console.log(avionAliada_Activa.depth);
            avionAliada_Activa.setDepth(0);

            ////se podria cambiar la sombra this.pajaroVision.setTexture("transparente");
                    
            //recargar nafta si hay en el tanque de la base
            //otras funciones como, si me quedan en mi panel 5 llaves, entonces tengo 5 reparaciones de vida
        
        /*
        Si pajaro z en 100 entonces 200, bajo vel, incremento gasto de gasolina, incremento tamaño avion
        si pajaro z es 200 entonces 100, incremento vel, descremento gasto de gasolina, decremento tamaño avion

            this.pajaro.z = ;*/

        ////Adentro resuelve cambios por altura y bomba
        setVelocidadAvion(avionAliada_Activa, avionActivaUltimaVX, avionActivaUTlimaVY);
        sendDatosWebSocket("datosAvionAltura");
    }

    function evento_tecla_avionAltura1(){
        avionAliada_Activa.z=100;
        avionAliada_Activa.scaleX=0.2;
        avionAliada_Activa.scaleY=0.2;
        avionAliada_Activa.setDepth(1);
        ////Adentro resuelve cambios por altura y bomba
        setVelocidadAvion(avionAliada_Activa, avionActivaUltimaVX, avionActivaUTlimaVY);
        sendDatosWebSocket("datosAvionAltura"); 
    }

    function evento_tecla_avionAltura2(){
        avionAliada_Activa.z=200;
        avionAliada_Activa.scaleX=0.3;
        avionAliada_Activa.scaleY=0.3;
        avionAliada_Activa.setDepth(2);
        ///this.pajaro2.mask = new Phaser.Display.Masks.BitmapMask(this, spotlightb);
        ///this.piso.mask = new Phaser.Display.Masks.BitmapMask(this, spotlightb);
        ///this.torreta.mask = new Phaser.Display.Masks.BitmapMask(this, spotlightb);
        
        ////Adentro resuelve cambios por altura y bomba
        setVelocidadAvion(avionAliada_Activa, avionActivaUltimaVX, avionActivaUTlimaVY);
        sendDatosWebSocket("datosAvionAltura");
    }

    function evento_tecla_avionDisparar(){
    /*      if (tiempoDelUpdate > avionAliada_Activa.lastFiredAvion) {
            evento_avion_disparo(tiempoDelUpdate, avionAliada_Activa);
        } */ 
    }
    function evento_tecla_avionDispararBomba(){
    /*      if (tiempoDelUpdate > avionAliada_Activa.lastFiredBombaAvion) {
            evento_avion_disparoBomba(tiempoDelUpdate, avionAliada_Activa);
        } */ 
    }

    function evento_tecla_autodestruccion(){
        isAvionActivaAliadaViva = false;
        setDestroyAvionAndGetNewActive(avionAliada_Activa, Gpo_AvionesAliados);
        
    }
/////////////////---------------------------------------------------------------------------------------
/////////////////-------------------------FIN eventos teclas-----------------------------------
/////////////////---------------------------------------------------------------------------------------







/////////////////---------------------------------------------------------------------------------------
/////////////////-------------------------INICIO funciones y eventos genericos y utilitarios-----------------------------------
/////////////////---------------------------------------------------------------------------------------
    function validarCondicionesPartidaAliada(){
        ///// SE EJECTUA SIempre en el update
        var condicionPerdidaContinuar = true;
        ////si se da alguna de las 3 condiciones
        /// me destruyen todas las aviones, me destruyen todos los elementos de mi base o alguno se rindio
        ///se dispara el termino de partida
        if (condicionPerdidaAliado) {
            condicionPerdidaContinuar = false;
            ////mandar send al websocket que se mori o se termino
        } 
        if (condicionPerdidaEnemigo) {
            condicionPerdidaContinuar = false;
        }
        return condicionPerdidaContinuar;
    }

    function distanceRound(priObj, secObj)
    {
        var dx = secObj.x - priObj.x;
        var dy = secObj.y - priObj.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    function actualizarVidaElemento(in_objeto, cantADescontar){
        in_objeto.vida -= cantADescontar;
    }

/////////////////---------------------------------------------------------------------------------------
/////////////////-------------------------FIN funciones y eventos genericos y utilitarios-----------------------------------
/////////////////---------------------------------------------------------------------------------------












/////////////////---------------------------------------------------------------------------------------
/////////////////-------------------------INICIO funciones y eventos de elementos base-----------------------------------
/////////////////---------------------------------------------------------------------------------------
    function hayEnemigoEnRangoTorreDeControl(time){
        torreControlAliada.HayEnemigo = false;
        if (jugadorMiNumero == 1) {
            colorBando = 'torreControl_negro';
        } else {
            colorBando = 'torreControl_rojo';
        }
        torreControlAliada.setTexture(colorBando);
        var arrayAvionesEnemigas=Gpo_AvionesEnemigos.getChildren();
        if(torreControlAliada.vida > 0){
            //para cada avion enemiga
            for (j = 0; j < arrayAvionesEnemigas.length; j++){
                if(arrayAvionesEnemigas[j].vida > 0){
                    //si distancia entre torre y avion < 100
                    distanciaEntreDosObjetos = distanceRound(torreControlAliada, arrayAvionesEnemigas[j]);
                    
                    rangoMaximoVision = torreControlAliada.rangoVision;
                    if (distanciaEntreDosObjetos <= rangoMaximoVision) {
                        //cambio att de torre
                        torreControlAliada.hayEnemigo = true;  
                        if (jugadorMiNumero == 1) {
                            colorBando = 'torreControl_negro_activo';
                        } else {
                            colorBando = 'torreControl_rojo_activo';
                        }
                        torreControlAliada.setTexture(colorBando);  
                        ////DISPARA UN EVENTO --> disparo bala fisica, imagen y sonido 
                        evento_torreControl_disparo(time, torreControlAliada, arrayAvionesEnemigas[j]);
                    }
                }
            }
        }
    }

    function hayAliadoEnRangoTorreDeControl(time){
        torreControlEnemiga.HayEnemigo = false;
        if (jugadorMiNumero == 2) {
            colorBando = 'torreControl_negro';
        } else {
            colorBando = 'torreControl_rojo';
        }
        torreControlEnemiga.setTexture(colorBando);
        var arrayAvionesAliadas=Gpo_AvionesAliados.getChildren();
        if(torreControlEnemiga.vida > 0){
            //para cada avion aliada
            for (j = 0; j < arrayAvionesAliadas.length; j++){
                if(arrayAvionesAliadas[j].vida > 0){
                    //si distancia entre torre y avion < 100
                    distanciaEntreDosObjetos = distanceRound(torreControlEnemiga, arrayAvionesAliadas[j]);
                    rangoMaximoVision = torreControlEnemiga.rangoVision;
                    if (distanciaEntreDosObjetos <= rangoMaximoVision) {
                        //cambio att de torre
                        torreControlEnemiga.hayEnemigo = true; 
                        if (jugadorMiNumero == 2) {
                            colorBando = 'torreControl_negro_activo';
                        } else {
                            colorBando = 'torreControl_rojo_activo';
                        }
                        torreControlEnemiga.setTexture(colorBando);  
                        ////DISPARA UN EVENTO --> disparo bala fisica, imagen y sonido 
                        evento_torreControl_disparo(time, torreControlEnemiga, arrayAvionesAliadas[j]);
                    }
                }
            }
        }
    }

    function evento_torreControl_disparo(time, in_TorreOrigen, in_AvionDestino){
        juego_var_destinoCreacionBalas = 0;
        if ((time > in_TorreOrigen.lastFiredTorre)){
            if (in_TorreOrigen.bando == "Aliado") {
                var bullet = in_TorreOrigen.bullet_torre_Aliada.get();
            } else {
                var bullet = in_TorreOrigen.bullet_torre_Enemiga.get();
            }
            if (bullet)
            {
                anguloEntre_TorreAvion = Phaser.Math.Angle.BetweenPoints(in_TorreOrigen, in_AvionDestino);
                in_TorreOrigen.rotation = anguloEntre_TorreAvion;
                Phaser.Geom.Line.SetToAngle(line, in_TorreOrigen.x, in_TorreOrigen.y - 50, anguloEntre_TorreAvion, 128);
                gfx.clear().strokeLineShape(line);
                bullet.fire(in_TorreOrigen,'Torre');
                snd_metralleta.play();
                in_TorreOrigen.lastFiredTorre = time + 100;
            }
        }
    }

/////////////////---------------------------------------------------------------------------------------
/////////////////-------------------------FIN funciones y eventos de elementos base-----------------------------------
/////////////////---------------------------------------------------------------------------------------








/////////////////---------------------------------------------------------------------------------------
/////////////////-------------------------INICIO funciones y eventos artilleros-----------------------------------
/////////////////---------------------------------------------------------------------------------------
    function crearArtillero(bando,num){
        var newArtillero = null;
                
        if (bando == "Aliado"){
            x = pistaAvionesAliada.x;
            y = pistaAvionesAliada.y;

            if (jugadorMiNumero == 1) {
                colorBando = 'artillero_negro';
            } else {
                colorBando = 'artillero_rojo';
            }

            newArtillero = sceneJuego.physics.add.image(x,y,jugadorMiNumero);//.setDisplayOrigin(0, 0);   //(juego_var_nav_width*0.90-88)
            newArtillero.bando = "Aliado";
            newArtillero.bullets_artillero_Aliada = sceneJuego.physics.add.group({
                classType: Bullet,
                maxSize: 3,
                runChildUpdate: true
            });
            newArtillero.bullets_artillero_Aliada.physicsBodyType = Phaser.Physics.ARCADE;
            artilleroSetearUbicacion(newArtillero);
        }else{
            x = pistaAvionesEnemiga.x;
            y = pistaAvionesEnemiga.y; 

            if (jugadorMiNumero == 2) {
                colorBando = 'artillero_negro';
            } else {
                colorBando = 'artillero_rojo';
            }

            newArtillero = sceneJuego.physics.add.image(x,y,jugadorMiNumero);//.setDisplayOrigin(0, 0);   //(juego_var_nav_width*0.90-88)
            newArtillero.bando = "Enemigo";
            newArtillero.bullets_artillero_Enemigo = sceneJuego.physics.add.group({
                classType: Bullet,
                maxSize: 3,
                runChildUpdate: true
            });
            newArtillero.bullets_artillero_Enemigo.physicsBodyType = Phaser.Physics.ARCADE;
            
            


            artilleroSetearUbicacion(newArtillero);  /////////////////ELIMINAR ESTO
        
        
        
        
        
        
        
        }
        newArtillero.setCollideWorldBounds(true);
        newArtillero.setBounce(0);
        newArtillero.vida = 100;
        newArtillero.rangoVision = rangoVisionArtillero;
        newArtillero.angle = 0;
        newArtillero.lastFiredArtillero = 0;
        newArtillero.num = num;
        newArtillero.HayEnemigo = false;
        newArtillero.physicsBodyType = Phaser.Physics.ARCADE;

        newArtillero.visible=true;
        newArtillero.body.enable=true;

        newArtillero.scaleX=1;
        newArtillero.scaleY=1;
        newArtillero.setDepth(0);

        newArtillero.estadoViva = true;
 
        return newArtillero;
    }

    function crearArtilleroFromJSON(){

    }

    function hayEnemigoEnRangoArtillero(time){  
        var arrayArtillerosAliados=Gpo_ArtillerosAliados.getChildren();
        var arrayAvionesEnemigas= Gpo_AvionesEnemigos.getChildren();
        //Para cada artillero del array
        for (i = 0; i < arrayArtillerosAliados.length; i++){
            if(arrayArtillerosAliados[i].vida > 0){
                arrayArtillerosAliados[i].HayEnemigo = false;
                if (jugadorMiNumero == 1) {
                    colorBando = 'artillero_negro';
                } else {
                    colorBando = 'artillero_rojo';
                }
                arrayArtillerosAliados[i].setTexture(colorBando);
                //para cada avion enemiga
                for (j = 0; j < arrayAvionesEnemigas.length; j++){
                    if(arrayAvionesEnemigas[j].vida > 0){
                        if (arrayAvionesEnemigas[j].z < 200){
                            //si distancia entre artillero y avion < 100
                            distanciaEntreDosObjetos = distanceRound(arrayArtillerosAliados[i], arrayAvionesEnemigas[j]); 
                        
                            rangoMaximoVision = arrayArtillerosAliados[i].rangoVision;
                            if (distanciaEntreDosObjetos <= rangoMaximoVision ) {
                                    //cambio att de artillero
                                    arrayArtillerosAliados[i].hayEnemigo = true;    
                                    if (jugadorMiNumero == 1) {
                                        colorBando = 'artillero_negro_activo';
                                    } else {
                                        colorBando = 'artillero_rojo_activo';
                                    }
                                    arrayArtillerosAliados[i].setTexture(colorBando);  
                                    ////DISPARA UN EVENTO --> disparo bala fisica, imagen y sonido 
                                    evento_artillero_disparo(time, arrayArtillerosAliados[i], arrayAvionesEnemigas[j]); 
                            
                            }
                        } 
                    }
                }
            }
        }
    }

    function hayAliadoEnRangoArtillero(time){  
        var arrayArtillerosEnemigos=Gpo_ArtillerosEnemigos.getChildren();
        var arrayAvionesAliadas= Gpo_AvionesAliados.getChildren();
        //Para cada artillero del array
        for (i = 0; i < arrayArtillerosEnemigos.length; i++){
            if(arrayArtillerosEnemigos[i].vida > 0){
                arrayArtillerosEnemigos[i].HayEnemigo = false;
                if (jugadorMiNumero == 2) {
                    colorBando = 'artillero_negro';
                } else {
                    colorBando = 'artillero_rojo';
                }
                arrayArtillerosEnemigos[i].setTexture(colorBando);
                //para cada avion aliada
                for (j = 0; j < arrayAvionesAliadas.length; j++){
                    if(arrayAvionesAliadas[j].vida > 0){
                        if (arrayAvionesAliadas[j].z < 200){
                            //si distancia entre artillero y avion < 100
                            distanciaEntreDosObjetos = distanceRound(arrayArtillerosEnemigos[i], arrayAvionesAliadas[j]); 
                        
                            rangoMaximoVision = arrayArtillerosEnemigos[i].rangoVision;
                            if (distanciaEntreDosObjetos <= rangoMaximoVision ) {
                                    //cambio att de artillero
                                    arrayArtillerosEnemigos[i].hayEnemigo = true;   
                                    if (jugadorMiNumero == 2) {
                                        colorBando = 'artillero_negro_activo';
                                    } else {
                                        colorBando = 'artillero_rojo_activo';
                                    }                 
                                    arrayArtillerosEnemigos[i].setTexture(colorBando);  
                                    ////DISPARA UN EVENTO --> disparo bala fisica, imagen y sonido 
                                    evento_artillero_disparo(time, arrayArtillerosEnemigos[i], arrayAvionesAliadas[j]); 
                            
                            }
                        } 
                    }
                }
            }
        }
    }

    function evento_artillero_disparo(time, in_ArtilleroOrigen, in_AvionDestino){
        juego_var_destinoCreacionBalas = 1;
        if ((time > in_ArtilleroOrigen.lastFiredArtillero)){ 
            if (in_ArtilleroOrigen.bando == "Aliado") {
                var bullet = in_ArtilleroOrigen.bullets_artillero_Aliada.get();  
                /* var bullet = bullets_artillero_Aliada.get(); */ 
            } else {
                var bullet = in_ArtilleroOrigen.bullets_artillero_Enemigo.get(); 
            }
            if (bullet)
            {
                anguloEntre_ArtilleroAvion = Phaser.Math.Angle.BetweenPoints(in_ArtilleroOrigen, in_AvionDestino);
                in_ArtilleroOrigen.rotation = anguloEntre_ArtilleroAvion;
                Phaser.Geom.Line.SetToAngle(line, in_ArtilleroOrigen.x, in_ArtilleroOrigen.y - 50, anguloEntre_ArtilleroAvion, 128);
                gfx.clear().strokeLineShape(line);

                bullet.fire(in_ArtilleroOrigen, anguloEntre_ArtilleroAvion);
                snd_metralleta.play();
                in_ArtilleroOrigen.lastFiredArtillero = time + 100;
            }
        }
    }

    function artilleroSetearUbicacion(artillero){
        ////Si se separa en 2 js (aliado.js, enemigo.js), se borra el codigo correspondiente del if
        if(artillero.bando == "Aliado"){
            mi_campo_x = campoAliado_x;
            mi_campo_y = campoAliado_y;
            mi_campo_w = campoAliado_w;
            mi_campo_h = campoAliado_h;
        
            mi_base_x = baseAliada_x;
            mi_base_y = baseAliada_y;
            mi_base_w = baseAliada_w;
            mi_base_h = baseAliada_h;

            x = Phaser.Math.Between(campoAliado_x+50, campoAliado_w-50);
            y = Phaser.Math.Between(campoAliado_y+100, (mi_campo_y+campoAliado_h-30) );
        }else{
            mi_campo_x = campoEnemigo_x;
            mi_campo_y = campoEnemigo_y;
            mi_campo_w = campoEnemigo_w;
            mi_campo_h = campoEnemigo_h;
        
            mi_base_x = baseEnemiga_x;
            mi_base_y = baseEnemiga_y;
            mi_base_w = baseEnemiga_w;
            mi_base_h = baseEnemiga_h;

            x = Phaser.Math.Between(campoEnemigo_x+50, campoEnemigo_w-50);
            y = Phaser.Math.Between(campoEnemigo_y+30, (campoEnemigo_h-100) );
        }

        artillero.x = x;
        artillero.y = y;
    }

    function moverArtillero(artillero){
        artillero.x=Phaser.Math.Between(campoAliado_x, campoAliado_w);
    } // artillero setear oncollide function with Base

    function moverGrupodeArtilleros(){
        var arrayArtillerosAliados = Gpo_ArtillerosAliados.getChildren();
        var arrayAvionesEnemigas = Gpo_AvionesEnemigos.getChildren();
        //Para cada artillero del array
        for (i = 0; i < arrayArtillerosAliados.length; i++){
            if(arrayArtillerosAliados[i].vida > 0){
                arrayArtillerosAliados[i].HayEnemigo = false;
                if (jugadorMiNumero == 1) {
                    colorBando = 'artillero_negro';
                } else {
                    colorBando = 'artillero_rojo';
                }
                arrayArtillerosAliados[i].setTexture(colorBando);
                //para cada avion enemiga
                for (j = 0; j < arrayAvionesEnemigas.length; j++){
                    if(arrayAvionesEnemigas[j].vida > 0){
                        if (arrayAvionesEnemigas[j].z < 200){
                            //si distancia entre artillero y avion < 100
                            distanciaEntreDosObjetos = distanceRound(arrayArtillerosAliados[i], arrayAvionesEnemigas[j]); 
                            rangoMaximoVision = arrayArtillerosAliados[i].rangoVision;
                            if (distanciaEntreDosObjetos > rangoMaximoVision) {
                                moverArtillero(arrayArtillerosAliados[i]);
                            }
                        } 
                    }
                }
            }

        }  

        sendDatosWebSocket("datosArtillerosByTimer");

    } // artillero setear oncollide function with Base

/////////////////---------------------------------------------------------------------------------------
/////////////////-------------------------FIN funciones y eventos artilleros-----------------------------------
/////////////////---------------------------------------------------------------------------------------










/////////////////---------------------------------------------------------------------------------------
/////////////----------- INICIO Class Avion-----------------
/////////////////---------------------------------------------------------------------------------------
    var Avion = new Phaser.Class({

    Extends: Phaser.Physics.Arcade.Image,

    initialize:

    function Avion (bando,num)
    {
        //Phaser.Physics.Arcade.Image.call(this, scene, 0, 0, 'avion');

        

        var avion = null;
        /////CREO LOS N aviones en base a si es una partida nudeva son 4 y si es cargada lo n que tenia
        
        if (bando == "Aliado"){
            x = pistaAvionesAliada.x;
            y = pistaAvionesAliada.y;

            if (jugadorMiNumero == 1) {
                colorBando = 'avionNegro';
            } else {
                colorBando = 'avionRojo';
            }
            avion = sceneJuego.physics.add.image(x, y,colorBando);
            avion.bando = "Aliado";
            avion.bullets_avion_Aliado = sceneJuego.physics.add.group({
                classType: Bullet,
                maxSize: 5,
                runChildUpdate: true
            });
            avion.bullets_avion_Aliado.physicsBodyType = Phaser.Physics.ARCADE; 

            //avion.mask = spotlight_instance;
        }else{
            x = pistaAvionesEnemiga.x;
            y = pistaAvionesEnemiga.y; 

            if (jugadorMiNumero == 2) {
                colorBando = 'avionNegro';
            } else {
                colorBando = 'avionRojo';
            }
            avion = sceneJuego.physics.add.image(x, y,colorBando);
            avion.bando = "Enemigo";    
            avion.bullets_avion_Enemigo = sceneJuego.physics.add.group({
                classType: Bullet,
                maxSize: 5,
                runChildUpdate: true
            });
            avion.bullets_avion_Enemigo.physicsBodyType = Phaser.Physics.ARCADE; 

        }
        avion.setCollideWorldBounds(true);
        avion.setBounce(0);
        avion.scaleX=0.1;
        avion.scaleY=0.1;
        avion.z = 0; 
        avion.setDepth(0);
        avion.vida = 100;
        avion.rangoVision = rangoVisionAvion;
        avion.angle = 0;
        avion.tieneBomba = true;
        avion.cantBombas = 1;
        avion.cantCombustible = 9910000;
        avion.unidadDeVelocidad = 0;
        avion.unidadDeConsumoCombustible = 0;
        avion.isAvionEnCampoEnemigo = false;
        avion.estadoViva = true;
        avion.num = num;
        avion.HayEnemigo = false;
        avion.lastFiredAvion = 0;
        avion.lastFiredBombaAvion = 0;
        avion.setVelocity(0,0);
        avion.physicsBodyType = Phaser.Physics.ARCADE;
        if (num == 1){
            avion.visible=true;
            avion.body.enable=true;
            avion.isLaAvionActiva = true;
            avion.enPista = true;

        }else{
            if (bando == "Aliado"){
                x = pistaAvionesAliada.x;
                y = pistaAvionesAliada.y;                
            }else{
                x = pistaAvionesEnemiga.x;
                y = pistaAvionesEnemiga.y; 

            }
            avion.x = x;
            avion.y = y; 

            /*  a = Phaser.Math.Between(origen_x+20,550); 
            avion.x = a;
            a = Phaser.Math.Between(origen_x+20,550);  
            avion.y = a; */

            avion.visible=false;
            ////avion.body.enable=false;  
            
            avion.isLaAvionActiva = false;
            avion.enPista = false;

        }
        /////avion.setAcceleration(10,0);
    return avion;



    },

    fire: function (avion)
    {
        
    },

    update: function (time, delta)
    {

    },

    kill: function ()
    {

    }

    });
/////////////----------- Fin Class Avion-----------------

/////////////////---------------------------------------------------------------------------------------
/////////////////-------------Comienzo Eventos Avion Genericos
/////////////////---------------------------------------------------------------------------------------
    function avionesCrearDesdeJSON(){

    }

    function getProximoAvionActivo(in_GrupoAviones){
        var in_AvionActivaAUX;
        in_AvionActivaAUX = in_GrupoAviones.getFirstAlive();
        in_AvionActivaAUX.visible=true;
        in_AvionActivaAUX.body.enable=true;
        in_AvionActivaAUX.isLaAvionActiva = true;
        in_AvionActivaAUX.enPista = true;
        return in_AvionActivaAUX;
    }

    function setDestroyAvionAndGetNewActive(in_avionADestruir, in_grupoAvionesARetirar){
        in_avionADestruir.vida = 0;
        in_avionADestruir.body.enable = false;
        in_avionADestruir.estadoViva = false;

        boom = sceneJuego.add.sprite(in_avionADestruir.x, in_avionADestruir.y, 'explosion');
        boom.anims.play('explode');
        snd_explosion.play();

        /// PARA ELIMINAR UN SPRITE, SI PERTENECE A UN GRUPO HAY QUE HACERLO ASI...
        in_grupoAvionesARetirar.killAndHide(in_avionADestruir);

        if (in_avionADestruir.bando == "Aliado") {
            if (Gpo_AvionesAliados.getTotalUsed() == 0) {
                condicionPerdidaAliado = true;
            } else {
                avionAliada_Activa = getProximoAvionActivo(Gpo_AvionesAliados);
                isAvionActivaAliadaViva = true;
            }
        } else {
            if (Gpo_AvionesEnemigos.getTotalUsed() == 0) {
                condicionPerdidaEnemigo = true;
            } else {
                avionEnemiga_Activa = getProximoAvionActivo(Gpo_AvionesEnemigos);
                isAvionActivaEnemigaViva = true;
            } 
        } 
    }

    function getUnidadDesplazamiento(avion){
        avion.unidadDeVelocidad = 5;
        if (avion.tieneBomba) {
            avion.unidadDeVelocidad = avion.unidadDeVelocidad - avion.unidadDeVelocidad*0.50;
        }
        if(avion.z > 100){ ///altura maxima
            avion.unidadDeVelocidad = avion.unidadDeVelocidad - avion.unidadDeVelocidad*0.50;
        }
        if ( (avion.tieneBomba) && (avion.z > 100) ) {
            avion.unidadDeVelocidad = avion.unidadDeVelocidad*0.10;
        }

        if (avion.z > 100) {
            avion.unidadDeVelocidad = avion.unidadDeVelocidad - avion.unidadDeVelocidad*1.50;
        }
    }

    function setVelocidadAvion(avion, velX, velY){
        if (avion.tieneBomba) {
            velX = velX*0.80;
            velY = velY*0.80;
        } 
        if(avion.z <= 100){//baja altura
            avionActivaUltimaVX = velX;
            avionActivaUTlimaVY = velY;
            avion.setVelocity(velX,velY);
        }else{ //200 es altura
            avionActivaUltimaVX = velX/2;
            avionActivaUTlimaVY = velY/2;
            avion.setVelocity(velX/2,velY/2);
        }
    }

    function setDescuentoCombustibleAvionUPDATE(num, avion){
        ////obtener el segundo valor para descontar cuando toca tecla para moverse
        getunidadDeConsumoCombustible(num, avion);
        //("avion.unidadDeConsumoCombustible::"+ avion.unidadDeConsumoCombustible );
        if ( avion.cantCombustible > 0) {
            avion.cantCombustible = avion.cantCombustible - avion.unidadDeConsumoCombustible;
        } else {
            ////EVENTO DE DESTRUCCION/caida POR FALTA DE COMBUSTIBLE
            hayCombutible(avion);  
        }
    }

    function getunidadDeConsumoCombustible(num, avion){
        avion.unidadDeConsumoCombustible = num;
        if (avion.tieneBomba) {
            avion.unidadDeConsumoCombustible = avion.unidadDeConsumoCombustible + num ;
        }
        if(avion.z > 100){ ///altura maxima
            avion.unidadDeConsumoCombustible = avion.unidadDeConsumoCombustible + num;
        }
        if (modificoDireccion) {
            avion.unidadDeConsumoCombustible = avion.unidadDeConsumoCombustible + num;
        }
    }

    function hayCombutible(avion){
        if ( (avion.cantCombustible <= 0) ){ // && isAvionActivaAliadaViva){
            ////CAMBIAR FLAG PARA QUE LA SOMBRA QUEDE EN LA BASE
            isAvionActivaAliadaViva = false;
            setDestroyAvionAndGetNewActive(avion, Gpo_AvionesAliados);
        }
    }

    function evento_avion_disparo(time, in_AvionOrigen){
        juego_var_destinoCreacionBalas = 2;
        var bullet = in_AvionOrigen.bullets_avion_Aliado.get();
        if (bullet)
        {
            bullet.fire(in_AvionOrigen);
            snd_metralleta.play();
            in_AvionOrigen.lastFiredAvion = time + 100;
        }
    }

    function evento_avion_disparoBomba(time, in_AvionOrigen){
        ///juego_var_destinoCreacionBalas = 2; // --> para que era esto???
    //console.log(in_AvionOrigen.z );
        if (in_AvionOrigen.z == 200 && in_AvionOrigen.tieneBomba){
            bombaBulletA.x=in_AvionOrigen.x;
            bombaBulletA.y=in_AvionOrigen.y;
            snd_caidaBomba.play();
            //console.log(bombaBulletA);
            tweenBombaA = sceneJuego.tweens.add({
                targets: [ bombaBulletA ],
                //x:'+=100',
                scaleX:'-=5',
                scaleY:'-=5',
                duration: 2000,
                ease: 'Power1'
            });
            snd_caidaBomba.stop();
            bombaBulletA.body.enable=true;
            timerBombaA = sceneJuego.time.addEvent({
                delay: 2000,   // EN MILI SEGUNDOS
                //callback: callback,
                callback: enmascararBomba,
                args: [bombaBulletA,tweenBombaA],
                callbackScope: bombaBulletA,tweenBombaA,
                loop: true,
            });
            //in_AvionOrigen.tieneBomba=false; --> ACTIVAR PARA QUE SOLO DISPARE UNA BOMBA!!!
        }
    }

    function setMaksAvion(in_avion){
        if (isEnSuTerritorio(in_avion)) {
            in_avion.mask = spotlight_instance;
        } else {
            in_avion.mask = 0;
        }
    }

    function isEnSuTerritorio(in_avion){
        if(in_avion.bando == "Aliado"){
            if(in_avion.y > juego_var_nav_height*0.48){
                return true;
            }
            if(in_avion.y < juego_var_nav_height*0.48){
                return false
            }
        }else{
            if(in_avion.y > juego_var_nav_height*0.48){
                return false;
            }
            if(in_avion.y < juego_var_nav_height*0.48){
                return true
            }
        }
    }

    function enmascararBomba (bomba,tweenBomba){
        bombaBulletA.body.enable=false;
        bomba.x=3000;
        bomba.y=3000;
        bomba.scaleX=4;
        bomba.scaleY=4;
        tweenBomba.stop();
    }

    function createSuper(){
        superOne = sceneJuego.physics.add.image(500, 300,"superOne");
        superOne.scaleX=0.1;
        superOne.scaleY=0.1;
        superOne.z = 0; 
        superOne.setDepth(999);
        superOne.setVelocity(0,0);
        superOne.visible=true;
        superOne.body.enable=true;
        superOne.unidadDeVelocidad=5;
    }
    
    function moveSuper(){
        if( (sceneJuego.superBorn.isDown) && (cantSuper==0) ) {
            createSuper();
            isSuperAlive = true;
            cantSuper=1;
        }

        if (isSuperAlive == true) {
            if(sceneJuego.flecha_right.isDown || sceneJuego.flecha_left.isDown || sceneJuego.flecha_up.isDown || sceneJuego.flecha_down.isDown || sceneJuego.superCenter.isDown || sceneJuego.superKill.isDown ){
                var diagonal=false;
                if(sceneJuego.flecha_right.isDown) {
                    superOne.x = superOne.x + superOne.unidadDeVelocidad;//++;
                    superOne.resetFlip();
                    if (sceneJuego.flecha_down.isDown){
                        // superOne.x = superOne.x + unidadDeVelocidad;//++; 
                        superOne.y = superOne.y + superOne.unidadDeVelocidad;//++;
                    }else{
                        if (sceneJuego.flecha_up.isDown){
                            // superOne.x = superOne.x + unidadDeVelocidad;//++; 
                            superOne.y = superOne.y - superOne.unidadDeVelocidad;//--;
                        }else{diagonal=true;}
                    ;}                
                } else if(sceneJuego.flecha_left.isDown) {
                    superOne.x = superOne.x - superOne.unidadDeVelocidad;//--;
                    if (sceneJuego.flecha_down.isDown){
                        // superOne.x = superOne.x - unidadDeVelocidad;//--; 
                        superOne.y = superOne.y + superOne.unidadDeVelocidad;//++;
                    }else{
                        if (sceneJuego.flecha_up.isDown){
                            // superOne.x = superOne.x - unidadDeVelocidad;//--; 
                            superOne.y = superOne.y - superOne.unidadDeVelocidad;//--;
                        }else{diagonal=true;}
                    ;}           
                } else if(sceneJuego.flecha_up.isDown) {
                    superOne.y = superOne.y - superOne.unidadDeVelocidad;//--;
                } else if(sceneJuego.flecha_down.isDown) {
                    superOne.y = superOne.y + superOne.unidadDeVelocidad;//++;
                }

                if (sceneJuego.superCenter.isDown){
                    superOne.x = 500;
                    superOne.y = 300;
                }
                if (sceneJuego.superKill.isDown){
                    superOne.x = 2500;
                    superOne.y = 2300;
                    superOne.visible= false;
                    superOne.body.enable= false;
                    isSuperAlive = false;
                    cantSuper=0;
                }
            } ////FIN IF DE BOTONES DE DIRECCION
        }
        if (isSuperAlive) {
            spotlight.x = superOne.x;
            spotlight.y = superOne.y+2;
        }
        
    }
/////////////////---------------------------------------------------------------------------------------
/////////////////-------------FIN Eventos Avion Genericos
/////////////////---------------------------------------------------------------------------------------






/////////////////---------------------------------------------------------------------------------------
/////////////////-------------Comienzo Eventos OVERLAP
/////////////////---------------------------------------------------------------------------------------
    function overlapEvent_artilleroOnCollide(artillero, objeto2){
        artilleroSetearUbicacion(artillero); 
        
        artilleroAuxNum = artillero.num;
        artilleroAuxX = artillero.x;
        artilleroAuxY = artillero.y;

        if (!isEnvioMisDatosBase) {
            sendDatosWebSocket("datosArtilleroByOverlap");
        }
    }

    function overlapEvent_choqueAviones(avion1,avion2){
        if (avion1.z == avion2.z){
            ////CAMBIAR FLAG PARA QUE LA SOMBRA QUEDE EN LA BASE
            isAvionActivaAliadaViva = false;
            isAvionActivaEnemigaViva = false;
            setDestroyAvionAndGetNewActive(avion1, Gpo_AvionesAliados);
            setDestroyAvionAndGetNewActive(avion2, Gpo_AvionesEnemigos);        
        }
    }

    function overlapEvent_impactoBalaEnArtilleroA(artillero,bala){
        artillero.vida--;
        //e.log(bala);
        snd_impacto_metralleta.play();
        //p1Danado = true;
        //console.log("recibio disparo"+artillero.vida);
        bala.kill();
        if (artillero.vida <= 0){
        //  console.log(artillero.vida);
            artillero.body.enable = false;
            boom = this.add.sprite(artillero.x, artillero.y, 'explosion');
            boom.anims.play('explode');
            snd_explosion.play();
            Gpo_ArtillerosAliados.killAndHide(artillero);
        // Gpo_ArtillerosAliados.killAndHide(artillero);        
        } 
    }

    function overlapEvent_impactoBalaEnArtilleroE(artillero,bala){
        artillero.vida--;
        //console.log(bala);
        snd_impacto_metralleta.play();
        //p1Danado = true;
        //console.log("recibio disparo"+artillero.vida);
        bala.kill();
        if (artillero.vida <= 0){
        //  console.log(artillero.vida);
            artillero.body.enable = false;
            boom = this.add.sprite(artillero.x, artillero.y, 'explosion');
            boom.anims.play('explode');
            snd_explosion.play();
            Gpo_ArtillerosEnemigos.killAndHide(artillero);
        // Gpo_ArtillerosEnemigos.killAndHide(artillero);        
        } 
    }

    function overlapEvent_impactoBalaEnAvionA(avion, bala ){
        avion.vida--;
        snd_impacto_metralleta.play();
        bala.kill();
        if (avion.vida <= 0){
            isAvionActivaAliadaViva = false;
            setDestroyAvionAndGetNewActive(avion, Gpo_AvionesAliados);
        } 
    } 

    function overlapEvent_impactoBalaEnAvionE(avion, bala ){
        avion.vida--;
        snd_impacto_metralleta.play();
        bala.kill();
        if (avion.vida <= 0){
            isAvionActivaEnemigaViva = false;
            setDestroyAvionAndGetNewActive(avion, Gpo_AvionesEnemigos); 
        } 
    } 

    function overlapEvent_impactoBalaEnElementoBaseA(elem_base,bala){
        elem_base.vida--;
        snd_impacto_metralleta.play();
        bala.kill();
        if (elem_base.vida <= 0){
        //  console.log(artillero.vida);
            elem_base.body.enable = false;
            boom = this.add.sprite(elem_base.x, elem_base.y, 'explosion');
            boom.anims.play('explode');
            snd_explosion.play();
            Gpo_ElementosBaseAliada.killAndHide(elem_base);      
            if (elem_base.getTotalUsed() == 0) {
                condicionPerdidaAliado = true;
            }  
        } 
    } 

    function overlapEvent_impactoBalaEnElementoBaseE(elem_base,bala){
        elem_base.vida--;
        //console.log(bala);
        snd_impacto_metralleta.play();
        //p1Danado = true;
        //console.log("recibio disparo"+artillero.vida);
        bala.kill();
        if (elem_base.vida <= 0){
        //  console.log(artillero.vida);
            elem_base.body.enable = false;
            boom = this.add.sprite(elem_base.x, elem_base.y, 'explosion');
            boom.anims.play('explode');
            snd_explosion.play();
            Gpo_ElementosBaseEnemiga.killAndHide(elem_base);
            //Gpo_ArtillerosAliados.killAndHide(torre);         
        } 
    } 

    function overlapEvent_impactoBombaEnArtilleroA(bomba,artillero ){
        artillero.vida=0;
        if (artillero.vida <= 0){
            boom = this.add.sprite(artillero.x, artillero.y, 'explosion');
            boom.anims.play('explode');
            snd_explosion.play();
            artillero.body.enable=false;
            Gpo_ArtillerosAliados.killAndHide(artillero);   
        } 
    } 

    function overlapEvent_impactoBombaEnArtilleroE(bomba,artillero ){
        artillero.vida=0;
        if (artillero.vida <= 0){
            boom = this.add.sprite(artillero.x, artillero.y, 'explosion');
            boom.anims.play('explode');
            snd_explosion.play();
            artillero.body.enable=false;
            Gpo_ArtillerosEnemigos.killAndHide(artillero);   
        } 
    } 

    function overlapEvent_impactoBombaEnElementoBaseA(bomba,elem_base ){
        elem_base.vida=0;
        if (elem_base.vida <= 0){
            boom = this.add.sprite(elem_base.x, elem_base.y, 'explosion');
            boom.anims.play('explode');
            snd_explosion.play();
            elem_base.body.enable=false;
            Gpo_ElementosBaseAliada.killAndHide(elem_base);   
            if (Gpo_ElementosBaseAliada.getTotalUsed() == 0) {
                condicionPerdidaAliado = true;
            }    
        } 
    } 

    function overlapEvent_impactoBombaEnElementoBaseE(bomba, elem_base){
        elem_base.vida=0;
        ////////bomba.destroy();   //// se va a la m
        if (elem_base.vida <= 0){
            boom = this.add.sprite(elem_base.x, elem_base.y, 'explosion');
            boom.anims.play('explode');
            snd_explosion.play();
            elem_base.body.enable=false;
            Gpo_ElementosBaseEnemiga.killAndHide(elem_base); 
            if (Gpo_ElementosBaseEnemiga.getTotalUsed() == 0) {
                condicionPerdidaEnemigo = true;
            }   
        } 
    }

/////////////////---------------------------------------------------------------------------------------
/////////////////-------------FIN Eventos OVERLAP----------------------------------
/////////////////---------------------------------------------------------------------------------------

function cargaElementosVistaLateral(){
    //VISTA LATERAL
    pisoLateral = sceneJuego.add.image(campoAliado_w/2, ((campoAliado_h+campoEnemigo_h)/2), 'pisoLateral');
    pisoLateral.setDepth(100);
    pisoLateral.setVisible(false);
    pisoLateral.displayWidth    = campoAliado_w;
    pisoLateral.displayHeight   = campoAliado_h+campoEnemigo_h;
    
    separacionVertical = sceneJuego.add.image(50,0, 'separacionVertical');
    separacionVertical.setDepth(900);
    separacionVertical.setVisible(false);
    separacionVertical.displayWidth    = 300;
    separacionVertical.displayHeight   = 1200;

    if (jugadorMiNumero == 1) {
        colorBando = 'avionIzquierdaComunNegra';
    } else {
        colorBando = 'avionIzquierdaComunRoja';
    }
    avionActivaAliadaLateral = sceneJuego.add.image(campoAliado_w/2, ((campoAliado_h+campoEnemigo_h)/2), colorBando);
    avionActivaAliadaLateral.setVisible(false);
    avionActivaAliadaLateral.setDepth(100);

    if (jugadorMiNumero == 2) {
        colorBando = 'avionDerechaComunNegra';
    } else {
        colorBando = 'avionDerechaComunRoja';
    }
    avionActivaEnemigaLateral = sceneJuego.add.image(2000, 2000, colorBando);
    avionActivaEnemigaLateral.setVisible(false);
    avionActivaEnemigaLateral.setDepth(100);
    
    if (jugadorMiNumero == 1) {
        colorBando = 'pistaAvionesVLateral_negro';
    } else {
        colorBando = 'pistaAvionesVLateral_roja';
    }
    pistaDeAvionAliadaLateral = sceneJuego.add.image(750, 500, colorBando);
    pistaDeAvionAliadaLateral.setDepth(100);
    pistaDeAvionAliadaLateral.scaleX = 1.5;
    pistaDeAvionAliadaLateral.scaleY = 1.5;

    if (jugadorMiNumero == 1) {
        colorBando = 'deposito_bombas_negro';
    } else {
        colorBando = 'deposito_bombas_rojo';
    }
    depositoDeBombasAliadoLateral = sceneJuego.add.image(820, 400, colorBando);
    depositoDeBombasAliadoLateral.setDepth(100);
    depositoDeBombasAliadoLateral.scaleX = 1.5;
    depositoDeBombasAliadoLateral.scaleY = 1.5;

    if (jugadorMiNumero == 1) {
        colorBando = 'torreControl_VLateral_negra';
    } else {
        colorBando = 'torreControl_VLateral_roja';
    }
    torreControlAliadaLateral = sceneJuego.add.image(700, 400, colorBando);
    torreControlAliadaLateral.setDepth(100);
    torreControlAliadaLateral.scaleX = 2;
    torreControlAliadaLateral.scaleY = 2;

    if (jugadorMiNumero == 1) {
        colorBando = 'deposito_combustible_negro';
    } else {
        colorBando = 'deposito_combustible_rojo';
    }
    depositoDeCombustibleAliadoLateral = sceneJuego.add.image(600, 470, colorBando);
    depositoDeCombustibleAliadoLateral.setDepth(100);
    depositoDeCombustibleAliadoLateral.scaleX = 1.5;
    depositoDeCombustibleAliadoLateral.scaleY = 1.5;


    pistaDeAvionAliadaLateral.setVisible(false);
    depositoDeBombasAliadoLateral.setVisible(false);
    torreControlAliadaLateral.setVisible(false);
    depositoDeCombustibleAliadoLateral.setVisible(false);
}

function reanudarJuego ()
{
	if (pausar)
	{
		pausar = false;
		/* sonidoReanudar.play();
		opvReanudar();	
		musicaFondo.play();*/
		separacionVertical.setVisible(false);
		pisoLateral.setVisible(false);
        avionActivaAliadaLateral.setVisible(false);
        avionActivaEnemigaLateral.setVisible(false);

        pistaDeAvionAliadaLateral.setVisible(false);
        depositoDeBombasAliadoLateral.setVisible(false);
        torreControlAliadaLateral.setVisible(false);
        depositoDeCombustibleAliadoLateral.setVisible(false);

	}
	if (!pausar){
		// ALGO
	
	}	 
	
}
function pausarJuego ()
{
	if (!pausar){
    
        pausar = true;
        /// sonidoPausa.play(); --> REPORDUZCO SONIDO
        // opvPausa(); --> SEND JSON
    }
	if (pausar){
            // DETENGO CUALQUIER SONIDO QUE ESTE CORRIENDO EN EL JUEGO
			// DETENGO MUSICA DE FONDO --> musicaFondo.stop();
			
            // AVISO EN PANTALLA
            tableroAvion.setText([
                'PARTIDA PAUSADA ',
            ]);
            //Muestra Vista Lateral
            if (avionAliada_Activa.y > campoEnemigo_h) {
                avionActivaAliadaLateral.setVisible(true);
                switch (avionAliada_Activa.z){
                    case 0:
                        avionActivaAliadaLateral.y=(campoAliado_h+campoEnemigo_h)-350;
                        avionActivaAliadaLateral.scaleX=0.8;
                        avionActivaAliadaLateral.scaleY=0.8;
                        break;
                    case 100:
                        avionActivaAliadaLateral.y=(campoAliado_h+campoEnemigo_h)-450;
                        avionActivaAliadaLateral.scaleX=0.5;
                        avionActivaAliadaLateral.scaleY=0.5;
                        break;
                    case 200:
                        avionActivaAliadaLateral.y=(campoAliado_h+campoEnemigo_h)-550;
                        avionActivaAliadaLateral.scaleX=0.3;
                        avionActivaAliadaLateral.scaleY=0.3;
                        break;
                }
                y = avionAliada_Activa.y;
                x = dadoVistaAereaY_obtengoUnXParaLateral(y);
                avionActivaAliadaLateral.x = x;


            }
            if (avionEnemiga_Activa.y > campoEnemigo_h) {
                avionActivaEnemigaLateral.setVisible(true);

                switch (avionEnemiga_Activa.z){
                    case 0:
                        avionActivaEnemigaLateral.y=(campoAliado_h+campoEnemigo_h)-350;
                        avionActivaEnemigaLateral.scaleX=0.8;
                        avionActivaEnemigaLateral.scaleY=0.8;
                        break;
                    case 100:
                        avionActivaEnemigaLateral.y=(campoAliado_h+campoEnemigo_h)-450;
                        avionActivaEnemigaLateral.scaleX=0.5;
                        avionActivaEnemigaLateral.scaleY=0.5;
                        break;
                    case 200:
                        avionActivaEnemigaLateral.y=(campoAliado_h+campoEnemigo_h)-550;
                        avionActivaEnemigaLateral.scaleX=0.3;
                        avionActivaEnemigaLateral.scaleY=0.3;
                        break;
                }
                y = avionEnemiga_Activa.y;
                x = dadoVistaAereaY_obtengoUnXParaLateral(y);
                avionActivaEnemigaLateral.x = x;

            }


			pisoLateral.setVisible(true);
            separacionVertical.setVisible(true);

            pistaDeAvionAliadaLateral.setVisible(true);
            depositoDeBombasAliadoLateral.setVisible(true);
            torreControlAliadaLateral.setVisible(true);
            depositoDeCombustibleAliadoLateral.setVisible(true);
            
            var sgm_pantalla = (campoAliado_h+campoEnemigo_h)/3;
	
            if (torreControlAliada.hayEnemigo) {
                if (jugadorMiNumero == 1) {
                    colorBando = 'torreControl_VLateral_negra_activa';
                } else {
                    colorBando = 'torreControl_VLateral_roja_activa';
                }
                torreControlAliadaLateral.setTexture(colorBando);
            }else{
                if (jugadorMiNumero == 1) {
                    colorBando = 'torreControl_VLateral_negra';
                } else {
                    colorBando = 'torreControl_VLateral_roja';
                }
                torreControlAliadaLateral.setTexture(colorBando);
            }


	}else{
        pisoLateral.setVisible(false);
    }
}

function dadoVistaAereaY_obtengoUnXParaLateral(vistaAereaY){
    var vistaLateralX=100;

    ////vista aerea va entre 300 y 600, o sea, mi 100% es 300
    var xABuscarEnPrimeraReglaDe3 = 0;
    var valorEntrante = (vistaAereaY-300);    
    // x% --- 100%
    // val --- 300px
    ////aplicao regla de 3...
    xABuscarEnPrimeraReglaDe3 = (valorEntrante*100) / 300


    ////vista lateral va desde 100 a 900 (valor absoluto de 800),
    //// o sea mi 100% es 800
    var xEntranteQueTengoDeAntes = xABuscarEnPrimeraReglaDe3;
    // x% de arriba ---- 100%
    // y en PX      ---- 800px
    ////aplico segunda regla de 3
    var valorEnPXLateralesAntesDeSalir = (xEntranteQueTengoDeAntes*800) / 100;
    vistaLateralX = valorEnPXLateralesAntesDeSalir+100;

    return vistaLateralX;
}
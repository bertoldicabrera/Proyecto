/////----------------version del archivo numero::  FrontEnd_respaldo_2021 04 08a  ---- ultimo modificador:: Joaquin--- con intento de espejado ---



/////-----------------INICIO VARIABLES GLOBALES--------------------
var sceneJuego;

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
var avionEnemiga_Activa;

var avionA_1;var avionA_2;var avionA_3;var avionA_4;
var avionE_1, avionE_2, avionE_3, avionE_4;

var artilleroA_1;var artilleroA_2;var artilleroA_3;var artilleroA_4;var artilleroA_5;var artilleroA_6;

var artilleroE_1;var artilleroE_2;var artilleroE_3;var artilleroE_4;var artilleroE_5;var artilleroE_6;

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
var avionActivaUltimaVY = 0;

var modificoDireccion = false;

var avionActivaAliadaX = 0;
var avionActivaAliadaY = 0;

var isAvionActivaAliadaViva = true;
var isAvionActivaEnemigaViva = true;

var tiempoDelUpdate = 0;

var imagenAvionesAliados;
var imagenAvionesEnemigos;

var isContinuarUpdate;


////CONDICIONES DE FIN DE PARTIDA
var condicionPerdidaAliado = false;
var condicionPerdidaEnemigo = false;
var mostrarAlert = true;


var superOne, isSuperAlive = false, cantSuper = 0;



var in_avionEnemigaActivaX, in_avionEnemigaActivaY;

var isTrajoDatosEnemigos = false;
var in_avionEnemiga_ActivaVx;
var in_avionEnemiga_ActivaVy;
var avionEnemiga_ActivaVx;
var avionEnemiga_ActivaVy;
var avionAliada_ActivaVx;
var avionAliada_ActivaVy;

var isEnvioMisDatosBase = true;

/////---------^^^^^^^^^^^^FIN VARIABLES GLOBALES^^^^^^^^^^^^----------


/////----------------------------------------------------------------------------------
/////----------------------------------------------------------------------------------
/////----------------------------------------------------------------------------------
////CREACION WEBSOCKET
var webSocket = new WebSocket("ws://localhost:8080/prueba/webSocketEndPointPartida");
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

var datosASync;

///// METODOS WEBSOCKETS
webSocket.onopen = function(event) {
    /* var numRandomAux = Math.round(Math.random() * 10);
    if (numRandomAux > 5) {
        jugadorMiNumero = 1;
    } else {
        jugadorMiNumero = 2;
    }
     */

    jugadorMiNumero = 2;
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

    dataJson = JSON.stringify(rows);
    if (isWSOpen) {
        webSocket.send(dataJson);
    }
};

/* webSocket.onclose = function(event) {
    webSocket.send("dato a enviar de cierre del websocket");
    isWSOpen = "false";

    console.log("WebSocket is closed now.");
    console.log('Onclose called' + event);
    console.log('code is' + event.code);
    console.log('reason is ' + event.reason);
    console.log('wasClean  is' + event.wasClean);
}; */

/* webSocket.onerror = function(event) {
    alert("Error con el WebSocket ***Verifique que el server este prendido: " + event, event );
};  */

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
    console.log("-------function onMessage(event)---------------------------");
    ////todos los datos que me manda tanto el server por primera vez, 
    //// como todo el resto de datos que me manda el enemigo para actualizar sus objetos y eventos
    
    console.log("event.data:"+event.data);
    var data = JSON.parse(event.data);
    console.log("data:"+data);

    datosRecibidosDesdeJugadorNumero = data.enviaDatoDesdeElJugadorNum;
    console.log("datosRecibidosDesdeJugadorNumero: "+datosRecibidosDesdeJugadorNumero);

    if (datosRecibidosDesdeJugadorNumero == jugadorMiNumero) {
 
 
    } else {

        datosASync = data.datos;
        console.log("etiqueta:: datosASync: "+datosASync);


        switch (datosASync) {
            case "datosBaseYArtillerosPriVez":
                sceneJuego.deposito_bombasAliado.x = data.deposito_bombasAliadoX;
                sceneJuego.deposito_bombasAliado.y = data.deposito_bombasAliadoY;
                
                sceneJuego.torreControlAliada.x = data.torreControlAliadaX;
                sceneJuego.torreControlAliada.y = data.torreControlAliadaY;
              
                sceneJuego.deposito_combustibleAliado.x = data.deposito_combustibleAliadoX;
                sceneJuego.deposito_combustibleAliado.y = data.deposito_combustibleAliadoY;
               
                sceneJuego.pistaAvionesAliada.x = data.pistaAvionesAliadaX;
                sceneJuego.pistaAvionesAliada.y = data.pistaAvionesAliadaY;
            
                avionAliada_Activa.x = sceneJuego.pistaAvionesAliada.x;
                avionAliada_Activa.y = sceneJuego.pistaAvionesAliada.y;

                artilleroA_1.x = data.datosArtilleros1x;
                artilleroA_1.y = data.datosArtilleros1y;
                artilleroA_2.x = data.datosArtilleros2x;
                artilleroA_2.y = data.datosArtilleros2y;
                artilleroA_3.x = data.datosArtilleros3x;
                artilleroA_3.y = data.datosArtilleros3y;
                artilleroA_4.x = data.datosArtilleros4x;
                artilleroA_4.y = data.datosArtilleros4y;
                artilleroA_5.x = data.datosArtilleros5x;
                artilleroA_5.y = data.datosArtilleros5y;
                artilleroA_6.x = data.datosArtilleros6x;
                artilleroA_6.y = data.datosArtilleros6y;
                
                break;
            case "datosArtillerosByTimer":
                artilleroA_1.x = data.datosArtilleros1x;
                artilleroA_1.y = data.datosArtilleros1y;
                artilleroA_2.x = data.datosArtilleros2x;
                artilleroA_2.y = data.datosArtilleros2y;
                artilleroA_3.x = data.datosArtilleros3x;
                artilleroA_3.y = data.datosArtilleros3y;
                artilleroA_4.x = data.datosArtilleros4x;
                artilleroA_4.y = data.datosArtilleros4y;
                artilleroA_5.x = data.datosArtilleros5x;
                artilleroA_5.y = data.datosArtilleros5y;
                artilleroA_6.x = data.datosArtilleros6x;
                artilleroA_6.y = data.datosArtilleros6y;
                break;
    
            case "datosArtilleroByOverlap":     
                x = data.datosArtilleroX;
                y = data.datosArtilleroY;  
                switch (data.datosArtilleroNum) {
                    case 1:
                        artilleroA_1.x = x;
                        artilleroA_1.y = y;
                        break;
                    case 2:
                        artilleroA_2.x = x;
                        artilleroA_2.y = y;
                        break;
                    case 3:
                        artilleroA_3.x = x;
                        artilleroA_3.y = y;
                        break;
                    case 4:
                        artilleroA_4.x = x;
                        artilleroA_4.y = y;
                        break;
                    case 5:
                        artilleroA_5.x = x;
                        artilleroA_5.y = y;
                        break;
                    case 6:
                        artilleroA_6.x = x;
                        artilleroA_6.y = y;
                        break;
                }
                

            break;
            
            case "datosAvion":
                
                break;
            case "datosEvento1":
                
                break;
        }


   
    }


 }
 
function sendDatosWebSocket(){
    if (isEnvioMisDatosBase) {
        console.log("entro a isEnvioMisDatosBase");

        isEnvioMisDatosBase = false;

        rows =
        { "partidaId": partidaID
        , "isIngresoPorPrimeraVez": isIngresoPorPrimeraVez
        , "sessionId": mi_SessId
        , "enviaDatoDesdeElJugadorNum": jugadorMiNumero


        , "cabezalDatosPrincipalesEnemigos": true

        , "deposito_bombasAliadoX": sceneJuego.deposito_bombasEnemigo.x
        , "deposito_bombasAliadoY": sceneJuego.deposito_bombasEnemigo.y

        , "torreControlAliadaX": sceneJuego.torreControlEnemiga.x
        , "torreControlAliadaY": sceneJuego.torreControlEnemiga.y

        , "deposito_combustibleAliadoX": sceneJuego.deposito_combustibleEnemigo.x
        , "deposito_combustibleAliadoY": sceneJuego.deposito_combustibleEnemigo.y

        , "pistaAvionesAliadaX": sceneJuego.pistaAvionesEnemiga.x
        , "pistaAvionesAliadaY": sceneJuego.pistaAvionesEnemiga.y
                
        //artilleroA_1

        };
        dataJson = JSON.stringify(rows);

        console.log("isEnvioMisDatosBase dataJson::" + dataJson);

        if (isWSOpen) {
            webSocket.send(dataJson);
        }
  
        
    }else{
        ///

        
    }

}

/*  ////EJEMPLO DE COMO HACER UN SEND AL WebSocket
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

    if (isWSOpen) {
        webSocket.send(dataJson);
    }
 
 */

/////----------------------------------------------------------------------------------
/////----------------------------------------------------------------------------------
/////----------------------------------------------------------------------------------






export default class s7_campoBatalla2 extends Phaser.Scene {
    constructor() {
        super({key: "s7_campoBatalla2", active: true});
    }
    preload() {
        /* alert(juego_var_nav_width   +"  ---x---   "+juego_var_nav_height); */
        campoAliado_x      = (origen_x); 
        campoAliado_y      = (origen_y); 
        campoAliado_w      = (juego_var_nav_width);  
        campoAliado_h      = (juego_var_nav_height/2); 

        lineaSeparacion_x   = (origen_x); 
        lineaSeparacion_y   = (campoAliado_h - (juego_var_nav_height*0.10) ); 
        lineaSeparacion_w   = (juego_var_nav_width); 
        lineaSeparacion_h   = (juego_var_nav_height*0.20); 
 
        campoEnemigo_x       = (origen_x); 
        campoEnemigo_y       = (campoAliado_h); 
        campoEnemigo_w       = (juego_var_nav_width); 
        campoEnemigo_h       = (juego_var_nav_height/2);

        rangoVisionAvion        = juego_var_rangoVisionAvion;
        rangoVisionArtillero    = juego_var_rangoVisionArtillero;
        rangoVisionTorre        = juego_var_rangoVisionTorre;

        ////------------IMAGENES INICIO----------------------------------
        this.load.image("piso","./assets/images/texturas/campoDeBatallaPiso.png");
        
        this.load.image("separacion","./assets/images/texturas/separacion.png");
        
        imagenAvionesAliados    = this.load.image("avionNegro","./assets/images/objetos/avionNegro.png");
        imagenAvionesEnemigos   = this.load.image("avionRojo","./assets/images/objetos/avionRojo.png");

        this.load.image("deposito_combustible_negro","./assets/images/objetos/deposito_combustible_negro.png");
        this.load.image("torreControl_negro","./assets/images/objetos/torreControl_negro.png");
        this.load.image("deposito_bombas_negro","./assets/images/objetos/deposito_bombas_negro.png");
        this.load.image("pistaAviones_negro","./assets/images/objetos/pistaAviones_negro.png");

        this.load.image("torreControl_negro_activo","./assets/images/objetos/torreControl_negro_activo.png");

        this.load.image("deposito_combustible_rojo","./assets/images/objetos/deposito_combustible_rojo.png");
        this.load.image("torreControl_rojo","./assets/images/objetos/torreControl_rojo.png");
        this.load.image("deposito_bombas_rojo","./assets/images/objetos/deposito_bombas_rojo.png");
        this.load.image("pistaAviones_rojo","./assets/images/objetos/pistaAviones_rojo.png");

        this.load.image("torreControl_rojo_activo","./assets/images/objetos/torreControl_rojo_activo.png");
        
        this.load.image("artillero_negro","./assets/images/objetos/artillero_negro.png");
        this.load.image("artillero_rojo","./assets/images/objetos/artillero_rojo.png");

        this.load.image("artillero_negro_activo","./assets/images/objetos/artillero_negro_activo.png");
        this.load.image("artillero_rojo_activo","./assets/images/objetos/artillero_rojo_activo.png");

        /// IMAGENES PARA VISTA LATERAL
        this.load.image("pisoLateral","./assets/images/texturas/pisoLateral.png");
        this.load.image("avionNegroLateral","./assets/images/objetos/airplane_3.png");
        this.load.image("avionNegroLateralActivo","./assets/images/objetos/airplane_3.png");
        this.load.image("avionRojoLateral","./assets/images/objetos/airplane_3.png");
        this.load.image("avionRojoLateralActivo","./assets/images/objetos/airplane_3.png");
       
        this.load.image("torreControl_rojoLateral","./assets/images/objetos/torreControl_VLateral_roja_activa.png");
        this.load.image("torreControl_rojoLateralActivo","./assets/images/objetos/torreControl_VLateral_roja_activa.png");
        this.load.image("torreControl_negroLateral","./assets/images/objetos/torreControl_VLateral_negra.png");
        this.load.image("torreControl_negroLateralActivo","./assets/images/objetos/torreControl_VLateral_negra_activa.png");
        //balas
        this.load.image("bala", "./assets/images/objetos/bala.png");

        // EXPLOSION

        this.load.spritesheet('explosion', './assets/images/efectos/explosion2.png', { frameWidth: 64, frameHeight: 64, endFrame: 23 });
        //bombas

        this.load.image('mask', './assets/images/mapaCapaOculta/mask1.png');
        this.load.image('maskb', './assets/images/mapaCapaOculta/mask1b.png');
        this.load.image('nada', './assets/images/mapaCapaOculta/nada.png');

        ////------------IMAGENES FIN----------------------------------
        


        ////------------SPRITESHEET INICIO----------------------------------
        
        ////------------SPRITESHEET FIN----------------------------------
        


        ////------------MUSICA y SONIDOS INICIO----------------------------------
        
        this.load.audio('snd_disparo_metralleta', "./assets/sounds/metralleta.m4a");
        this.load.audio('snd_impacto_metralleta', "./assets/sounds/impactoMetralleta.m4a");
        this.load.audio('sonidoexplosion', './assets/sounds/explosion.m4a');
        this.load.audio('caidaYExplosionBomba', './assets/sounds/caidaYExplosionBomba.m4a');

        ///PONER IF PARA QUE CARGUE UNA U OTRA
/*         this.load.audio('musicaFondo1', './assets/sounds/musicaFondo1.m4a');
        this.load.audio('musicaFondo2', './assets/sounds/musicaFondo2.m4a');
        this.load.audio('musicaFondo3', './assets/sounds/musicaFondo3.m4a'); */
        ////------------MUSICA y SONIDOS FIN----------------------------------

        this.load.image("superOne","./assets/images/objetos/superOne.png");

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
            elemento_w          = 50; 
            elemento_espacio    = 20;    
        
            base_inicioRandom_x = Phaser.Math.Between(origen_x+20,700); 
            //base_inicioRandom_x = base_inicioRandom_x;

            baseEnemiga_x = base_inicioRandom_x; 
            //baseEnemiga_y = Math.round( juego_var_nav_height*0.80 ); 
            baseEnemiga_y = Math.round( juego_var_nav_height*0.03 );//Math.round( origen_y*0.80 ); 
            baseEnemiga_w = ((elemento_w*3)+(elemento_espacio*2));//campoAliado_w*0.50; 
            baseEnemiga_h = 100; 

            /* let graphics3 = this.add.graphics();
            graphics3.fillStyle(0x326ba8, 1);
            graphics3.fillRect(baseEnemiga_x, baseEnemiga_y, baseEnemiga_w ,baseEnemiga_h);
              */

            ////AJUSTE POR MAL ORIGIN DE IMAGENES Y PARA OBTENER BIEN LAS COORDENADAS EN BASE A LA IMAGEN
            baseEnemiga_x = baseEnemiga_x + (elemento_w/2); 
            baseEnemiga_y = baseEnemiga_y + (elemento_w/2); 
/*             baseEnemiga_w = ((elemento_w*3)+(elemento_espacio*2));//campoAliado_w*0.50; 
            baseEnemiga_h = 100; */
           
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

            }else{
                /////IF ES UNA PARTIDA CARGADA DE LAS GUARDADAS.... IGUALAR TODOS LOS DATOS DE 
                ////datos from json
            }
            this.deposito_combustibleEnemigo = this.physics.add.image(baseEnemiga_x, baseEnemiga_y, 'deposito_combustible_rojo');//.setDisplayOrigin(0, 0);
            this.deposito_combustibleEnemigo.setCollideWorldBounds(true);
            this.deposito_combustibleEnemigo.setBounce(0);
            /* var elemento_w = this.deposito_combustibleAliado.width;
            var elemento_espacio = 20; */
            this.deposito_combustibleEnemigo.vida = 100;
            this.deposito_combustibleEnemigo.physicsBodyType = Phaser.Physics.ARCADE;;
            Gpo_ElementosBaseEnemiga.add(this.deposito_combustibleEnemigo);
       
            ////CREACION DE torreControl
            if (juego_var_isPartidaNueva) {

            }else{
                /////IF ES UNA PARTIDA CARGADA DE LAS GUARDADAS.... IGUALAR TODOS LOS DATOS DE 
                ////datos from json
            }
            this.torreControlEnemiga = this.physics.add.image( (baseEnemiga_x+elemento_w+elemento_espacio), baseEnemiga_y, 'torreControl_rojo');//.setDisplayOrigin(0, 0);
            this.torreControlEnemiga.setCollideWorldBounds(true);
            this.torreControlEnemiga.setBounce(0);
            this.torreControlEnemiga.vida = 100;
            this.torreControlEnemiga.hayEnemigo = false;
            this.torreControlEnemiga.rangoVision = rangoVisionTorre;
            this.torreControlEnemiga.lastFiredTorre = 0;
            this.torreControlEnemiga.bullet_torre_Enemiga = this.physics.add.group({
                classType: Bullet,
                maxSize: 3,
                runChildUpdate: true
            });
            this.torreControlEnemiga.bullet_torre_Enemiga.physicsBodyType = Phaser.Physics.ARCADE;
            this.torreControlEnemiga.setImmovable();
            this.torreControlEnemiga.bando = "Enemiga";
            this.torreControlEnemiga.physicsBodyType = Phaser.Physics.ARCADE;
            Gpo_ElementosBaseEnemiga.add(this.torreControlEnemiga);

            ////CREACION DE deposito_bombasAliado
            if (juego_var_isPartidaNueva) {

            }else{
                /////IF ES UNA PARTIDA CARGADA DE LAS GUARDADAS.... IGUALAR TODOS LOS DATOS DE 
                ////datos from json
            }
            this.deposito_bombasEnemigo = this.physics.add.image( (baseEnemiga_x+(elemento_w*2)+(elemento_espacio*2)), baseEnemiga_y, 'deposito_bombas_rojo');//.setDisplayOrigin(0, 0);
            this.deposito_bombasEnemigo.setCollideWorldBounds(true);
            this.deposito_bombasEnemigo.setBounce(0);
            this.deposito_bombasEnemigo.vida = 100;
            this.deposito_bombasEnemigo.setImmovable();
            this.deposito_bombasEnemigo.physicsBodyType = Phaser.Physics.ARCADE;
            Gpo_ElementosBaseEnemiga.add(this.deposito_bombasEnemigo);
 

            ////CREACION DE pistaAvionesAliada
            if (juego_var_isPartidaNueva) {

            }else{
                /////IF ES UNA PARTIDA CARGADA DE LAS GUARDADAS.... IGUALAR TODOS LOS DATOS DE 
                ////datos from json
            }
            this.pistaAvionesEnemiga = this.physics.add.image( (baseEnemiga_x+elemento_w+elemento_espacio), (baseEnemiga_y+(elemento_w/2)+(elemento_espacio*2)) , 'pistaAviones_rojo');     /////    baseEnemiga_x, (baseEnemiga_y+elemento_w+elemento_espacio), 'pistaAviones_negro').setDisplayOrigin(0, 0);
            this.pistaAvionesEnemiga.setCollideWorldBounds(true);
            this.pistaAvionesEnemiga.setBounce(0);
            this.pistaAvionesEnemiga.setImmovable();
            this.pistaAvionesEnemiga.physicsBodyType = Phaser.Physics.ARCADE;
            Gpo_ElementosBaseEnemiga.add(this.pistaAvionesEnemiga);

            ////CREACION DE Artilleros Aliados
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
        /////----------^^^^^^^^^^^^CREACION DE MI BASE^^^^^^^^^^^-------------


        /////------------------CREACION DE BASE ALIADA::--------------
        elemento_w          = 50;//this.deposito_combustibleEnemigo.width;
        elemento_espacio    = 20;    
    
        base_inicioRandom_x = Phaser.Math.Between(origen_x+20,700); 
        base_inicioRandom_x = base_inicioRandom_x;

        baseAliada_x = base_inicioRandom_x; 
        baseAliada_y = Math.round( juego_var_nav_height*0.80 ); //Math.round( juego_var_nav_height*0.03 ); 
        baseAliada_w = ((elemento_w*3)+(elemento_espacio*2));//campoEnemigo_w*0.50; 
        baseAliada_h = 100; 

     /*    let graphics4 = this.add.graphics();
        graphics4.fillStyle(0xfc6203, 1);
        graphics4.fillRect(baseEnemiga_x, baseEnemiga_y, baseEnemiga_w ,baseEnemiga_h);
         */

        ////AJUSTE POR MAL ORIGIN DE IMAGENES Y PARA OBTENER BIEN LAS COORDENADAS EN BASE A LA IMAGEN
        baseAliada_x = baseAliada_x + (elemento_w/2); 
        baseAliada_y = baseAliada_y + (elemento_w/2); 
/*             baseEnemiga_w = ((elemento_w*3)+(elemento_espacio*2));//campoEnemigo_w*0.50; 
        baseEnemiga_h = 100; */
        
                        //completar enemigo
                        //cantidadArtillerosEnemigas += 1;
                        //arrayArtillerosEnemigos.push(this.artilleroE_1);

        ////CREACION DE pistaAvionesEnemiga
        if (juego_var_isPartidaNueva) {

        }else{
            /////IF ES UNA PARTIDA CARGADA DE LAS GUARDADAS.... IGUALAR TODOS LOS DATOS DE 
            ////datos from json
        }
        this.pistaAvionesAliada = this.physics.add.image((baseAliada_x+elemento_w+elemento_espacio), (baseAliada_y-elemento_espacio/2), 'pistaAviones_negro');    
        this.pistaAvionesAliada.setCollideWorldBounds(true);
        this.pistaAvionesAliada.setBounce(0);
        this.pistaAvionesAliada.setImmovable();
        this.pistaAvionesAliada.physicsBodyType = Phaser.Physics.ARCADE;
        ///////Gpo_ElementosBaseEnemiga.add(this.pistaAvionesEnemiga);

        ////CREACION DE deposito_bombasEnemigo
        if (juego_var_isPartidaNueva) {

        }else{
            /////IF ES UNA PARTIDA CARGADA DE LAS GUARDADAS.... IGUALAR TODOS LOS DATOS DE 
            ////datos from json
        }
        this.deposito_bombasAliado = this.physics.add.image( (baseAliada_x), (baseAliada_y+(elemento_espacio*2.5)), 'deposito_bombas_negro');//.setDisplayOrigin(0, 0);
        this.deposito_bombasAliado.setCollideWorldBounds(true);
        this.deposito_bombasAliado.setBounce(0);
        this.deposito_bombasAliado.vida = 100;
        this.deposito_bombasAliado.setImmovable();
        this.deposito_bombasAliado.physicsBodyType = Phaser.Physics.ARCADE;
        Gpo_ElementosBaseAliada.add(this.deposito_bombasAliado);


        ////CREACION DE torreControl
        if (juego_var_isPartidaNueva) {

        }else{
            /////IF ES UNA PARTIDA CARGADA DE LAS GUARDADAS.... IGUALAR TODOS LOS DATOS DE 
            ////datos from json
        }
        this.torreControlAliada = this.physics.add.image( (baseAliada_x+elemento_espacio*3.5), (baseAliada_y+(elemento_espacio*2.5)), 'torreControl_negro');//.setDisplayOrigin(0, 0);
        this.torreControlAliada.setCollideWorldBounds(true);
        this.torreControlAliada.setBounce(0);
        this.torreControlAliada.vida = 100;
        this.torreControlAliada.hayEnemigo = false;
        this.torreControlAliada.rangoVision = rangoVisionTorre;
        this.torreControlAliada.lastFiredTorre = 0;
        this.torreControlAliada.bullet_torre_Aliada = this.physics.add.group({
            classType: Bullet,
            maxSize: 3,
            runChildUpdate: true
        });
        this.torreControlAliada.bullet_torre_Aliada.physicsBodyType = Phaser.Physics.ARCADE;
        this.torreControlAliada.setImmovable();
        this.torreControlAliada.bando = "Aliada";
        this.torreControlAliada.physicsBodyType = Phaser.Physics.ARCADE;
        Gpo_ElementosBaseAliada.add(this.torreControlAliada);

        ////CREACION DE deposito_combustibleEnemigo
        if (juego_var_isPartidaNueva) {

        }else{
            /////IF ES UNA PARTIDA CARGADA DE LAS GUARDADAS.... IGUALAR TODOS LOS DATOS DE 
            ////datos from json
        }
        this.deposito_combustibleAliado = this.physics.add.image((baseAliada_x+elemento_espacio*7), (baseAliada_y+(elemento_espacio*2.5)), 'deposito_combustible_negro');//.setDisplayOrigin(0, 0);
        this.deposito_combustibleAliado.setCollideWorldBounds(true);
        this.deposito_combustibleAliado.setBounce(0);
        // var elemento_w = this.deposito_combustibleAliado.width;
        //var elemento_espacio = 20; 
        this.deposito_combustibleAliado.vida = 100;
        this.deposito_combustibleAliado.physicsBodyType = Phaser.Physics.ARCADE;;
        Gpo_ElementosBaseAliada.add(this.deposito_combustibleAliado);


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



        /////----------^^^^^^^^^^^^CREACION DE BASE ENEMIGA^^^^^^^^^^^-------------

        
        /////----------------Inicio de CREACION DE AVIONES :: aviones ::--------------
        if (juego_var_isPartidaNueva) {
            avionE_1= new Avion("Enemigo",1); Gpo_AvionesEnemigos.add(avionE_1);
            avionE_2= new Avion("Enemigo",2); Gpo_AvionesEnemigos.add(avionE_2);
            avionE_3= new Avion("Enemigo",3); Gpo_AvionesEnemigos.add(avionE_3);
            avionE_4= new Avion("Enemigo",4); Gpo_AvionesEnemigos.add(avionE_4);

            avionA_1= new Avion("Aliado",1); Gpo_AvionesAliados.add(avionA_1);
            avionA_2= new Avion("Aliado",2); Gpo_AvionesAliados.add(avionA_2);
            avionA_3= new Avion("Aliado",3); Gpo_AvionesAliados.add(avionA_3);
            avionA_4= new Avion("Aliado",4); Gpo_AvionesAliados.add(avionA_4);
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
        avionEnemiga_Activa = getProximoAvionActivo(Gpo_AvionesEnemigos);
        avionAliada_Activa = getProximoAvionActivo(Gpo_AvionesAliados);
        /////----------^^^^^^^^^^^Fin de CREACION DE AVIONES :: aviones ^^^^^^^^^^-------------


//// ME QUEDE ACA!!!


        ////CREACION DE MASCARA 
        spotlight = this.make.sprite({
            x: 0,
            y: 0,
            key: 'mask',
            add: false,
        });
        spotlight_instance = new Phaser.Display.Masks.BitmapMask(this, spotlight);

        /// APLICO SOMBRA A AQUELLO QUE QUIERA ENMASCARAR: ARTILLEROS, ELEMENTOS DE BASE+AVIONES

/*             avionAliada_Activa.mask = spotlight_instance;
            this.pisoEnemigo.mask = spotlight_instance;
            artilleroA_1.mask = spotlight_instance;
            artilleroA_2.mask = spotlight_instance;
            artilleroA_3.mask = spotlight_instance;
            artilleroA_4.mask = spotlight_instance;
            artilleroA_5.mask = spotlight_instance;
            artilleroA_6.mask = spotlight_instance;
            this.torreControlAliada.mask = spotlight_instance;
            this.pistaAvionesAliada.mask = spotlight_instance;
            this.deposito_bombasAliado.mask = spotlight_instance;
            this.deposito_combustibleAliado.mask = spotlight_instance;
  */

        ///aplico a los elementos enemigos


        ////CREACION SEPARACION DE CAMPOS
        this.separacionCampos = this.physics.add.image(lineaSeparacion_x, lineaSeparacion_y-50, "separacion").setDisplayOrigin(0, 0);
        this.separacionCampos.displayWidth = lineaSeparacion_w;
        this.separacionCampos.displayHeight = lineaSeparacion_h+100; 
        this.separacionCampos.setDepth(50);
        //console.log("x:"+lineaSeparacion_x+" y: "+lineaSeparacion_y);
        //console.log("x:"+lineaSeparacion_w+" y: "+lineaSeparacion_h);

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
       // this.physics.add.overlap(Gpo_ArtillerosAliados, Gpo_ArtillerosAliados, overlapEvent_artilleroOnCollide, null, this);
        //this.physics.add.overlap(Gpo_ArtillerosAliados, Gpo_ElementosBaseAliada, overlapEvent_artilleroOnCollide, null, this);
        this.physics.add.overlap(Gpo_ArtillerosEnemigos, Gpo_ArtillerosEnemigos, overlapEvent_artilleroOnCollide, null, this);
        this.physics.add.overlap(Gpo_ArtillerosEnemigos, Gpo_ElementosBaseEnemiga, overlapEvent_artilleroOnCollide, null, this);

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
        this.physics.add.overlap(this.torreControlAliada.bullet_torre_Aliada, Gpo_AvionesEnemigos, overlapEvent_impactoBalaEnAvionE, null, this);
        // DISPARO DESDE TORRE ENEMIGA
        this.physics.add.overlap(this.torreControlEnemiga.bullet_torre_Enemiga, Gpo_AvionesAliados, overlapEvent_impactoBalaEnAvionA, null, this);

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
            args: [Gpo_ArtillerosEnemigos.getChildren()],
            callbackScope: Gpo_ArtillerosEnemigos,
            loop: true,
        });

        isContinuarUpdate = true;

        
        /// VISTA LATERAL CARGO IMAGENES CORRESPONDIENTES

        cargaElementosVistaLateral();
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
            getUnidadDesplazamiento(avionEnemiga_Activa);

            if(this.right.isDown || this.left.isDown || this.up.isDown || this.down.isDown){
                avionEnemiga_Activa.enPista = false;

                var vX, vY;
                var diagonal=false;
                if(this.right.isDown) {
                    ultimaTeclaPresionada = "right";
                    avionEnemiga_Activa.x = avionEnemiga_Activa.x + avionEnemiga_Activa.unidadDeVelocidad;//++;
                    avionEnemiga_Activa.resetFlip();
                    if (this.down.isDown){
                        // avionAliada_Activa.x = avionAliada_Activa.x + unidadDeVelocidad;//++; 
                        avionEnemiga_Activa.y = avionEnemiga_Activa.y + avionEnemiga_Activa.unidadDeVelocidad;//++;
                        avionEnemiga_Activa.angle=45;
                        vX = 10;vY = 10;
                    }else{
                        if (this.up.isDown){
                            // avionAliada_Activa.x = avionAliada_Activa.x + unidadDeVelocidad;//++; 
                            avionEnemiga_Activa.y = avionEnemiga_Activa.y - avionEnemiga_Activa.unidadDeVelocidad;//--;
                            avionEnemiga_Activa.angle=-45;
                            vX = 10;vY = -10;
                        }else{avionEnemiga_Activa.angle=0;diagonal=true;vX = 10;vY = 0;}
                    ;}                
                } else if(this.left.isDown) {
                    ultimaTeclaPresionada = "left";
                    avionEnemiga_Activa.x = avionEnemiga_Activa.x - avionEnemiga_Activa.unidadDeVelocidad;//--;
                    avionEnemiga_Activa.resetFlip();
                    avionEnemiga_Activa.flipX=true;
                    if (this.down.isDown){
                        // avionAliada_Activa.x = avionAliada_Activa.x - unidadDeVelocidad;//--; 
                        avionEnemiga_Activa.y = avionEnemiga_Activa.y + avionEnemiga_Activa.unidadDeVelocidad;//++;
                        avionEnemiga_Activa.angle=-45;
                        vX = -10;vY = 10;
                    }else{
                        if (this.up.isDown){
                            // avionAliada_Activa.x = avionAliada_Activa.x - unidadDeVelocidad;//--; 
                            avionEnemiga_Activa.y = avionEnemiga_Activa.y - avionEnemiga_Activa.unidadDeVelocidad;//--;
                            avionEnemiga_Activa.angle=45;
                            vX = -10;vY = -10;
                        }else{avionEnemiga_Activa.angle=0;diagonal=true; vX = -10;vY = 0;}
                    ;}           
                } else if(this.up.isDown) {
                    ultimaTeclaPresionada = "up";
                    avionEnemiga_Activa.y = avionEnemiga_Activa.y - avionEnemiga_Activa.unidadDeVelocidad;//--;
                    avionEnemiga_Activa.resetFlip();
                    avionEnemiga_Activa.angle=-90;
                    vX = 0;
                    vY = -10;
                } else if(this.down.isDown) {
                    ultimaTeclaPresionada = "down";
                    avionEnemiga_Activa.y = avionEnemiga_Activa.y + avionEnemiga_Activa.unidadDeVelocidad;//++;
                    avionEnemiga_Activa.resetFlip();
                    avionEnemiga_Activa.angle=90;
                    vX = 0;
                    vY = 10;
                }
                
                avionEnemiga_ActivaVx = vX;
                avionEnemiga_ActivaVy = vY;

                setVelocidadAvion(avionEnemiga_Activa, vX, vY);
                modificoDireccion = true;
                /* var cursors = this.input.keyboard.createCursorKeys();
                var duration = this.cursors.getDuration();
                console.log(this.duration); */
                
               /*  if (ultimaTeclaPresionada != ultimaTeclaPresionadaAux) {
                    ultimaTeclaPresionadaAux = ultimaTeclaPresionada;
                    sendDatosWebSocket();
                } */
                
        //        sendDatosWebSocket();

            } ////FIN IF DE BOTONES DE DIRECCION
            



            //// Actualizacion de mascara en base a avionAliada_Activa x y
            ////Nota: aunque el movimiento de la avion se saque para eventos de boton, esta actualizacion se tiene que hacer constantemente en el update debido a la velocidad del avion (incersia)
            spotlight.x = avionEnemiga_Activa.x;
            spotlight.y = avionEnemiga_Activa.y+2;

            //// ACTUALIZAR TEXTO DE TABLERO AVION:
            tableroAvion.setText([
                'DATOS AVION:',
    /*           'x: ' + avionAliada_Activa.body.speed,
                'y: ' + avionAliada_Activa.y, */
                'Altura: ' + avionEnemiga_Activa.z,
                'Bomba: ' + avionEnemiga_Activa.tieneBomba,
                'Vida: ' + avionEnemiga_Activa.vida,
                'Combustible: ' + avionEnemiga_Activa.cantCombustible,
            ]);

            
            ////descuento de combustible
            if (avionEnemiga_Activa.enPista == false) {
                setDescuentoCombustibleAvionUPDATE(1, avionEnemiga_Activa);
            }
            
            /////---------FISICAS DE AVIONES y BALAS ENEMIGAS------
///////////////setMaksAvion(avionAliada_Activa);

            //// ACTUALIZAR TEXTO DE TABLERO BASE:
            tableroBase.setText([
                'DATOS BASE:',
                'Vida de Torre: ' + this.torreControlEnemiga.vida + '%',
                'Vida de Dep. Combustible: ' + this.deposito_combustibleEnemigo.vida + '%',
                'Vida de Dep. Bombas: ' + this.deposito_bombasEnemigo.vida + '%',
                'Cant. Artilleros: ' + Gpo_ArtillerosEnemigos.getTotalUsed(),
                'Cant. Aviones: ' + Gpo_AvionesEnemigos.getTotalUsed(),
            ]);

            
            if (avionEnemiga_Activa.vida > 0) {
                ////avionAliada_Activa = avionAliada_Activa;
                //////DETECTAR SI SE QUEDA SIN NAFTA EXPLOTA
                hayCombutible(avionEnemiga_Activa);
            }else{
                avionEnemiga_Activa.body.enable = false;
            }

            /*
            tiempoDelUpdate = time;
            */

            /////DISPARO AVION   ////se intento pasar a un evento pero empeora la jugabilidad
            if ((this.disparoAvion.isDown) && (tiempoDelUpdate > avionEnemiga_Activa.lastFiredAvion)){
                evento_avion_disparoEnemigo(tiempoDelUpdate, avionEnemiga_Activa);
            }
            //console.log(avionAliada_Activa.lastFiredBombaAvion);
            if ((this.disparoAvionBomba.isDown) && (tiempoDelUpdate > avionEnemiga_Activa.lastFiredBombaAvion)){
                evento_avion_disparoBombaEnemigo(tiempoDelUpdate, avionEnemiga_Activa);
            } 

            //////DETECTAR SI AVIONES ENEMIGAS PASAN SOBRE ALGUN ARTILLERO
            hayEnemigoEnRangoArtilleroAliado(time);
            hayAliadoEnRangoArtilleroEnemigo(time);

            //////DETECTAR SI AVIONES ENEMIGAS PASAN SOBRE LA TORRE
            hayEnemigoEnRangoTorreDeControlAliada(time);
            hayAliadoEnRangoTorreDeControlEnemiga(time);


        }else{
            if (mostrarAlert) {
                if (condicionPerdidaEnemigo) {
                    tableroAvion.visible = false;
                    tableroAvion = this.add.text(400, 280, 'Move the mouse', { font: '36px Courier Bold', fill: '#ff0000' });
                    tableroAvion.setText([
                        '¡¡¡PERDISTE!!',
                    ]);
                    tableroAvion.setDepth(9);
                    //// ACTUALIZAR TEXTO DE TABLERO BASE:
                    tableroBase.setText([
                        'DATOS BASE:',
                        'Vida de Torre: ' + this.torreControlEnemiga.vida + '%',
                        'Vida de Dep. Combustible: ' + this.deposito_combustibleEnemigo.vida + '%',
                        'Vida de Dep. Bombas: ' + this.deposito_bombasEnemigo.vida + '%',
                        'Cant. Artilleros: ' + Gpo_ArtillerosEnemigos.getTotalUsed(),
                        'Cant. Aviones: ' + Gpo_AvionesEnemigos.getTotalUsed(),
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
                console.log("Aprete la P");
                pausarJuego();
                break;
            case Phaser.Input.Keyboard.KeyCodes.R:
                reanudarJuego();
                break;
        }
    }
    function evento_tecla_avionDireccion_D(){
       sendDatosWebSocket();
    }
    function evento_tecla_avionDireccion_A(){
        sendDatosWebSocket();
    }
    function evento_tecla_avionDireccion_W(){
        sendDatosWebSocket();
    }
    function evento_tecla_avionDireccion_S(){
        sendDatosWebSocket();
    }

    function evento_tecla_avionAltura0(){
        //IF el avion esta arriba de la pista de la base
            //implmenetar transicion:: if era mas grande --> setTransicionScaleAumentar(this.pajaro,0.2,0.1);
            
            //// a medida que tengo una aviona activa, la guardo referencia y luego hago
            avionEnemiga_Activa.z=0;
            avionEnemiga_Activa.scaleX=0.1;
            avionEnemiga_Activa.scaleY=0.1;
        //console.log(avionAliada_Activa.depth);
            avionEnemiga_Activa.setDepth(0);

            ////se podria cambiar la sombra this.pajaroVision.setTexture("transparente");
                    
            //recargar nafta si hay en el tanque de la base
            //otras funciones como, si me quedan en mi panel 5 llaves, entonces tengo 5 reparaciones de vida
        
        /*
        Si pajaro z en 100 entonces 200, bajo vel, incremento gasto de gasolina, incremento tamaño avion
        si pajaro z es 200 entonces 100, incremento vel, descremento gasto de gasolina, decremento tamaño avion

            this.pajaro.z = ;*/

        ////Adentro resuelve cambios por altura y bomba
        setVelocidadAvion(avionEnemiga_Activa, avionActivaUltimaVX, avionActivaUltimaVY);
    }

    function evento_tecla_avionAltura1(){
        avionEnemiga_Activa.z=100;
        avionEnemiga_Activa.scaleX=0.2;
        avionEnemiga_Activa.scaleY=0.2;
        avionEnemiga_Activa.setDepth(1);
        ////Adentro resuelve cambios por altura y bomba
        setVelocidadAvion(avionEnemiga_Activa, avionActivaUltimaVX, avionActivaUltimaVY); 
    }

    function evento_tecla_avionAltura2(){
        avionEnemiga_Activa.z=200;
        avionEnemiga_Activa.scaleX=0.3;
        avionEnemiga_Activa.scaleY=0.3;
        avionEnemiga_Activa.setDepth(2);
        ///this.pajaro2.mask = new Phaser.Display.Masks.BitmapMask(this, spotlightb);
        ///this.piso.mask = new Phaser.Display.Masks.BitmapMask(this, spotlightb);
        ///this.torreta.mask = new Phaser.Display.Masks.BitmapMask(this, spotlightb);
        
        ////Adentro resuelve cambios por altura y bomba
        setVelocidadAvion(avionEnemiga_Activa, avionActivaUltimaVX, avionActivaUltimaVY);
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
        setDestroyAvionAndGetNewActive(avionEnemiga_Activa, Gpo_AvionesEnemigos);
        
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
    function hayEnemigoEnRangoTorreDeControlAliada(time){
        sceneJuego.torreControlAliada.HayEnemigo = false;
        sceneJuego.torreControlAliada.setTexture("torreControl_negro");
        var arrayAvionesEnemigas=Gpo_AvionesEnemigos.getChildren();
        if(sceneJuego.torreControlAliada.vida > 0){
            //para cada avion enemiga
            for (j = 0; j < arrayAvionesEnemigas.length; j++){
                if(arrayAvionesEnemigas[j].vida > 0){
                    //si distancia entre torre y avion < 100
                    distanciaEntreDosObjetos = distanceRound(sceneJuego.torreControlAliada, arrayAvionesEnemigas[j]);
                    
                    rangoMaximoVision = sceneJuego.torreControlAliada.rangoVision;
                    if (distanciaEntreDosObjetos <= rangoMaximoVision) {
                        //cambio att de torre
                        sceneJuego.torreControlAliada.hayEnemigo = true;                    
                        sceneJuego.torreControlAliada.setTexture("torreControl_negro_activo");  
                        ////DISPARA UN EVENTO --> disparo bala fisica, imagen y sonido 
                        //console.log("Entro");
                        evento_torreControl_disparo(time, sceneJuego.torreControlAliada, arrayAvionesEnemigas[j]);
                    }
                }
            }
        }
    }

    function hayAliadoEnRangoTorreDeControlEnemiga(time){
        sceneJuego.torreControlEnemiga.HayEnemigo = false;
        sceneJuego.torreControlEnemiga.setTexture("torreControl_rojo");
        var arrayAvionesAliadas=Gpo_AvionesAliados.getChildren();
        if(sceneJuego.torreControlEnemiga.vida > 0){
            //para cada avion aliada
            for (j = 0; j < arrayAvionesAliadas.length; j++){
                if(arrayAvionesAliadas[j].vida > 0){
                    //si distancia entre torre y avion < 100
                    distanciaEntreDosObjetos = distanceRound(sceneJuego.torreControlEnemiga, arrayAvionesAliadas[j]);
                    rangoMaximoVision = sceneJuego.torreControlEnemiga.rangoVision;
                    if (distanciaEntreDosObjetos <= rangoMaximoVision) {
                        //cambio att de torre
                        sceneJuego.torreControlEnemiga.hayEnemigo = true; 
                        sceneJuego.torreControlEnemiga.setTexture("torreControl_rojo_activo");  
                        ////DISPARA UN EVENTO --> disparo bala fisica, imagen y sonido 
                        evento_torreControl_disparo(time, sceneJuego.torreControlEnemiga, arrayAvionesAliadas[j]);
                    }
                }
            }
        }
    }

    function evento_torreControl_disparo(time, in_TorreOrigen, in_AvionDestino){
        juego_var_destinoCreacionBalas = 0;
        //console.log(in_TorreOrigen.bando);
        if ((time > in_TorreOrigen.lastFiredTorre)){
            if (in_TorreOrigen.bando == "Aliada") {
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
            x = sceneJuego.pistaAvionesAliada.x;
            y = sceneJuego.pistaAvionesAliada.y;
            newArtillero = sceneJuego.physics.add.image(x,y,"artillero_negro");//.setDisplayOrigin(0, 0);   //(juego_var_nav_width*0.90-88)
            newArtillero.bando = "Aliado";
            newArtillero.bullets_artillero_Aliada = sceneJuego.physics.add.group({
                classType: Bullet,
                maxSize: 3,
                runChildUpdate: true
            });
            newArtillero.bullets_artillero_Aliada.physicsBodyType = Phaser.Physics.ARCADE;
            artilleroSetearUbicacion(newArtillero);
        }else{
            x = sceneJuego.pistaAvionesEnemiga.x;
            y = sceneJuego.pistaAvionesEnemiga.y; 

            newArtillero = sceneJuego.physics.add.image(x,y,"artillero_rojo");//.setDisplayOrigin(0, 0);   //(juego_var_nav_width*0.90-88)
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

    function hayEnemigoEnRangoArtilleroAliado(time){  
        var arrayArtillerosAliados=Gpo_ArtillerosAliados.getChildren();
        var arrayAvionesEnemigas= Gpo_AvionesEnemigos.getChildren();
        //Para cada artillero del array
        for (i = 0; i < arrayArtillerosAliados.length; i++){
            if(arrayArtillerosAliados[i].vida > 0){
                arrayArtillerosAliados[i].HayEnemigo = false;
                arrayArtillerosAliados[i].setTexture("artillero_negro");
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
                                    arrayArtillerosAliados[i].setTexture("artillero_negro_activo");  
                                    ////DISPARA UN EVENTO --> disparo bala fisica, imagen y sonido 
                                    evento_artillero_disparo(time, arrayArtillerosAliados[i], arrayAvionesEnemigas[j]); 
                            
                            }
                        } 
                    }
                }
            }
        }
    }

    function hayAliadoEnRangoArtilleroEnemigo(time){  
        var arrayArtillerosEnemigos=Gpo_ArtillerosEnemigos.getChildren();
        var arrayAvionesAliadas= Gpo_AvionesAliados.getChildren();
        //Para cada artillero del array
        for (i = 0; i < arrayArtillerosEnemigos.length; i++){
            if(arrayArtillerosEnemigos[i].vida > 0){
                arrayArtillerosEnemigos[i].HayEnemigo = false;
                arrayArtillerosEnemigos[i].setTexture("artillero_rojo");
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
                                    arrayArtillerosEnemigos[i].setTexture("artillero_rojo_activo");  
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

            x = Phaser.Math.Between(campoEnemigo_x+50, campoEnemigo_w-50);
            y = Phaser.Math.Between(campoEnemigo_y+250, (mi_campo_y+campoEnemigo_h-30) );
            
        }else{
            mi_campo_x = campoEnemigo_x;
            mi_campo_y = campoEnemigo_y;
            mi_campo_w = campoEnemigo_w;
            mi_campo_h = campoEnemigo_h;
        
            mi_base_x = baseEnemiga_x;
            mi_base_y = baseEnemiga_y;
            mi_base_w = baseEnemiga_w;
            mi_base_h = baseEnemiga_h;
            x = Phaser.Math.Between(campoAliado_x+50, campoAliado_w-50);
            y = Phaser.Math.Between(campoAliado_y+30, (campoAliado_h-100) );

        }

        artillero.x = x;
        artillero.y = y;
        //console.log(artillero.bando + " " + x + " "+y);
    }

    function moverArtillero(artillero){
        if (artillero.bando == "Aliado")
            artillero.x=Phaser.Math.Between(campoAliado_x, campoAliado_w);
        else
            artillero.x=Phaser.Math.Between(campoEnemigo_x, campoEnemigo_w);
    } // artillero setear oncollide function with Base

    function moverGrupodeArtilleros(){

    var arrayArtilleros = Gpo_ArtillerosEnemigos.getChildren();
    var arrayAviones = Gpo_AvionesAliados.getChildren();
    var textura = "artillero_rojo";

    //Para cada artillero del array
    for (i = 0; i < arrayArtilleros.length; i++){
        if(arrayArtilleros[i].vida > 0){
            arrayArtilleros[i].HayEnemigo = false;
            arrayArtilleros[i].setTexture(textura);
            //para cada avion enemiga
            for (j = 0; j < arrayAviones.length; j++){
                if(arrayAviones[j].vida > 0){
                    if (arrayAviones[j].z < 200){
                        //si distancia entre artillero y avion < 100
                        distanciaEntreDosObjetos = distanceRound(arrayArtilleros[i], arrayAviones[j]); 
                        rangoMaximoVision = arrayArtilleros[i].rangoVision;
                        if (distanciaEntreDosObjetos > rangoMaximoVision) {
                            moverArtillero(arrayArtilleros[i]);
                        }
                    } 
                }
            }
        }

    }  
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
            x = sceneJuego.pistaAvionesAliada.x;
            y = sceneJuego.pistaAvionesAliada.y;

            avion = sceneJuego.physics.add.image(x, y,"avionNegro");
            avion.bando = "Aliado";
            avion.bullets_avion_Aliado = sceneJuego.physics.add.group({
                classType: Bullet,
                maxSize: 5,
                runChildUpdate: true
            });
            avion.bullets_avion_Aliado.physicsBodyType = Phaser.Physics.ARCADE; 

            //avion.mask = spotlight_instance;
        }else{
            x = sceneJuego.pistaAvionesEnemiga.x;
            y = sceneJuego.pistaAvionesEnemiga.y; 

            avion = sceneJuego.physics.add.image(x,y,"avionRojo");
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
                x = sceneJuego.pistaAvionesAliada.x;
                y = sceneJuego.pistaAvionesAliada.y;                
            }else{
                x = sceneJuego.pistaAvionesEnemiga.x;
                y = sceneJuego.pistaAvionesEnemiga.y; 

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
        avion.unidadDeVelocidad = 1;
        if (avion.tieneBomba) {
            avion.unidadDeVelocidad = avion.unidadDeVelocidad - avion.unidadDeVelocidad*0.50;
        }
        if(avion.z > 100){ ///altura maxima
            avion.unidadDeVelocidad = avion.unidadDeVelocidad - avion.unidadDeVelocidad*0.50;
        }
        if ( (avion.tieneBomba) && (avion.z > 100) ) {
            avion.unidadDeVelocidad = avion.unidadDeVelocidad*0.10;
        }
    }

    function setVelocidadAvion(avion, velX, velY){
        if (avion.tieneBomba) {
            velX = velX*0.80;
            velY = velY*0.80;
        } 
        if(avion.z <= 100){//baja altura
            avionActivaUltimaVX = velX;
            avionActivaUltimaVY = velY;
            avion.setVelocity(velX,velY);
        }else{ //200 es altura
            avionActivaUltimaVX = velX/2;
            avionActivaUltimaVY = velY/2;
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

    function evento_avion_disparoEnemigo(time, in_AvionOrigen){
        juego_var_destinoCreacionBalas = 2;
        var bullet = in_AvionOrigen.bullets_avion_Enemigo.get();
        if (bullet)
        {
            bullet.fire(in_AvionOrigen);
            snd_metralleta.play();
            in_AvionOrigen.lastFiredAvion = time + 100;
        }
    }
    function evento_avion_disparoAliado(time, in_AvionOrigen){
        juego_var_destinoCreacionBalas = 2;
        var bullet = in_AvionOrigen.bullets_avion_Aliado.get();
        if (bullet)
        {
            bullet.fire(in_AvionOrigen);
            snd_metralleta.play();
            in_AvionOrigen.lastFiredAvion = time + 100;
        }
    }

    function evento_avion_disparoBombaEnemigo(time, in_AvionOrigen){
        ///juego_var_destinoCreacionBalas = 2; // --> para que era esto???
    //console.log(in_AvionOrigen.z );
        if (in_AvionOrigen.z == 200 && in_AvionOrigen.tieneBomba){
            bombaBulletE.x=in_AvionOrigen.x;
            bombaBulletE.y=in_AvionOrigen.y;
            snd_caidaBomba.play();
            //console.log(bombaBulletA);
            tweenBombaE = sceneJuego.tweens.add({
                targets: [ bombaBulletE ],
                //x:'+=100',
                scaleX:'-=5',
                scaleY:'-=5',
                duration: 2000,
                ease: 'Power1'
            });
            snd_caidaBomba.stop();
            bombaBulletE.body.enable=true;
            timerBombaE = sceneJuego.time.addEvent({
                delay: 2000,   // EN MILI SEGUNDOS
                //callback: callback,
                callback: enmascararBomba,
                args: [bombaBulletE,tweenBombaE],
                callbackScope: bombaBulletE,tweenBombaE,
                loop: true,
            });
            //in_AvionOrigen.tieneBomba=false; --> ACTIVAR PARA QUE SOLO DISPARE UNA BOMBA!!!
        }
    }
    function evento_avion_disparoBombaAliado(time, in_AvionOrigen){
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
/*     //VISTA LATERAL
    sceneJuego.pisoLateral = sceneJuego.add.image(campoAliado_w/2, ((campoAliado_h+campoEnemigo_h)/2), 'pisoLateral');
    sceneJuego.pisoLateral.setDepth(100);
    sceneJuego.pisoLateral.setVisible(false);
    sceneJuego.pisoLateral.displayWidth    = campoAliado_w;
    sceneJuego.pisoLateral.displayHeight   = campoAliado_h+campoEnemigo_h;
    avionActivaAliadaLateral = sceneJuego.add.image(campoAliado_w/2, ((campoAliado_h+campoEnemigo_h)/2), 'avionNegroLateral');
    avionActivaAliadaLateral.setVisible(false);
    avionActivaAliadaLateral.setDepth(100);
    sceneJuego.torreControlAliada = sceneJuego.add.image(0, ((campoAliado_h+campoEnemigo_h)/2), 'torreControl_negroLateral');
    sceneJuego.torreControlAliada.setDepth(100); */
}

function reanudarJuego ()
{
	if (pausar)
	{
		pausar = false;
		/* sonidoReanudar.play();
		opvReanudar();	
		musicaFondo.play();*/
		
		sceneJuego.pisoLateral.setVisible(false);
        avionActivaAliadaLateral.setVisible(false);
        sceneJuego.torreControlAliada.setVisible(false);
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
			sceneJuego.pisoLateral.setVisible(true);
            avionActivaAliadaLateral.setVisible(true);
            sceneJuego.torreControlAliada.setVisible(true);
            
            var sgm_pantalla = (campoAliado_h+campoEnemigo_h)/3;
            switch (avionAliada_Activa.z){
                case 0:
                    avionActivaAliadaLateral.y=(campoAliado_h+campoEnemigo_h)-70;
                    avionActivaAliadaLateral.scaleX=0.8;
                    avionActivaAliadaLateral.scaleY=0.8;
                    break;
                case 100:
                    avionActivaAliadaLateral.y=(campoAliado_h+campoEnemigo_h)-300;
                    avionActivaAliadaLateral.scaleX=0.5;
                    avionActivaAliadaLateral.scaleY=0.5;
                    break;
                case 200:
                    avionActivaAliadaLateral.y=(campoAliado_h+campoEnemigo_h)-500;
                    avionActivaAliadaLateral.scaleX=0.3;
                    avionActivaAliadaLateral.scaleY=0.3;
                    break;
            }			
            avionActivaAliadaLateral.x = 200;
            /// DIBUJAR LOS ELEMENTOS DE LA BASE.
            sceneJuego.torreControlAliada.x=200;
            sceneJuego.torreControlAliada.y=(campoAliado_h+campoEnemigo_h)-70;
	}else{
        sceneJuego.pisoLateral.setVisible(false);
    }
}
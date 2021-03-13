<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="FrontEnd/JuegoSaviorsOfTheSomme/stylesContenedor.css">
    <link rel="icon" href="FrontEnd/JuegoSaviorsOfTheSomme/assets/images/web/favicon.ico">
    <title>Saviors of the Somme</title>
    
</head>
    <body>
        <div id="phaser-app"></div>

        <script src="FrontEnd/node_modules/phaser/dist/phaser-arcade-physics.js"></script>
        <script src="FrontEnd/node_modules/phaser/dist/phaser.js"></script>
        

        <script>
            pepito=1;
            juego_var_sceneJuego = 0;

            juego_var_nav_width   = 1000;
            juego_var_nav_height  = 600;

            juego_var_rangoVisionAvion      = 100;
            juego_var_rangoVisionArtillero  = 100;
            juego_var_rangoVisionTorre      = 200;

            juego_var_destinoCreacionBalas = 0;
            ////0-torre
            ////1-artillero
            ////2-avion

            juego_var_isPartidaNueva = true;

            juego_var_Aliado_cantidadAviones        = 4;
            juego_var_Aliado_cantidadArtilleros     = 6;
            juego_var_Aliado_tieneTorreDeControl    = 1;
            juego_var_Aliado_tieneDepCombustible    = 1;
            juego_var_Aliado_tieneDepBombas         = 1;

            juego_var_Enemigo_cantidadAviones       = 4;
            juego_var_Enemigo_cantidadArtilleros    = 6;
            juego_var_Enemigo_tieneTorreDeControl   = 1;
            juego_var_Enemigo_tieneDepCombustible   = 1;
            juego_var_Enemigo_tieneDepBombas        = 1;


 

/*             global_var_s3_x= 0;
            global_var_s3_y= 0;
            global_var_s3_w= 0;
            global_var_s3_h= 0; 
            global_var_s4_x= 0;
            global_var_s4_y= 0;
            global_var_s4_w= 0;
            global_var_s4_h= 0; 
            global_var_s5_x= 0;
            global_var_s5_y= 0;
            global_var_s5_w= 0;
            global_var_s5_h= 0; 
            global_var_s7_x= 0;
            global_var_s7_y= 0;
            global_var_s7_w= 0;
            global_var_s7_h= 0;  */


    /*         global_var_s1_x= 0;
            global_var_s1_y= 0;
            global_var_s1_w= 0;
            global_var_s1_h= 0; 
            
            global_var_s2_x= 0;
            global_var_s2_y= 0;
            global_var_s2_w= 0;
            global_var_s2_h= 0; 
            

            global_var_s6_x= 0;
            global_var_s6_y= 0;
            global_var_s6_w= 0;
            global_var_s6_h= 0;  */
        </script> 


        <script src="FrontEnd/JuegoSaviorsOfTheSomme/src/obj_bala.js"></script>
<!-- 

        <script src="./src/obj_artillero.js"></script>
        <script src="./src/obj_avion.js"></script>
        <script src="./src/obj_despositoCombustible.js"></script>
        <script src="./src/obj_despositoBombas.js"></script>
        <script src="./src/obj_torreControl.js"></script>
        <script src="./src/obj_base.js"></script>
        <script src="./src/obj_campo.js"></script> -->

        <script src="FrontEnd/JuegoSaviorsOfTheSomme/src/s7_campoBatalla.js"></script>
        <!--<script src="./src/s7_campoBatalla2.js"></script>
         <script src="./src/s6_lineaFrontera.js"></script>
        <script src="./src/s5_menu.js"></script>
        <script src="./src/s4_tableroInfo.js"></script>
        <script src="./src/s3_vistaLateral.js"></script>
        <script src="./src/s2_campoEnemigo.js"></script>
        <script src="./src/s1_miCampo.js"></script> -->
        <script src="FrontEnd/JuegoSaviorsOfTheSomme/src/juegoInit.js" type="module"></script>


    </body>
</html>
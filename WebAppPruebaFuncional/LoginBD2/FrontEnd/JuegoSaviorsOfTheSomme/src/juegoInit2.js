/////// CREACION DE VARIABLES

////var juegoPrincipal;

////




////---------------------
///se podrian declarar variables globales aca...
   
import s1_miCampo from './s1_miCampo.js';
import s2_campoEnemigo from './s2_campoEnemigo.js';
import s6_lineaFrontera from './s6_lineaFrontera.js'; 
import s3_vistaLateral from './s3_vistaLateral.js';
import s4_tableroInfo from './s4_tableroInfo.js';
import s5_menu from './s5_menu.js'; 
//import s7_campoBatalla from './s7_campoBatalla.js';
import s7_campoBatalla2 from './s7_campoBatalla2.js';

/* juego_var_nav_width = window.innerWidth
|| document.documentElement.clientWidth
|| document.body.clientWidth;

juego_var_nav_height = window.innerHeight
|| document.documentElement.clientHeight
|| document.body.clientHeight;
  */
/* alert(juego_var_nav_width   +"  x   "+juego_var_nav_height);
 */
/* juego_var_nav_width = 1000;
juego_var_nav_height = 600; */

const config = {
	transparent: true,
	backgroundColor: 'rgba(0, 0, 0, 0)',
    type: Phaser.AUTO,
    parent: 'phaser-app',
    width: juego_var_nav_width, //(juego_var_nav_width) - 20,
    height: juego_var_nav_height, //(juego_var_nav_height) - 20,   
    scene: [s7_campoBatalla2], //s3_vistaLateral, s4_tableroInfo, s5_menu
	physics: {
		default: "arcade",
		arcade: {
		  debug: false,
		},
	}
};

var game = new Phaser.Game(config);


/////----------------------------------------------------------------------------------
/////----------------------------------------------------------------------------------
/////----------------------------------------------------------------------------------
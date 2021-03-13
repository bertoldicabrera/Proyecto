
  var button;
  var text;

export default class s5_menu extends Phaser.Scene {
    constructor() {
        super({key: "s5_menu", active: false});
    }
    preload() {
        this.load.image("backMenu","./assets/images/mapaCapaOculta/backMenu.png");
        
        this.load.image("popUpBackground","./assets/images/texturas/popUpBackground.png");
        
        this.load.image("arrowBack","./assets/images/web/arrowBack.png");
    
    
    
    }
   
  

    create() {
        var back = this.add.image(0, 0, 'backMenu');
        back.scaleX=15;
        back.scaleY=15;

        /* 
        var rt = this.add.renderTexture(10, 10, 100, 100);

        var frame = new Frame(texture, "hola", sourceIndex, x, y, width, height)
        var rt = this.add.renderTexture(50, 50, 200, 200, "back", frame);
         */


       /*  let graphics = this.add.graphics();
        graphics.fillStyle(0xc9ad75, 1);
        graphics.fillRect(global_var_s5_x, global_var_s5_y, global_var_s5_w, global_var_s5_h);
 */




         var popUpBackground = this.add.image(global_var_s5_x, global_var_s5_y, 'popUpBackground');        
         popUpBackground.scaleX=1.5;
         popUpBackground.scaleY=1.5;


        text = this.add.text(global_var_s5_x, global_var_s5_y, "s5_menu", { font: "16px Courier", fill: "#213859s" });


        ////agregar html con formulario y botones
        ////http://phaser.io/examples/v3/view/game-objects/dom-element/form-input

        ////https://phaser.io/examples/v3/view/input/dom-events/pointer-button-status
    }

}